package com.tcj.work.messageManage.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.EnumActivityStatus;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.Activity;
import com.tcj.domains.ActivityProject;
import com.tcj.domains.ActivityProjectAdditional;
import com.tcj.domains.City;
import com.tcj.domains.DemoManage;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.Pronivce;
import com.tcj.domains.User;
import com.tcj.work.activityManage.service.ActivityManageService;
import com.tcj.work.messageManage.service.MessageManageService;

/**
 * @dscription 活动数据维护 
 * @author frb
 * @date 2016/07/30 下午2.00
 * @version 1.0
 * @history
 */
@Component("messageManageServiceImpl")
public class MessageManageServiceImpl implements MessageManageService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	/*
	 * 更加userId查询用户信息
	 * (non-Javadoc)
	 * @see com.tcj.work.messageManage.service.MessageManageService#getUserInfoById(java.lang.String)
	 */
	public User getUserInfoById(String userId) throws EgladServiceException {
		// TODO Auto-generated method stub
		String Hql="from User where id="+userId;
		
		List<User> list = this.splitPageDao.findByHql(Hql);

		if (list.size() > 0) {
			return (User) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
		
	/*
	 * 超级管理员
	 * 查询（条件查询）
	 * 查询留言
	 */
	public Page getListMessage0(Map<String, String> param) throws EgladServiceException {
		
		ArrayList al = new ArrayList();
		String username = MapUtils.getString(param, "username", "");
		String providername = MapUtils.getString(param, "providername", "");
		String providerId = MapUtils.getString(param, "providerId", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
			String sql="select a.id,a.name as username,a.provider_id,a.sex,a.tel,a.time,a.visitor_feedback,a.count,"
					+ "a.email,b.id as providerId,b.name as providername from ds_visitor a inner join provider b on a.provider_id=b.id "
					+ " where 1=1";
			// 判断是否为空，动态增加hql语句，动态增加ArrayList
			if (!"".equals(MapUtils.getString(param, "username", ""))) {
				sql += " and a.name like ?";
				al.add("%" + username + "%");//留言用户条件查询
			}
			if (!"".equals(MapUtils.getString(param, "providername", ""))) {
				sql += " and b.name like ?";
				al.add("%" + providername + "%");//会员单位条件查询
			}
			System.out.println("sql=============="+sql);
			Object[] objs = al.toArray();
			
			return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
	
	/*
	 * 快车网管理员
	 * 查询（条件查询）
	 * 查询留言
	 */
	@Override
	public Page getListMessage1(Map<String, String> param) throws EgladServiceException {
		// TODO Auto-generated method stub

		ArrayList al = new ArrayList();
		String username = MapUtils.getString(param, "username", "");
		String providername = MapUtils.getString(param, "providername", "");
		String providerId = "-1";
		
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String sql="select a.id,a.name as username,a.provider_id,a.sex,a.tel,a.time,a.visitor_feedback,a.count,"
				+ "a.email,b.id as providerId,b.name as providername from ds_visitor a inner join provider b on a.provider_id=b.id "
				+ " where a.provider_id="+providerId;
			// 判断是否为空，动态增加hql语句，动态增加ArrayList
			if (!"".equals(MapUtils.getString(param, "username", ""))) {
				sql += " and a.name like ?";
				al.add("%" + username + "%");//留言用户条件查询
			}
			System.out.println("sql=============="+sql);
			Object[] objs = al.toArray();
			
			return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
	
	/*
	 * 会员单位管理员
	 * 查询（条件查询）
	 * 查询留言
	 */
	@Override
	public Page getListMessage2(Map<String, String> param) throws EgladServiceException {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String username = MapUtils.getString(param, "username", "");
		String providername = MapUtils.getString(param, "providername", "");
		String providerId = MapUtils.getString(param, "providerId", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		String sql="select a.id,a.name as username,a.provider_id,a.sex,a.tel,a.time,a.visitor_feedback,a.count,"
				+ "a.email,b.id as providerId,b.name as providername from ds_visitor a inner join provider b on a.provider_id=b.id "
				+ " where a.provider_id = "+providerId;
			// 判断是否为空，动态增加hql语句，动态增加ArrayList
			if (!"".equals(MapUtils.getString(param, "username", ""))) {
				sql += " and a.name like ?";
				al.add("%" + username + "%");//留言用户条件查询
			}
			System.out.println("sql=============="+sql);
			Object[] objs = al.toArray();
			
			return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);

	}
/*
 * 超级管理员
 * 查询商品评论
 * 
 * (non-Javadoc)
 * @see com.tcj.work.messageManage.service.MessageManageService#getListComment0(java.util.Map)
 */
	@Override
	public Page getListComment0(Map<String, String> param) throws EgladServiceException{
		// TODO Auto-generated method stub
		
		ArrayList al = new ArrayList();
		String goodsname = MapUtils.getString(param, "goodsname", "");
		String providername = MapUtils.getString(param, "providername", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
 
        String sql="select a.id,a.goods_id,a.username,a.title,a.`status`,a.content,"
        		+ "date_format(a.createtime,'%Y-%m-%d %H:%i:%s') as create_time,"
        		+ "date_format(a.modifytime,'%Y-%m-%d %H:%i:%s') as modify_time,"
        		+ "b.provider_id,b.name as goodsname,"
        		+ "c.name as providername "
        		+ " from comment a inner join goods b on a.goods_id=b.id inner join provider c on b.provider_id=c.id "
        		+ "where 1=1";
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "goodsname", ""))) {
			sql += " and b.name like ?";
			al.add("%" + goodsname + "%");
		}
		if (!"".equals(MapUtils.getString(param, "providername", ""))) {
			sql += " and c.name like ?";
			al.add("%" + providername + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
/*
 * 快车网管理员
 * 查询商品评论
 * (non-Javadoc)
 * @see com.tcj.work.messageManage.service.MessageManageService#getListComment1(java.util.Map)
 */
	@Override
	public Page getListComment1(Map<String, String> param) throws EgladServiceException{
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String goodsname = MapUtils.getString(param, "goodsname", "");
		String providername = MapUtils.getString(param, "providername", "");
		String providerId = "-1";
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
 
        String sql="select a.id,a.goods_id,a.username,a.title,a.`status`,a.content,"
        		+ "date_format(a.createtime,'%Y-%m-%d %H:%i:%s') as create_time,"
        		+ "date_format(a.modifytime,'%Y-%m-%d %H:%i:%s') as modify_time,"
        		+ "b.provider_id,b.name as goodsname,"
        		+ "c.name as providername "
        		+ " from comment a inner join goods b on a.goods_id=b.id inner join provider c on b.provider_id=c.id "
        		+ "where b.provider_id="+providerId;
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "goodsname", ""))) {
			sql += " and b.name like ?";
			al.add("%" + goodsname + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
/*
 * 会员单位管理员
 * 查询商品评论
 * (non-Javadoc)
 * @see com.tcj.work.messageManage.service.MessageManageService#getListComment2(java.util.Map)
 */
	@Override
	public Page getListComment2(Map<String, String> param) throws EgladServiceException{
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String goodsname = MapUtils.getString(param, "goodsname", "");
		String providername = MapUtils.getString(param, "providername", "");
		String providerId = MapUtils.getString(param, "providerId", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
 
        String sql="select a.id,a.goods_id,a.username,a.title,a.`status`,a.content,"
        		+ "date_format(a.createtime,'%Y-%m-%d %H:%i:%s') as create_time,"
        		+ "date_format(a.modifytime,'%Y-%m-%d %H:%i:%s') as modify_time,"
        		+ "b.provider_id,b.name as goodsname,"
        		+ "c.name as providername "
        		+ " from comment a inner join goods b on a.goods_id=b.id inner join provider c on b.provider_id=c.id "
        		+ "where b.provider_id="+providerId;
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "goodsname", ""))) {
			sql += " and b.name like ?";
			al.add("%" + goodsname + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}

}
