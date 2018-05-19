package com.jvax.test;
import java.util.*;
public class TestRegExp {
    public static final String HN_PATTERN = "[H,h,N,n]{2}[8,7]{1}\\d{7}";
    public static final String ID_PATTERN = "[a-zA-Z]{1}[1,2]{1}\\d{8}";
    public static final String Y_PATTERN = "[0-9]+[Y,y]{1}[0-9]+";
    public static final String W_PATTERN = "[0-9]+[W,w]{1}[0-9]+";
    public static final String LocalPhone_PATTERN = "[\\[\\s-(]?[0-9]+[\\]\\s-)]?[0-9\\s]+";


    public static void main(String[] args) {
        System.out.println(args[0]+"\t"+HN_PATTERN+"\t"+args[0].matches(HN_PATTERN));
        System.out.println(args[0]+"\t"+ID_PATTERN+"\t"+args[0].matches(ID_PATTERN));
        System.out.println(args[0]+"\t"+Y_PATTERN+"\t"+args[0].matches(Y_PATTERN));
        System.out.println(args[0]+"\t"+W_PATTERN+"\t"+args[0].matches(W_PATTERN));
        System.out.println(args[0]+"\t"+LocalPhone_PATTERN+"\t"+args[0].matches(LocalPhone_PATTERN));

final Base64.Decoder decoder = Base64.getDecoder();
final Base64.Encoder encoder = Base64.getEncoder();
final String text = "@c:\\windows\\documents\\中文字串.xls@就是sheet#A3@HN87654321^A2@A123456789";
//編碼
try{
    byte[] textByte = text.getBytes("UTF-8");
    String encodedText = encoder.encodeToString(textByte);
    System.out.println(encodedText);
    System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
    
}catch(Exception e)
{
    
}
//解碼
        
        
    }
}