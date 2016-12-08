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
@Table(name = "ds_index")
@DataTransferObject
public class DsIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "Id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "sequence",length=10,nullable=true,updatable=true)
	private Integer sequence;

	@RemoteProperty
	@Column(name = "picLoc",length=255,nullable=true,updatable=true)
	private String picloc;

	@RemoteProperty
	@Column(name = "content",length=255,nullable=true,updatable=true)
	private String content;

	@RemoteProperty
	@Column(name = "englishContent",length=255,nullable=true,updatable=true)
	private String englishcontent;

	@RemoteProperty
	@Column(name = "link",length=255,nullable=true,updatable=true)
	private String link;

	@RemoteProperty
	@Column(name = "category",length=255,nullable=true,updatable=true)
	private String category;

	@RemoteProperty
	@Column(name = "buildingId",length=255,nullable=true,updatable=true)
	private String buildingid;


	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setSequence(Integer sequence){
		this.sequence = sequence;
	}
	public Integer getSequence(){
		return this.sequence;
	}
	public void setPicloc(String picloc){
		this.picloc = picloc;
	}
	public String getPicloc(){
		return this.picloc;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setEnglishcontent(String englishcontent){
		this.englishcontent = englishcontent;
	}
	public String getEnglishcontent(){
		return this.englishcontent;
	}
	public void setLink(String link){
		this.link = link;
	}
	public String getLink(){
		return this.link;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}
	public void setBuildingid(String buildingid){
		this.buildingid = buildingid;
	}
	public String getBuildingid(){
		return this.buildingid;
	}
}