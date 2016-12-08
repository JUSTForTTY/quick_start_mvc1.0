package com.tcj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单状态
 * @author chengy
 *
 */
public enum EnumOrderInfoStatus {
    CANCEL(0, "已取消"),

    WAIT_PAY(11, "待付款"),

    DELIVERYING_NOT_PAIED(12, "配送中（未付款）"),

    PAYING(21, "付款中"),

    DELIVERYING_PAID(32, "配送中（已付款）"),

    SUCCESS(33, "已下单");

	
    private Integer code;
    private String  description;

    EnumOrderInfoStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public boolean equals(String code) {
        if (this.code.equals(code)) {
            return true;
        }
        return false;
    }

    public boolean equals(EnumOrderInfoStatus enumType) {
        if (enumType != null && this.code.equals(enumType.getCode())) {
            return true;
        }
        return false;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<EnumOrderInfoStatus> toList() {
        List<EnumOrderInfoStatus> result = new ArrayList<EnumOrderInfoStatus>();
        for (EnumOrderInfoStatus institution : values()) {
            result.add(institution);
        }
        return result;
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumOrderInfoStatus e : EnumOrderInfoStatus.values()) {
            enumDataMap.put(e.getCode(), e.getDescription());
        }
        return enumDataMap;
    }

}
