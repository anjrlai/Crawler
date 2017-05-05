package com.jvax.format;
import com.jvax.command.*;
import com.jvax.object.*;
import java.util.*;
import java.text.*;
/**
 * 格式介面
 * 
 */
public abstract class Format implements FormatCommand{
    private final String DateTimeFileNamePattern = "yyyyMMdd_HHmm";
    private String FileName;
    private Vector<Topic> topics;

    public void setFileName(String FileName){
        this.FileName = FileName;
        System.out.println(this.FileName);
    };

    public void setFileName(){
        this.FileName = FileName;
    };
    protected String getFileName(){
        return this.FileName;
    };

    public void setData(Vector<Topic> topics){
        this.topics = topics;
    };
    protected Topic getFirstTopic(){
        return topics.get(0);
    };
    protected String getDateTimeFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFileNamePattern);
        return sdf.format(new Date());
    };
    
}
