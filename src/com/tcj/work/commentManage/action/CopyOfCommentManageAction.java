package com.tcj.work.commentManage.action;

import java.util.List;
import java.util.Map;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcj.common.dao.model.Page;
import com.tcj.domains.ActivityComment;
import com.tcj.domains.User;
import com.tcj.work.commentManage.biz.CommentManageBiz;
import com.tcj.work.commentManage.service.CommentManageService;

@Controller
@RequestMapping("/commentManage/comment")
public class CopyOfCommentManageAction {
	@Autowired
	@Qualifier("commentManageBizImpl")
	private CommentManageBiz commentBiz;
	
	private CommentManageService commentManageService;

	/*
	 * 
	 * 查询(条件查询)
	 * 
	 */
	@RemoteMethod
	@RequestMapping(value = "/list")
	public String getList(ModelMap model,Map<String, String> param) {
		
		model.addAttribute("Page", commentBiz.getList(param));
		model.addAttribute("test", "111");
		System.out.println("~~~~~~~~#################################");
		return "work/commentManage/comment";
	}



	
	
	


}
