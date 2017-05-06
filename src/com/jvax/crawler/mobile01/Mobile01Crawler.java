package com.jvax.crawler.mobile01;
import java.util.*;
import java.util.regex.*;
import com.jvax.crawler.Crawler;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.text.SimpleDateFormat;
/**
 * Mobile01網路爬蟲
 * 
 */
public class Mobile01Crawler extends Crawler{

    private Document xmlDoc;

    private String test_url              = "https://www.mobile01.com/topicdetail.php?f=507&t=5133833";
    private String WebpageBase           = "https://www.mobile01.com/";
    private String nextPageUrl           = null;
    /**
     * Mobile01網站tokens
     */
    private String boardname_token       = "title";
    private String article_token         = "article";

    private String postdate_token        = "div.date";
    private String content_token         = "div.single-post-content";

    private String reply_quote_token     = "blockquote";
    private String article_ID_token      = "li";
    private String user_ID_token         = "div.fn";
    private String subject_token         = "h1.topic";

    private String div_navbar            = "div.navbar";
    private String div_pagination_a      = "div.pagination a";
    private String nextPageUrl_token     = "下一頁";

    /**
     * 使用Regular Expression過濾網址
     */
    private String filterPattern = "https://www.mobile01.com[-a-zA-Z0-9+@#/=~_|.;\\p{Punct}]*";
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
        parseArticle();
         while(nextPageUrl!=null)
         {
            setUrl(nextPageUrl);
            parseArticle();
         }
    };

    public void crawlArticle(String Url){
        Url=filterUrl(Url);
        setUrl(Url);
        parseArticle();
        while(nextPageUrl!=null)
        {
            setUrl(nextPageUrl);
            parseArticle();
        }
    };

    public void crawlArticleList(String Url){
        
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
     * Only mobile01.com urls could be left;
     */
    private String filterUrl(String Url){
        return (Pattern.matches(this.filterPattern, Url))?Url:null;
    };
    
    protected void parseArticle(){
        try{
            this.parseArticle(getResponse());
        }catch(Exception e){
            
        }
    }

    /**
     * 抓出本文、回文內容
     */
    protected void parseArticle(String HTML){
		this.xmlDoc = Jsoup.parse(HTML);
        nextPageUrl=null;


		Elements articles = xmlDoc.select(article_token);
        // this.setReplyCount(articles.size()-1);
		for (Element article : articles)
		{
            int FloorNum = chopFloorNum(article);
			String ArticleContent = chopArticleContent(article);
			String quote = chopArticleQuote(article);
			ArticleContent=(quote.length()==0)?ArticleContent:ArticleContent.replace(quote, "q["+quote+"]");
            if(FloorNum==1)
            {
                this.setUserId(chopUserId(article));
                this.setBoardName(chopBoardName(this.xmlDoc));
                this.setSubject(chopSubject(article));
                this.setPostDate(chopPostDate(article));
                this.setContent(ArticleContent);
                this.setCrawledDate();
            }
            else
            {
    			this.setReplyTag("#"+FloorNum);
	    		this.setReplyUserId(article.select("div.fn").text());
		    	this.setReplyContent(ArticleContent);
                this.setReplyPostDate(chopPostDate(article));
			    this.setReplyCrawledDate();
    			this.addReply();
	    		this.setReplyCount(FloorNum-1);
            }
        }
		Elements links = xmlDoc.select(div_navbar).select(div_pagination_a);
		for (Element link : links)
		{
			if(link.text().indexOf(nextPageUrl_token)>-1) 
			{
			    nextPageUrl=WebpageBase+""+link.attr("href");
            }
		}
    }


    private String skipMetaString(String Content, String MetaString){
        return Content=(Content.indexOf(MetaString)>-1)?Content.substring(Content.indexOf(MetaString)+MetaString.length(), Content.length()):Content;
    }
    private String chopContent(String Content, String terminate_token){
        return Content=(Content.indexOf(terminate_token)>-1)?Content.substring(0, Content.indexOf(terminate_token)):Content;
    }
    private String chopBoardName(Document xmlDoc){
        return chopBoardName(xmlDoc.select(boardname_token).text(), "-");
    }
    private String chopBoardName(String BoardName, String token){
        return BoardName.substring(0, (BoardName.indexOf(token)>-1)?BoardName.indexOf(token)-1:BoardName.length());
    }
    private String chopPostDate(Element article){
        return chopPostDate(article.select(postdate_token).text(), "#");
    }
    private String chopPostDate(String PostDate, String token){
        PostDate=PostDate.substring(0, (PostDate.indexOf(token)>-1)?PostDate.indexOf(token)-2:PostDate.length());
        return PostDate+":00";
    }
    private int chopFloorNum(Element article){
        return chopFloorNum(article.select(postdate_token).text(), "#");
    }
    private int chopFloorNum(String FloorNum, String token){
        return Integer.parseInt(FloorNum.substring(FloorNum.indexOf(token)+1, FloorNum.length()));
    }


    private String chopArticleContent(Element article){
        return article.select(content_token).text();
    }

    private String chopArticleQuote(Element article){
        return article.select(content_token).select(reply_quote_token).text();
    }

    private String chopArticleID(Element article){
		String tmp = article.select(article_ID_token).text();
		String[] tmpinfo = tmp.split(" ");
		return tmpinfo[1];
    }
    private String chopUserId(Element article){
        return article.select(user_ID_token).text();
    }
    private String chopSubject(Element article){
        return xmlDoc.select(subject_token).text();
    }
    

}
