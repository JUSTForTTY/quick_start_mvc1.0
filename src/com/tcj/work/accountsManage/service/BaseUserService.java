package com.tcj.work.accountsManage.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.basicsInfo.BaseUser;
import com.tcj.domains.tree.TreeNode;

public interface BaseUserService {
	//public Page getBaseUserInfos(Map param) throws EgladServiceException;
	
	//public BaseUser getBaseUserInfoById(Map param,HttpServletRequest request) throws EgladServiceException;
	
	//public void  saveBaseUserInfo(BaseUser baseUser,Map param) throws Exception;

	//public void  saveUserInfo(BaseUser baseUser,Map<String, String> param) throws Exception;

	//public List<Map<String, String>> getUserType()throws Exception;
	
	
	//public BaseUser  saveBaseUserInfo4Mobile(BaseUser baseUser, String ip) throws EgladServiceException;
	
	//public List getUserType(Map param) throws EgladServiceException;
	
	//public List getSysRole(Map param) throws EgladServiceException;
	
	//public void updateBaseUserState(String flag,String userIds) throws EgladServiceException;
	
	//public void  checkLoginId(Map param) throws EgladServiceException;
	
	//public List<TreeNode> getBaseOrg(Map map) throws EgladServiceException;
	
	public boolean saveLoginInfo(Map map,HttpSession session) throws Exception;
	
	//public String saveImgFile(HttpServletRequest request,  String userId, InputStream in) throws Exception;
	
	/***
	 * 修改密码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public void updateBaseUserPwd(Map map) throws Exception;
	
	/***
	 * 查询所属站点路径
	 * @return
	 * @throws Exception
	 */
	//public String getUserServiceCenter(HttpServletRequest request) throws Exception;
	
	//public Map getUserServiceCenterById(String id,HttpServletRequest request) throws Exception;
	
	//public Integer  getCount(Map map) throws Exception;
}
