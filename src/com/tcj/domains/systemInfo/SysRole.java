package com.tcj.domains.systemInfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @author lul
 * @version 1.0 10-09
 * @param 系统-角色表  t_sys_role
 */

@Entity
@Table(name = "t_sys_role")
@DataTransferObject
public class SysRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	private Integer roleId;//角色ID
	
	@Column(name = "ROLE_NAME",length = 100,nullable=false)
	private String roleName;//角色名称
	
	@Column(name = "MEMO",length = 200)
	private String memo;//备注
	
	@Column(name = "IS_DEFAULT_ROLE",length = 1)
	private String isDefaultRole;//是否默认角色
	
	@Column(name = "IS_DELETED",length = 1,nullable=false)
	private String isDeleted;//是否删除
	
	@Column(name = "CREATE_TIME",updatable = false,nullable=false)
	private Date createTime;//创建时间
	
	@Column(name = "CREATOR",length = 40,updatable = false,nullable=false)
	private String creator;//创建人
	
	@Column(name = "CREATOR_IP",length = 15,updatable = false,nullable=false)
	private String creatorIp;//创建IP
	
	@Column(name = "MODIFY_TIME",nullable=false)
	private Date modifyTime;//修改时间
	
	@Column(name = "MODIFIER",length = 40,nullable=false)
	private String modifier;//修改人
	
	@Column(name = "MODIFIER_IP",length = 15,nullable=false)
	private String modifierIp;//修改IP

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIsDefaultRole() {
		return isDefaultRole;
	}

	public void setIsDefaultRole(String isDefaultRole) {
		this.isDefaultRole = isDefaultRole;
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
}
