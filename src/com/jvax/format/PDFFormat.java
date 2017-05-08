package com.jvax.format;
/**
 * 網路爬蟲介面
 * 
 */
public class PDFFormat extends Format{

    public PDFFormat(){
        super();
    }
    private final String FileExt = ".pdf";
    public void exportToFile(){};
    @Override
    protected void setFileName(){
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
