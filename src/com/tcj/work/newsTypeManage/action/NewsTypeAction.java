package com.tcj.work.newsTypeManage.action;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.work.newsTypeManage.biz.NewsTypeBiz;

/**
 * Demo基础方法.
 * 
 * @author xxx
 * @date 2016-7-20 下午1:20:15
 * @version 1.0
 * @history
 */
@Controller("newsTypeAction")
@RemoteProxy(name = "newsTypeAction")
public class NewsTypeAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("newsTypeBizImpl")
	private NewsTypeBiz newsTypeBiz;
	
	/**
	 * 查询新闻类型
	 */
	@RemoteMethod
	public List getCategoryType(Map param){
		try {
			return newsTypeBiz.getCategoryType(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询新闻类别父类别
	 */
	@RemoteMethod
	public List getParentName(Map param){
		try {
			return newsTypeBiz.getParentName(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		return newsTypeBiz.getList(param);
	}

	/*
	 * 
	 * 删除和恢复
	 * 
	 */
	@RemoteMethod
	public ResultBean deletes(Map<String, String> param) {
		return this.newsTypeBiz.delete(param);
	}

	/*
	 * 
	 * 根据id查询
	 * 
	 */
	@RemoteMethod
	public Object getNewsTypeById(String id) {
		return this.newsTypeBiz.getNewsTypeById(id);
	}
	

	

	/*
	 * 
	 * 新增
	 * 
	 */
	@RemoteMethod
	public ResultBean saveNewsType(Map<String, String> map) {
		return  newsTypeBiz.saveNewsType(map);
	}

}
