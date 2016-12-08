package com.tcj.common.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.StringUtils;

import com.tcj.domains.LoginEntity;
/**
 * 在线用户Session监听器
 * @author zhangll
 * @CreateTime 2014-12-18 下午5:05:58
 */
public class OnlineUserSessionListener implements HttpSessionListener,HttpSessionAttributeListener {
	
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		try {
			String name = event.getName();
			Object value = event.getValue();
			if ("LogInEntity".equals(name) && value != null && value instanceof LoginEntity) {
				LoginEntity entity = (LoginEntity) value;
				OnlineUserHelper.addUser(session.getId(), entity);
				//logInfo("用户" + entity.getLogInId() + "登录到系统");
			}
		} catch (Exception e) {
		}
		//checkOk(session);
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		try {
			String name = event.getName();
			Object value = event.getValue();
			if ("LogInEntity".equals(name) && value instanceof LoginEntity) {
				LoginEntity entity = (LoginEntity) value;
				if(entity.getSessionFlag()==null)
					OnlineUserHelper.addUser(session.getId(), null);
				//logInfo("用户" + entity.getLogInId() + "退出系统");
			}
		} catch (Exception e) {
		}
		//checkOk(session);
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		try {
			String name = event.getName();
			Object value = event.getValue();
			if ("LogInEntity".equals(name) && value != null && value instanceof LoginEntity) {
				LoginEntity entity = (LoginEntity) value;
				OnlineUserHelper.addUser(session.getId(), entity);
				//logInfo("用户" + entity.getLogInId() + "登录到系统");
			}
		} catch (Exception e) {
		}
		
		//checkOk(session);
	}

	public void sessionCreated(HttpSessionEvent event) {
		try {
			HttpSession session = event.getSession();
			OnlineUserHelper.addUser(session.getId(), null);
		} catch (Exception e) {
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		try {
			/*Object obj = session.getAttribute("LogInDemoEntity");
			if (obj != null && obj instanceof LogInDemoEntity) {
				LogInDemoEntity entity = (LogInDemoEntity) obj;
				OnlineUserHelper.removeUser(session.getId());
				//logInfo("用户" + entity.getLogInId() + "退出系统");
			}*/
			Map<String, LoginEntity> users= OnlineUserHelper.getOnlineUsers();
			users.get(session.getId()).setSessionFlag("1");
			OnlineUserHelper.removeUser(session.getId());
		} catch (Exception e) {
		}
		
		//checkOk(session);
	}
	
	private void checkOk(HttpSession session){
		ServletContext application = session.getServletContext();
		Object appAttrObj = application.getAttribute("onlineUsers");
		if (appAttrObj != null && appAttrObj instanceof HashMap) {
			HashMap<String, LoginEntity> appOnlineUsers = (HashMap<String, LoginEntity>) appAttrObj;
			logInfo(" application中在线用户数量："+appOnlineUsers.size());
			HashMap<String, LoginEntity> helperOnlineUsers = OnlineUserHelper.getOnlineUsers();
			logInfo(" 系统中在线用户数量："+appOnlineUsers.size());
			logInfo(" 比较 appOnlineUsers==helperOnlineUsers 结果为："+(appOnlineUsers==helperOnlineUsers));
			logInfo(" 比较 appOnlineUsers和helperOnlineUsers 结果为："+(appOnlineUsers.equals(helperOnlineUsers)));
		}
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
