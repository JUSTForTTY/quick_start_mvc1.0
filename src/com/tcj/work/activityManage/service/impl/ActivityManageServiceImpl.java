package com.tcj.work.activityManage.service.impl;

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
import com.tcj.work.activityManage.service.ActivityManageService;

/**
 * @dscription 活动数据维护 
 * @author frb
 * @date 2016/07/30 下午2.00
 * @version 1.0
 * @history
 */
@Component("activityManageServiceImpl")
public class ActivityManageServiceImpl implements ActivityManageService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	/*
	 * 查询（条件查询）
	 * 查询活动
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getList(java.util.Map)
	 */
	public Page getList(Map<String, String> param) {
		
		ArrayList al = new ArrayList();
		String cid = MapUtils.getString(param, "cid", "");
		String activityName = MapUtils.getString(param, "name", "");
		String isDelete = MapUtils.getString(param, "isDelete", "");
		System.out.println("isDelete================"+isDelete);
		System.out.println("cid===============?"+cid);
		System.out.println("name===============?"+activityName);
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));

		String sql = "select a.aid,a.cid,a.bid,a.name,a.subtitle,a.age,a.logo,a.official_url,a.area_id,a.province_id,a.city_id,"
				+ "a.site,a.address,a.lat,a.lng,a.type,a.day,date_format(a.register_start,'%Y-%m-%d') as register_start, "
				+ "date_format(a.register_end,'%Y-%m-%d') as register_end,"
				+ "date_format(a.start_time,'%Y-%m-%d') as start_time,"
				+ "date_format(a.end_time,'%Y-%m-%d') as end_time,"				
				+ "a.status,a.briefintro,a.act_detail,a.act_detail_url,a.stick,a.follow_sum,a.create_user,a.modify_user,"
				+ "date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as modify_time,"
				+ "date_format(a.agree_num_time,'%Y-%m-%d %H:%i:%s') as agree_num_time,"
				+ "a.click_num,a.agree_num,a.disagree_num,a.is_delete,b.province,c.city from "
				+ "activity a left join province b  on a.province_id=b.provinceID left join city c on a.city_id=c.cityID "
				+ "where 1=1";
		//根据社团id查询活动
		sql += " and cid =? ";
		al.add(cid);
		//is_delete=0 显示没有被删除的数据
		sql += " and is_delete = ?";
//		判断如果isDelete=1,查询已删除活动,否则查询未删除活动
		if(isDelete.equals("1")){
		al.add(CommonDefine.ID_DELETE_TRUE);	
		System.out.println("查询已删除数据");
		}else{
			al.add(CommonDefine.ID_DELETE_FALSE);
			System.out.println("查询未删除数据");
		}
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "name", ""))) {
			sql += " and a.name like ?";
			al.add("%" + activityName + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
	
	/*
	 * 查询（条件查询）
	 * 查询活动项目
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getList(java.util.Map)
	 */
	public Page getProjectList(Map<String, String> param) {
		ArrayList al = new ArrayList();
		String activityName = MapUtils.getString(param, "name", "");
		String isDelete = MapUtils.getString(param, "isDelete", "");
		System.out.println("isDelete================"+isDelete);
		String aid = MapUtils.getString(param, "aid", "");		
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		
		String sql = "select apid,aid,bid,name,price,refprice,content,project_num,"
				+ "date_format(start_time,'%y-%m-%d') as start_time,"
				+ "date_format(end_time,'%y-%m-%d') as end_time,"
				+ "date_format(create_time,'%y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(modify_time,'%y-%m-%d %H:%i:%s') as modify_time,"
				+ "create_user,modify_user from activity_project where 1=1";
		//status=0显示,没有被删除的数据
		sql += " and status = ?";

//		判断如果isDelete=1,查询已删除活动,否则查询未删除活动
		if(isDelete.equals("1")){
		al.add(CommonDefine.ID_DELETE_TRUE);	
		System.out.println("Project查询已删除数据");
		}else{
			al.add(CommonDefine.ID_DELETE_FALSE);
			System.out.println("Project查询未删除数据");
		}
		
		sql += " and aid = ?";
		al.add(aid);
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "name", ""))) {
			sql += " and name like ?";
			al.add("%" + activityName + "%");
		}
		System.out.println("sql=============="+sql);
		Object[] objs = al.toArray();

		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}
	/*
	 * 查询（条件查询）
	 * 查询附加项目
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getList(java.util.Map)
	 */
	public Page getAdditionList(Map<String, String> param) {
		ArrayList al = new ArrayList();
		String activityName = MapUtils.getString(param, "name", "");
		String isDelete = MapUtils.getString(param, "isDelete", "");
		System.out.println("isDelete================"+isDelete);
		String aid = MapUtils.getString(param, "aid", "");		
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(20));
		
		String sql = "select apaid,aid,bid,name,price,content,date_format(start_time,'%Y-%m-%d') as start_time,"
				+ "date_format(end_time,'%Y-%m-%d') as end_time,date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ "date_format(modify_time,'%Y-%m-%d %H:%i:%s') as modify_time,create_user,modify_user,is_delete "
				+ "from activity_project_additional where 1=1";
		//is_delete=0显示,没有被删除的数据
		sql += " and is_delete = ?";

