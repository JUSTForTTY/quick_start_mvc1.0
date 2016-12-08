package com.tcj.domains;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

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

import com.tcj.common.util.DateUtil;


@Entity
@Table(name = "t_demo")
@DataTransferObject
public class DemoManage implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@RemoteProperty
	@Id
	@Column(name = "Id",length=11,updatable=true)
	private String id;

	@RemoteProperty
	@Column(name = "USER_NAME",length=255,nullable=true,updatable=true)
	private String username;

	@RemoteProperty
	@Column(name = "USER_PASS",length=255,nullable=true,updatable=true)
	private String userpass;
	
	@RemoteProperty
	@Column(name = "USER_AGE",length=255,nullable=true,updatable=true)
	private String userage;
	
	@RemoteProperty
	@Column(name = "status",length=11,nullable=true,updatable=true)
	private  Integer status;
	
	@RemoteProperty
	@Column(name = "create_user",length=32,nullable=true,updatable=true)
	private  String createuser;
	
	@RemoteProperty
	@Column(name = "create_time",nullable=true,updatable=true)
	private  Date createtime;
	@RemoteProperty
	@Column(name = "modify_user",length=32,nullable=true,updatable=true)
	private  String modifyuser;
	
	@RemoteProperty
	@Column(name = "modify_time",nullable=true,updatable=true)
	private  Date modifytime;
	

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUserage() {
		return userage;
	}

	public void setUserage(String userage) {
		this.userage = userage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}