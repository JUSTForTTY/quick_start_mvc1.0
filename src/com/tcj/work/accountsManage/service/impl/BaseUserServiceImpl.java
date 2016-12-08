package com.tcj.work.accountsManage.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.util.MD5;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.MaritimeManage;
import com.tcj.domains.User;
import com.tcj.domains.UserAdmin;
import com.tcj.work.accountsManage.service.BaseUserService;
import com.tcj.work.authorize.service.IAuthorizeService;

@Component("baseUserService")
public class BaseUserServiceImpl implements BaseUserService {
	@Autowired()
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	private Log log = LogFactory.getLog(getClass());

	private String userTypes[] = new String[] { "管理员:", "海事所管理员:" };

	
	/***
	 * 查询用户类别
	 */
	// public List getUserType(Map param) throws EgladServiceException {
	// String
	// sql="SELECT USER_TYPE_ID AS VAL,USER_TYPE_NAME AS TEXT,USER_TYPE_FLAG FROM t_base_user_type WHERE IS_DELETED='0' ";
	// return splitPageDao.findBySql(sql);
	// }
	/***
	 * 查询用户角色
	 */
	// public List getSysRole(Map param) throws EgladServiceException {
	// String
	// sql="SELECT ROLE_ID AS VAL,ROLE_NAME AS TEXT FROM t_sys_role WHERE IS_DELETED='0' ";
	// return splitPageDao.findBySql(sql);
	// }

	/**
	 * 检查 工号是否存在
	 */
	// public void checkLoginId(Map param) throws EgladServiceException{
	// String
	// sql="SELECT * FROM t_base_user WHERE LOGIN_ID='"+(param.get("loginId")+"").replaceAll("'",
	// "''")+"'";
	// if(!"".equals(MapUtils.getString(param, "userId",""))){
	// sql+=" AND USER_ID !="+param.get("userId");
	// }
	// List list=splitPageDao.findBySql(sql);
	// if (list.size()>0) {
	// throw new EgladServiceException("账号已存在！");
	// }
	// }

