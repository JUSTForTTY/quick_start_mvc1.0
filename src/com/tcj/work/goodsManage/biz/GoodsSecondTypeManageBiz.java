package com.tcj.work.goodsManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.City;
import com.tcj.domains.Pronivce;

public interface GoodsSecondTypeManageBiz {

	Page getList(Map<String, String> param);

	ResultBean delete(String ids, String isDelete);

	ResultBean getById(String id);

	ResultBean saveOrUpdate(Map<String, String> map);


}
