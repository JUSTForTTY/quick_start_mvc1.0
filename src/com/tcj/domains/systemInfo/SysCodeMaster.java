package com.tcj.domains.systemInfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

/***
 * 
 * @author lul
 * @version 1.0 10-31
 * @param 系统-代码表
 */
@Entity
@Table(name = "t_sys_code_master")
@DataTransferObject
public class SysCodeMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@RemoteProperty
	@Column(name = "CODE_TYPE",length=30)
	private String codeType;// CODE类型
	
	@Id
	@RemoteProperty
	@Column(name = "CODE_VALUE",length=4)
	private String codeValue;// CODE值
	
	@RemoteProperty
	@Column(name = "TYPE_DESC",length=60)
	private String codeDesc;// 类型说明
	
	@RemoteProperty
	@Column(name = "CODE_NAME",length=100)
	private String codeName;// CODE名称
	
	@RemoteProperty
	@Column(name = "CODE_NAME2",length=100)
	private String codeName2;// CODE名称2
	
	@RemoteProperty
	@Column(name = "NAME_DESC",length=120)
	private String nameDesc;// 名称说明
	
	@RemoteProperty
	@Column(name = "CAN_MODIFY",length=1)
	private String canModify;// CODE修改标识
	
	@RemoteProperty
	@Column(name = "IS_DEFAULT",length=1)
	private String isDefault;// 是否为默认
	
	@RemoteProperty
	@Column(name = "PM",length=10)
	private String pm;// 拼音助记符
	
	@RemoteProperty
	@Column(name = "DM",length=10)
	private String dm;// 自定义助记符
	
	@RemoteProperty
	@Column(name = "SORT")
	private Integer sort;// 显示顺序
	
	@RemoteProperty
	@Column(name = "IS_SYS_PROTECT",length=1)
	private String isSysProtect;// 是否为系统级别
	
	@RemoteProperty
	@Column(name = "MEMO",length=200)
	private String memo;// 备注
	
	@RemoteProperty
	@Column(name = "CREATE_TIME",nullable=false,updatable=false)
	private Date createTime;
	
	@RemoteProperty
	@Column(name = "CREATOR",length=40,nullable=false,updatable=false)
	private String creator;
	
	@RemoteProperty
	@Column(name = "CREATOR_IP",length=15,nullable=false,updatable=false)
	private String creatorIp;
	
	@RemoteProperty
	@Column(name = "MODIFY_TIME",nullable=false)
	private Date modifyTime;
	
	@RemoteProperty
	@Column(name = "MODIFIER",length=40,nullable=false)
	private String modifier;
	
	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=15,nullable=false)
	private String modifierIp;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeName2() {
		return codeName2;
	}

	public void setCodeName2(String codeName2) {
		this.codeName2 = codeName2;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}

	public String getCanModify() {
		return canModify;
	}

	public void setCanModify(String canModify) {
		this.canModify = canModify;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsSysProtect() {
		return isSysProtect;
	}

	public void setIsSysProtect(String isSysProtect) {
		this.isSysProtect = isSysProtect;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
