package com.tcj.work.activityManage.biz.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.EnumActivityStatus;
import com.tcj.domains.Activity;
import com.tcj.domains.ActivityProject;
import com.tcj.domains.ActivityProjectAdditional;
import com.tcj.domains.City;
import com.tcj.domains.Pronivce;
import com.tcj.work.activityManage.biz.ActivityManageBiz;
import com.tcj.work.activityManage.service.ActivityManageService;


@Component("activityManageBizImpl")
public class ActivityManageBizImpl implements ActivityManageBiz {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("activityManageServiceImpl")
	private ActivityManageService activityManageService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 查询活动
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			
			 Page  page=activityManageService.getList(param);	 
				
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
	 * 查询(条件查询)
	 * 查询活动项目
	 */
	@RemoteMethod
	public Page getProjectList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}			 					
			return activityManageService.getProjectList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 
	 * 查询(条件查询)
	 * 查询附加项目
	 */
	@RemoteMethod
	public Page getAdditionList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}			 					
			return activityManageService.getAdditionList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 
	 * 活动删除
	 * 
	 */
	@RemoteMethod
	public ResultBean delete(String ids,String isDelete) {
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("isDelete", isDelete);
			activityManageService.delete(param);
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
	 * 活动项目删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletesProject(String ids,String isDelete) {
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("isDelete", isDelete);
			activityManageService.deletesProject(param);
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
	 * 活动项目删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletesAddition(String ids,String isDelete) {
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("isDelete", isDelete);
			activityManageService.deletesAddition(param);
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
	 * 根据id查询活动
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.activityManageService.getById(id));
			Activity activity=new Activity();
			activity =this.activityManageService.getById(id);
//			System.out.println("测试时间====="+activity.getCreateTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");			  
			   String registerStart =formatter.format(activity.getRegisterStart());
			   String registerEnd =formatter.format(activity.getRegisterEnd());
			   String startTime =formatter.format(activity.getStartTime());
			   String endTime =formatter.format(activity.getEndTime());
			   resultBean.setRegisterStart(registerStart);
			   resultBean.setRegisterEnd(registerEnd);
			   resultBean.setStartTime(startTime);
			   resultBean.setEndTime(endTime);
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
	 * 根据id查询活动项目
	 * 
	 */
	@RemoteMethod
	public ResultBean getByIdProject(String aid,String apid) {
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.activityManageService.getByIdProject(aid,apid));
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
	 * 根据id查询附加项目
	 * 
	 */
	@RemoteMethod
	public ResultBean getByIdAddition(String aid,String apaid) {
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.activityManageService.getByIdAddition(aid,apaid));
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
	 * 活动新增、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String,String> map) {
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		Activity activity=new Activity();
		activity.setCid(MapUtils.getString(map, "cid",""));
		activity.setAid(MapUtils.getString(map, "aid", ""));
		activity.setName(MapUtils.getString(map, "name", "")); 
		activity.setSubtitle(MapUtils.getString(map, "subtitle", "")); 
		activity.setDay(Integer.valueOf(MapUtils.getString(map, "day", ""))); 
		activity.setMobileContent(MapUtils.getString(map, "mobileContent", ""));
		activity.setAge(MapUtils.getString(map, "age", ""));
//		activity.setOfficialUrl(MapUtils.getString(map, "officialUrl", "")); //官方网址
		activity.setBriefintro(MapUtils.getString(map, "briefintro", "")); //简介
		activity.setAddress(MapUtils.getString(map, "address", "")); 
//		activity.setType(MapUtils.getString(map, "type", "")); //活动类型
		activity.setStatus(MapUtils.getString(map, "status", "")); 
//		activity.setSite(MapUtils.getString(map, "site", "")); //活动举办地
		activity.setRegisterStart(Date.valueOf(MapUtils.getString(map, "registerStart", ""))); 
		activity.setRegisterEnd(Date.valueOf(MapUtils.getString(map, "registerEnd", ""))); 
		activity.setStartTime(Date.valueOf(MapUtils.getString(map, "startTime", ""))); 
		activity.setEndTime(Date.valueOf(MapUtils.getString(map, "endTime", ""))); 
		activity.setStick(Integer.valueOf(MapUtils.getString(map, "stick", ""))); 
		activity.setActDetailUrl(MapUtils.getString(map, "actDetailUrl", "")); 
		activity.setActDetail(MapUtils.getString(map, "actDetail", "")); 
		activity.setLogo(MapUtils.getString(map, "image", "")); 
		activity.setCityId(MapUtils.getString(map, "cityId", ""));
		activity.setProvinceId(MapUtils.getString(map, "provinceId", ""));
   System.out.println("cid==========="+MapUtils.getString(map, "cid", ""));

		try {
			
			this.activityManageService.saveOrUpdate(activity);			
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
	 * 活动项目新增、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdateProject(Map<String,String> map) {
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		ActivityProject actProject=new ActivityProject();
		
		actProject.setApid(MapUtils.getString(map, "apid", ""));
		actProject.setAid(MapUtils.getString(map, "aid", ""));
		actProject.setName(MapUtils.getString(map, "name", ""));
		actProject.setPrice(MapUtils.getString(map, "price",""));
		actProject.setRefprice(MapUtils.getString(map, "refprice",""));
		actProject.setContent(MapUtils.getString(map, "content", ""));
//		actProject.setProjectNum(Integer.valueOf(MapUtils.getString(map, "projectNum", "")));
//		actProject.setStartTime(Date.valueOf(MapUtils.getString(map, "startTime", "")));
//		actProject.setEndTime(Date.valueOf(MapUtils.getString(map, "endTime", "")));
		System.out.println("gggggggggggggggggggggggggg====="+MapUtils.getString(map, "aid", ""));
		System.out.println("gggggggggggggggggggggggggg====="+MapUtils.getString(map, "apid", ""));
		try {
			
			this.activityManageService.saveOrUpdateProject(actProject);			
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
	 * 附加项目新增、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdateAddition(Map<String,String> map) {
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		ActivityProjectAdditional actAddition=new ActivityProjectAdditional();
		
		actAddition.setApaid(MapUtils.getString(map, "apaid", ""));
		actAddition.setAid(MapUtils.getString(map, "aid", ""));
		actAddition.setName(MapUtils.getString(map, "name", ""));
		actAddition.setPrice(MapUtils.getString(map, "price",""));
		actAddition.setContent(MapUtils.getString(map, "content", ""));
		actAddition.setStartTime(Date.valueOf(MapUtils.getString(map, "startTime", "")));
		actAddition.setEndTime(Date.valueOf(MapUtils.getString(map, "endTime", "")));
		System.out.println("gggggggggggggggggggggggggg====="+MapUtils.getString(map, "aid", ""));
		System.out.println("gggggggggggggggggggggggggg====="+MapUtils.getString(map, "apaid", ""));
		try {
			
			this.activityManageService.saveOrUpdateAddition(actAddition);			
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
		 
		return this.activityManageService.getProvince(map); 
	}
	/*
	 * 
	 * 
	 * 查询省级
	 * @see com.tcj.work.activityManage.biz.ActivityManageBiz#getProvince(java.util.Map)
	 */
	@RemoteMethod
	public List<City> getCity(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		return this.activityManageService.getCity(map); 
	}

}
