
package com.tcj.work.activityManage.action;

import com.tcj.work.activityManage.action.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.EnumActivityStatus;
import com.tcj.common.enums.EnumActivityStick;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.activityManage.biz.ActivityManageBiz;
import com.tcj.work.demoManage.biz.*;
import com.tcj.work.demoManage.service.*;

/**
 * @dscription 活动数据维护 
 * @author frb
 * @date 2016/07/30 下午2.00
 * @version 1.0
 * @history
 */
@Controller("activityManageAction")
@RemoteProxy(name = "activityManageAction")//父类路径
public class ActivityManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("activityManageBizImpl")
	private ActivityManageBiz activityManageBiz;

	/*
	 * 
	 * 查询(条件查询)
	 * 查询活动
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
			  
		return this.activityManageBiz.getList(param);
	}
	
	/*
	 * 
	 * 查询(条件查询)
	 * 查询活动项目
	 */
	@RemoteMethod
	public Page getProjectList(Map<String, String> param) {
		
		return this.activityManageBiz.getProjectList(param);
	}
	/*
	 * 
	 * 查询(条件查询)
	 * 查询附加项目
	 */
	@RemoteMethod
	public Page getAdditionList(Map<String, String> param) {
		
		return this.activityManageBiz.getAdditionList(param);
	}

	/*
	 * 
	 * 活动删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletes(String ids,String isDelete) {
		
		System.out.println("测试ids======"+ids);
		System.out.println("测试recoverId======"+isDelete);
		return this.activityManageBiz.delete(ids,isDelete);
	}
	/*
	 * 
	 * 活动项目删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletesProject(String ids,String isDelete) {
		return this.activityManageBiz.deletesProject(ids,isDelete);
	}
	/*
	 * 
	 * 附加项目删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletesAddition(String ids,String isDelete) {
		return this.activityManageBiz.deletesAddition(ids,isDelete);
	}

	/*
	 * 
	 * 根据id查询活动
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {

		
		
		return this.activityManageBiz.getById(id);
	}
	/*
	 * 
	 * 根据id查询活动项目
	 * 
	 */
	@RemoteMethod
	public ResultBean getByIdProject(String aid,String apid) {
		
		return this.activityManageBiz.getByIdProject(aid,apid);
	}
	/*
	 * 
	 * 根据id查询附加项目
	 * 
	 */
	@RemoteMethod
	public ResultBean getByIdAddition(String aid,String apaid) {
		
		return this.activityManageBiz.getByIdAddition(aid,apaid);
	}
	
	/*
	 * 
	 * 活动增加、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String, String> map) {


		return this.activityManageBiz.saveOrUpdate(map);
		 
	
	}
	/*
	 * 
	 * 活动项目增加、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdateProject(Map<String, String> map) {
		
		
		return this.activityManageBiz.saveOrUpdateProject(map);
		
		
	}
	/*
	 * 
	 * 活动项目增加、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdateAddition(Map<String, String> map) {
		
		
		return this.activityManageBiz.saveOrUpdateAddition(map);
		
		
	}
	/*
	 * 
	 * 查询省级
	 * 
	 */
	@RemoteMethod
	public List<Pronivce> getProvince(Map<String, String> map) {
			
		return (List<Pronivce>) this.activityManageBiz.getProvince(map);
			
	}
	/*
	 * 
	 * 查询市级
	 * 
	 */
	@RemoteMethod
	public List<City> getCity(Map<String, String> map) {
		
		return (List<City>) this.activityManageBiz.getCity(map);
		
	}

}
