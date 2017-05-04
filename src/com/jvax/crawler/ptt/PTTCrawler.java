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


    private String terminateToken = "※ 發信站";
    /**
     * 使用Regular Expression過濾網址
     */
    private String filterPattern = "https://www.ptt.cc[-a-zA-Z0-9+@#/=~_|.;]*";

    /**
     * 初始化
     */
    public void init() throws Exception{
        super.init();
    }
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

    public void execute(){
        String Url=filterUrl(fetchUrl());
        setUrl(Url);
        parseHTML();
    };

    public void execute(String Url){
        Url=filterUrl(Url);
        setUrl(Url);
        parseHTML();
    };

    private String fetchUrl(){
        return "https://www.ptt.cc/bbs/Lifeismoney/M.1492487566.A.966.html";
        
    };
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
		Document xmlDoc = Jsoup.parse(HTML);
		
		
        /**
         * 抓出上方Meta訊息
         */
		Elements metas = xmlDoc.select("span.article-meta-value");
        String UserIdStrData = metas.eq(0).text().trim();
        String BoardStrData  = metas.eq(1).text().trim();
        String TitleStrData  = metas.eq(2).text().trim();
        String DateStrData   = metas.eq(3).text().trim();
		String MetasStr = "作者"+metas.eq(0).text()+" 看板"+metas.eq(1).text()+" 標題"+metas.eq(2).text()+" 時間"+metas.eq(3).text();
        System.out.println(MetasStr);

        /**
         * 抓出本文內容
         */
		Elements cont = xmlDoc.select("div#main-container");
		String Content = cont.text();
		Content=skipMetaString(Content, MetasStr);
		Content=parseContent(Content, terminateToken);
        System.out.println(Content);

        /**
         * 抓出回文內容
         */

    }

    private String skipMetaString(String Content, String MetaString){
        return Content=(Content.indexOf(MetaString)>-1)?Content.substring(Content.indexOf(MetaString)+MetaString.length(), Content.length()):Content;
    }
    private String parseContent(String Content, String terminateToken){
        return Content=(Content.indexOf(terminateToken)>-1)?Content.substring(0, Content.indexOf(terminateToken)):Content;
    }
}
