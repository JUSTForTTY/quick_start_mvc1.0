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
@Table(name = "t_type_code")
@DataTransferObject
public class T_Type_Code implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "TID",length=10,updatable=true)
	private Integer tid;

	@RemoteProperty
	@Column(name = "COMMENT",length=255,updatable=true)
	private String comment;

	@RemoteProperty
	@Column(name = "PARENT_ID",length=10,updatable=true)
	private Integer parentId;

	@RemoteProperty
	@Column(name = "PIC",length=255,updatable=true)
	private String pic;

	@RemoteProperty
	@Column(name = "SMALL_PIC",length=255,updatable=true)
	private String smallPic;

	@RemoteProperty
	@Column(name = "TNAME",length=100,updatable=true)
	private String tname;


	public void setTid(Integer tid){
		this.tid = tid;
	}
	public Integer getTid(){
		return this.tid;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
	}
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	public Integer getParentId(){
		return this.parentId;
	}
	public void setPic(String pic){
		this.pic = pic;
	}
	public String getPic(){
		return this.pic;
	}
	public void setSmallPic(String smallPic){
		this.smallPic = smallPic;
	}
	public String getSmallPic(){
		return this.smallPic;
	}
	public void setTname(String tname){
		this.tname = tname;
	}
	public String getTname(){
		return this.tname;
	}
}