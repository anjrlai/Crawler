# Crawler
  [prototype] using apache httpclient and jsoup to parse webcontent.

#Libraries
  Apache HttpClient
  Apache Commons-Logging
  Jsoup
  Jxl
  Apache PDFBox

# Env
export CLASSPATH=".:/home/ubuntu/workspace/lib/httpclient-4.5.3.jar:/home/ubuntu/workspace/lib/commons-logging-1.2.jar:/home/ubuntu/workspace/lib/jsoup-1.10.2.jar:/home/ubuntu/workspace/lib/pdfbox-2.0.5.jar:/home/ubuntu/workspace/dist"

#compile
cd /home/ubuntu/workspace/src
javac -d ../dist com/jvax/test/TestCrawler.java
javac -d ../dist com/jvax/crawler/Crawler.java

cd /home/ubuntu/workspace/dist

java com.jvax.test.TestCrawler "https://www.ptt.cc/bbs/Lifeismoney/M.1493880038.A.831.html"

cd /home/ubuntu/workspace/src
