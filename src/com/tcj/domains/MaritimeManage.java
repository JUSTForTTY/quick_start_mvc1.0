package com.tcj.domains;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import com.tcj.common.util.DateUtil;


@Entity
@Table(name = "admin_manage")
@DataTransferObject
public class MaritimeManage implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "Id",length=19,updatable=false)
	private Integer id;

	@RemoteProperty
	@Column(name = "Maritime_name",length=255,nullable=true,updatable=false)
	private String maritimeName;

	@RemoteProperty
	@Column(name = "Maritime_address",length=255,nullable=true,updatable=false)
	private String maritimeAddress;

	@RemoteProperty
	@Column(name = "create_user",length=255,nullable=true,updatable=false)
	private String createuser;
	
	@RemoteProperty
	@Column(name = "create_time",nullable=true,updatable=false)
	private Date createtime;

	@RemoteProperty
	@Column(name = "Maritime_contacts",length=255,nullable=true,updatable=false)
	private String maritimeContacts;

	@RemoteProperty
	@Column(name = "Maritime_mobile",length=255,nullable=true,updatable=false)
	private String maritimeMobile;
	
	@RemoteProperty
	@Column(name = "update_time",length=0,nullable=true,updatable=false)
	private Date updatetime;
	
	@RemoteProperty
	@Column(name = "IS_DELETED",length=11,nullable=false,updatable=false)
	private Integer IS_DELETED;

	@RemoteProperty
	@Column(name = "marine_id",length=19,nullable=true,updatable=false)
	private String marine_id;
	
	@RemoteProperty
	@Column(name = "F3",length=255,nullable=true,updatable=false)
	private String f3;

	@RemoteProperty
	@Column(name = "F4",length=255,nullable=true,updatable=false)
	private String f4;

	@RemoteProperty
	@Column(name = "F5",length=255,nullable=true,updatable=false)
	private String f5;


	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setMaritimeName(String maritimeName){
		this.maritimeName = maritimeName;
	}
	public String getMaritimeName(){
		return this.maritimeName;
	}
	public void setMaritimeAddress(String maritimeAddress){
		this.maritimeAddress = maritimeAddress;
	}
	public String getMaritimeAddress(){
		return this.maritimeAddress;
	}
	public void setMaritimeContacts(String maritimeContacts){
		this.maritimeContacts = maritimeContacts;
	}
	public String getMaritimeContacts(){
		return this.maritimeContacts;
	}
	public void setMaritimeMobile(String maritimeMobile){
		this.maritimeMobile = maritimeMobile;
	}
	public String getMaritimeMobile(){
		return this.maritimeMobile;
	}	
	public String getMarine_id() {
		return marine_id;
	}

	public void setMarine_id(String marine_id) {
		this.marine_id = marine_id;
	}

	public void setF3(String f3){
		this.f3 = f3;
	}
	public String getF3(){
		return this.f3;
	}
	public void setF4(String f4){
		this.f4 = f4;
	}
	public String getF4(){
		return this.f4;
	}
	public void setF5(String f5){
		this.f5 = f5;
	}
	public String getF5(){
		return this.f5;
	}
	public Integer getIS_DELETED() {
		return IS_DELETED;
	}

	public void setIS_DELETED(Integer iS_DELETED) {
		IS_DELETED = iS_DELETED;
	}

	@Override
	public String toString() {
		return "MaritimeManage [id=" + id + ", maritimeName=" + maritimeName + ", maritimeAddress=" + maritimeAddress
				+ ", createuser=" + createuser + ", createtime=" + createtime + ", maritimeContacts=" + maritimeContacts
				+ ", maritimeMobile=" + maritimeMobile + ", updatetime=" + updatetime + ", IS_DELETED=" + IS_DELETED
				+ ", marine_id=" + marine_id + ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + "]";
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime =createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}}