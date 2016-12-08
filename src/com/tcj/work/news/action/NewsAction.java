package com.tcj.work.news.action;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.goodsManage.service.GoodsManageService;
import com.tcj.work.news.biz.NewsBiz;


/**
 * @author zxs
 * @date 2016/10/31 上午 十一点
 * @version 1.0
 *
 */
@Controller
@RemoteProxy(name="newsAction")
public class NewsAction {

	@Autowired
	@Qualifier("newsBizImpl")
	private NewsBiz newsBiz;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	@Autowired
	@Qualifier("goodsManageServiceImpl")
	private GoodsManageService goodsManageService;

	/**
	 * 
	 *根据条件查询，分页 
	 * 
	 * @return 
	 * 
	 */
	@RemoteMethod
	public Page getList(Map<String ,String> params){
		System.out.println("你好");


	
		
			return newsBiz.getList(params);
		

	}

	/**
	 * 
	 * 
	 * 
	 * 删除
	 * 逻辑删除（或者物理删除）
	 * 
	 */

	@RemoteMethod
	public ResultBean deletedata(String id) {
		System.out.println("你好，删除方法");
		return newsBiz.delete(id);
	}


	/**
	 * 
	 * 全部用saveorUpdate 方法
	 * 
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean  SaveOrUpdate(Map<String,String>map){
		
		
		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//
			map.put("usertype", userdata.getUsertype().toString());
			Integer usertype=MapUtils.getInteger(map, "usertype");
			if(usertype==0){
				map.put("buildingId",userdata.getBuildingId());
			}	else{
			map.put("providerId", userdata.getProviderId().toString());
		
				}

System.out.println("buildingId==11222="+userdata.getBuildingId());
		
		return newsBiz.saveOrupdate(map);
	
	} catch (EgladServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();		
	}
	return null;

	}

























	/**
	 * 
	 * 新增页面
	 * 
	 * 没有id的日子里
	 * 
	 */

	@RemoteMethod
	public ResultBean insertNews(Map map) {
		ResultBean resultBean = new ResultBean();
		try {
			newsBiz.insert(map);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}


	/**
	 * 
	 * 根据id查询
	 * 
	 * 
	 */

	@RemoteMethod
	public ResultBean getByid(Integer id) {

		return newsBiz.getByid(id);
	}


	/**
	 * 
	 * 
	 * update更新
	 * 
	 */
	@RemoteMethod
	public ResultBean updateNews(Map map) {
//		ResultBean resultBean = new ResultBean();
//		try {
//			newsBiz.update(map);
//			resultBean.setSuccess(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultBean.setSuccess(false);
//			resultBean.setMsg(e.getMessage());
//		}
//		return resultBean;
		
		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//
			map.put("usertype", userdata.getUsertype().toString());
			Integer usertype=MapUtils.getInteger(map, "usertype");
			if(usertype==0){
			
			
			map.put("buildingId",userdata.getBuildingId());
			}else{
			
			map.put("memberId", userdata.getProviderId().toString());
			}
System.out.println("buildingId==11222="+userdata.getBuildingId());
		
		
		
		return newsBiz.update(map);
		

		} catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;

		}



/*
 * 
 * 物理删除
 * 
 * 
 */
	@RemoteMethod
public ResultBean deleteNews(String id){
	
	ResultBean resultBean=new ResultBean();
	try {
		newsBiz.delData(id);
	} catch (Exception e) {
		resultBean.setSuccess(false);
		e.printStackTrace();
	}
	return resultBean;
}

	
	/**
	 * 
	 * 大事记
	 * @Page
	 *大事记 列表
	 * 
	 */
	
	@RemoteMethod
	public Page breakingNews(Map map){
		return newsBiz.getBreakingNews(map);
	}
	
	/**
	 * 
	 * 
	 * 大事记（修改和更新）
	 * 
	 */
	@RemoteMethod
	public ResultBean getsaveOrUpdate(Map map) {
		System.out.println("你好，来增加，来修改");
		return  newsBiz.getsaveOrUpdate(map);
	}
}
