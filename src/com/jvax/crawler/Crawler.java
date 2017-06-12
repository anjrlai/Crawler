package com.jvax.crawler;
import com.jvax.object.*;
import com.jvax.format.*;
import com.jvax.command.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.net.*;
import org.apache.http.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.config.*;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * 網路爬蟲
 * 
 */
public abstract class Crawler implements CrawlerCommand{

    /**
     * abstract methods 給繼承的Crawler進行實做 implement
     */
    protected abstract void parseArticle(String HTML);
    protected abstract void parseArticle();
    protected abstract void parseArticleList(String HTML);
    protected abstract void parseArticleList();
    
    private Topic topic;
    private Vector<Topic> topics;
    private Reply reply;
    private Date now;
    private Format format;
    private SimpleDateFormat sdf;
    private HttpHost proxy;
    private RequestConfig config;
    private String DateTimeStringPattern = "yyyy-MM-dd HH:mm:ss";
    /**
     * 初始化(設定HttpClient, 可抓取SSL)
     */
    public void init() throws Exception{
        // TopicBucket = new Vector<Topic>();
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
		this.httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		this.proxy = null;
		this.topic = new Topic();
		this.topics = new Vector<Topic>();
		this.reply = new Reply();
		now = new Date();
		sdf = new SimpleDateFormat(this.DateTimeStringPattern);
    }

    // private Vector<Topic> TopicBucket;
    private String Url;
    private String responseContent;
	private HttpGet httpget;
	private CloseableHttpClient httpclient;
	private CloseableHttpResponse response;

    /**
     * 設定文章網址
     */
    protected void setUrl(String Url){
        this.Url = Url;
    };
    /**
     * 讀取文章網址
     */
    public String getUrl(){
        return this.Url;
    };

    public void configProxy(String proxyIP, int proxyPort, String proxyType){
        this.proxy  = new HttpHost(proxyIP, proxyPort, proxyType);
        this.config = RequestConfig.custom().setProxy(this.proxy).build();
    };

    protected void setTopicUrl(String Url){
        this.topic.setUrl(Url);
    };

    protected void setUserId(String UserId){
        this.topic.setUserId(UserId);
    };

    protected void setBoardName(String BoardName){
        this.topic.setBoardName(BoardName);
    };

    public String getBoardName(){
        return this.topic.getBoardName();
    };

    protected void setSubject(String Subject){
        this.topic.setSubject(Subject);
    };

    protected void setPostDate(String PostDate){
        this.topic.setPostDate(PostDate);
    };

    protected void setContent(String Content){
        this.topic.setContent(Content);
    };

    protected void setReplyCount(int ReplyCount){
        this.topic.setReplyCount(ReplyCount);
    };

    protected void setCrawledDate(){
        this.topic.setCrawledDate(sdf.format(now));
    };

    protected void setReplyTag(String Tag){
        this.reply.setTag(Tag);
    };

    protected void setReplyUserId(String UserId){
        this.reply.setUserId(UserId);
    };

    protected void setReplyContent(String Content){
        this.reply.setContent(Content);
    };

    protected void setReplyPostDate(String PostDate){
        this.reply.setPostDate(PostDate);
    };

    protected void setReplyCrawledDate(){
        this.reply.setCrawledDate(sdf.format(now));
    };

    protected void addReply(){
        this.topic.addReply(this.reply);
        this.reply = new Reply();
    };
    protected void addTopic(){
        this.addTopic(this.topic);
    };
    private void addTopic(Topic topic){
        this.topics.add(topic);
        this.topic = new Topic();
    };
    private void removeTopic(Topic topic){
        this.topics.remove(topic);
    };

    /**
     * 抓取網頁內容回應
     */
    protected String getResponse() throws Exception{
	try {
            /* proxy implement */	    
            // HttpHost proxy = new HttpHost("127.0.0.1", 8080, "http");
            // RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            // HttpHost target = new HttpHost("httpbin.org", 443, "https");
            // HttpGet request = new HttpGet("/");
            // request.setConfig(config);
            // System.out.println("Executing request " + request.getRequestLine() + " to " + target + " via " + proxy);
            // CloseableHttpResponse response = httpclient.execute(target, request);
            if(proxy!=null)
            {
                
            }
            this.sleep(2000);
            this.httpget = new HttpGet(this.Url);

            this.response = this.httpclient.execute(httpget);
            try {
                HttpEntity entity = this.response.getEntity();
                if ( entity != null ) {
                    InputStream instream = entity.getContent();
					try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "utf-8"));
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null)
						sb.append(line + "\n");
					this.responseContent = sb.toString();
                    } finally {
                        instream.close();
                    }
					}
            } finally {
				
            }
		} finally {
				
        }
        return responseContent;
    };
    public Vector<Topic> getTopics(){
        return this.topics;
    }

    public final void sleep(long milliseconds) throws Exception 
    {
		try {
		// thread to sleep for 1000 milliseconds
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    public void setFormat(Format format){
        this.format = format;
    }
    public void exportToFile(){
        this.format.setData(this.topics);
        this.format.exportToFile(((Topic)this.topics.get(0)).getBoardName());
    }

}
