package com.tcj.work.rechargerulesManage.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.RechargeRules;
import com.tcj.work.rechargerulesManage.biz.RechargerulesBiz;
import com.tcj.work.rechargerulesManage.service.RechargerulesService;

@Component("rechargerulesBizImpl")
public class RechargerulesBizImpl implements RechargerulesBiz{
	@Autowired
	@Qualifier("rechargerulesServiceImpl")
	private RechargerulesService rechargerulesServiceImpl;

	@Override
	public Page getList(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return rechargerulesServiceImpl.getList(map);
	}

	@Override
	public ResultBean SaveOrUpdate(RechargeRules rechargeRules) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
				rechargerulesServiceImpl.SaveOrUpdate(rechargeRules);
				resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean delete(String ids) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			rechargerulesServiceImpl.delete(ids);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean getById(String id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(rechargerulesServiceImpl.getById(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		return rechargerulesServiceImpl.getSequence();
	}
}
