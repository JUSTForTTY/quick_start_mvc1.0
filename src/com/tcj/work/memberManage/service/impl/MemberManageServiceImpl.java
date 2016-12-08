package com.tcj.work.memberManage.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.tcj.domains.LoginEntity;

import com.tcj.domains.News;
import com.tcj.domains.Provider;
import com.tcj.domains.User;
import com.tcj.work.memberManage.service.MemberManageService;
@Component("memberManageServiceImpl")
public class MemberManageServiceImpl implements MemberManageService {

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.memberManage.service.MemberManageService#getMemberList(java.util.Map)
	 * 超管看一切得会员单位
	 */
	@Override
	public Page getMemberList(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList al=new ArrayList();
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		String name=MapUtils.getString(map, "name");
		System.out.println("name为页面传过来的+++==="+name);
        Integer id=MapUtils.getInteger(map, "id");
        System.out.println("id==="+id);
		String title=MapUtils.getString(map, "title");
		System.out.println("title为页面传过来的+++==="+title);
		String status=MapUtils.getString(map, "status");
		System.out.println("status为页面传过来的+++==="+status);
		String sql="select id as id , name as name,title as title ,address as address,longitude as longitude,latitude as latitude,memberUrl as memberUrl, detailintro as detailintro,miniature as miniature,image1 as image1,type as type, status as status,date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time,date_format(update_time,'%Y-%m-%d %H:%i:%s') as update_time,create_user as create_user ,update_user as update_user from provider where 1=1";
		System.out.println("sql===="+sql);
		if(!"".equals(name)){
			sql+=" and  name like ?";
			al.add("%"+name +"%");
		}	


		if(!"".equals(title)){
			sql+=" and title like ?";
			al.add("%"+title +"%");
		}	

		if(!"".equals(status)){
			sql+=" and  status = ?";
			al.add(status);
		}	



		sql+=" order by id desc";
		System.out.println("所有运行完的sql==="+sql);
		Object[] objs=al.toArray();
		return splitPageDao.findBySql(sql,objs,pgNumber.intValue(),pgSize.intValue());
	}


	/**
	 * 快车网的会员单位
	 * 
	 * 
	 */

	@Override
	public Page getMemberList1(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList al=new ArrayList();
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		String name=MapUtils.getString(map, "name");
		System.out.println("name为页面传过来的+++==="+name);
		String title=MapUtils.getString(map, "title");
		System.out.println("title为页面传过来的+++==="+title);
		String status=MapUtils.getString(map, "status");
		System.out.println("status为页面传过来的+++==="+status);
		String sql="select id as id , name as name,title as title ,address as address,longitude as longitude,latitude as latitude,memberUrl as memberUrl, detailintro as detailintro,miniature as miniature,image1 as image1,type as type, status as status,date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time,date_format(update_time,'%Y-%m-%d %H:%i:%s') as update_time,create_user as create_user ,update_user as update_user from provider where 1=1";
		System.out.println("sql===="+sql);
		if(!"".equals(name)){
			sql+=" and  name like ?";
			al.add("%"+name +"%");
		}	


		if(!"".equals(title)){
			sql+=" and title like ?";
			al.add("%"+title +"%");
		}	

		if(!"".equals(status)){
			sql+=" and  status = ?";
			al.add(status);
		}	
		sql+=" order by id desc";
		System.out.println("所有运行完的sql==="+sql);
		Object[] objs=al.toArray();
		return splitPageDao.findBySql(sql,objs,pgNumber.intValue(),pgSize.intValue());
	}

	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.memberManage.service.MemberManageService#getMemberList2(java.util.Map)
	 * 
	 * 会员单位只能看自己的
	 * 
	 * 
	 */



