package com.jvax.crawler.lineq;
import com.jvax.crawler.Crawler;
import com.jvax.command.*;
/**
 * 網路爬蟲介面
 * 
 */
public class LineQCrawler extends Crawler{
    
    public void init(){
        System.out.println("LineQCrawler.init() exec!");
    }
    public String getResponse(){
        System.out.println("LineQCrawler.getResponse() exec!");
        return null;
    }
    public void parseHTML(){
        try{
            this.parseHTML(super.getResponse());
        }catch(Exception e){
            
        }
    }
    public void parseHTML(String HTML){}
    public void execute(){};
    public void execute(String Url){};

}
