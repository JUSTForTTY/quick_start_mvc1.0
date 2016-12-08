package com.tcj.work.accountsManage.biz.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.util.MD5utils;
import com.tcj.common.ResultBean;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.User;
import com.tcj.work.accountsManage.biz.UserLoginBiz;
import com.tcj.work.accountsManage.service.UserloginService;
@Component("userLoginBizImpl")
public class UserLoginBizImpl implements UserLoginBiz {	
	@Autowired
	@Qualifier("userLoginServiceImpl")
	private UserloginService loginService; 
	@Override
	public ResultBean checkUser(Map map,HttpSession session) {
		ResultBean resultBean = new ResultBean();
		String username = MapUtils.getString(map,"loginId");
		String password = MapUtils.getString(map, "loginPwd");
		String sRnd=(String)session.getAttribute("sRand");
		if(!sRnd.equals(MapUtils.getString(map,"imageCode"))){
			resultBean.setSuccess(false);
			resultBean.setMsg("验证码错误");
			return resultBean;
		}	
		User user = loginService.checkUser(username,MD5utils.MD5Encode(password));
		if(user!=null){
			resultBean.setSuccess(true);
			resultBean.setData(user.getUsertype());
			LoginEntity loginEntity = new LoginEntity();
			loginEntity.setUserId(user.getId().toString());
			loginEntity.setUserName(user.getUsername());
			loginEntity.setUserTypeFlag(String.valueOf(user.getUsertype()));
			session.setAttribute("LogInDemoEntity",loginEntity);
		}else{
			resultBean.setSuccess(false);
			resultBean.setMsg("用户名或密码错误");
		}
		return resultBean;
	}
	@Override
	public void loginOut(User user) {		

	}
}
