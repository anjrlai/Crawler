package com.jvax.command;
import com.jvax.object.*;
/**
 * 格式指令介面
 */
public interface FormatCommand extends Command{

    public void exportToFile();
}