package com.jvax.format;
import jxl.*;
import jxl.write.*;
import jxl.format.*;
import java.io.*;

/**
 * 網路爬蟲介面
 * 
 */
public class XLSFormat extends Format{

    public XLSFormat(){
        super();
    }
    private final String FileExt = ".xls";
    public void exportToFile(){};
    @Override
    protected void setFileName(){
        super.setFileName(
            (super.getFileName()!=null)?
                super.getFileName():
                    super.getBoardName()+"@"+super.getDateTimeFileName()+FileExt
        );
        try {
            workbook = Workbook.createWorkbook(new File(getFileName()));
        } catch(IOException ioe) {
            
        }
    };
    public String getFileName(){
        return super.getFileName();
    };

	private WritableWorkbook workbook;

}
