package com.tcj.common.util;

import java.io.InputStream;
import java.util.Properties;

public class DilectSqlUtil {
	
	private static String db;
	private static final String ORACLE = "oracle";
	private static final String MYSQL = "mysql";
	private static final String SQLSERVER = "sqlserver";
//	static{
//		db = (String) ServletActionContext.getServletContext().getAttribute("DB");
//	}
	
	static{
		Properties p = new Properties();
		try{			
			InputStream in = DilectSqlUtil.class.getResourceAsStream("/jdbc.properties");			
			p.load(in);
			db= p.getProperty("db.name");
			in.close();
			if(SQLSERVER.equals(db)){
				DICTIONARY_COLUMS = "SYSCOLUMNS";
				DICTIONARY_COLUMS_TABLE_NAME = "OBJECT_NAME(ID)";
				DICTIONARY_COLUMS_COLUM_NAME = "NAME";
				DICTIONARY_COLUMS_LENGTH = "LENGTH";
				DICTIONARY_COLUMS_SCALE = "SCALE";
				DICTIONARY_COLUMS_PRECISION = "PREC";
			}else if(MYSQL.equals(db)){
				DICTIONARY_COLUMS = "COLUMNS";
				DICTIONARY_COLUMS_TABLE_NAME = "TABLE_NAME";
				DICTIONARY_COLUMS_COLUM_NAME = "COLUMN_NAME";
				DICTIONARY_COLUMS_LENGTH = "DATA_LENGTH";
				DICTIONARY_COLUMS_SCALE = "DATA_SCALE";
				DICTIONARY_COLUMS_PRECISION = "DATA_PRECISION";
			}else if(ORACLE.equals(db)){
				DICTIONARY_COLUMS = "COLS";
				DICTIONARY_COLUMS_TABLE_NAME = "TABLE_NAME";
				DICTIONARY_COLUMS_COLUM_NAME = "COLUMN_NAME";
				DICTIONARY_COLUMS_LENGTH = "DATA_LENGTH";
				DICTIONARY_COLUMS_SCALE = "DATA_SCALE";
				DICTIONARY_COLUMS_PRECISION = "DATA_PRECISION";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getDb(){
		return db;
	}
	
	/**
	 * 列名的表
	 */
	private static String DICTIONARY_COLUMS;
	/**
	 * 列名的表
	 */
	public static String DICTIONARY_COLUMS(){
		return DICTIONARY_COLUMS;
	}
	/**
	 * 表名字段--用来查询的字段
	 */
	private static String DICTIONARY_COLUMS_TABLE_NAME;
	/**
	 * 表名字段--用来查询的字段
	 */
	public static String DICTIONARY_COLUMS_TABLE_NAME(){
		return DICTIONARY_COLUMS_TABLE_NAME;
	}
	/**
	 * 列名名字段
	 */
	private static String DICTIONARY_COLUMS_COLUM_NAME;
	/**
	 * 列名名字段
	 */
	public static String DICTIONARY_COLUMS_COLUM_NAME(){
		return DICTIONARY_COLUMS_COLUM_NAME;
	}
	/**
	 * 列长度字段
	 */
	private static String DICTIONARY_COLUMS_LENGTH;
	/**
	 * 列长度字段
	 */
	public static String DICTIONARY_COLUMS_LENGTH(){
		return DICTIONARY_COLUMS_LENGTH;
	}
	/**
	 * 列SCALE字段
	 */
	private static String DICTIONARY_COLUMS_SCALE;
	/**
	 * 列SCALE字段
	 */
	public static String DICTIONARY_COLUMS_SCALE(){
		return DICTIONARY_COLUMS_SCALE;
	}
	/**
	 * 列PRECISION字段
	 */
	private static String DICTIONARY_COLUMS_PRECISION;
	/**
	 * 列PRECISION字段
	 */
	public static String DICTIONARY_COLUMS_PRECISION(){
		return DICTIONARY_COLUMS_PRECISION;
	}
	
	/**
	 * 表名字段--用来查询的条件
	 */
	public static String DICTIONARY_COLUMS_TABLE_NAME_CONDITION(String tableName){
		String res = "";
		if(SQLSERVER.equals(db)){
			res = "ID = OBJECT_ID('" + tableName + "')";
		}else if(MYSQL.equals(db) || ORACLE.equals(db)){
			res = DICTIONARY_COLUMS_TABLE_NAME + "= '" + tableName + "'";
		}
		return res;
	}
	/**
	 * 截取字符串--只获取字符串
	 * @author zhangll
	 * @CreateTime 2014-5-13 下午2:59:29
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getSubStrSql(){
		String res = "";
		if(SQLSERVER.equals(db)){
			res = "SUBSTRING";
		}else if(MYSQL.equals(db)){
			res = "SUBSTR";
		}else if(ORACLE.equals(db)){
			res = "SUBSTR";
		}
		return res;
	}
	public static String getSubStrSql2(String column, String beginNum,String endNum){
		String res = "";
		if(SQLSERVER.equals(db)){
			res = "SUBSTRING("+column+","+beginNum+","+endNum+"+1)";
		}else if(MYSQL.equals(db)){
			res = "SUBSTR("+column+","+beginNum+","+endNum+"+1)";
		}else if(ORACLE.equals(db)){
			res = "SUBSTR("+column+","+beginNum+","+endNum+")";
		}
		return res;
	}
	
	/**
	 * 长度函数--只获取字符串
	 * @author zhangll
	 * @CreateTime 2014-5-13 下午2:59:29
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getLengthSql(){
		String res = "";
		if(SQLSERVER.equals(db)){
			res = "LEN";
		}else if(MYSQL.equals(db)){
			res = "LENGTH";
		}else if(ORACLE.equals(db)){
			res = "LENGTH";
		}
		return res;
	}
	/**
	 * Instr函数
	 * @author zhangll
	 * @CreateTime 2014-5-15 下午5:33:29
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getInstrFunc(String column, String inStr){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = "CHARINDEX(" + column + ",'" + inStr + "')";
		}else if(MYSQL.equals(db) || ORACLE.equals(db)){
			sqlStr = "INSTR(" + column + ",'" + inStr + "')";
		}
		return sqlStr;
	}
	/**
	 * 实现LPAD函数
	 * @author zhangll
	 * @CreateTime 2014-5-14 下午12:52:22
	 * @param column 列
	 * @param paddedLength 填充后字符串的长度
	 * @param padStr 填充字符
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getLPadFunc(String column, int paddedLength, String padStr){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			if(padStr == null){//默认添加空格
				padStr = " ";
			}
			if(paddedLength < 0){
				sqlStr = "NULL";
			}else if(paddedLength == 0){
				sqlStr = "''";
			}else{
				sqlStr = "CASE "
					   + "WHEN LEN(" + column + ") >= " + paddedLength + " THEN LEFT(" + column + "," + paddedLength + ") "
					   + "ELSE LEFT(REPLICATE('" + padStr + "'," + paddedLength + "-LEN(" + column + ")" + ")," + paddedLength + "-LEN(" + column + "))+CONVERT(CHAR," + column+ ") "
					   + "END";
			}
		}else if(MYSQL.equals(db) || ORACLE.equals(db)){
			if(padStr == null){//默认添加空格
				sqlStr = "LPAD(" + column + "," + paddedLength + ")";
			}else{
				sqlStr = "LPAD(" + column + "," + paddedLength + ",'" + padStr + "')";
			}
		}
		return sqlStr;
	}
	
	/**
	 * 虚表
	 * @author zhangll
	 * @CreateTime 2014-5-13 下午2:59:29
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getDualSql(){
		String res = "";
		if(ORACLE.equals(db)){
			res = " FROM DUAL ";
		}
		return res;
	}
	
	/**
	 * 得到第一条结果集
	 * @author zhangll
	 * @CreateTime 2014-5-13 上午10:19:23
	 * @param sql
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getFirstResultSql(String sql){
		if(sql == null) return "";
		String res = "";
		if(SQLSERVER.equals(db)){
			int selectIndex = sql.toLowerCase().indexOf("select");
			if (selectIndex > -1) {
				int topIndex = selectIndex + 6;
				res = sql.substring(0, topIndex) + " TOP 1 " + sql.substring(topIndex);
			}else{
				res = " TOP 1 " + sql.substring(selectIndex);
			}
		}else if(MYSQL.equals(db)){
			res = sql + " LIMIT 0,1 ";
		}else if(ORACLE.equals(db)){
			if(sql.toLowerCase().indexOf("where") > -1){
				res = sql + " and ROWNUM = 1 ";
			}else {
				res = sql + " where ROWNUM = 1 ";
			}
		}
		return res;
	}
	
	/**
	 * 字符串转为日期
	 * @param column
	 * @param pattern
	 * @return
	 */
	public static String str2Date(String column,String pattern){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = getPatternSql(pattern).replaceAll("column",column);
			//TODO
		}
		else if(MYSQL.equals(db)){
			sqlStr = "STR_TO_DATE('"+column+"','"+getPatternSql(pattern)+"')";
		}
		else{
			sqlStr = "to_date("+column+",'"+getPatternSql(pattern)+"')";
		}	
		return sqlStr;
	}
	
	/**
	 * 数字型转为字符串
	 * @param column
	 * @param charLength
	 * @return
	 */
	public static String number2Char(String column,Integer charLength){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			if(charLength == null){
				sqlStr = "CONVERT(CHAR," + column + ")";
			}else{
				sqlStr = "CONVERT(CHAR(" + charLength + ")," + column + ")";		
			}
		}
		else if(MYSQL.equals(db)){
			if(charLength == null){
				sqlStr = "CAST(" + column + " AS CHAR)";
			}else{
				sqlStr = "CAST(" + column + " AS CHAR(" + charLength + "))";		
			}
		}
		else{
			sqlStr = "to_char(" + column + ")";
		}
		
		return sqlStr;
	}
	
	/**
	 * 字符串转为数字型
	 * @param column
	 * @param numberLength
	 * @param prec 小数位数
	 * @return
	 */
	public static String char2Number(String column,Integer numberLength, Integer prec){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			if(numberLength == null){
				sqlStr = "CONVERT(decimal," + column + ")";
			}else{
				if(prec == null || prec < 0){
					prec = 0;
				}
				sqlStr = "CONVERT(decimal(" + numberLength + "," + prec + ")," + column + ")";
			}
		}
		else if(MYSQL.equals(db)){
			if(numberLength == null){
				sqlStr = "CAST(" + column + " AS decimal)";
			}else{
				sqlStr = "CAST(" + column + " AS decimal(" + numberLength + "," + prec + "))";		
			}
		}
		else{
			sqlStr = "to_number(" + column + ")";
		}
		return sqlStr;
	}
	
