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
@Table(name = "t_supply_info")
@DataTransferObject
public class T_Supply_Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "SID",length=10,updatable=true)
	private Integer sid;

	@RemoteProperty
	@Column(name = "ADDRESS",length=100,updatable=true)
	private String address;

	@RemoteProperty
	@Column(name = "BACKUP",length=20,updatable=true)
	private String backup;

	@RemoteProperty
	@Column(name = "CHARGEMAN",length=20,updatable=true)
	private String chargeman;

	@RemoteProperty
	@Column(name = "COMMENT",length=255,updatable=true)
	private String comment;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=32,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "MOBILE",length=20,updatable=true)
	private String mobile;

	@RemoteProperty
	@Column(name = "ORGAN_CODE",length=20,updatable=true)
	private String organCode;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=1,updatable=true)
	private String roleId;

	@RemoteProperty
	@Column(name = "SNAME",length=30,updatable=true)
	private String sname;


	public void setSid(Integer sid){
		this.sid = sid;
	}
	public Integer getSid(){
		return this.sid;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setBackup(String backup){
		this.backup = backup;
	}
	public String getBackup(){
		return this.backup;
	}
	public void setChargeman(String chargeman){
		this.chargeman = chargeman;
	}
	public String getChargeman(){
		return this.chargeman;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
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
	public void setOrganCode(String organCode){
		this.organCode = organCode;
	}
	public String getOrganCode(){
		return this.organCode;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public void setSname(String sname){
		this.sname = sname;
	}
	public String getSname(){
		return this.sname;
	}
}