package com.tcj.work.goodsManage.biz.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.Category;
import com.tcj.domains.City;
import com.tcj.domains.Club;
import com.tcj.domains.Pronivce;
import com.tcj.work.clubManage.biz.ClubManageBiz;
import com.tcj.work.clubManage.service.ClubManageService;
import com.tcj.work.goodsManage.biz.GoodsTypeManageBiz;
import com.tcj.work.goodsManage.service.GoodsTypeManageService;


@Component("goodsTypeManageBizImpl")
public class GoodsTypeManageBizImpl implements GoodsTypeManageBiz {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("goodsTypeManageServiceImpl")
	private GoodsTypeManageService goodsTypeManageService;
	
	/*
	 * 
	 * 查询(条件查询)
	 * 查询商品类别
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			
			 Page  page=goodsTypeManageService.getList(param);	 
				
			 //将状态status转为枚举类型
//			 List<Activity> lst=page.getRows();
//			 Map map=EnumActivityStatus.toMap();
//
//			 for(int i=0;i<lst.size();i++){
//				 lst.get(i).setStatus(map.get(lst.get(i).getStatus()).toString());		  	  
//			 }
			
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 
	 * 商品类别删除
	 * 
	 */
	@RemoteMethod
	public ResultBean delete(String ids,String isDelete) {
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("isDelete", isDelete);
			goodsTypeManageService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}	

	/*
	 * 
	 * 根据id查询商品类别
	 * 
	 */
	@RemoteMethod
	public ResultBean getById(String id) {
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.goodsTypeManageService.getById(id));
//			Club club=new Club();
//			club =this.goodsTypeManageService.getById(id);
//            //eazyui时间单独处理.
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
//			if(null!=club.getFeeStartDate()){				
//			   String startTime =formatter.format(club.getFeeStartDate());
//			   resultBean.setStartTime(startTime);
//			}
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	/*
	 * 
	 * 商品类别新增、更新
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String,String> map) {
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		Category category=new Category();
		if(!"".equals(MapUtils.getString(map, "id", ""))){
		category.setId(Integer.valueOf(MapUtils.getString(map, "id", "")));
		}
//		club.setBid(Integer.valueOf(MapUtils.getString(map, "bid","")));
		category.setName(MapUtils.getString(map, "name", "")); 
        category.setDescription(MapUtils.getString(map, "description", ""));  
        System.out.println("isopen========="+MapUtils.getString(map, "isopen", ""));
        category.setIsopen(Integer.valueOf(MapUtils.getString(map, "isopen", ""))); 
        category.setRemark(MapUtils.getString(map, "remark", ""));
        category.setSort(Integer.valueOf(MapUtils.getString(map, "sort", "")));
        category.setUrl(MapUtils.getString(map, "url", ""));
        category.setTag1(MapUtils.getString(map, "tag1", ""));
        category.setTag2(MapUtils.getString(map, "tag2", ""));
        category.setTag3(MapUtils.getString(map, "tag3", ""));
        category.setTag4(MapUtils.getString(map, "tag4", ""));
        category.setTag5(MapUtils.getString(map, "tag5", ""));
		category.setImage(MapUtils.getString(map, "image", "")); 
   System.out.println("ssssssssssssss哈哈cityId="+MapUtils.getString(map, "cityId", ""));
		try {
			
			this.goodsTypeManageService.saveOrUpdate(category);			
			resultBean.setSuccess(true);
		
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

}
