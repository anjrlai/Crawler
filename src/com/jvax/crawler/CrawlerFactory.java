package com.jvax.crawler;
import com.jvax.crawler.ptt.*;
import com.jvax.crawler.mobile01.*;
import com.jvax.crawler.lineq.*;
import com.jvax.crawler.appledaily.*;

/**
 * 網路爬蟲工廠
 * 
 */
public abstract class CrawlerFactory {

    public static Crawler createCrawler(int CrawlerTypeID){
        switch(CrawlerTypeID)
        {
            case CrawlerParameter.PTT:
                return new PTTCrawler();
            case CrawlerParameter.Mobile01:
                return new Mobile01Crawler();
            case CrawlerParameter.LineQ:
                return new LineQCrawler();
            case CrawlerParameter.AppleDaily:
                return new AppleDailyCrawler();
        }
        return null;
    }
    public abstract void createCrawler(String CrawlerTypeName);
    

}
