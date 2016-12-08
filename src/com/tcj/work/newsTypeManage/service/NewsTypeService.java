package com.tcj.work.newsTypeManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;

public interface NewsTypeService {

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
	 * @param ids
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
	public Object getNewsTypeById(String id) throws Exception;

	/**
	 * 保存数据
	 * 
	 * @param paramDemoManage
	 * @throws Exception
	 */
	public void saveNewsType(Category category) throws Exception;

	public List getCategoryType(Map param);
	
	public List getParentName(Map param);


}