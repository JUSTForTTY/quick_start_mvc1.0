
package com.tcj.work.goodsManage.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.*;
import com.tcj.work.clubManage.biz.ClubManageBiz;
import com.tcj.work.goodsManage.biz.GoodsTypeManageBiz;


/**
 * @dscription 商品类别数据维护 
 * @author fengrb
 * @date 2016/11/02 
 * @version 1.0
 * @history
 */
@Controller("goodsTypeManageAction")
@RemoteProxy(name = "goodsTypeManageAction")//父类路径
public class GoodsTypeManageAction {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("goodsTypeManageBizImpl")
	private GoodsTypeManageBiz goodsTypeManageBiz;

	/*
	 * 
	 * 查询(条件查询)
	 * 查询商品类别
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
			  
		return this.goodsTypeManageBiz.getList(param);
	}
	/*
	 * 
	 * 商品类别删除
	 * 
	 */
	@RemoteMethod
	public ResultBean deletes(String ids,String isDelete) {
		
		System.out.println("测试ids======"+ids);
		System.out.println("测试recoverId======"+isDelete);
		return this.goodsTypeManageBiz.delete(ids,isDelete);
	}
	/*
	 * 
	 * 根据id查询商品类别
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {		
		System.out.println("类别id=============="+id);
		return this.goodsTypeManageBiz.getById(id);
	}
	
	/*
	 * 
	 * 商品类别增加、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String, String> map) {

		return this.goodsTypeManageBiz.saveOrUpdate(map);
		 
	
	}	

}
