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
@Table(name = "t_sys_code_master")
@DataTransferObject
public class T_Sys_Code_Master implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "CODE_VALUE",length=4,updatable=true)
	private String codeValue;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "CODE_TYPE",length=30,updatable=true)
	private String codeType;

	@RemoteProperty
	@Column(name = "CAN_MODIFY",length=1,nullable=true,updatable=true)
	private String canModify;

	@RemoteProperty
	@Column(name = "TYPE_DESC",length=60,nullable=true,updatable=true)
	private String typeDesc;

	@RemoteProperty
	@Column(name = "CODE_NAME",length=100,nullable=true,updatable=true)
	private String codeName;

	@RemoteProperty
	@Column(name = "CODE_NAME2",length=100,nullable=true,updatable=true)
	private String codeName2;

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
	@Column(name = "DM",length=10,nullable=true,updatable=true)
	private String dm;

	@RemoteProperty
	@Column(name = "IS_DEFAULT",length=1,nullable=true,updatable=true)
	private String isDefault;

	@RemoteProperty
	@Column(name = "IS_SYS_PROTECT",length=1,nullable=true,updatable=true)
	private String isSysProtect;

	@RemoteProperty
	@Column(name = "MEMO",length=200,nullable=true,updatable=true)
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
	@Column(name = "NAME_DESC",length=120,nullable=true,updatable=true)
	private String nameDesc;

	@RemoteProperty
	@Column(name = "PM",length=10,nullable=true,updatable=true)
	private String pm;

	@RemoteProperty
	@Column(name = "SORT",length=10,nullable=true,updatable=true)
	private Integer sort;


	public void setCodeValue(String codeValue){
		this.codeValue = codeValue;
	}
	public String getCodeValue(){
		return this.codeValue;
	}
	public void setCodeType(String codeType){
		this.codeType = codeType;
	}
	public String getCodeType(){
		return this.codeType;
	}
	public void setCanModify(String canModify){
		this.canModify = canModify;
	}
	public String getCanModify(){
		return this.canModify;
	}
	public void setTypeDesc(String typeDesc){
		this.typeDesc = typeDesc;
	}
	public String getTypeDesc(){
		return this.typeDesc;
	}
	public void setCodeName(String codeName){
		this.codeName = codeName;
	}
	public String getCodeName(){
		return this.codeName;
	}
	public void setCodeName2(String codeName2){
		this.codeName2 = codeName2;
	}
	public String getCodeName2(){
		return this.codeName2;
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
	public void setDm(String dm){
		this.dm = dm;
	}
	public String getDm(){
		return this.dm;
	}
	public void setIsDefault(String isDefault){
		this.isDefault = isDefault;
	}
	public String getIsDefault(){
		return this.isDefault;
	}
	public void setIsSysProtect(String isSysProtect){
		this.isSysProtect = isSysProtect;
	}
	public String getIsSysProtect(){
		return this.isSysProtect;
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
	public void setNameDesc(String nameDesc){
		this.nameDesc = nameDesc;
	}
	public String getNameDesc(){
		return this.nameDesc;
	}
	public void setPm(String pm){
		this.pm = pm;
	}
	public String getPm(){
		return this.pm;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return this.sort;
	}
}