# Crawler
  [prototype] using apache httpclient and jsoup to parse webcontent.

#Libraries

  - Apache HttpClient[https://hc.apache.org/]
  - Apache Commons-Logging[https://commons.apache.org/proper/commons-logging/]
  - Apache PDFBox[https://pdfbox.apache.org/]
  - Jsoup[https://jsoup.org/]
  - Jxl[http://jexcelapi.sourceforge.net/]

***

# Env
export CLASSPATH=".:/home/ubuntu/workspace/lib/httpclient-4.5.3.jar:/home/ubuntu/workspace/lib/commons-logging-1.2.jar:/home/ubuntu/workspace/lib/jsoup-1.10.2.jar:/home/ubuntu/workspace/lib/pdfbox-2.0.5.jar:/home/ubuntu/workspace/dist"  

#compile
  commands:  
    
    cd /home/ubuntu/workspace/src  
    javac -d ../dist com/jvax/test/TestCrawler.java  
    javac -d ../dist com/jvax/crawler/Crawler.java  

    cd /home/ubuntu/workspace/dist  
    java com.jvax.test.TestCrawler  
    cd /home/ubuntu/workspace/src  

