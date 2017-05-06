package com.jvax.format;
import com.jvax.command.*;
import java.util.*;
import java.text.*;
/**
 * 格式介面
 * 
 */
public abstract class Format implements FormatCommand{
    private final String DateTimeFileNamePattern = "yyyyMMdd_HHmm";
    private String FileName;
    private String BoardName;

    public void setFileName(String FileName){
        this.FileName = FileName;
        System.out.println(this.FileName);
    };
    /**
     * This Method is assumed to be override by subclass.
     * for the reason of different format/layout FileExt
     */
    public void setFileName(){
    };
    protected String getFileName(){
        return this.FileName;
    };
    public void setBoardName(String BoardName){
        this.BoardName = BoardName;
    };
    public String getBoardName(){
        return this.BoardName;
    };

    protected String getDateTimeFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFileNamePattern);
        return sdf.format(new Date());
    };
    
}
