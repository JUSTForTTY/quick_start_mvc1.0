package com.tcj.domains.systemInfo;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;
/**
*
* @author zhouc
* 2015-03-18
*/
@Entity
@Table(name = "t_sys_parameter")
@DataTransferObject
public class SysParameter implements Serializable{
	private static final long serialVersionUID = 1L;
	@RemoteProperty
	@Id
	@Column(name = "PARAMETER_ID")
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	private Integer parameterId;

	public Integer getParameterId(){
		return parameterId;
	}

	public void setParameterId(Integer parameterId){
		this.parameterId=parameterId;
	}

	@RemoteProperty
	@Column(name = "PARAMETER_CD",length=50,nullable=false)
	private String parameterCd;

	public String getParameterCd(){
		return parameterCd;
	}

	public void setParameterCd(String parameterCd){
		this.parameterCd=parameterCd;
	}

	@RemoteProperty
	@Column(name = "PARAMETER_VALUE",nullable=false)
	private String parameterValue;

	public String getParameterValue(){
		return parameterValue;
	}

	public void setParameterValue(String parameterValue){
		this.parameterValue=parameterValue;
	}

}