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
@Table(name = "t_base_org")
@DataTransferObject
public class T_Base_Org implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "ORG_ID",length=10,updatable=true)
	private Integer orgId;

	@RemoteProperty
	@Column(name = "CANCEL_DATE",length=0,nullable=true,updatable=true)
	private Date cancelDate;

	@RemoteProperty
	@Column(name = "CELL_TEL",length=255,nullable=true,updatable=true)
	private String cellTel;

	@RemoteProperty
	@Column(name = "CONTACT_PERSON",length=255,nullable=true,updatable=true)
	private String contactPerson;

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
	@Column(name = "F_ORG_ID",length=10,nullable=true,updatable=true)
	private Integer fOrgId;

	@RemoteProperty
	@Column(name = "FOUND_DATE",length=0,nullable=true,updatable=true)
	private Date foundDate;

	@RemoteProperty
	@Column(name = "GROUP_SORT",length=10,nullable=true,updatable=true)
	private Integer groupSort;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=255,nullable=true,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "MEMO",length=255,nullable=true,updatable=true)
	private String memo;

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
	@Column(name = "ORG_ADDRESS",length=255,nullable=true,updatable=true)
	private String orgAddress;

	@RemoteProperty
	@Column(name = "ORG_CODE",length=255,nullable=true,updatable=true)
	private String orgCode;

	@RemoteProperty
	@Column(name = "ORG_EMAIL",length=255,nullable=true,updatable=true)
	private String orgEmail;

	@RemoteProperty
	@Column(name = "ORG_FAX",length=255,nullable=true,updatable=true)
	private String orgFax;

	@RemoteProperty
	@Column(name = "ORG_FULL_NAME",length=255,nullable=true,updatable=true)
	private String orgFullName;

	@RemoteProperty
	@Column(name = "ORG_NAME",length=255,nullable=true,updatable=true)
	private String orgName;


	public void setOrgId(Integer orgId){
		this.orgId = orgId;
	}
	public Integer getOrgId(){
		return this.orgId;
	}
	public void setCancelDate(Date cancelDate){
		this.cancelDate = cancelDate;
	}
	public Date getCancelDate(){
		return this.cancelDate;
	}
	public void setCellTel(String cellTel){
		this.cellTel = cellTel;
	}
	public String getCellTel(){
		return this.cellTel;
	}
	public void setContactPerson(String contactPerson){
		this.contactPerson = contactPerson;
	}
	public String getContactPerson(){
		return this.contactPerson;
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
	public void setFOrgId(Integer fOrgId){
		this.fOrgId = fOrgId;
	}
	public Integer getFOrgId(){
		return this.fOrgId;
	}
	public void setFoundDate(Date foundDate){
		this.foundDate = foundDate;
	}
	public Date getFoundDate(){
		return this.foundDate;
	}
	public void setGroupSort(Integer groupSort){
		this.groupSort = groupSort;
	}
	public Integer getGroupSort(){
		return this.groupSort;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	public String getMemo(){
		return this.memo;
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
	public void setOrgAddress(String orgAddress){
		this.orgAddress = orgAddress;
	}
	public String getOrgAddress(){
		return this.orgAddress;
	}
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
	public String getOrgCode(){
		return this.orgCode;
	}
	public void setOrgEmail(String orgEmail){
		this.orgEmail = orgEmail;
	}
	public String getOrgEmail(){
		return this.orgEmail;
	}
	public void setOrgFax(String orgFax){
		this.orgFax = orgFax;
	}
	public String getOrgFax(){
		return this.orgFax;
	}
	public void setOrgFullName(String orgFullName){
		this.orgFullName = orgFullName;
	}
	public String getOrgFullName(){
		return this.orgFullName;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	public String getOrgName(){
		return this.orgName;
	}
}