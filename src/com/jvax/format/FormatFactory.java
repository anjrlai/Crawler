package com.jvax.format;

/**
 * 網路爬蟲工廠
 * 
 */
public class FormatFactory {

    public static Format createFormat(int FormatTypeID){
        switch(FormatTypeID)
        {
            case FormatParameter.XLS:
                return null;
            case FormatParameter.PDF:
                return null;
        }
        return null;
    }


}
