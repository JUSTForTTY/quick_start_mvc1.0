package com.tcj.work.orderManage.action;

import java.util.Map;

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
import com.tcj.work.orderManage.biz.OrderBiz;

/**
 * 
 * 
 * 
 * @author zxs
 * 
 * @date 2016/11/3日上午九点半
 * @version 1.0
 */

@Controller("orderAction")
@RemoteProxy(name="orderAction")
public class OrderAction {

	@Autowired
	@Qualifier("orderBizImpl")
	private OrderBiz  orderBiz;
 	@RemoteMethod
 	public Page getList(Map <String,String> map){
 		System.out.println("你好订单列表");
 		return orderBiz.getOrderList(map);
}
 	/**
 	 * 
 	 * 
 	 * 根据订单id查询子订单的物品详情
 	 * 
 	 * 
 	 * 
 	 */
 	@RemoteMethod
	public Page getByid(Map param){
		try {
			return orderBiz.getByid(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 	
 	
 	
 	/**
 	 * 
 	 * 导出数据 excel导出数据
 	 * 
 	 */
 	
 	
 	
}
