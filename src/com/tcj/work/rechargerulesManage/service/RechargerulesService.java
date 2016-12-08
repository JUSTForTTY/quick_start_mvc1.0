package com.tcj.work.rechargerulesManage.service;

import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.RechargeRules;

public interface RechargerulesService {

	Page getList(Map<String, String> map);

	void SaveOrUpdate(RechargeRules rechargeRules);

	void delete(String ids);

	RechargeRules getById(String id) throws Exception;

	String getSequence();

}
