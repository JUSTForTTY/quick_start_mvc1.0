
package com.tcj.work.clubManage.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;
import com.tcj.work.clubManage.biz.ClubManageBiz;


/**
 * @dscription 社团数据维护 
 * @author fengrb
 * @date 2016/09/06 
 * @version 1.0
 * @history
 */
@Controller("clubManageAction")
@RemoteProxy(name = "clubManageAction")//父类路径
public class clubManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("clubManageBizImpl")
	private ClubManageBiz clubManageBiz;

	/*
	 * 
	 * 查询(条件查询)
	 * 查询社团
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
			  
		return this.clubManageBiz.getList(param);
	}
	/*
	 * 
	 * 社团删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletes(String ids,String isDelete) {
		
		System.out.println("测试ids======"+ids);
		System.out.println("测试recoverId======"+isDelete);
		return this.clubManageBiz.delete(ids,isDelete);
	}
	/*
	 * 
	 * 社团审核
	 * 
	 */
	@RemoteMethod
	public ResultBean clubPending(String ids,String status) {
		
		System.out.println("测试ids======"+ids);
		System.out.println("测试recoverId======"+status);
		return this.clubManageBiz.clubPending(ids,status);
	}
	/*
	 * 
	 * 根据id查询社团
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {		
		
		return this.clubManageBiz.getById(id);
	}
	
	/*
	 * 
	 * 社团增加、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String, String> map) throws EgladServiceException {

		return this.clubManageBiz.saveOrUpdate(map);
		 
	
	}
	
	/*
	 * 
	 * 查询省级
	 * 
	 */
	@RemoteMethod
	public List<Pronivce> getProvince(Map<String, String> map) {
			
		return (List<Pronivce>) this.clubManageBiz.getProvince(map);
			
	}
	/*
	 * 
	 * 查询市级
	 * 
	 */
	@RemoteMethod
	public List<City> getCity(Map<String, String> map) {
		
		return (List<City>) this.clubManageBiz.getCity(map);
		
	}

}
