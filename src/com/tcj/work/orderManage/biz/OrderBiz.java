package com.tcj.work.orderManage.biz;

import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

public interface OrderBiz {

	Page getOrderList(Map<String, String> map);

	Page getByid(Map param);

	

}
