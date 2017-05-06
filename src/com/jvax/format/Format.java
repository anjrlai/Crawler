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
    private String BoardName;
    protected Vector<Topic> topics;

    public Format(){
        topics = new Vector<Topic>();
    }
    public void setFileName(String FileName){
        this.FileName = FileName;
        System.out.println(this.FileName);
    };
    
    protected String getFileName(){
        return this.FileName;
    };
    
    protected void setBoardName(String BoardName){
        this.BoardName = BoardName;
    };
    protected String getBoardName(){
        return this.BoardName;
    };

    protected String getDateTimeFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFileNamePattern);
        return sdf.format(new Date());
    };

    
    public void exportToFile(String BoardName){
        this.setBoardName(
            (this.BoardName==null)?BoardName:this.BoardName
        );
        this.setFileName();
        // this.writeData();
        //[TO DOs].....
    };
    /**
     * This Method is assumed to be override by subclass.
     * for the reason of different format/layout FileExt
     */
    protected void setFileName(){};
    public void setData(Vector<Topic> topics){
        this.topics =topics;
    };

}
