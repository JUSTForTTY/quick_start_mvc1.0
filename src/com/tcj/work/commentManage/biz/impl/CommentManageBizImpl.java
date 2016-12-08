package com.tcj.work.commentManage.biz.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.CommentStatusEnum;
import com.tcj.common.enums.CommentStickEnum;
import com.tcj.domains.ActivityComment;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.commentManage.biz.CommentManageBiz;
import com.tcj.work.commentManage.service.CommentManageService;
import com.tcj.work.demoManage.service.DemoManageService;


/**
 * Biz层.
 * 
 * @author tty 1547970885@qq.com
 * @date 2016-7-21 下午1:20:15
 * @version 1.0
 * Biz层处理业务逻辑，例如计算、加密等工作。
 */
@Component("commentManageBizImpl")
public class CommentManageBizImpl implements CommentManageBiz {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("commentManageServiceImpl")
	private CommentManageService commentManageService;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	
	
	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public List getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			return commentManageService.getList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/*
	 * 
	 * 更新
	 * 
	 */
	@Override
	public ResultBean update(Map<String, String> param) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try{
		ActivityComment activityComment=new ActivityComment();
		activityComment.setAcid(MapUtils.getString(param,"acid",""));
		
		String label=MapUtils.getString(param, "label");
		if(label.equalsIgnoreCase("jh")){
			if(MapUtils.getShort(param, "value")==1)
				activityComment.setStatus(CommentStatusEnum.STATUS_CHECKED.getCode());	
			else activityComment.setStatus(CommentStatusEnum.STATUS_BEST.getCode());	
		}		
		else if(label.equalsIgnoreCase("zd")){
			if(MapUtils.getShort(param, "value")==1)
				activityComment.setStick(CommentStickEnum.STICK_NO.getCode());
			else activityComment.setStick(CommentStickEnum.STICK_YES.getCode());
		}
		//String userId="jk87";
		//userId=authorizeService.getLoginUser().getUserId();
		String	userId=MapUtils.getString(param, "userId");
		activityComment.setModifyUser(userId);
		activityComment.setModifyTime(new Timestamp(new Date().getTime()));
		commentManageService.update(activityComment,label);
		
		resultBean.setData(activityComment);
		resultBean.setSuccess(true);
		}
		catch(Exception e){
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	
	/*
	 * 
	 * 删除
	 * 
	 */
	@Override
	public ResultBean delete(Map<String, String> param) {
		// TODO Auto-generated method stub
		ResultBean resultBean =new ResultBean();
		try{
			
			String userId="jk87";
			//userId=authorizeService.getLoginUser().getUserId();
			//param.put("modify_user", userId);
			commentManageService.delete(param);
		   resultBean.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			resultBean.setSuccess(false);			
		}
		
		return resultBean;
	}


	@Override
	public List countList(Map<String, String> param) {
		// TODO Auto-generated method stub
		
		try {
			
			 return commentManageService.countList(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}


	@Override
	public List save(Map<String, String> param) {
		// TODO Auto-generated method stub
		try {
			
			//String userId="jk87";
			//userId=authorizeService.getLoginUser().getUserId();
			//param.put("userId", userId);
			
			return commentManageService.saveOrUpdate(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List getActivityName(Map<String, String> param) {
		// TODO Auto-generated method stub
		return commentManageService.getActivityName(param);
	}

	
	/*
	 * 
	 * 查询，精华帖
	 * 
	 */
	public Page getBestList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return commentManageService.getBestList(param);
	}



}
