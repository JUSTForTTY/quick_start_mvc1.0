package com.tcj.work.goodsManage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcj.common.EgladServiceException;
import com.tcj.common.dao.BaseDao;
import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.dao.impl.BaseDaoImpl;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.BatchTool;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.CommonUtil;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.AssocBuildingCategory;
import com.tcj.domains.AssocBuildingGoods;
import com.tcj.domains.AssocCategoryGoods;
import com.tcj.domains.Category;
import com.tcj.domains.Goods;
import com.tcj.domains.News;
import com.tcj.domains.Tbk_Goods;
import com.tcj.domains.User;
import com.tcj.work.goodsManage.service.GoodsManageService;
@Component("goodsManageServiceImpl")
public class GoodsManageServiceImpl implements GoodsManageService {

	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;

	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	/*
	 * 
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.goodsManage.service.GoodsManageService#getList(java.util.Map)
	 * 
	 * 超管管所有商品
	 * 
	 */
	@Override
	public Page getList(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList al=new ArrayList();
		String name =MapUtils.getString(map, "name");
		String title=MapUtils.getString(map,"title");
		String status=MapUtils.getString(map,"status");
		String pname=MapUtils.getString(map, "pname");
		//String status=MapUtils.getString(map,"status");
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		String providerId=MapUtils.getString(map, "provider_id");
		System.out.println("provider_id==="+providerId);
		String type=MapUtils.getString(map, "type");	
		String sql ="select  g.name as name,g.title as title ,p.name as pname, g.provider_id as provider_id,g.price as price,g.unit as unit,g.type as type,g.status as status,g.id as id from goods  g   inner join provider p on g.provider_id=p.id where g.provider_id in(select p.id from provider where 1=1)";
		System.out.println("sql==="+sql);
		if(!"".equals(name)){
			sql+=" and  g.name like ?";
			al.add("%"+name +"%");
		}

		if(!"".equals(pname)){
			sql+=" and p.name like ?";
			al.add("%"+pname +"%");
		}


		if(!"".equals(title)){
			sql+=" and g.title like ?";
			al.add("%" + title +"%");
		}

		if(!"".equals(status)){
			sql+=" and g.status = ?";
			al.add(status);

		}

		if(!"".equals(type)){
			sql+=" and g.type = ?";
			al.add(type);

		}

		sql+=" order by id desc";

		Object[] objs=al.toArray();
		return splitPageDao.findBySql(sql,objs,pgNumber.intValue(),pgSize.intValue());
	}
	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.goodsManage.service.GoodsManageService#getList1(java.util.Map)
	 * 物业快车网管物业快车网的商品
	 * 
	 * 
	 */

	@Override
	public Page getList1(Map<String, String> map) {
		// TODO Auto-generated method stub
		ArrayList al=new ArrayList();
		String name =MapUtils.getString(map, "name");
		String title=MapUtils.getString(map,"title");
		String status=MapUtils.getString(map,"status");
		String pname=MapUtils.getString(map, "pname");
		//String status=MapUtils.getString(map,"status");
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		String providerId=MapUtils.getString(map, "provider_id");
		System.out.println("provider_id==="+providerId);
		String type=MapUtils.getString(map, "type");	
		String sql ="select  g.name as name,g.title as title ,p.name as pname, g.provider_id as provider_id,g.price as price,g.unit as unit,g.type as type,g.status as status,g.id as id,acg.goods_id as goods_id,acg.category_id as category_id from goods  g inner join assoc_category_goods acg on acg.goods_id =g.id   left join provider p on g.provider_id=p.id where g.provider_id in(select p.id from provider where 1=1) and g.provider_id=-1 and 1=1 ";
		System.out.println("sql==="+sql);
		if(!"".equals(name)){
			sql+=" and  name like ?";
			al.add("%"+name +"%");
		}

		if(!"".equals(pname)){
			sql+=" and p.name like ?";
			al.add("%"+pname +"%");
		}


		if(!"".equals(title)){
			sql+=" and g.title like ?";
			al.add("%" + title +"%");
		}

		if(!"".equals(status)){
			sql+=" and g.status = ?";
			al.add(status);

		}

		if(!"".equals(type)){
			sql+=" and g.type = ?";
			al.add(type);

		}

		sql+=" order by id desc";

		Object[] objs=al.toArray();
		return splitPageDao.findBySql(sql,objs,pgNumber.intValue(),pgSize.intValue());
	}

