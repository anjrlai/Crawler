package com.jvax.format.PDF;
import com.jvax.format.Format;
import com.jvax.object.*;
/**
 * 網路爬蟲介面
 * 
 */
public class PDFFormat extends Format{

    private final String FileExt = ".pdf";
    public void exportToFile(){};
    //@override
    public void setFileName(){
        super.setFileName((super.getFileName()!=null)?super.getFileName()+FileExt:super.getFirstTopic().getBoardName()+"@"+super.getDateTimeFileName()+FileExt);
        // super.setFileName((super.getFileName()!=null)?super.getFileName()+FileExt:super.getFirstTopic().getBoardName()+FileExt);
    };
    public String getFileName(){
        return super.getFileName();
    };

}
