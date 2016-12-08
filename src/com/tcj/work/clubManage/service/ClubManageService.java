package com.tcj.work.clubManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.City;
import com.tcj.domains.Club;
import com.tcj.domains.Pronivce;


public interface ClubManageService {

	Page getList(Map<String, String> param) throws EgladServiceException;

	void delete(Map<String, String> param);

	Object getById(String id) throws Exception;

	void saveOrUpdate(Club club) throws Exception;

	List<Pronivce> getProvince(Map<String, String> map);

	List<City> getCity(Map<String, String> map);

	void updateImage(Map<String,String> param);

	void clubPending(Map<String, String> param);

}
