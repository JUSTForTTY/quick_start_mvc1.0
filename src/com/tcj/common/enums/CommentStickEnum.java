package com.tcj.common.enums;

public enum CommentStickEnum {

	STICK_NO(0,"未置顶"),
	STICK_YES(1,"置顶");
	
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
	CommentStickEnum(int code,String description){
		this.code=code;
		this.description=description;
	}
	
	
}
