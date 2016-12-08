package com.tcj.work.clubManage.service.impl;

import java.sql.Timestamp;
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
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.Activity;
import com.tcj.domains.City;
import com.tcj.domains.Club;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.Pronivce;
import com.tcj.domains.User;
import com.tcj.work.clubManage.service.ClubManageService;
import com.tcj.work.messageManage.service.MessageManageService;

/**
 * @dscription 社团数据维护 
 * @author frb
 * @date 2016/09/06 
 * @version 1.0
 * @history
 */
@Component("clubManageServiceImpl")
public class ClubManageServiceImpl implements ClubManageService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;
	@Autowired()
	@Qualifier("messageManageServiceImpl")
	private MessageManageService messageManageService;
	/*
	 * 查询（条件查询）
	 * 查询社团
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getList(java.util.Map)
	 */
	public Page getList(Map<String, String> param) throws EgladServiceException {
		
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String userId = loginEntity.getUserId();
        User userinfo=messageManageService.getUserInfoById(userId);//根据session userId查询用户信息
        Integer usertype=userinfo.getUsertype();
        Integer  providerId=userinfo.getProviderId();//所属会员单位
     	
		ArrayList al = new ArrayList();
		String providername = MapUtils.getString(param, "providername", "");
		String clubName = MapUtils.getString(param, "name", "");
		String isDelete = MapUtils.getString(param, "isDelete", "");
		String status = MapUtils.getString(param, "status", "");
		System.out.println("isDelete================"+isDelete);
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));

		String sql = "select a.cid,a.bid,a.name,a.logo,a.brief,a.type,a.club_member_sum,a.fee_circle,"
				+ "a.city,a.administrator,a.slogan,a.operator,a.mobile,a.create_id,a.status as clubstatus,a.create_user,"
				+ "a.modify_user,"
				+ "date_format(a.fee_start_date,'%Y-%m-%d %H:%i:%s') as fee_start_date,"
				+ "date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as modify_time,"
				+ "c.id as provider_id,c.name as providername"	
				+ " from "
				+ "club a inner join provider c on a.provider_id=c.id left join city b on a.city=b.cityID  "
				+ "where ";
		      if(usertype==0){
			    sql+="1=1";
		     }else if(usertype==1){
			    sql+="a.provider_id="+"'"+-1+"'";
		     }else if(usertype==2){
			    sql+="a.provider_id="+providerId;
	         }
