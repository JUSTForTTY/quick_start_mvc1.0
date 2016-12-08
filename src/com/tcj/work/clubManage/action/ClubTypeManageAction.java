package com.tcj.work.clubManage.action;

import java.util.Map;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.ClubType;
import com.tcj.work.clubManage.biz.ClubTypeManageBiz;

/**
 * @dscription 社团类别维护 
 * @author fengrb
 * @date 2016/10/21
 * @version 1.0
 * @history
 */
@Controller("clubTypeManageAction")
@RemoteProxy(name = "clubTypeManageAction")//父类路径
public class ClubTypeManageAction {

	@Autowired
	@Qualifier("clubTypeManageBizImpl")
	private ClubTypeManageBiz clubTypeManageBiz;

	/*
	 * 
	 * 查询(条件查询)
	 * 查询社团类别
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
			  
		return this.clubTypeManageBiz.getList(param);
	}
	/*
	 * 
	 * 根据id查询社团
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {

		
		
		return this.clubTypeManageBiz.getById(id);
	}
	/*
	 * 
	 * 社团类别删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletes(String ids,String isDelete) {
		
		System.out.println("测试ids======"+ids);
		System.out.println("测试isDelete======"+isDelete);
		return this.clubTypeManageBiz.delete(ids,isDelete);
	}
	
	/*
	 * 
	 * 社团增加、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String, String> map) {

		return this.clubTypeManageBiz.saveOrUpdate(map);
		 
	
	}
}