	@Override
	public Page getMemberList2(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList al=new ArrayList();
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		String name=MapUtils.getString(map, "name");
		System.out.println("name为页面传过来的+++==="+name);
		String title=MapUtils.getString(map, "title");
		System.out.println("title为页面传过来的+++==="+title);
		String status=MapUtils.getString(map, "status");
		System.out.println("status为页面传过来的+++==="+status);
		Integer providerId=MapUtils.getInteger(map, "providerId");
		System.out.println("BizImpl传过来的providerId+++==="+providerId);
		String sql="select id as id , name as name,title as title ,address as address,longitude as longitude,latitude as latitude,memberUrl as memberUrl, detailintro as detailintro,miniature as miniature,image1 as image1,type as type, status as status,date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time,date_format(update_time,'%Y-%m-%d %H:%i:%s') as update_time,create_user as create_user ,update_user as update_user from provider where id="+providerId ;
		System.out.println("sql===="+sql);
		if(!"".equals(name)){
			sql+=" and  name like ?";
			al.add("%"+name +"%");
		}	
		if(!"".equals(title)){
			sql+=" and title like ?";
			al.add("%"+title +"%");
		}
		sql+=" order by id desc";
		System.out.println("所有运行完的sql==="+sql);
		Object[] objs=al.toArray();
		return splitPageDao.findBySql(sql,objs,pgNumber.intValue(),pgSize.intValue());
	}
	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.memberManage.service.MemberManageService#saveOrUpdate(com.tcj.domains.Provider)
	 *超管管所有
	 *
	 */

