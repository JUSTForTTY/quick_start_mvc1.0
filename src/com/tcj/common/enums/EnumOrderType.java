package com.tcj.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumOrderType {

	ORDERINFO_STATUS_NEW((short) 1121, "订单未付款"), 
	ORDERINFO_STATUS_ALIPAYING_NOSTOCKUP((short) 3321, "已下单（支付宝）"), 
	ORDERINFO_STATUS_COD_NOSTOCKUP((short) 3221, "已下单（货到付款）"), 
	ORDERINFO_STATUS_PREPAY_NOSTOCKUP((short) 3421, "已下单（预存款）"), 
	ORDERINFO_STATUS_CLOUD_INVENTORY_NOSTOCKUP((short) 3521, "已下单（云库存）"), 
	ORDERINFO_STATUS_ALIPAYING_STOCKUP((short) 3322, "支付宝付款后备货中"), 
	
	ORDERINFO_STATUS_COD_STOCKUP((short) 3222, "货到付款备货"), 
	ORDERINFO_STATUS_PREPAY_STOCKUP((short) 3422, "预付款备货"), 
	
	ORDERINFO_STATUS_CLOUD_INVENTORY_STOCKUP((short) 3522, "云库存备货"), 
	ORDERINFO_STATUS_ALIPAYING_SENDING((short) 3323, "支付宝配送中"), 
	
	ORDERINFO_STATUS_COD_SENDING((short) 3223, "货到付款配送中"), 
	ORDERINFO_STATUS_PREPAY_SENDING((short) 3423, "预付款配送中"), 
	
	ORDERINFO_STATUS_CLOUD_INVENTORY_SENDING((short) 3523, "云库存配送中"), 
	
	ORDERINFO_STATUS_ALIPAYING_SENDED((short) 3324, "支付宝已交货"), 
	ORDERINFO_STATUS_COD_SENDED((short) 3224, "货到付款已交货"), 
	ORDERINFO_STATUS_PREPAY_SENDED((short) 3424, "预付款已交货"), 
	ORDERINFO_STATUS_CLOUD_INVENTORY_SENDED((short) 3524, "云库存已交货"),
 
	ORDERINFO_STATUS_COMMENT((short)4425,"已评论");
   

    private short  code;

    private String desc;

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    EnumOrderType(short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Map<String, String> toMap() {
        Map<String, String> enumDataMap = new HashMap<String, String>();
        for (EnumOrderType e : EnumOrderType.values()) {
            enumDataMap.put(String.valueOf(e.getCode()), e.getDesc());
        }
        return enumDataMap;
    }
}
