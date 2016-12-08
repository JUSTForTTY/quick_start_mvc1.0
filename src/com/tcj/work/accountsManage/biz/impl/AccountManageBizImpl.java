package com.tcj.work.accountsManage.biz.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tcj.common.RandomCode;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.MD5utils;
import com.tcj.domains.*;
import com.tcj.work.accountsManage.biz.AccountManageBiz;
import com.tcj.work.accountsManage.service.AccountManageService;
import com.tcj.work.authorize.service.IAuthorizeService;

@Component("accountManageBizImpl")
public class AccountManageBizImpl implements AccountManageBiz {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("accountManageServiceImpl")
	private AccountManageService accountManageService;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			return accountManageService.getList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultBean delete(String flag,String uids) {
		ResultBean resultBean = new ResultBean();
		try {
			DemoManage demoManage = new DemoManage();
			Map<String, String> param = new HashMap<String, String>();
//			LoginEntity entity = authorizeService.getLoginUser();
//			String loginId = entity.getUserId().toString();
			param.put("uids", uids);
			param.put("loginId", "12");
			accountManageService.delete(flag,param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	public ResultBean getByUid(String uid) {
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setSuccess(true);
			resultBean.setData(this.accountManageService.getByUid(uid));
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean update(Map<String, String> map,HttpSession session) {
		ResultBean resultBean = new ResultBean();
		LoginEntity entity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		try {
			User user = new User();
			user.setUid(MapUtils.getString(map, "uid"));
			user.setRealname(MapUtils.getString(map, "realname", ""));
			user.setModifyTime(new Timestamp(new Date().getTime()));
			user.setModifyUser(entity.getUserId());
			user.setUsertype(Integer.parseInt(MapUtils.getString(map, "userType", "0")));
			user.setMobile((MapUtils.getString(map, "mobile", "0")));
			user.setEmail((MapUtils.getString(map, "email", "0")));
			accountManageService.update(user);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg("保存信息失败");
		}
		return resultBean;
	}

	@Override
	public ResultBean save(Map<String, String> map) {
		ResultBean resultBean = new ResultBean();
		try {
			User user = new User();
			user.setUsername(MapUtils.getString(map, "username", ""));
			user.setPasswd(MD5utils.MD5Encode(MapUtils.getString(map, "password", "")));
			user.setUsertype(Integer.parseInt(MapUtils.getString(map, "usertype", "0")));
			user.setMobile(MapUtils.getString(map, "mobile", ""));
			user.setEmail(MapUtils.getString(map, "email", ""));
			user.setBid(10);
			user.setCreateTime(new Timestamp(new Date().getTime()));
			user.setTakingtime(new Timestamp(new Date().getTime()));
			user.setStatus(CommonDefine.ID_DELETE_FALSE);
			user.setCredentialtype("0");
			user.setNickname(MapUtils.getString(map, "username", ""));
			user.setPrepay(0.00);
			user.setRegtime(new Timestamp(new Date().getTime()));
			user.setInviteCode(RandomCode.toSerialCode(MapUtils.getString(map, "mobile", "")));
			resultBean=this.accountManageService.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public Object getDetail(String uid) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(accountManageService.getDetail(uid));
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
	public ResultBean updateDetail(String str,String passwd,String uid) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(accountManageService.updateDetail(str,passwd,uid));
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
	public ResultBean resetPwd(String uid) {
		ResultBean resultBean = new ResultBean();
		User user = new User();
		user.setPasswd(MD5utils.MD5Encode("123456"));
		user.setModifyTime(new Timestamp(new Date().getTime()));
		accountManageService.resetPwd(uid,user);
		resultBean.setSuccess(true);
		return resultBean;
	}
	
}
