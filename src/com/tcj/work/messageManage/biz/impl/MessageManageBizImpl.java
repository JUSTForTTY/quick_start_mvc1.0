package com.tcj.work.messageManage.biz.impl;

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
import com.tcj.common.enums.EnumActivityStatus;
import com.tcj.domains.Activity;
import com.tcj.domains.ActivityProject;
import com.tcj.domains.ActivityProjectAdditional;
import com.tcj.domains.City;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.Pronivce;
import com.tcj.domains.User;
import com.tcj.work.activityManage.biz.ActivityManageBiz;
import com.tcj.work.activityManage.service.ActivityManageService;
import com.tcj.work.messageManage.biz.MessageManageBiz;
import com.tcj.work.messageManage.service.MessageManageService;


@Component("messageManageBizImpl")
public class MessageManageBizImpl implements MessageManageBiz {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("messageManageServiceImpl")
	private MessageManageService messageManageService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 查询商品评论
	 */
	@RemoteMethod
	public Page getListComment(Map<String, String> param) throws EgladServiceException {
		
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
		
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}						
				//根据用户角色userType操作数据
				//'0'超级管理员;'1'快车网管理员;'2'会员单位管理员
				if(userType==0){
					Page  page=messageManageService.getListComment0(param);	 
					return page;
				}else if(userType==1){
					Page  page=messageManageService.getListComment1(param);
					return page;
				}else if(userType==2){
					Page  page=messageManageService.getListComment2(param);
					return page;
				}
				
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 
	 * 查询(条件查询)
	 * 查询会员单位留言
	 */
	@RemoteMethod
	public Page getListMessage(Map<String, String> param) throws EgladServiceException {
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
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}		
			
			//根据用户角色userType操作数据
			//'0'超级管理员;'1'快车网管理员;'2'会员单位管理员
			if(userType==0){
				Page  page=messageManageService.getListMessage0(param);	
				return page;
			}else if(userType==1){
				Page  page=messageManageService.getListMessage1(param);
				return page;
			}else if(userType==2){
				Page  page=messageManageService.getListMessage2(param);
				return page;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}