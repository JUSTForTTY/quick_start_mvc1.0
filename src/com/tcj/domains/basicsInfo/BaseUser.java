package com.tcj.domains.basicsInfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @author jiayy
 * @version 1.0 2015/03/11
 * @param 基础
 *            -用户表 t_base_user
 */
@Entity(name="BaseUser")
@Table(name = "t_base_user")
@DataTransferObject
public class BaseUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@RemoteProperty
	@Column(name = "USER_ID")
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	private Integer userId;// 用户ID

	@RemoteProperty
	@Column(name = "PHOTO_PATH")
	private String photoPath;//照片路径

	@RemoteProperty
	@Column(name = "LOGIN_ID")
	private String loginId;//账号
	
	@RemoteProperty
	@Column(name = "LOGIN_PWD")
	private String loginPwd;//密码
	
	@RemoteProperty
	@Column(name = "USER_NAME")
	private String userName;//姓名
	
	@RemoteProperty
	@Column(name = "ORG_ID")
	private Integer orgId;//组织机构ID
	
	@RemoteProperty
	@Column(name = "USER_SEX")
	private String userSex;//性别
	
	@RemoteProperty
	@Column(name = "USER_TYPE_ID ")
	private Integer userTypeId;//用户类别ID
	
	@RemoteProperty
	@Column(name = "USER_TYPE_FLAG")
	private String userTypeFlag;//类别区分
	
	@RemoteProperty
	@Column(name = "ROLE_ID")
	private Integer roleId;//角色ID
	
	@RemoteProperty
	@Column(name = "CELL_TEL")
	private String cellTel;//手机/电话
	
	@RemoteProperty
	@Column(name = "USER_EMAIL")
	private String userEmail;//电子邮箱
	
	@RemoteProperty
	@Column(name = "OTHER_CON")
	private String otherCon;//其他联系方式
	
	@RemoteProperty
	@Column(name = "POST_ID")
	private String postId;//职务ID
	
	@RemoteProperty
	@Column(name = "USER_BIRTHDAY")
	private Date userBirthday;//生日
	
	@RemoteProperty
	@Column(name = "ID_CARD_NO")
	private String idCardNo;//身份证号码
	
	@RemoteProperty
	@Column(name = "DOMICILE_PLACE")
	private String domicilePlace;//户籍地
	
	@RemoteProperty
	@Column(name = "SERVICE_CENTER_ID")
	private Integer serviceCenterId;//所属站点
	
	@RemoteProperty
	@Column(name = "LAST_LOGIN_TIME")
	private Date lastLoginTime;//最近登录系统时间
	
	@RemoteProperty
	@Column(name = "LOGIN_TIMES")
	private Integer loginTimes;//累计登录系统次数
	
	@RemoteProperty
	@Column(name = "IS_LOCKED")
	private String isLocked;//账号是否冻结
	
	@RemoteProperty
	@Column(name = "OCCUPATION_ID")
	private String occupationId;//职业ID
	
	@RemoteProperty
	@Column(name = "HOBBIES_ID")
	private String hobbiesId;//兴趣ID
	
	@RemoteProperty
	@Column(name = "REG_RESOURCE_CD")
	private String regResourceCd;//来源系统代码
	
	@RemoteProperty
	@Column(name = "REG_RESOURCE")
	private String regResource;//来源系统
	
	@RemoteProperty
	@Column(name = "IS_DELETED", length = 1, nullable = false)
	private String isDeleted;//
	
	@RemoteProperty
	@Column(name = "CREATE_TIME", nullable = false,updatable = false)
	private Date createTime;//
	
	@RemoteProperty
	@Column(name = "CREATOR",  nullable = false,updatable = false)
	private String creator;//
	
	@RemoteProperty
	@Column(name = "CREATOR_IP",  nullable = false, updatable = false)
	private String creatorIp;//
	
	@RemoteProperty
	@Column(name = "MODIFY_TIME", nullable = false)
	private Date modifyTime;//
	
	@RemoteProperty
	@Column(name = "MODIFIER",  nullable = false)
	private String modifier;//
	
	@RemoteProperty
	@Column(name = "MODIFIER_IP",  nullable = false )
	private String modifierIp;//
	
	@RemoteProperty
	@Column(name = "MARITAL_STATUS",length=1)
	private String maritalStatus;//

	@RemoteProperty
	@Transient
	private String serviceCenterName;
	
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeFlag() {
		return userTypeFlag;
	}

	public void setUserTypeFlag(String userTypeFlag) {
		this.userTypeFlag = userTypeFlag;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getCellTel() {
		return cellTel;
	}

	public void setCellTel(String cellTel) {
		this.cellTel = cellTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getOtherCon() {
		return otherCon;
	}

	public void setOtherCon(String otherCon) {
		this.otherCon = otherCon;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getDomicilePlace() {
		return domicilePlace;
	}

	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public String getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}

	public String getHobbiesId() {
		return hobbiesId;
	}

	public void setHobbiesId(String hobbiesId) {
		this.hobbiesId = hobbiesId;
	}

	public String getRegResourceCd() {
		return regResourceCd;
	}

	public void setRegResourceCd(String regResourceCd) {
		this.regResourceCd = regResourceCd;
	}

	public String getRegResource() {
		return regResource;
	}

	public void setRegResource(String regResource) {
		this.regResource = regResource;
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

	public Integer getServiceCenterId() {
		return serviceCenterId;
	}

	public void setServiceCenterId(Integer serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}

	public String getServiceCenterName() {
		return serviceCenterName;
	}

	public void setServiceCenterName(String serviceCenterName) {
		this.serviceCenterName = serviceCenterName;
	}

	@Override
	public String toString() {
		return "BaseUser [userId=" + userId + ", photoPath=" + photoPath
				+ ", loginId=" + loginId + ", loginPwd=" + loginPwd
				+ ", userName=" + userName + ", orgId=" + orgId + ", userSex="
				+ userSex + ", userTypeId=" + userTypeId + ", userTypeFlag="
				+ userTypeFlag + ", roleId=" + roleId + ", cellTel=" + cellTel
				+ ", userEmail=" + userEmail + ", otherCon=" + otherCon
				+ ", postId=" + postId + ", userBirthday=" + userBirthday
				+ ", idCardNo=" + idCardNo + ", domicilePlace=" + domicilePlace
				+ ", serviceCenterId=" + serviceCenterId + ", lastLoginTime="
				+ lastLoginTime + ", loginTimes=" + loginTimes + ", isLocked="
				+ isLocked + ", occupationId=" + occupationId + ", hobbiesId="
				+ hobbiesId + ", regResourceCd=" + regResourceCd
				+ ", regResource=" + regResource + ", isDeleted=" + isDeleted
				+ ", createTime=" + createTime + ", creator=" + creator
				+ ", creatorIp=" + creatorIp + ", modifyTime=" + modifyTime
				+ ", modifier=" + modifier + ", modifierIp=" + modifierIp
				+ ", maritalStatus=" + maritalStatus + ", serviceCenterName="
				+ serviceCenterName + "]";
	}
	
	
	
}
