package com.jvax.format;
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
    };
    public String getFileName(){
        return super.getFileName();
    };

}
