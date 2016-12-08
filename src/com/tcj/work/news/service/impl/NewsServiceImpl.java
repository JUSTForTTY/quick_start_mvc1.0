package com.tcj.work.news.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.tcj.domains.Activity;
import com.tcj.domains.AssocBuildingNews;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.domains.Provider;
import com.tcj.domains.User;
import com.tcj.work.news.service.NewsService;
@Component("newsServiceImpl")
public class NewsServiceImpl implements NewsService {

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;


	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;


	@Override
	public Page getList(Map<String, String> params) {
		// TODO Auto-generated method stub

		ArrayList  al=new ArrayList();
		String title=MapUtils.getString(params,"title","");
		String type=MapUtils.getString(params,"type","");
		String status=MapUtils.getString(params,"status","");
		System.out.println("status===="+status);                                                                                    
		//Integer usertype=MapUtils.getInteger(params, "usertype");
		String ProviderId=MapUtils.getString(params, "providerId");
		String name=MapUtils.getString(params, "name");
		System.out.println("memberId-----==="+ProviderId);
		Integer pgNumber = MapUtils.getInteger(params, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(params, "rows", Integer.valueOf(20));
		String sql = " select n.id as id, p.id as pid,n.title as title,p.name as name,n.briefintro as briefintro ,n.image as image ,n.is_scroll as is_scroll,n.content as content ,n.status as status,n.url as url,n.type as type,date_format(n.gmt_create ,'%Y-%m-%d %H:%i:%s') as gmt_create,date_format(n.gmt_publish,'%Y-%m-%d %H:%i:%s' ) as gmt_publish,date_format(n.gmt_modify,'%Y-%m-%d %H:%i:%s' ) as gmt_modify   from news n left join provider p on n.provider_id=p.id where n.provider_id in(select p.id from provider where 1=1) ";
		System.out.println("hql===="+sql);
		if (!"".equals(title)) {
			sql += " and n.title like ?  ";
			al.add("%" +title + "%");
		}
		if (!"".equals(type)) {
			sql += " and n.type = ?  ";
			al.add(type);
		}
		if (!"".equals(status)) {
			sql += " and n.status = ?  ";
			al.add(status);
		}

		if (!"".equals(name)) {
			sql += " and p.name like ? ";
			al.add("%" +name + "%");
		}


		sql+="order by n.id desc";
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 
	 * 
	 */
	@Override
	public Page getList1(Map<String, String> params) {
		// TODO Auto-generated method stub
		ArrayList  al=new ArrayList();
		String title=MapUtils.getString(params,"title","");
		String type=MapUtils.getString(params,"type","");
		String status=MapUtils.getString(params,"status","");

		//Integer usertype=MapUtils.getInteger(params, "usertype");
		String ProviderId=MapUtils.getString(params, "providerId");
		String name=MapUtils.getString(params, "name");
		System.out.println("memberId-----==="+ProviderId);
		Integer pgNumber = MapUtils.getInteger(params, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(params, "rows", Integer.valueOf(20));


		String sql = " select n.id as id, p.id as pid,n.title as title,p.name as name,n.briefintro as briefintro ,"
				+ "n.image as image ,n.is_scroll as is_scroll,n.content as content ,n.status as status,n.url as url,"
				+ "n.type as type,date_format(n.gmt_create ,'%Y-%m-%d %H:%i:%s') as gmt_create,"
				+ "date_format(n.gmt_publish,'%Y-%m-%d %H:%i:%s' ) as gmt_publish,"
				+ "date_format(n.gmt_modify,'%Y-%m-%d %H:%i:%s' ) as gmt_modify "
				+ "from news n left join provider p on n.provider_id=p.id where n.provider_id ="+ProviderId ;
		System.out.println("hql===="+sql);
		if (!"".equals(title)) {
			sql += " and n.title like ?  ";
			al.add("%" + title + "%");
		}
		if (!"".equals(type)) {
			sql += " and n.type = ?  ";
			al.add(type);
		}
		if (!"".equals(status)) {
			sql += " and n.status = ?  ";
			al.add( status);
		}
		if (!"".equals(name)) {
			sql += " and p.name like ?  ";
			al.add("%" + name + "%");
		}


		sql+=" order by n.id desc";
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());

	}	



	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.news.service.NewsService#delete(java.util.Map)
	 * 物理删除
	 * 
	 * 
	 * 
	 */
	public void delete(Map<String, String> param) {
		// TODO Auto-generated method stub
		String ids = param.get("ids").toString();
		//String user = MapUtils.getString(param, "onhome", "")
		Date gmt_publish = new Date();
		Date gmt_modify=new Date();
		ArrayList al = new ArrayList();
		al.add(CommonDefine.ID_DELETE_TRUE);// o是没删除的

		al.add(gmt_publish);
		al.add(gmt_modify);
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
		String sql = "update news set status=?,gmt_publish=?,gmt_modify=? where id in ";
		sql = new BatchTool().batchDeleteSql(sql, objs, 3);
		System.out.println("删除sql:" + sql);
		splitPageDao.deleteAll(sql, objs);

	}


	@Override
	public void insert(Map param) throws  EgladServiceException {
		// TODO Auto-generated method stub
		News news = new News();
		news.setContent(MapUtils.getString(param, "content"));
		news.setBriefintro(MapUtils.getString(param,"briefintro"));
		news.setTitle(MapUtils.getString(param, "title"));
		news.setType(MapUtils.getString(param, "type"));
		System.out.println((MapUtils.getString(param, "type")+"你好"));
		news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
		System.out.println("1111===="+String.valueOf(CommonDefine.ID_DELETE_FALSE));
		news.setImage(MapUtils.getString(param, "image"));
		news.setCreateTime(DateUtil.newDate());
		news.setPublishTime(DateUtil.newDate());

		//	String sql="insert into news(title,type,briefintro,image,content)"+"values('"+news.getTitle()+"','"+news.getType()+"','"+news.getBriefintro()+"','"+news.getImage()+"','"+news.getContent()+"')";

		String sql1="select * from news where title='"+news.getTitle().replaceAll("'","''")+"'";
		List<Map> list=splitPageDao.findBySql(sql1);

		if(list.size()>0){
			throw new EgladServiceException ("新闻标题重名！");
		}
		//	System.out.println(sql);
		//		splitPageDao.excuteSql(sql);
		//	System.out.println(sql);

		splitPageDao.saveOrUpdate(news);
		System.out.println("前期前期群"+(news));
	}


	@Override
	public News getByid(Integer id)throws Exception {
		// TODO Auto-generated method stub
		String hql="from News where id='"+id+"'";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (News) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}


	@Override
	public void update(Map map) throws EgladServiceException {
		// TODO Auto-generated method stub		
		System.out.println("你好，更新的");




	}

	//saveorupdate以后就用了
	@Override
	public void saveOrUpdate(News news) throws Exception {
		// TODO Auto-generated method stub
		if (null == news.getId() || "".equals(news.getId())) {
			System.out.println("id为空");
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);
			news.setGmtCreate(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());
			news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getByid(news.getId());
			System.out.println("id不为空");
			System.out.println(news.getId()+"修改之后");
			newsdata.setId(news.getId());
			System.out.println("id你好，你该来了");
			newsdata.setTitle(news.getTitle());
			newsdata.setContent(news.getContent());
			newsdata.setUrl(news.getUrl());
			newsdata.setImage(news.getImage());
			newsdata.setType(news.getType());
			newsdata.setIsScroll(news.getIsScroll());
			System.out.println("type===="+news.getType());
			//	newsdata.setMobilecontent(news.getMobilecontent());
			System.out.println("你好，图片" + news.getImage());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			System.out.println("简介你好" + news.getBriefintro());
			newsdata.setModifyTime(DateUtil.newDate());
			newsdata.setProviderId(news.getProviderId());
			// userdata.setUserName(news.getUserName());
			news = newsdata;

		}
		System.out.println("哈哈哈哈");
		splitPageDao.saveOrUpdate(news);

	}
	/**
	 * 
	 * 上传图片
	 * 
	 */

	@Override
	public void updateLoadImage(Map<String, String> param) {
		// TODO Auto-generated method stub
		String id = MapUtils.getString(param, "id", "");
		String image = MapUtils.getString(param, "image", "");
		String sql = "UPDATE news set image=? where id=?";
		System.out.println("你好，上传图片"+sql);
		ArrayList al = new ArrayList();
		al.add(image);
		al.add(id);
		baseDao.executeSql(sql, al.toArray());
	}








	@Override
	public void abnsaveOrUpdate(AssocBuildingNews abn) {
		// TODO Auto-generated method stub
		splitPageDao.saveOrUpdate(abn);
	}


	@Override
	public void updateNews(News news) throws Exception {
		// TODO Auto-generated method stub
		if (null == news.getId() || "".equals(news.getId())) {
			System.out.println("id为空");
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);
			news.setGmtCreate(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());
		//	news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getByid(news.getId());
			System.out.println("id不为空");
			System.out.println(news.getId()+"修改之后");
			newsdata.setId(news.getId());
			System.out.println("id你好，你该来了");
			newsdata.setTitle(news.getTitle());
			newsdata.setContent(news.getContent());
			newsdata.setUrl(news.getUrl());
			newsdata.setStatus(news.getStatus());
			newsdata.setImage(news.getImage());
			newsdata.setType(news.getType());
			System.out.println("type===="+news.getType());
			newsdata.setIsScroll(news.getIsScroll());
			System.out.println("置顶+==="+news.getIsScroll());
			//newsdata.setProviderId(memberId);
			//newsdata.setProviderId(-1);
			newsdata.setProviderId(news.getProviderId());
			System.out.println(news.getProviderId()+"providerId");
			//	newsdata.setMobilecontent(news.getMobilecontent());
			System.out.println("你好，图片" + news.getImage());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			System.out.println("简介你好" + news.getBriefintro());

			Date date = new Date(System.currentTimeMillis());
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
			//  String gmt_create = df.format(date);
			//  String gmt_publish= df.format(date);

			newsdata.setModifyTime(DateUtil.newDate());
			// userdata.setUserName(news.getUserName());
			news = newsdata;

		}
		System.out.println("哈哈哈哈");
		splitPageDao.saveOrUpdate(news);

	}

