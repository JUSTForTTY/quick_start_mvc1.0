package com.tcj.work.memberManage.service;

import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

import com.tcj.domains.Provider;
import com.tcj.domains.User;

public interface MemberManageService {

	Page getMemberList(Map<String, String> map);

	void saveOrUpdate(Provider member) throws Exception;

	public Provider getByid(Integer id) throws Exception;

	void updateLoadImage(Map param);

	void delete(Map<String, String> param);

	Page getMemberList1(Map<String, String> map);

	Page getMemberList2(Map<String, String> map);

	void saveOrUpdate1(Provider member,User user)throws Exception;

	void saveOrUpdate2(Provider member,User user) throws Exception;

	public Provider  getBymemberid(Integer id) throws Exception;

	

	
}
