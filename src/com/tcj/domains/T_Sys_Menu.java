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
@Table(name = "t_sys_menu")
@DataTransferObject
public class T_Sys_Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "MENU_ID",length=10,updatable=true)
	private Integer menuId;

	@RemoteProperty
	@Column(name = "F_MENU_ID",length=60,nullable=true,updatable=true)
	private String fMenuId;

	@RemoteProperty
	@Column(name = "IS_END_MENU",length=1,nullable=true,updatable=true)
	private String isEndMenu;

	@RemoteProperty
	@Column(name = "MEMO",length=100,nullable=true,updatable=true)
	private String memo;

	@RemoteProperty
	@Column(name = "MENU_NAME",length=40,updatable=true)
	private String menuName;

	@RemoteProperty
	@Column(name = "MENU_SRC",length=80,nullable=true,updatable=true)
	private String menuSrc;

	@RemoteProperty
	@Column(name = "SORT",length=10,nullable=true,updatable=true)
	private Integer sort;


	public void setMenuId(Integer menuId){
		this.menuId = menuId;
	}
	public Integer getMenuId(){
		return this.menuId;
	}
	public void setFMenuId(String fMenuId){
		this.fMenuId = fMenuId;
	}
	public String getFMenuId(){
		return this.fMenuId;
	}
	public void setIsEndMenu(String isEndMenu){
		this.isEndMenu = isEndMenu;
	}
	public String getIsEndMenu(){
		return this.isEndMenu;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	public String getMemo(){
		return this.memo;
	}
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public String getMenuName(){
		return this.menuName;
	}
	public void setMenuSrc(String menuSrc){
		this.menuSrc = menuSrc;
	}
	public String getMenuSrc(){
		return this.menuSrc;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return this.sort;
	}
}