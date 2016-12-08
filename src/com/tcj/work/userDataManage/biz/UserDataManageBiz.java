package com.tcj.work.userDataManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

public interface UserDataManageBiz {

	public	Page getList(Map<String, String> param);

	public Object getDetail(String uid);

	public ResultBean deletes(String uids, String is_delete);

}
