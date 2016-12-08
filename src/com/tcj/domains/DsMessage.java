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
@Table(name = "ds_message")
@DataTransferObject
public class DsMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=11,updatable=true)
	private Integer id;
	
	@RemoteProperty
	@Column(name = "member_id",length=11,nullable=true,updatable=true)
	private Integer memberId;

	@RemoteProperty
	@Column(name = "building_id",length=255,nullable=true,updatable=true)
	private String buildingId;

	@RemoteProperty
	@Column(name = "name",length=255,nullable=true,updatable=true)
	private String name;
	@RemoteProperty
	@Column(name = "content",length=65535,nullable=true,updatable=true)
	private String content;
	@RemoteProperty
	@Column(name = "mobile",length=255,nullable=true,updatable=true)
	private String mobile;
	@RemoteProperty
	@Column(name = "email",length=255,nullable=true,updatable=true)
	private String email;

	@RemoteProperty
	@Column(name = "addTime",length=255,nullable=true,updatable=true)
	private String addtime;


	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setBuildingId(String buildingId){
		this.buildingId = buildingId;
	}
	public String getBuildingId(){
		return this.buildingId;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setAddtime(String addtime){
		this.addtime = addtime;
	}
	public String getAddtime(){
		return this.addtime;
	}
}