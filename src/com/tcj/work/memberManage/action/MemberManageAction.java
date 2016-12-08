package com.tcj.work.memberManage.action;

import java.util.Map;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.work.memberManage.biz.MemberManageBiz;

/**
 *  
 * 
 * @author zxs
 * @version 1.0
 * @date 2016/11/15
 *
 */

@Controller
@RemoteProxy(name="memberManageAction")
public class MemberManageAction {

	@Autowired
	@Qualifier("memberManageBizImpl")
	private MemberManageBiz memberManageBiz;
	
	@RemoteMethod
	public Page getMemberList(Map<String,String> map){
		return memberManageBiz.getPageList(map);
	}
	
	
	
	
	/**
	 * 
	 * 新增和修改
	 * 
	 * saveorupdate 方法
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map map){
	
	System.out.println("来后台");	
		return memberManageBiz.saveOrupdate(map);
		
		
}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**
 * 
 * 根据id查询,做修改
 * 
 * 
 */
@RemoteMethod
public ResultBean getByid(Integer id){
	System.out.println("你好，世界");
	return memberManageBiz.getByid(id);
}
	
/**
 * 
 * 根据用户信息里面的providerId来获取信息
 * 
 * 
 */
@RemoteMethod
public ResultBean getMenmberByid(Integer id){
	System.out.println("你好，世界");
	return memberManageBiz.getBymemberid(id);
}






	
/**
 * 
 * 根据id删除
 * 
 * 	
 */
@RemoteMethod
public ResultBean 	deleteData(String id){
		return memberManageBiz.delete(id);
	}
	
	
	
}