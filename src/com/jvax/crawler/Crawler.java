package com.jvax.crawler;
import com.jvax.object.Topic;
import com.jvax.command.*;
import java.util.*;
import java.io.*;
import org.apache.http.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * 網路爬蟲
 * 
 */
public abstract class Crawler implements Command{

    protected abstract void parseHTML(String HTML);
    protected abstract void parseHTML();

    /**
     * 初始化(設定HttpClient, 可抓取SSL)
     */
    public void init() throws Exception{
        // TopicBucket = new Vector<Topic>();
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
		this.httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
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

    /**
     * 抓取網頁內容回應
     */
    protected String getResponse() throws IOException{
	try {
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
}
