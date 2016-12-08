package com.tcj.work.userManage.biz.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.MD5utils;
import com.tcj.domains.Activity;
import com.tcj.domains.Club;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.messageManage.service.MessageManageService;
import com.tcj.work.userManage.biz.UserManageBiz;
import com.tcj.work.userManage.service.UserManageService;

@Component("userManageBizImpl")
public class UserManageBizImpl implements UserManageBiz{

	private Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("userManageServiceImpl")
	private  UserManageService  userManageService;
	
	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	
	@Autowired()
	@Qualifier("messageManageServiceImpl")
	private MessageManageService messageManageService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) throws EgladServiceException {
		// TODO Auto-generated method stub
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String userId = loginEntity.getUserId();
        User userinfo=messageManageService.getUserInfoById(userId);//根据session userId查询用户信息
        
        if(null!=userinfo.getProviderId()){
        Integer  providerId=userinfo.getProviderId();//所属会员单位
        param.put("providerId", providerId.toString());
        }
         Integer  userType=userinfo.getUsertype();//所属角色
         param.put("userType", userType.toString());
		return userManageService.getList(param);
	}

	@Override
	public ResultBean deletes(String ids) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			News news = new News();
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			//	param.put("isDelete");
			userManageService.deletes(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public List<User> Exportexcel(Map params) {
		// TODO Auto-generated method stub
		return  userManageService.Exportexcel(params);
	}

	@Override
	public ResultBean saveOrUpdate(Map<String, String> map) throws EgladServiceException {
		// TODO Auto-generated method stub
		
		//获取session用户信息
				WebContext contxt = WebContextFactory.get();
				HttpSession session=contxt.getSession();
				LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
				String userId = loginEntity.getUserId();//用户id
//				System.out.println("快来啊"+userId);
		        User userinfo=messageManageService.getUserInfoById(userId);//根据session userId查询用户信息
		        
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		User user=new User();
		//修改所用Id
		if(!"".equals(MapUtils.getString(map, "id",""))){
			System.out.println("id============="+MapUtils.getString(map, "id",""));
			user.setId(Integer.valueOf(MapUtils.getString(map, "id","")));
		    user.setPasswd(MapUtils.getString(map, "password", ""));      
		}else{
		    user.setPasswd(MD5utils.MD5Encode(MapUtils.getString(map, "password", ""))); //MD5加密   
		}
		//快车网管理员、会员单位管理员根据session定义providerId
		if(null!=userinfo.getProviderId()){
	        Integer  providerId=userinfo.getProviderId();
	        user.setProviderId(providerId);
	        }
		//页面传值，超级管理员自定义providerId
		if(!"".equals(MapUtils.getString(map, "providerId", ""))){
			  user.setProviderId(Integer.valueOf(MapUtils.getString(map, "providerId", "")));
		}
		if(!"".equals(MapUtils.getString(map, "usertype", ""))){
			 user.setUsertype(Integer.valueOf(MapUtils.getString(map, "usertype", "")));
		}
        user.setUsername(MapUtils.getString(map, "username", ""));
        user.setRealname(MapUtils.getString(map, "realname", ""));
        user.setGender(MapUtils.getString(map, "gender", ""));
        System.out.println("gender========"+MapUtils.getString(map, "gender", ""));
        user.setAddress(MapUtils.getString(map, "address", ""));
        user.setTel(MapUtils.getString(map, "tel", ""));
        user.setEmail(MapUtils.getString(map, "email", ""));
    
		try {
			
			this.userManageService.saveOrUpdate(user);			
			resultBean.setSuccess(true);
		
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
}

	/*
	 * 根据id查询
	 * (non-Javadoc)
	 * @see com.tcj.work.userManage.biz.UserManageBiz#getById(java.lang.String)
	 */
	@Override
	public ResultBean getById(String id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.userManageService.getById(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	}
