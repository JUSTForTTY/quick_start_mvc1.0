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
@Table(name = "t_user_info")
@DataTransferObject
public class T_User_Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "UID",length=10,updatable=true)
	private Integer uid;

	@RemoteProperty
	@Column(name = "ADDRESS",length=100,updatable=true)
	private String address;

	@RemoteProperty
	@Column(name = "AMOUNT",length=12,updatable=true)
	private Float amount;

	@RemoteProperty
	@Column(name = "BIRTHDAY",length=0,updatable=true)
	private Date birthday;

	@RemoteProperty
	@Column(name = "CAMOUNT_C",length=32,updatable=true)
	private String camountC;

	@RemoteProperty
	@Column(name = "COMMENT",length=255,updatable=true)
	private String comment;

	@RemoteProperty
	@Column(name = "COMPANY",length=100,updatable=true)
	private String company;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_USER",length=10,updatable=true)
	private Integer createUser;

	@RemoteProperty
	@Column(name = "GENDER",length=30,updatable=true)
	private String gender;

	@RemoteProperty
	@Column(name = "IS_BOUND",length=1,nullable=true,updatable=true)
	private String isBound;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,nullable=true,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=32,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "MOBILE",length=20,updatable=true)
	private String mobile;

	@RemoteProperty
	@Column(name = "PAY_PWD",length=32,updatable=true)
	private String payPwd;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=1,nullable=true,updatable=true)
	private String roleId;

	@RemoteProperty
	@Column(name = "TAKE_DATE",length=0,updatable=true)
	private Date takeDate;

	@RemoteProperty
	@Column(name = "UNAME",length=20,updatable=true)
	private String uname;

	@RemoteProperty
	@Column(name = "UPDATE_TIME",length=0,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "UPDATE_USER",length=10,updatable=true)
	private Integer updateUser;

	@RemoteProperty
	@Column(name = "WEIXIN_ID",length=30,updatable=true)
	private String weixinId;


	public void setUid(Integer uid){
		this.uid = uid;
	}
	public Integer getUid(){
		return this.uid;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAmount(Float amount){
		this.amount = amount;
	}
	public Float getAmount(){
		return this.amount;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public Date getBirthday(){
		return this.birthday;
	}
	public void setCamountC(String camountC){
		this.camountC = camountC;
	}
	public String getCamountC(){
		return this.camountC;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
	}
	public void setCompany(String company){
		this.company = company;
	}
	public String getCompany(){
		return this.company;
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
	public void setGender(String gender){
		this.gender = gender;
	}
	public String getGender(){
		return this.gender;
	}
	public void setIsBound(String isBound){
		this.isBound = isBound;
	}
	public String getIsBound(){
		return this.isBound;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setLoginPwd(String loginPwd){
		this.loginPwd = loginPwd;
	}
	public String getLoginPwd(){
		return this.loginPwd;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setPayPwd(String payPwd){
		this.payPwd = payPwd;
	}
	public String getPayPwd(){
		return this.payPwd;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public void setTakeDate(Date takeDate){
		this.takeDate = takeDate;
	}
	public Date getTakeDate(){
		return this.takeDate;
	}
	public void setUname(String uname){
		this.uname = uname;
	}
	public String getUname(){
		return this.uname;
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
	public void setWeixinId(String weixinId){
		this.weixinId = weixinId;
	}
	public String getWeixinId(){
		return this.weixinId;
	}
}