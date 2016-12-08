package com.tcj.work.orderManage.biz.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.goodsManage.service.GoodsManageService;
import com.tcj.work.orderManage.biz.OrderBiz;
import com.tcj.work.orderManage.service.OrderService;

@Component("orderBizImpl")
public class OrderBizImpl implements OrderBiz {

	@Autowired
	@Qualifier("orderServiceImpl")
	private OrderService orderService;


	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	@Autowired
	@Qualifier("goodsManageServiceImpl")
	private GoodsManageService goodsManageService;

	@Override
	public Page getOrderList(Map<String, String> map) {
		// TODO Auto-generated method stub

		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
					//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//
			map.put("usertype", userdata.getUsertype().toString());
			Integer usertype=MapUtils.getInteger(map,"usertype");
			if(usertype==0){
				return orderService.getList(map);	//超级管理员方法
			}else if(usertype==1){
				map.put("providerId", userdata.getProviderId().toString());
				return orderService.getList(map);	//物业快车管理员方法
			}else{
				map.put("providerId", userdata.getProviderId().toString());
				return orderService.getList2(map);//会员单位管理员
			}
		} catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}




	@Override
	public Page getByid(Map param) {
		// TODO Auto-generated method stub
		return orderService.getByid(param);
	}


}
