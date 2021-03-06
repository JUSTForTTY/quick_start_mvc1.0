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
@Table(name = "t_base_area")
@DataTransferObject
public class T_Base_Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "AREA_ID",length=10,updatable=true)
	private Integer areaId;

	@RemoteProperty
	@Column(name = "AREA_ADDRESS",length=200,nullable=true,updatable=true)
	private String areaAddress;

	@RemoteProperty
	@Column(name = "AREA_CODE",length=20,nullable=true,updatable=true)
	private String areaCode;

	@RemoteProperty
	@Column(name = "AREA_EMAIL",length=30,nullable=true,updatable=true)
	private String areaEmail;

	@RemoteProperty
	@Column(name = "AREA_FAX",length=20,nullable=true,updatable=true)
	private String areaFax;

	@RemoteProperty
	@Column(name = "AREA_FULL_NAME",length=2147483647,nullable=true,updatable=true)
	private String areaFullName;

	@RemoteProperty
	@Column(name = "AREA_NAME",length=40,nullable=true,updatable=true)
	private String areaName;

	@RemoteProperty
	@Column(name = "CANCEL_DATE",length=0,nullable=true,updatable=true)
	private Date cancelDate;

	@RemoteProperty
	@Column(name = "CELL_TEL",length=20,nullable=true,updatable=true)
	private String cellTel;

	@RemoteProperty
	@Column(name = "CONTACT_PERSON",length=40,nullable=true,updatable=true)
	private String contactPerson;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATOR",length=40,nullable=true,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=15,nullable=true,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "F_AREA_ID",length=255,nullable=true,updatable=true)
	private String fAreaId;

	@RemoteProperty
	@Column(name = "FOUND_DATE",length=0,nullable=true,updatable=true)
	private Date foundDate;

	@RemoteProperty
	@Column(name = "GROUP_SORT",length=10,nullable=true,updatable=true)
	private Integer groupSort;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,nullable=true,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "MEMO",length=200,nullable=true,updatable=true)
	private String memo;

	@RemoteProperty
	@Column(name = "MODIFIER",length=40,nullable=true,updatable=true)
	private String modifier;

	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=15,nullable=true,updatable=true)
	private String modifierIp;

	@RemoteProperty
	@Column(name = "MODIFY_TIME",length=0,nullable=true,updatable=true)
	private Date modifyTime;


	public void setAreaId(Integer areaId){
		this.areaId = areaId;
	}
	public Integer getAreaId(){
		return this.areaId;
	}
	public void setAreaAddress(String areaAddress){
		this.areaAddress = areaAddress;
	}
	public String getAreaAddress(){
		return this.areaAddress;
	}
	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
	}
	public String getAreaCode(){
		return this.areaCode;
	}
	public void setAreaEmail(String areaEmail){
		this.areaEmail = areaEmail;
	}
	public String getAreaEmail(){
		return this.areaEmail;
	}
	public void setAreaFax(String areaFax){
		this.areaFax = areaFax;
	}
	public String getAreaFax(){
		return this.areaFax;
	}
	public void setAreaFullName(String areaFullName){
		this.areaFullName = areaFullName;
	}
	public String getAreaFullName(){
		return this.areaFullName;
	}
	public void setAreaName(String areaName){
		this.areaName = areaName;
	}
	public String getAreaName(){
		return this.areaName;
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
	public void setFAreaId(String fAreaId){
		this.fAreaId = fAreaId;
	}
	public String getFAreaId(){
		return this.fAreaId;
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
}