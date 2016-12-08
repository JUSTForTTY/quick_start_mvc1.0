package com.tcj.work.newsType.service;

import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.News;

public interface NewsTypeManageService {

	Page getTypeList(Map<String, String> param);

	void saveOrUpdate(News news) throws Exception;

	void delete(Map<String, String> param);

	public News getBynid(Integer id) throws Exception;

	public void saveType(Map map) throws Exception;
}
