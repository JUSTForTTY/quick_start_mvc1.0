package com.tcj.work.userManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.User;

public interface UserManageService {

	public Page getList(Map<String, String> param);

	public List<User> Exportexcel(Map params);

	public void saveOrUpdate(User user) throws EgladServiceException;

	public Object getById(String id) throws EgladServiceException;

	public void deletes(Map<String, String> param);

}
