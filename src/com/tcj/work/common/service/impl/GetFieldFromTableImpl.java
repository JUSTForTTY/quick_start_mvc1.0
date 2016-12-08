package com.tcj.work.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.domains.LoginEntity;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.common.service.GetFieldFromTableService;




@Controller("getFieldFromTableImpl")
public class GetFieldFromTableImpl implements GetFieldFromTableService{
	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;
	/*@Autowired
	@Qualifier("authorizeService")
	private AuthorizeServiceImpl authorizeService;*/
	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	public List<Map> PageLists(Map map) {
		String sql = "";

		try {
			LoginEntity entity=authorizeService.getLoginUser();

			//if(roleId.equals(entity.getUserTypeFlag())){
			sql="SELECT A.MENU_ID ID,A.MENU_NAME TEXT,A.MENU_SRC LINK, A.MENU_ID VALUE,IFNULL(A.F_MENU_ID,'0')F_MENU_ID,A.SORT";
			sql+=" FROM  t_sys_menu A INNER JOIN t_sys_role_menu B ON(B.FUNCTION_ID=A.MENU_ID) order by sort";
			System.out.println("菜单查看："+sql);
			//}
		} catch (EgladServiceException e) {
			e.printStackTrace();
		}



		try {
			LoginEntity entity=authorizeService.getLoginUser();
			String userType=entity.getUserTypeFlag();
		
			String role="";
			//超级管理员权限范围
			if("0".equals(userType)){
				role="B.ROlE_ID=0  ";
			}
			//物业快车网管理员权限范围
			if("1".equals(userType)){
				role="B.ROlE_ID=1 ";
			}
			//会员单位管理员权限范围
			if("2".equals(entity.getUserTypeFlag())){
				role="B.ROlE_ID=2 ";
			}
			//社团管理员权限范围
			if("3".equals(entity.getUserTypeFlag())){
				role="B.ROlE_ID=3";
			}
	
			sql="SELECT A.MENU_ID ID,A.MENU_NAME TEXT,A.MENU_SRC LINK, A.MENU_ID VALUE,IFNULL(A.F_MENU_ID,'0')F_MENU_ID,A.SORT";
			sql+=" FROM  t_sys_menu A INNER JOIN t_sys_role_menu B ON(B.FUNCTION_ID=A.MENU_ID) "
					+ "WHERE "+role+" "
					+ "order by sort";

		} catch (EgladServiceException e) {
			e.printStackTrace();
		}



		String loginId=map.get("loginId").toString();
		String loginIp=(String) map.get("loginIp");


		//		sql="SELECT A.MENU_ID ID,A.MENU_NAME TEXT,A.MENU_SRC LINK, A.MENU_ID VALUE,IFNULL(A.F_MENU_ID,'0')F_MENU_ID,A.SORT";
		//		sql+=" FROM t_sys_menu A";

		List<Map> lstTmp = (List<Map>)baseDao.findBySql(sql);
		for(int i =0; i<lstTmp.size(); i++){
			lstTmp.get(i).put("f_menu_id", lstTmp.get(i).get("F_MENU_ID"));
			lstTmp.get(i).remove("F_MENU_ID");
			lstTmp.get(i).put("value", lstTmp.get(i).get("VALUE"));
			lstTmp.get(i).remove("VALUE");
			lstTmp.get(i).put("id", lstTmp.get(i).get("ID"));
			lstTmp.get(i).remove("ID");
			lstTmp.get(i).put("text", lstTmp.get(i).get("TEXT"));
			lstTmp.get(i).remove("TEXT");
			lstTmp.get(i).put("src", lstTmp.get(i).get("SRC"));
			lstTmp.get(i).remove("SRC");
			lstTmp.get(i).put("link", lstTmp.get(i).get("LINK"));
			lstTmp.get(i).remove("LINK");
			lstTmp.get(i).put("showcheck", true);
			lstTmp.get(i).put("checkstate", 0);
			lstTmp.get(i).put("complete", true);
			lstTmp.get(i).put("isexpand", true);
			lstTmp.get(i).put("hasChildren", true);

		}
		System.out.println(lstTmp);
		return lstTmp;
	}

	public void getTree(ArrayList<Map> list,String PID,List<Map> PageLists){
		ArrayList<Map> searchList = getItem(PageLists,PID);
		if(searchList.size()>0){
			for(int i=0;i<searchList.size();i++){
				Map mapItem = searchList.get(i);				
				list.add(mapItem);
				mapItem.put("ChildNodes", new ArrayList<Map>());
				getTree((ArrayList<Map>)mapItem.get("ChildNodes"),mapItem.get("id").toString(),PageLists);
				ArrayList<Map> childList = (ArrayList<Map>)mapItem.get("ChildNodes");
				if(childList.size() > 0){
					mapItem.put("hasChildren",true);
				}
				else{
					mapItem.put("hasChildren",false);
				}
			}
		}
	}

	//从list找子项
	public ArrayList<Map> getItem(List<Map> list,String PID)
	{
		ArrayList<Map> searchList = new ArrayList<Map>();
		for(int i = 0;i < list.size(); i ++){
			Map cMap = list.get(i);
			if(cMap.get("f_menu_id").equals(PID)){
				searchList.add(cMap);
				continue;
			}
		}
		return searchList;
	}

	/*public List<Map> getCommonFunctions(Map map){
		String sql="";
		return new ArrayList<Map>();
	}*/
}
