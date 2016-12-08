package com.tcj.work.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.log4j.Log4jSetParam;
import com.tcj.domains.LoginEntity;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.common.service.GetFieldFromTableService;



@Controller("generalMethodAction")
@RemoteProxy(name = "generalMethodAction")
public class GetFieldFromTableAction {
	@Autowired
	@Qualifier("getFieldFromTableImpl")
	private GetFieldFromTableService getFieldFromTableService;
	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	@RemoteMethod  
	public ArrayList<Map> getPagelist(Map map) {
		 List<Map>  pageList=new ArrayList<Map>();
		 ArrayList<Map> lstTree = new ArrayList<Map>();
	   try {
		   LoginEntity entity=authorizeService.getLoginUser();
		   System.out.println("登陆id:"+entity.getUserId());
		   String loginId=entity.getUserId().toString();
		   String loginIp=entity.getIp();
		   map.put("loginId", loginId);
		   map.put("loginIp", loginIp);
		   System.out.println(loginId+"树树树树树树树树");
		   List<Map> lstTmp = (List<Map>)getFieldFromTableService.PageLists(map);
		   getFieldFromTableService.getTree(lstTree, "0",lstTmp);
		} catch (Exception e) {
			e.printStackTrace();
	  }
	   return lstTree;
	 }
	
}
