package com.tcj.work.rechargerulesManage.biz;

import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.RechargeRules;

public interface RechargerulesBiz {
	public Page getList(Map<String, String> map) throws Exception;

	public ResultBean SaveOrUpdate(RechargeRules rechargeRules);

	public ResultBean delete(String ids);

	public ResultBean getById(String id);
	
	public String getSequence();
}
