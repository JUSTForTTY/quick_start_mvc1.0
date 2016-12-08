package com.tcj.work.codemasterManage.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.CodeMaster;
import com.tcj.domains.CodeType;

public interface CodeMasterService {
	public Page getList(Map<String, String> param) throws Exception;
	public void deletes(String ids,String isdelete) throws Exception;
	public CodeMaster getById(String id) throws Exception;
	public void SaveOrUpdate(CodeMaster codeMaster)throws Exception;
	public void update(CodeMaster codeMaster)throws Exception;
	public List getCodeType() throws Exception;
	public boolean IsNotrue(CodeMaster codeMaster) throws Exception;
	public Page getTypeList(Map<String, String> param);
	public CodeType getTypeById(String id)throws Exception;
	public boolean IsEcho(CodeType codeType);
	public void updateType(CodeType codeType);
}
