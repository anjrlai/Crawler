package com.jvax.test;
import com.jvax.crawler.ptt.*;
import com.jvax.crawler.*;
import com.jvax.command.*;
import java.util.regex.*;

/**
 * 測試網路爬蟲
 * 測試各種不同種類爬蟲爬搜功能
 */
public class TestCrawler {

    /**
     * 主程式進入點
     */
    public static void main(String[] args) throws Exception{
        Crawler crawler = CrawlerFactory.createCrawler(CrawlerParameter.PTT);
        crawler.init();
//        crawler.setUrl("https://www.ptt.cc/bbs/Lifeismoney/M.1492398287.A.783.html");
//        crawler.parseHTML();
        crawler.execute();
    };

}
