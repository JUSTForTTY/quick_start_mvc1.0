package com.tcj.work.newsType.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.work.newsType.biz.NewsTypeManageBiz;


/**
 * 
 * 
 * 
 * 
 * 
 * @author   zxs
 * 
 * @date 2016/10/25
 * 
 *@version 1.0
 *
 */



@Controller("newsTypeManageAction")
@RemoteProxy(name="newsTypeManageAction")
public class NewsTypeManageAction {

	@Autowired
	@Qualifier("newsTypeManageBizImpl")
	private NewsTypeManageBiz newsTypeManageBiz;
	
	/**
	 * 
	 * 分页查询  根据条件查询
	 * 
	 * 
	 */
	@RemoteMethod
	public Page getNewsTypeList(Map<String,String> param){
		
		return newsTypeManageBiz.getTypeList(param);
		
	}
	
	/**
	 * 
	 * 新增和修改
	 * 
	 * 
	 * 
	 */
	
	@RemoteMethod
	public ResultBean SaveOrUpdate(Map<String, String> map){
		
		
		
		
		return newsTypeManageBiz.saveOrUpdate(map);
	}
	
	
	/**
	 * 删除（逻辑删除）
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean deletedata(String id) {
		System.out.println("你好，删除方法");
		return newsTypeManageBiz.delete(id);
	}
	
	/**
	 * 
	 * 根据id查询
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean getByid(Integer id) {

		return newsTypeManageBiz.getBynid(id);
	}
	
	/**
	 * 
	 * 新增
	 * 
	 * 
	 */
	
	public ResultBean saveType(Map map) {
		ResultBean resultBean = new ResultBean();
		try {
			newsTypeManageBiz.saveType(map);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	
	
	
	
}
