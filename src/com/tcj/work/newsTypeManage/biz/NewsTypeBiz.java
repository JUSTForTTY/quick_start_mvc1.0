package com.tcj.work.newsTypeManage.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;

public interface NewsTypeBiz {

	public Page getList(Map<String, String> param);

	public ResultBean delete(Map<String, String> param);

	public Object getNewsTypeById(String id);

	public ResultBean saveNewsType(Map<String, String> map);

	public List getCategoryType(Map param);
	
	public List getParentName(Map param);

}