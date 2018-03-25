# Crawler
  [prototype]  
    Using apache httpclient and jsoup to crawl internet. such as ptt.cc & mobile01.com
    add Format Factory Pattern

#Libraries

  - Apache HttpClient[https://hc.apache.org/]
  - Apache Commons-Logging[https://commons.apache.org/proper/commons-logging/]
  - Apache PDFBox[https://pdfbox.apache.org/]
  - Jsoup[https://jsoup.org/]
  - Jxl[http://jexcelapi.sourceforge.net/]
  - Junit4[http://junit.org/junit4/]

***
# Create workspace @ c9.io and install openjdk-7-jdk  
  commands:  
    sudo apt-get update  
    sudo apt-get install openjdk-7-jdk  
***
# PTT using java version 8 modify apt-get sourcelist first then exec apt-get update
  commands:
  
    sudo add-apt-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get install oracle-java8-installer
  

# Using Apache Ant

  compile commands:  
  
    ant clean  
    ant  

  test commands:  
    
    ant test  

  clean-up commands:  
    
    ant clean
    
  clean xls commands:  
    
    ant cleanxls
    

# Todos  
  - Zero MQ function
  - Key Value Database
  - reverse index
  - elasticsearch Database

