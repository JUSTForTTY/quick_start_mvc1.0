package com.tcj.work.messageManage.service;

import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.User;

public interface MessageManageService {
	
	User getUserInfoById(String userId) throws EgladServiceException;
	Page getListMessage0(Map<String, String> param) throws EgladServiceException;
	Page getListMessage1(Map<String, String> param) throws EgladServiceException;
	Page getListMessage2(Map<String, String> param) throws EgladServiceException;

	Page getListComment0(Map<String, String> param) throws EgladServiceException;
	Page getListComment1(Map<String, String> param) throws EgladServiceException;
	Page getListComment2(Map<String, String> param) throws EgladServiceException;

}
