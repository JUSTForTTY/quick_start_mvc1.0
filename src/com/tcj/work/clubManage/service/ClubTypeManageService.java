package com.tcj.work.clubManage.service;

import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.ClubType;

public interface ClubTypeManageService {

	Page getList(Map<String, String> param);

	void saveOrUpdate(ClubType clubType) throws EgladServiceException;

	void delete(Map<String, String> param);

	ClubType getById(String id) throws EgladServiceException;

}
