package com.tcj.work.codemasterManage.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.CodeMaster;
import com.tcj.domains.CodeType;
import com.tcj.work.codemasterManage.biz.CodeMaterBiz;
import com.tcj.work.codemasterManage.service.CodeMasterService;


@Component("codeMasterBizImpl")
public class CodeMasterBizImpl implements CodeMaterBiz{
	@Autowired
	@Qualifier("codeMasterServiceImpl")
	private CodeMasterService codeMasterService;
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) throws Exception {
		// TODO Auto-generated method stub
		return codeMasterService.getList(param);
	}
	/*
	 * 删除(批量删除)
	 */
	@RemoteMethod
	public ResultBean deletes(String ids,String isdelete){
		ResultBean resultBean = new ResultBean();
		try {
			codeMasterService.deletes(ids,isdelete);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
		
	}
	@Override
	public ResultBean getById(String id) throws Exception {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(codeMasterService.getById(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	@Override
	public ResultBean SaveOrUpdate(CodeMaster codeMaster) throws Exception {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			if(codeMasterService.IsNotrue(codeMaster)){
				codeMaster.setIs_delete(CommonDefine.ID_DELETE_FALSE);
				codeMasterService.SaveOrUpdate(codeMaster);
				resultBean.setSuccess(true);
			}else{
				resultBean.setSuccess(false);
				resultBean.setMsg("此类型下种类编号已被占用或已被删除请至恢复页面进行恢复");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	@Override
	public ResultBean update(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			CodeMaster codeMaster = new CodeMaster();
			codeMaster.setId(MapUtils.getString(map, "id"));
			codeMaster.setCodetype(MapUtils.getInteger(map, "codetype"));
			codeMaster.setCodeno(MapUtils.getString(map, "codeno"));
			codeMaster.setCodename(MapUtils.getString(map, "codename"));
			codeMaster.setCodedescription(MapUtils.getString(map, "codedescription"));
			if(codeMasterService.IsNotrue(codeMaster)){
				codeMasterService.update(codeMaster);
				resultBean.setSuccess(true);
			}else{
				resultBean.setSuccess(false);
				resultBean.setMsg("此类型下种类编号已被占用或已被删除请至恢复页面进行恢复");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	@Override
	public List getCodeType() throws Exception {
		// TODO Auto-generated method stub
		return codeMasterService.getCodeType();
	}
	@Override
	public Page getTypeList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return codeMasterService.getTypeList(param);
	}
	@Override
	public ResultBean getTypeById(String id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(codeMasterService.getTypeById(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	@Override
	public ResultBean SaveOrUpdatetype(Map<String, String> map) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			CodeType codeType = new CodeType();
			codeType.setCodetype(""+MapUtils.getInteger(map, "codetype"));
			if(!("").equals(MapUtils.getString(map, "id",""))){
				codeType.setId(MapUtils.getInteger(map, "id"));
			}
			codeType.setCodedescription(MapUtils.getString(map, "codedescription"));
			if(codeMasterService.IsEcho(codeType)){
				codeMasterService.updateType(codeType);
				resultBean.setSuccess(true);
			}else{
				resultBean.setSuccess(false);
				resultBean.setMsg("此代码类型编号重复");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

}
