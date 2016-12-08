package com.tcj.work.messageManage.biz;

import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.model.Page;

public interface MessageManageBiz {

	Page getListComment(Map<String, String> param) throws EgladServiceException;

	Page getListMessage(Map<String, String> param) throws EgladServiceException;

}
