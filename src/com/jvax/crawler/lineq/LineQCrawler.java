package com.jvax.crawler.lineq;
import com.jvax.crawler.Crawler;
import com.jvax.command.*;
/**
 * 網路爬蟲介面
 * 
 */
public class LineQCrawler extends Crawler{
    
    public void init(){
    }
    public String getResponse(){
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
