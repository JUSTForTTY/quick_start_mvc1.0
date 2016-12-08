package com.tcj.work.codemasterManage.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.CodeMaster;
import com.tcj.work.codemasterManage.biz.CodeMaterBiz;

/**
 * CodeMaster 的增删该查
 * @author SRC
 * @date 2016.07.24
 * 
 */
@Controller("codeMasterAction")
@RemoteProxy(name = "codeMasterAction")
public class CodeMasterAction {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("codeMasterBizImpl")
	private CodeMaterBiz codeMaterBiz;
	/**
	 * 获取codemaster列表
	 * @param map 模糊查询条件
	 * @throws Exception 
	 */
	@RemoteMethod
	public Page getList(Map<String , String> param) throws Exception{
		return codeMaterBiz.getList(param);
		
	}
	/**
	 * 获取codeType列表
	 * @param param 模糊查询条件
	 * @return
	 * @throws Exception
	 */
	
	@RemoteMethod
	public Page getTypeList(Map<String, String> param) throws Exception{
		return codeMaterBiz.getTypeList(param);
	}
	/**
	 * 删除
	 * @param ids id组合的字符串
	 */
	@RemoteMethod
	public ResultBean deletes(String ids,String isdelete) throws Exception{
		System.out.println("ids:"+ids+" isdelete:"+isdelete);
		return  codeMaterBiz.deletes(ids,isdelete);
	}
	/**
	 * 获取codemaster对象
	 * @param id :codeMaster_ID
	 * @throws Exception 
	 */
	@RemoteMethod
	public ResultBean getById(String id) throws Exception{
		return codeMaterBiz.getById(id);
		
	}
	/**
	 * 获取codetype对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public ResultBean getTypeById(String id) throws Exception{
		return codeMaterBiz.getTypeById(id);
		
	}
	/**
	 * save
	 * @param CodeMaster 
	 * @throws Exception 
	 */
	@RemoteMethod
	public ResultBean SaveOrUpdate (CodeMaster codeMaster)throws Exception{
		return codeMaterBiz.SaveOrUpdate(codeMaster);
		
	}
	/**
	 * update
	 * @param map
	 * @throws Exception 
	 */
	@RemoteMethod
	public ResultBean update (Map<String, String> map)throws Exception{
		return codeMaterBiz.update(map);
		
	}
	/**
	 * 获取codetype下拉列表
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public List getCodeType()throws Exception{
		return codeMaterBiz.getCodeType();
		
	}
	/**
	 * codetype saveorupdate
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RemoteMethod
	public ResultBean SaveOrUpdatetype(Map<String, String> map) throws Exception{
		System.out.println("SaveOrUpdatetype:"+map);
		return codeMaterBiz.SaveOrUpdatetype(map);
		
	}
	
}
