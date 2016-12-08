package com.tcj.work.commentManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;

public interface CommentManageService {

	/**
	 * 查询数据
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getList(Map<String, String> param) throws Exception;

	
	public List getActivityName(Map<String, String> param);
	
	
	public Page getBestList(Map<String, String> param);
	/**
	 * 更新数据
	 * 
	 * @param 
	 * @throws Exception
	 */
	public void update(ActivityComment paramActivityComment,String label) throws Exception;
	
	
	
	
	/**
	 * 删除数据
	 * 
	 * @param param
	 * @throws Exception
	 */
	public void delete(Map<String, String> param) throws Exception;


	/**
	 * 保存数据
	 * 
	 * @param paramDemoManage
	 * @throws Exception
	 */
	public List saveOrUpdate(Map<String, String> param) throws Exception;

	/**
	 * 统计
	 * 
	 *
	 * @throws Exception
	 */
	public List countList(Map<String, String> param) throws Exception;

	


}
