package com.tcj.work.accountsManage.biz;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tcj.common.ResultBean;
import com.tcj.domains.User;

public interface UserLoginBiz {
	/**
	 * 根据用户名和密码验证用户信息是否正确
	 * @param map
	 * @param session
	 * @return
	 */
	public ResultBean checkUser(Map map,HttpSession session);
	
	/**
	 * 退出登录
	 * @param user
	 */
	public void loginOut(User user);
}
