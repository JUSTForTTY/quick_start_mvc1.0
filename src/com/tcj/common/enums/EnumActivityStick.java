package com.tcj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EnumActivityStick {

	 /**
     * 活动不置顶
     */
    ACTIVITY_TYPE_NOStick("0", "不置顶"),

    /**
     * 活动置顶
     */
    ACTIVITY_TYPE_Stick("1", "置顶");


    private String code;
    private String description;

    EnumActivityStick(String code, String description) {
        this.code = code;// 数字
        this.description = description;// 描述
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

    public static List<EnumActivityStick> toList() {
        List<EnumActivityStick> result = new ArrayList<EnumActivityStick>();
        for (EnumActivityStick institution : values()) {
            result.add(institution);
        }
        return result;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (EnumActivityStick e : EnumActivityStick.values()) {
            enumDataMap.put(String.valueOf(e.getCode()), e.getDescription());
        }
        return enumDataMap;
    }
	
}
