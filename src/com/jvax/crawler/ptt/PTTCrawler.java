package com.jvax.crawler.ptt;
import java.util.*;
import java.util.regex.*;
import com.jvax.crawler.Crawler;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.text.SimpleDateFormat;

/**
 * PTT網路爬蟲
 */
public class PTTCrawler extends Crawler{

    private Document xmlDoc;

    private String test_url              = "https://www.ptt.cc/bbs/Lifeismoney/M.1493871419.A.573.html";
    /**
     * PTT網站tokens
     */
    private String terminate_token       = "※ 發信站";
    private String topic_meta_token      = "span.article-meta-value";
    private String article_content_token = "div#main-container";
    private String reply_token           = "div.push";

    private String reply_tag_token       = "span.push-tag";
    private String reply_userid_token    = "span.push-userid";
    private String reply_content_token   = "span.push-content";
    private String reply_postdate_token  = "span.push-ipdatetime";
    /**
     * 使用Regular Expression過濾網址
     */
    private String filterPattern = "https://www.ptt.cc[-a-zA-Z0-9+@#/=~_|.;]*";
    private String MetasStr;

    /**
     * 初始化
     */
    public void init() throws Exception{
        super.init();
    }

    /**
     * 指令組成Compose
     * 過濾網址
     * 設定網址
     * 抓取網頁
     */
    public void crawlArticle(){
        String Url=filterUrl(fetchUrl());
        setUrl(Url);
        parseHTML();
    };

    public void crawlArticle(String Url){
        Url=filterUrl(Url);
        setUrl(Url);
        parseHTML();
    };

    private String fetchUrl(){
        return this.test_url;
        
    };

    protected void setUrl(String Url){
        super.setUrl(Url);
    }
    protected String getResponse(){
        try {
                return super.getResponse();
        } catch(Exception e) {
        }
        return null;
    }
    /*
     * Only ptt.cc urls could be left;
     */
    private String filterUrl(String Url){
        return (Pattern.matches(this.filterPattern, Url))?Url:null;
    };
    
    protected void parseHTML(){
        try{
            this.parseHTML(getResponse());
        }catch(Exception e){
            
        }
    }
    protected void parseHTML(String HTML){
		this.xmlDoc = Jsoup.parse(HTML);
        parseMetaData();
        parseContent();
        parseReplies();

    }

    /**
     * 抓出上方Meta訊息
     */
    private void parseMetaData(){
		Elements metas = xmlDoc.select(this.topic_meta_token);
        String UserIdStrData = metas.eq(0).text().trim();
        String BoardStrData  = metas.eq(1).text().trim();
        String TitleStrData  = metas.eq(2).text().trim();
        String DateStrData   = metas.eq(3).text().trim();
		MetasStr = "作者"+metas.eq(0).text()+" 看板"+metas.eq(1).text()+" 標題"+metas.eq(2).text()+" 時間"+metas.eq(3).text();

        this.setUserId(UserIdStrData);
        this.setBoardName(BoardStrData);
        this.setSubject(TitleStrData);
        // /*--- DateStrData Need to be Process!!!---*/
        this.setPostDate(DateStrData);
        
    };

    /**
     * 抓出本文內容
     */
    private void parseContent(){
		Elements cont = this.xmlDoc.select(this.article_content_token);
		String Content = cont.text();
		Content=skipMetaString(Content, MetasStr);
		Content=chopContent(Content, terminate_token);
        this.setContent(Content);

        this.setCrawledDate();
    }

    /**
     * 抓出回文內容
     */
    private void parseReplies(){
        Elements pushes = xmlDoc.select(this.reply_token);
        this.setReplyCount(pushes.size());
        for (Element push : pushes)
        {
			this.setReplyTag(push.select(this.reply_tag_token).text());
			this.setReplyUserId(push.select(this.reply_userid_token).text());
			String ReplyContent = push.select(this.reply_content_token).text();
			ReplyContent=ReplyContent.replaceAll("'", "\"");
			ReplyContent=ReplyContent.substring(1, ReplyContent.length());
			this.setReplyContent(ReplyContent);
			this.setReplyPostDate(push.select(this.reply_postdate_token).text());
			//----------------
			this.setReplyCrawledDate();
			this.addReply();
        }
    }

    private String skipMetaString(String Content, String MetaString){
        return Content=(Content.indexOf(MetaString)>-1)?Content.substring(Content.indexOf(MetaString)+MetaString.length(), Content.length()):Content;
    }
    private String chopContent(String Content, String terminate_token){
        return Content=(Content.indexOf(terminate_token)>-1)?Content.substring(0, Content.indexOf(terminate_token)):Content;
    }
}
