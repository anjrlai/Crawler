package com.jvax.object;
import com.jvax.command.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.reflect.*;
/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class DAO implements DAOCommand{
	
	private static DAO instance = null;
	private static Topic current_topic = null;
	private static Vector<Topic> topics = null;
	
	protected DAO() {
    	// Exists only to defeat instantiation.
    }
    public static DAO getInstance() {
    	if(instance == null) {
    		synchronized(DAO.class) {
    			instance = new DAO();
    		}
    		current_topic = new Topic();
    		topics = new Vector<Topic>();
    	}
    	return instance;
    }
	public Topic getTopicByValue(String Attribute, String Value) {
	    try {
            Class c = Class.forName("com.jvax.object.Topic");
            for (Topic topic : topics) {
                @SuppressWarnings("unchecked")
                Method m = c.getMethod("get"+Attribute, new Class[]{});
                if(((String)m.invoke(topic, new Object[]{})).equals(Value)) {
                    return topic;
                }
            }
	    }
	    catch(ClassNotFoundException cnfe) {}
	    catch(NoSuchMethodException nsme) {}
	    catch(IllegalAccessException iae) {}
	    catch(InvocationTargetException ite) {}

        return null;
	};


	public void setBoardName(String BoardName){};
    public String getBoardName() {
    	return null;
    };

    public void setTopicUrl(String Url){};
    public String getTopicUrl() {
    	return null;
    };

    public void setUserId(String UserId){};
    public String getUserId() {
    	return null;
    };

    public void setSubject(String Subject){};
    public String getSubject() {
    	return null;
    };

    public void setPostDate(Date PostDate){};
    public Date getPostDate() {
    	return null;
    };

    public void setContent(String Content){};
    public String getContent() {
    	return null;
    };

    public void setReplyCount(int ReplyCount){};
    public int getReplyCount() {
    	return -1;
    };

    public void setCrawledDate(Date CrawledDate){};
    public Date getCrawledDate() {
    	return null;
    };

    public void setReplyTag(String Tag){};
    public String getReplyTag() {
    	return null;
    };

    public void setReplyUserId(String UserId){};
    public String getReplyUserId() {
    	return null;
    };

    public void setReplyContent(String Content){};
    public String getReplyContent() {
    	return null;
    };

    public void setReplyPostDate(Date PostDate){};
    public Date getReplyPostDate(){
    	return null;
    };

    public void setReplyCrawledDate(Date CrawledDate){};
    public Date getReplyCrawledDate(){
    	return null;
    };

    public void addReply(Reply reply){};
    public void removeReply(Reply reply){};

    public void addTopic(Topic topic){};
    public void removeTopic(Topic topic){};

}
