package com.tcj.work.demoManage.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;

public interface DemoManageBiz {

	public Page getList(Map<String, String> param);

	public ResultBean delete(String ids);

	public ResultBean getById(String paramString);

	public ResultBean save(Map<String, String> map);

	public ResultBean update(Map<String, String> ma);

}