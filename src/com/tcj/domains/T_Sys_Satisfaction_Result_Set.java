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

		return this.satisfactionResultSetId;
	}

		this.satisfactionResultCd = satisfactionResultCd;
	}

		return this.satisfactionResultCd;
	}

		this.satisfactionResultNm = satisfactionResultNm;
	}

		return this.satisfactionResultNm;
	}
