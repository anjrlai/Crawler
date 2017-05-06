package com.jvax.format.XLS;
import com.jvax.format.Format;
import com.jvax.object.*;
/**
 * 網路爬蟲介面
 * 
 */
public class XLSFormat extends Format{
    
    private final String FileExt = ".xls";
    public void exportToFile(){};
    @Override
    public void setFileName(){
        super.setFileName(
            (super.getFileName()!=null)?
                super.getFileName():
                    super.getBoardName()+"@"+super.getDateTimeFileName()+FileExt
        );
    };
    public String getFileName(){
        return super.getFileName();
    };
    
}
