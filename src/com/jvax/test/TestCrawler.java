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
        int ArticleCount = 10;
        String BoardUrl = "https://www.ptt.cc/bbs/Broad_Band/index.html";
        // String BoardUrl = "https://www.ptt.cc/bbs/MobileComm/index.html";
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

        crawler.setFormat(new XLSXFormat());                        /* 指定輸出格式為XLSX */
        // To-Do 思考要不要將Format改成工廠模式...
        
        /**
         * 指定輸出欄位
         */
        Vector<String> outCols = new Vector<String>();
        outCols.addElement("UserId");                               /* 文章作者 */
        outCols.addElement("BoardName");                            /* 文章板名 */
        outCols.addElement("Subject");                              /* 文章主旨 */
        outCols.addElement("Url");                                  /* 文章網址 */
        outCols.addElement("Content");                              /* 文章內文 */
        outCols.addElement("ReplyCount");                           /* 文章回應數 */
        outCols.addElement("CrawledDate");                          /* 文章抓取日期 */

        crawler.setOutputColumn(outCols);
        // To-Do 是否將爬蟲的功能簡化，輸出欄位的問題應該是Format的工作...
        
        crawler.exportToFile();                                     /* 匯出檔案 */



        /**
         * 產製Mobile01爬蟲
         */
        // crawler = CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        // crawler.init();                                              /* 執行初始化 */
        // crawler.crawlArticleList("https://www.mobile01.com/topiclist.php?f=507", 10);
        // Urls=crawler.getUrls();
        // for(String Url : Urls)
        //     crawler.crawlArticle(Url);                               /* 單一文章爬蒐（顯示抓取內容）*/
        // /**
        //  * 指定輸出格式為XLS，並匯出檔案
        //  */
        // crawler.setFormat(new XLSXFormat());
        // crawler.exportToFile();

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
