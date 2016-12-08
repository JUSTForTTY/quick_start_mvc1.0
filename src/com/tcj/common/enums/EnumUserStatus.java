package com.tcj.common.enums;

public enum EnumUserStatus {
    
	
	STATUS_NO(0,"未删除"),
	STATUS_YES(1,"已删除"),
	USERTYPE_SUPER(0,"超级管理员"),
	USERTYPE_MANAGE(1,"管理员"),
	USERTYPE_USER(2,"用户");
	
	 private int code;
	 private String description;
	 
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	EnumUserStatus(int code,String description){
		this.code=code;
		this.description=description;
	}	
}