	@Override
	public void saveOrUpdate(Provider member) throws Exception {
		// TODO Auto-generated method stub
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		if (null == member.getId() || "".equals(member.getId())) {
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);

			// String title=news.getTitle().replaceAll("", "");

			member.setCreateTime(DateUtil.newDate());

			member.setDetailintro(member.getDetailintro());
			//	System.out.println("你好，大世界简介" + news.getBriefintro());
			//	news.setNid(sequence);
			member.setType("provider");
			//	news.setBid(CommonDefine.JK_BUILDING);
			member.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));

			member.setCreateUser(user);//session中的创建人
			//	member.setUpdateUser(user);//session中的更新人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			Provider newsdata = getByid(member.getId());
			newsdata.setName(member.getName());
			newsdata.setTitle(member.getTitle());
			newsdata.setAddress(member.getAddress());
			newsdata.setLongitude(member.getLongitude());
			newsdata.setLatitude(member.getLatitude());
			newsdata.setMemberurl(member.getMemberurl());
			newsdata.setDetailintro(member.getDetailintro());
			newsdata.setImage1(member.getImage1());
			newsdata.setMiniature(member.getMiniature());
			newsdata.setUpdateTime(DateUtil.newDate());
			newsdata.setId(member.getId());
			System.out.println("updatetime====="+(DateUtil.newDate()));
			//newsdata.setModifyTime(DateUtil.newDate());
			newsdata.setUpdateUser(user);//session中的更新人
			member = newsdata;
		}
		splitPageDao.saveOrUpdate(member);

	}
	/*
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.memberManage.service.MemberManageService#saveOrUpdate1(com.tcj.domains.Provider)
	 * 
	 * 物业快车网只对自己的会员单位进行修改
	 * 
	 */

	@Override
	public void saveOrUpdate1(Provider member,User user) throws Exception {
		// TODO Auto-generated method stub
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user1 = loginEntity.getUserName();
		if (null == member.getId() || "".equals(member.getId())) {
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);

			// String title=news.getTitle().replaceAll("", "");

			member.setCreateTime(DateUtil.newDate());
          //  member.setId(-1);
			member.setDetailintro(member.getDetailintro());
			//	System.out.println("你好，大世界简介" + news.getBriefintro());
			//	news.setNid(sequence);
			member.setType("provider");
			//	news.setBid(CommonDefine.JK_BUILDING);
			member.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));

			member.setCreateUser(user1);//session中的创建人
			//	member.setUpdateUser(user);//session中的更新人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			Provider newsdata = getByid(member.getId());
			newsdata.setName(member.getName());
			newsdata.setTitle(member.getTitle());
			newsdata.setAddress(member.getAddress());
			newsdata.setId(user.getProviderId());
			newsdata.setLongitude(member.getLongitude());
			newsdata.setLatitude(member.getLatitude());
			newsdata.setMemberurl(member.getMemberurl());
			newsdata.setDetailintro(member.getDetailintro());
			newsdata.setImage1(member.getImage1());
			newsdata.setMiniature(member.getMiniature());
			newsdata.setId(user.getProviderId());
			System.out.println(user.getProviderId()+"666用户信息中的providerId");
			newsdata.setUpdateTime(DateUtil.newDate());
			System.out.println("updatetime====="+(DateUtil.newDate()));
			//newsdata.setModifyTime(DateUtil.newDate());
			newsdata.setUpdateUser(user1);//session中的更新人
			member = newsdata;
		}
		splitPageDao.saveOrUpdate(member);

	}
	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.memberManage.service.MemberManageService#saveOrUpdate2(com.tcj.domains.Provider)
	 * 会员单位只对自己的会员单位进行修改
	 *
	 *
	 *
	 */
	@Override
	public void saveOrUpdate2(Provider member,User user) throws Exception {
		// TODO Auto-generated method stub
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user1 = loginEntity.getUserName();
		if (null == member.getId() || "".equals(member.getId())) {
			member.setCreateTime(DateUtil.newDate());
			member.setDetailintro(member.getDetailintro());
			member.setType("provider");
		
			member.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			member.setCreateUser(user1);//session中的创建人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			Provider newsdata = getByid(member.getId());
			newsdata.setName(member.getName());
			newsdata.setTitle(member.getTitle());
			newsdata.setAddress(member.getAddress());
			newsdata.setLongitude(member.getLongitude());
			newsdata.setLatitude(member.getLatitude());
			newsdata.setMemberurl(member.getMemberurl());
			newsdata.setDetailintro(member.getDetailintro());
			newsdata.setImage1(member.getImage1());
			newsdata.setId(user.getProviderId());
			System.out.println("你好啊，providerId"+user.getProviderId());
			newsdata.setMiniature(member.getMiniature());
			newsdata.setUpdateTime(DateUtil.newDate());
			System.out.println("updatetime====="+(DateUtil.newDate()));
			//newsdata.setModifyTime(DateUtil.newDate());
			newsdata.setUpdateUser(user1);//session中的更新人
			member = newsdata;
		}
		splitPageDao.saveOrUpdate(member);

	}
	@Override
	public Provider getByid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM Provider WHERE id='" + id + "'";
		System.out.println("sql==================" + hql);
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (Provider) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}


	@Override
	public void updateLoadImage(Map param) {
		// TODO Auto-generated method stub
		String id = MapUtils.getString(param, "id", "");
		String miniature = MapUtils.getString(param, "miniature", "");
		String sql = "UPDATE member set miniature=? where id=?";
		System.out.println("你好，上传图片"+sql);
		ArrayList al = new ArrayList();
		al.add(miniature);
		al.add(id);
		baseDao.executeSql(sql, al.toArray());
	}


	@Override
	public void delete(Map<String, String> param) {
		// TODO Auto-generated method stub
		//    Member member=new Member();
		String ids = param.get("ids").toString();
		//String user = MapUtils.getString(param, "onhome", "")
		//	member.setUpdateTime( DateUtil.newDate());
		//	member.setCreatetime( DateUtil.newDate());

		Date date = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
		String create_time = df.format(date);
		System.out.println(create_time);



		String update_time = df.format(date);
		System.out.println(update_time);


		//	Date gmt_modify=new Date();
		//	Date create_time=new Date();
		ArrayList al = new ArrayList();
		al.add(CommonDefine.ID_DELETE_TRUE);// o是没删除的

		al.add(update_time);
		al.add(create_time);
		String a[] = ids.split(",");
		for (int i = 0; i < a.length; i++) {
			al.add(a[i]);
		}
		System.out.print("1111"+ids);
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		Object[] objs = al.toArray();
		System.out.println("length:" + objs.length);
		String sql = "update provider set status=?,create_time=?,update_time=? where id in ";
		sql = new BatchTool().batchDeleteSql(sql, objs, 3);
		System.out.println("删除sql:" + sql);
		splitPageDao.deleteAll(sql, objs);

	}


	@Override
	public Provider getBymemberid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM Provider WHERE id='" + -1 + "'";
		System.out.println("sql==================" + hql);
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (Provider) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}























}


