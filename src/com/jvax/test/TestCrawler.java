package com.jvax.test;
import com.jvax.crawler.*;
import com.jvax.format.*;
import com.jvax.object.*;
import java.io.*;
import java.util.*;
import org.junit.*;

/**
 * 測試網路爬蟲
 * 測試各種不同種類爬蟲爬搜功能
 */
public class TestCrawler {

    /**
     * 主程式進入點
     */
    public static void main(String[] args) throws Exception{
        int ArticleCount = 10;
        // String BoardUrl = "https://www.ptt.cc/bbs/Broad_Band/index.html";
        String BoardUrl = "https://www.ptt.cc/bbs/Espannol/index.html";
        execute(BoardUrl, ArticleCount);
    }
    
    /**
     * 執行爬蟲指定版面網址BoardUrl, 文章數量ArticleCount
     */
    @Test
    public static void execute(String BoardUrl, int ArticleCount) throws Exception{
        
        Vector<String> Urls = new Vector<String>();

        /**
         * 產製PTT爬蟲
         */
        Crawler crawler = CrawlerFactory.createCrawler(CrawlerParameter.PTT);
        crawler.init();                     /* 執行初始化 */
        crawler.crawlArticleList(BoardUrl, ArticleCount);         /* 文章列表爬蒐（抓取網址）*/
        Urls=crawler.getUrls();
        for(String Url : Urls)
        {
            crawler.crawlArticle(Url);      /* 單一文章爬蒐（顯示抓取內容）*/
            crawler.showTopicReplies();
        }

        /**
         * 指定輸出格式為XLS，並匯出檔案
         */
        crawler.setFormat(new XLSFormat());
        crawler.exportToFile();

/***********************************************************************/

        // /**
        //  * 產製Mobile01爬蟲
        //  */
        // crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        // crawler.init();                     /* 執行初始化 */
        // crawler.crawlArticle();             /* 文章爬蒐（抓取結果）*/
        // // crawler.crawlArticleList();      /* 文章列表爬蒐（抓取網址）*/
        // crawler.showTopicReplies();

        // /**
        //  * 指定輸出格式為PDF，並匯出檔案
        //  */
        // crawler.setFormat(new PDFFormat());
        // crawler.exportToFile();
    };
}
