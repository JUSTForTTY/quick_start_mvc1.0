package com.tcj.work.demoManage.service;

import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;

public interface DemoManageService {

	/**
	 * 查询数据
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page getList(Map<String, String> param) throws Exception;

	/**
	 * 删除数据
	 * 
	 * @param param
	 * @throws Exception
	 */
	public void delete(Map<String, String> param) throws Exception;

	/**
	 * 根据主键获得数据
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DemoManage getById(String id) throws Exception;

	/**
	 * 保存数据
	 * 
	 * @param paramDemoManage
	 * @throws Exception
	 */
	public void save(DemoManage paramDemoManage) throws Exception;

	/**
	 * 更新数据
	 * 
	 * @param paramDemoManage
	 * @throws Exception
	 */
	public void update(DemoManage paramDemoManage) throws Exception;


}