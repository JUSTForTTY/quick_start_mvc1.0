package com.tcj.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "tbk_goods")
@DataTransferObject
public class Tbk_Goods implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=255,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "goods_id",length=255,nullable=true,updatable=true)
	private String goodsId;

	@RemoteProperty
	@Column(name = "name",length=255,nullable=true,updatable=true)
	private String name;

	@RemoteProperty
	@Column(name = "image",length=255,nullable=true,updatable=true)
	private String image;

	@RemoteProperty
	@Column(name = "goods_detail_url",length=2147483647,nullable=true,updatable=true)
	private String goodsDetailUrl;

	@RemoteProperty
	@Column(name = "status",length=255,nullable=true,updatable=true)
	private String status;

	@RemoteProperty
	@Column(name = "shop_name",length=255,nullable=true,updatable=true)
	private String shopName;

	@RemoteProperty
	@Column(name = "price",length=255,nullable=true,updatable=true)
	private Float price;

	@RemoteProperty
	@Column(name = "commodity_monthly_sales",length=255,nullable=true,updatable=true)
	private String commodityMonthlySales;

	@RemoteProperty
	@Column(name = "price_to_income",length=255,nullable=true,updatable=true)
	private String priceToIncome;

	@RemoteProperty
	@Column(name = "activity_status",length=255,nullable=true,updatable=true)
	private String activityStatus;

	@RemoteProperty
	@Column(name = "general_commission",length=255,nullable=true,updatable=true)
	private String generalCommission;

	@RemoteProperty
	@Column(name = "activity_to_income",length=255,nullable=true,updatable=true)
	private String activityToIncome;

	@RemoteProperty
	@Column(name = "activity_commission",length=255,nullable=true,updatable=true)
	private String activityCommission;

	@RemoteProperty
	@Column(name = "activity_start_time",length=0,nullable=true,updatable=true)
	private Date activityStartTime;

	@RemoteProperty
	@Column(name = "activity_end_time",length=0,nullable=true,updatable=true)
	private Date activityEndTime;

	@RemoteProperty
	@Column(name = "sales_trademanager",length=255,nullable=true,updatable=true)
	private String salesTrademanager;

	@RemoteProperty
	@Column(name = "tbk_short_url",length=2147483647,nullable=true,updatable=true)
	private String tbkShortUrl;

	@RemoteProperty
	@Column(name = "tbk_url",length=2147483647,nullable=true,updatable=true)
	private String tbkUrl;

	@RemoteProperty
	@Column(name = "amoy_password",length=255,nullable=true,updatable=true)
	private String amoyPassword;

	@RemoteProperty
	@Column(name = "total_coupon",length=255,nullable=true,updatable=true)
	private String totalCoupon;

	@RemoteProperty
	@Column(name = "coupon_surplus",length=255,nullable=true,updatable=true)
	private String couponSurplus;

	@RemoteProperty
	@Column(name = "coupon_denomination",length=255,nullable=true,updatable=true)
	private String couponDenomination;

	@RemoteProperty
	@Column(name = "coupon_start_time",length=0,nullable=true,updatable=true)
	private Date couponStartTime;

	@RemoteProperty
	@Column(name = "coupon_end_time",length=0,nullable=true,updatable=true)
	private Date couponEndTime;

	@RemoteProperty
	@Column(name = "coupon_url",length=2147483647,nullable=true,updatable=true)
	private String couponUrl;

	@RemoteProperty
	@Column(name = "coupon_face",length=255,nullable=true,updatable=true)
	private String couponFace;

	@RemoteProperty
	@Column(name = "create_time",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "update_time",length=0,nullable=true,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "publish_time",length=0,nullable=true,updatable=true)
	private Date publishTime;

	@RemoteProperty
	@Column(name = "remark",length=255,nullable=true,updatable=true)
	private String remark;

	@RemoteProperty
	@Column(name = "F1",length=255,nullable=true,updatable=true)
	private Integer f1;

	@RemoteProperty
	@Column(name = "F2",length=255,nullable=true,updatable=true)
	private String f2;

	@RemoteProperty
	@Column(name = "goods_status",length=255,nullable=true,updatable=true)
	private String goodsStatus;


	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setGoodsId(String goodsId){
		this.goodsId = goodsId;
	}
	public String getGoodsId(){
		return this.goodsId;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setGoodsDetailUrl(String goodsDetailUrl){
		this.goodsDetailUrl = goodsDetailUrl;
	}
	public String getGoodsDetailUrl(){
		return this.goodsDetailUrl;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setShopName(String shopName){
		this.shopName = shopName;
	}
	public String getShopName(){
		return this.shopName;
	}
	public void setPrice(Float price){
		this.price = price;
	}
	public Float getPrice(){
		return this.price;
	}
	public void setCommodityMonthlySales(String commodityMonthlySales){
		this.commodityMonthlySales = commodityMonthlySales;
	}
	public String getCommodityMonthlySales(){
		return this.commodityMonthlySales;
	}
	public void setPriceToIncome(String priceToIncome){
		this.priceToIncome = priceToIncome;
	}
	public String getPriceToIncome(){
		return this.priceToIncome;
	}
	public void setActivityStatus(String activityStatus){
		this.activityStatus = activityStatus;
	}
	public String getActivityStatus(){
		return this.activityStatus;
	}
	public void setGeneralCommission(String generalCommission){
		this.generalCommission = generalCommission;
	}
	public String getGeneralCommission(){
		return this.generalCommission;
	}
	public void setActivityToIncome(String activityToIncome){
		this.activityToIncome = activityToIncome;
	}
	public String getActivityToIncome(){
		return this.activityToIncome;
	}
	public void setActivityCommission(String activityCommission){
		this.activityCommission = activityCommission;
	}
	public String getActivityCommission(){
		return this.activityCommission;
	}
	public void setActivityStartTime(Date activityStartTime){
		this.activityStartTime = activityStartTime;
	}
	public Date getActivityStartTime(){
		return this.activityStartTime;
	}
	public void setActivityEndTime(Date activityEndTime){
		this.activityEndTime = activityEndTime;
	}
	public Date getActivityEndTime(){
		return this.activityEndTime;
	}
	public void setSalesTrademanager(String salesTrademanager){
		this.salesTrademanager = salesTrademanager;
	}
	public String getSalesTrademanager(){
		return this.salesTrademanager;
	}
	public void setTbkShortUrl(String tbkShortUrl){
		this.tbkShortUrl = tbkShortUrl;
	}
	public String getTbkShortUrl(){
		return this.tbkShortUrl;
	}
	public void setTbkUrl(String tbkUrl){
		this.tbkUrl = tbkUrl;
	}
	public String getTbkUrl(){
		return this.tbkUrl;
	}
	public void setAmoyPassword(String amoyPassword){
		this.amoyPassword = amoyPassword;
	}
	public String getAmoyPassword(){
		return this.amoyPassword;
	}
	public void setTotalCoupon(String totalCoupon){
		this.totalCoupon = totalCoupon;
	}
	public String getTotalCoupon(){
		return this.totalCoupon;
	}
	public void setCouponSurplus(String couponSurplus){
		this.couponSurplus = couponSurplus;
	}
	public String getCouponSurplus(){
		return this.couponSurplus;
	}
	public void setCouponDenomination(String couponDenomination){
		this.couponDenomination = couponDenomination;
	}
	public String getCouponDenomination(){
		return this.couponDenomination;
	}
	public void setCouponStartTime(Date couponStartTime){
		this.couponStartTime = couponStartTime;
	}
	public Date getCouponStartTime(){
		return this.couponStartTime;
	}
	public void setCouponEndTime(Date couponEndTime){
		this.couponEndTime = couponEndTime;
	}
	public Date getCouponEndTime(){
		return this.couponEndTime;
	}
	public void setCouponUrl(String couponUrl){
		this.couponUrl = couponUrl;
	}
	public String getCouponUrl(){
		return this.couponUrl;
	}
	public void setCouponFace(String couponFace){
		this.couponFace = couponFace;
	}
	public String getCouponFace(){
		return this.couponFace;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setPublishTime(Date publishTime){
		this.publishTime = publishTime;
	}
	public Date getPublishTime(){
		return this.publishTime;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public void setF1(Integer f1){
		this.f1 = f1;
	}
	public Integer getF1(){
		return this.f1;
	}
	public void setF2(String f2){
		this.f2 = f2;
	}
	public String getF2(){
		return this.f2;
	}
	public void setGoodsStatus(String goodsStatus){
		this.goodsStatus = goodsStatus;
	}
	public String getGoodsStatus(){
		return this.goodsStatus;
	}
}