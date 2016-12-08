package com.tcj.work.activityManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.ActivityProject;
import com.tcj.domains.ActivityProjectAdditional;
import com.tcj.domains.City;
import com.tcj.domains.Pronivce;
import com.tcj.domains.ActivityProjectAdditional;

public interface ActivityManageService {

	Page getList(Map<String, String> param);

	void delete(Map<String, String> param);

	Activity getById(String id) throws Exception;

	void saveOrUpdate(Activity activity) throws Exception;

	Page getProjectList(Map<String, String> param);

	void saveOrUpdateProject(ActivityProject actProject) throws Exception;

	ActivityProject getByIdProject(String id, String apid) throws Exception;

	void deletesProject(Map<String, String> param);

	Page getAdditionList(Map<String, String> param);

	void deletesAddition(Map<String, String> param);

	void saveOrUpdateAddition(ActivityProjectAdditional actAddition) throws Exception;

	ActivityProjectAdditional getByIdAddition(String aid, String apaid) throws Exception;

	void updateImage(Map<String, String> param);

	List<Pronivce> getProvince(Map<String, String> map);

	List<City> getCity(Map<String, String> map);

}
