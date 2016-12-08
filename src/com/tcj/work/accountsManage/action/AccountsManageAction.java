/*
 * Copyright 2016-xxxx the original author or authors.
 *
 */
package com.tcj.work.accountsManage.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.accountsManage.biz.AccountManageBiz;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.demoManage.biz.*;
import com.tcj.work.demoManage.service.*;

/**
 * 用户管理基础方法
 * 
 * @author xxx
 * @date 2016-7-20 下午1:20:15
 * @version 1.0
 * @history
 */
@Controller("AccountsManageAction")
@RemoteProxy(name = "AccountsManageAction")
public class AccountsManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("accountManageBizImpl")
	private AccountManageBiz accountManageBiz;
	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	/**
	 * 查询
	 * @param param
	 * @return
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		return accountManageBiz.getList(param);
	}
	/**
	 * 冻结或解冻
	 * @param flag 0 冻结 1解冻
	 * @param uids
	 * @return
	 */
	@RemoteMethod
	public ResultBean deletes(String flag,String uids) {
		return this.accountManageBiz.delete(flag,uids);
	}
	@RemoteMethod
	public ResultBean resetPwd(String uids){
		return this.accountManageBiz.resetPwd(uids);
	}
	/**
	 * 根据uid查询
	 * @param uid
	 * @return
	 */
	@RemoteMethod
	public ResultBean getByUid(String uid) {
		return this.accountManageBiz.getByUid(uid);
	}
	/**
	 * 新增
	 * @param map
	 * @return
	 */
	@RemoteMethod
	public ResultBean save(Map<String, String> map) {
		return this.accountManageBiz.save(map);
	}
	
	/**
	 * 更新
	 * @param map
	 * @return
	 */
	@RemoteMethod
	public ResultBean update(Map<String, String> map,HttpSession session) {
		return this.accountManageBiz.update(map,session);
	}
	@RemoteMethod
	public Object getDetail(String uid) {
		return  accountManageBiz.getDetail(uid);
	}
	
	@RemoteMethod
	public ResultBean updateDetail(String str,String passwd,String uid) {//传过来的有可能是状态，还有新密码
		return  accountManageBiz.updateDetail(str,passwd,uid);
	}
   
}