//		判断如果isDelete=1,查询已删除活动,否则查询未删除活动
		if(isDelete.equals("1")){
		al.add(CommonDefine.ID_DELETE_TRUE);	
		System.out.println("Project查询已删除数据");
		}else{
			al.add(CommonDefine.ID_DELETE_FALSE);
			System.out.println("Project查询未删除数据");
		}
		
		sql += " and aid = ?";
		al.add(aid);
		// 判断是否为空，动态增加hql语句，动态增加ArrayList
		if (!"".equals(MapUtils.getString(param, "name", ""))) {
			sql += " and name like ?";
			al.add("%" + activityName + "%");
		}
		System.out.println("hql=============="+sql);
		Object[] objs = al.toArray();
		
		return splitPageDao.findBySql(sql, objs,pgNumber, pgSize);
	}

	/*
	 * 
	 * 活动删除（逻辑删除）
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
		String sql="update activity set is_delete=?,modify_time=?,modify_user=? where aid in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);

	}
	/*
	 * 
	 * 活动项目删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 * @see com.tcj.work.activityManage.service.ActivityManageService#delete(java.util.Map)
	 */
	public void deletesProject(Map<String, String> param) {
		// TODO Auto-generated method stub

        //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();

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
		String sql="update activity_project set  status=?,modify_time=?,modify_user=? where apid in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
		
	}
	/*
	 * 
	 * 附加项目删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 * @see com.tcj.work.activityManage.service.ActivityManageService#delete(java.util.Map)
	 */
	public void deletesAddition(Map<String, String> param) {
		// TODO Auto-generated method stub

        //获取session用户信息
		WebContext contxt = WebContextFactory.get();
		HttpSession session=contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();

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
		String sql="update activity_project_additional set  is_delete=?,modify_time=?,modify_user=? where apaid in ";
		//批量删除sql
		sql= new BatchTool().batchDeleteSql(sql, objs);
		System.out.print("删除sql"+sql);
		splitPageDao.deleteAll(sql, objs);
		
	}

	/*
	 * 
	 * 根据Id查询活动
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getById(java.lang.String)
	 */
	public Activity getById(String id) throws Exception {
		// TODO Auto-generated method stub
		String Hql = "FROM Activity WHERE aid='" + id + "' ";
		List<Activity> list = this.splitPageDao.findByHql(Hql);
		
//		for(int i=0;i<list.size();i++){
//			System.out.println("测试时间=================="+list.get(i).getStartTime());
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			   String createTime = formatter.format(list.get(i).getStartTime());
//
//		}
		
		if (list.size() > 0) {
			return (Activity) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	/*
	 * 
	 * 根据Id查询活动项目
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getById(java.lang.String)
	 */
	public ActivityProject getByIdProject(String aid,String apid) throws Exception {
		// TODO Auto-generated method stub
		String hql = " FROM ActivityProject WHERE aid= '" + aid + "' ";
		hql += " and apid = '"+apid+"' ";
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (ActivityProject) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}
	/*
	 * 
	 * 根据Id查询附加项目
	 * @see com.tcj.work.activityManage.service.ActivityManageService#getById(java.lang.String)
	 */
	public ActivityProjectAdditional getByIdAddition(String aid,String apaid) throws Exception {
		// TODO Auto-generated method stub
//		String sql = " select apaid,aid,bid,name,price,content,date_format(start_time,'%Y-%m-%d') as start_time,date_format(end_time,'%Y-%m-%d') as end_time,date_format(create_time,'%Y-%m-%d') as create_time,date_format(modify_time,'%Y-%m-%d') as modify_time,create_user,modify_user,is_delete FROM activity_project_additional WHERE aid= '" + aid + "' ";
//		sql += " and apaid = '"+apaid+"' ";
		String hql="from ActivityProjectAdditional where apaid=?";
		Object[] objs={apaid};
		List<ActivityProjectAdditional> lst =baseDao.query(hql, objs);
		 
		if (lst.size() > 0) {
			return lst.get(0);
		}	 
		throw new EgladServiceException("并没有这条记录");
		
	}

	
	/*
	 * 
	 * 活动新增、更新
	 * @see com.tcj.work.activityManage.service.ActivityManageService#saveOrUpdate(com.tcj.domains.Activity)
	 */
	public void saveOrUpdate(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		
        //获取session用户信息
//		WebContext contxt = WebContextFactory.get();
//		HttpSession session=contxt.getSession();
//		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
//		String user = loginEntity.getUserName();
		
		//模拟登录
		String user="admin";
		//判断aid是否为空，如果为空则操作增加，否则执行更新。
		if(null==activity.getAid()||"".equals(activity.getAid())){
			String sql="CALL pro_getRunningNO('AID')";
			String  sequence=splitPageDao.findByjkSequence(sql);
			activity.setAid(sequence);		
			activity.setCreateTime(DateUtil.newDate());
			activity.setModifyTime(new Timestamp(new Date().getTime()));
			//增加前引入社团编号
			activity.setCid(activity.getCid());
			//如果新增设置默认值为0
			activity.setAgreeNum(0);//赞
			activity.setDisagreeNum(0);//踩
			activity.setClickNum(0);//点击次数
			activity.setFollowSum(0);//关注人数
			activity.setCreateUser(user);//session中的创建人
			activity.setModifyUser(user);//session中的更新人
		}
		else{
			//修改前做查询处理,防止未填写字段致空。
			Activity activityInfo=getById(activity.getAid());
			//要修改的字段存入bean中
			activityInfo.setName(activity.getName());
			activityInfo.setSubtitle(activity.getSubtitle());
			activityInfo.setBriefintro(activity.getBriefintro());
			activityInfo.setSite(activity.getSite());
			activityInfo.setDay(activity.getDay());
			activityInfo.setAge(activity.getAge());
			activityInfo.setMobileContent(activity.getMobileContent());
			activityInfo.setActDetailUrl(activity.getActDetailUrl());
			activityInfo.setOfficialUrl(activity.getOfficialUrl());
			activityInfo.setAddress(activity.getAddress());
			activityInfo.setEndTime(activity.getEndTime());
			activityInfo.setStartTime(activity.getStartTime());
			activityInfo.setRegisterStart(activity.getRegisterStart());
			activityInfo.setRegisterEnd(activity.getRegisterEnd());
			activityInfo.setStatus(activity.getStatus());
			activityInfo.setStick(activity.getStick());
			activityInfo.setActDetail(activity.getActDetail());
			activityInfo.setProvinceId(activity.getProvinceId());
			activityInfo.setCityId(activity.getCityId());
			activityInfo.setModifyTime(new Timestamp(new Date().getTime()));
			activityInfo.setModifyUser(user);//session中的更新人
			activity=activityInfo;
		}

		//增加、修改前引入默认楼宇编号
		activity.setBid(String.valueOf(CommonDefine.JK_BUILDING));
		//增加、修改前引入逻辑删除(默认否)
		activity.setIsDelete(CommonDefine.ID_DELETE_FALSE);
		//增加、修改前设置默认活动类型(1)
		activity.setType("1");
		splitPageDao.saveOrUpdate(activity);
	}
	
	
	/*
	 * 
	 * 活动项目新增、更新
	 * @see com.tcj.work.activityManage.service.ActivityManageService#saveOrUpdate(com.tcj.domains.Activity)
	 */
	public void saveOrUpdateProject(ActivityProject actProject) throws Exception {
		// TODO Auto-generated method stub
		
        //获取session用户信息
//		WebContext contxt = WebContextFactory.get();
//		HttpSession session=contxt.getSession();
//		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
//		String user = loginEntity.getUserName();
		
//		模拟登录
		String user="admin";
		//判断aid是否为空，如果为空则操作增加，否则执行更新。
		if(null==actProject.getApid()||"".equals(actProject.getApid())){
			String sql="CALL pro_getRunningNO('APID')";
			String  sequence=splitPageDao.findByjkSequence(sql);
			actProject.setApid(sequence);		
			actProject.setCreateTime(DateUtil.newDate());
			actProject.setModifyTime(new Timestamp(new Date().getTime()));
			actProject.setCreateUser(user);
			actProject.setModifyUser(user);
		}
		else{
			//修改前做查询处理,防止未填写字段致空。
			ActivityProject actProjectInfo=getByIdProject(actProject.getAid(),actProject.getApid());
			//要修改的字段存入bean中
			actProjectInfo.setName(actProject.getName());
			actProjectInfo.setPrice(actProject.getPrice());
			actProjectInfo.setRefprice(actProject.getRefprice());
//			actProjectInfo.setProjectNum(actProject.getProjectNum());
			actProjectInfo.setContent(actProject.getContent());
//			actProjectInfo.setEndTime(actProject.getEndTime());
//			actProjectInfo.setStartTime(actProject.getStartTime());
			actProjectInfo.setModifyTime(new Timestamp(new Date().getTime()));
            actProjectInfo.setModifyUser(user);
			actProject=actProjectInfo;
		}

		//增加、修改前引入默认楼宇编号
		actProject.setBid(CommonDefine.JK_BUILDING);
		//增加、修改前引入逻辑删除(默认否)
		actProject.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));

		splitPageDao.saveOrUpdate(actProject);
	}
	/*
	 * 
	 * 附加项目新增、更新
	 * @see com.tcj.work.activityManage.service.ActivityManageService#saveOrUpdate(com.tcj.domains.Activity)
	 */
	public void saveOrUpdateAddition(ActivityProjectAdditional actAddition) throws Exception {
		// TODO Auto-generated method stub
        //获取session用户信息
//		WebContext contxt = WebContextFactory.get();
//		HttpSession session=contxt.getSession();
//		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
//		String user = loginEntity.getUserName();
		
//		模拟登录
		String user="admin";
		//判断aid是否为空，如果为空则操作增加，否则执行更新。
		if(null==actAddition.getApaid()||"".equals(actAddition.getApaid())){
			String sql="CALL pro_getRunningNO('APAID')";
			String  sequence=splitPageDao.findByjkSequence(sql);
			actAddition.setApaid(sequence);		
			actAddition.setCreateTime(DateUtil.newDate());
			actAddition.setModifyTime(new Timestamp(new Date().getTime()));
			actAddition.setCreateUser(user);
			actAddition.setModifyUser(user);
		}
		else{
			//修改前做查询处理,防止未填写字段致空。
			ActivityProjectAdditional actAdditionInfo=getByIdAddition(actAddition.getAid(),actAddition.getApaid());
			//要修改的字段存入bean中
			actAdditionInfo.setName(actAddition.getName());
			actAdditionInfo.setPrice(actAddition.getPrice());
			actAdditionInfo.setContent(actAddition.getContent());
			actAdditionInfo.setEndTime(actAddition.getEndTime());
			actAdditionInfo.setStartTime(actAddition.getStartTime());
			actAdditionInfo.setModifyTime(new Timestamp(new Date().getTime()));
			actAdditionInfo.setModifyUser(user);
			actAddition=actAdditionInfo;
		}
		
		//增加、修改前引入默认楼宇编号
		actAddition.setBid(CommonDefine.JK_BUILDING);
		//增加、修改前引入逻辑删除(默认否)
		actAddition.setIsDelete(CommonDefine.ID_DELETE_FALSE);
		
		splitPageDao.saveOrUpdate(actAddition);
	}

	/*
	 * 
	 * 
	 * 图片上传
	 * @see com.tcj.work.activityManage.service.ActivityManageService#updateImage(java.util.Map)
	 */
	public void updateImage(Map<String, String> param) {
		// TODO Auto-generated method stub
		String aid = MapUtils.getString(param, "aid", "");
		String image = MapUtils.getString(param, "image", "");
		
		System.out.println("Image=================ddd"+image);
		String sql = "UPDATE activity set logo=? where aid=?";
		ArrayList al = new ArrayList();
		al.add(image);
		al.add(aid);
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
