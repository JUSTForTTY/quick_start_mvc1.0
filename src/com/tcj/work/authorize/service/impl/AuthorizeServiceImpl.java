package com.tcj.work.authorize.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.BaseDao;
import com.tcj.domains.LoginEntity;
import com.tcj.work.authorize.service.IAuthorizeService;

@Controller("authorizeService")
public class AuthorizeServiceImpl implements IAuthorizeService {
	private BaseDao baseDao;
	public LoginEntity getLoginUser() {
		WebContext context = WebContextFactory.get();
        HttpSession session = context.getSession();
        System.out.println("AuthorizeServiceImpl session id=" + session.getId());
        LoginEntity entity=null;
        try {
        	 entity=(LoginEntity) session.getAttribute("LogInDemoEntity");
             if(entity==null){
             	throw new EgladServiceException("登录超时，请重新登录！");
             }
		} catch (Exception e) {
			//throw new EgladServiceException("登录超时，请重新登录！");
		}
       
        return entity;
	}
}