	// public List<TreeNode> getBaseOrg(Map map) throws EgladServiceException{
	// String hql="from BaseOrg ";
	// List<BaseOrg> menuList=splitPageDao.findByHql(hql);
	// TreeNode firstTree=new TreeNode();
	// firstTree.setId("");
	// firstTree.setText("组织结构");
	// List<TreeNode> alltrees=new ArrayList();
	// List<TreeNode> trees=new ArrayList();
	// for(BaseOrg obj:menuList){
	// TreeNode tree=new TreeNode();
	// tree.setId(obj.getOrgId()+"");
	// tree.setText(obj.getOrgName());
	// if(obj.getfOrgId()!=null){
	// tree.setParentId(obj.getfOrgId()+"");
	// }
	// alltrees.add(tree);
	// }
	// for(TreeNode t:alltrees){
	// for(TreeNode tc:alltrees){
	// if(tc.getParentId()==null){
	//
	// }
	// else if(tc.getParentId().equals(t.getId())){
	// t.addChild(tc);
	// }
	// }
	// if(t.getParentId()==null){
	// firstTree.addChild(t);
	// }
	// }
	// trees.add(firstTree);
	// return trees;
	// }
	/***
	 * 登录
	 * 
	 * @throws UnknownHostException
	 */
	@Override
	public boolean saveLoginInfo(Map map,HttpSession session1)
			throws Exception {
		String loginId = MapUtils.getString(map, "loginId", "");
		loginId = loginId.replace("'", "''");
		 String imageCode=MapUtils.getString(map, "imageCode","");//输入的验证码
		 String sRtn=MapUtils.getString(map, "sRnd","");//session中验证码
		 //验证码判断
		 if (!imageCode.equals(sRtn)) {
		 throw new EgladServiceException("验证码错误！");
		 }
		String hql = " FROM UserAdmin WHERE username='" 
		 + loginId + "' and usertype in (3,4,5) and Is_Deleted = 0";
		List<UserAdmin> list = splitPageDao.findByHql(hql);
		String hql1="";
		if (list.size() > 0) {
			if(!"".equals(list.get(0).getMarineId())&&list.get(0).getMarineId()!=null){
				hql1 = " from MaritimeManage where marine_id='"+list.get(0).getMarineId()+"' and IS_DELETED = 0";
				System.out.println(hql1);
				List<MaritimeManage> list1 = splitPageDao.findByHql(hql1);
				if(list1.size()>0){
					UserAdmin user = list.get(0);
					System.out.println("---user:"+user.toString());
					if (user.getPasswd().equals(
							MD5.Create(MapUtils.getString(map, "loginPwd")))) {
						// 是否冻结或删除
						if (("0").equals(user.getStatus() + "")) {
							throw new EgladServiceException("账号冻结或已删除！");
						}
						if(user.getUsertype()>2&&user.getUsertype()<6){
							// 放入session
							WebContext context = WebContextFactory.get();
							HttpSession session = context.getSession();
							LoginEntity entity = new LoginEntity();
							entity.setUserId(user.getId().toString());
							System.out.println("usertype:"+user.getUsertype());
							if(user.getUsertype()==5){
								MaritimeManage maritime = getMaritimeById(user.getMarineId());
								entity.setUserName(maritime.getMaritimeName()+"管理员："+user.getUsername());
								session.setAttribute("maritimeID", maritime.getMarine_id());
							}else{
								entity.setUserName("管理员："+user.getUsername());
							}

							InetAddress addr = InetAddress.getLocalHost();
							String ip = addr.getHostAddress();
							entity.setIp(ip);

							entity.setUserTypeFlag(user.getUsertype() + "");
							System.out.println("loginEntity:" + entity.toString());
							
							session.setAttribute("LogInEntity", entity);
//							session.setAttribute("maritimeID", getMaritimeId(user.getMobile()));

							// 更新登录数字和登录时间
							// Integer loginTimes = user.getLoginTimes();
							// if (loginTimes == null) {
							// loginTimes = 0;
							// }
							// user.setLoginTimes(loginTimes + 1);
							// user.setLastLoginTime(new Date());
							// splitPageDao.saveOrUpdate(user);
							// splitPageDao.getSession().flush();
							// String sql = "update t_base_user set LAST_LOGIN_TIME='"
							// + DateUtil.format(user.getLastLoginTime(),
							// "yyyy-MM-dd HH:mm:ss")
							// + "',LOGIN_TIMES=(ifnull(LOGIN_TIMES,0)+1) where USER_ID='"
							// + user.getUserId() + "'";
							// splitPageDao.excuteSql(sql);
							return true;
						}
						
					}
					
				}else{
					throw new EgladServiceException("此账号不存在所属海事所！");
				}
			}else{
			
			UserAdmin user = list.get(0);
			System.out.println("---user:"+user.toString());
			if (user.getPasswd().equals(
					MD5.Create(MapUtils.getString(map, "loginPwd")))) {
				// 是否冻结或删除
				if (("0").equals(user.getStatus() + "")) {
					throw new EgladServiceException("账号冻结或已删除！");
				}
				if(user.getUsertype()>2&&user.getUsertype()<6){
					// 放入session
					WebContext context = WebContextFactory.get();
					HttpSession session = context.getSession();
					LoginEntity entity = new LoginEntity();
					entity.setUserId(user.getId().toString());
					System.out.println("usertype:"+user.getUsertype());
					if(user.getUsertype()==5){
						MaritimeManage maritime = getMaritimeById(user.getMarineId());
						entity.setUserName(maritime.getMaritimeName()+"管理员："+user.getUsername());
						session.setAttribute("maritimeID", maritime.getMarine_id());
					}else{
						entity.setUserName("管理员："+user.getUsername());
					}

					InetAddress addr = InetAddress.getLocalHost();
					String ip = addr.getHostAddress();
					entity.setIp(ip);

					entity.setUserTypeFlag(user.getUsertype() + "");
					System.out.println("loginEntity:" + entity.toString());
					
					session.setAttribute("LogInEntity", entity);
//					session.setAttribute("maritimeID", getMaritimeId(user.getMobile()));

					// 更新登录数字和登录时间
					// Integer loginTimes = user.getLoginTimes();
					// if (loginTimes == null) {
					// loginTimes = 0;
					// }
					// user.setLoginTimes(loginTimes + 1);
					// user.setLastLoginTime(new Date());
					// splitPageDao.saveOrUpdate(user);
					// splitPageDao.getSession().flush();
					// String sql = "update t_base_user set LAST_LOGIN_TIME='"
					// + DateUtil.format(user.getLastLoginTime(),
					// "yyyy-MM-dd HH:mm:ss")
					// + "',LOGIN_TIMES=(ifnull(LOGIN_TIMES,0)+1) where USER_ID='"
					// + user.getUserId() + "'";
					// splitPageDao.excuteSql(sql);
					return true;
				}
				
			   }
			}
		}
		throw new EgladServiceException("账号或密码错误！");
	}
	
