package com.tcj.common.util;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.xfire.transport.http.XFireServletController;

/**
 * 
 * WebService工具包
 * @author zhangll
 * @CreateTime 2014-3-12 上午9:57:20
 */
public class WebServiceUtil {
	/**
	 * OA系统
	 */
	public static final String REMOTE_SYSTEM_OA = "OA";
	
	/**
	 * 得到WebService端请求
	 * @author zhangll
	 * @CreateTime 2014-3-12 上午9:59:03
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static HttpServletRequest getWebRequest(){
		return XFireServletController.getRequest();
	}
	/**
	 * 得到WebService端请求IP
	 * @author zhangll
	 * @CreateTime 2014-3-12 上午10:00:24
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getWebServiceIp(){
		return getWebServiceIp(getWebRequest());
	}
	/**
	 * 得到WebService端请求IP
	 * @author zhangll
	 * @CreateTime 2014-3-12 上午10:00:24
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public static String getWebServiceIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
	}
}
