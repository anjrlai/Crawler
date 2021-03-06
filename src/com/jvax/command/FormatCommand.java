package com.jvax.command;
import com.jvax.object.*;
import java.util.*;
/**
 * 格式指令介面
 */
public interface FormatCommand extends Command{

    public void exportToFile();

    public void setData(Vector<Topic> topics);

    public void setOutputColumn(Vector<String> columns);

}