	//新增超管新闻

	@Override
	public Integer insertguanlian(News news) {
		// TODO Auto-generated method stub
		//news.getm
		//String memberId=MapUtils.getString(null, "", "memberId");
		//System.out.println("memberId======111==="+memberId);
		//news.setStatus("0");
		//	news.setGmtCreate(DateUtil.newDate());
		//news.setPublishTime(DateUtil.newDate());

		Date date = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
		String gmt_create = df.format(date);
		String gmt_publish= df.format(date);


		System.out.println("news的创建时间"+news.getGmtCreate());

		//news.setProviderId(-1);

		String sql="insert into news(title,url,type,is_scroll,status,briefintro,image,content,gmt_create,gmt_publish,provider_id)"+"values('"+news.getTitle()+"','"+news.getUrl()+"','"+news.getType()+"','"+news.getIsScroll()+"','"+news.getStatus()+"','"+news.getBriefintro()+"','"+news.getImage()+"','"+news.getContent()+"','"+gmt_create+"','"+gmt_publish+"','"+news.getProviderId()+"')";



		return splitPageDao.saveReturnLastId(sql) ;
	}


	//新增快车网新闻

	@Override
	public Integer insertguanlian1(News news) {
		// TODO Auto-generated method stub
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
		String gmt_create = df.format(date);
		String gmt_publish= df.format(date);


		System.out.println("news的创建时间"+news.getGmtCreate());

		news.setProviderId(-1);

		String sql="insert into news(title,url,type,is_scroll,status,briefintro,image,content,gmt_create,gmt_publish,provider_id)"+"values('"+news.getTitle()+"','"+news.getUrl()+"','"+news.getType()+"','"+news.getIsScroll()+"','"+news.getStatus()+"','"+news.getBriefintro()+"','"+news.getImage()+"','"+news.getContent()+"','"+gmt_create+"','"+gmt_publish+"','"+news.getProviderId()+"')";



		return splitPageDao.saveReturnLastId(sql) ;
	}




