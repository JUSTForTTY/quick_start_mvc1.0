package com.tcj.work.NewsManage.biz.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.DateUtil;
import com.tcj.common.util.IOUtil;
import com.tcj.domains.Category;
import com.tcj.domains.DemoManage;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.work.NewsManage.biz.NewsManageBiz;
import com.tcj.work.NewsManage.service.NewsManageService;


@Component("newsManageBizImpl")
public class NewsManageBizImpl implements NewsManageBiz {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("newsManageServiceImpl")
	private NewsManageService newsManageService;

	@Override
	public Page getNewsList(Map<String, String> param) {

		return newsManageService.getNewsList(param);

	}

	@Override
	public ResultBean delete(String ids) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			News news = new News();
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
		//	param.put("isDelete");
			newsManageService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean saveOrUpdate(News news) {
		// TODO Auto-generated method stub

		ResultBean resultBean = new ResultBean();
		try {

			// news.setCaid(news.getCaid());
			// String caid=news.getCaid()
			System.out.println(news.getCaid());
			this.newsManageService.saveOrUpdate(news);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean getBynid(String nid, String caid) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {

			resultBean.setData(this.newsManageService.getBynid(nid, caid));

			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}

	@Override
	public ResultBean getByCaid(String caid) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {

			resultBean.setData(this.newsManageService.getByCaid(caid));

			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}

	@Override
	public Page getCourseList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.getCourseList(param);

	}

	@Override
	public Page getallContentList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.getallContentList(param);
	}

	@Override
	public Page getStarStudent(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.getStarStudent(param);
	}

	@Override
	public Page getFamousTeacher(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.getFamousTeacher(param);
	}

	@Override
	public Page getVideo(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.getVideo(param);
	}

	@Override
	public Page getPersonProcess(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.getPersonProcess(param);
	}

	@Override
	public Page geniusEvent(Map<String, String> param) {
		// TODO Auto-generated method stub
		return newsManageService.geniusEvent(param);
	}

	@Override
	public void saveOrUpdateImg(News news) {
		// TODO Auto-generated method stub
		newsManageService.saveOrUpdateImg(news);
	}

	@Override
	public ResultBean geniussaveOrUpdate(News news) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {

			// news.setCaid(news.getCaid());
			// String caid=news.getCaid()
			System.out.println(news.getCaid());
			this.newsManageService.geniussaveOrUpdate(news);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean contentsaveOrUpdate(News news) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {

			// news.setCaid(news.getCaid());
			// String caid=news.getCaid()
			System.out.println(news.getCaid());
			this.newsManageService.contentsaveOrUpdate(news);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}


	@Override
	public ResultBean Recovery(News news) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {			
			newsManageService.Recovery(news);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	/**
	 * 名师和明星学员saveorupdate
	 * 
	 * 
	 */
	
//	@Override
//	public ResultBean starsaveOrUpdate(News news) {
//		// TODO Auto-generated method stub
//		ResultBean resultBean = new ResultBean();
//		try {
//
//			// news.setCaid(news.getCaid());
//			// String caid=news.getCaid()
//			System.out.println(news.getCaid());
//			this.newsManageService.starsaveOrUpdate(news);
//			resultBean.setSuccess(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultBean.setSuccess(false);
//			resultBean.setMsg(e.getMessage());
//		}
//		return resultBean;
//	}

}
