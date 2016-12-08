package com.tcj.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.MapUtils;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


/***
 * 工具方法类
 * @author shant
 *
 */
public class CommonUtil {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param temp
	 * @return
	 */
	public static Boolean isNotEmpty(String temp) {
		return temp != null && !"".equals(temp) ;
	}
	

	/**
	 * 字符串替换
	 * 
	 * @return
	 */

	public static String strReplace(String org) {
		if(org==null){
			return "";
		}
		if (org.indexOf("!") >= 0) {
			org = org.replace("!", "!!");
		}

		if (org.indexOf("%") >= 0) {
			org = org.replace("%", "!%");
		}

		if (org.indexOf("_") >= 0) {
			org = org.replace("_", "!_");
		}
		
		if (org.indexOf("'") >= 0) {
			org = org.replace("'", "''");
		}
		
		return org;
	}
	
	
	/***
	 * 通过DWR静态方法获得HttpSession
	 * @return
	 */
	public static HttpSession getHttpSessionByDwr(){
		WebContext ctx = WebContextFactory.get(); 
		return ctx.getSession();
	}
	

	/**
	 * sql查询时用于时间的判断大小
	 */
	public static String ChangeStartDate(String test){
		return test+" 00:00:00";
	}
	public static String ChangeEndDate(String test){
		return test+" 23:59:59";
	}

	/**  
	  * 判断字符串是否是整数  
	  */  
	public static boolean isInteger(String value) {   
		try {   
			Integer.parseInt(value);   
			return true;   
		} catch (NumberFormatException e) {   
			return false;   
		}
	}
	
	public static String getIp(){
		WebContext ctx = WebContextFactory.get();
		if (ctx != null) {
			return getIp(ctx.getHttpServletRequest());
		}
		return null;
	}
	
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 获取最大的凭证号
	 * @param map
	 * @return
	 */
	public static String  getMaxVoucherNo(String  where){
			String sql="";
			if(!"oracle".equals(DilectSqlUtil.getDb())){
				sql="SELECT ISNULL(MAX(VOUCHER_NO),0) VOUCHER_NO FROM (" +
						" select VOUCHER_NO,row_number()over(order by VOUCHER_NO  ) rownum from T_LEDGER_VOUCHERS A WHERE "+where+ " "+
						" )  A WHERE VOUCHER_NO=rownum";	
			}else {
				sql="SELECT NVL(MAX(VOUCHER_NO),0) VOUCHER_NO FROM (" +
						" select VOUCHER_NO from T_LEDGER_VOUCHERS A WHERE "+where+ " ORDER BY VOUCHER_NO "+
						" ) WHERE VOUCHER_NO=ROWNUM";	
			}
					
		return sql;
	}

	/**
	 * Map 转实体
	 * @param clazz
	 * @param map
	 * @return
	 */
	 public static <T> T toBean(Class<T> clazz, Map<String,String> map) {
	        T obj = null;
	        try {
	        	Map newMap= new HashMap();
	        	for (String key :map.keySet()) {
	        		   newMap.put(key.replaceAll("_", ""), MapUtils.getString(map,key,""));
	        	 }
	        	
	            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
	            obj = clazz.newInstance(); // 创建 JavaBean 对象
	 
	            // 给 JavaBean 对象的属性赋值
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            for (int i = 0; i < propertyDescriptors.length; i++) {
	                PropertyDescriptor descriptor = propertyDescriptors[i];
	                String propertyName = descriptor.getName().toUpperCase();
	                if (newMap.containsKey(propertyName.toUpperCase())) {
	                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
	                    try {
	                    	String type=descriptor.getReadMethod().toString();
	                    	if(type.contains("Integer")){
	                    		  descriptor.getWriteMethod().invoke(obj,MapUtils.getInteger(newMap, propertyName));
	                    	}else if (type.contains("String")) {
	                    		  descriptor.getWriteMethod().invoke(obj,MapUtils.getString(newMap, propertyName));
							}else if (type.contains("Double")) {
	                    		  descriptor.getWriteMethod().invoke(obj,MapUtils.getDouble(newMap, propertyName));
							}else if (type.contains("Date")) {
								String date=MapUtils.getString(newMap, propertyName,"");
								if(!date.equals("")){
								  descriptor.getWriteMethod().invoke(obj,df.parse(date));
								}
								
							}
	                      
	                    } catch (InvocationTargetException e) {
	                        System.out.println("字段映射失败");
	                    }
	                }
	            }
	        } catch (IllegalAccessException e) {
	            System.out.println("实例化 JavaBean 失败");
	        } catch (IntrospectionException e) {
	            System.out.println("分析类属性失败");
	        } catch (IllegalArgumentException e) {
	            System.out.println("映射错误");
	        } catch (InstantiationException e) {
	            System.out.println("实例化 JavaBean 失败");
	        }catch (Exception e) {
	        	e.printStackTrace();
			}
	        return (T) obj;
	    }
	 /**
	  * 科目分层
	  * @param i
	  * @returns {String}
	  */
	 public static String getBlank(int i){
	 	String str="";
	 	while(i-- > 0){
	 		str+="    ";
	 	}
	 	return str; 
	 }	
	 
