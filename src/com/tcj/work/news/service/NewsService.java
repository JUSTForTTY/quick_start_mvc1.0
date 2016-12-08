package com.tcj.work.news.service;

import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.AssocBuildingNews;
import com.tcj.domains.News;
import com.tcj.domains.User;

public interface NewsService {

	Page getList(Map<String, String> params);

	void delete(Map<String, String> param);

	void insert(Map param)  throws  EgladServiceException;

	public  News getByid(Integer id) throws Exception;

	void update(Map map)  throws  EgladServiceException;

	void saveOrUpdate(News news)throws Exception;

	void updateLoadImage(Map<String, String> param);//上传图片

	Integer insertguanlian(News news);

	void abnsaveOrUpdate(AssocBuildingNews abn);

	void updateNews(News news) throws Exception;

	Integer insertguanlian1(News news);

	Integer insertguanlian2(News news,User user);

	void updateNews1(News news) throws Exception;

	void updateNews2(News news) throws Exception;

	void delData(String id);

	Page getList1(Map<String, String> params);

	Page getBreakingNews(Map map);

	void getsaveOrUpdate(News news) throws Exception;

	void getsaveOrUpdate1(News member) throws Exception;

	Page getBreakingNews1(Map map);


	
	
	
}