	/*
	 * 
	 * 
	 * 	(non-Javadoc)
	 * @see com.tcj.work.goodsManage.service.GoodsManageService#getList2(java.util.Map)
	 * 
	 * 会员单位管会员单位的商品
	 * 
	 */


	@Override
	public Page getList2(Map<String, String> map) {
		// TODO Auto-generated method stub

		ArrayList al=new ArrayList();
		String name =MapUtils.getString(map, "name");
		String title=MapUtils.getString(map,"title");
		String status=MapUtils.getString(map,"status");
		String pname=MapUtils.getString(map, "pname");
		//String status=MapUtils.getString(map,"status");
		Integer pgNumber=MapUtils.getInteger(map,"page",Integer.valueOf(1));
		Integer pgSize=MapUtils.getInteger(map,"rows",Integer.valueOf(40));
		String providerId=MapUtils.getString(map, "providerId");
		System.out.println("provider_id==="+providerId);
		String type=MapUtils.getString(map, "type");	
		String sql ="select  g.name as name,g.title as title ,p.name as pname, g.provider_id as provider_id,g.price as price,g.unit as unit,g.type as type,g.status as status,g.id as id from goods  g   inner join provider p on g.provider_id=p.id where g.provider_id="+providerId;
		System.out.println("sql==="+sql);
		if(!"".equals(name)){
			sql+=" and  name like ?";
			al.add("%"+name +"%");
		}

		if(!"".equals(pname)){
			sql+=" and p.name like ?";
			al.add("%"+pname +"%");
		}


		if(!"".equals(title)){
			sql+=" and g.title like ?";
			al.add("%" + title +"%");
		}

		if(!"".equals(status)){
			sql+=" and g.status = ?";
			al.add(status);

		}

		if(!"".equals(type)){
			sql+=" and g.type = ?";
			al.add(type);

		}

		sql+=" order by id desc";

		Object[] objs=al.toArray();
		return splitPageDao.findBySql(sql,objs,pgNumber.intValue(),pgSize.intValue());
	}

	@Override
	public void deletetList(Map<String, String> param) {
		// TODO Auto-generated method stub


		String ids = param.get("ids").toString();
		//String user = MapUtils.getString(param, "onhome", "")
		Date publish_time = new Date();
		Date update_time=new Date();
		ArrayList al = new ArrayList();
		al.add(CommonDefine.ID_DELETE_TRUE);// o是没删除的

		al.add(publish_time);
		al.add(update_time);
		String a[] = ids.split(",");
		for (int i = 0; i < a.length; i++) {
			al.add(a[i]);
		}
		System.out.print("1111"+ids);
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		Object[] objs = al.toArray();
		System.out.println("length:" + objs.length);
		String sql = "update tbk_goods set status=?,publish_time=?,update_time=? where id in ";
		sql = new BatchTool().batchDeleteSql(sql, objs, 3);
		System.out.println("删除sql:" + sql);
		splitPageDao.deleteAll(sql, objs);

	}




	@Override
	public List<Category> getCategoryId(Map<String, String> map) {
		// TODO Auto-generated method stub
		String hql = "FROM Category where parent_id =-1 ";
		return splitPageDao.findByHql(hql);
	}


	@Override
	public List<Category> getparentid(Map<String, String> map) {
		// TODO Auto-generated method stub

		String id=MapUtils.getString(map, "id", "");
		ArrayList al= new ArrayList();
		String hql = "FROM Category where parentId=? ";
		al.add(id);
		Object objs=al.toArray();
		return baseDao.query(hql, (Object[]) objs);
	}




	/*
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.goodsManage.service.GoodsManageService#save(com.tcj.domains.Goods)
	 * 超级管理员增加商品，随便增加到那个会员单位去
	 * 
	 */