	/***
	 * 按指定分隔符将字符数组(集合)拼接成字符串
	 * @param separator
	 * @return
	 */
	public static String stringListToSingleString(List<String> strings, String separator){
		if(strings==null || strings.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		//修正数组长度为0的bug
		if(strings.size() > 0){
			for (int i = 0; i < strings.size()-1; i++) {
				sb.append(strings.get(i));
				sb.append(separator);
			}
			sb.append(strings.get(strings.size()-1));
		}
		return sb.toString();
	}
	
	/***
	 * 按指定分隔符将字符数组(集合)拼接成字符串
	 * @param separator
	 * @return
	 */
	public static String stringListToSingleString(String[] strings, String separator){
		if(strings==null || strings.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length-1; i++) {
			sb.append(strings[i]);
			sb.append(separator);
		}
		sb.append(strings[strings.length-1]);
		
		return sb.toString();
	}
	
	/***
	 * 按指定分隔符将字符数组(集合)拼接成字符串
	 * @param separator
	 * @return
	 */
	public static String stringListToSingleString4Sql(String[] strings){
		if(strings==null || strings.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length-1; i++) {
			sb.append("'");
			sb.append(strings[i]);
			sb.append("',");
		}
		sb.append("'");
		sb.append(strings[strings.length-1]);
		sb.append("'");
		
		return sb.toString();
	}
	
	/***
	 * 按指定分隔符将字符数组(集合)拼接成字符串
	 * @param separator
	 * @return
	 */
	public static String stringListToSingleString4Sql(List<String> strings){
		if(strings==null || strings.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.size()-1; i++) {
			sb.append("'");
			sb.append(strings.get(i));
			sb.append("',");
		}
		sb.append("'");
		sb.append(strings.get(strings.size()-1));
		sb.append("'");
		
		return sb.toString();
	} 
	
	/**
	 * 将指定double值转换为保留指定小数位的double值,四舍五入
	 * @param d
	 * @param decimal 小数点后的位数
	 * @return
	 */
	public static double changeDecimal(double d, int decimal){
		BigDecimal bg = new BigDecimal(Double.toString(d));
        return bg.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/***
	 * 四舍五入保留两位小数
	 * @param d
	 * @return
	 */
	public static double changeDecimal(double d){
		return changeDecimal(d, 2);
	}
	
	/**
	 * 将指定double值转换为保留指定小数位的double值,四舍五入
	 * @param d
	 * @param decimal 小数点后的位数
	 * @return
	 */
	public static float changeDecimal(float d, int decimal){
		BigDecimal bg = new BigDecimal(Float.toString(d));
        return bg.setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	/***
	 * 四舍五入保留两位小数
	 * @param d
	 * @return
	 */
	public static float changeDecimal(float d){
		return changeDecimal(d, 2);
	}
	
	/**
	 * 两个double值做减法，防止精度问题
	 * @author shant
	 * @CreateTime 2013-10-18 下午1:22:03
	 * @param d1 被减数
	 * @param d2 减数
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static double doubleSub(double d1,double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.subtract(bd2).doubleValue(); 
    } 
}
