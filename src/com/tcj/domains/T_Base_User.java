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
@Table(name = "t_base_user")
@DataTransferObject
public class T_Base_User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "USER_ID",length=10,updatable=true)
	private Integer userId;

	@RemoteProperty
	@Column(name = "CELL_TEL",length=255,nullable=true,updatable=true)
	private String cellTel;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATOR",length=255,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=255,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "DOMICILE_PLACE",length=255,nullable=true,updatable=true)
	private String domicilePlace;

	@RemoteProperty
	@Column(name = "HOBBIES_ID",length=255,nullable=true,updatable=true)
	private String hobbiesId;

	@RemoteProperty
	@Column(name = "ID_CARD_NO",length=255,nullable=true,updatable=true)
	private String idCardNo;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "IS_LOCKED",length=255,nullable=true,updatable=true)
	private String isLocked;

	@RemoteProperty
	@Column(name = "LAST_LOGIN_TIME",length=0,nullable=true,updatable=true)
	private Date lastLoginTime;

	@RemoteProperty
	@Column(name = "LOGIN_ID",length=255,nullable=true,updatable=true)
	private String loginId;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=255,nullable=true,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "LOGIN_TIMES",length=10,nullable=true,updatable=true)
	private Integer loginTimes;

	@RemoteProperty
	@Column(name = "MARITAL_STATUS",length=1,nullable=true,updatable=true)
	private String maritalStatus;

	@RemoteProperty
	@Column(name = "MODIFIER",length=255,updatable=true)
	private String modifier;

	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=255,updatable=true)
	private String modifierIp;

	@RemoteProperty
	@Column(name = "MODIFY_TIME",length=0,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "OCCUPATION_ID",length=255,nullable=true,updatable=true)
	private String occupationId;

	@RemoteProperty
	@Column(name = "ORG_ID",length=10,nullable=true,updatable=true)
	private Integer orgId;

	@RemoteProperty
	@Column(name = "OTHER_CON",length=255,nullable=true,updatable=true)
	private String otherCon;

	@RemoteProperty
	@Column(name = "PHOTO_PATH",length=255,nullable=true,updatable=true)
	private String photoPath;

	@RemoteProperty
	@Column(name = "POST_ID",length=255,nullable=true,updatable=true)
	private String postId;

	@RemoteProperty
	@Column(name = "REG_RESOURCE",length=255,nullable=true,updatable=true)
	private String regResource;

	@RemoteProperty
	@Column(name = "REG_RESOURCE_CD",length=255,nullable=true,updatable=true)
	private String regResourceCd;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=10,nullable=true,updatable=true)
	private Integer roleId;

	@RemoteProperty
	@Column(name = "SERVICE_CENTER_ID",length=10,nullable=true,updatable=true)
	private Integer serviceCenterId;

	@RemoteProperty
	@Column(name = "USER_BIRTHDAY",length=0,nullable=true,updatable=true)
	private Date userBirthday;

	@RemoteProperty
	@Column(name = "USER_EMAIL",length=255,nullable=true,updatable=true)
	private String userEmail;

	@RemoteProperty
	@Column(name = "USER_NAME",length=255,nullable=true,updatable=true)
	private String userName;

	@RemoteProperty
	@Column(name = "USER_SEX",length=255,nullable=true,updatable=true)
	private String userSex;

	@RemoteProperty
	@Column(name = "USER_TYPE_FLAG",length=255,nullable=true,updatable=true)
	private String userTypeFlag;

	@RemoteProperty
	@Column(name = "USER_TYPE_ID",length=10,nullable=true,updatable=true)
	private Integer userTypeId;


	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getUserId(){
		return this.userId;
	}
	public void setCellTel(String cellTel){
		this.cellTel = cellTel;
	}
	public String getCellTel(){
		return this.cellTel;
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
	public void setDomicilePlace(String domicilePlace){
		this.domicilePlace = domicilePlace;
	}
	public String getDomicilePlace(){
		return this.domicilePlace;
	}
	public void setHobbiesId(String hobbiesId){
		this.hobbiesId = hobbiesId;
	}
	public String getHobbiesId(){
		return this.hobbiesId;
	}
	public void setIdCardNo(String idCardNo){
		this.idCardNo = idCardNo;
	}
	public String getIdCardNo(){
		return this.idCardNo;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setIsLocked(String isLocked){
		this.isLocked = isLocked;
	}
	public String getIsLocked(){
		return this.isLocked;
	}
	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastLoginTime(){
		return this.lastLoginTime;
	}
	public void setLoginId(String loginId){
		this.loginId = loginId;
	}
	public String getLoginId(){
		return this.loginId;
	}
	public void setLoginPwd(String loginPwd){
		this.loginPwd = loginPwd;
	}
	public String getLoginPwd(){
		return this.loginPwd;
	}
	public void setLoginTimes(Integer loginTimes){
		this.loginTimes = loginTimes;
	}
	public Integer getLoginTimes(){
		return this.loginTimes;
	}
	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}
	public String getMaritalStatus(){
		return this.maritalStatus;
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
	public void setOccupationId(String occupationId){
		this.occupationId = occupationId;
	}
	public String getOccupationId(){
		return this.occupationId;
	}
	public void setOrgId(Integer orgId){
		this.orgId = orgId;
	}
	public Integer getOrgId(){
		return this.orgId;
	}
	public void setOtherCon(String otherCon){
		this.otherCon = otherCon;
	}
	public String getOtherCon(){
		return this.otherCon;
	}
	public void setPhotoPath(String photoPath){
		this.photoPath = photoPath;
	}
	public String getPhotoPath(){
		return this.photoPath;
	}
	public void setPostId(String postId){
		this.postId = postId;
	}
	public String getPostId(){
		return this.postId;
	}
	public void setRegResource(String regResource){
		this.regResource = regResource;
	}
	public String getRegResource(){
		return this.regResource;
	}
	public void setRegResourceCd(String regResourceCd){
		this.regResourceCd = regResourceCd;
	}
	public String getRegResourceCd(){
		return this.regResourceCd;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public Integer getRoleId(){
		return this.roleId;
	}
	public void setServiceCenterId(Integer serviceCenterId){
		this.serviceCenterId = serviceCenterId;
	}
	public Integer getServiceCenterId(){
		return this.serviceCenterId;
	}
	public void setUserBirthday(Date userBirthday){
		this.userBirthday = userBirthday;
	}
	public Date getUserBirthday(){
		return this.userBirthday;
	}
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}
	public String getUserEmail(){
		return this.userEmail;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setUserSex(String userSex){
		this.userSex = userSex;
	}
	public String getUserSex(){
		return this.userSex;
	}
	public void setUserTypeFlag(String userTypeFlag){
		this.userTypeFlag = userTypeFlag;
	}
	public String getUserTypeFlag(){
		return this.userTypeFlag;
	}
	public void setUserTypeId(Integer userTypeId){
		this.userTypeId = userTypeId;
	}
	public Integer getUserTypeId(){
		return this.userTypeId;
	}
}