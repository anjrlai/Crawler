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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * 測試網路爬蟲
 * 測試各種不同種類爬蟲爬搜功能
 */
public class TestCrawlerWithUrl {
    private static final Log LOGGER = LogFactory.getLog(TestCrawlerWithUrl.class);
    static String FILE_NAME;
    static InputStream ExcelFileToRead;
    static String targetUrl;
    static String LastReplyPostedDate;
    static Crawler crawler;

    /**
     * 主程式進入點
     */
    public static void main(String[] args) throws Exception{
        FILE_NAME = args[0];
        ExcelFileToRead = new FileInputStream(args[0]);
        LOGGER.info("Tru to Read Xls("+args[0]+")");
        readNwriteXls(ExcelFileToRead);
        
        
    }
    public static Crawler createCrawler(String Url) throws Exception {
        // if(Url.indexOf(CrawlerParameter.Url_PTT)>0)
        // {
        //     return CrawlerFactory.createCrawler(CrawlerParameter.PTT);
        // }
        // if(Url.indexOf(CrawlerParameter.Url_Mobile01)>0)
        // {
        //     return CrawlerFactory.createCrawler(CrawlerParameter.Mobile01);
        // }
        if(Url.indexOf(CrawlerParameter.Url_Gamer)>0)
        {
            return CrawlerFactory.createCrawler(CrawlerParameter.Gamer);
        }
        return null;

    }
    public static void executeCrawl() throws Exception {
            // System.out.println("--"+targetUrl+"--");
        if(crawler!=null)
        {
            
            crawler.init();
            crawler.crawlArticle(targetUrl);
            Vector<Topic> topics = crawler.getTopics();
            Topic lasttopic = topics.get(topics.size()-1);
            LastReplyPostedDate = lasttopic.getLastReplyPostedDate();
            System.out.println("targetUrl:: "+targetUrl+", LastReplyPostedDate:: "+LastReplyPostedDate);
        }
            // Reply reply = temp.getLastReply();
            // System.out.println(reply.getPostDate());
    }

    public static void readNwriteXls(InputStream ExcelFileToRead) throws Exception {
        Workbook wb  = WorkbookFactory.create(ExcelFileToRead); 
    	Sheet sheet = wb.getSheetAt(0);
		
		Row row; 
		Cell cell;
		Cell Newcell;

		Iterator rows = sheet.rowIterator();
		rows.next();
		while (rows.hasNext())
		{
		    row=(Row) rows.next();
			Iterator cells = row.cellIterator();
            cell = row.getCell(1);
            if (cell == null) { }
            else
            {
				if (cell.getCellTypeEnum() == CellType.STRING)
				{
				    targetUrl = (cell.getStringCellValue()).trim();
					crawler = createCrawler(targetUrl);
					executeCrawl();
					if(LastReplyPostedDate!=null)
					{
					    Newcell = row.getCell(2);
					    Newcell.setCellValue(LastReplyPostedDate);
					}
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
		}

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            wb.write(outputStream);
            wb.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }		
		
		
    }

/*
        for(String Url : Urls)
            crawler.crawlArticle(Url);                               // 單一文章爬蒐（顯示抓取內容）
*/


}
