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
@Table(name = "t_order")
@DataTransferObject
public class T_Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "OID",length=10,updatable=true)
	private Integer oid;

	@RemoteProperty
	@Column(name = "CID",length=10,updatable=true)
	private Integer cid;

	@RemoteProperty
	@Column(name = "CNAME",length=30,updatable=true)
	private String cname;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_USER",length=10,updatable=true)
	private Integer createUser;

	@RemoteProperty
	@Column(name = "EFFECT_DATA",length=255,updatable=true)
	private String effectData;

	@RemoteProperty
	@Column(name = "EWCODE",length=255,updatable=true)
	private String ewcode;

	@RemoteProperty
	@Column(name = "MID",length=10,updatable=true)
	private Integer mid;

	@RemoteProperty
	@Column(name = "MNAME",length=30,updatable=true)
	private String mname;

	@RemoteProperty
	@Column(name = "SID",length=10,updatable=true)
	private Integer sid;

	@RemoteProperty
	@Column(name = "TOTAL_PRICE",length=12,updatable=true)
	private Float totalPrice;

	@RemoteProperty
	@Column(name = "TYPE",length=30,updatable=true)
	private String type;

	@RemoteProperty
	@Column(name = "UID",length=10,updatable=true)
	private Integer uid;

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
	public void setCid(Integer cid){
		this.cid = cid;
	}
	public Integer getCid(){
		return this.cid;
	}
	public void setCname(String cname){
		this.cname = cname;
	}
	public String getCname(){
		return this.cname;
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
	public void setEffectData(String effectData){
		this.effectData = effectData;
	}
	public String getEffectData(){
		return this.effectData;
	}
	public void setEwcode(String ewcode){
		this.ewcode = ewcode;
	}
	public String getEwcode(){
		return this.ewcode;
	}
	public void setMid(Integer mid){
		this.mid = mid;
	}
	public Integer getMid(){
		return this.mid;
	}
	public void setMname(String mname){
		this.mname = mname;
	}
	public String getMname(){
		return this.mname;
	}
	public void setSid(Integer sid){
		this.sid = sid;
	}
	public Integer getSid(){
		return this.sid;
	}
	public void setTotalPrice(Float totalPrice){
		this.totalPrice = totalPrice;
	}
	public Float getTotalPrice(){
		return this.totalPrice;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setUid(Integer uid){
		this.uid = uid;
	}
	public Integer getUid(){
		return this.uid;
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