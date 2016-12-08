package com.tcj.work.demoManage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.demoManage.service.DemoManageService;

@Component("demoManageServiceImpl")
public class DemoManageServiceImpl implements DemoManageService {
	
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	/*
	 * @see com.tcj.work.demoManage.service#getList()
	 * 条件查询：条件查询，使用positional parameter
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public Page getList(Map<String, String> param) throws Exception {
		ArrayList al = new ArrayList();
		String username = MapUtils.getString(param, "username", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from DemoManage where 1=1";
		//status=0没有被删除的数据
		   hql += " and status = ?";
		   al.add("0");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "username", ""))) {
			hql += " and username like ?";
			al.add("%" + username + "%");
		}
		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * @see com.tcj.work.demoManage.service#delete()
	 * 删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public void delete(Map<String, String> param) throws Exception {
		int status = 1;
		String loginId = param.get("loginId").toString();
		String ids = param.get("ids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());
		ArrayList al = new ArrayList();
		al.add(status);
		al.add(loginId);
		al.add(modifytime);
		String a [] = ids.split(",");
		for(int i=0;i<a.length;i++){
			al.add(a[i]);
		}
		System.out.print(ids);
        for(int i =0 ; i < al.size(); i ++ ){
           System.out.println(al.get(i));
}
		
		Object[] objs = al.toArray();
	
		System.out.println(objs.length);
		String sql="update t_demo set   status=?,modify_user=?,modify_time=? where id in ";
	    sql= new BatchTool().batchDeleteSql(sql, objs);
	    System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
	
		/*Object[] objs = ids.split(","); 
		splitPageDao.executeSql(sql,objs);*/
	}

	/*
	 * @see com.tcj.work.demoManage.service#getById()
	 * 更具ID查询
	 * 
	 */
	public DemoManage getById(String id) throws Exception {
		String hql = " FROM DemoManage WHERE id='" + id + "' ";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (DemoManage) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

	/*
	 * @see com.tcj.work.demoManage.service#save()
	 * 新增
	 * 使用sequence增加STRING类型的ID
	 */
	public void save(DemoManage demoManage) throws Exception {
		/*Integer id = Integer.valueOf(splitPageDao.findBySequence("select nextval('did')"));
		demoManage.setId(id);*/
		  String sql="CALL pro_getRunningNO('UID')"; 
		  String sequence=splitPageDao.findByjkSequence(sql);
		 demoManage.setId(sequence);
		  this.splitPageDao.saveOrUpdate(demoManage);
	}

	/*
	 * @see com.tcj.work.demoManage.service#update()
	 * 更新
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public void update(DemoManage demoManage) throws Exception {
		String sql = "update t_demo set user_name=?,user_pass=?,user_age=? ,modify_user=?,modify_time=? where id=?";
	
		Object[] objs = { demoManage.getUsername(), demoManage.getUserpass(), demoManage.getUserage(),
				demoManage.getModifyuser(),demoManage.getModifytime(),demoManage.getId() };
		System.out.println(objs.length);
		System.out.println(demoManage.getModifytime());
		this.splitPageDao.executeSql(sql, objs);
		
		 // this.splitPageDao.saveOrUpdate(demoManage);
	}
}