package com.jvax.test;
import com.jvax.crawler.*;

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
         * PTT爬蟲製造
         */
        Crawler crawler = CrawlerFactory.createCrawler(CrawlerParameter.PTT);
        crawler.init();                /* 執行初始化 */
        crawler.execute();             /* 顯示除錯（抓取結果）*/
        // crawler.execute(args[0]);
        // crawler.setBucket(FetchPttUrls);
        System.out.println(crawler.getTopic());
        for(int i = 0 ; i < crawler.getTopic().getReplyCount() ; i ++)
            System.out.println(crawler.getTopic().getReplies().get(i));

        /**
         * Mobile01爬蟲製造
         */
        crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        crawler.init();                /* 執行初始化 */
        crawler.execute();             /* 顯示除錯（抓取結果）*/
        // crawler.execute(args[0]);
        // crawler.setBucket(FetchMobile01Urls);
        System.out.println(crawler.getTopic());
        for(int i = 0 ; i < crawler.getTopic().getReplyCount() ; i ++)
            System.out.println(crawler.getTopic().getReplies().get(i));
       
    };

}
