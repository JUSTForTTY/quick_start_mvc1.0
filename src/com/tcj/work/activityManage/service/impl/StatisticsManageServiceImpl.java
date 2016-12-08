package com.tcj.work.activityManage.service.impl;

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
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.StatisticsEntity;
import com.tcj.work.activityManage.service.StatisticsManageService;

/**
 * @date 2016.8.7
 * @author panyf
 * @version 1.0
 * @dscription 活动统计
 */
@Component("statisticsManageServiceImpl")
public class StatisticsManageServiceImpl implements StatisticsManageService{
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
 
	@Override
	public Page getList(Map<String, String> param) {
		ArrayList al = new ArrayList();
		String activityName = MapUtils.getString(param, "name", "");
		String actName = MapUtils.getString(param, "actname", "");
		String paystatus=MapUtils.getString(param, "paystatus","");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(5));
		/*String sql = "select  u.mobile as mobile,c.name as activityName,c.activity_project_name,u.realname as actMemberName,round(a.payment,2) as payment,e.CodeName as pay_type,f.CodeName as status,date_format(c.start_time,'%Y-%m-%d %H:%i:%s') as start_time,date_format(c.end_time,'%Y-%m-%d %H:%i:%s') as end_time "
				+ "from user u,log a,user_cost_item b,activity_project_view c,activity_member d,codemaster e,codemaster f "
				+ "where u.uid=b.create_user and a.lid = b.lid and b.apid = c.apid  and a.pay_type = e.CodeNo and e.CodeType=123 and a.status = f.CodeNo and f.CodeType=107";
		*/
		String sql="   select u.mobile as mobile,"
				+ " uci.name as activity_project_name,"
				+ " round(l.payment,2) as payment,"
				+ " u.realname as actMemberName,a.name as activityName,l.status ,"
				+ " cd.CodeName as pay_type,date_format(a.start_time,'%Y-%m-%d') as start_time,"
				+ " date_format(a.end_time,'%Y-%m-%d') as end_time"
				+ " from user_cost_item uci,log l,user u,"
				+ " activity_project ap,activity a,codemaster cd"
				+ " where uci.lid=l.lid and  uci.create_user=u.id "
				+ " and ap.apid = uci.apid and a.aid = ap.aid "
				+ " and cd.CodeNo=l.pay_type  ";
		if (!"".equals(activityName)) {
			sql += " and a.name like ?";
			al.add("%" + activityName + "%");
		}
		if (!"".equals(actName)) {
			sql += " and uci.name like ?";
			al.add("%" + actName + "%");
		}
		if (!"".equals(paystatus)&&!"3".equals(paystatus)) {
			sql += " and l.status = ?";
			al.add( paystatus );
		}
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql,objs, pgNumber.intValue(), pgSize.intValue());
	}

	@Override
	public List<StatisticsEntity> exportExcel(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String activityName = MapUtils.getString(param, "name", "");
		String actName = MapUtils.getString(param, "actname", "");
		String paystatus=MapUtils.getString(param, "paystatus","");
		String sql="   select u.mobile as mobile,"
				+ " uci.name as activity_project_name,"
				+ " round(l.payment,2) as payment,"
				+ " u.realname as actMemberName,a.name as activityName,l.status ,"
				+ " cd.CodeName as pay_type,date_format(a.start_time,'%Y-%m-%d') as start_time,"
				+ " date_format(a.end_time,'%Y-%m-%d') as end_time"
				+ " from user_cost_item uci,log l,user u,"
				+ " activity_project ap,activity a,codemaster cd"
				+ " where uci.lid=l.lid and  uci.create_user=u.id "
				+ " and ap.apid = uci.apid and a.aid = ap.aid "
				+ " and cd.CodeNo=l.pay_type  ";
		if (!"".equals(activityName)) {
			sql += " and a.name like ?";
			al.add("%" + activityName + "%");
		}
		if (!"".equals(actName)) {
			sql += " and uci.name like ?";
			al.add("%" + actName + "%");
		}
		if (!"".equals(paystatus)&&!"3".equals(paystatus)) {
			sql += " and l.status = ?";
			al.add( paystatus );
		}
		Object[] objs = al.toArray();
		List<StatisticsEntity> list =splitPageDao.findBySql(sql, objs);
		return list;
	}

}
