package com.tcj.common.util;

/**
 * @author MINGYULU 
 * @version 2.1
 * @since June 28,2016
 * 描述：报告状态枚举，对应CodeMaster表中的CodeType为501
 * @modify June 30,2016 ,添加了新的枚举数值UNAPPROVALBEKNOW，并更新了valueOf。
 *
 */
public enum ReportMenu {
	/**
	 * 未审核
	 */
	UNAUDITING("未审核",0), 
	/**
	 * 已审核
	 */
	AUDITING("已审核",1),
	/**
	 * 不批准
	 */
	UNAPPROVAL("不批准",2),
	/**
	 * 到港
	 */
	ARRIVIED("到港",3), 
	/**
	 * 出港
	 */
	EXCITED("出港",4), 
	/**
	 * 未审核取消
	 */
	UNADUITINGCANCLE("未审核取消",5), 
	/**
	 * 已审核取消
	 */
	AUDITINGCANCLE("已审核取消",6),
	/**
	 * 未批准已知晓
	 */
	UNAPPROVALBEKNOW("未批准已知晓",7);
	
	/**
	 * 枚举相应的名字
	 */
	private String name;

	/**
	 * 枚举相应的数值
	 */
	private int value;

	/**
	 * 
	 * @param name
	 * 			枚举名称
	 * @param value
	 * 			枚举数值
	 */
	private ReportMenu(String name,int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}

	/**
	 * 静态方法
	 * @param intValue
	 * 				枚举对应的值
	 * @return ReportMenu
	 * 				枚举对象，用于Switch
	 * 
	 */
	
	public static ReportMenu valueOf(int intValue) {
		ReportMenu r = null;
		switch(intValue){
		case 0:
			r = ReportMenu.UNAUDITING;break;
		case 1:
			r = ReportMenu.AUDITING;break;
		case 2:
			r = ReportMenu.UNAPPROVAL;break;
		case 3:
			r = ReportMenu.ARRIVIED;break;
		case 4:
			r = ReportMenu.EXCITED;break;
		case 5:
			r = ReportMenu.UNADUITINGCANCLE;break;
		case 6:
			r = ReportMenu.AUDITINGCANCLE;break;
		case 7:
			r = ReportMenu.UNAPPROVALBEKNOW;break;
			
		}
		return r;
	}

}
