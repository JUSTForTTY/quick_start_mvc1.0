package com.tcj.work.commentManage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.CommentStatusEnum;
import com.tcj.common.enums.CommentStickEnum;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.ActivityComment;
import com.tcj.work.commentManage.service.CommentManageService;


@Component("commentManageServiceImpl")
public class CommentManageServiceImpl implements CommentManageService {
	
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	/*
	 * @see com.tcj.work.demoManage.service#getList()
	 * 条件查询：条件查询，使用positional parameter
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public List getList(Map<String, String> param) throws Exception {
		ArrayList al = new ArrayList();
		//String username = MapUtils.getString(param, "username", "");
		Integer pgNumber = MapUtils.getInteger(param, "page", Integer.valueOf(1));
		Integer pgSize = MapUtils.getInteger(param, "rows", Integer.valueOf(3));
		//String hql = "from ActivityComment where 1=1";
		 String sql="SELECT a.acid AS acid,a.aid AS aid,a.parent_id AS parent_id,a.content AS content,a.nickname AS nickname,a.status AS `status`,a.stick AS stick,CAST(a.create_time AS char(20)) AS create_time,a.agree_num AS agree_num,a.disagree_num AS disagree_num,u.username AS username,u.image AS image,a2.`status` AS r_status,a2.acid AS r_acid,a2.parent_id AS r_parent_id,a2.nickname AS r_nickname,a2.content AS r_content,CAST(a2.create_time AS char(20)) AS r_create_time,a2.agree_num AS r_agree_num,a2.disagree_num AS r_disagree_num,u2.image AS r_image,u2.username AS r_username " +
		 		"FROM activity_comment a " +
		 		"LEFT JOIN `user` u " +
		 		"ON a.create_user=u.uid " +
		 		"LEFT JOIN activity_comment a2 " +
		 		"ON a.acid=a2.parent_id " +
		 		"LEFT JOIN `user` u2 " +
		 		"ON a2.create_user=u2.uid " +
		 		"JOIN (SELECT acid FROM activity_comment WHERE parent_id='-1' " +
		 		"and aid=?";
		 		al.add(MapUtils.getString(param, "aid"));	
		 		if(!"".equals(MapUtils.getString(param, "con"))){
				    sql+= " and status <> ? ";	    
				    al.add(CommentStatusEnum.STATUS_DELETE.getCode());
				 	}
		 		sql+="ORDER BY stick DESC, create_time DESC LIMIT ?,?) ac " +
		 		"ON ac.acid = a.acid " + 		
		 		"where a.parent_id='-1' and a.aid=? ";
		 		al.add((pgNumber-1)*pgSize);
		 		al.add(pgSize);
		 		al.add(MapUtils.getString(param, "aid"));
		 	
		    //sql+= " and (a2.`status`<>? or a2.`status` is null)";
		    //al.add(CommentStatusEnum.STATUS_DELETE.getCode());
		    
		 sql+=" ORDER BY a.stick DESC,a.create_time DESC,a2.create_time ASC";
		
		 
		 Object[] objs = al.toArray();
//		return splitPageDao.findBySql(sql, objs, pgNumber.intValue(), pgSize.intValue());
		
		return splitPageDao.findBySql(sql, objs);
	}

	/*
	 * @see com.tcj.work.demoManage.service#delete()
	 * 删除（逻辑删除）
	 * @ Object[] objs ,参数数组，和sql中的参数位置要对应。
	 */
	public void delete(Map<String, String> param) throws Exception {
		//int status=CommentStatusEnum.STATUS_DELETE.getCode();
		
		String sql="update activity_comment set status=?,modify_user=?,modify_time=? where acid=?";
	  	Object[] objs={MapUtils.getString(param, "status"),MapUtils.getString(param, "modify_user",""),new Timestamp(new Date().getTime()),MapUtils.getString(param, "id","")};
	    
	  	this.splitPageDao.deleteAll(sql, objs);
	}

	/*
	 * @see com.tcj.work.demoManage.service#getById()
	 * ID查询
	 * 
	 */


