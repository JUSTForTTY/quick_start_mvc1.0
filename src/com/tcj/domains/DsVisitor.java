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
@Table(name = "ds_visitor")
@DataTransferObject
public class DsVisitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "name",length=255,nullable=true,updatable=true)
	private String name;

	@RemoteProperty
	@Column(name = "sex",length=255,nullable=true,updatable=true)
	private String sex;

	@RemoteProperty
	@Column(name = "tel",length=255,nullable=true,updatable=true)
	private String tel;

	@RemoteProperty
	@Column(name = "provider_id",length=19,nullable=true,updatable=true)
	private Integer providerId;
	
	
	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	@RemoteProperty
	@Column(name = "time",length=255,nullable=true,updatable=true)
	private String time;

	@RemoteProperty
	@Column(name = "visitor_feedback",length=65535,nullable=true,updatable=true)
	private String visitorFeedback;

	@RemoteProperty
	@Column(name = "count",length=10,nullable=true,updatable=true)
	private Integer count;

	@RemoteProperty
	@Column(name = "email",length=255,nullable=true,updatable=true)
	private String email;


	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getSex(){
		return this.sex;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getTel(){
		return this.tel;
	}
	public void setTime(String time){
		this.time = time;
	}
	public String getTime(){
		return this.time;
	}
	public void setVisitorFeedback(String visitorFeedback){
		this.visitorFeedback = visitorFeedback;
	}
	public String getVisitorFeedback(){
		return this.visitorFeedback;
	}
	public void setCount(Integer count){
		this.count = count;
	}
	public Integer getCount(){
		return this.count;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
}