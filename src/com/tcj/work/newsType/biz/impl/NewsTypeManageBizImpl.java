package com.tcj.work.newsType.biz.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

import com.tcj.domains.News;
import com.tcj.work.newsType.biz.NewsTypeManageBiz;
import com.tcj.work.newsType.service.NewsTypeManageService;
@Component("newsTypeManageBizImpl")
public class NewsTypeManageBizImpl implements NewsTypeManageBiz {

	@Autowired
	@Qualifier("newsTypeManageServiceImpl")
	private NewsTypeManageService newsTypeManageService;
	
	
	@Override
	public Page getTypeList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsTypeManageService.getTypeList(param);
	}


	@Override
	public ResultBean saveOrUpdate(Map<String, String> param) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		News news=new News();
		//Integer id = null;
	//	news.setId(Integer.valueOf(MapUtils.getString(param,id)));
		//System.out.println(Integer.valueOf(MapUtils.getString(param,id)+"nihao a "));
		news.setTitle(MapUtils.getString(param, "title",""));
		news.setType(MapUtils.getString(param, "type",""));
		news.setContent(MapUtils.getString(param, "content",""));
		news.setStatus(MapUtils.getString(param, "status",""));
		news.setUrl(MapUtils.getString(param, "url",""));
		news.setBriefintro(MapUtils.getString(param, "briefintro",""));
		news.setId(MapUtils.getInteger(param, "id"));
		//System.out.println(news.setId(MapUtils.getInteger(param, "id")));
		try {
			
		newsTypeManageService.saveOrUpdate(news);			
			resultBean.setSuccess(true);
		
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}


	@Override
	public ResultBean delete(String id) {
		// TODO Auto-generated method stub
	
			// TODO Auto-generated method stub
			ResultBean resultBean = new ResultBean();
			try {
				News news = new News();
				Map<String, String> param = new HashMap<String, String>();
				param.put("ids", id);
			//	param.put("isDelete");
				newsTypeManageService.delete(param);
				resultBean.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				resultBean.setSuccess(false);
				resultBean.setMsg(e.getMessage());
			}
			return resultBean;
		}


	@Override
	public ResultBean getBynid(Integer id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {

			resultBean.setData(newsTypeManageService.getBynid(id));

			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}


	@Override
	public void saveType(Map map) throws Exception {
		// TODO Auto-generated method stub
		newsTypeManageService.saveType(map);
	}


}
