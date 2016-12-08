package com.tcj.work.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonUtil;
import com.tcj.work.common.service.CommonMethodService;

@Component("commonMethodService")
public class CommonMethodServiceImpl implements CommonMethodService {
	@Autowired()
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	/***
	 * @author jiayy
	 * @param key 形式： OCCUPATION_ID,POST_ID,HOBBIES_ID 
	 * @param return 返回值：Map<String, List>
	 */
	public Map<String, List> getCodeMaster(String key) throws Exception
	{
		Map<String, List> reMap=new HashMap<String,List>();
		String keys[]=key.split(",");
		String sql="";
		for (int i = 0; i < keys.length; i++) {
			sql="SELECT CODE_VALUE AS VAL,CODE_NAME AS TEXT FROM  t_sys_code_master WHERE CODE_TYPE='"+keys[i]+"' ORDER BY SORT ";
			List list=splitPageDao.findBySql(sql);
			reMap.put(keys[i], list);
		}
		return reMap;
	}
	
	public Page getServiceCenter(Map param) throws Exception{
		Integer pageNumber=MapUtils.getInteger(param,"page",1);
  		Integer pageSize=MapUtils.getInteger(param,"rows",100);
		String sql="SELECT a.*,b.AREA_FULL_NAME FROM t_base_service_center a left join t_base_area b on(b.AREA_ID=a.AREA_ID and b.IS_DELETED=0) WHERE a.IS_DELETED=0";
		String serviceCenterName=MapUtils.getString(param, "serviceCenterName","");
		serviceCenterName=CommonUtil.strReplace(serviceCenterName);
		if (!serviceCenterName.equals("")) {
			sql+=" AND SERVICE_CENTER_NAME like '%"+serviceCenterName+"%' ESCAPE '!' ";
		}
		Page page=splitPageDao.findBySql(sql, pageNumber, pageSize); 
		return page;
	}
}
