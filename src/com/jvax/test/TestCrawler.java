package com.jvax.test;
import com.jvax.crawler.*;
import com.jvax.object.*;
import com.jvax.format.*;
import com.jvax.format.XLS.*;
import com.jvax.format.PDF.*;
import java.io.*;
import java.util.*;

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

        // for(int i = 0 ; i < crawler.getTopic().getReplyCount() ; i ++)
        //     System.out.println(crawler.getTopic().getReplies().get(i));

        // /**
        //  * 產製Mobile01爬蟲
        //  */
        // crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        // crawler.init();                /* 執行初始化 */
        // crawler.crawlArticle();        /* 顯示除錯（抓取結果）*/
        // // crawler.crawlArticle(args[0]);
        // // crawler.setBucket(FetchMobile01Urls);
        // System.out.println(crawler.getTopic());
        // for(int i = 0 ; i < crawler.getTopic().getReplyCount() ; i ++)
        //     System.out.println(crawler.getTopic().getReplies().get(i));

        /**
         * 產製XLS Layout
         */
        Format format = FormatFactory.createFormat(FormatParameter.XLS);
        // String BoardName = crawler.getTopic().getBoardName();
        Vector<Topic> topics = new Vector<Topic>();
        topics.add(crawler.getTopic());
        format.setData(topics);
        format.setFileName();

        format = FormatFactory.createFormat(FormatParameter.PDF);
        format.setData(topics);
        format.setFileName();


        // System.out.println(crawler.getTopic());
        // format.setFileName(crawler.getTopic().getBoardName());
        // ((XLSFormat)format).setFileName();
    };

    /**
     * 載入預設設定檔 config.properties
     */
    private final void loadConfig() throws Exception 
    {
		this.prop = new Properties();
		try {
			prop.load(new InputStreamReader(
			    new FileInputStream("config.properties"), "UTF-8"));
			    
		} catch (IOException io) {
			io.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }
    private Properties prop;
}
