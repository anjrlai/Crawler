package com.jvax.command;
import com.jvax.object.*;
public interface Command {

    public void execute();
    public void execute(String Url);
    public Topic getTopic();
}