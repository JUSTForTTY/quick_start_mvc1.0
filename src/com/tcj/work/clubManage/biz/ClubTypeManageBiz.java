package com.tcj.work.clubManage.biz;

import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.ClubType;

public interface ClubTypeManageBiz {

	Page getList(Map<String, String> param);

	ResultBean delete(String ids, String isDelete);

	ResultBean saveOrUpdate(Map<String, String> map);

	ResultBean getById(String id);

}
