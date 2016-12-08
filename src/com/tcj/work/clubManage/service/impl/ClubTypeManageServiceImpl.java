package com.tcj.work.clubManage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.Club;
import com.tcj.domains.ClubType;
import com.tcj.domains.LoginEntity;
import com.tcj.work.clubManage.service.ClubTypeManageService;

@Component("clubTypeManageServiceImpl")
public class ClubTypeManageServiceImpl implements ClubTypeManageService {

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;
	
	@Override
	public Page getList(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String name = MapUtils.getString(param, "name", "");
		System.out.println("name============="+name);
		String isDelete = MapUtils.getString(param, "isDelete", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		
		String sql = "select id,club_type,create_user,description,is_delete,modify_user, "
				+ "date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(modify_time,'%Y-%m-%d %H:%i:%s') as modify_time "
				+ "from club_type where 1=1 ";
				
			sql += " and is_delete = ?";
			
			if(isDelete.equals("1")){				
			    al.add(CommonDefine.ID_DELETE_TRUE);	
			    System.out.println("查询已删除数据");
			}else{
		        al.add(CommonDefine.ID_DELETE_FALSE);	
		        System.out.println("查询未删除数据");
	        }
			
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		    if (!"".equals(MapUtils.getString(param, "name", ""))) {
			    sql += " and description like ?";
			    al.add("%" +name + "%");
		    }
		        System.out.println("sql=============="+sql);
	          	Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}

	/*
	 * 
	 * 社团类别删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 * @see com.tcj.work.activityManage.service.ActivityManageService#delete(java.util.Map)
	 */
	public void delete(Map<String, String> param) {
		// TODO Auto-generated method stubHttpSession session = contxt.getSession();
		  //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
//		
//		模拟登录
//		String user="admin";
		
		String ids = param.get("ids").toString();
		System.out.println("ids===="+ids);
		Date  modifytime=new Timestamp(new Date().getTime());//删除时间
		String isdelete = param.get("isDelete");
		System.out.println("isDelete===="+isdelete);
		ArrayList al = new ArrayList();
		//判断如果isDelete=0,逻辑恢复,1做逻辑删除
		if("0".equals(isdelete)){
	    al.add(CommonDefine.ID_DELETE_FALSE);
	    System.out.println("恢复类别");
		}else{
			al.add(CommonDefine.ID_DELETE_TRUE);
			 System.out.println("删除类别");
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
		String sql="update club_type set is_delete=?,modify_time=?,modify_user=? where id in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);

	}

	@Override
	public void saveOrUpdate(ClubType clubType) throws EgladServiceException {
		// TODO Auto-generated method stub
	      //获取session用户信息
			WebContext contxt = WebContextFactory.get();
			HttpSession session=contxt.getSession();
			LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
			String user = loginEntity.getUserName();
//			
//			模拟登录
//			String user="admin";
			//判断aid是否为空，如果为空则操作增加，否则执行更新。
			if(null==clubType.getId()||"".equals(clubType.getId())){				
				clubType.setCreateTime(DateUtil.newDate());
				clubType.setModifyTime(new Timestamp(new Date().getTime()));
				clubType.setCreateUser(user);//session中的创建人
				clubType.setModifyUser(user);//session中的更新人
			}
			else{
				//修改前做查询处理,防止未填写字段致空。
				ClubType clubTypeInfo=getById(clubType.getId().toString());
				//要修改的字段存入bean中       
				clubTypeInfo.setClubType(clubType.getClubType());
				clubTypeInfo.setDescription(clubType.getDescription());
				clubTypeInfo.setModifyTime(new Timestamp(new Date().getTime()));
				clubTypeInfo.setModifyUser(user);//session中的更新人
				clubType=clubTypeInfo;
			}

			//增加、修改前引入逻辑删除(默认否)
			clubType.setIsDelete(CommonDefine.ID_DELETE_FALSE);

			splitPageDao.saveOrUpdate(clubType);
	}

	/*
	 * 根据社团类别id查询
	 * 
	 */
	public ClubType getById(String id) throws EgladServiceException {
		// TODO Auto-generated method stub
		String Hql = "FROM ClubType WHERE Id='" + id + "' ";
		List<ClubType> list = this.splitPageDao.findByHql(Hql);

		if (list.size() > 0) {
			return (ClubType) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

}
