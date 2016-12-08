package com.tcj.work.goodsManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Category;
import com.tcj.domains.Goods;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.Tbk_Goods;
import com.tcj.domains.User;

public interface GoodsManageBiz {

	Page getList(Map<String, String> map);

	ResultBean deleteList(String id);

	ResultBean saveorUpdate(Map map);

	List<Category> getCategoryId(Map<String, String> map);

	List<Category> getParaentId(Map<String, String> map);

	void selectUser(User user);

	ResultBean getById(Integer id);

	ResultBean savegoods(Map map);

	public void delData(String id) throws Exception;

	public Integer saveuploadGoods(Goods goods);

	
	

}
