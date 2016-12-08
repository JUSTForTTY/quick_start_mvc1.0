package com.tcj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EnumActivityStatus {

	 /**
     * 活动正常
     */
    ACTIVITY_TYPE_NORMAL("0", "正常"),

    /**
     * 活动已取消
     */
    ACTIVITY_TYPE_CANCEL("1", "已取消"),

    /**
     * 活动已结束
     */
    ACTIVITY_TYPE_END("2", "已结束");
	 

    private String code;
    private String description;

    EnumActivityStatus(String code, String description) {
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

    public static List<EnumActivityStatus> toList() {
        List<EnumActivityStatus> result = new ArrayList<EnumActivityStatus>();
        for (EnumActivityStatus institution : values()) {
            result.add(institution);
        }
        return result;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (EnumActivityStatus e : EnumActivityStatus.values()) {
            enumDataMap.put(String.valueOf(e.getCode()), e.getDescription());
        }
        return enumDataMap;
    }
	
}
