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
@Table(name = "t_prepay_bak")
@DataTransferObject
public class T_Prepay_Bak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "PID",length=10,updatable=true)
	private Integer pid;

	@RemoteProperty
	@Column(name = "AMOUNT",length=12,updatable=true)
	private Float amount;

	@RemoteProperty
	@Column(name = "AMOUNT_C",length=100,updatable=true)
	private String amountC;

	@RemoteProperty
	@Column(name = "UID",length=10,updatable=true)
	private Integer uid;


	public void setPid(Integer pid){
		this.pid = pid;
	}
	public Integer getPid(){
		return this.pid;
	}
	public void setAmount(Float amount){
		this.amount = amount;
	}
	public Float getAmount(){
		return this.amount;
	}
	public void setAmountC(String amountC){
		this.amountC = amountC;
	}
	public String getAmountC(){
		return this.amountC;
	}
	public void setUid(Integer uid){
		this.uid = uid;
	}
	public Integer getUid(){
		return this.uid;
	}
}