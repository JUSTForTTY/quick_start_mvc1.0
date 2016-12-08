package com.tcj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CategoryType {
	/*
	 * 动态新闻
	 */
	CATEGORY_TYPE_DYNAMICNEWS("1", "动态新闻"),
	/*
	 * 家长必读
	 */
	CATEGORY_TYPE_MUSTREAD("2", "家长必读"),
	/*
	 * 课程体系
	 */
	CATEGORY_TYPE_CURRICULUM("3", "课程体系"),
	/*
	 * 市场前景
	 */
	CATEGORY_TYPE_MARKETETPROSPECT("4", "市场前景"),
	/*
	 * 商业模式
	 */
	CATEGORY_TYPE_BUSINESSMODEL("5", "商业模式"),
	/*
	 * 加盟优势
	 */
	CATEGORY_TYPE_ADVANTAGE("6", "加盟优势"),

	/*
	 * 
	 * 加盟流程
	 */

	CATEGORY_TYPE_JOININGPROCESS("7", "加盟流程"),
	/*
	 * 
	 * 走进天才纪
	 */
	CATEGORY_TYPE_JOINGENIUS("8", "走进天才纪"),
	/*
	 * 
	 * 天才纪发展
	 */
	CATEGORY_TYPE_DEVELOPMENT("9", "天才纪发展"),
	/*
	 * 
	 * 明星学员
	 */
	CATEGORY_TYPE_STARMEMBER("10", "明星学员"),
	
	/*
	 * 
	 * 天才纪发展
	 */
	CATEGORY_TYPE_BIGEVENT("11", "天才纪大事件"),
	/*
	 * 课程体系
	 */
	CATEGORY_TYPE_CURRICULUMINTRO("12", "课程体系介绍"),
	/*
	 * 名师风采
	 */
	CATEGORY_TYPE_TEACHERS("13", "名师风采"),
	/*
	 * 名师风采
	 */
	CATEGORY_TYPE_VIDEOS("14", "相关视频"),
	/**
	 * 滑动新闻
	 * 
	 */
	CATEGORY_TYPE_ROLLING_NEWS("15","滑动新闻"),
/**
 * 
 * 个性化流程
 */
	CATEGORY_TYPE_PERSONPROCESS("16","个性化流程"),
	
	/**
	 * 关于天才纪
	 * 
	 * 
	 */
	CATEGORY_TYPE_Genius("17","关于天才纪"),
	/**
	 * 关于董事长
	 * 
	 * 
	 */
	CATEGORY_TYPE_CEO("18","关于董事长"),
	
	
	/**
	 * 
	 * 加入我们
	 * 
	 * 
	 */
	CATEGORY_TYPE_JOINWE("19","加入我们");
	
	
	
	
	
	
	
	
	private String code;// 数字
	private String description;// 描述（如天才纪发展）

	CategoryType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public boolean equals(String code) {
		if (this.code.equals(code)) {
			return true;
		}
		return false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<CategoryType> toList() {
		List<CategoryType> result = new ArrayList<CategoryType>();
		for (CategoryType institution : values()) {
			result.add(institution);
		}
		return result;
	}

	public static Map<String, String> toMap() {
		Map<String, String> enumDataMap = new HashMap<String, String>();
		for (CategoryType e : CategoryType.values()) {
			enumDataMap.put(String.valueOf(e.getCode()), e.getDescription());
		}
		return enumDataMap;
	}
}
