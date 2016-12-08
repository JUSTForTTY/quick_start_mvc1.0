package com.tcj.work.rechargerulesManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.CodeMaster;
import com.tcj.domains.RechargeRules;
import com.tcj.work.rechargerulesManage.service.RechargerulesService;

@Component("rechargerulesServiceImpl")
public class RechargerulesServiceImpl implements RechargerulesService{
	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	@Override
	public Page getList(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		Integer pgNumber = MapUtils.getInteger(map, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(map, "rows", Integer.valueOf(20)); 
		String amount=MapUtils.getString(map, "amount", "");
		String reward=MapUtils.getString(map, "reward", "");
		String hql="from RechargeRules where 1=1";
		if(!"".equals(amount)){
			hql+=" and amount like ?";
			al.add("%" + amount + "%");
		}
		if(!"".equals(reward)){
			hql+=" and reward like ?";
			al.add("%" + reward + "%");
		}
		hql += " order by amount asc ";
		//?参数对象数组
		System.out.println("hql:"+hql);
		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	@Override
	public void SaveOrUpdate(RechargeRules rechargeRules) {
		// TODO Auto-generated method stub
		 splitPageDao.saveOrUpdate(rechargeRules);
	}

	@Override
	public void delete(String ids) {
		// TODO Auto-generated method stub
		String sql=" delete from recharge_rules where reid in ("+ids+")";
		splitPageDao.excuteSql(sql);
	}

	@Override
	public RechargeRules getById(String id) throws EgladServiceException {
		// TODO Auto-generated method stub
		String hql=" from RechargeRules where reid ='"+id+"'";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (RechargeRules) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

	@Override
	public String getSequence() {
		// TODO Auto-generated method stub
		String sql="CALL pro_getRunningNO('RID')"; 
		  String sequence=splitPageDao.findByjkSequence(sql);
		  System.out.println("sequence:"+sequence);
		return sequence;
	}
}
