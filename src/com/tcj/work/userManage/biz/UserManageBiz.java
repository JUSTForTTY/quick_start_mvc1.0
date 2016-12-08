package com.tcj.work.userManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.User;

public interface UserManageBiz {

  public  Page getList(Map<String, String> param) throws EgladServiceException;

  public ResultBean deletes(String ids);

public List<User> Exportexcel(Map params);

public ResultBean saveOrUpdate(Map<String, String> map) throws EgladServiceException;

public ResultBean getById(String id);

}
