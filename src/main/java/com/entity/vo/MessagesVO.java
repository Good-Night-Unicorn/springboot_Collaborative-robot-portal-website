package com.entity.vo;

import com.entity.MessagesEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 问题反馈
 * @author 
 * @email 
 * @date 2024-04-21 14:06:52
 */
public class MessagesVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户名
	 */
	
	private String username;
		
	/**
	 * 头像
	 */
	
	private String avatarurl;
		
	/**
	 * 留言内容
	 */
	
	private String content;
		
	/**
	 * 留言图片
	 */
	
	private String cpicture;
		
	/**
	 * 回复内容
	 */
	
	private String reply;
		
	/**
	 * 回复图片
	 */
	
	private String rpicture;
				
	
	/**
	 * 设置：用户名
	 */
	 
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
				
	
	/**
	 * 设置：头像
	 */
	 
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	
	/**
	 * 获取：头像
	 */
	public String getAvatarurl() {
		return avatarurl;
	}
				
	
	/**
	 * 设置：留言内容
	 */
	 
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取：留言内容
	 */
	public String getContent() {
		return content;
	}
				
	
	/**
	 * 设置：留言图片
	 */
	 
	public void setCpicture(String cpicture) {
		this.cpicture = cpicture;
	}
	
	/**
	 * 获取：留言图片
	 */
	public String getCpicture() {
		return cpicture;
	}
				
	
	/**
	 * 设置：回复内容
	 */
	 
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	/**
	 * 获取：回复内容
	 */
	public String getReply() {
		return reply;
	}
				
	
	/**
	 * 设置：回复图片
	 */
	 
	public void setRpicture(String rpicture) {
		this.rpicture = rpicture;
	}
	
	/**
	 * 获取：回复图片
	 */
	public String getRpicture() {
		return rpicture;
	}
			
}
