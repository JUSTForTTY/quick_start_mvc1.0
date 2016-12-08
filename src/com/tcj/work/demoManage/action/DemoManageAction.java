/*
 * Copyright 2016-xxxx the original author or authors.
 *
 */
package com.tcj.work.demoManage.action;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.demoManage.biz.*;
import com.tcj.work.demoManage.service.*;

/**
 * Demo基础方法.
 * 
 * @author xxx
 * @date 2016-7-20 下午1:20:15
 * @version 1.0
 * @history
 */
@Controller("demoManageAction")
@RemoteProxy(name = "demoManageAction")
public class DemoManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("demoManageBizImpl")
	private DemoManageBiz demoManageBiz;

	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		return demoManageBiz.getList(param);
	}

	/*
	 * 
	 * 删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletes(String ids) {
		return this.demoManageBiz.delete(ids);
	}

	/*
	 * 
	 * 根据id查询
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {
		// int ids = Integer.parseInt(id);
		return this.demoManageBiz.getById(id);
	}

	/*
	 * 
	 * 新增
	 * 
	 */
	@RemoteMethod
	public ResultBean save(Map<String, String> map) {
		return this.demoManageBiz.save(map);
	}

	/*
	 * 
	 * 更新
	 * 
	 */
	@RemoteMethod
	public ResultBean update(Map<String, String> map) {
		return this.demoManageBiz.update(map);
	}
}
