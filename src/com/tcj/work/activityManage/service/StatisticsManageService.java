package com.tcj.work.activityManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.StatisticsEntity;

public interface StatisticsManageService {

	Page getList(Map<String, String> param);

	List<StatisticsEntity> exportExcel(Map<String, String> param);

}
