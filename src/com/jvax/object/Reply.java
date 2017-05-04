package com.jvax.object;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class Reply {
	private String MD5Hash;
	private String Tag;
	private String UserId;
	private String Content;
	private String PostDate;
	private String CrawledDate;
	private String insertSQL;
	private String updateSQL;
	
	public void setMD5Hash(String MD5Hash)
	{
		this.MD5Hash = MD5Hash;
	}
	public String getMD5Hash()
	{
		return this.MD5Hash;
	}
	public void setTag(String Tag)
	{
		this.Tag = Tag;
	}
	public String getTag()
	{
		return this.Tag;
	}
	public void setUserId(String UserId)
	{
		this.UserId = UserId;
	}
	public String getUserId()
	{
		return this.UserId;
	}
	public void setContent(String Content)
	{
		this.Content = Content;
	}
	public String getContent()
	{
		return this.Content;
	}
	public void setPostDate(String PostDate)
	{
		this.PostDate = PostDate;
	}
	public String getPostDate()
	{
		return this.PostDate;
	}
	public void setCrawledDate(String CrawledDate)
	{
		this.CrawledDate = CrawledDate;
	}
	public String getCrawledDate()
	{
		return this.CrawledDate;
	}
	public String toString()
	{
		return ""+this.Tag+","+
		""+this.UserId+","+
		""+this.Content+","+
		""+this.PostDate+"";
	}
	public String getInsertSQL()
	{
//		return "INSERT INTO `PTT`.`Topics` (`MD5Hash`, `PostDate`, `HTMLContent`, `HTMLContentLength`, `UserId`, `BoardName`, `Subject`, `Content`, `Url`, `ReplyCount`, `CrawledDate`) VALUES ("+
		return "INSERT INTO `PTT`.`Replies` (`MD5Hash`, `Tag`, `UserId`,  `Content`, `PostDate`, `CrawledDate`) VALUES ("+
		"'"+this.MD5Hash+"',"+
		"'"+this.Tag+"',"+
		"'"+this.UserId+"',"+
		"'"+this.Content+"',"+
		"'"+this.PostDate+"',"+
		"'"+this.CrawledDate+"')";
	}
	public String getInsertSQLifexistAAA()
	{
		return "INSERT INTO `PTT`.`Replies` (`MD5Hash`, `Tag`, `UserId`,  `Content`, `PostDate`, `CrawledDate`) VALUES ("+
		"'"+this.MD5Hash+"',"+
		"'"+this.Tag+"',"+
		"'"+this.UserId+"',"+
		"'"+this.Content+"',"+
		"'"+this.PostDate+"',"+
		"'"+this.CrawledDate+"') WHERE NOT EXISTS ("+
		" SELECT  `Content` FROM `PTT`.`Replies` WHERE "+
		"`PTT`.`Replies`.`UserId` ='"+this.UserId+"' and "+
		"`PTT`.`Replies`.`Content` ='"+this.Content+"' and "+
		"`PTT`.`Replies`.`PostDate` ='"+this.PostDate+"');";
	}	
	public String getInsertSQLifexist()
	{
		return "INSERT INTO `PTT`.`Replies` (`MD5Hash`, `Tag`, `UserId`,  `Content`, `PostDate`, `CrawledDate`)"+
		" SELECT * FROM (SELECT "+
		"'"+this.MD5Hash+"',"+
		"'"+this.Tag+"',"+
		"'"+this.UserId+"',"+
		"'"+this.Content+"',"+
		"'"+this.PostDate+"',"+
		"'"+this.CrawledDate+"') AS tmp WHERE NOT EXISTS ("+
		" SELECT  `Content` FROM `PTT`.`Replies` WHERE "+
		"`PTT`.`Replies`.`UserId` ='"+this.UserId+"' and "+
		"`PTT`.`Replies`.`Content` ='"+this.Content+"' and "+
		"`PTT`.`Replies`.`PostDate` ='"+this.PostDate+"') LIMIT 1;";
	}	
	public String getUpdateSQL()
	{
		return "UPDATE `PTT`.`Replies` SET "+
		"`Tag` = '"+this.Tag+"', "+
		"`PostDate` = '"+this.PostDate+"', "+
		"`Content` = '"+this.Content+"', "+
		"WHERE `Replies`.`MD5Hash` ='"+this.MD5Hash+"' and `Replies`.`UserId` ='"+this.UserId+"' ";
	}

}
