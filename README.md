# Crawler
  using apache httpclient and jsoup to parse webcontent.
  
# Env
export CLASSPATH=".:/home/ubuntu/workspace/lib/httpclient-4.5.3.jar:/home/ubuntu/workspace/lib/commons-logging-1.2.jar:/home/ubuntu/workspace/lib/jsoup-1.10.2.jar"

#compile
cd /home/ubuntu/workspace/src

javac -d ../dist com/jvax/test/TestCrawler.java
cd /home/ubuntu/workspace/dist
java com.jvax.test.TestCrawler
cd /home/ubuntu/workspace/src
