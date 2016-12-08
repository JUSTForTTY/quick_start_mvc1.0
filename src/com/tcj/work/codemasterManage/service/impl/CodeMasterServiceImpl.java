package com.tcj.work.codemasterManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.CodeMaster;
import com.tcj.domains.CodeType;
import com.tcj.domains.DemoManage;
import com.tcj.work.codemasterManage.service.CodeMasterService;
@Component("codeMasterServiceImpl")
public class CodeMasterServiceImpl implements CodeMasterService{
	
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	/*
	 * (non-Javadoc)
	 * @see com.tcj.work.codemasterManage.service.CodeMasterService#getList(java.util.Map)
	 * 条件查询
	 */
	@Override
	public Page getList(Map<String, String> param) throws Exception {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20)); 
		String ctdescription=MapUtils.getString(param, "ctdescription", "");
		String codename=MapUtils.getString(param, "codename", "");
		String codetype=MapUtils.getString(param, "codetype", "");
		String is_delete = MapUtils.getString(param, "is",""+CommonDefine.ID_DELETE_FALSE);
		//String hql="from CodeMaster where 1=1 and is_delete=? ";
		String sql="select cm.id,cm.CodeType as codetype ,ct.codedescription as ctdescription,cm.codeno,"
				+ " cm.codename,cm.codedescription"
				+ " from CodeMaster cm,CodeType ct "
				+ " where cm.codetype=ct.codetype "
				+ " and cm.is_delete=? ";
		al.add(is_delete);
		if(!"".equals(ctdescription)){
			sql+=" and ct.codedescription like ?";
			al.add("%" + ctdescription + "%");
		}
		if(!"".equals(codename)){
			sql+=" and cm.codename like ?";
			al.add("%" + codename + "%");
		}
		if(!"".equals(codetype)&&!"all".equals(codetype)){
			sql+=" and cm.CodeType like ?";
			al.add("%" + codetype + "%");
		}
		sql+=" order by cm.codetype,cm.codeno asc ";
		//?参数对象数组
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}
	/*
	 * 批量删除
	 */
	public void deletes(String ids,String isdelete) throws Exception{
		String sql ="update codemaster set is_delete="+isdelete+" where id in("+ids+")";
		/*String hql="delete   from codemaster  where id in("+ids+")";
		splitPageDao.excuteSql(hql);*/
		splitPageDao.excuteSql(sql);
	}
	/*
	 * (non-Javadoc)
	 * @see com.tcj.work.codemasterManage.service.CodeMasterService#getById(java.lang.String)
	 *	根据ID 找对象
	 */
	@Override
	public CodeMaster getById(String id) throws Exception {
		// TODO Auto-generated method stub
		String hql=" from CodeMaster where id ='"+id+"'";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (CodeMaster) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	@Override
	public void SaveOrUpdate(CodeMaster codeMaster) throws Exception {
		// TODO Auto-generated method stub
		if(codeMaster.getId()!=null){
			
		}else{
			 String sql="CALL pro_getRunningNO('CODEID')"; 
			  String sequence=splitPageDao.findByjkSequence(sql);
			  codeMaster.setId(sequence);
		}
		 
		  
		  
		  this.splitPageDao.saveOrUpdate(codeMaster);
	}
	@Override
	public void update(CodeMaster codeMaster) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update codemaster set CodeType=?,CodeNo=?,CodeName=? ,CodeDescription=? where id=?";
		
		Object[] objs = {codeMaster.getCodetype(),codeMaster.getCodeno(),codeMaster.getCodename(),codeMaster.getCodedescription(),codeMaster.getId() };

		this.splitPageDao.executeSql(sql, objs);
	}
	@Override
	public List getCodeType() throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT codetype AS VAL,"
				+ "codeDescription AS TEXT FROM "
				+ " codetype ";
		return splitPageDao.findBySql(sql);
	}
	/**
	 * 验证小种类是否重复
	 */
	@Override
	public boolean IsNotrue(CodeMaster codeMaster) throws Exception {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String hql="from CodeMaster where codetype=? and codeno =?";
		al.add(codeMaster.getCodetype());
		al.add(codeMaster.getCodeno());
		if(codeMaster.getId()!=null){
			hql+=" and id !=?";
			al.add(codeMaster.getId());
		}
		//?参数对象数组
		Object[] objs = al.toArray();
		List list = splitPageDao.query(hql, objs);
		if(list.size()>0){
			return false;
		}
		return true;
	}
	@Override
	public Page getTypeList(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20)); 
		String codetype=MapUtils.getString(param, "codetype", "");
		String codedescription=MapUtils.getString(param, "codedescription", "");
		String hql="from CodeType where 1=1 ";
		if(!"".equals(codetype)){
			hql+=" and codetype like ?";
			al.add("%" + codetype + "%");
		}
		if(!"".equals(codedescription)){
			hql+=" and codeDescription like ?";
			al.add("%" + codedescription + "%");
		}
		hql+=" order by codetype asc ";	
		//?参数对象数组
		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}
	@Override
	public CodeType getTypeById(String id) throws EgladServiceException {
		// TODO Auto-generated method stub
		String hql=" from CodeType where id ='"+id+"'";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (CodeType) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	@Override
	public boolean IsEcho(CodeType codeType) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String hql=" from CodeType where codetype =?";
		al.add(codeType.getCodetype());
		if(codeType.getId()!=null){
			hql+=" and id !=?";
			al.add(codeType.getId());
		}
		//?参数对象数组
		Object[] objs = al.toArray();
		List list = splitPageDao.query(hql, objs);
		if(list.size()>0){
			return false;
		}
		return true;
	}
	@Override
	public void updateType(CodeType codeType) {
		// TODO Auto-generated method stub
		splitPageDao.saveOrUpdate(codeType);
	}
	
	
}
