package com.tcj.common.listener;

import java.util.HashMap;
import java.util.Map;

import com.tcj.domains.LoginEntity;



/**
 * 在线用户检测帮组类
 * @author zhangll
 * @CreateTime 2014-12-18 下午5:55:33
 */
public class OnlineUserHelper {
	/**
	 * 当前在线用户存放容器
	 */
	private static HashMap<String, LoginEntity> onlineUsers = new HashMap<String, LoginEntity>();
	
	/**
	 * 增加一个用户
	 * @author zhangll
	 * @CreateTime 2014-12-19 上午10:03:23
	 * @param entity
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public synchronized static void addUser(String sessionId, LoginEntity entity){
		if (sessionId != null) {
			onlineUsers.put(sessionId, entity);
		}
	}
	/**
	 * 增加一组用户
	 * @author zhangll
	 * @CreateTime 2014-12-19 上午10:09:24
	 * @param users
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public synchronized static void addUsers(Map<String, LoginEntity> users){
		if (users != null) {
			onlineUsers.putAll(users);
		}
	}
	/**
	 * 移除一个用户
	 * @author zhangll
	 * @CreateTime 2014-12-19 上午10:04:30
	 * @param entity
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public synchronized static void removeUser(String sessionId){
		if (sessionId != null) {
			onlineUsers.remove(sessionId);
		}
	}
	/**
	 * 
	 * @author zhangll
	 * @CreateTime 2014-12-19 上午10:10:42
	 * @param users
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public synchronized static void removeUsers(Map<String, LoginEntity> users){
		if (users != null) {
			for (String sessionId : users.keySet()) {
				onlineUsers.remove(sessionId);
			}
		}
	}
	/**
	 * 获取当前所有用户
	 * @author zhangll
	 * @CreateTime 2014-12-18 下午5:58:13
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public synchronized static HashMap<String, LoginEntity> getOnlineUsers(){
		return onlineUsers;
	}
	/**
	 * 获取当前用户数量
	 * @author zhangll
	 * @CreateTime 2014-12-19 上午10:12:07
	 * @return
	 * 版本说明
	 *    版本号	修改者	修改描述
	 */
	public synchronized static int getOnlineUsersCount(){
		return onlineUsers.size();
	}
}
