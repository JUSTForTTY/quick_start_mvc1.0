package com.tcj.work.activityManage.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.EnumActivityStatus;
import com.tcj.domains.Activity;
import com.tcj.domains.StatisticsEntity;
import com.tcj.work.activityManage.biz.StatisticsManageBiz;
import com.tcj.work.activityManage.service.StatisticsManageService;

@Component("statisticsManageBizImpl")
public class StatisticsManageBizImpl implements StatisticsManageBiz{
private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("statisticsManageServiceImpl")
	private StatisticsManageService statisticsManageService;

	@Override
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			return statisticsManageService.getList(param);	 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<StatisticsEntity> exportExcel(Map<String, String> param) {
		// TODO Auto-generated method stub
		return statisticsManageService.exportExcel(param);
	}
}
