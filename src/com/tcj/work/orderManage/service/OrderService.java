package com.tcj.work.orderManage.service;

import java.util.Map;

import com.tcj.common.dao.model.Page;

import com.tcj.domains.Order;

public interface OrderService {

	Page getList(Map<String, String> map);

	Page getByid(Map param);

	Page getList1(Map<String, String> map);

	Page getList2(Map<String, String> map);



}
