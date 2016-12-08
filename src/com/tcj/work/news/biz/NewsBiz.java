package com.tcj.work.news.biz;

import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

public interface NewsBiz {

	Page getList(Map<String, String> params);

	ResultBean delete(String id);

	void insert(Map param)  throws  EgladServiceException;

	ResultBean getByid(Integer id);



	ResultBean saveOrupdate(Map<String, String> map);

	ResultBean update(Map map);

	  public void delData(String id) throws Exception;

	Page getBreakingNews(Map map);

	ResultBean getsaveOrUpdate(Map map) ;



	
}
