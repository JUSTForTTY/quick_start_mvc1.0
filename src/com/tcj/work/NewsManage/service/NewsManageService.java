package com.tcj.work.NewsManage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Category;
import com.tcj.domains.News;

public interface NewsManageService {

	Page getNewsList(Map<String, String> param);

	void delete(Map<String, String> param);
	void updateImage(Map<String, String> param);
	void saveOrUpdate(News news) throws Exception;

	public News getBynid(String nid, String caid) throws Exception;

	public News getByCaid(String caid) throws Exception;

	Page getCourseList(Map<String, String> param);

	// 所有内容的查询
	Page getallContentList(Map<String, String> param);

	// 明星学员
	Page getStarStudent(Map<String, String> param);

	Page getFamousTeacher(Map<String, String> param);

	Page getVideo(Map<String, String> param);

	Page getPersonProcess(Map<String, String> param);

	Page geniusEvent(Map<String, String> param);

	void saveOrUpdateImg(News news);

	void geniussaveOrUpdate(News news) throws Exception;
//content 里面的saveorupdate
	void contentsaveOrUpdate(News news) throws Exception;

	void updateMiniature(Map param);

	void Recovery(News news) throws Exception;

	//void starsaveOrUpdate(News news) throws Exception;

}
