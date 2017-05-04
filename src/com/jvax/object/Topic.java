package com.jvax.object;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class Topic {
	private String MD5Hash;
	private String PostDate;
	private String HTMLContent;
	private long HTMLContentLength;
	private String UserId;
	private String BoardName;
	private String Subject;
	private String Content;
	private String Url;
	private int ReplyCount;
	private String CrawledDate;
	private Vector Replies;
	private String HitCountString;
	

	private String insertSQL;
	private String updateSQL;

	public Topic() {
		this.Replies = new Vector();
	}

	public void setReplies(Vector Replies)
	{
		this.Replies = Replies;
	}
	public void addReply(Reply reply)
	{
		this.Replies.addElement(reply);
	}
	public boolean removeReply(Reply reply)
	{
		return this.Replies.removeElement(reply);
	}
	public Vector getReplies()
	{
		return this.Replies;
	}
	public void setMD5Hash(String MD5Hash)
	{
		this.MD5Hash = MD5Hash;
	}
	public String getMD5Hash()
	{
		return this.MD5Hash;
	}
	public void setPostDate(String PostDate)
	{
		this.PostDate = PostDate;
	}
	public String getPostDate()
	{
		return this.PostDate;
	}
	public void setHTMLContent(String HTMLContent)
	{
		this.HTMLContent = HTMLContent;
	}
	public String getHTMLContent()
	{
		return this.HTMLContent;
	}
	public void setHTMLContentLength(long HTMLContentLength)
	{
		this.HTMLContentLength = HTMLContentLength;
	}
	public long getHTMLContentLength()
	{
		return this.HTMLContentLength;
	}
	public void setUserId(String UserId)
	{
		this.UserId = UserId;
	}
	public String getUserId()
	{
		return this.UserId;
	}
	public void setBoardName(String BoardName)
	{
		this.BoardName = BoardName;
	}
	public String getBoardName()
	{
		return this.BoardName;
	}
	public void setSubject(String Subject)
	{
		this.Subject = Subject;
	}
	public String getSubject()
	{
		return this.Subject;
	}
	public void setContent(String Content)
	{
		this.Content = Content;
	}
	public String getContent()
	{
		return this.Content;
	}
	public void setUrl(String Url)
	{
		this.Url = Url;
	}
	public String getUrl()
	{
		return this.Url;
	}
	public void setReplyCount(int ReplyCount)
	{
		this.ReplyCount = ReplyCount;
	}
	public int getReplyCount()
	{
		return this.ReplyCount;
	}
	public void setCrawledDate(String CrawledDate)
	{
		this.CrawledDate = CrawledDate;
	}
	public String getCrawledDate()
	{
		return this.CrawledDate;
	}
	public void setHitCountString(String HitCountString)
	{
		this.HitCountString = HitCountString;
	}
	public String getHitCountString()
	{
		return this.HitCountString;
	}
	
	
	public String toString()
	{
		return ""+this.PostDate+","+
		""+this.UserId+","+
		""+this.BoardName+","+
		""+this.Subject+","+
		""+this.Url+","+
		""+this.ReplyCount+","+
		""+this.Content+"";
	}	
	public String getInsertSQL()
	{
//		return "INSERT INTO `PTT`.`Topics` (`MD5Hash`, `PostDate`, `HTMLContent`, `HTMLContentLength`, `UserId`, `BoardName`, `Subject`, `Content`, `Url`, `ReplyCount`, `CrawledDate`) VALUES ("+
		return "INSERT INTO `PTT`.`Topics` (`MD5Hash`, `PostDate`, `HTMLContentLength`, `UserId`, `BoardName`, `Subject`, `Content`, `Url`, `ReplyCount`, `CrawledDate`) VALUES ("+
		"'"+this.MD5Hash+"',"+
		"'"+this.PostDate+"',"+
//		"'"+this.HTMLContent+"',"+
		""+this.HTMLContentLength+","+
		"'"+this.UserId+"',"+
		"'"+this.BoardName+"',"+
		"'"+this.Subject+"',"+
		"'"+this.Content+"',"+
		"'"+this.Url+"',"+
		""+this.ReplyCount+","+
		"'"+this.CrawledDate+"')";
	}
	public String getInsertSQLifexist()
	{
		return "INSERT INTO `PTT`.`Topics` (`MD5Hash`, `PostDate`, `HTMLContentLength`, `UserId`, `BoardName`, `Subject`, `Content`, `Url`, `ReplyCount`, `CrawledDate`) VALUES ("+
		"'"+this.MD5Hash+"',"+
		"'"+this.PostDate+"',"+
//		"'"+this.HTMLContent+"',"+
		""+this.HTMLContentLength+","+
		"'"+this.UserId+"',"+
		"'"+this.BoardName+"',"+
		"'"+this.Subject+"',"+
		"'"+this.Content+"',"+
		"'"+this.Url+"',"+
		""+this.ReplyCount+","+
		"'"+this.CrawledDate+"') WHERE NOT EXISTS ("+
		"SELECT  Content FROM `PTT`.`Topics` WHERE "+
		"`PTT`.`Topics`.`UserId` ='"+this.UserId+"' and "+
		"`PTT`.`Topics`.`HTMLContentLength` ='"+this.HTMLContentLength+"' and "+
		"`PTT`.`Topics`.`Content` ='"+this.Content+"' and "+
		"`PTT`.`Topics`.`MD5Hash` ='"+this.MD5Hash+"' and "+
		"`PTT`.`Topics`.`PostDate` ='"+this.PostDate+"');";
	}	
	
	public String getUpdateSQL()
	{
		return "UPDATE `PTT`.`Topics` SET "+
		"`PostDate` = '"+this.PostDate+"', "+
//		"`HTMLContent`= '"+this.HTMLContent+"',"+
		"`HTMLContentLength` = "+this.HTMLContentLength+","+
		"`Subject` = '"+this.Subject+"', "+
		"`Content` = '"+this.Content+"', "+
		"`Url` = '"+this.Url+"', "+
		"`ReplyCount` = "+this.ReplyCount+", "+
		"WHERE `Topics`.`MD5Hash` ='"+this.MD5Hash+"' and `Topics`.`UserId` ='"+this.UserId+"' ";
	}

}
