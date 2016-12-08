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
@Table(name = "ds_prize")
@DataTransferObject
public class DsPrize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "prizeId",length=10,updatable=true)
	private Integer prizeid;

	@RemoteProperty
	@Column(name = "prizeType",length=255,nullable=true,updatable=true)
	private String prizetype;

	@RemoteProperty
	@Column(name = "prizeDescription",length=255,nullable=true,updatable=true)
	private String prizedescription;


	public void setPrizeid(Integer prizeid){
		this.prizeid = prizeid;
	}
	public Integer getPrizeid(){
		return this.prizeid;
	}
	public void setPrizetype(String prizetype){
		this.prizetype = prizetype;
	}
	public String getPrizetype(){
		return this.prizetype;
	}
	public void setPrizedescription(String prizedescription){
		this.prizedescription = prizedescription;
	}
	public String getPrizedescription(){
		return this.prizedescription;
	}
}