package com.tcj.work.activityManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.StatisticsEntity;

public interface StatisticsManageBiz {

	Page getList(Map<String, String> param);
	List<StatisticsEntity> exportExcel(Map<String, String> param);
}
