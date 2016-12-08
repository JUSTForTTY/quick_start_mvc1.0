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
@Table(name = "t_sys_info")
@DataTransferObject
public class T_Sys_Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "SID",length=10,updatable=true)
	private Integer sid;

	@RemoteProperty
	@Column(name = "HOUR",length=10,updatable=true)
	private Integer hour;

	@RemoteProperty
	@Column(name = "MINUTE",length=10,updatable=true)
	private Integer minute;

	@RemoteProperty
	@Column(name = "SECOND",length=10,updatable=true)
	private Integer second;

	@RemoteProperty
	@Column(name = "TIME_INTERVAL",length=10,updatable=true)
	private Integer timeInterval;


	public void setSid(Integer sid){
		this.sid = sid;
	}
	public Integer getSid(){
		return this.sid;
	}
	public void setHour(Integer hour){
		this.hour = hour;
	}
	public Integer getHour(){
		return this.hour;
	}
	public void setMinute(Integer minute){
		this.minute = minute;
	}
	public Integer getMinute(){
		return this.minute;
	}
	public void setSecond(Integer second){
		this.second = second;
	}
	public Integer getSecond(){
		return this.second;
	}
	public void setTimeInterval(Integer timeInterval){
		this.timeInterval = timeInterval;
	}
	public Integer getTimeInterval(){
		return this.timeInterval;
	}
}