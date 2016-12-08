package com.tcj.work.rechargerulesManage.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.RechargeRules;
import com.tcj.work.rechargerulesManage.biz.RechargerulesBiz;

/**
 * rechargerules管理模块
 * @author SRC
 * @date 2016-07-30
 */
@Controller("rechargerulesAction")
@RemoteProxy(name = "rechargerulesAction")
public class RechargerulesAction {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("rechargerulesBizImpl")
	private RechargerulesBiz rechargerulesBizImpl;
	/**
	 * 获取奖励规则列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public Page getList(Map<String, String> map) throws Exception{
		return rechargerulesBizImpl.getList(map);
		
	}
	/**
	 * 新增或者更新
	 * @param map 
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public ResultBean SaveOrUpdate(Map<String, String> map) throws Exception{
		RechargeRules rechargeRules = new RechargeRules();
		if(MapUtils.getString(map, "id")==null){
			rechargeRules.setReid(rechargerulesBizImpl.getSequence());
			rechargeRules.setCreateTime(new Date());
		}else{
			rechargeRules.setReid(MapUtils.getString(map, "id",""));
		}
		
		rechargeRules.setAmount(MapUtils.getInteger(map, "amount",0));
		rechargeRules.setReward(MapUtils.getString(map, "reward",""));
		rechargeRules.setModifyTime(new Date());
		rechargeRules.setBid(CommonDefine.JK_BUILDING);
		rechargeRules.setFlag(""+CommonDefine.RECHARGE_MONEY);
		return rechargerulesBizImpl.SaveOrUpdate(rechargeRules);
		
	}
	/**
	 * 删除或者恢复
	 * @param ids
	 * @param isdelete 删除恢复标记
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public ResultBean deletes(String ids)throws Exception{
		return rechargerulesBizImpl.delete(ids);
		
	}
	/**
	 * 获取单个奖励规则对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public ResultBean getById(String id) throws Exception{
		return rechargerulesBizImpl.getById(id);
		
	}
}
