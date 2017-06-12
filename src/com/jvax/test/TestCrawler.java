package com.jvax.test;
import org.apache.commons.logging.*;
import com.jvax.crawler.ptt.*;
import com.jvax.crawler.*;
import com.jvax.format.*;
import com.jvax.object.*;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 測試網路爬蟲
 * 測試各種不同種類爬蟲爬搜功能
 */
public class TestCrawler {
    private static final Log LOGGER = LogFactory.getLog(TestCrawler.class);

    /**
     * 主程式進入點
     */
    public static void main(String[] args) throws Exception{
        int ArticleCount = 25;
        // String BoardUrl = "https://www.ptt.cc/bbs/Broad_Band/index.html";
        String BoardUrl = "https://www.ptt.cc/bbs/Espannol/index.html";
        execute(BoardUrl, ArticleCount);
    }

    /**
     * 執行爬蟲指定版面網址BoardUrl, 文章數量ArticleCount
     */
    public static void execute(String BoardUrl, int ArticleCount) throws Exception{
        
        Vector<String> Urls = null;
        /**
         * 產製PTT爬蟲
         */
        Crawler crawler = CrawlerFactory.createCrawler(CrawlerParameter.PTT);
        crawler.init();                                              /* 執行初始化 */
        crawler.crawlArticleList(BoardUrl, ArticleCount);            /* 文章列表爬蒐（抓取網址）*/
        Urls=crawler.getUrls();
        for(String Url : Urls)
            crawler.crawlArticle(Url);                               /* 單一文章爬蒐（顯示抓取內容）*/
        /**
         * 指定輸出格式為XLS，並匯出檔案
         */
        crawler.setFormat(new XLSFormat());
        crawler.exportToFile();

        /**
         * 產製Mobile01爬蟲
         */
        crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        crawler.init();                                              /* 執行初始化 */
        crawler.crawlArticleList("https://www.mobile01.com/topiclist.php?f=507", 2);
        Urls=crawler.getUrls();
        for(String Url : Urls)
            crawler.crawlArticle(Url);                               /* 單一文章爬蒐（顯示抓取內容）*/
        /**
         * 指定輸出格式為XLS，並匯出檔案
         */
        crawler.setFormat(new XLSFormat());
        crawler.exportToFile();

    };

    
    
    @Test
    public void test() throws Exception{  /* 測試Mobile01單一文章 */
        Crawler crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        crawler.init();
        crawler.crawlArticleList("https://www.mobile01.com/topiclist.php?f=507", 50);
        // crawler.crawlArticle(ArticleUrl);
        // String ArticleUrl = "https://www.mobile01.com/topicdetail.php?f=507&t=4834229";
    }
    
}
