package com.tcj.work.clubManage.biz.impl;

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
import com.tcj.domains.Activity;
import com.tcj.domains.City;
import com.tcj.domains.Club;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.Pronivce;
import com.tcj.domains.User;
import com.tcj.work.clubManage.biz.ClubManageBiz;
import com.tcj.work.clubManage.service.ClubManageService;
import com.tcj.work.messageManage.service.MessageManageService;


@Component("clubManageBizImpl")
public class ClubManageBizImpl implements ClubManageBiz {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("clubManageServiceImpl")
	private ClubManageService clubManageService;
	
	@Autowired()
	@Qualifier("messageManageServiceImpl")
	private MessageManageService messageManageService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 查询社团
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			
			 Page  page=clubManageService.getList(param);	 
				
			 //将状态status转为枚举类型
//			 List<Activity> lst=page.getRows();
//			 Map map=EnumActivityStatus.toMap();
//
//			 for(int i=0;i<lst.size();i++){
//				 lst.get(i).setStatus(map.get(lst.get(i).getStatus()).toString());		  	  
//			 }
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 
	 * 社团删除
	 * 
	 */
	@RemoteMethod
	public ResultBean delete(String ids,String isDelete) {
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("isDelete", isDelete);
			clubManageService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	/*
	 * 
	 * 社团审核
	 * 
	 */
	@RemoteMethod
	public ResultBean clubPending(String ids,String status) {
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("status", status);
			clubManageService.clubPending(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	/*
	 * 
	 * 根据id查询社团
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.clubManageService.getById(id));
//			Club club=new Club();
//			club =(Club) this.clubManageService.getById(id);
//            //eazyui时间单独处理.
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
//			if(null!=club.getFeeStartDate()){				
//			   String startTime =formatter.format(club.getFeeStartDate());
//			   resultBean.setStartTime(startTime);
//			}
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	/*
	 * 
	 * 社团新增、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String,String> map) throws EgladServiceException {
		
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String userId = loginEntity.getUserId();//用户id
//		System.out.println("快来啊"+userId);
        User userinfo=messageManageService.getUserInfoById(userId);//根据session userId查询用户信息
        
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		Club club=new Club();
		
		//快车网管理员、会员单位管理员根据session定义providerId
		if(null!=userinfo.getProviderId()){
	        Integer  providerId=userinfo.getProviderId();
	        club.setProviderId(providerId);
	        }
		//页面传值，超级管理员自定义providerId
		if(!"".equals(MapUtils.getString(map, "provider_id", ""))){
			club.setProviderId(Integer.valueOf(MapUtils.getString(map, "provider_id", "")));
		}
		club.setCid(MapUtils.getString(map, "cid", ""));
//		club.setBid(Integer.valueOf(MapUtils.getString(map, "bid","")));
		club.setName(MapUtils.getString(map, "name", "")); 
		club.setBrief(MapUtils.getString(map, "brief",""));
		if(!"".equals(MapUtils.getString(map, "clubMemberSum",""))){
		club.setClubMemberSum(Integer.valueOf(MapUtils.getString(map, "clubMemberSum","")));
		}
		club.setFeeCircle(MapUtils.getString(map, "feeCircle",""));
		if(!"".equals(MapUtils.getString(map, "feeStartDate",""))){
		club.setFeeStartDate(Date.valueOf(MapUtils.getString(map, "feeStartDate","")));
		}
		club.setMobile(MapUtils.getString(map, "mobile",""));
		club.setType(MapUtils.getString(map, "type", "")); 
		club.setAdministrator(MapUtils.getString(map,"administrator",""));
		club.setSlogan(MapUtils.getString(map, "slogan",""));
		club.setOperator(MapUtils.getString(map, "operator",""));
		club.setStatus(MapUtils.getString(map, "status", "")); 
		club.setLogo(MapUtils.getString(map, "image", "")); 
		club.setCity(MapUtils.getString(map, "city", ""));
   System.out.println("ssssssssssssss哈哈cityId="+MapUtils.getString(map, "cityId", ""));
		try {
			
			this.clubManageService.saveOrUpdate(club);			
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
	 * 
	 * 
	 * 查询省级
	 * @see com.tcj.work.activityManage.biz.ActivityManageBiz#getProvince(java.util.Map)
	 */
	@RemoteMethod
	public List<Pronivce> getProvince(Map<String, String> map) {
		// TODO Auto-generated method stub
		 
		return this.clubManageService.getProvince(map); 
	}
	/*
	 * 
	 * 
	 * 查询市级
	 * @see com.tcj.work.activityManage.biz.ActivityManageBiz#getProvince(java.util.Map)
	 */
	@RemoteMethod
	public List<City> getCity(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		return this.clubManageService.getCity(map); 
	}

}
