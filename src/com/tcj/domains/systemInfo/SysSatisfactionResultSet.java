package com.tcj.domains.systemInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @author 
 * @version 1.0 
 * @param 系统-系统参数  t_sys_satisfaction_result_set
 *
 */
@Entity
@Table(name = "t_sys_satisfaction_result_set")
@DataTransferObject
public class SysSatisfactionResultSet {
	@RemoteProperty
	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@Column(name = "SATISFACTION_RESULT_SET_ID")
	private Integer satisfactionResultSetId;//系统参数ID
	
	@RemoteProperty
	@Column(name = "SATISFACTION_RESULT_CD",length = 40,nullable=false)
	private String satisfactionResultCd;//参数代码
	
	@RemoteProperty
	@Column(name = "SATISFACTION_RESULT_NM",length = 60,nullable=false)
	private String satisfactionResultNm;//参数值

	public Integer getSatisfactionResultSetId() {
		return satisfactionResultSetId;
	}

	public void setSatisfactionResultSetId(Integer satisfactionResultSetId) {
		this.satisfactionResultSetId = satisfactionResultSetId;
	}

	public String getSatisfactionResultCd() {
		return satisfactionResultCd;
	}

	public void setSatisfactionResultCd(String satisfactionResultCd) {
		this.satisfactionResultCd = satisfactionResultCd;
	}

	public String getSatisfactionResultNm() {
		return satisfactionResultNm;
	}

	public void setSatisfactionResultNm(String satisfactionResultNm) {
		this.satisfactionResultNm = satisfactionResultNm;
	}


	
}
