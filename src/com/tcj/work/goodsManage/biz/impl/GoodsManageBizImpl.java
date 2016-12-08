package com.tcj.work.goodsManage.biz.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.Activity;
import com.tcj.domains.AssocBuildingCategory;
import com.tcj.domains.AssocBuildingGoods;
import com.tcj.domains.AssocCategoryGoods;
import com.tcj.domains.Category;
import com.tcj.domains.Goods;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.domains.Tbk_Goods;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.goodsManage.biz.GoodsManageBiz;
import com.tcj.work.goodsManage.service.GoodsManageService;

@Component("goodsManageBizImpl")
public class GoodsManageBizImpl implements GoodsManageBiz{

	@Autowired
	@Qualifier("goodsManageServiceImpl")
	private GoodsManageService goodsManageService;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;
	@Override
	public Page getList(Map<String, String> map) {
		// TODO Auto-generated method stub
		//System.out.println("budilg"+MapUtils.getString(map,"buildingId"));

		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//
			map.put("usertype", userdata.getUsertype().toString());
			Integer usertype=MapUtils.getInteger(map, "usertype");
			if(usertype==0){
				System.out.println();
				return goodsManageService.getList(map);
			}else if(usertype==1){
				
				    
				return goodsManageService.getList(map);
			}else{
				map.put("providerId", userdata.getProviderId().toString());
				return goodsManageService.getList2(map);
			}
		}
		catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}
	@Override
	public ResultBean deleteList(String id) {
		// TODO Auto-generated method stub
		ResultBean resultBean=new ResultBean();
		try{
			Tbk_Goods goods=new Tbk_Goods();
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", id);
			System.out.println("这个id"+id);
			goodsManageService.deletetList(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean saveorUpdate(Map map) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		Goods goods=new Goods();
		goods.setId(MapUtils.getInteger(map, "id"));//id
		goods.setName(MapUtils.getString(map, "name"));//商品id
		goods.setTitle(MapUtils.getString(map, "title"));//商品name
		goods.setType(MapUtils.getString(map, "type"));
		goods.setStatus(MapUtils.getString(map,"status"));
		goods.setPrice(MapUtils.getString(map, "price"));
		goods.setUnit(MapUtils.getString(map, "unit"));
		goods.setImage1(MapUtils.getString(map, "image1"));
		goods.setMiniature(MapUtils.getString(map, "miniature"));
		goods.setDetailintro(MapUtils.getString(map, "detailintro"));
		goods.setProviderId(MapUtils.getInteger(map, "providerId"));
		goods.setWeight(MapUtils.getString(map, "weight"));
		goods.setSize(MapUtils.getString(map, "size"));
		System.out.println("你好，你的世界");

		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//
			map.put("usertype", userdata.getUsertype().toString());
			Integer usertype=MapUtils.getInteger(map, "usertype");
		try {	
			if(usertype==0){
			Integer id=goodsManageService.save(goods);//超级管理员新增商品
			AssocCategoryGoods acg=new AssocCategoryGoods();
			acg.setGoodsId(id); 
			System.out.println("categoryId"+MapUtils.getInteger(map,"categoryId"));
			acg.setCategoryId(MapUtils.getInteger(map,"categoryId")); 
			goodsManageService.saveAssocCategoryGoods(acg);
			}else if(usertype==1){
				Integer id=goodsManageService.save(goods);//物业快车网商品的新增
				AssocCategoryGoods acg=new AssocCategoryGoods();
				acg.setGoodsId(id); 
				System.out.println("categoryId"+MapUtils.getInteger(map,"categoryId"));
				acg.setCategoryId(MapUtils.getInteger(map,"categoryId")); 
				goodsManageService.saveAssocCategoryGoods(acg);
			}else{
				map.put("providerId", userdata.getProviderId().toString());
				Integer providerId=MapUtils.getInteger(map, "providerId");	
				goods.setProviderId(providerId);
				Integer id=goodsManageService.save2(goods);//会员单位新增商品
				AssocCategoryGoods acg=new AssocCategoryGoods();
				acg.setGoodsId(id); 
				System.out.println("categoryId"+MapUtils.getInteger(map,"categoryId"));
				acg.setCategoryId(MapUtils.getInteger(map,"categoryId")); 
				goodsManageService.saveAssocCategoryGoods(acg);
			}
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
		}
		catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}
	@Override
	public List<Category> getCategoryId(Map<String, String> map) {
		// TODO Auto-generated method stub
		return goodsManageService.getCategoryId(map);
	}

	@Override
	public List<Category> getParaentId(Map<String, String> map) {
		// TODO Auto-generated method stub
		return goodsManageService.getparentid(map);
	}

	@Override
	public void selectUser(User user) {
		// TODO Auto-generated method stub
		goodsManageService.selectUser(user);
	}

	@Override
	public ResultBean getById(Integer id) {
		// TODO Auto-generated method stub

		ResultBean resultBean = new ResultBean();
		try {

			resultBean.setData(goodsManageService.getById(id));
			Goods goods=new Goods();
			goods =this.goodsManageService.getById(id);
			//			System.out.println("测试时间====="+activity.getCreateTime());
			//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");			  
			//			   String activityStartTime =formatter.format(tbk_goods.getActivityStartTime());
			//			   String activityEndTime =formatter.format(tbk_goods.getActivityEndTime());
			//			   String couponStartTime =formatter.format(tbk_goods.getCouponStartTime());
			//			   String couponEndTime =formatter.format(tbk_goods.getCouponEndTime());
			//			   resultBean.setActivityStartTime(activityStartTime);
			//			   resultBean.setActivityEndTime(activityEndTime);
			//			   resultBean.setCouponStartTime(couponStartTime);
			//			   resultBean.setCouponEndTime(couponEndTime);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}

	@Override
	public ResultBean savegoods(Map map) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		Goods goods=new Goods();
		goods.setId(MapUtils.getInteger(map, "id"));//id
		//	goods.setGoodsId(MapUtils.getString(map, "goodsId"));//商品id
		goods.setName(MapUtils.getString(map, "name"));//商品name
		//	goods.setImage(MapUtils.getString(map, "image"));
		goods.setTitle(MapUtils.getString(map,"title"));
		goods.setType(MapUtils.getString(map, "type"));
		goods.setStatus(MapUtils.getString(map,"status"));
		goods.setPrice(MapUtils.getString(map, "price"));
		goods.setUnit(MapUtils.getString(map, "unit"));
		goods.setWeight(MapUtils.getString(map,"weight"));
		goods.setSize(MapUtils.getString(map, "size"));
		goods.setMiniature(MapUtils.getString(map, "miniature"));
		goods.setDetailintro(MapUtils.getString(map,"detailintro"));
		goods.setProviderId(MapUtils.getInteger(map, "providerId"));
		System.out.println("你好，你的世界");
		
		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//
			map.put("usertype", userdata.getUsertype().toString());
			Integer usertype=MapUtils.getInteger(map, "usertype");
		try {	
			if(usertype==0){
			goodsManageService.savegoods(goods);
			}else if(usertype==1){
				goodsManageService.savegoods(goods);
			}else{
				goodsManageService.savegoods(goods);
			}
			resultBean.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
		catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}

	@Override
	public void delData(String id) throws Exception {
		// TODO Auto-generated method stub
		goodsManageService.delData(id);
	}

	@Override
	public Integer saveuploadGoods(Goods goods) {
		// TODO Auto-generated method stub
		//	Integer id=goodsManageService.save(tbk_goods);

		return goodsManageService.saveUploadGoods(goods);

	}

	//	@Override
	//	public Integer saveuploadGoods(Goods goods) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}



}
