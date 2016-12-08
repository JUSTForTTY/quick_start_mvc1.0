package com.tcj.work.newsType.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.News;
import com.tcj.work.newsType.service.NewsTypeManageService;

@Component("newsTypeManageServiceImpl")
public class NewsTypeManageServiceImpl implements NewsTypeManageService    {

	@Autowired
	@Qualifier("splitPageDao")
    private SplitPageDao  splitPageDAO;
	@Override
	public Page getTypeList(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String id=MapUtils.getString(param, "id","");
		String title = MapUtils.getString(param, "title", "");
		String status=MapUtils.getString(param, "status","");
		String type=MapUtils.getString(param, "type","");
	    Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String hql = "from News where 1=1 ";
		
		if (!"".equals(title)) {
			hql += " and title like ?  ";
			al.add("%" + title + "%");
		}
		if(!"".equals(status)){
			hql+=" and status like ? ";
			al.add("%" + status + "%");
		}
		if (!"".equals(type)) {
			hql += " and type like ?   order by id desc ";
			al.add("%" + type + "%");
		}
//		if (!"".equals(MapUtils.getString(param, "id", ""))) {
//			hql += " and id = ?  order by id desc";
//			al.add(id);
//	}
		
		Object[] objs = al.toArray();
		System.out.println("你好，你走到这里了");
		return splitPageDAO.findByHql(hql, objs, pgNumber.intValue(), pgSize.intValue());
	}
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
			News newsdata = getBynid(news.getId());
			System.out.println("id不为空");
			System.out.println(news.getId()+"修改之后");
			newsdata.setId(news.getId());
			System.out.println("id你好，你该来了");
			newsdata.setTitle(news.getTitle());
			newsdata.setContent(news.getContent());
			newsdata.setUrl(news.getUrl());
		//	newsdata.setImage(news.getImage());
		//	newsdata.setMobilecontent(news.getMobilecontent());
			System.out.println("你好，图片" + news.getImage());
			System.out.println("neirong" + news.getContent());
			System.out.println("链接你好" + news.getUrl());
			newsdata.setBriefintro(news.getBriefintro());
			System.out.println("简介你好" + news.getBriefintro());
			newsdata.setModifyTime(DateUtil.newDate());
			// userdata.setUserName(news.getUserName());
			news = newsdata;
			
		}
		System.out.println("哈哈哈哈");
		splitPageDAO.saveOrUpdate(news);

	}
	@Override
	public News getBynid(Integer  id) throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM News WHERE id='" + id + "'";
		System.out.println("sql==================你好" + hql);
		List list = this.splitPageDAO.findByHql(hql);
		if (list.size() > 0) {
			return (News) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}

	
	
	
	@Override
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
		splitPageDAO.deleteAll(sql, objs);

	}
	@Override
	public void saveType(Map map) throws Exception {
		// TODO Auto-generated method stub
//		News news = new News();
//		news.setTitle(MapUtils.getString(map, "title"));
//		news.setUrl(MapUtils.getString(map, "url"));
//		news.setContent((MapUtils.getString(map, "content")));
//		news.setBriefintro((MapUtils.getString(map, "briefintro")));
//		news.setType((MapUtils.getString(map, "type")));
//		news.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
//		
//		//插入字段
//		String sql ="insert into t_merchant_info (title,url,content,briefintro,type)"+"values("+news.getTitle()+",'" +news.getUrl()+ "','" + news.getContent() + "','"+news.getBriefintro()+"','"+news.getType()+"')";		
//		String sql1="SELECT * FROM news WHERE title ='"+news.getTitle().replaceAll("'", "''")+"'" ;
//		List<Map> list=splitPageDAO.findBySql(sql1);
//		
//			if(list.size()>0){
//				throw new Exception("标题重名，请重新输入！");
//				}
//			//	System.out.println(sql);
//		//		splitPageDao.excuteSql(sql);
//		System.out.println(sql);
//		
//		splitPageDAO.excuteSql(sql);
//	}
 
}
}