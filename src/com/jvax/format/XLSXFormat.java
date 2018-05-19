package com.jvax.format;
import java.lang.reflect.Method;
import org.apache.commons.logging.*;
import com.jvax.object.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
/**
 * 網路爬蟲介面
 * 
 */
public class XLSXFormat extends Format{
    private static final Log LOGGER = LogFactory.getLog(XLSXFormat.class);
    XSSFWorkbook wb;
    Sheet sheet;

    public XLSXFormat(){
        super();
        // sheetInfo = new Hashtable<String, Integer>();
        sheetInfo = new Vector<String>();
        headers   = new Hashtable<String, String>();
        try {
            // wb = new XSSFWorkbook(new File(getFileName()));
            wb = new XSSFWorkbook();

        } catch(Exception e) {
			e.printStackTrace();
        }
    }
    private final String FileExt = ".xlsx";

    public void exportToFile(){};

    @Override
    protected void setFileName(){
        super.setFileName(
            (super.getFileName()!=null)?
                super.getFileName():
                    // super.getBoardName()+"@"+super.getDateTimeFileName()+FileExt
                    "test.xlsx"
        );
    };

    private void setHeaders(Hashtable<String, String> headers){
        this.headers = headers;
    }
    private void writeHeaders(){
    try {
        // this.label = new Label(0, 0, "User");
        // this.sheet.addCell(label);
        // this.label = new Label(1, 0, "Subject");
        // this.sheet.addCell(label);
        // this.label = new Label(2, 0, "URL");
        // this.sheet.addCell(label);
        } catch(Exception e) {
	        e.printStackTrace();
        }
    }   
    private void saveFile(){
        try (FileOutputStream out = new FileOutputStream(super.getFileName())) {
                wb.write(out);
        } catch(Exception e) {
	        e.printStackTrace();
        }
    }    
private void writeData(){
        int id = 0;
        Class c = null;
        this.createSheet("總表");
        if(this.topics!=null)
        {
            try {
                    for (Topic topic : topics){
                    Row row = this.sheet.createRow((short) 0+id);
                    Cell cell = row.createCell((short) 0);
                    c = Class.forName("com.jvax.object.Topic");
                    Vector<String> columns = super.getOutputColumn();
                    for (int j = 0 ; j < columns.size() ; j ++)
                    {
                      @SuppressWarnings("unchecked")
                      Method m = c.getMethod("get"+columns.get(j), new Class[]{});
                      cell = row.createCell((short) j);

                      if(m.invoke(topic, new Object[]{}) instanceof String)
                      cell.setCellValue((String)m.invoke(topic, new Object[]{}));
                      else
                      cell.setCellValue((Integer)m.invoke(topic, new Object[]{}));
                    }
                    id++;
                }
                this.sheet.autoSizeColumn(0);
            } catch(Exception e) {
			    e.printStackTrace();
            }
        }
        else{
            LOGGER.error("topics is null!!!");
        }
    };
    public String getFileName(){
        return super.getFileName();
    };
    private void createSheet(String SheetName){
        if(!this.sheetInfo.contains(SheetName)){
            this.sheetInfo.addElement(SheetName);
        }
        this.sheet = this.wb.createSheet(SheetName);
    };

    // private Hashtable<String, Integer> sheetInfo;
    private Vector<String> sheetInfo;
    private Hashtable<String, String> headers;
    private Vector<Topic> topics;

    public void exportToFile(String BoardName){
        super.setBoardName(
            (super.getBoardName()==null)?BoardName:super.getBoardName()
        );
        this.setFileName();
        this.writeData();        
        //[TO DOs].....
            // Save
        try (FileOutputStream fileOut = new FileOutputStream(super.getBoardName()+"@"+super.getDateTimeFileName()+FileExt)) {
                wb.write(fileOut);
            } catch(Exception e) {
			    e.printStackTrace();
            }
            
    };
    public void setData(Vector<Topic> topics){
        this.topics =topics;
    };

    @Override
    public void setOutputColumn(Vector<String> columns)
    {};
}
