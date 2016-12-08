package com.tcj.work.userManage.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.User;
import com.tcj.work.userManage.biz.UserManageBiz;

/**
 * 
 * @author zxs
 * @editor frb
 * @date 2016-11-29 
 * @version 1.0
 * @history
 */
@Controller("userManageAction")
@RemoteProxy(name="userManageAction")
public class UserManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("userManageBizImpl")
	private  UserManageBiz   userManageBiz;	
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) throws EgladServiceException {
		return userManageBiz.getList(param);
	}
	/*
	 * 新增or修改
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String, String> map) throws EgladServiceException {

		return this.userManageBiz.saveOrUpdate(map);	 
	
	}
	/*
	 * 
	 * 根据id查询
	 */
	@RemoteMethod
	public ResultBean getById(String id) {		
		
		return this.userManageBiz.getById(id);
	}
	/*
	 * 
	 * 
	 */
	@RemoteMethod
	public  ResultBean deletes(String ids) {

		return userManageBiz.deletes(ids);	
	}

	/**
	 * 
	 * 根据真实姓名，用户名导出数据
	 * 
	 * 
	 */
	@RemoteMethod
	@RequestMapping(value="/exportlog")
	public String getGather(Map params,Model model,HttpSession session,HttpServletRequest request,
			@RequestParam(value="username") String username) throws UnsupportedEncodingException{

		
		List<User> user=userManageBiz.Exportexcel(params);
		username= new String( username.getBytes("iso-8859-1"), "UTF-8");
		params.put("username", username);
	//	((User) user).getUsername();
		System.out.println("所有值都在user里面"+user);
	//	System.out.println(((Map<String, String>) log).size());
		System.out.println("你好，你走到这里了");
		model.addAttribute("query",user);//把user里面的值放到query里面，带着前台去读值


		return  "/work/User/exportUser";
	}




} 
