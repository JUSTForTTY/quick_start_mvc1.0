package com.tcj.work.accountsManage.service.impl;

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
import com.tcj.common.Encryption;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.MD5utils;
import com.tcj.domains.ClubMember;
import com.tcj.domains.DemoManage;
import com.tcj.domains.User;
import com.tcj.work.accountsManage.service.AccountManageService;
@Component("accountManageServiceImpl")
public class AccountManageServiceImpl implements AccountManageService {
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Override
	/*
	 * @see com.tcj.work.demoManage.service#getList()
	 * 条件查询：条件查询，使用positional parameter
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public Page getList(Map<String, String> param) throws Exception {
		ArrayList al = new ArrayList();
		String username = MapUtils.getString(param, "username", "");
		String realname = MapUtils.getString(param, "realname", "");
		String usertype = MapUtils.getString(param, "usertype", "");
		String status = MapUtils.getString(param, "status", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
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
		//status=1没有被删除的数据
		if(status.equals("1")||status.equals("0")){
			sql += " and a.status = ? ";
			al.add(status);
		}
		   
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "username", ""))) {
			sql += " and a.username like ?";
			al.add("%" + username + "%");
		}
		if (!"".equals(MapUtils.getString(param, "realname", ""))) {
			sql += " and a.realname like ?";
			al.add("%" + realname + "%");
		}
		if (!"".equals(MapUtils.getString(param, "usertype", ""))) {
			sql += " and a.usertype like ?";
			al.add("%" + usertype + "%");
		}
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/**
	 * 删除
	 */
	public void delete(String flag,Map<String, String> param) {
		int status = 0;
		if(flag.equals("1")) status=1;
		String loginId = param.get("loginId").toString();
		String uids = param.get("uids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());
		ArrayList al = new ArrayList();
		al.add(status);
		al.add(loginId);
		al.add(modifytime);
		String a [] = uids.split(",");
		for(int i=0;i<a.length;i++){
			al.add(a[i]);
		}
		System.out.print(uids);
        for(int i =0 ; i < al.size(); i ++ ){
           System.out.println(al.get(i));
}
		
		Object[] objs = al.toArray();
	
		System.out.println(objs.length);
		String sql="update User set   status=?,modify_user=?,modify_time=? where uid in ";
	    sql= new BatchTool().batchDeleteSql(sql, objs);
	    System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
		
	}

	@Override
	public Object getByUid(String uid) throws Exception {
		String hql = " FROM User WHERE uid='" + uid + "' ";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (User) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

	@Override
	public void update(User user) throws Exception{
		String sql = "update User set modify_user=?,modify_time=?,"
				+ "usertype=?,realname=?,mobile=?,email=? where uid=?";
		Object[] objs = {user.getModifyUser(),user.getModifyTime(),user.getUsertype(),
				user.getRealname(),user.getMobile(),user.getEmail(),user.getUid()};
		this.splitPageDao.executeSql(sql, objs);
	}

	@Override
	public ResultBean save(User user) throws Exception {
		ResultBean resultBean = new ResultBean();
		String msg=checkUser(user);
		  if(msg.equals("true")){
			  String sql="CALL pro_getRunningNO('UID')";   
			  String sequence=splitPageDao.findByjkSequence(sql);
			  user.setUid(sequence);
			  user.setCreateUser(sequence);
			  user.setImage("images/newUserCenter/usericon/"+sequence+".png");
			  user.setPrepayC(Encryption.getComfirm(user));
			  this.splitPageDao.saveOrUpdate(user);
			  ClubMember clubMember = new ClubMember();
				clubMember.setCmid(sequence);
				clubMember.setUid(sequence);
				clubMember.setName(user.getUsername());
				clubMember.setCreateTime(new Date());
				clubMember.setModifyTime(new Date());
				clubMember.setBid(user.getBid());
				clubMember.setCreateUser(user.getUsername());
				splitPageDao.saveOrUpdate(clubMember);
				resultBean.setData(user);
				resultBean.setSuccess(true);
				return resultBean;
		  }
		  else{
			  resultBean.setMsg(msg); 
		  }
		  resultBean.setSuccess(false);
		  return resultBean;

		  
	}
	//查重
	private String checkUser(User user){
		//验证用户名
		List<User> list;
		String hql;
		ArrayList al;
		Object[] objs;
		//用户名检测
		hql = "from User n where n.username=?";
		al = new ArrayList();
		al.add(user.getUsername());
		objs = al.toArray();
		list=splitPageDao.query(hql, objs);
		if (list.size()>0)
		return "用户名已存在";
		//电话号码检测
		if(user.getMobile().length()!=11)
			return "请输入11位号码";
		hql = "from User n where n.mobile=?";
		al = new ArrayList();
		al.add(user.getMobile());
		objs = al.toArray();
		list=splitPageDao.query(hql, objs);
		if (list.size()>0)
			return "号码已存在";
		//邮箱检测
		if(!user.getEmail().equals("")){
			if(user.getEmail().contains("@")){
				hql = "from User n where n.email=?";
				al = new ArrayList();
				al.add(user.getEmail());
				objs = al.toArray();
				list=splitPageDao.query(hql, objs);
				if (list.size()>0)
					return "邮箱已存在";
			}
			else{
				return "邮箱格式错误";
			}
			
		}
				
		return true+"";
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
	public List updateDetail(String str,String passwd,String uid) throws EgladServiceException {
		// TODO Auto-generated method stub
		String hql=" from User where uid='"+uid+"' ";
		List list= splitPageDao.findByHql(hql);
		User user=new  User();
	if (list.size() > 0) {
		 user=(User)list.get(0);
	 }else throw new EgladServiceException("并没有这条记录");
	
	
	  if(str!=null&&!str.equals("")){
		   user.setStatus(Integer.parseInt(str));
	  }
	  if(passwd!=null&&!passwd.equals("")){
		    user.setPasswd(MD5utils.MD5Encode(passwd));
	  }
	     splitPageDao.saveOrUpdate(user);
		 return list;
	}

	@Override
	public void resetPwd(String uids,User user) {
		ArrayList al = new ArrayList();
		al.add(user.getPasswd());
		al.add(user.getModifyTime());
		String a [] = uids.split(",");
		for(int i=0;i<a.length;i++){
			al.add(a[i]);
		}
		System.out.print(uids);
        for(int i =0 ; i < al.size(); i ++ ){
           System.out.println(al.get(i));
}
		Object[] objs = al.toArray();
		String sql="update User set passwd=?,modify_time=? where uid in ";
		sql= new BatchTool().batchDeleteSql(sql, objs,2);
	    System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
	}  	
}
