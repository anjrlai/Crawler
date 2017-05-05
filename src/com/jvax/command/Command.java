package com.jvax.command;
import com.jvax.object.*;
/**
 * 爬蟲指令介面
 */
public interface Command {

    public void crawlArticle();
    public void crawlArticle(String Url);
    public void crawlArticleList();
    public void crawlArticleList(String Url);
    public Topic getTopic();
}