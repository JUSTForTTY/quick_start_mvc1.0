package com.tcj.work.NewsManage.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.CategoryType;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.Category;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.work.NewsManage.service.NewsManageService;

@Component("newsManageServiceImpl")
public class NewsManageServiceImpl implements NewsManageService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	//
	@Override
	public Page getNewsList(Map<String, String> param) {
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
		String onhome = MapUtils.getString(param, "onhome", "");
		String status = MapUtils.getString(param, "status", "");

		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		
		if (!"".equals(title)) {
			hql += " and title like ?  ";
			al.add("%" + title + "%");
		}
		if (!"".equals(onhome)) {
			hql += " and onhome like ?  ";
			al.add("%" + onhome + "%");
		}
		if (!"".equals(status)) {
			hql += " and status = ?  ";
			al.add(status);
		}

		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order by nid desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	@Override
	public void delete(Map<String, String> param) {
		
		
		//获取session用户信息
				WebContext contxt = WebContextFactory.get();
				HttpSession session=contxt.getSession();
				LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
				String user = loginEntity.getUserName();

		
		
		String ids = param.get("ids").toString();
		//String user = MapUtils.getString(param, "onhome", "")
		Date modifytime = new Date();
		ArrayList al = new ArrayList();
		al.add(CommonDefine.ID_DELETE_TRUE);// o是没删除的
		al.add(user);
		al.add(modifytime);
		String a[] = ids.split(",");
		for (int i = 0; i < a.length; i++) {
			al.add(a[i]);
		}
		System.out.print(ids);
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		Object[] objs = al.toArray();
		System.out.println("length:" + objs.length);
		String sql = "update news set status=?,modify_user=?,modify_time=? where nid in ";
		sql = new BatchTool().batchDeleteSql(sql, objs, 3);
		System.out.println("删除sql:" + sql);
		splitPageDao.deleteAll(sql, objs);

	}

	public void isTitleExist(News news) throws Exception {
		// 滚动新闻
		String sql = "";
		ArrayList al = new ArrayList();
		List list = null;
		sql = "select * from news where caid=? and title=?";
		al.add(news.getCaid());
		al.add(news.getTitle());
		sql += " and status=?";
		al.add(CommonDefine.ID_DELETE_FALSE);

		// 如果是更新的情况
		if (null != news.getNid() && !"".equals(news.getNid())) {
			sql += " and nid !=?";
			al.add(news.getNid());
		}

		list = splitPageDao.findBySql(sql, al.toArray());

		if (list.size() > 0) {
			String msg = "标题已经存在！";
			// 名师，
			if (CategoryType.CATEGORY_TYPE_ROLLING_NEWS.getCode().equals(news.getCaid()))
				msg = "标题已经存在！";
			// 课程体系，动态新闻，家长必读，

			throw new EgladServiceException(msg);
		}
	}
	
	public void isExist(News news) throws Exception {
		// 课程体系，名师， 动态新闻，家长必读，相关视频
		String sql = "";
		ArrayList al = new ArrayList();
		List list = null;

		sql = "select * from news where caid=? and (english_title=? or title=?)";
		al.add(news.getCaid());
		al.add(news.getEnglishTitle());
		al.add(news.getTitle());
		sql += " and status=?";
		al.add(CommonDefine.ID_DELETE_FALSE);

		// 如果是更新的情况
		if (null != news.getNid() && !"".equals(news.getNid())) {
			sql += " and nid !=?";
			al.add(news.getNid());
		}

		list = splitPageDao.findBySql(sql, al.toArray());

		if (list.size() > 0) {
			String msg = "标题或英文标题已经存在！";
			// 名师，
			if (CategoryType.CATEGORY_TYPE_TEACHERS.getCode().equals(news.getCaid()))
				msg = "姓名或英文名已经存在！";
			// 课程体系，动态新闻，家长必读，相关视频
			if (CategoryType.CATEGORY_TYPE_DYNAMICNEWS.getCode().equals(news.getCaid())
					|| CategoryType.CATEGORY_TYPE_MUSTREAD.getCode().equals(news.getCaid())
					|| CategoryType.CATEGORY_TYPE_CURRICULUM.getCode().equals(news.getCaid())
					|| CategoryType.CATEGORY_TYPE_VIDEOS.getCode().equals(news.getCaid()))
				msg = "标题或英文标题已经存在！";

			throw new EgladServiceException(msg);
		}
	}
	
	

	@Override
	public void saveOrUpdate(News news) throws Exception {
		// TODO Auto-generated method stub
		// 判断id是否为空，如果为空则操作增加，否则执行更新。
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		// 查重操作
		// String sql1 = "";
		// ArrayList al = new ArrayList();
		// List list = null;
		//
		// sql1 = "select * from news where ";
		// al.add(news.getEnglishTitle());
		// if (news.getTitle() != null && !news.getTitle().equals("")) {
		// sql1 = sql1 + "(english_title=? or title=? or briefintro=?)";
		// al.add(news.getTitle());
		// } else {
		// sql1 += " english_title=?";
		// }
		// sql1 += " and status=?";
		// al.add(CommonDefine.ID_DELETE_FALSE);
		//
		// // 如果是更新的情况
		// if (null != news.getNid() && !"".equals(news.getNid())) {
		// sql1 += " and nid !=?";
		// al.add(news.getNid());
		// }
		//
		// list = splitPageDao.findBySql(sql1, al.toArray());
		//
		// if (list.size() > 0)
		// throw new EgladServiceException("标题或英文标题已经存在！");
		if (CategoryType.CATEGORY_TYPE_ROLLING_NEWS.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_ROLLING_NEWS.getCode().equals(news.getCaid())) {
			isTitleExist(news);
		}
		// 名师，课程体系，动态新闻，家长必读，相关视频
		if (CategoryType.CATEGORY_TYPE_TEACHERS.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_DYNAMICNEWS.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_MUSTREAD.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_CURRICULUM.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_VIDEOS.getCode().equals(news.getCaid())) {
			isExist(news);
		}

		if (null == news.getNid() || "".equals(news.getNid())) {
			String sql = "CALL pro_getRunningNO('NID')";
			String sequence = splitPageDao.findByjkSequence(sql);

			// String title=news.getTitle().replaceAll("", "");

			news.setCreateTime(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());

			news.setBriefintro(news.getBriefintro());
			System.out.println("你好，大世界简介" + news.getBriefintro());
			news.setNid(sequence);

			news.setBid(CommonDefine.JK_BUILDING);
			news.setStatus(CommonDefine.ID_DELETE_FALSE);
			
			news.setCreateUser(user);//session中的创建人
			news.setModifyUser(user);//session中的更新人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getBynid(news.getNid(), news.getCaid());
			newsdata.setCaid(news.getCaid());
			newsdata.setTitle(news.getTitle());
			newsdata.setEnglishTitle(news.getEnglishTitle());
			newsdata.setContent(news.getContent());
			newsdata.setOnhome(news.getOnhome());
			newsdata.setUrl(news.getUrl());
			newsdata.setImage(news.getImage());
			newsdata.setMobilecontent(news.getMobilecontent());
			System.out.println("你好，图片" + news.getImage());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			System.out.println("简介你好" + news.getBriefintro());
			newsdata.setModifyTime(DateUtil.newDate());
			newsdata.setModifyUser(user);//session中的更新人
			// userdata.setUserName(news.getUserName());
			news = newsdata;
		}
		splitPageDao.saveOrUpdate(news);

	}

	@Override
	public News getBynid(String nid, String caid) throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM News WHERE nid='" + nid + "' and caid='" + caid + "' ";
		System.out.println("sql==================" + hql);
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (News) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

	/*
	 * 
	 * 课程体系 英文标题，标题，内容，简介，图片 (non-Javadoc)
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#getCourseList(java.util
	 * .Map)
	 */
	@Override
	public Page getCourseList(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
	//	String isDelete = MapUtils.getString(param, "isDelete", "");
		String status=MapUtils.getString(param, "status","");
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
		//hql += " and status = ?";
//		if (isDelete.equals("1")) {
//			al.add("1");
//			System.out.println("查询已删除数据1");
//		} else {
//			al.add("CommonDefine.ID_DELETE_FALSE");
//			System.out.println("查询未删除数据0");
//		}
//
//		System.out.println(hql + "11111111112234567");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList

		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		
		if (!"".equals(status)) {
			hql += " and status = ?  ";
			al.add(status);
		}
		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order  by nid desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 课程体系介绍，市场前景，商业模式，加盟优势，加盟流程，走进天才纪，天才纪发展
	 * 
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#getallContentList(java.
	 * util.Map)
	 */
	@Override
	public Page getallContentList(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
		hql += " and status = ?";
		al.add("0");
		System.out.println(hql + "11111111112234567");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList

		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order  by nid desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 明星学员 图片，标题（姓名），简介（年龄），内容，首页显示，排序
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#getStarStudent(java.
	 * util.Map)
	 */
	@Override
	public Page getStarStudent(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");// 姓名
		//String isDelete = MapUtils.getString(param, "isDelete", "");
		String status= MapUtils.getString(param, "status", "");
		String onhome = MapUtils.getString(param, "onhome", "");// 是否首页显示
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
//		hql += " and status = ?";
//		if (isDelete.equals("1")) {
//			al.add("1");
//			System.out.println("查询已删除数据1");
//		} else {
//			al.add("CommonDefine.ID_DELETE_FALSE");
//			System.out.println("查询未删除数据0");
//		}
		// status=0没有被删除的数据
		// hql += " and status = ?";
		// al.add("0");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		if (!"".equals(MapUtils.getString(param, "onhome", ""))) {
			hql += " and onhome like ?";
			al.add("%" + onhome + "%");
		}

		if (!"".equals(status)) {
			hql += " and status = ?  ";
			al.add(status);
		}
		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order by  nid  desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 名师 (non-Javadoc)
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#getFamousTeacher(java.
	 * util.Map)
	 */
	@Override
	public Page getFamousTeacher(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
		hql += " and status = ?";
		al.add("0");
		System.out.println(hql + "11111111112234567");
		// status=0没有被删除的数据
		// hql += " and status = ?";
		// al.add("0");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order by nid  desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 视频 (non-Javadoc)
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#getVideo(java.util.Map)
	 */

	@Override
	public Page getVideo(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
		String onhome = MapUtils.getString(param, "onhome", "");
		String status= MapUtils.getString(param, "status", "");
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
//		hql += " and status = ?";
//		al.add("0");
//		System.out.println(hql + "11111111112234567");
		// status=0没有被删除的数据
		// hql += " and status = ?";
		// al.add("0");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList

		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		if (!"".equals(MapUtils.getString(param, "onhome", ""))) {
			hql += " and onhome like ?";
			al.add("%" + onhome + "%");
		}

		if (!"".equals(status)) {
			hql += " and status = ?  ";
			al.add(status);
		}
		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order by nid desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 个性化流程 (non-Javadoc)
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#getPersonProcess(java.
	 * util.Map)
	 */
	@Override
	public Page getPersonProcess(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
		String keywords = MapUtils.getString(param, "keywords", "");
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
		hql += " and status = ?";
		al.add("0");
		System.out.println(hql + "11111111112234567");
		// status=0没有被删除的数据
		// hql += " and status = ?";
		// al.add("0");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		if (!"".equals(MapUtils.getString(param, "keywords", ""))) {
			hql += " and keywords like ?";
			al.add("%" + keywords + "%");
		}

		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order by nid desc";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	/*
	 * 
	 * 天才纪大事件新闻查询 (non-Javadoc)
	 * 
	 * @see
	 * com.tcj.work.NewsManage.service.NewsManageService#geniusEvent(java.util.
	 * Map)
	 */
	@Override
	public Page geniusEvent(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String title = MapUtils.getString(param, "title", "");
		 String status=MapUtils.getString(param, "status","");
		String caid = MapUtils.getString(param, "caid", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		// 查询未删除的数据
		//hql += " and status = ?";
	//	al.add("0");
		System.out.println(hql + "11111111112234567");
		// status=0没有被删除的数据
		// hql += " and status = ?";
		// al.add("0");
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "title", ""))) {
			hql += " and title like ?";
			al.add("%" + title + "%");
		}
		if (!"".equals(status)) {
			hql += " and status = ?  ";
			al.add(status);
		}
		if (!"".equals(MapUtils.getString(param, "caid", ""))) {
			hql += " and caid = ?  order by  date desc ";
			al.add(caid);
		}

		Object[] objs = al.toArray();
		return splitPageDao.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	@Override
	public void saveOrUpdateImg(News news) {
		// TODO Auto-generated method stub
		String sql = "CALL pro_getRunningNO('NID')";
		String sequence = splitPageDao.findByjkSequence(sql);
		news.setNid(sequence);

		// System.out.print("ID:"+upload.getId());
		// System.out.print("ID:"+upload.getImage());

		splitPageDao.saveOrUpdate(news);

	}
	/*
	 * 
	 * 天才纪大事件时间的saveorupdate
	 * 
	 */

	@Override
	public void geniussaveOrUpdate(News news) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 判断id是否为空，如果为空则操作增加，否则执行更新。
		 //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		// String sql1="select * from news where
		// title='"+news.getTitle().replaceAll("'", "''")+"'and "
		// + "caid='"+news.getCaid().replaceAll("'", "''")+"'";
		// if(news.getNid()!=null){
		// sql1+= "and nid !='"+news.getNid()+"'";
		// }
		// List lst = splitPageDao.findBySql(sql1);
		// if(lst.size()>0){
		// throw new EgladServiceException("标题重复，请重新输入！");
		// }
		String sql = "";
		ArrayList al = new ArrayList();
		List list = null;

		sql = "select * from news where caid=?";
		al.add(news.getCaid());
		al.add(news.getDate());
		if (news.getTitle() != null && !news.getTitle().equals("")) {
			sql = sql + " and (date=? or title=?)";
			al.add(news.getTitle());
		} else {
			sql += " and date=?";
		}
		sql += " and status=?";
		al.add(CommonDefine.ID_DELETE_FALSE);

		// 如果是更新的情况
		if (null != news.getNid() && !"".equals(news.getNid())) {
			sql += " and nid !=?";
			al.add(news.getNid());
		}

		list = splitPageDao.findBySql(sql, al.toArray());

		if (list.size() > 0)
			throw new EgladServiceException("天才纪事件已经存在！");

		/*
		 * ArrayList al = new ArrayList(); String sql1 =
		 * "select * from news where title=?";
		 * al.add(news.getTitle().replaceAll("'", "''")); sql1 += "or date=?";
		 * al.add(news.getDate().replaceAll("'", "''"));
		 * System.out.println("nihao,shijian" + news.getDate()); sql1 +=
		 * "and caid=?"; al.add(news.getCaid().replaceAll("'", "''")); sql1 +=
		 * "and status=?"; al.add("0"); // 如果修改， if (news.getNid() != null) {
		 * sql1 += "and nid !=?"; al.add(news.getNid());
		 * System.out.println("你好nid======" + news.getNid()); }
		 * 
		 * Object[] objs = al.toArray(); List lst = splitPageDao.findBySql(sql1,
		 * objs); if (lst.size() > 0) { throw new
		 * EgladServiceException("标题/时间不可重复，请重新输入！"); }
		 */

		if (null == news.getNid() || "".equals(news.getNid())) {
			// if (news.getTitle() == news.getTitle()) {
			sql = "CALL pro_getRunningNO('NID')";
			String sequence = splitPageDao.findByjkSequence(sql);
			// Date dt=new Date();
			// SimpleDateFormat format = new SimpleDateFormat("yyyy");
			// String date = format.format(date);
			// newsdata.setDate(news.getDate());

			news.setDate(news.getDate());
			System.out.println("大事件时间" + news.getDate());
			news.setCreateTime(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());
			news.setNid(sequence);
			news.setBid(CommonDefine.JK_BUILDING);
			news.setStatus(CommonDefine.ID_DELETE_FALSE);
			news.setCreateUser(user);//session中的创建人
		news.setModifyUser(user);//session中的更新人
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {
			News newsdata = getBynid(news.getNid(), news.getCaid());

			newsdata.setCaid(news.getCaid());
			newsdata.setTitle(news.getTitle());
			newsdata.setDate(news.getDate());// 时间，显示时间
			System.out.println("获得的时间" + news.getDate());
			newsdata.setModifyTime(DateUtil.newDate());
			newsdata.setModifyUser(user);//session中的更新人
			// userdata.setUserName(news.getUserName());
			news = newsdata;
		}
		splitPageDao.saveOrUpdate(news);
	}

	@Override
	public News getByCaid(String caid) throws Exception {
		// TODO Auto-generated method stub
		News news = new News();
		String hql = " FROM News WHERE  caid='" + caid + "' ";
		System.out.println("sql==================" + hql);
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			news = (News) list.get(0);
		}
		return news;
	}

	@Override
	public void contentsaveOrUpdate(News news) throws Exception {
		// TODO Auto-generated method stub
		
		 //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		
		
		
		
		if (null == news.getNid() || "".equals(news.getNid())) {
			String sql = "CALL pro_getRunningNO('NID')";
			String sequence = splitPageDao.findByjkSequence(sql);

			// String title=news.getTitle().replaceAll("", "");

			news.setCreateTime(DateUtil.newDate());
			news.setPublishTime(DateUtil.newDate());

			// news.setBriefintro(news.getBriefintro());
			System.out.println("你好，大世界简介" + news.getBriefintro());
			news.setNid(sequence);
		news.setCreateUser(user);//session中的创建人
			news.setModifyUser(user);//session中的更新人
			news.setBid(CommonDefine.JK_BUILDING);
			news.setStatus(CommonDefine.ID_DELETE_FALSE);
			news.setModifyUser(user);//session中的更新人
			
			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);
		} else {

			News newsdata = getByCaid(news.getCaid());
			newsdata.setCaid(news.getCaid());
			System.out.println(news.getCaid() + "类别你好");
			newsdata.setTitle(news.getTitle());
			newsdata.setEnglishTitle(news.getEnglishTitle());
			newsdata.setContent(news.getContent());
			newsdata.setOnhome(news.getOnhome());
			newsdata.setUrl(news.getUrl());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			newsdata.setImage(news.getImage());
			System.out.println("简介你好" + news.getBriefintro());
			news.setModifyTime(new Timestamp(new Date().getTime()));
			// userdata.setUserName(news.getUserName());
			news = newsdata;
		}
		splitPageDao.saveOrUpdate(news);

	}

	@Override
	public void updateImage(Map<String, String> param) {
		// TODO Auto-generated method stub
		String nid = MapUtils.getString(param, "nid", "");
		String image = MapUtils.getString(param, "image", "");
		String sql = "UPDATE news set image=? where nid=?";
		ArrayList al = new ArrayList();
		al.add(image);
		al.add(nid);
		baseDao.executeSql(sql, al.toArray());
	}

	@Override
	public void updateMiniature(Map param) {
		// TODO Auto-generated method stub
		String nid = MapUtils.getString(param, "nid", "");
		String miniture = MapUtils.getString(param, "image", "");
		String sql = "UPDATE news set miniture=? where nid=?";
		ArrayList al = new ArrayList();
		al.add(miniture);
		al.add(nid);
		baseDao.executeSql(sql, al.toArray());
	}

	@Override
	public void Recovery(News news) throws Exception {
		//滚动图片
		if (CategoryType.CATEGORY_TYPE_ROLLING_NEWS.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_ROLLING_NEWS.getCode().equals(news.getCaid())) {
			isTitleExist(news);
		}
		// 名师，课程体系，动态新闻，家长必读，相关视频
		if (CategoryType.CATEGORY_TYPE_TEACHERS.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_DYNAMICNEWS.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_MUSTREAD.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_CURRICULUM.getCode().equals(news.getCaid())
				|| CategoryType.CATEGORY_TYPE_VIDEOS.getCode().equals(news.getCaid())) {
			isExist(news);
		}
		// TODO Auto-generated method stub
		//获取session用户信息
				WebContext contxt = WebContextFactory.get();
				HttpSession session=contxt.getSession();
				LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
				String user = loginEntity.getUserName();
		
		
		String nid = news.getNid();
		Date modifytime = new Date();
		//String modifyuser="admin";
		String sql = "update news set status=?,modify_user=?,modify_time=? where nid=? ";
		ArrayList al = new ArrayList();
		al.add(CommonDefine.ID_DELETE_FALSE);// //CommonDefine类中的
		
        al.add(user);
		al.add(modifytime);// 修改时间
        al.add(nid);
		Object[] objs = al.toArray();// 将list转换为数组

		log.debug("删除sql" + sql);
		baseDao.deleteAll(sql, objs);
	}

}
