package com.tcj.work.demoManage.biz.impl;

import java.sql.Timestamp;
import java.util.Date;
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
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.demoManage.biz.DemoManageBiz;
import com.tcj.work.demoManage.service.DemoManageService;

@Component("demoManageBizImpl")
public class DemoManageBizImpl implements DemoManageBiz {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("demoManageServiceImpl")
	private DemoManageService demoManageService;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			return demoManageService.getList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 
	 * 删除
	 * 
	 */
	@RemoteMethod
	public ResultBean delete(String ids) {
		ResultBean resultBean = new ResultBean();
		try {
			DemoManage demoManage = new DemoManage();
			Map<String, String> param = new HashMap<String, String>();
//			LoginEntity entity = authorizeService.getLoginUser();
//			String loginId = entity.getUserId().toString();
			param.put("ids", ids);
			param.put("loginId", "12");
			demoManageService.delete(param);
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
	 * 根据id查询
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setSuccess(true);
			resultBean.setData(this.demoManageService.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	/*
	 * 
	 * 新增
	 * 
	 */
	@RemoteMethod
	public ResultBean save(Map<String, String> map) {
		ResultBean resultBean = new ResultBean();
		try {
//			LoginEntity entity = authorizeService.getLoginUser();
//			String loginId = entity.getUserId().toString();
			DemoManage demoManage = new DemoManage();
			demoManage.setUsername(MapUtils.getString(map, "username", ""));
			demoManage.setUserage(MapUtils.getString(map, "userage", ""));
			demoManage.setUserpass(MapUtils.getString(map, "userpass", ""));
			demoManage.setCreateuser(12+"");
			demoManage.setCreatetime(DateUtil.newDate());
			demoManage.setStatus(0);
//			System.out.print(entity.getUserId());

			this.demoManageService.save(demoManage);
			resultBean.setData(demoManage);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage()+"gu");
		}
		return resultBean;
	}

	/*
	 * 
	 * 更新
	 * 
	 */
	@RemoteMethod
	public ResultBean update(Map<String, String> map) {
		ResultBean resultBean = new ResultBean();
		try {
//			LoginEntity entity = authorizeService.getLoginUser();
//			String loginId = entity.getUserId().toString();
			DemoManage demoManage = new DemoManage();
			demoManage.setId(MapUtils.getString(map, "id"));
			demoManage.setUsername(MapUtils.getString(map, "username", ""));
			demoManage.setUserage(MapUtils.getString(map, "userage", ""));
			demoManage.setUserpass(MapUtils.getString(map, "userpass", ""));
			demoManage.setModifyuser("12");
			demoManage.setModifytime(new Timestamp(new Date().getTime()));
		//	demoManage.setStatus(0);
			
			this.demoManageService.update(demoManage);
			resultBean.setData(demoManage);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
}
