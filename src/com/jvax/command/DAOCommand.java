package com.jvax.command;
import com.jvax.object.*;
import java.util.*;
/**
 * 爬蟲指令介面
 */
public interface DAOCommand extends Command{

    public void setBoardName(String BoardName);
    public String getBoardName();

    public void setTopicUrl(String Url);
    public String getTopicUrl();

    public void setUserId(String UserId);
    public String getUserId();

    public void setSubject(String Subject);
    public String getSubject();

    public void setPostDate(Date PostDate);
    public Date getPostDate();

    public void setContent(String Content);
    public String getContent();

    public void setReplyCount(int ReplyCount);
    public int getReplyCount();

    public void setCrawledDate(Date CrawledDate);
    public Date getCrawledDate();

    public void setReplyTag(String Tag);
    public String getReplyTag();

    public void setReplyUserId(String UserId);
    public String getReplyUserId();

    public void setReplyContent(String Content);
    public String getReplyContent();

    public void setReplyPostDate(Date PostDate);
    public Date getReplyPostDate();

    public void setReplyCrawledDate(Date CrawledDate);
    public Date getReplyCrawledDate();

    public void addReply(Reply reply);
    public void removeReply(Reply reply);

    public void addTopic(Topic topic);
    public void removeTopic(Topic topic);

    
}