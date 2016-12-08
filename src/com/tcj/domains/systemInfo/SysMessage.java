package com.tcj.domains.systemInfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_sys_message")
@DataTransferObject
public class SysMessage implements Serializable {
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@RemoteProperty
	@Column(name = "MESSAGE_ID")
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	private Integer messageId;// 系统消息ID
	
	@RemoteProperty
	@Column(name = "SEND_USER_ID",nullable=false)
	private Integer sendUserId;// 发送人用户ID
	
	@RemoteProperty
	@Column(name = "RECEIVE_USER_ID",nullable=false)
	private Integer receiveUserId;// 接收人用户ID
	
	@RemoteProperty
	@Column(name = "MESSAGE_CONTENT",length=1000,nullable=false)
	private String messageContent;//消息内容
	
	@RemoteProperty
	@Column(name = "IS_READ",length=1,nullable=false)
	private String isRead;//是否已读
}
