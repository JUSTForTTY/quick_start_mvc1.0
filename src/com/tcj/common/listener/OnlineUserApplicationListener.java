package com.tcj.common.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcj.domains.LoginEntity;


/**
 * 在线用户项目级监听器
 * @author zhangll
 * @CreateTime 2014-12-19 上午9:36:38
 */
public class OnlineUserApplicationListener implements ServletContextListener {
	/**
	 * 日志组件
	 */
	private Log log = LogFactory.getLog(getClass());
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//logInfo(" 销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//logInfo(" 开始启动...");
		
		try {
			HashMap<String, LoginEntity> onlineUsers = OnlineUserHelper.getOnlineUsers();
			if (onlineUsers != null) {
				ServletContext context = event.getServletContext();
				context.setAttribute("onlineUsers", onlineUsers);
				//logInfo(" 当前在线用户放置于application...");
			}
		} catch (Exception e) {
		}
		
		//logInfo(" 成功启动...");
	}
	
	/**
	 * 日志
	 * @author zhangll
	 * @CreateTime 2014-12-19 上午9:50:53
	 * @param msg
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	private void logInfo(String msg){
		/*
		if (log.isInfoEnabled()) {
			log.info(getClass() + StringUtils.defaultString(msg, ""));
		}
		*/
		System.out.println(getClass() + StringUtils.defaultString(msg, ""));
	}

}