//		判断如果isDelete=1,查询已删除活动,否则查询未删除活动
		if(isDelete.equals("1")){		
			sql += " and is_delete = ?";
		    al.add(CommonDefine.ID_DELETE_TRUE);	
		    System.out.println("查询已删除数据");
		}else
		 if(status.equals("0")){
			sql += " and is_delete = ? and a.status = ? ";
			al.add(CommonDefine.ID_DELETE_FALSE);
			al.add("0");//未审状态
			System.out.println("查询未删除未审核数据");
		}else{
			sql += " and is_delete = ? and a.status = ? ";
			al.add(CommonDefine.ID_DELETE_FALSE);
			al.add("1");//已审状态
			System.out.println("查询未删除已审核数据");
		}
		//条件查询
		if (!"".equals(MapUtils.getString(param, "name", ""))) {
			sql += " and a.name like ?";
			al.add("%" +clubName + "%");
		}
		if (!"".equals(MapUtils.getString(param, "providername", ""))) {
			sql += " and c.name like ?";
			al.add("%" +providername + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
	/*
	 * 
	 * 社团删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 * @see com.tcj.work.activityManage.service.ActivityManageService#delete(java.util.Map)
	 */
	public void delete(Map<String, String> param) {
		// TODO Auto-generated method stubHttpSession session = contxt.getSession();
	
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();

		//模拟登入
//		String user="frb";
//		String createUser="admin";
		String isDelete=MapUtils.getString(param, "isDelete", "");
		System.out.println("isDelete================"+isDelete);
		String ids = param.get("ids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());//删除时间
		ArrayList al = new ArrayList();
		//判断如果isDelete=0,逻辑恢复,否则做逻辑删除
		if(isDelete.equals("0")){
			al.add(CommonDefine.ID_DELETE_FALSE);
		}else{
		    al.add(CommonDefine.ID_DELETE_TRUE);
		}
		al.add(modifytime);
		al.add(user);

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
		String sql="update club set is_delete=?,modify_time=?,modify_user=? where cid in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);

	}
	/*
	 * 
	 * 社团删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 * @see com.tcj.work.activityManage.service.ActivityManageService#delete(java.util.Map)
	 */
	public void clubPending(Map<String, String> param) {
		// TODO Auto-generated method stubHttpSession session = contxt.getSession();
		
		//获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
		
		//模拟登入
//		String user="frb";
//		String createUser="admin";
		String status=MapUtils.getString(param, "status", "");
		System.out.println("status================"+status);
		String ids = param.get("ids").toString();
		Date  modifytime=new Timestamp(new Date().getTime());//删除时间
		ArrayList al = new ArrayList();
		//判断如果status=1,通过审核
			al.add("1");
		al.add(modifytime);
		al.add(user);
		
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
		String sql="update club set status=?,modify_time=?,modify_user=? where cid in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
		
	}
	/*
	 * 
	 * 根据Id查询社团
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getById(java.lang.String)
	 */
	public Object getById(String id) throws Exception {
		// TODO Auto-generated method stub
//		String Hql = "FROM Club WHERE cid='" + id + "' ";
		String sql = "select a.cid,a.bid,a.name,a.logo,a.brief,a.type,a.club_member_sum,a.fee_circle,"
				+ "a.city,a.administrator,a.slogan,a.operator,a.mobile,a.create_id,a.status as clubstatus,a.create_user,"
				+ "a.modify_user,"
				+ "date_format(a.fee_start_date,'%Y-%m-%d %H:%i:%s') as fee_start_date,"
				+ "date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as modify_time,"
				+ "c.id as provider_id,c.name as providername"	
				+ " from "
				+ "club a inner join provider c on a.provider_id=c.id left join city b on a.city=b.cityID  "
				+ "where ";
		      sql+="cid='" + id + "' ";
		List<Club> list = this.splitPageDao.findBySql(sql);

		if (list.size() > 0) {
			return  list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	/*
	 * 
	 * 根据Id查询社团
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getById(java.lang.String)
	 */
	public Club getByClubId(String id) throws Exception {
		// TODO Auto-generated method stub
		String Hql = "FROM Club WHERE cid='" + id + "' ";
		List<Club> list = this.splitPageDao.findByHql(Hql);
		
		if (list.size() > 0) {
			return  list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	/*
	 * 
	 * 社团新增、更新
	 * @see com.tcj.work.activityManage.service.ActivityManageService#saveOrUpdate(com.tcj.domains.Activity)
	 */
	public void saveOrUpdate(Club club) throws Exception {
		// TODO Auto-generated method stub
		
        //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();
//		
//		模拟登录
//		String user="admin";
		//判断aid是否为空，如果为空则操作增加，否则执行更新。
		if(null==club.getCid()||"".equals(club.getCid())){
			String sql="CALL pro_getRunningNO('CID')";
			String  sequence=splitPageDao.findByjkSequence(sql);
			club.setCid(sequence);				
			club.setCreateTime(DateUtil.newDate());
			club.setModifyTime(new Timestamp(new Date().getTime()));
			club.setCreateUser(user);//session中的创建人
			club.setModifyUser(user);//session中的更新人
		}
		else{
			//修改前做查询处理,防止未填写字段致空。
			Club clubInfo= getByClubId(club.getCid());
			//要修改的字段存入bean中
			clubInfo.setProviderId(club.getProviderId());
			clubInfo.setAdministrator(club.getAdministrator());
            clubInfo.setBrief(club.getBrief());
            if(club.getClubMemberSum()!=null || !"".equals(club.getClubMemberSum())){
            clubInfo.setClubMemberSum(club.getClubMemberSum());
            }
			clubInfo.setName(club.getName());
			clubInfo.setStatus(club.getStatus());
			clubInfo.setCity(club.getCity());
			clubInfo.setLogo(club.getLogo());
			clubInfo.setFeeCircle(club.getFeeCircle());
			if(club.getFeeStartDate()!=null || !"".equals(club.getFeeStartDate())){
				clubInfo.setFeeStartDate(club.getFeeStartDate());
			}
			clubInfo.setMobile(club.getMobile());
			clubInfo.setOperator(club.getOperator());
			clubInfo.setSlogan(club.getSlogan());
			clubInfo.setType(club.getType());
			clubInfo.setModifyTime(new Timestamp(new Date().getTime()));
			clubInfo.setModifyUser(user);//session中的更新人
			club=(Club) clubInfo;
		}

		//增加、修改前引入默认楼宇编号
		club.setStatus("1");
		//增加、修改前引入默认楼宇编号
		club.setBid(CommonDefine.JK_BUILDING);
		//增加、修改前引入逻辑删除(默认否)
		club.setIsDelete(CommonDefine.ID_DELETE_FALSE);

		splitPageDao.saveOrUpdate(club);
	}
	/*
	 * 
	 * 
	 * 图片上传
	 * @see com.tcj.work.activityManage.service.ActivityManageService#updateImage(java.util.Map)
	 */
	public void updateImage(Map<String, String> param) {
		// TODO Auto-generated method stub
		String cid = MapUtils.getString(param, "cid", "");
		String image = MapUtils.getString(param, "image", "");
		
		System.out.println("Image=================ddd"+image);
		String sql = "UPDATE club set logo=? where cid=?";
		ArrayList al = new ArrayList();
		al.add(image);
		al.add(cid);
		baseDao.executeSql(sql, al.toArray());
	}
	/*
	 * 
	 * 查询省级
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getProvince(java.util.Map)
	 */
	public List<Pronivce> getProvince(Map<String, String> map) {
	    String hql = "FROM Pronivce where 1=1 ";
		return splitPageDao.findByHql(hql);
	}
	/*
	 * 
	 * 
	 * 查询市级
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getCity(java.util.Map)
	 */
	public List<City> getCity(Map<String, String> map) {
		 
		String provinceId=MapUtils.getString(map, "provinceId", "");
		ArrayList al= new ArrayList();
		String hql = "FROM City where father=? ";
		al.add(provinceId);
		Object objs=al.toArray();
		return baseDao.query(hql, (Object[]) objs);
	}

}