	/**
	 * 新增会员单位新闻
	 */

	@Override
	public Integer insertguanlian2(News news,User user) {
		// TODO Auto-generated method stub
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
		String gmt_create = df.format(date);
		String gmt_publish= df.format(date);


		System.out.println("news的创建时间"+news.getGmtCreate());

		System.out.println("providerId"+user.getProviderId());

		String sql="insert into news(title,url,type,is_scroll,status,briefintro,image,content,gmt_create,gmt_publish,provider_id)"+"values('"+news.getTitle()+"','"+news.getUrl()+"','"+news.getType()+"','"+news.getIsScroll()+"','"+news.getStatus()+"','"+news.getBriefintro()+"','"+news.getImage()+"','"+news.getContent()+"','"+gmt_create+"','"+gmt_publish+"','"+user.getProviderId()+"')";



		return splitPageDao.saveReturnLastId(sql) ;
	}





	@Override
	public void updateNews1(News news) throws Exception{
		// TODO Auto-generated method stub
		if (null == news.getId() || "".equals(news.getId())) {
			System.out.println("id为空");
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);
			news.setGmtCreate(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());
			news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getByid(news.getId());
			System.out.println("id不为空");
			System.out.println(news.getId()+"修改之后");
			newsdata.setId(news.getId());
			System.out.println("id你好，你该来了");
			newsdata.setTitle(news.getTitle());
			newsdata.setContent(news.getContent());
			newsdata.setUrl(news.getUrl());
			newsdata.setImage(news.getImage());
			newsdata.setType(news.getType());
			newsdata.setIsScroll(news.getIsScroll());
			System.out.println("type===="+news.getType());
			//newsdata.setProviderId(memberId);
			newsdata.setProviderId(-1);
			System.out.println(news.getProviderId()+"providerId");
			//	newsdata.setMobilecontent(news.getMobilecontent());
			System.out.println("你好，图片" + news.getImage());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			System.out.println("简介你好" + news.getBriefintro());

			Date date = new Date(System.currentTimeMillis());
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
			//  String gmt_create = df.format(date);
			//  String gmt_publish= df.format(date);

			newsdata.setModifyTime(DateUtil.newDate());
			// userdata.setUserName(news.getUserName());
			news = newsdata;

		}
		System.out.println("哈哈哈哈");
		splitPageDao.saveOrUpdate(news);

	}





	@Override
	public void updateNews2(News news) throws Exception {
		// TODO Auto-generated method stub
		if (null == news.getId() || "".equals(news.getId())) {
			System.out.println("id为空");
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);
			news.setGmtCreate(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());
		//	news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getByid(news.getId());
			System.out.println("id不为空");
			System.out.println(news.getId()+"修改之后");
			newsdata.setId(news.getId());
			newsdata.setStatus(news.getStatus());
			System.out.println("id你好，你该来了");
			newsdata.setTitle(news.getTitle());
			newsdata.setContent(news.getContent());
			newsdata.setUrl(news.getUrl());
			newsdata.setImage(news.getImage());
			newsdata.setType(news.getType());
			newsdata.setIsScroll(news.getIsScroll());
			System.out.println("type===="+news.getType());
			//newsdata.setProviderId(memberId);
			//newsdata.setProviderId(-1);
			System.out.println(news.getProviderId()+"providerId");
			//	newsdata.setMobilecontent(news.getMobilecontent());
			System.out.println("你好，图片" + news.getImage());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			System.out.println("简介你好" + news.getBriefintro());

			Date date = new Date(System.currentTimeMillis());
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
			//  String gmt_create = df.format(date);
			//  String gmt_publish= df.format(date);

			newsdata.setModifyTime(DateUtil.newDate());
			// userdata.setUserName(news.getUserName());
			news = newsdata;

		}
		splitPageDao.saveOrUpdate(news);
	}



	@Override
	public void delData(String id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM news WHERE id IN("+id+")";
		System.out.println("删除sql====="+sql);
		splitPageDao.excuteSql(sql);
	}

	/**
	 * 
	 * 分页
	 * 
	 * 
	 */



	@Override
	public Page getBreakingNews(Map map) {
		// TODO Auto-generated method stub
		ArrayList  al=new ArrayList();
		String english_title=MapUtils.getString(map,"title","");
		String pname=MapUtils.getString(map, "pname","");
		Integer pgNumber = MapUtils.getInteger(map, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(map, "rows", Integer.valueOf(20));

		String sql=" select n.id as id,n.title as title,n.english_title as english_title,n.date as date,p.name as pname,p.id as pid from news n  left join provider p on n.provider_id=p.id where n.provider_id in(select p.id from provider where 1=1) ";

		if (!"".equals(english_title)) {
			sql += " and  n.title like ?  ";
			al.add("%" + english_title + "%");
		}
		if (!"".equals(pname)) {
			sql += " and  p.name like ?  ";
			al.add("%" + pname + "%");
		}
		sql+=" order by n.id desc";
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());

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
	public void getsaveOrUpdate(News news) throws Exception {
		// TODO Auto-generated method stub
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		if (null == news.getId() || "".equals(news.getId())) {
			//	String sql = "CALL pro_getRunningNO('NID')";
			//	String sequence = splitPageDao.findByjkSequence(sql);

			// String title=news.getTitle().replaceAll("", "");

			news.setCreateTime(DateUtil.newDate());

			//member.setDetailintro(member.getDetailintro());
			//	System.out.println("你好，大世界简介" + news.getBriefintro());
			//	news.setNid(sequence);
			//	member.setType("provider");
			//	news.setBid(CommonDefine.JK_BUILDING);
			news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			news.setCaid("11");
			news.setBid(10);
			news.setCreateUser(user);//session中的创建人
			//	member.setUpdateUser(user);//session中的更新人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getByid(news.getId());
			//newsdata.setName(news.getName());
			System.out.println("nihao id");
			newsdata.setTitle(news.getTitle());
			newsdata.setEnglishTitle(news.getEnglishTitle());
			newsdata.setGmtModify(DateUtil.newDate());
			newsdata.setProviderId(news.getProviderId());
			newsdata.setId(news.getId());
			newsdata.setDate(news.getDate());
			newsdata.setModifyUser(user);//session中的更新人
			news = newsdata;
		}
		splitPageDao.saveOrUpdate(news);

	}
	@Override
	public void getsaveOrUpdate1(News member) throws Exception {
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
		//	member
		//	user.setProviderId(member.getProviderId());
			System.out.println("最后一层"+member.getProviderId());
			//member.setDetailintro(member.getDetailintro());
			//	System.out.println("你好，大世界简介" + news.getBriefintro());
			//	news.setNid(sequence);
			//	member.setType("provider");
			//	news.setBid(CommonDefine.JK_BUILDING);
			member.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			member.setCaid("11");
			member.setBid(10);
			member.setCreateUser(user1);//session中的创建人
			//	member.setUpdateUser(user);//session中的更新人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getByid(member.getId());
			//newsdata.setName(news.getName());
			System.out.println("nihao id");
			newsdata.setTitle(member.getTitle());
			newsdata.setEnglishTitle(member.getEnglishTitle());
			newsdata.setGmtModify(DateUtil.newDate());
			newsdata.setProviderId(member.getProviderId());
			newsdata.setId(member.getId());
			newsdata.setDate(member.getDate());
			newsdata.setModifyUser(user1);//session中的更新人
			member = newsdata;
		}
		splitPageDao.saveOrUpdate(member);
	}
	@Override
	public Page getBreakingNews1(Map map) {
		// TODO Auto-generated method stub
		ArrayList  al=new ArrayList();
		String english_title=MapUtils.getString(map,"title","");
		String pname=MapUtils.getString(map, "pname","");
		Integer pgNumber = MapUtils.getInteger(map, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(map, "rows", Integer.valueOf(20));
		Integer ProviderId=MapUtils.getInteger(map,"providerId");
		System.out.println("nihaoa"+ProviderId);
		String sql=" select n.id as id,n.title as title,n.english_title as english_title,n.date as date,p.name as pname,p.id as pid from news n  left join provider p on n.provider_id=p.id where n.provider_id= "+ProviderId;

		if (!"".equals(english_title)) {
			sql += " and  n.title like ?  ";
			al.add("%" + english_title + "%");
		}
		if (!"".equals(pname)) {
			sql += " and  p.name like ?  ";
			al.add("%" + pname + "%");
		}
		sql+=" order by n.id desc";
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());

	}


}		


