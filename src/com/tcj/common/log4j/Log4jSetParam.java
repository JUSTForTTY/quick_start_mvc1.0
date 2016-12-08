package com.tcj.common.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
public class Log4jSetParam {
	
	/**
	 * 登录
	 */
	public static final String LOGIN="00";
	/**
	 * 查询
	 */
	public static final String SEARCH="01";
	/**
	 * 修改
	 */
	public static final String MODIFY="02";
	/**
	 * 新增
	 */
	public static final String ADD="03";
	/**
	 * 删除
	 */
	public static final String DELETE="04";
	/**
	 * 导入
	 */
	public static final String IMPORT="05";
	/**
	 * 导出
	 */
	public static final String EXPORT="06";
	/**
	 * 其它
	 */
	public static final String OTHER="07";
	/***
	 * 打印
	 */
	public static final String PRINT="08";
	
	/**
	 * 给log4j传递参数，记录当前操作
	 * @param functionId : 页面ID,例如："runningAccountManage"
	 * @param functionName : 页面名称,例如："序时账"
	 * @param operationType : Log4jSetParam类的静态变量{登录：LOGIN；查询：SEARCH；修改：MODIFY；新增：ADD；删除：DELETE；导入：IMPORT；导出：EXPORT；其他：OTHER},例如Log4jSetParam.SEARCH
	 * @param operationKeyWord : 操作关键字,例如："删除助记码为C的常用摘要"  (查询可直接传"查询")
	 */
	public void setLog4jParam(String functionId,String functionName,String operationType,String operationKeyWord) {
		MDC.put("functionId",functionId);
		MDC.put("functionName",functionName);
		MDC.put("operationType",operationType);
		MDC.put("operationKeyWord",operationKeyWord);
		MDC.put("specialInfo","");
	}
	/**
	 * 给log4j传递参数，记录当前操作
	 * @param functionId : 页面ID,例如："runningAccountManage"
	 * @param functionName : 页面名称,例如："序时账"
	 * @param operationType : Log4jSetParam类的静态变量{登录：LOGIN；查询：SEARCH；修改：MODIFY；新增：ADD；删除：DELETE；导入：IMPORT；导出：EXPORT；其他：OTHER},例如Log4jSetParam.SEARCH
	 * @param operationKeyWord : 操作关键字,例如："删除助记码为C的常用摘要"  (查询可直接传"查询")
	 * @param specialInfo 特殊备注，不是显示
	 */
	public void setLog4jParam(String functionId,String functionName,String operationType,String operationKeyWord,String specialInfo) {
		MDC.put("functionId",functionId);
		MDC.put("functionName",functionName);
		MDC.put("operationType",operationType);
		MDC.put("operationKeyWord",operationKeyWord);
		MDC.put("specialInfo",specialInfo);
	}
	

	public static Log log = LogFactory.getLog("dbLog");
	
	public static void debug(Object o){
		if(log.isInfoEnabled())
			log.debug(o);
	}
	
	public static void info(Object o){
		if(log.isInfoEnabled())
			log.info(o);
	}
	
	public static void info(){
		if(log.isInfoEnabled())
			log.info("成功");
	}
	
	public static void warn(Object o){
		if(log.isInfoEnabled())
			log.warn(o);
	}
	
	public static void error(Object o){
		if(log.isInfoEnabled())
			log.error(o);
	}
	
}
