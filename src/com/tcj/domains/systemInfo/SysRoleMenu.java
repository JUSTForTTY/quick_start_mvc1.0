package com.tcj.domains.systemInfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;

/***
 * 
 * @author lul
 * @version 1.0 10-10
 * @param 系统-角色菜单关系  t_sys_role_MENU
 *
 */

@Entity
@Table(name = "t_sys_role_menu")
@DataTransferObject
public class SysRoleMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FUNCTION_ID")
	private Integer functionId;//功能ID
	
	@Column(name = "FUNCTION_NAME",length = 40,nullable=false)
	private String functionName;//功能名称
	
	@Id
	@Column(name = "ROLE_ID")
	private Integer roleId;//角色ID
	
	@Column(name = "ROLE_NAME",length = 100,nullable=false)
	private String roleName;//角色名称
	
	@Column(name = "IS_END_MENU",length = 1,nullable=false)
	private String isEndMenu;//是否为末级菜单

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIsEndMenu() {
		return isEndMenu;
	}

	public void setIsEndMenu(String isEndMenu) {
		this.isEndMenu = isEndMenu;
	}
}
