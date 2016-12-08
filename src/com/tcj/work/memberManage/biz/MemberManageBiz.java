package com.tcj.work.memberManage.biz;

import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

public interface MemberManageBiz {

	Page getPageList(Map<String, String> map);

	ResultBean saveOrupdate(Map map);

	ResultBean getByid(Integer id);

	ResultBean delete(String id);



	ResultBean getBymemberid(Integer id);



}
