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
@Table(name = "ds_codemaster")
@DataTransferObject
public class DsCodeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@RemoteProperty
	@Column(name = "codeType",length=255,nullable=true,updatable=true)
	private String codetype;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "codeNo",length=10,updatable=true)
	private Integer codeno;

	@RemoteProperty
	@Column(name = "codeName",length=255,nullable=true,updatable=true)
	private String codename;

	@RemoteProperty
	@Column(name = "codeDes",length=255,nullable=true,updatable=true)
	private String codedes;


	public void setCodetype(String codetype){
		this.codetype = codetype;
	}
	public String getCodetype(){
		return this.codetype;
	}
	public void setCodeno(Integer codeno){
		this.codeno = codeno;
	}
	public Integer getCodeno(){
		return this.codeno;
	}
	public void setCodename(String codename){
		this.codename = codename;
	}
	public String getCodename(){
		return this.codename;
	}
	public void setCodedes(String codedes){
		this.codedes = codedes;
	}
	public String getCodedes(){
		return this.codedes;
	}
}