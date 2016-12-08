package com.tcj.work.clubManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.City;
import com.tcj.domains.Pronivce;

public interface ClubManageBiz {

	Page getList(Map<String, String> param);

	ResultBean delete(String ids, String isDelete);

	ResultBean getById(String id);

	List<Pronivce> getProvince(Map<String, String> map);

	List<City> getCity(Map<String, String> map);

	ResultBean saveOrUpdate(Map<String, String> map) throws EgladServiceException;

	ResultBean clubPending(String ids, String status);

}
