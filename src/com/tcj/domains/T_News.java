package com.tcj.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_news")
@DataTransferObject
public class T_News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "NID",length=10,updatable=true)
	private Integer nid;

	@RemoteProperty
	@Column(name = "CONTENT",length=255,updatable=true)
	private String content;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_USER",length=10,updatable=true)
	private Integer createUser;

	@RemoteProperty
	@Column(name = "IS_RECOMMENDED",length=1,updatable=true)
	private String isRecommended;

	@RemoteProperty
	@Column(name = "KEYWORDS",length=255,updatable=true)
	private String keywords;

	@RemoteProperty
	@Column(name = "PIC",length=255,updatable=true)
	private String pic;

	@RemoteProperty
	@Column(name = "TITLE",length=255,updatable=true)
	private String title;

	@RemoteProperty
	@Column(name = "TYPE",length=255,updatable=true)
	private String type;

	@RemoteProperty
	@Column(name = "UPDATE_TIME",length=0,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "UPDATE_USER",length=10,updatable=true)
	private Integer updateUser;


	public void setNid(Integer nid){
		this.nid = nid;
	}
	public Integer getNid(){
		return this.nid;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateUser(Integer createUser){
		this.createUser = createUser;
	}
	public Integer getCreateUser(){
		return this.createUser;
	}
	public void setIsRecommended(String isRecommended){
		this.isRecommended = isRecommended;
	}
	public String getIsRecommended(){
		return this.isRecommended;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getKeywords(){
		return this.keywords;
	}
	public void setPic(String pic){
		this.pic = pic;
	}
	public String getPic(){
		return this.pic;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateUser(Integer updateUser){
		this.updateUser = updateUser;
	}
	public Integer getUpdateUser(){
		return this.updateUser;
	}
}