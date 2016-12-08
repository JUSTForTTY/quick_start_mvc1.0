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
@Table(name = "t_sys_parameter")
@DataTransferObject
public class T_Sys_Parameter implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "PARAMETER_ID",length=10,updatable=true)
	private Integer parameterId;

	@RemoteProperty
	@Column(name = "PARAMETER_CD",length=50,updatable=true)
	private String parameterCd;

	@RemoteProperty
	@Column(name = "PARAMETER_VALUE",length=255,updatable=true)
	private String parameterValue;


	public void setParameterId(Integer parameterId){
		this.parameterId = parameterId;
	}

		return this.parameterId;
	}

		this.parameterCd = parameterCd;
	}

		return this.parameterCd;
	}

		this.parameterValue = parameterValue;
	}

		return this.parameterValue;
	}
