package com.tcj.common.enums;

public enum CommentStatusEnum {
	
	STATUS_UNCHECKED(0,"未审核"),
	STATUS_CHECKED(1,"已审核"),
	STATUS_BEST(2,"精华"),	
	STATUS_DELETE(3,"被删除");
	
	
	 private int code;
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

	private String description;
	
	CommentStatusEnum(int code, String description) {
	        this.code = code;// 数字
	        this.description = description;// 描述
	    }
	
	
	
}