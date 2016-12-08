package com.tcj.work.userDataManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.EnumUserStatus;
import com.tcj.work.userDataManage.service.UserDataManageService;
@Component("userDataManageServiceImpl")
public class UserDataManageServiceImpl implements  UserDataManageService{
  
	
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	
	@Override
	public Page getList(Map<String, String> param) {
		// TODO Auto-generated method stub
		
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));	
		String username = MapUtils.getString(param, "username","");
		String realname = MapUtils.getString(param, "realname","");
		String usertype = MapUtils.getString(param, "usertype","");
		String status = MapUtils.getString(param, "status","");
		String sql = " select   a.uid,a.bid,a.company_id,a.member_id, "
				+ "    b.province,c.city, "
				+ "    d.username as d_uname ,e.CodeName as e_status,f.CodeName as f_userType, "
				+ "    g.building_name as g_building_name,  "
				+ "    a.username,a.passwd,a.userquestion,a.useranswer,a.realname,a.englishname,a.nickname, "
				+ "    a.gender,a.tel,a.fax,a.email,a.msn,a.qq,a.mobile,a.postcode, "
				+ "    a.address,a.credentialtype,a.credential,a.contactor, "
				+ "    a.prepay,a.prepay_c,a.score,  "
				+ "    date_format(a.regtime,'%Y-%m-%d %H:%i:%s') as  a_regtime ,a.level,"
				+ "    date_format(a.birthday,'%Y-%m-%d') as a_birthday,a.usertype, "
				+ "    a.status,a.creator_id,a.group_id,a.recuser_id,a.recusername,a.cardId,a.openId, "
				+ "    a.create_user,"
				+ "    date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as a_create_time, "
				+ "    a.modify_user,"
				+ "    date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as a_modify_time,a.image, "
				+ "    date_format(a.taking_time,'%Y-%m-%d %H:%i:%s') as a_taking_time,a.inviteCode,a.proviceId,a.cityId,a.payword "
				+ "    from  user   a "
				+ "    left join  province  b  on  a.proviceId=b.id "
				+ "    left join  city      c  on  a.cityId=c.id "
				+ "    left join  user      d  on  a.recuser_id=d.uid  "
				+ "    left join  codemaster e on  a.status=e.CodeNo  and e.CodeType=122 and e.is_delete=0 "
				+ "    left join  codemaster f on  a.usertype=f.CodeNo  and f.CodeType=121 and f.is_delete=0 "
				+ "    left join  building  g on  a.bid=g.id      and   g.status=1   "
				+ "    where  1=1  ";
		
		ArrayList al = new ArrayList();
		
		
	if (status!=null&&!"".equals(status)&&!status.equals("2")) {
		//status=1没有被删除的数据
		   sql += " and a.status = ? ";
		   al.add(status);
		// 判断是否为空，动态增加hql语句，动态增加ArrayList	   
	}
	if (usertype!=null&&!"".equals(usertype)) {
		//status=1没有被删除的数据
		   sql += " and a.usertype = ? ";
		   al.add(usertype);
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
	}
    if (!"".equals(MapUtils.getString(param, "username",""))) {
			sql += " and a.username like ?";
			al.add("%" + username + "%");
		}
    if (!"".equals(MapUtils.getString(param, "realname",""))) {
			sql += " and a.realname like ?";
			al.add("%" + realname + "%");
		}
    System.out.println("##"+sql);
		Object[] objs = al.toArray();
		return  splitPageDao.findBySql(sql, objs, pgNumber, pgSize);
	}
	@Override
	public Object getDetail(String uid) {
		// TODO Auto-generated method stub
		String sql = " select   a.uid,a.bid,a.company_id,a.member_id, "
				+ "    b.province,c.city, "
				+ "    d.username as d_uname ,e.CodeName as e_status,f.CodeName as f_userType, "
				+ "    g.building_name as g_building_name,  "
				+ "    a.username,a.passwd,a.userquestion,a.useranswer,a.realname,a.englishname,a.nickname, "
				+ "    a.gender,a.tel,a.fax,a.email,a.msn,a.qq,a.mobile,a.postcode, "
				+ "    a.address,a.credentialtype,a.credential,a.contactor, "
				+ "    a.prepay,a.prepay_c,a.score,  "
				+ "    date_format(a.regtime,'%Y-%m-%d %H:%i:%s') as  a_regtime ,a.level,"
				+ "    date_format(a.birthday,'%Y-%m-%d') as a_birthday,a.usertype, "
				+ "    a.status,a.creator_id,a.group_id,a.recuser_id,a.recusername,a.cardId,a.openId, "
				+ "    a.create_user, "
				+ "    date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as a_create_time, "
				+ "    a.modify_user,"
				+ "    date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as a_modify_time,a.image, "
				+ "    date_format(a.taking_time,'%Y-%m-%d %H:%i:%s') as a_taking_time,a.inviteCode,a.proviceId,a.cityId,a.payword "
				+ "    from  user   a "
				+ "    left join  province  b  on  a.proviceId=b.id "
				+ "    left join  city      c  on  a.cityId=c.id "
				+ "    left join  user      d  on  a.recuser_id=d.uid  "
				+ "    left join  codemaster e on  a.status=e.CodeNo  and e.CodeType=122 and e.is_delete=0 "
				+ "    left join  codemaster f on  a.usertype=f.CodeNo  and f.CodeType=121 and f.is_delete=0 "
				+ "    left join  building  g on  a.bid=g.id      and   g.status=1   "
				+ "    where  1=1  ";
		
		ArrayList al = new ArrayList();
		
		if (uid!=null&&!"".equals(uid)) {			
			   sql += " and a.uid = ? ";
			   al.add(uid);			  
	}
		 System.out.println("uid="+uid+"@@"+sql);
			Object[] objs = al.toArray();
			List list = splitPageDao.findBySql(sql, objs);
		return list.get(0);
	}
	@Override
	public void deletes(String uids,String is_delete) {
		// TODO Auto-generated method stub
		String sql =" update user set status="+is_delete+" where uid in("+uids+") ";
		/*String hql="delete   from codemaster  where id in("+ids+")";
		splitPageDao.excuteSql(hql);*/
		 System.out.println("uids="+uids+"@@"+sql);
		splitPageDao.excuteSql(sql);	
	}   		
}
