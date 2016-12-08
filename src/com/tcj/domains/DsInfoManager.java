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
@Table(name = "ds_info_manager")
@DataTransferObject
public class DsInfoManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "business_id",length=10,updatable=true)
	private Integer businessId;

	@RemoteProperty
	@Column(name = "marker_id",length=255,nullable=true,updatable=true)
	private String markerId;

	@RemoteProperty
	@Column(name = "business",length=255,nullable=true,updatable=true)
	private String business;

	@RemoteProperty
	@Column(name = "marker_name",length=255,nullable=true,updatable=true)
	private String markerName;

	@RemoteProperty
	@Column(name = "area",length=255,nullable=true,updatable=true)
	private String area;

	@RemoteProperty
	@Column(name = "number",length=255,nullable=true,updatable=true)
	private String number;


	public void setBusinessId(Integer businessId){
		this.businessId = businessId;
	}
	public Integer getBusinessId(){
		return this.businessId;
	}
	public void setMarkerId(String markerId){
		this.markerId = markerId;
	}
	public String getMarkerId(){
		return this.markerId;
	}
	public void setBusiness(String business){
		this.business = business;
	}
	public String getBusiness(){
		return this.business;
	}
	public void setMarkerName(String markerName){
		this.markerName = markerName;
	}
	public String getMarkerName(){
		return this.markerName;
	}
	public void setArea(String area){
		this.area = area;
	}
	public String getArea(){
		return this.area;
	}
	public void setNumber(String number){
		this.number = number;
	}
	public String getNumber(){
		return this.number;
	}
}