	/*
	 * @see com.tcj.work.demoManage.service#save()
	 * 新增
	 * 使用sequence增加STRING类型的ID
	 */
	public List saveOrUpdate(Map<String, String> param) throws Exception {
	
		String sql = "CALL pro_getRunningNO('ACID')";
		Integer sequence = splitPageDao.findBySequence(sql);
		
		ActivityComment comment = new ActivityComment();
		comment.setParentId(MapUtils.getString(param, "parent_id"));
		comment.setAcid(sequence.toString());
		comment.setAid(MapUtils.getString(param,"aid"));
		comment.setContent(MapUtils.getString(param, "content"));
		comment.setCmid(MapUtils.getString(param, "userId"));
		
		comment.setStick(CommentStickEnum.STICK_NO.getCode());
		comment.setStatus(CommentStatusEnum.STATUS_UNCHECKED.getCode());
		comment.setDisagreeNum(0);
		comment.setAgreeNum(0);
		
		
		Date date=new Date();
		comment.setCreateTime(date);
		comment.setCreateUser(MapUtils.getString(param, "userId"));
		comment.setModifyTime(date);
		comment.setModifyUser(MapUtils.getString(param, "userId"));
		
		
		
		splitPageDao.saveOrUpdate(comment);
		
		sql = "SELECT a.acid AS acid,CAST(a.create_time AS CHAR(20))AS create_time,a.nickname AS nickname,u.image AS image,u.username AS username " +
			  "FROM activity_comment a " +
			  "LEFT JOIN `user` u " +
			  "ON a.create_user=u.uid " +
			  "WHERE acid="+sequence.toString();
	
		return splitPageDao.findBySql(sql);
		
		
	}


	@Override
	public void update(ActivityComment activityComment,String label) throws Exception {
		// TODO Auto-generated method stub
		int value;
		if(label.equalsIgnoreCase("jh")){
			label="status";
			value=activityComment.getStatus();
		}else{
			label="stick";
			value=activityComment.getStick();
		}
			
		String sql="update activity_comment set "+label+"=?,modify_user=?,modify_time=? where acid=?";
		Object[] objs={value,activityComment.getModifyUser(),activityComment.getModifyTime(),activityComment.getAcid()};
		
		this.splitPageDao.executeSql(sql, objs);
		
	}

	public List countList(Map<String, String> param) throws Exception {
		
		ArrayList al = new ArrayList();
		String sql="SELECT COUNT(*) AS com_num,COUNT(DISTINCT create_user) AS member FROM activity_comment where 1=1";
		if(!"all".equalsIgnoreCase(MapUtils.getString(param, "con"))){
			 sql+= " and status <> ?";	    
			 al.add(CommentStatusEnum.STATUS_DELETE.getCode());
		}
		
		sql+=" and aid=?";
		al.add(MapUtils.getString(param, "aid"));
		
		Object[] objs = al.toArray();	
		
		return this.splitPageDao.findBySql(sql,objs);
		
	}

	@Override
	public List getActivityName(Map<String, String> param) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		String sql="SELECT name FROM activity WHERE aid=?";
		al.add(MapUtils.getString(param, "aid"));
		Object[] objs = al.toArray();	
		
		return this.splitPageDao.findBySql(sql,objs);
	}

	@Override
	public Page getBestList(Map<String, String> param) {
		// TODO Auto-generated method stub
		String sql="Select a.acid AS acid,a.nickname AS nickname,a.content AS content,u.username AS username " +
				"FROM activity_comment a " +
				"LEFT JOIN user u " +
				"ON  a.create_user=u.uid " +
				"WHERE aid=? and a.parent_id='-1' AND a.`status` =? " +
				"ORDER BY a.create_time DESC";
		Object[] objs ={MapUtils.getString(param, "aid"),CommentStatusEnum.STATUS_BEST.getCode()}; 	
		
		return this.splitPageDao.findBySql(sql, objs,1, 10);
	}


}