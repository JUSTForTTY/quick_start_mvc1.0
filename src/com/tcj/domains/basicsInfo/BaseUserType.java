package com.tcj.domains.basicsInfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @author lul
 * @version 1.0 10-08
 * @param 基础-用户表类别  t_base_user_type
 */
@Entity
@Table(name = "t_base_user_type")
@DataTransferObject
public class BaseUserType implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_TYPE_ID")
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	private Integer userTypeId;//用户类别ID
	
	@Column(name = "USER_TYPE_NAME",length = 40,nullable=false)
	private String userTypeName;//类别名称
	
	@Column(name = "USER_TYPE_DESC",length = 200)
	private String userTypeDesc;//类别描述
	
	@Column(name = "USER_TYPE_FLAG",length = 1,nullable=false)
	private String userTypeFlag;//类别区分
	
	@Column(name = "IS_DELETED",length = 1,nullable=false)
	private String isDeleted;//是否删除
	
	@Column(name = "CREATE_TIME",updatable = false,nullable=false)
	private Date createTime;//创建时间
	
	@Column(name = "CREATOR",length = 40,updatable = false,nullable=false)
	private String creator;//创建人
	
	@Column(name = "CREATOR_IP",length = 15,updatable = false,nullable=false)
	private String creatorIp;//创建IP
	
	@Column(name = "MODIFY_TIME",nullable=false)
	private Date modifyTime;//修改时间
	
	@Column(name = "MODIFIER",length = 40,nullable=false)
	private String modifier;//修改人
	
	@Column(name = "MODIFIER_IP",length = 15,nullable=false)
	private String modifierIp;//修改IP

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	public String getUserTypeFlag() {
		return userTypeFlag;
	}

	public void setUserTypeFlag(String userTypeFlag) {
		this.userTypeFlag = userTypeFlag;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorIp() {
		return creatorIp;
	}

	public void setCreatorIp(String creatorIp) {
		this.creatorIp = creatorIp;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifierIp() {
		return modifierIp;
	}

	public void setModifierIp(String modifierIp) {
		this.modifierIp = modifierIp;
	}
}
