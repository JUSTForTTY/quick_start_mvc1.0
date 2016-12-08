package com.tcj.work.userDataManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;

public interface UserDataManageService {
 
	
	Page getList(Map<String, String> param);

	Object getDetail(String uid);

	void deletes(String uids, String is_delete);
}
