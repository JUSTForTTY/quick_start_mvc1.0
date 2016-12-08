package com.tcj.work.codemasterManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.CodeMaster;

public interface CodeMaterBiz {
	public Page getList(Map<String, String> param) throws Exception;
	public ResultBean deletes(String ids,String isdelete) throws Exception;
	public ResultBean getById(String id) throws Exception;
	public ResultBean SaveOrUpdate(CodeMaster codeMaster) throws Exception;
	public ResultBean update(Map<String, String> map) throws Exception;
	public List getCodeType() throws Exception;
	public Page getTypeList(Map<String, String> param);
	public ResultBean getTypeById(String id);
	public ResultBean SaveOrUpdatetype(Map<String, String> map);
}
