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
@Table(name = "t_order_detail")
@DataTransferObject
public class T_Order_Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "OID",length=10,updatable=true)
	private Integer oid;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "GID",length=10,updatable=true)
	private Integer gid;

	@RemoteProperty
	@Column(name = "BRAND",length=30,updatable=true)
	private String brand;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_USER",length=10,updatable=true)
	private Integer createUser;

	@RemoteProperty
	@Column(name = "GNAME",length=100,updatable=true)
	private String gname;

	@RemoteProperty
	@Column(name = "GRADE",length=10,updatable=true)
	private String grade;

	@RemoteProperty
	@Column(name = "MIN_NUM",length=12,updatable=true)
	private Float minNum;

	@RemoteProperty
	@Column(name = "PIC",length=255,updatable=true)
	private String pic;

	@RemoteProperty
	@Column(name = "PRICE",length=12,updatable=true)
	private Float price;

	@RemoteProperty
	@Column(name = "PRODUCER",length=50,updatable=true)
	private String producer;

	@RemoteProperty
	@Column(name = "QUANTITY",length=12,updatable=true)
	private Float quantity;

	@RemoteProperty
	@Column(name = "SALE_FLAG",length=1,updatable=true)
	private String saleFlag;

	@RemoteProperty
	@Column(name = "SPECIAL_PRICE",length=12,updatable=true)
	private Float specialPrice;

	@RemoteProperty
	@Column(name = "STEP",length=12,updatable=true)
	private Float step;

	@RemoteProperty
	@Column(name = "STOCK",length=12,updatable=true)
	private Float stock;

	@RemoteProperty
	@Column(name = "TID",length=10,updatable=true)
	private Integer tid;

	@RemoteProperty
	@Column(name = "UNIT",length=20,updatable=true)
	private String unit;

	@RemoteProperty
	@Column(name = "UNIT_COMMENT",length=50,updatable=true)
	private String unitComment;

	@RemoteProperty
	@Column(name = "UPDATE_TIME",length=0,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "UPDATE_USER",length=10,updatable=true)
	private Integer updateUser;


	public void setOid(Integer oid){
		this.oid = oid;
	}
	public Integer getOid(){
		return this.oid;
	}
	public void setGid(Integer gid){
		this.gid = gid;
	}
	public Integer getGid(){
		return this.gid;
	}
	public void setBrand(String brand){
		this.brand = brand;
	}
	public String getBrand(){
		return this.brand;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateUser(Integer createUser){
		this.createUser = createUser;
	}
	public Integer getCreateUser(){
		return this.createUser;
	}
	public void setGname(String gname){
		this.gname = gname;
	}
	public String getGname(){
		return this.gname;
	}
	public void setGrade(String grade){
		this.grade = grade;
	}
	public String getGrade(){
		return this.grade;
	}
	public void setMinNum(Float minNum){
		this.minNum = minNum;
	}
	public Float getMinNum(){
		return this.minNum;
	}
	public void setPic(String pic){
		this.pic = pic;
	}
	public String getPic(){
		return this.pic;
	}
	public void setPrice(Float price){
		this.price = price;
	}
	public Float getPrice(){
		return this.price;
	}
	public void setProducer(String producer){
		this.producer = producer;
	}
	public String getProducer(){
		return this.producer;
	}
	public void setQuantity(Float quantity){
		this.quantity = quantity;
	}
	public Float getQuantity(){
		return this.quantity;
	}
	public void setSaleFlag(String saleFlag){
		this.saleFlag = saleFlag;
	}
	public String getSaleFlag(){
		return this.saleFlag;
	}
	public void setSpecialPrice(Float specialPrice){
		this.specialPrice = specialPrice;
	}
	public Float getSpecialPrice(){
		return this.specialPrice;
	}
	public void setStep(Float step){
		this.step = step;
	}
	public Float getStep(){
		return this.step;
	}
	public void setStock(Float stock){
		this.stock = stock;
	}
	public Float getStock(){
		return this.stock;
	}
	public void setTid(Integer tid){
		this.tid = tid;
	}
	public Integer getTid(){
		return this.tid;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
	public String getUnit(){
		return this.unit;
	}
	public void setUnitComment(String unitComment){
		this.unitComment = unitComment;
	}
	public String getUnitComment(){
		return this.unitComment;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateUser(Integer updateUser){
		this.updateUser = updateUser;
	}
	public Integer getUpdateUser(){
		return this.updateUser;
	}
}