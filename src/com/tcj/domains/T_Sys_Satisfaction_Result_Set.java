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
@Table(name = "t_sys_satisfaction_result_set")
@DataTransferObject
public class T_Sys_Satisfaction_Result_Set implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "SATISFACTION_RESULT_SET_ID",length=10,updatable=true)
	private Integer satisfactionResultSetId;

	@RemoteProperty
	@Column(name = "SATISFACTION_RESULT_CD",length=40,updatable=true)
	private String satisfactionResultCd;

	@RemoteProperty
	@Column(name = "SATISFACTION_RESULT_NM",length=60,updatable=true)
	private String satisfactionResultNm;


	public void setSatisfactionResultSetId(Integer satisfactionResultSetId){
		this.satisfactionResultSetId = satisfactionResultSetId;
	}
	public Integer getSatisfactionResultSetId(){
		return this.satisfactionResultSetId;
	}
	public void setSatisfactionResultCd(String satisfactionResultCd){
		this.satisfactionResultCd = satisfactionResultCd;
	}
	public String getSatisfactionResultCd(){
		return this.satisfactionResultCd;
	}
	public void setSatisfactionResultNm(String satisfactionResultNm){
		this.satisfactionResultNm = satisfactionResultNm;
	}
	public String getSatisfactionResultNm(){
		return this.satisfactionResultNm;
	}
}