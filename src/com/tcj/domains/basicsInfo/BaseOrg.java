package com.tcj.domains.basicsInfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @author jiayy
 * @version 1.0 10-09
 * @param 基础-组织机构表
 */
@Entity
@Table(name = "t_base_org")
@DataTransferObject
public class BaseOrg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@RemoteProperty
	@Column(name = "ORG_ID")
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	private Integer orgId;// 组织机构ID

	@RemoteProperty
	@Column(name = "ORG_CODE")
	private String orgCode;// varchar(10) 10
	
	@RemoteProperty
	@Column(name = "F_ORG_ID")
	private Integer fOrgId;// 父组织机构ID int

	@RemoteProperty
	@Column(name = "ORG_NAME")
	private String orgName;// 组织机构名称 varchar(40) 40

	@RemoteProperty
	@Column(name = "ORG_FULL_NAME")
	private String orgFullName;// 组织机构全名 varchar(400) 400

	@RemoteProperty
	@Column(name = "GROUP_SORT")
	private Integer groupSort;// 组内顺序 int

	@RemoteProperty
	@Column(name = "CONTACT_PERSON")
	private String contactPerson; // 组织机构联系人 varchar(40) 40

	
	@RemoteProperty
	@Column(name = "CELL_TEL")
	private String cellTel;// 手机/电话 varchar(20) 20
	
	@RemoteProperty
	@Column(name = "ORG_ADDRESS")
	private String orgAddress; // 地址 varchar(200) 200
	
	@RemoteProperty
	@Column(name = "FOUND_DATE")
	private Date foundDate;// 建立日期 date
	
	@RemoteProperty
	@Column(name = "CANCEL_DATE")
	private Date cancelDate; // 取消日期 date
	
	@RemoteProperty
	@Column(name = "ORG_FAX")
	private String orgFax;// 传真 varchar(20) 20
	
	@RemoteProperty
	@Column(name = "ORG_EMAIL")
	private String orgEmail;// 电子邮箱 varchar(30) 30
	
	@RemoteProperty
	@Column(name = "MEMO")
	private String memo;// 备注 varchar(200) 200
	
	@RemoteProperty
	@Column(name = "IS_DELETED")
	private String isDeleted;// 是否删除 0：否；1：是 char(1) 1 TRUE
	
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getfOrgId() {
		return fOrgId;
	}

	public void setfOrgId(Integer fOrgId) {
		this.fOrgId = fOrgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgFullName() {
		return orgFullName;
	}

	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	public Integer getGroupSort() {
		return groupSort;
	}

	public void setGroupSort(Integer groupSort) {
		this.groupSort = groupSort;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCellTel() {
		return cellTel;
	}

	public void setCellTel(String cellTel) {
		this.cellTel = cellTel;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getOrgFax() {
		return orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