	@Override
	public Integer save(Goods goods) {
		// TODO Auto-generated method stub
		//	tbk_goods.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
		String sql="insert into   goods(name,title,type,status,price,unit,detailintro,miniature,provider_id,weight,size) "+"values('"+goods.getName()+"','"+goods.getTitle()+"',"
				+"'"+goods.getType()+"','"+goods.getStatus()+"','"+goods.getPrice()+"','"+goods.getUnit()+"','"+goods.getDetailintro()+"','"+goods.getMiniature()+"','"+goods.getProviderId()+"','"+goods.getWeight()+"','"+goods.getSize()+"')";
		return   splitPageDao.saveReturnLastId(sql) ;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.goodsManage.service.GoodsManageService#save1(com.tcj.domains.Goods)
	 * 物业快车网新增商品，增加到会员单位为-1
	 * 
	 */

	@Override
	public Integer save1(Goods goods) {
		// TODO Auto-generated method stub
		goods.setProviderId(-1);
		String sql="insert into   goods(name,title,type,status,price,unit,detailintro,miniature,provider_id) "+"values('"+goods.getName()+"','"+goods.getTitle()+"',"
				+"'"+goods.getType()+"','"+goods.getStatus()+"','"+goods.getPrice()+"','"+goods.getUnit()+"','"+goods.getDetailintro()+"','"+goods.getMiniature()+"','"+goods.getProviderId()+"')";
		return   splitPageDao.saveReturnLastId(sql) ;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see com.tcj.work.goodsManage.service.GoodsManageService#save2(com.tcj.domains.Goods)
	 * 会员单位新增商品，新增到自己的会员单位去。
	 * 
	 */

	@Override
	public Integer save2(Goods goods) {
		// TODO Auto-generated method stub
		String sql="insert into   goods(name,title,type,status,price,unit,detailintro,miniature,provider_id) "+"values('"+goods.getName()+"','"+goods.getTitle()+"',"
				+"'"+goods.getType()+"','"+goods.getStatus()+"','"+goods.getPrice()+"','"+goods.getUnit()+"','"+goods.getDetailintro()+"','"+goods.getMiniature()+"','"+goods.getProviderId()+"')";
		return   splitPageDao.saveReturnLastId(sql) ;
	}




	@Override
	public void saveAssocCategoryGoods(AssocCategoryGoods acg) {
		// TODO Auto-generated method stub
		splitPageDao.saveOrUpdate(acg);
	}


	@Override
	public void selectUser(User user) {
		// TODO Auto-generated method stub
		String hql = "FROM User where 1=1 ";
		System.out.println("hql===="+hql);
		splitPageDao.findByHql(hql);
	}



	@Override
	public void saveAssocBuildingGoods(AssocBuildingGoods abg) {
		// TODO Auto-generated method stub
		splitPageDao.saveOrUpdate(abg);
	}


	@Override
	public Goods getById(Integer id) throws Exception  {
		// TODO Auto-generated method stub
		String hql = " FROM Goods WHERE id='" + id + "'";
		System.out.println("sql==================" + hql);
		List list = this.splitPageDao.findByHql(hql);
		if (list.size() > 0) {
			return (Goods) list.get(0);
		}
		throw new EgladServiceException("并没有这条记录");
	}


	@Override
	public void savegoods(Goods goods) throws Exception {
		// TODO Auto-generated method stub
		//			Goods goodsdata = getById(goods.getId());
		//			
		//			goodsdata.setName(goods.getName());
		//			goodsdata.setTitle(goods.getTitle());
		//			goodsdata.setPrice(goods.getPrice());
		//			goodsdata.setUnit(goods.getUnit());
		//			goodsdata.setMiniature(goods.getMiniature());
		//			goodsdata.setDetailintro(goods.getDetailintro());
		//			goodsdata.setProviderId(goods.getProviderId());
		//			goodsdata.setStatus(goods.getStatus());
		//			goodsdata=goods;
		System.out.println("修改，你好");		
		splitPageDao.saveOrUpdate(goods);

	}


	@Override
	public User getByUserId(Integer id) {
		//  
		String hql="from User where id="+id+"";


		List<User> lst=splitPageDao.findByHql(hql);

		return lst.get(0);
	}


	@Override
	public void delData(String id) throws Exception {
		// TODO Auto-generated method stub
		String sql="delete FROM tbk_goods WHERE id IN("+id+")";
		System.out.println(sql);
		splitPageDao.excuteSql(sql);
	}


	@Override
	public Integer saveUploadGoods(Goods goods) {



		// TODO Auto-generated method stub

		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
			String activityStartTime=sdf.format(goods.getActivityStartTime());
			String activityEndTime=sdf.format(goods.getActivityEndTime());
			String couponStartTime=sdf.format(goods.getCouponStartTime()); 
			String couponEndTime=sdf.format(goods.getCouponEndTime()); 
			String sql="insert into  tbk_goods(goods_id,name,image,goods_detail_url,shop_name,price,commodity_monthly_sales,price_to_income,general_commission,activity_status,activity_to_income,activity_commission,activity_start_time,activity_end_time,sales_trademanager,tbk_short_url,tbk_url,amoy_password,total_coupon,coupon_surplus,coupon_denomination,coupon_start_time,coupon_end_time,coupon_url,coupon_face,F1) "+"values('"+goods.getGoodsId()+"','"+goods.getName()+"','"+goods.getImage()+"','"+goods.getGoodsDetailUrl()+"','"+goods.getShopName()+"','"+goods.getPrice()+"',"
					+"'"+goods.getCommodityMonthlySales()+"','"+goods.getPriceToIncome()+"','"+goods.getGeneralCommission()+"','"+goods.getActivityStatus()+"','"+goods.getActivityToIncome()+"','"+goods.getActivityCommission()+"','"+activityStartTime+"','"+activityEndTime+"','"+goods.getSalesTrademanager()+"','"+goods.getTbkShortUrl()+"','"+goods.getTbkUrl()+"','"+goods.getAmoyPassword()+"','"+goods.getTotalCoupon()+"','"+goods.getCouponSurplus()+"','"+goods.getCouponDenomination()+"',"
					+ "'"+couponStartTime+"','"+couponEndTime+"','"+goods.getCouponUrl()+"','"+goods.getCouponFace()+"','"+goods.getF1()+"')";

			System.out.println("sql===="+sql);*/
		//return 	splitPageDao.saveReturnLastId(sql);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
		String createtime=sdf.format(goods.getCreatetime());
		String supplydate=sdf.format(goods.getSupplydate());
		String lastModified=sdf.format(goods.getLastModified());

		System.out.println("你好=====你真好");

		String sql="insert into goods(name,title,aliasname,barcode,privatecode,publiccode,tradecode,briefintro,detailintro,miniature,image1,image2,image3,image4,image5,size,weight,model,spec,brand_id,material,color,serialno,producearea,productdate,provider_id,refprice,costprice,price,vipprice,discount,url,recommendlevel,recommendremark,viewnum,ordernum,salenum,stocknum,isfreshgoods,ishotgoods,isoutofstock,creator,createtime,classification,sort,supplydate,status,type,last_modified,score_add,unit,indexId)" +"values('"+goods.getName()+"','"+goods.getTitle()+"','"+goods.getAliasname()+"','"+goods.getBarcode()+"','"+goods.getPrivatecode()+"','"+goods.getPubliccode()+"','"+goods.getTradecode()+"','"+goods.getBriefintro()+"','"+goods.getDetailintro()+"','"+goods.getMiniature()+"','"+goods.getImage1()+"','"+goods.getImage2()+"','"+goods.getImage3()+"','"+goods.getImage4()+"','"+goods.getImage5()+"','"+goods.getSize()+"','"+goods.getWeight()+"','"+goods.getModel()+"','"+goods.getSpec()+"','"+goods.getBrandId()+"','"+goods.getMaterial()+"','"+goods.getColor()+"','"+goods.getSerialno()+"','"+goods.getProducearea()+"','"+goods.getProductdate()+"','"+goods.getProviderId()+"','"+goods.getRefprice()+"','"+goods.getCostprice()+"','"+goods.getPrice()+"','"+goods.getVipprice()+"','"+goods.getDiscount()+"','"+goods.getUrl()+"','"+goods.getRecommendlevel()+"','"+goods.getRecommendremark()+"','"+goods.getViewnum()+"','"+goods.getOrdernum()+"','"+goods.getSalenum()+"','"+goods.getStocknum()+"','"+goods.getIsfreshgoods()+"','"+goods.getIshotgoods()+"','"+goods.getIsoutofstock()+"','"+goods.getCreator()+"','"+createtime+"','"+goods.getClassification()+"','"+goods.getSort()+"','"+supplydate+"','"+goods.getStatus()+"','"+goods.getType()+"','"+lastModified+"','"+goods.getScoreAdd()+"','"+goods.getUnit()+"','"+goods.getIndexid()+"')";	
		System.out.println("你好啊==="+sql);
		return splitPageDao.saveReturnLastId(sql);


	}


	/**
	 * 
	 * 上传图片
	 * 
	 */

	public void updateLoadImage(Map param) {
		// TODO Auto-generated method stub
		String id = MapUtils.getString(param, "id", "");
		String miniature = MapUtils.getString(param, "miniature", "");
		String sql = "UPDATE goods set miniature=? where id=?";
		System.out.println("你好，上传图片"+sql);
		ArrayList al = new ArrayList();
		al.add(miniature);
		al.add(id);
		baseDao.executeSql(sql, al.toArray());
	}


	@Override
	public Goods getgoodsId(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Goods where id="+id+"";


		List<Goods> lst=splitPageDao.findByHql(hql);

		return lst.get(0);
	}

	@Override
	public void updategoods(Goods goods) {
		// TODO Auto-generated method stub
		
		splitPageDao.saveOrUpdate(goods);
		
	}




}


//
//@Override
//	public void saveOrUpdate(Tbk_Goods tbk_goods) {




// TODO Auto-generated method stub
//		if (null == tbk_goods.getId() || "".equals(tbk_goods.getId())) {
//			System.out.println("id为空");
//		//	String sql = "CALL pro_getRunningNO('NID')";
//		//	String sequence = splitPageDao.findByjkSequence(sql);
//			tbk_goods.setCreateTime(DateUtil.newDate());
//			tbk_goods.setPublishTime(DateUtil.newDate());
//			tbk_goods.setStatus(String.valueOf(CommonDefine.ID_DELETE_FALSE));
//			System.out.println("你好，狀態,你新增了嗎" + CommonDefine.ID_DELETE_FALSE);		
//		
//		} else {
//			//News newsdata = getByid(news.getId());
//		//	System.out.println("id不为空");
//		//	System.out.println(news.getId()+"修改之后");
//		//	newsdata.setId(news.getId());
//		//	System.out.println("id你好，你该来了");
//		//	newsdata.setTitle(news.getTitle());
//		//	newsdata.setContent(news.getContent());
//		//	newsdata.setUrl(news.getUrl());
//		//	newsdata.setImage(news.getImage());
//		//	newsdata.setType(news.getType());
//		//	newsdata.setIsScroll(news.getIsScroll());
//		//	System.out.println("type===="+news.getType());
//		//	newsdata.setMobilecontent(news.getMobilecontent());
//		//	System.out.println("你好，图片" + news.getImage());
//		//	System.out.println("neirong" + news.getContent());
//		//	System.out.println("链接你好" + news.getUrl());
//		//	newsdata.setBriefintro(news.getBriefintro());
//		//	System.out.println("简介你好" + news.getBriefintro());
//		//	newsdata.setModifyTime(DateUtil.newDate());
//			// userdata.setUserName(news.getUserName());
//		//	news = newsdata;
//			
//		}
//		System.out.println("哈哈哈哈");
//	//	Map<String, String> map = new HashMap<String, String>();
//	//	saveorUpdate1(map);
//		 System.out.println("你好，你的听");
//		splitPageDao.saveOrUpdate(tbk_goods);
//
//	}





