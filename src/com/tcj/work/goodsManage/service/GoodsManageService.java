package com.tcj.work.goodsManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.AssocBuildingGoods;
import com.tcj.domains.AssocCategoryGoods;
import com.tcj.domains.Category;
import com.tcj.domains.Goods;
import com.tcj.domains.Tbk_Goods;
import com.tcj.domains.User;

public interface GoodsManageService {

	Page getList(Map<String, String> map);

	void deletetList(Map<String, String> param);

	void savegoods(Goods goods) throws Exception;

	List<Category> getCategoryId(Map<String, String> map);

	List<Category> getparentid(Map<String, String> map);

	Integer save(Goods goods);//超管新增所有

	void saveAssocCategoryGoods(AssocCategoryGoods acg);

	void selectUser(User user);

	void saveAssocBuildingGoods(AssocBuildingGoods abg);

Goods getById(Integer id) throws Exception;
	
	
	public User getByUserId(Integer id);

	void delData(String id) throws Exception;

	public Integer saveUploadGoods(Goods goods);

	void updateLoadImage(Map param);

	Goods getgoodsId(Integer id);

	Page getList1(Map<String, String> map);

	Page getList2(Map<String, String> map);

	Integer save1(Goods goods);//新增快车网商品

	Integer save2(Goods goods);

	void updategoods(Goods goods);

	

}
