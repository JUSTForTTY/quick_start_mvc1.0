package com.tcj.work.commentManage.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.ActivityComment;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.User;
import com.tcj.work.commentManage.biz.CommentManageBiz;
import com.tcj.work.commentManage.service.CommentManageService;

@Controller("commentManageAction")
@RemoteProxy(name = "commentManageAction")
public class CommentManageAction {
	@Autowired
	@Qualifier("commentManageBizImpl")
	private CommentManageBiz commentBiz;


	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	public List getList(Map<String, String> param) {
		return commentBiz.getList(param);
	}
	
	@RemoteMethod
	public List getActivityName(Map<String, String> param) {
		return commentBiz.getActivityName(param);
	}
	
	@RemoteMethod
	public Page getBestList(Map<String, String> param) {
		return commentBiz.getBestList(param);
	}

	@RemoteMethod
	public ResultBean update(Map<String, String> param,HttpSession session) {
		
		LoginEntity loginEntity=(LoginEntity)session.getAttribute("LogInDemoEntity");
		param.put("userId",loginEntity.getUserId());
		return commentBiz.update(param);
	}
	
	@RemoteMethod
	public ResultBean deletes(Map<String, String> param,HttpSession session) {
		
		LoginEntity loginEntity=(LoginEntity)session.getAttribute("LogInDemoEntity");
		param.put("modify_user",loginEntity.getUserId());
		return commentBiz.delete(param);
	}
	
	
	@RemoteMethod
	public List countList(Map<String, String> param) {
	
		return commentBiz.countList(param);
	}

	@RemoteMethod
	public List save(Map<String, String> param,HttpSession session) {
		
		LoginEntity loginEntity=(LoginEntity)session.getAttribute("LogInDemoEntity");
		param.put("userId",loginEntity.getUserId());
		
		return commentBiz.save(param);
	
	}
	


}
