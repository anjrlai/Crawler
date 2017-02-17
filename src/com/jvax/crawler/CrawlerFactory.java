package com.jvax.crawler;
/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public abstract class CrawlerFactory {

    public abstract void createCrawler(String CrawlerTypeName);
    public abstract void createCrawler(int CrawlerTypeID);
    

}
