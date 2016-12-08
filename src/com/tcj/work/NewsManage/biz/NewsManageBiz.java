package com.tcj.work.NewsManage.biz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Category;
import com.tcj.domains.News;

public interface NewsManageBiz {

	Page getNewsList(Map<String, String> param);

	ResultBean delete(String ids);

	ResultBean saveOrUpdate(News news);

	// 根据caid与id查询
	ResultBean getBynid(String nid, String caid);

	// 课程体系
	Page getCourseList(Map<String, String> param);

	// 所有内容加title的查询显示
	Page getallContentList(Map<String, String> param);

	// 明星学员
	Page getStarStudent(Map<String, String> param);

	// 名师
	Page getFamousTeacher(Map<String, String> param);

	// 视频
	Page getVideo(Map<String, String> param);

	// 个性化流程
	Page getPersonProcess(Map<String, String> param);

	Page geniusEvent(Map<String, String> param);

	void saveOrUpdateImg(News news);

	ResultBean geniussaveOrUpdate(News news);

	ResultBean getByCaid(String caid);
//content 的saveorupdate
	ResultBean contentsaveOrUpdate(News news);


	ResultBean Recovery(News news);

//	ResultBean starsaveOrUpdate(News news);

}
