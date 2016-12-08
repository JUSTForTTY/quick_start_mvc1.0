package com.tcj.work.accountsManage.action;

import java.util.Map;

import javax.servlet.http.HttpSession;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.tcj.common.ResultBean;
import com.tcj.work.accountsManage.biz.UserLoginBiz;

@Controller("userLoginAction")
@RemoteProxy(name="userLoginAction")
public class UserLoginAction {
	
	@Autowired
	@Qualifier("userLoginBizImpl")
	private UserLoginBiz loginBiz;
	
	@RemoteMethod
	public ResultBean checkUserInfo(Map map,HttpSession session){
		ResultBean resultBean =  loginBiz.checkUser(map, session);
		return resultBean;
	}
}
