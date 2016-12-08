package com.tcj.work.goodsManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.Category;
import com.tcj.domains.City;
import com.tcj.domains.Club;
import com.tcj.domains.Pronivce;


public interface GoodsTypeManageService {

	Page getList(Map<String, String> param);

	void delete(Map<String, String> param);

	Category getById(String id) throws Exception;

	void saveOrUpdate(Category category) throws Exception;

	void updateImage(Map<String,String> param);

}
