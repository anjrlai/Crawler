package com.jvax.crawler.appledaily;
import com.jvax.crawler.Crawler;
import com.jvax.command.*;
/**
 * 網路爬蟲介面
 * 
 */
public class AppleDailyCrawler extends Crawler{
    
    public void init(){
        System.out.println("AppleDailyCrawler.init() exec!");
    }
    public String getResponse(){
        System.out.println("AppleDailyCrawler.getResponse() exec!");
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
