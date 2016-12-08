package com.tcj.work.orderManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonUtil;
import com.tcj.domains.News;
import com.tcj.domains.Order;
import com.tcj.work.orderManage.service.OrderService;



@Component("orderServiceImpl")
public class OrderServiceImpl implements OrderService {


	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;



	@Override
	public Page getList(Map<String, String> map) {
		// TODO Auto-generated method stub

		ArrayList  al=new ArrayList();
		String user_name=MapUtils.getString(map, "user_name");
		String status=MapUtils.getString(map, "status");
		String pay_type=MapUtils.getString(map, "pay_type");
		String sday = MapUtils.getString(map, "sday");
		String eday = MapUtils.getString(map, "eday");
		//	Integer usertype=MapUtils.getInteger(map,"usertype");
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		//超级管理员查询所有的订单
		String sql=" select o.id as id,so.provider_id as provider_id,date_format(o.order_time ,'%Y-%m-%d %H:%i:%s')as order_time,o.total_price/100 as total_price,"
				+ "o.total_score as total_score, o.goods_type_count as goods_type_count,o.goods_count as goods_count,"
				+ "o.receiver_name as receiver_name,o.receiver_address as receiver_address, o.receiver_mobile as receiver_mobile ,"
				+ "o.user_name as user_name, o.referee as referee,"
				+ "o.pay_type as pay_type,p.name as name, o.status as status from  `order` o left join sub_order so on o.id=so.order_id  left join provider p on so.provider_id=p.id where so.provider_id in(select p.id from provider where 1=1) ";
		//		if (!"".equals(user_name)) {
		//			hql += " and user_name like ?  ";
		//			al.add("%" + user_name + "%");
		//		}
		System.out.println("sql====" +sql);
		if(!"".equals(user_name)){
			sql+=" and user_name like ?";
			al.add("%" + user_name +"%");
		}
		if("1".equals(MapUtils.getString(map, "status"))){
			System.out.println("已下单"+status);
			sql+=" and (o.status='33' or o.status='32')";
			//al.add(status);
			//sql+=" and (o.status='33' or o.status='32')";
		}
		if("2".equals(status)){
			System.out.println("已取消下单"+status);
			sql+=" and (o.status='0' )";
		}
		if("3".equals(status)){
			System.out.println("备货"+status);
			sql+=" and (o.status='32' or o.status='21')";
		}
		if("4".equals(status)){
			System.out.println("送货"+status);
			sql+=" and (o.status='12' or o.status='21')";
		}
		if("5".equals(status)){
			System.out.println("交易成功"+status);
			sql+=" and (o.status='33')";
		}

		if(!"".equals(pay_type)){
			sql+=" and o.pay_type= ?";
			al.add(pay_type);
		}
		if ("".equals(sday) && "".equals(eday)) {
			sql += " and date_format(o.order_time,'%Y-%m-%d') = curdate()";
		}
		//		 大于起始时间 (单)
		if (!"".equals(sday) && "".equals(eday)) { // 特殊符转义
			sday = CommonUtil.strReplace(sday);
			sql += "and date_format(o.order_time,'%Y-%m-%d')  >= '" + sday+ "'";
		}
		// 小于终止时间（单）
		if ("".equals(sday) && !"".equals(eday)) {
			// 特殊符转义
			eday = CommonUtil.strReplace(eday);
			sql += "and date_format(o.order_time,'%Y-%m-%d')  <= '" + eday+ "' ";
		} 

		// 大于起始，小于终止时间
		if (!"".equals(sday) && !"".equals(eday)) {
			// 特殊符转义
			sday = CommonUtil.strReplace(sday);
			eday = CommonUtil.strReplace(eday);
			sql += "and date_format(o.order_time,'%Y-%m-%d')  >= '" + sday
					+ "' ";
			sql += "and date_format(o.order_time,'%Y-%m-%d')  <= '" + eday
					+ "' ";
		}
		
		sql+=" order by o.id desc";
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());

	}

	@Override
	public Page getList1(Map<String, String> map) {
		// TODO Auto-generated method stub

		ArrayList  al=new ArrayList();
		String user_name=MapUtils.getString(map, "user_name");
		String status=MapUtils.getString(map, "status");
		String pay_type=MapUtils.getString(map, "pay_type");
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		Integer providerId=MapUtils.getInteger(map, "providerId");
		System.out.println("订单快车网管理员id"+providerId);
		//快车网管理员只查询到快车网的订单
		String sql=" select o.id as id,so.provider_id as provider_id,date_format(o.order_time ,'%Y-%m-%d %H:%i:%s')as order_time,o.total_price/100 as total_price,"
				+ "o.total_score as total_score, o.goods_type_count as goods_type_count,o.goods_count as goods_count,"
				+ "o.receiver_name as receiver_name,o.receiver_address as receiver_address, o.receiver_mobile as receiver_mobile ,"
				+ "o.user_name as user_name, o.referee as referee,"
				+ "o.pay_type as pay_type,p.name as name, o.status as status from  `order` o left join sub_order so on o.id=so.order_id  left join provider p on so.provider_id=p.id where so.provider_id in(select p.id from provider where 1=1)  and so.provider_id=-1 and 1=1 ";
		//		if (!"".equals(user_name)) {
		//			hql += " and user_name like ?  ";
		//			al.add("%" + user_name + "%");
		//		}
		System.out.println("sql====" +sql);
		if(!"".equals(user_name)){
			sql+=" and user_name like ?";
			al.add("%" + user_name +"%");
		}
		if("1".equals(MapUtils.getString(map, "status"))){
			System.out.println("已下单"+status);
			sql+=" and (o.status='33' or o.status='32')";
			//al.add(status);
			//sql+=" and (o.status='33' or o.status='32')";
		}
		if("2".equals(status)){
			System.out.println("已取消下单"+status);
			sql+=" and (o.status='0' )";
		}
		if("3".equals(status)){
			System.out.println("备货"+status);
			sql+=" and (o.status='32' or o.status='21')";
		}
		if("4".equals(status)){
			System.out.println("送货"+status);
			sql+=" and (o.status='12' or o.status='21')";
		}
		if("5".equals(status)){
			System.out.println("交易成功"+status);
			sql+=" and (o.status='33')";
		}

		if(!"".equals(pay_type)){
			sql+=" and o.pay_type= ?";
			al.add(pay_type);
		}
		sql+=" order by o.id desc";
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}

	@Override
	public Page getList2(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList  al=new ArrayList();
		String user_name=MapUtils.getString(map, "user_name");
		String status=MapUtils.getString(map, "status");
		String pay_type=MapUtils.getString(map, "pay_type");
		String sday = MapUtils.getString(map, "sday");
		String eday = MapUtils.getString(map, "eday");
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		Integer providerId=MapUtils.getInteger(map, "providerId");
		System.out.println("会员单位管理员providerId"+providerId);
		//会员单位管理员只查询到会员单位自己用户下的订单
		String sql=" select o.id as id,so.provider_id as provider_id ,p.name as name,date_format(o.order_time ,'%Y-%m-%d %H:%i:%s')as order_time,o.total_price/100 as total_price,o.total_score as total_score, o.goods_type_count as goods_type_count,o.goods_count as goods_count,"
				+ "o.receiver_name as receiver_name,o.receiver_address as receiver_address, o.receiver_mobile as receiver_mobile ,o.user_name as user_name, o.referee as referee,"
				+ "o.pay_type as pay_type,o.status as status  from  `order` o left join sub_order so on so.order_id=o.id left join provider p on so.provider_id=p.id where so.provider_id ="+providerId ;
		//		if (!"".equals(user_name)) {
		//			hql += " and user_name like ?  ";
		//			al.add("%" + user_name + "%");
		//		}
	//	(CASE WHEN o.status='1' THEN '已下单' WHEN o.status='2' THEN '未下单' WHEN o.status='3' THEN '备货' WHEN o.status='4' THEN '送货'  WHEN o.status='5' THEN '交易成功' END) AS status
		System.out.println("sql====" +sql);
		if(!"".equals(user_name)){
			sql+=" and o.user_name like ?";
			al.add("%" + user_name +"%");
		}
		if("1".equals(MapUtils.getString(map, "status"))){
			System.out.println("已下单"+status);
			sql+=" and (o.status='33' or o.status='32')";
			//al.add(status);
			//sql+=" and (o.status='33' or o.status='32')";
		}
		if("2".equals(status)){
			System.out.println("已取消下单"+status);
			sql+=" and (o.status='0' )";
		}
		if("3".equals(status)){
			System.out.println("备货"+status);
			sql+=" and (o.status='32' or o.status='21')";
		}
		if("4".equals(status)){
			System.out.println("送货"+status);
			sql+=" and (o.status='12' or o.status='21')";
		}
		if("5".equals(status)){
			System.out.println("交易成功"+status);
			sql+=" and (o.status='33')";
		}

		if(!"".equals(pay_type)){
			sql+=" and o.pay_type= ?";
			al.add(pay_type);
		}
		
		if ("".equals(sday) && "".equals(eday)) {
			sql += " and date_format(o.order_time,'%Y-%m-%d') = curdate()";
		}
		//		 大于起始时间 (单)
		if (!"".equals(sday) && "".equals(eday)) { // 特殊符转义
			sday = CommonUtil.strReplace(sday);
			sql += "and date_format(o.order_time,'%Y-%m-%d')  >= '" + sday+ "'";
		}
		// 小于终止时间（单）
		if ("".equals(sday) && !"".equals(eday)) {
			// 特殊符转义
			eday = CommonUtil.strReplace(eday);
			sql += "and date_format(o.order_time,'%Y-%m-%d')  <= '" + eday+ "' ";
		} 

		// 大于起始，小于终止时间
		if (!"".equals(sday) && !"".equals(eday)) {
			// 特殊符转义
			sday = CommonUtil.strReplace(sday);
			eday = CommonUtil.strReplace(eday);
			sql += "and date_format(o.order_time,'%Y-%m-%d')  >= '" + sday
					+ "' ";
			sql += "and date_format(o.order_time,'%Y-%m-%d')  <= '" + eday
					+ "' ";
		}
		
		
		sql+=" order by o.id desc";
		System.out.println("sql====11111" +sql);
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}


	@Override
	public Page getByid(Map param) {
		// TODO Auto-generated method stub
		ArrayList  al=new ArrayList();
		String id=MapUtils.getString(param, "id");
		Integer pgNumber=MapUtils.getInteger(param,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(param,"rows",Integer.valueOf(40));
		String sql = "  select od.id as id,od.goods_id as goods_id,od.goods_name as goods_name,od.unit_price/100 as unit_price,od.goods_count as goods_count  from  `order` o inner join order_detail od  on od.order_id=o.id  WHERE o.id='" + id + "'";
		System.out.println("sql==================你好" + sql);
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
	}




	//
	//	@Override
	//	public Object getByid(String id) throws Exception {
	//		// TODO Auto-generated method stub
	//
	//		System.out.println("id====="+id);
	//		String sql = "  select od.id as id,od.goods_id as goods_id,od.goods_name as goods_name,od.unit_price as unit_price,od.goods_count as goods_count  from  `order` o inner join order_detail od  on od.order_id=o.id  WHERE o.id='" + id + "'";
	//		System.out.println("sql==================你好" + sql);
	//		//		List list = this.splitPageDao.findBySql(sql);
	//		//		if (list.size() > 0) {
	//		//			return (Order) list.get(0);
	//		//		} 
	//		//		throw new EgladServiceException("并没有这条记录");
	//		//	}
	//		List lists=splitPageDao.findBySql(sql);
	//		if (lists.size()==1) {
	//
	//			return lists.get(0);
	//		}
	//		return null;
	//	}




}


