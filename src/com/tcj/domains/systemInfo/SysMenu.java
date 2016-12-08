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
 * @param 系统-菜单表【固定】  t_sys_menu
 *
 */

@Entity
@Table(name = "t_sys_menu")
@DataTransferObject
public class SysMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MENU_ID")
	private Integer menuId;//菜单ID
	
	@Column(name = "MENU_NAME",length = 40,nullable=false)
	private String menuName;//菜单名称
	
	@Column(name = "F_MENU_ID",length = 60)
	private String fMenuId;//父菜单ID
	
	@Column(name = "MENU_SRC",length = 80)
	private String menuSrc;//菜单页面路径
	
	@Column(name = "IS_END_MENU",length = 1)
	private String isEndMenu;//是否为末级菜单
	
	@Column(name = "MEMO",length = 100)
	private String memo;//备注
	
	@Column(name = "SORT")
	private Integer sort;//排序

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getfMenuId() {
		return fMenuId;
	}

	public void setfMenuId(String fMenuId) {
		this.fMenuId = fMenuId;
	}

	public String getMenuSrc() {
		return menuSrc;
	}

	public void setMenuSrc(String menuSrc) {
		this.menuSrc = menuSrc;
	}

	public String getIsEndMenu() {
		return isEndMenu;
	}

	public void setIsEndMenu(String isEndMenu) {
		this.isEndMenu = isEndMenu;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
