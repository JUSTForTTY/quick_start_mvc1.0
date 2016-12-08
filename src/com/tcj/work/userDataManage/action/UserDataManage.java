package com.tcj.work.userDataManage.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.work.userDataManage.biz.UserDataManageBiz;
import com.tcj.work.userManage.biz.UserManageBiz;

/**
 * 
 * @author Lion
 * @date 2016-7-20 下午1:20:15
 * @version 1.0
 * @history
 */

@Controller("userDataManage")
@RemoteProxy(name="userDataManage")
public class UserDataManage {
   
	
private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("userDataManageBizImpl")
	private  UserDataManageBiz   userDataManageBiz;	
	/*
	 * 
	 * 查询(条件查询)
	 *  即将使用sql实现表连接
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		return userDataManageBiz.getList(param);
	}
	@RemoteMethod
	public Object getDetail(String uid) {
		return userDataManageBiz.getDetail(uid);
	}
	@RemoteMethod
	public ResultBean deletes(String uids,String is_delete) throws Exception{
		System.out.println("ids:"+uids);
		return  userDataManageBiz.deletes(uids,is_delete);
	}
}
