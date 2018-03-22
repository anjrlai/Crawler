package com.jvax.format;

/**
 * 網路爬蟲工廠
 * 
 */
public class FormatFactory {
    private FormatFactory() {};
    public static Format createFormat(int FormatTypeID){
        switch(CrawlerTypeID)
        {
            case FormatParameter.XLS:
                return new XLSFormat();
            case FormatParameter.XLSX:
                return new XLSXFormat();
            case FormatParameter.PDF:
                return new PDFFormat();
        }
        return null;
    }


}
