package com.jvax.crawler.appledaily;
import com.jvax.crawler.Crawler;
import com.jvax.command.*;
import java.util.*;
/**
 * 網路爬蟲介面
 * 
 */
public class AppleDailyCrawler extends Crawler{
    
    public void init(){}
    public String getResponse(){
        return null;
    }
    protected void parseArticle(){}
    protected void parseArticle(String HTML){}
    protected void parseArticleList(){};
    protected void parseArticleList(String HTML){};
    
    public void crawlArticle(){};
    public void crawlArticle(String Url){};
    public Hashtable<String, String> crawlArticleList(){return null;};
    public Hashtable<String, String> crawlArticleList(String Url){return null;};
    public Vector<String> getUrls(){return null;};  

}
