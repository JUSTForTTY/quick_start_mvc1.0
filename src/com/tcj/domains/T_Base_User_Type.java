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
@Table(name = "t_base_user_type")
@DataTransferObject
public class T_Base_User_Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "USER_TYPE_ID",length=10,updatable=true)
	private Integer userTypeId;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATOR",length=40,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=15,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "MODIFIER",length=40,updatable=true)
	private String modifier;

	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=15,updatable=true)
	private String modifierIp;

	@RemoteProperty
	@Column(name = "MODIFY_TIME",length=0,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "USER_TYPE_DESC",length=200,nullable=true,updatable=true)
	private String userTypeDesc;

	@RemoteProperty
	@Column(name = "USER_TYPE_FLAG",length=1,updatable=true)
	private String userTypeFlag;

	@RemoteProperty
	@Column(name = "USER_TYPE_NAME",length=40,updatable=true)
	private String userTypeName;


	public void setUserTypeId(Integer userTypeId){
		this.userTypeId = userTypeId;
	}
	public Integer getUserTypeId(){
		return this.userTypeId;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}
	public String getCreator(){
		return this.creator;
	}
	public void setCreatorIp(String creatorIp){
		this.creatorIp = creatorIp;
	}
	public String getCreatorIp(){
		return this.creatorIp;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setModifier(String modifier){
		this.modifier = modifier;
	}
	public String getModifier(){
		return this.modifier;
	}
	public void setModifierIp(String modifierIp){
		this.modifierIp = modifierIp;
	}
	public String getModifierIp(){
		return this.modifierIp;
	}
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	public Date getModifyTime(){
		return this.modifyTime;
	}
	public void setUserTypeDesc(String userTypeDesc){
		this.userTypeDesc = userTypeDesc;
	}
	public String getUserTypeDesc(){
		return this.userTypeDesc;
	}
	public void setUserTypeFlag(String userTypeFlag){
		this.userTypeFlag = userTypeFlag;
	}
	public String getUserTypeFlag(){
		return this.userTypeFlag;
	}
	public void setUserTypeName(String userTypeName){
		this.userTypeName = userTypeName;
	}
	public String getUserTypeName(){
		return this.userTypeName;
	}
}