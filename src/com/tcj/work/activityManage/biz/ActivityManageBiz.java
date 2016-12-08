package com.tcj.work.activityManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.City;
import com.tcj.domains.Pronivce;

public interface ActivityManageBiz {

	Page getList(Map<String, String> param);

	ResultBean delete(String ids, String isDelete);

	ResultBean getById(String id);

	ResultBean saveOrUpdate(Map<String, String> map);

	Page getProjectList(Map<String, String> param);

	ResultBean saveOrUpdateProject(Map<String, String> map);

	ResultBean getByIdProject(String aid,String apid);

	ResultBean deletesProject(String ids, String isDelete);

	Page getAdditionList(Map<String, String> param);

	ResultBean deletesAddition(String ids, String isDelete);

	ResultBean saveOrUpdateAddition(Map<String, String> map);

	ResultBean getByIdAddition(String aid, String apaid);

	List<Pronivce> getProvince(Map<String, String> map);

	List<City> getCity(Map<String, String> map);

}
