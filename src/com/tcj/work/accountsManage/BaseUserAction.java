package com.tcj.work.accountsManage;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.work.accountsManage.service.BaseUserService;
import com.tcj.work.authorize.service.IAuthorizeService;

@Controller("baseUserAction")
@RemoteProxy(name = "baseUserAction")
public class BaseUserAction {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("baseUserService")
	private BaseUserService baseUserService;
	
	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	

	/***
	 * 查询所有的用户
	 */
//	@RemoteMethod
//	public Page getBaseUserInfos(Map param) {
//		try {
//			if (log.isDebugEnabled()) {
//				log.debug("加载页面！");
//			}
//			return baseUserService.getBaseUserInfos(param);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	/***
	 * 查询单个用户所有信息
	 */
//	@RemoteMethod
//	public ResultBean  getBaseUserInfoById(Map param,HttpServletRequest request) {
//		ResultBean resultBean = new ResultBean();
//		try{
//			resultBean.setSuccess(true);
//			resultBean.setData(baseUserService.getBaseUserInfoById(param,request));
//		}catch(Exception e){
//			e.printStackTrace();
//			resultBean.setSuccess(false);
//			resultBean.setMsg(e.getMessage());
//		}
//		return resultBean;
//	}
	
	/**
	 * 保存用户信息
	 * @param baseUser
	 * @return
	 */
//	@RemoteMethod
//	public ResultBean  saveBaseUserInfo(BaseUser baseUser,Map param) {
//		ResultBean resultBean = new ResultBean();
//		try{
//			LoginEntity entity=authorizeService.getLoginUser();
//			String loginId=entity.getUserId().toString();
//			String loginIp=entity.getIp();
//			param.put("loginId", loginId);
//			param.put("loginIp", loginIp);
//			resultBean.setSuccess(true);
//			baseUserService.saveBaseUserInfo(baseUser,param);
//			resultBean.setData(baseUser);
//		}catch(Exception e){
//			e.printStackTrace();
//			resultBean.setSuccess(false);
//			resultBean.setMsg(e.getMessage());
//		}
//		return resultBean;
//	}
	
	/**
	 * 注册
	 * @param baseUser
	 * @return
	 */
//	@RemoteMethod
//	public ResultBean  saveBaseUserInfoToOne(BaseUser baseUser) {
//		ResultBean resultBean = new ResultBean();
//		try{
//			Map<String, String> map=new HashMap<String, String>();
//			map.put("loginId", "-1");
//			map.put("loginIp", "1.1.1.1");
//			resultBean.setSuccess(true);
//			baseUserService.saveUserInfo(baseUser,map);
//			resultBean.setData(baseUser);
//		}catch(Exception e){
//			e.printStackTrace();
//			resultBean.setSuccess(false);
//			resultBean.setMsg(e.getMessage());
//		}
//		return resultBean;
//	}
	
	
	/***
	 * 查询用户类别
	 */
//	@RemoteMethod
//	public List getUserType(Map param){
//		try {
//			return baseUserService.getUserType(param);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	/***
	 * 查询用户角色
	 */
//	@RemoteMethod
//	public List getSysRole(Map param) {
//		try {
//			return baseUserService.getSysRole(param);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 修改用户的状态
	 * falg 0，删除 1.冻结，2。解冻
	 */
//	@RemoteMethod
//	public ResultBean  updateBaseUserState(String flag,String userIds) {
//		ResultBean resultBean = new ResultBean();
//		try{
//			resultBean.setSuccess(true);
//			baseUserService.updateBaseUserState(flag,userIds);
//		}catch(Exception e){
//			e.printStackTrace();
//			resultBean.setSuccess(false);
//			resultBean.setMsg(e.getMessage());
//		}
//		return resultBean;
//	}

	

	
	@RemoteMethod
	public ResultBean getLoginInfo(Map map,HttpSession session) {
		ResultBean resultBean = new ResultBean();
		try{
			resultBean.setSuccess(true);
			//验证码
			String sRnd=(String)session.getAttribute("sRand");
			map.put("sRnd", sRnd);
			boolean userTypeFlag= baseUserService.saveLoginInfo(map,session);
			resultBean.setData(userTypeFlag);
		}catch(Exception e){
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	
	@RemoteMethod
	public boolean logout(){
		System.out.println("------已点击退出登录！");
		if(log.isDebugEnabled()){
			log.debug("点击取消退出系统！");
		}
		try{
			WebContext context = WebContextFactory.get();
			if(context != null){
				HttpSession session = context.getSession();
				if(session.getAttribute("LogInEntity") != null){
					session.removeAttribute("LogInEntity");
					session.removeAttribute("maritimeID");
				}
			}
			return true;
		}catch (Exception e) {
			log.debug(e);
			return false;
		}
	}
//	@RemoteMethod
//	public List<Map<String, String>> getUserTypeToOne(){
//		try{
//			return baseUserService.getUserType();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@RemoteMethod
//	public  String updateHeadPic(HttpServletRequest request){
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//	
//	/***
//	 * 修改密码
//	 * @return
//	 */
//	
	@RemoteMethod
	public ResultBean updateBaseUserPwd(Map map){
		ResultBean resultBean=new ResultBean();
		try {
			baseUserService.updateBaseUserPwd(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
//	/**
//	 * 后台管理
//	 * @param request
//	 * @return
//	 */
//	@RemoteMethod
//	public String getUserServiceCenter(HttpServletRequest request){
//		String path="";
//		try{
//			path=baseUserService.getUserServiceCenter(request);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return path;
//	}
//	
//	/**
//	 * 前台选择
//	 * @param request
//	 * @return
//	 */
//	@RemoteMethod
//	public Map getUserServiceCenterById(String id,HttpServletRequest request){
//		Map map=new HashMap();
//		try{
//			return baseUserService.getUserServiceCenterById(id,request);
//		}catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	/***
//	 * 查询信息条数
//	 * @param map
//	 * @return
//	 */
//	@RemoteMethod
//	public Integer  getCount(Map map) {
//		try {
//			return baseUserService.getCount(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return 0;
//	}
}
