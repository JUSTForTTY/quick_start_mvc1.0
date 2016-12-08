package com.tcj.work.newsTypeManage.service.impl;

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

import com.jspsmart.upload.Request;
import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.newsTypeManage.service.NewsTypeService;

@Component("newsTypeServiceImpl")
public class NewsTypeServiceImpl implements NewsTypeService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	/*
	 * @see com.tcj.work.demoManage.service#getList() 条件查询：条件查询，使用positional
	 * parameter
	 * 
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public Page getList(Map<String, String> param) throws Exception {
		// String username = MapUtils.getString(param, "username", "");
		Integer pgNumber = MapUtils.getInteger(param, "page",
				Integer.valueOf(1));
		Integer pgSize = MapUtils
				.getInteger(param, "rows", Integer.valueOf(20));
		String id = MapUtils.getString(param, "id").trim().replaceAll("'", "")
				.replaceAll("\''", "");
		String isDelete = MapUtils.getString(param, "isDelete").trim()
				.replaceAll("'", "").replaceAll("\''", "");
		String type = MapUtils.getString(param, "type").trim()
				.replaceAll("'", "").replaceAll("\''", "");
		String name = MapUtils.getString(param, "name").trim()
				.replaceAll("'", "").replaceAll("\''", "");
		String parentName = MapUtils.getString(param, "parent_name").trim()
				.replaceAll("'", "").replaceAll("\''", "");
		String onHome = MapUtils.getString(param, "onHome").trim()
				.replaceAll("'", "").replaceAll("\''", "");
		// a left join Building b on a.bid=b.id where 1=1
		String sql = " select a.caid,a.bid,a.codeNo,a.parent_id,a.name,a.sort,a.description,a.english_name,"
				+ " a.onHomeType,a.grade,a.url,a.icon,b.building_name,c.CodeName,d.name as parent_name,"
				+ " a.create_user,date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ " a.modify_user,date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as modify_time,a.is_delete,"
				+ " (case when a.onHome='1' then '是' else '否' end) as onHome"
				+ " from category a "
				+ " left join building b on a.bid=b.id"
				+ " left join codemaster c on a.codeNo=c.CodeNo"// 新闻类别类型
				+ " left join category d on a.parent_id=d.caid"
				+ " where c.CodeType= ? ";// codemaster表中的CodeType为113指新闻类型
		ArrayList al = new ArrayList();
		al.add(113);// codemaster表中的CodeType为113指新闻类型
		if (isDelete.equals("0")) {// 未删除
			sql += " and a.is_delete= ? ";
			al.add(CommonDefine.ID_DELETE_FALSE);// CommonDefine类中的ID_DELETE_FALSE指未被删除
		} else if (isDelete.equals("1")) {// 已删除
			sql += " and a.is_delete= ? ";
			al.add(CommonDefine.ID_DELETE_TRUE);// CommonDefine类中的ID_DELETE_TRUE指以被删除
		}
		if (!"".equals(id) && id != null) {
			sql += " and a.caid = ? ";
			al.add(id);
		}
		if (!"".equals(type) && type != null) {
			sql += " and a.codeNo = ? ";
			al.add(type);
		}
		if (!"".equals(name) && name != null) {
			sql += " and a.name like  ? ";
			al.add("%" + name + "%");
		}
		if (!"".equals(parentName) && parentName != null) {
			sql += " and a.parent_id = ? ";
			al.add(parentName);
		}
		if (!"".equals(onHome) && onHome != null) {
			sql += " and a.onHome = ? ";
			al.add(onHome);
		}
		sql +=" order by a.caid";
		log.debug("查询：" + sql);
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs, pgNumber, pgSize);

	}

	/*
	 * @see com.tcj.work.demoManage.service#delete() 删除（逻辑删除）
	 * 
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public void delete(Map<String, String> param) throws Exception {
		String flag = MapUtils.getString(param, "flag");
		String ids = MapUtils.getString(param, "ids");
		String sql = "update category set is_delete=?,modify_user=?,modify_time=? where caid in ";
		ArrayList al = new ArrayList();
		log.debug("查看状态标记：" + flag);
		if (flag.equals("1")) {// 删除
			al.add(CommonDefine.ID_DELETE_TRUE);// //CommonDefine类中的ID_DELETE_TRUE指已被删除
		} else if (flag.equals("0")) {// 恢复
			al.add(CommonDefine.ID_DELETE_FALSE);// //CommonDefine类中的
													// ID_DELETE_FALSE指未被删除
		}

		WebContext contxt = WebContextFactory.get();
		HttpSession session = contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session
				.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();// 创建或修改人
		log.debug("更新人：" + user);
		al.add(user);// 更新人

		Date modifytime = new Timestamp(new Date().getTime());
		al.add(modifytime);// 修改时间

		String a[] = ids.split(",");// ids是字符串内容为1,2,3,4,5，将它们之间的逗号去除
		for (String str : a) {// 循环遍历数组a
			al.add(str);// 将数组al中的值即要删除的id逐个放入list即al中
		}

		Object[] objs = al.toArray();// 将list转换为数组

		sql = new BatchTool().batchDeleteSql(sql, objs);
		log.debug("删除sql" + sql);
		splitPageDao.deleteAll(sql, objs);
	}

	/*
	 * @see com.tcj.work.demoManage.service#getById() 更具ID查询
	 */
	public Object getNewsTypeById(String id) throws Exception {
		String sql = " select a.caid,a.bid,a.codeNo,a.name,a.sort,a.description,onHome,is_delete,"
				+ " (case  when a.parent_id='-1' then ''  else a.parent_id end) as parent_id,"// 父类别值为-1则不显示
				+ " a.onHomeType,a.grade,a.url,a.icon,"
				+ " a.create_user,date_format(a.create_time,'%Y-%m-%d %H:%i:%s') as create_time,"
				+ " a.modify_user,date_format(a.modify_time,'%Y-%m-%d %H:%i:%s') as modify_time"
				+ " from category a " + " where caid='" + id + "'";
		log.debug("查看详情" + sql);
		List list = splitPageDao.findBySql(sql);
		log.debug("查看长度：" + list.size());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * @see com.tcj.work.demoManage.service#save() 新增 使用sequence增加STRING类型的ID
	 */
	public void saveNewsType(Category category) throws Exception {
//		 验证新闻类别名重名
		 String hql = "";
		 String caidJge = category.getCaid();
		 String name = category.getName();
		 if (caidJge != null && !"".equals(caidJge)) {// 修改
		 hql += " from Category where caid != '" + caidJge
		 + "' and is_delete = '" + CommonDefine.ID_DELETE_FALSE
		 + "'" + " and name = '" + name+"'";
		 } else {// 新增
		 hql += " from Category where is_delete = '"
		 + CommonDefine.ID_DELETE_FALSE + "'" + " and name = '"
		 + name+"'";
		 }
		 List list = splitPageDao.findByHql(hql);
		 if (list.size() > 0) {
		 log.debug("该新闻类别已存在，请重新输入");
		 throw new EgladServiceException("该新闻类别已存在，请重新输入");
		 }
        String caid = category.getCaid();//新闻类别id
		if (caid==null||"".equals(caid)) {// 新增
			String sql = "CALL pro_getRunningNO('CAID')";
			String caidNew = splitPageDao.findByjkSequence(sql);
			category.setCaid(caidNew);
		}
		this.splitPageDao.saveOrUpdate(category);
	}

	@Override
	public List getCategoryType(Map param) {
		// TODO Auto-generated method stub
		String sql = "SELECT codeNo AS VAL,codeName AS TEXT FROM codemaster where CodeType= ? and is_delete=?";
		ArrayList al = new ArrayList();
		al.add(113);// CodeType为113指新闻类别类型
		al.add(CommonDefine.ID_DELETE_FALSE);
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs);
	}

	@Override
	public List getParentName(Map param) {
		// TODO Auto-generated method stub
		String sql = "SELECT caid AS VAL,name AS TEXT FROM category where parent_id= ? and is_delete= ? ";
		ArrayList al = new ArrayList();
		al.add(-1);// -1指父类别
		al.add(CommonDefine.ID_DELETE_FALSE);
		Object[] objs = al.toArray();
		return splitPageDao.findBySql(sql, objs);
	}

}