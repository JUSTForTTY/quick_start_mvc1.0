package com.tcj.work.memberManage.biz.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.domains.Provider;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.goodsManage.service.GoodsManageService;
import com.tcj.work.memberManage.biz.MemberManageBiz;
import com.tcj.work.memberManage.service.MemberManageService;
@Component("memberManageBizImpl")
public class MemberManageBizImpl implements MemberManageBiz{

	@Autowired
	@Qualifier("memberManageServiceImpl")
	private MemberManageService memberManageService;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	@Autowired
	@Qualifier("goodsManageServiceImpl")
	private GoodsManageService goodsManageService;

	@Override
	public Page getPageList(Map<String, String> map) {
		// TODO Auto-generated method stub

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
				return memberManageService.getMemberList(map);//超管看一切的会员单位
			}
			else if(usertype==1){
				map.put("providerId", userdata.getProviderId().toString());
				return memberManageService.getMemberList1(map);//快车网看快车网会员单位
			}else{
				map.put("providerId", userdata.getProviderId().toString());

				return memberManageService.getMemberList2(map);//会员单位看会员单位的
			}

		}

		catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}




	@Override
	public ResultBean saveOrupdate(Map map) {
		// TODO Auto-generated method stub

		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			map.put("usertype", userdata.getUsertype().toString());
			
			ResultBean result=new ResultBean();
			Provider member=new Provider();
			member.setId(MapUtils.getInteger(map, "id"));
			member.setName(MapUtils.getString(map, "name"));
			member.setTitle(MapUtils.getString(map,"title"));
			member.setAddress(MapUtils.getString(map, "address"));
			member.setLongitude(MapUtils.getString(map,"longitude"));
			member.setLatitude(MapUtils.getString(map, "latitude"));
			member.setMemberurl(MapUtils.getString(map, "memberurl"));//private里面的字段，也就是bean里面的字段
			member.setImage1(MapUtils.getString(map, "image1"));
			member.setMiniature(MapUtils.getString(map, "miniature"));
			System.out.println("图片==="+MapUtils.getString(map, "miniature"));
			member.setType(MapUtils.getString(map, "type"));
			//member.setProviderId(MapUtils.getInteger(map, "provider_id"));
			
			member.setDetailintro(MapUtils.getString(map, "detailintro"));
			Integer usertype=MapUtils.getInteger(map,"usertype");
		
			try {	
				if(usertype==0){
					memberManageService.saveOrUpdate(member);	
				}else if(usertype==1){
//					map.put("providerId", userdata.getProviderId().toString());
//					Integer providerId=MapUtils.getInteger(map, "providerId");
//                     User user=new User();
//                      user.setProviderId(providerId);
					memberManageService.saveOrUpdate(member);	
				}else{
					map.put("providerId", userdata.getProviderId().toString());
					Integer providerId=MapUtils.getInteger(map, "providerId");
					User user=new User();
					user.setProviderId(providerId);
					memberManageService.saveOrUpdate2(member,user);	
				}
				result.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
				System.out.println(e.getMessage());
				result.setMsg(e.getMessage());
			}
			return result;

		}	
		catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}
	@Override
	public ResultBean getByid(Integer id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(memberManageService.getByid(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}

	@Override
	public ResultBean delete(String id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			News news = new News();
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", id);
			//	param.put("isDelete");
			memberManageService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}




	@Override
	public ResultBean getBymemberid(Integer id) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setData(memberManageService.getBymemberid(id));
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;

	}


}

