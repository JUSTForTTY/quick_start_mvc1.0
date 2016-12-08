package com.tcj.work.accountsManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.DemoManage;
import com.tcj.domains.User;

public interface AccountManageService {

	Page getList(Map<String, String> param) throws Exception;

	void delete(String flag,Map<String, String> param);

	Object getByUid(String uid) throws Exception;

	void update(User user) throws Exception;

	ResultBean save(User user) throws Exception;

	Object getDetail(String uid);

	List updateDetail(String str,String passwd,String uid) throws EgladServiceException;

	public void resetPwd(String uids,User user);
}
