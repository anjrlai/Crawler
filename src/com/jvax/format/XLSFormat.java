package com.jvax.format;
import com.jvax.object.*;
import jxl.*;
import jxl.write.*;
import jxl.format.*;
import java.io.*;
import java.util.*;

/**
 * 網路爬蟲介面
 * 
 */
public class XLSFormat extends Format{

    public XLSFormat(){
        super();
        SheetInfo = new Hashtable<String, Integer>();
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
    private void writeData(){
        int id = 1;
        this.createSheet("總表", 0);
        if(this.topics!=null)
        {
            try {
                    for (Topic topic : topics){
                    // System.out.println((String)topic.getSubject());
                    label = new Label(0, id, (String)topic.getUserId());
                    this.sheet.addCell(label);
                    label = new Label(1, id, (String)topic.getSubject());
                    this.sheet.addCell(label);
                    label = new Label(2, id, (String)topic.getUrl());
                    this.sheet.addCell(label);
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
        if(!this.SheetInfo.containsKey(SheetName)){
            this.SheetInfo.put(SheetName, new Integer(SheetID));
        }
        this.sheet = workbook.createSheet(SheetName, SheetID);
    };
    public String getFileName(){
        return super.getFileName();
    };

    private Hashtable<String, Integer> SheetInfo;
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

}
