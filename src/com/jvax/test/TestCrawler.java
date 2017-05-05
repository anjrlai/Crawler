package com.jvax.test;
import com.jvax.crawler.*;
import com.jvax.format.*;

/**
 * 測試網路爬蟲
 * 測試各種不同種類爬蟲爬搜功能
 */
public class TestCrawler {

    /**
     * 主程式進入點
     */
    public static void main(String[] args) throws Exception{
        /**
         * 產製PTT爬蟲
         */
        Crawler crawler = CrawlerFactory.createCrawler(CrawlerParameter.PTT);
        crawler.init();                /* 執行初始化 */
        crawler.crawlArticle();        /* 顯示除錯（抓取結果）*/
        // crawler.crawlArticle(args[0]);
        // crawler.setBucket(FetchPttUrls);
        System.out.println(crawler.getTopic());
        for(int i = 0 ; i < crawler.getTopic().getReplyCount() ; i ++)
            System.out.println(crawler.getTopic().getReplies().get(i));

        /**
         * 產製Mobile01爬蟲
         */
        crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        crawler.init();                /* 執行初始化 */
        crawler.crawlArticle();        /* 顯示除錯（抓取結果）*/
        // crawler.crawlArticle(args[0]);
        // crawler.setBucket(FetchMobile01Urls);
        System.out.println(crawler.getTopic());
        for(int i = 0 ; i < crawler.getTopic().getReplyCount() ; i ++)
            System.out.println(crawler.getTopic().getReplies().get(i));

        /**
         * 產製XLS Layout
         */
        Format format = FormatFactory.createFormat(FormatParameter.XLS);
       
    };

}
