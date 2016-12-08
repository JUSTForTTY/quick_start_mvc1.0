package com.tcj.work.userDataManage.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.userDataManage.biz.UserDataManageBiz;
import com.tcj.work.userDataManage.service.UserDataManageService;
import com.tcj.work.userManage.service.UserManageService;


@Component("userDataManageBizImpl")
public class UserDataManageBizImpl  implements UserDataManageBiz {

	
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("userDataManageServiceImpl")
	private  UserDataManageService  userDataManageService;
	
	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@Override
	public Page getList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return userDataManageService.getList(param);
	}

	@Override
	public Object getDetail(String uid) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(userDataManageService.getDetail(uid));
			System.out.println("data:::"+resultBean.getData());
			resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean deletes(String uids,String  is_delete) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
 try{
	 userDataManageService.deletes(uids,is_delete);
	 resultBean.setSuccess(true);
		
	}catch(Exception e){
		resultBean.setSuccess(false);
		resultBean.setMsg(e.getMessage());		
	}
	return resultBean;
   }
}