	/**
	 * @param id
	 * 		maritimeID
	 * @return 
	 */
	private MaritimeManage getMaritimeById(String id){
		String hql = " from MaritimeManage where marine_id = '"+id+"'";
		List<MaritimeManage> list = splitPageDao.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


	/***
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void updateBaseUserPwd(Map map) throws Exception {
		LoginEntity entity = authorizeService.getLoginUser();
		String hql = " from User where uid='" + entity.getUserId()
				+ "' ";
		List<User> baseUsers = splitPageDao.findByHql(hql);
		System.out.println("：：：：：：：：：：：；；::,"+baseUsers.size());
		if (baseUsers.size() > 0) {
			User baseUser = baseUsers.get(0);
			String pwd = MD5.Create(MapUtils.getString(map, "pwd", ""));
			String newPwd = MD5.Create(MapUtils.getString(map, "newPwd", ""));
			log.debug("orPwd:" + pwd);
			log.debug("newPwd:" + newPwd);
			if (pwd.equals(baseUser.getPasswd())) {
				String sql = "update user set passwd = '" + newPwd
						+ "' where uid = '" + baseUser.getUid() + "' ";
				splitPageDao.excuteSql(sql);
				// baseUser.setPasswd(newPwd);
				// splitPageDao.saveOrUpdate(baseUser);
				// splitPageDao.getSession().flush();
			} else {
				throw new EgladServiceException("原密码错误，请重新输入！");
			}
		} else {
			throw new EgladServiceException("登录超时，请重新登录！");
		}
	}

	// public String getUserServiceCenter(HttpServletRequest request) throws
	// Exception {
	// String path="";
	// LoginEntity entity=authorizeService.getLoginUser();
	// String
	// sql="select SERVICE_PHOTO_PATH  FROM t_base_user a,t_base_service_center b "
	// +
	// " WHERE a.SERVICE_CENTER_ID=b.SERVICE_CENTER_ID and USER_ID='"+entity.getUserId()+"'";
	// List<Map> list=splitPageDao.findBySql(sql);
	// if (list.size()>0) {
	// Map map=list.get(0);
	// path=MapUtils.getString(map, "SERVICE_PHOTO_PATH","");
	// if(!path.equals("")){
	// String nowPath =
	// request.getSession().getServletContext().getRealPath("/");
	// String filePath=nowPath+path;
	// File headImage=new File(filePath);
	// if(!headImage.exists()){
	// path="/images/headImages/defaultScLogo.png";
	// }
	// }else{
	// path="/images/headImages/defaultScLogo.png";
	// }
	// }else{
	// path="/images/headImages/defaultScLogo.png";
	// }
	// return path;
	// }

	// public Map getUserServiceCenterById(String id, HttpServletRequest
	// request) throws Exception{
	// String path="";
	// String
	// sql="select SERVICE_PHOTO_PATH ,SERVICE_CENTER_NAME FROM t_base_service_center b "
	// + " WHERE b.SERVICE_CENTER_ID ='"+id+"'";
	// List<Map> list=splitPageDao.findBySql(sql);
	// Map map=new HashMap();
	// map.put("name", "");
	// if (list.size()>0) {
	// Map mm=list.get(0);
	// path=MapUtils.getString(mm, "SERVICE_PHOTO_PATH","");
	// map.put("name", MapUtils.getString(mm, "SERVICE_CENTER_NAME",""));
	// if(!path.equals("")){
	// String nowPath =
	// request.getSession().getServletContext().getRealPath("/");
	// String filePath=nowPath+path;
	// File headImage=new File(filePath);
	// if(!headImage.exists()){
	// path="/images/headImages/defaultScLogo.png";
	// }
	// }else{
	// path="/images/headImages/defaultScLogo.png";
	// }
	// }else{
	// path="/images/headImages/defaultScLogo.png";
	// }
	// map.put("path", path);
	//
	// return map;
	// }

	/***
	 * 查询未读信息条数据
	 */
	// public Integer getCount(Map map) throws Exception{
	// LoginEntity entity=authorizeService.getLoginUser();
	// String sql="select count(*) num from t_sys_message A ";
	// //平台用户
	// if(entity.getUserTypeFlag().equals("1")){
	//
	// }else{
	// sql+=" AND A.RECEIVE_USER_ID="+entity.getUserId();
	// }
	// List<Map> list=splitPageDao.findBySql(sql);
	// if (list.size()>0) {
	// return MapUtils.getInteger(list.get(0),"num",0);
	// }
	// return 0;
	// }
}
