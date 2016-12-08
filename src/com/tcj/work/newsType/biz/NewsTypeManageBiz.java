package com.tcj.work.newsType.biz;

import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

public interface NewsTypeManageBiz {

	Page getTypeList(Map<String, String> param);

	ResultBean saveOrUpdate(Map<String, String> param);

	ResultBean delete(String id);

	ResultBean getBynid(Integer id);

	public void saveType(Map map) throws Exception;


}
