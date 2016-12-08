package com.tcj.work.clubManage.biz.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.Activity;
import com.tcj.domains.Club;
import com.tcj.domains.ClubType;
import com.tcj.work.clubManage.biz.ClubTypeManageBiz;
import com.tcj.work.clubManage.service.ClubManageService;
import com.tcj.work.clubManage.service.ClubTypeManageService;

@Component("clubTypeManageBizImpl")
public class ClubTypeManageBizImpl implements ClubTypeManageBiz {

	@Autowired
	@Qualifier("clubTypeManageServiceImpl")
	private ClubTypeManageService clubTypeManageService;
	@Override
	public Page getList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return clubTypeManageService.getList(param);
	}
	@Override
	public ResultBean delete(String ids, String isDelete) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", ids);
			param.put("isDelete", isDelete);
			clubTypeManageService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	@Override
	public ResultBean saveOrUpdate(Map<String, String> map) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		ClubType clubType=new ClubType();
		if(!"".equals(MapUtils.getString(map, "id", ""))){
		clubType.setId(Integer.valueOf(MapUtils.getString(map, "id", "")));
		System.out.println("id============"+MapUtils.getString(map, "id", ""));
		}
		clubType.setDescription(MapUtils.getString(map, "description", "")); 
		clubType.setClubType(MapUtils.getString(map, "clubType", "")); 

		try {
			
			this.clubTypeManageService.saveOrUpdate(clubType);			
			resultBean.setSuccess(true);
		
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}
	@Override
	public ResultBean getById(String id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {		
			resultBean.setData(this.clubTypeManageService.getById(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

}
