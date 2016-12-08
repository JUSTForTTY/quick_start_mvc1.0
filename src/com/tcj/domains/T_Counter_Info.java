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
@Table(name = "t_counter_info")
@DataTransferObject
public class T_Counter_Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "CID",length=10,updatable=true)
	private Integer cid;

	@RemoteProperty
	@Column(name = "ADDRESS",length=100,updatable=true)
	private String address;

	@RemoteProperty
	@Column(name = "CNAME",length=20,updatable=true)
	private String cname;

	@RemoteProperty
	@Column(name = "COMMENT",length=255,updatable=true)
	private String comment;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_USER",length=10,updatable=true)
	private Integer createUser;

	@RemoteProperty
	@Column(name = "IS_BOUND",length=1,updatable=true)
	private String isBound;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "LATITUDE",length=12,updatable=true)
	private Float latitude;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=32,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "LONGITUDE",length=12,updatable=true)
	private Float longitude;

	@RemoteProperty
	@Column(name = "MID",length=10,updatable=true)
	private Integer mid;

	@RemoteProperty
	@Column(name = "MOBILE",length=20,updatable=true)
	private String mobile;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=1,updatable=true)
	private String roleId;

	@RemoteProperty
	@Column(name = "TEL_NUM",length=20,updatable=true)
	private String telNum;

	@RemoteProperty
	@Column(name = "TEL_NUM2",length=20,updatable=true)
	private String telNum2;

	@RemoteProperty
	@Column(name = "UPDATE_TIME",length=0,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "UPDATE_USER",length=10,updatable=true)
	private Integer updateUser;

	@RemoteProperty
	@Column(name = "WEIXIN_ID",length=30,updatable=true)
	private String weixinId;


	public void setCid(Integer cid){
		this.cid = cid;
	}
	public Integer getCid(){
		return this.cid;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setCname(String cname){
		this.cname = cname;
	}
	public String getCname(){
		return this.cname;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
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
	public void setLatitude(Float latitude){
		this.latitude = latitude;
	}
	public Float getLatitude(){
		return this.latitude;
	}
	public void setLoginPwd(String loginPwd){
		this.loginPwd = loginPwd;
	}
	public String getLoginPwd(){
		return this.loginPwd;
	}
	public void setLongitude(Float longitude){
		this.longitude = longitude;
	}
	public Float getLongitude(){
		return this.longitude;
	}
	public void setMid(Integer mid){
		this.mid = mid;
	}
	public Integer getMid(){
		return this.mid;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public void setTelNum(String telNum){
		this.telNum = telNum;
	}
	public String getTelNum(){
		return this.telNum;
	}
	public void setTelNum2(String telNum2){
		this.telNum2 = telNum2;
	}
	public String getTelNum2(){
		return this.telNum2;
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