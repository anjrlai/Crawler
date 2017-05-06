package com.jvax.command;
import com.jvax.object.*;
import java.util.*;
/**
 * 爬蟲指令介面
 */
public interface CrawlerCommand extends Command{

    public void crawlArticle();
    public void crawlArticle(String Url);
    // public void crawlArticleList();
    // public void crawlArticleList(String Url);
    public Hashtable<String, String> crawlArticleList();
    public Hashtable<String, String> crawlArticleList(String Url);
    public Topic getTopic();
    public void exportToFile();
}