	/**
	 * 日期转为字符串
	 * @param column
	 * @param pattern
	 * @return
	 */
	public static String compareDate(String column,String pattern){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = getPatternSql(pattern).replaceAll("column",column);
			//TODO
		}
		else if(MYSQL.equals(db)){
			sqlStr = "date_format("+column+",'"+getPatternSql(pattern)+"')";
		}
		else{
			sqlStr = "to_char("+column+",'"+getPatternSql(pattern)+"')";
		}		
		return sqlStr;
	}
	/**
	 * 日期转为字符串
	 * @param column
	 * @param charLength 1.charLength=null时与compareDate返回一致;2.charLength规定返回字符长度
	 * @param pattern
	 * @return
	 */
	public static String date2Char(String column,Integer charLength,String pattern){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = getPatternSql(pattern).replaceAll("column",column);
			//TODO
		}
		else if(MYSQL.equals(db)){
			if(charLength == null){
				sqlStr = "CAST(date_format("+column+",'"+getPatternSql(pattern)+"') AS CHAR)";
			}else{
				sqlStr = "CAST(date_format("+column+",'"+getPatternSql(pattern)+"') AS CHAR("+charLength+"))";
			}
		}
		else{
			sqlStr = "to_char("+column+",'"+getPatternSql(pattern)+"')";
		}	
		return sqlStr;
	}
	
	/**
	 * 目前只能写这4种pattern:yyyy-MM-dd,yyyy-MM-dd HH:mm:ss,yyyyMMdd,yyyyMM
	 * @param pattern
	 * @return
	 */
	private static String getPatternSql(String pattern){
		if(MYSQL.equals(db)){
			return pattern.replaceAll("yyyy","%Y").replaceAll("MM","%m").replaceAll("dd","%d").replaceAll("HH:mm:ss","%T");
		}else if(SQLSERVER.equals(db)){
			if("yyyy-MM-dd".equals(pattern)){
				return "CONVERT(varchar(100),column,23)";
			}else if("yyyy-MM-dd HH:mm:ss".equals(pattern)){
				return "CONVERT(varchar(100),column,120)";
			}else if("yyyyMMdd".equals(pattern)){
				return "CONVERT(varchar(100),column,112)";
			}else if("yyyyMM".equals(pattern)){
				return "CONVERT(varchar(6),column,112)";
			}
		}else if(ORACLE.equals(db)){
			return pattern.replaceAll("mm","mi").replaceAll("MM","mm").replaceAll("HH","hh24");
		}
		return null;
	}
	
	/**
	 * 普通列转为字符串
	 * @param column
	 * @param charLength
	 * @return
	 */
	public static String toChar(String column,Integer charLength){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			if(charLength == null){
				sqlStr = "CONVERT(CHAR," + column + ")";
			}else{
				sqlStr = "CONVERT(CHAR(" + charLength + ")," + column + ")";		
			}
		}
		else if(MYSQL.equals(db)){
			if(charLength == null){
				sqlStr = "CAST("+column+" AS CHAR)";
			}else{
				sqlStr = "CAST("+column+" AS CHAR("+charLength+"))";
			}
		}
		else{
			sqlStr = "to_char("+column+")";
		}
		
		return sqlStr;
	}
	
	/**
	 * null值处理
	 * @param column
	 * @param nullValue
	 * @return
	 */
	public static String isNull(String column,String nullValue){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = "ISNULL("+column+","+nullValue+")";
		}
		else if(MYSQL.equals(db)){
			sqlStr = "IFNULL("+column+","+nullValue+")";
		}
		else{
			sqlStr = "NVL("+column+","+nullValue+")";
		}
		
		return sqlStr;
	}
	
	/**
	 * null值处理
	 * @param column
	 * @param nullValue
	 * @return
	 */
	public static String isNull(){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = "ISNULL ";
		}
		else if(MYSQL.equals(db)){
			sqlStr = "IFNULL ";
		}
		else{
			sqlStr = "NVL ";
		}
		
		return sqlStr;
	}
	
	/**
	 * 字符拼接
	 * @param strArr：需要拼接的列
	 * @return
	 */
	public static String concat(String[] strArr){
		if(SQLSERVER.equals(db)){
			return concatChar(strArr,"+");
		}
		else if(MYSQL.equals(db)){
			return "CONCAT_WS('',"+concatChar(strArr,",")+")";
		}
		return concatChar(strArr,"||");
	}
	
	/**
	 * 字符拼接
	 * @param strArr：需要拼接的列
	 * @return
	 */
	public static String concat(String strArr){
		if(SQLSERVER.equals(db)){
			return strArr+"+";
		}
		else if(MYSQL.equals(db)){
			return "CONCAT_WS('',"+strArr+","+")";
		}
		return strArr+"||";
	}
	
	/**
	 * 字符拼接
	 * @param target
	 * @param charStr
	 * @return
	 */
	private static String concatChar(String[] target,String charStr){
		String newStr = "";
		for(int i=0;i<target.length;i++){
			if(i==target.length-1){
				newStr+=target[i];
				break;
			}
			newStr+=target[i]+charStr;
		}
		return newStr;
	}
	
	/**
	 * 当前日期
	 * @return
	 */
	public static String getSysDate(){
		if(SQLSERVER.equals(db)){
			return "getdate()";
		}
		else if(MYSQL.equals(db)){
			return "SYSDATE()";
		}
		return "sysdate";
	}
	/**
	 * 实现ADD_MONTHS函数
	 * @author zhangll
	 * @CreateTime 2014-5-14 下午2:10:44
	 * @param column
	 * @param addMonths
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getAddMonthsFunc(String column, int addMonths){
		String sqlStr = "";
		if(SQLSERVER.equals(db)){
			sqlStr = "DATEADD(month," + addMonths + "," + column + ")";
		}else if(MYSQL.equals(db)){
			sqlStr = "DATE_ADD("+ column + ",INTERVAL " + addMonths + " MONTH)";
		}else if(ORACLE.equals(db)){
			sqlStr = "ADD_MONTHS(" + column + "," + addMonths + ")";
		}
		return sqlStr;
	}
	
	public static String loginMenuOrder(){
		if(SQLSERVER.equals(db)){
			return "order by floor(menu.menu_no),right('0'+replace(substring(menu.menu_no,charindex('.',menu.menu_no),8000),'.',''),2)";
		}
		else if(MYSQL.equals(db)){
			return "order by floor(menu.menu_no),LPAD(replace(SUBSTR(menu.menu_no,INSTR(menu.menu_no,'.'),8000),'.',''),2,'0')";
		}
		return "order by to_char(floor(menu.menu_no), '00')||'.'||to_char(ltrim(ltrim(menu.menu_no,'0123456789'), '.'), '00')";
		
	}
	/**
	 * 递归SQL，内部类
	 * 单例
	 * @author zhangll
	 * @CreateTime 2014-5-15 上午10:50:10
	 */
	public static class RecursiveSql{
		private RecursiveSql(){};
		private static RecursiveSql instance;
		public static RecursiveSql getInstance(){
			if(instance == null) instance = new RecursiveSql();
			return instance;
		}
		/**
		 * 
		 * @author zhangll
		 * @CreateTime 2014-5-15 上午11:05:18
		 * @param tableName
		 * @param pColumn
		 * @param cColumn
		 * @param mode
		 * @return
		 * 版本说明
		 *    版本号	修改者	修改描述
		 */
		public String getRecursiveSqlStr(String tableName, String pColumn, String cColumn, boolean mode){
			return null;
		}
	}
	
	/**
	 * 
	 * @author jyy
	 * 返回表名
	 * @CreateTime 2014-06-15 上午11:05:18
	 * @param tableName
	 * @param pColumn
	 * @param cColumn
	 * @param mode
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getTableName(){
		String sqlStr = "";
		if(ORACLE.equals(db)){
			sqlStr = " FROM DUAL";
		}
		return sqlStr;
		
	}
	/**
	 * 
	 * @author jyy
	 * 返回表名
	 * @CreateTime 2014-06-15 上午11:05:18
	 * @param tableName
	 * @param pColumn
	 * @param cColumn
	 * @param mode
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String returnTableName(String tableName){
		String sqlStr = "";
		if(ORACLE.equals(db)){
			sqlStr = tableName;
		}
		return sqlStr;
		
	}
	/***
	 * 
	 * @author jiayy
	 * 类型匹配，
	 */

	public static String characterType()
	{
		String sqlStr = "";
		if(ORACLE.equals(db)){
			sqlStr = "TO_CHAR";
		}
		return sqlStr;
	}
	
	/***
	 * 
	 * @author jiayy
	 * sql自增
	 * ORACLE 查找序列
	 */

	public static String returnId(String id)
	{
		String sqlStr = "";
		if(ORACLE.equals(db)){
			sqlStr = id;
		}
		return sqlStr;
	}
}
