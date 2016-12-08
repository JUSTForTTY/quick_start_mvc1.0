
package com.tcj.work.messageManage.action;

import com.tcj.work.activityManage.action.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.EnumActivityStatus;
import com.tcj.common.enums.EnumActivityStick;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.activityManage.biz.ActivityManageBiz;
import com.tcj.work.demoManage.biz.*;
import com.tcj.work.demoManage.service.*;
import com.tcj.work.messageManage.biz.MessageManageBiz;

/**
 * @dscription 商品评论、会员单位留言数据维护 
 * @author frb
 * @date 2016/11/12 
 * @version 1.0
 * @history
 */
@Controller("messageManageAction")
@RemoteProxy(name = "messageManageAction")//父类路径
public class MessageManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("messageManageBizImpl")
	private MessageManageBiz messageManageBiz;

	/*
	 * 
	 * 查询商品评论信息
	 */
	@RemoteMethod
	public Page getListComment(Map<String, String> param) throws EgladServiceException {
			  
		return this.messageManageBiz.getListComment(param);
	}
	/*
	 * 
	 * 查询会员单位留言
	 */
	@RemoteMethod
	public Page getListMessage(Map<String, String> param) throws EgladServiceException {
		
		return this.messageManageBiz.getListMessage(param);
	}
	
}