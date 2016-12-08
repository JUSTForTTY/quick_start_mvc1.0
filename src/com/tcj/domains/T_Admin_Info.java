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
@Table(name = "t_admin_info")
@DataTransferObject
public class T_Admin_Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "AID",length=10,updatable=true)
	private Integer aid;

	@RemoteProperty
	@Column(name = "ANAME",length=30,updatable=true)
	private String aname;

	@RemoteProperty
	@Column(name = "BACKUP",length=20,updatable=true)
	private String backup;

	@RemoteProperty
	@Column(name = "COMMENT",length=255,updatable=true)
	private String comment;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=32,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=1,updatable=true)
	private String roleId;


	public void setAid(Integer aid){
		this.aid = aid;
	}
	public Integer getAid(){
		return this.aid;
	}
	public void setAname(String aname){
		this.aname = aname;
	}
	public String getAname(){
		return this.aname;
	}
	public void setBackup(String backup){
		this.backup = backup;
	}
	public String getBackup(){
		return this.backup;
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
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
}