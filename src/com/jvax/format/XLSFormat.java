package com.jvax.format;
import org.apache.commons.logging.*;
import com.jvax.object.*;
import jxl.*;
import jxl.write.*;
import jxl.format.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
/**
 * 網路爬蟲介面
 * 
 */
public class XLSFormat extends Format{
    private static final Log LOGGER = LogFactory.getLog(XLSFormat.class);

    public XLSFormat(){
        super();
        sheetInfo = new Hashtable<String, Integer>();
        headers   = new Hashtable<String, String>();
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
			ioe.printStackTrace();
        }
    };
    private void setHeaders(Hashtable<String, String> headers){
        this.headers = headers;
    }
    private void writeHeaders(){
    try {
        this.label = new Label(0, 0, "User");
        this.sheet.addCell(label);
        this.label = new Label(1, 0, "Subject");
        this.sheet.addCell(label);
        this.label = new Label(2, 0, "URL");
        this.sheet.addCell(label);
        } catch(WriteException we) {
	        we.printStackTrace();
        }
    }   
    private void writeData(){
        int id = 1;
        this.createSheet("總表", 0);
        if(this.topics!=null)
        {
            this.writeHeaders();
            try {
                    for (Topic topic : topics){
                    System.out.println(topic.toString("\n"));
                    label = new Label(0, id, (String)topic.getUserId());
                    this.sheet.addCell(label);
                    label = new Label(1, id, (String)topic.getSubject());
                    this.sheet.addCell(label);
                    label = new Label(2, id, (String)topic.getUrl());
                    this.sheet.addCell(label);
                    // label = new Label(3, id, (String)topic.class.getMethod("getCrawledDate").invoke());
                    // this.sheet.addCell(label);
                    
                    
                    id++;
                }
            } catch(WriteException we) {
			    we.printStackTrace();
            }
        }
        else{
            System.out.println("topics is null!!!");
        }
    };
    private void saveFile(){
        try {
			workbook.write();
			workbook.close();
        } catch(WriteException we) {
			we.printStackTrace();
        } catch(IOException ioe) {
			ioe.printStackTrace();
        }
    }    
    private void createSheet(String SheetName, int SheetID){
        if(!this.sheetInfo.containsKey(SheetName)){
            this.sheetInfo.put(SheetName, new Integer(SheetID));
        }
        this.sheet = workbook.createSheet(SheetName, SheetID);
    };
    public String getFileName(){
        return super.getFileName();
    };
    private Hashtable<String, Integer> sheetInfo;
    private Hashtable<String, String> headers;
    private Vector<Topic> topics;
    private WritableSheet sheet;
	private WritableWorkbook workbook;
    private Label label;

    public void exportToFile(String BoardName){
        super.setBoardName(
            (super.getBoardName()==null)?BoardName:super.getBoardName()
        );
        this.setFileName();
        this.writeData();
        //[TO DOs].....


        this.saveFile();
    };
    public void setData(Vector<Topic> topics){
        this.topics =topics;
    };

    @Override
    public void setOutputColumn(Vector<String> columns)
    {};
}
