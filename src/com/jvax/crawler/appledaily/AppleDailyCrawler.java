package com.jvax.crawler.appledaily;
import com.jvax.crawler.Crawler;
/**
 * 網路爬蟲介面
 * 
 */
public class AppleDailyCrawler implements Crawler{
    private String TargetUrl;
    public void setTargetUrl(String TargetUrl){
        this.TargetUrl = TargetUrl;
    };
    public String getTargetUrl(){
        return this.TargetUrl;
    };
    
}
