package com.tcj.work.goodsManage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.Activity;
import com.tcj.domains.Category;
import com.tcj.domains.City;
import com.tcj.domains.Club;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.Pronivce;
import com.tcj.work.clubManage.service.ClubManageService;
import com.tcj.work.goodsManage.service.GoodsSecondTypeManageService;
import com.tcj.work.goodsManage.service.GoodsTypeManageService;

/**
 * @dscription 商品类别数据维护 
 * @author fengrb
 * @date 2016/11/02
 * @version 1.0
 * @history
 */
@Component("goodsSecondTypeManageServiceImpl")
public class GoodsSecondTypeManageServiceImpl implements GoodsSecondTypeManageService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	/*
	 * 查询（条件查询）
	 * 查询商品类别
	 */
	public Page getList(Map<String, String> param) {
		ArrayList al = new ArrayList();
		String clubName = MapUtils.getString(param, "name", "");
		String isDelete = MapUtils.getString(param, "isDelete", "");
		String id = MapUtils.getString(param, "id", "");
		System.out.println("isDelete================"+isDelete);
		System.out.println("id================"+id);
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));

		String sql = "select id,name,remark,sort,image,url,isopen,tag1,tag2,tag3,tag4,tag5,"
				+ "description,"
				+ "create_user,"
				+ "modify_user,"
				+ "date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(modify_time,'%Y-%m-%d %H:%i:%s') as modify_time"	
				+ " from "
				+ "category "
				+ "where 1=1";
		
		
//		判断如果isDelete=1,查询已删除活动,否则查询未删除活动
		if(isDelete.equals("1")){		
			sql += " and is_delete = ?";
		    al.add(CommonDefine.ID_DELETE_TRUE);	
		    System.out.println("查询已删除数据");
		}else{
			sql += " and is_delete = ? ";
			al.add(CommonDefine.ID_DELETE_FALSE);
			System.out.println("查询未删除数据");
		}
		sql+=" and parent_id= ?";
		al.add(id);
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "name", ""))) {
			sql += " and name like ?";
			al.add("%" +clubName + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
	/*
	 * 
	 * 商品类别删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public void delete(Map<String, String> param) {
		// TODO Auto-generated method stubHttpSession session = contxt.getSession();
	
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();

		//模拟登入
//		String user="frb";
//		String createUser="admin";
		String isDelete=MapUtils.getString(param, "isDelete", "");
		System.out.println("isDelete================"+isDelete);
		String ids = param.get("ids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());//删除时间
		ArrayList al = new ArrayList();
		//判断如果isDelete=0,逻辑恢复,否则做逻辑删除
		if(isDelete.equals("0")){
			al.add(CommonDefine.ID_DELETE_FALSE);
		}else{
		    al.add(CommonDefine.ID_DELETE_TRUE);
		}
		al.add(modifytime);
		al.add(user);

		//将传过来的ids分割成子字符串，进行遍历。
		String a [] = ids.split(",");
		for(int i=0;i<a.length;i++){
			al.add(a[i]);
		}
		System.out.println("ids="+ids);
		//遍历数组
		for(int i =0 ; i < al.size(); i ++ ){
			System.out.println("al="+al.get(i));
		}

		Object[] objs = al.toArray();

		System.out.println("objs.length="+objs.length);
		String sql="update Category set is_delete=?,modify_time=?,modify_user=? where id in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);

	}
	/*
	 * 
	 * 社团删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 * @see com.tcj.work.activityManage.service.ActivityManageService#delete(java.util.Map)
	 */
	public void clubPending(Map<String, String> param) {
		// TODO Auto-generated method stubHttpSession session = contxt.getSession();
		
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		
		//模拟登入
//		String user="frb";
//		String createUser="admin";
		String status=MapUtils.getString(param, "status", "");
		System.out.println("status================"+status);
		String ids = param.get("ids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());//删除时间
		ArrayList al = new ArrayList();
		//判断如果status=1,通过审核
			al.add("1");
		al.add(modifytime);
		al.add(user);
		
		//将传过来的ids分割成子字符串，进行遍历。
		String a [] = ids.split(",");
		for(int i=0;i<a.length;i++){
			al.add(a[i]);
		}
		System.out.println("ids="+ids);
		//遍历数组
		for(int i =0 ; i < al.size(); i ++ ){
			System.out.println("al="+al.get(i));
		}
		
		Object[] objs = al.toArray();
		
		System.out.println("objs.length="+objs.length);
		String sql="update club set status=?,modify_time=?,modify_user=? where cid in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
		
	}
	/*
	 * 
	 * 根据Id查询商品类别
	 */
	public Category getById(String id) throws Exception {
		// TODO Auto-generated method stub
		String Hql = "FROM Category WHERE id='" + id + "' ";
		List<Category> list = this.splitPageDao.findByHql(Hql);

		if (list.size() > 0) {
			return (Category) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	/*
	 * 
	 * 商品类别新增、更新
	 */
	public void saveOrUpdate(Category category) throws Exception {
		// TODO Auto-generated method stub
		
        //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
//		
//		模拟登录
//		String user="admin";
		//判断id是否为空，如果为空则操作增加，否则执行更新。
		if(null==category.getId()||"".equals(category.getId())){
			
			category.setCreateTime(DateUtil.newDate());
			category.setModifyTime(new Timestamp(new Date().getTime()));
			category.setCreateUser(user);//session中的创建人
			category.setModifyUser(user);//session中的更新人
			
		}
		else{
			//修改前做查询处理,防止未填写字段致空。
			Category categoryInfo=getById(String.valueOf(category.getId()));
			//要修改的字段存入bean中
            categoryInfo.setName(category.getName());
            categoryInfo.setIsopen(category.getIsopen());
            categoryInfo.setRemark(category.getRemark());
            categoryInfo.setDescription(category.getDescription());
            categoryInfo.setDescription(category.getDescription());
            categoryInfo.setSort(category.getSort());
			categoryInfo.setImage(category.getImage());
			categoryInfo.setParentId(category.getParentId());
			categoryInfo.setUrl(category.getUrl());
			categoryInfo.setTag1(category.getTag1());
			categoryInfo.setTag2(category.getTag2());
			categoryInfo.setTag3(category.getTag3());
			categoryInfo.setTag4(category.getTag4());
			categoryInfo.setTag5(category.getTag5());
			categoryInfo.setModifyTime(new Timestamp(new Date().getTime()));
			categoryInfo.setModifyUser(user);//session中的更新人
			category=categoryInfo;
		}

		//增加、修改前引入逻辑删除(默认否)
		category.setIsDelete(String.valueOf(CommonDefine.ID_DELETE_FALSE));
		splitPageDao.saveOrUpdate(category);
	}
	/*
	 * 
	 * 
	 * 图片上传
	 * @see com.tcj.work.activityManage.service.ActivityManageService#updateImage(java.util.Map)
	 */
	public void updateImage(Map<String, String> param) {
		// TODO Auto-generated method stub
		String cid = MapUtils.getString(param, "cid", "");
		String image = MapUtils.getString(param, "image", "");
		
		System.out.println("Image=================ddd"+image);
		String sql = "UPDATE club set logo=? where cid=?";
		ArrayList al = new ArrayList();
		al.add(image);
		al.add(cid);
		baseDao.executeSql(sql, al.toArray());
	}
}
