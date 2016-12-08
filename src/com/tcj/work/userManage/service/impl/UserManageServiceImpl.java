package com.tcj.work.userManage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.Activity;
import com.tcj.domains.Club;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.domains.User;
import com.tcj.work.messageManage.service.MessageManageService;
import com.tcj.work.userManage.service.UserManageService;
@Component("userManageServiceImpl")
public class UserManageServiceImpl  implements UserManageService{
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	
	@Autowired()
	@Qualifier("messageManageServiceImpl")
	private MessageManageService messageManageService;
	
	/*
	 * 根据角色查询：'0'超级管理员;'1'快车网管理员;'2'会员单位管理;
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		
		ArrayList al = new ArrayList();
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String status = MapUtils.getString(param, "status");
		System.out.println("status========="+status);
		String username = MapUtils.getString(param, "username");
		String realname = MapUtils.getString(param, "realname");
		String serchUserType = MapUtils.getString(param, "serchUserType");
		String providername = MapUtils.getString(param, "providername");
		Integer userType = Integer.valueOf(MapUtils.getString(param, "userType"));
		String providerId = MapUtils.getString(param, "providerId");
		String sql = "select a.id as userId,a.username,a.provider_id,a.usertype,a.realname,"
				+ "a.englishname,a.nickname,a.gender,a.tel,a.fax,a.email,a.msn,a.qq,a.mobile,a.postcode,"
				+ "a.address,a.credentialtype,a.credential,a.contactor,a.prepay,a.score,a.regtime,a.level,"
				+ "a.birthday,a.createtime,a.status as userstatus,b.name as providername "
				+ " from user a inner join provider b on a.provider_id=b.id "
				+ " where ";
			if(userType==0){
				sql+="1=1";
			}else if(userType==1){
//				sql+="a.provider_id="+"'"+-1+"'";
				sql+="1=1";
			}else if(userType==2){
				sql+="a.provider_id="+providerId;
			}
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "username"))) {
			sql += " and a.username like ?";
			al.add("%" + username + "%");
		}
		if (!"".equals(MapUtils.getString(param, "realname"))) {
			sql += " and a.realname like ?  ";
			al.add("%" + realname + "%");
		}
		if (!"".equals(MapUtils.getString(param, "providername"))) {
			sql += " and b.name like ?  ";
			al.add("%" + providername + "%");
		}
		if (!"".equals(MapUtils.getString(param, "serchUserType"))) {
			sql += " and a.usertype like ?  ";
			al.add("%" + serchUserType + "%");
		}
		//默认查询为删除
		if ("".equals(MapUtils.getString(param, "status"))) {
			sql += " and a.status=1 ";
			System.out.println("默认查询未删除");
		}
		//条件查询用户状态
		if (!"".equals(MapUtils.getString(param, "status"))) {
			sql += " and a.status="+status;
			System.out.println("条件查询");
		}
		sql+=" order by a.id desc ";	
		
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	@Override
	public List<User> Exportexcel(Map params) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
	//	String username=MapUtils.getString(params, "username");
		String hql="from User where 1=1";
//		if (!"".equals(MapUtils.getString(params, "username"))) {
//			hql += " and username like ?";
//			al.add("%" + username + "%");
//		}
		
		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql);
	}

	@Override
	public void saveOrUpdate(User user) throws EgladServiceException {
		// TODO Auto-generated method stub
		
		//判断aid是否为空，如果为空则操作增加，否则执行更新。
		if(null==user.getId()||"".equals(user.getId())){
			user.setCreateTime(DateUtil.newDate());
			user.setRegtime(DateUtil.newDate());
			user.setModifyTime(new Timestamp(new Date().getTime()));
		}
		else{
			//修改前做查询处理,防止未填写字段致空。
			User userInfo=messageManageService.getUserInfoById(user.getId().toString());
			System.out.println("注册时间"+userInfo.getRegtime());
			//要修改的字段存入+bean中
			System.out.println("id====d======="+userInfo.getId());
			userInfo.setAddress(user.getAddress());
			userInfo.setGender(user.getGender());
			userInfo.setRealname(user.getRealname());
			userInfo.setUsertype(user.getUsertype());
			userInfo.setUsername(user.getUsername());
            userInfo.setProviderId(user.getProviderId());
            userInfo.setModifyTime(new Timestamp(new Date().getTime()));
			user=userInfo;
		}

		user.setCredentialtype("0");
		user.setStatus(1);
        user.setPrepay("0.00");
        user.setScore(0);
        user.setOpenid("0");
		splitPageDao.saveOrUpdate(user);
	}

	@Override
	public Object getById(String id) throws EgladServiceException {
		// TODO Auto-generated method stub
//		String Hql = "FROM User WHERE id='" + id + "' ";
		String sql = "select a.id as userId,a.username,a.provider_id,a.usertype,a.realname,"
				+ "a.englishname,a.nickname,a.gender,a.tel,a.fax,a.email,a.msn,a.qq,a.mobile,a.postcode,"
				+ "a.address,a.credentialtype,a.credential,a.contactor,a.prepay,a.score,a.regtime,a.level,"
				+ "a.birthday,a.createtime,a.status as userstatus,a.passwd as password,b.id as providerId,b.name as providername "
				+ " from user a inner join provider b on a.provider_id=b.id "
				+ " where a.id="+id;
		List<User> list = this.splitPageDao.findBySql(sql);

		if (list.size() > 0) {
			return  list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

	@Override
	public void deletes(Map<String, String> param) {
		// TODO Auto-generated method stub
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		String ids = param.get("ids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());
		ArrayList al = new ArrayList();
		al.add(0);////删除标记
		al.add(modifytime);//删除时间
		al.add(user);//删除人

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
		String sql="update user set status=?,modify_time=?,modify_user=? where id in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
	}

	
}
