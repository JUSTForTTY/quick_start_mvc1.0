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
@Table(name = "t_sys_role_menu")
@DataTransferObject
public class T_Sys_Role_Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "ROLE_ID",length=10,updatable=true)
	private Integer roleId;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "FUNCTION_ID",length=10,updatable=true)
	private Integer functionId;

	@RemoteProperty
	@Column(name = "FUNCTION_NAME",length=40,updatable=true)
	private String functionName;

	@RemoteProperty
	@Column(name = "IS_END_MENU",length=1,updatable=true)
	private String isEndMenu;

	@RemoteProperty
	@Column(name = "ROLE_NAME",length=100,updatable=true)
	private String roleName;


	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public Integer getRoleId(){
		return this.roleId;
	}
	public void setFunctionId(Integer functionId){
		this.functionId = functionId;
	}
	public Integer getFunctionId(){
		return this.functionId;
	}
	public void setFunctionName(String functionName){
		this.functionName = functionName;
	}
	public String getFunctionName(){
		return this.functionName;
	}
	public void setIsEndMenu(String isEndMenu){
		this.isEndMenu = isEndMenu;
	}
	public String getIsEndMenu(){
		return this.isEndMenu;
	}
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	public String getRoleName(){
		return this.roleName;
	}
}