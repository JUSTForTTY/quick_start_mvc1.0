package com.tcj.work.news.biz.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.AssocBuildingNews;
import com.tcj.domains.LoginEntity;
import com.tcj.domains.News;
import com.tcj.domains.Provider;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.goodsManage.service.GoodsManageService;
import com.tcj.work.news.biz.NewsBiz;
import com.tcj.work.news.service.NewsService;
@Component("newsBizImpl")
public class NewsBizImpl implements NewsBiz {

	@Autowired
	@Qualifier("newsServiceImpl")
	private NewsService newsService;

	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	@Autowired
	@Qualifier("goodsManageServiceImpl")
	private GoodsManageService goodsManageService;


	@Override
	public Page getList(Map<String, String> params) {
		// TODO Auto-generated method stub

		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			//

			params.put("usertype", userdata.getUsertype().toString());

			Integer usertype=MapUtils.getInteger(params, "usertype");
			if(usertype==0){
				//params.put("buildingId",userdata.getBuildingId());
				return newsService.getList(params);//超管看所有的

			}else if(usertype==1){
				return newsService.getList(params);
			}else{
				params.put("providerId", userdata.getProviderId().toString());
				System.out.println("buildingId==11222="+userdata.getProviderId());
				return newsService.getList1(params);
			}
		} catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;


	}

	@Override
	public ResultBean delete(String id) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		try {
			News news = new News();
			Map<String, String> param = new HashMap<String, String>();
			param.put("ids", id);
			//	param.put("isDelete");
			newsService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public void insert(Map param) throws  EgladServiceException {
		// TODO Auto-generated method stub
		newsService.insert(param);
	}

	@Override
	public ResultBean getByid(Integer id) {
		// TODO Auto-generated method stub

		ResultBean resultBean = new ResultBean();
		try{
			resultBean.setData(newsService.getByid(id));
			News news=new News();
			news=newsService.getByid(id);
			news.getDate();
			System.out.println("nihaoaa"+news.getDate());
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");			  
			//   String Date =formatter.format(news.getDate());
			//   resultBean.setData(Date);






			resultBean.setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}



	@Override
	public ResultBean saveOrupdate(Map<String, String> map) {
		// TODO Auto-generated method stub

		ResultBean resultBean = new ResultBean();
		//前台map数据给bean
		News news=new News();
		//Integer id = null;
		//	news.setId(Integer.valueOf(MapUtils.getString(param,id)));
		//System.out.println(Integer.valueOf(MapUtils.getString(param,id)+"nihao a "));
		news.setId(MapUtils.getInteger(map, "id"));//新闻id
		news.setTitle(MapUtils.getString(map, "title",""));//标题
		news.setType(MapUtils.getString(map, "type",""));//类型
		System.out.println("tyep");
		news.setContent(MapUtils.getString(map, "content",""));//内容
		news.setStatus(MapUtils.getString(map, "status",""));
		System.out.println("nihao+"+MapUtils.getString(map, "status",""));
		if(news.getStatus().equals("0")){
			news.setUrl(MapUtils.getString(map, "url",""));//状态
			System.out.println(MapUtils.getString(map, "url","")+"url链接");
		}else{
			news.setUrl("newsDetail.htm");
		}
		//链接地址
		news.setImage(MapUtils.getString(map, "image",""));//图片地址
		news.setBriefintro(MapUtils.getString(map, "briefintro",""));//简介

		news.setIsScroll(MapUtils.getString(map, "isScroll",""));

		System.out.println("nihao a置顶 "+MapUtils.getString(map, "isScroll",""));
		Integer buildingId=MapUtils.getInteger(map, "buildingId");
		Integer providerId=MapUtils.getInteger(map, "providerId");//用户信息中过来的
		news.setProviderId(MapUtils.getInteger(map, "provider_id"));//页面过来的

		System.out.println("会员单位providerId==="+providerId);
		Integer usertype=MapUtils.getInteger(map, "usertype");
		System.out.println("id===="+usertype);
		//String memberId=MapUtils.getString(map, "memberId");
		//System.out.println(news.setId(MapUtils.getInteger(param, "id")));
		//news.setStatus("0");
		//news.setGmtCreate(DateUtil.newDate());
		//	news.setPublishTime(DateUtil.newDate());	
		try {
			System.out.println("id===="+usertype);
			if(usertype==0){
				Integer id= newsService.insertguanlian(news);				  
				AssocBuildingNews abn=new AssocBuildingNews(); 
				abn.setNewsId(id);
				System.out.println("id===="+id);
				System.out.println("buildingId"+buildingId);
				abn.setBuildingId((MapUtils.getInteger(map, "buildingId")));
				newsService.abnsaveOrUpdate(abn);
				resultBean.setSuccess(true);
			}
			else if (usertype==1){
				Integer id= newsService.insertguanlian(news);				  
				AssocBuildingNews abn=new AssocBuildingNews(); 
				System.out.println("nihao");
				abn.setNewsId(id);
				System.out.println("id===="+id);
				System.out.println("buildingId"+buildingId);
				abn.setBuildingId((MapUtils.getInteger(map, "buildingId")));
				newsService.abnsaveOrUpdate(abn);
				resultBean.setSuccess(true);
			}

			else{
				System.out.println("1111+providerId"+providerId);

				User user=new User();
				user.setProviderId(providerId);
				Integer id= newsService.insertguanlian2(news,user);				  
				AssocBuildingNews abn=new AssocBuildingNews(); 
				abn.setNewsId(id);
				System.out.println("id===="+id);
				System.out.println("buildingId"+buildingId);
				abn.setBuildingId((MapUtils.getInteger(map, "buildingId")));
				newsService.abnsaveOrUpdate(abn);
				resultBean.setSuccess(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public ResultBean update(Map map) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();
		News news=new News();
		news.setId(MapUtils.getInteger(map,"id"));
		news.setContent(MapUtils.getString(map,"content"));
		news.setBriefintro(MapUtils.getString(map, "briefintro"));
		news.setImage(MapUtils.getString(map, "image"));
		news.setTitle(MapUtils.getString(map, "title"));
		news.setType(MapUtils.getString(map, "type"));
		news.setStatus(MapUtils.getString(map, "status"));
		if(news.getStatus().equals("0")){
			news.setUrl(MapUtils.getString(map, "url",""));//状态
			System.out.println(MapUtils.getString(map, "url","")+"url链接");
		}else{
			news.setUrl("newsDetail.htm");
		}
		
		news.setIsScroll(MapUtils.getString(map, "isScroll"));
		System.out.println(MapUtils.getString(map, "isScroll")+"置顶的东西");
		news.setModifyTime(DateUtil.newDate());
		news.setProviderId(MapUtils.getInteger(map,"providerId"));
		System.out.println(MapUtils.getInteger(map,"providerId")+"nihao，providerId");
		System.out.println("更新时间"+news.getModifyTime());
		//news.setStatus("0");
		Integer usertype=MapUtils.getInteger(map,"usertype");
		Integer memberId=MapUtils.getInteger(map, "memberId");//用户信息
		System.out.println("修改usertype为"+usertype);
		//	System.out.println("修改providerId"+providerid);
		System.out.println("你好，你的世界");

		try {				
			if(usertype==0){	
				//	news.setProviderId(memberId);
				newsService.updateNews(news);
				resultBean.setSuccess(true);
			}
			else if(usertype==1){	
				newsService.updateNews(news);
				resultBean.setSuccess(true);
			}
			else{

				news.setProviderId(memberId);
				System.out.println("memberId"+memberId);
				User user =new User();

				newsService.updateNews2(news);
				resultBean.setSuccess(true);
			}



		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	@Override
	public void delData(String id) throws Exception {
		// TODO Auto-generated method stub
		newsService.delData(id);
	}

	/**
	 * Page  列表页面
	 * 
	 * 
	 */



	@Override
	public Page getBreakingNews(Map map) {
		// TODO Auto-generated method stub
		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			map.put("usertype", userdata.getUsertype().toString());//

			Integer usertype=MapUtils.getInteger(map, "usertype");
			System.out.println("用户类型"+usertype);
			if(usertype==0){

				return newsService.getBreakingNews(map);
			}else {
				map.put("providerId",userdata.getProviderId().toString());
				return newsService.getBreakingNews1(map);
			}
		} catch (EgladServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		return null;
	}


	@Override
	public ResultBean getsaveOrUpdate(Map map) {
		// TODO Auto-generated method stub

		LoginEntity entity = null;
		try {
			entity = authorizeService.getLoginUser();
			System.out.println("用户id:"+entity.getUserId());
			//查询用户信息
			User userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));
			map.put("usertype", userdata.getUsertype().toString());	
			ResultBean result=new ResultBean();
			News member=new News();
			member.setId(MapUtils.getInteger(map, "id"));

			member.setTitle(MapUtils.getString(map,"title"));
			member.setEnglishTitle(MapUtils.getString(map, "englishTitle"));
			member.setType(MapUtils.getString(map, "type"));
			//member.setTitle(MapUtils.getString(map, "title"));
			//	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			String str=MapUtils.getString(map, "Date");
			String[] strs=str.split("[-]"); 
			//	System.out.println("111"+strs[0]);
			//	System.out.println("111"+strs[1]);
			//	System.out.println("111"+strs[2]);
			String Date=strs[0]+strs[1]+strs[2];
			System.out.println("时间伪装花"+Date);
			//			if(!"".equals(MapUtils.getString(map, "Date"))){
			//				System.out.println("时间"+sdf.format(MapUtils.getString(map, "Date")));
			member.setDate(Date);
			//				 
			//			}
			member.setProviderId(MapUtils.getInteger(map,"providerId"));


			Integer usertype=MapUtils.getInteger(map,"usertype");
			System.out.println("大事记++=="+usertype);
			try {	
				if(usertype==0){
					newsService.getsaveOrUpdate(member);	
				}else if(usertype==1){
					//					map.put("providerId", userdata.getProviderId().toString());
					//					Integer providerId=MapUtils.getInteger(map, "providerId");
					//                     User user=new User();
					//                      user.setProviderId(providerId);
					newsService.getsaveOrUpdate(member);	
				}else{
					map.put("providerId", userdata.getProviderId().toString());
					Integer providerId=MapUtils.getInteger(map, "providerId");
					//	User user=new User();
					member.setProviderId(providerId);
					System.out.println("nihao providerid"+providerId);
					newsService.getsaveOrUpdate1(member);	
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


}
