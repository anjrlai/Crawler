package com.jvax.crawler.mobile01;
import java.util.*;
import java.util.regex.*;
import com.jvax.crawler.Crawler;
import com.jvax.object.*;
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
    private String test_url_list         = "https://www.mobile01.com/topiclist.php?f=507";
    private final String WebpageBase     = "https://www.mobile01.com/";
    private String nextPageUrl           = null;
    private String ListUrl               = null;
    private int ArticleCount             = 30;
    private int UrlListLimit             = 100;
    private Hashtable<String, String> UrlList;
    private Vector<String> Urls;
    /**
     * Mobile01網站tokens
     */
    private String hyperlink             = "href";
    private String boardname_token       = "title";
    private String article_token         = "article";
    private String space_token           = " ";

    private String rlistcontainer_token  = "td.subject";
    private String title_a_token         = "a.topic_gen";
    private String btn_group_token       = "div.pagination";
    private String btn_token             = "span.disable";
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
    private int TotalPage = -1;
    private int CurrentPage = -1;

    /**
     * 初始化
     */
    public void init() throws Exception{
        super.init();
        this.UrlList = new Hashtable<String, String>();
        this.Urls    = new Vector<String>();
    }

    /**
     * 指令組成Compose [過濾網址、設定網址、抓取網頁]
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
        // Topic temp = super.getTopic();
        // System.out.println("getReplyCount:::"+temp.getReplyCount());
    };

    public void crawlArticle(String Url){
        Url=filterUrl(Url);
        setUrl(Url);
        
        // System.out.println("super.setTopicUrl("+Url+");");
        super.setTopicUrl(Url);
        parseArticle();
        while(nextPageUrl!=null)
        {
            // System.out.println("nextPageUrl::::setUrl("+nextPageUrl+");");
            setUrl(nextPageUrl);
            parseArticle();
        }
    };

    public Hashtable<String, String> crawlArticleList(){
        setUrl(this.test_url_list);
        parseArticleList();
        return UrlList;
    };

    public Hashtable<String, String> crawlArticleList(String Url){
        setUrl(Url);
        parseArticleList();
        return UrlList;
    };

    public Hashtable<String, String> crawlArticleList(String Url, int ArticleCount){
        setListUrl(Url);
        // setUrl(Url);
        setArticleCount(ArticleCount);
        parseArticleList();
        
        while(UrlList.size()<ArticleCount)
        {
            setUrl(this.nextPageUrl);
            parseArticleList();
        }
        
        return UrlList;
    };

    public Vector<String> getUrls(){
        Enumeration names;
        String key;
        names = this.UrlList.keys();
        this.Urls.removeAllElements();
        while(names.hasMoreElements()) {
                key = (String) names.nextElement();
                this.Urls.addElement((String)UrlList.get(key));
        }
        return this.Urls;
    };  

    private String fetchUrl(){
        return this.test_url;
        
    };

    protected void setUrl(String Url){
        super.setUrl(Url);
    }
    private void setListUrl(String Url){
        this.ListUrl = Url;
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
// 			System.out.println(ArticleContent);
            if(FloorNum==1)
            {
                this.setUserId(chopUserId(article));
                this.setBoardName(chopBoardName(this.xmlDoc));
                this.setSubject(chopSubject(article));
                this.setPostDate(chopPostDate(article));
//                System.out.println("setPostDate("+chopPostDate(article)+");");
                this.setContent(ArticleContent);
                this.setCrawledDate();
            }
            else
            {
    			this.setReplyTag("#"+FloorNum);
	    		this.setReplyUserId(article.select("div.fn").text());
		    	this.setReplyContent(ArticleContent);
                this.setReplyPostDate(chopPostDate(article));
                // System.out.println("setPostDate("+chopPostDate(article)+");");
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
		if(this.nextPageUrl==null)
		{
		    this.addToContainer();
		}
    }
    protected void parseArticleList(){
        try{
            this.parseArticleList(getResponse());
        }catch(Exception e){
            
        }
    }
    protected void parseArticleList(String HTML){
		this.xmlDoc = Jsoup.parse(HTML);
		parseArticleUrls();
		parseTotalPage();
        //[ToDos].......
        //..............
        //..............
    }
    private void parseArticleUrls(){
		Elements r_list = xmlDoc.select(rlistcontainer_token);
        Elements articles = r_list.select(title_a_token);
        for (Element article : articles)
        {
          if(UrlList.size()< ArticleCount)
          {
            System.out.println("::"+article.text() +"\t"+ WebpageBase+article.attr(hyperlink));
            UrlList.put(article.text(),WebpageBase+article.attr(hyperlink));
            // Urls.addElement(WebpageBase+article.attr(hyperlink));
          }
        }        
    };
    private void parseNextPage(){
    };
    private void parseTotalPage(){
		Elements btns = xmlDoc.select(btn_group_token);
        Element btn = btns.get(btns.size()-1);
        String[] PageID = btn.text().split(space_token);
        this.CurrentPage = Integer.parseInt(btn.select(btn_token).text());
        this.TotalPage = Integer.parseInt(PageID[PageID.length-1]);
        this.nextPageUrl = this.ListUrl+"&p="+(this.CurrentPage+1);
    };
    
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
    private void setArticleCount(int ArticleCount){
        this.ArticleCount = ArticleCount;
    }
    private int getArticleCount(){
        return this.ArticleCount;
    }
    public void addToContainer(){
        super.addTopic();
    }

}
