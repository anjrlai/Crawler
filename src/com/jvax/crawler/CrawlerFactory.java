package com.jvax.crawler;
import com.jvax.crawler.ptt.*;
import com.jvax.crawler.mobile01.*;
/**
 * 網路爬蟲工廠
 * 
 */
public abstract class CrawlerFactory {

    public Crawler createCrawler(int CrawlerTypeID){
        switch(CrawlerTypeID)
        {
            case CrawlerParameter.PTT:
                return new PTTCrawler();
            case CrawlerParameter.Mobile01:
                return new Mobile01Crawler();
        }
        return null;
    }
    public abstract void createCrawler(String CrawlerTypeName);
    

}
