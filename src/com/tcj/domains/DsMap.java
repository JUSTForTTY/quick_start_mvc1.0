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
@Table(name = "ds_map")
@DataTransferObject
public class DsMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "map_id",length=10,updatable=true)
	private Integer mapId;

	@RemoteProperty
	@Column(name = "map_name",length=255,nullable=true,updatable=true)
	private String mapName;

	@RemoteProperty
	@Column(name = "floor",length=255,nullable=true,updatable=true)
	private String floor;

	@RemoteProperty
	@Column(name = "map_level",length=255,nullable=true,updatable=true)
	private String mapLevel;

	@RemoteProperty
	@Column(name = "content",length=65535,nullable=true,updatable=true)
	private String content;


	public void setMapId(Integer mapId){
		this.mapId = mapId;
	}
	public Integer getMapId(){
		return this.mapId;
	}
	public void setMapName(String mapName){
		this.mapName = mapName;
	}
	public String getMapName(){
		return this.mapName;
	}
	public void setFloor(String floor){
		this.floor = floor;
	}
	public String getFloor(){
		return this.floor;
	}
	public void setMapLevel(String mapLevel){
		this.mapLevel = mapLevel;
	}
	public String getMapLevel(){
		return this.mapLevel;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
}