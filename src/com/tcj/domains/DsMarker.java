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
@Table(name = "ds_marker")
@DataTransferObject
public class DsMarker implements Serializable {
	private static final long serialVersionUID = 1L;

	@RemoteProperty
	@Column(name = "map_id",length=10,updatable=true)
	private Integer mapId;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "marker_id",length=10,updatable=true)
	private Integer markerId;

	@RemoteProperty
	@Column(name = "marker_name",length=255,nullable=true,updatable=true)
	private String markerName;

	@RemoteProperty
	@Column(name = "marker_longitude",length=255,nullable=true,updatable=true)
	private String markerLongitude;

	@RemoteProperty
	@Column(name = "marker_latitude",length=255,nullable=true,updatable=true)
	private String markerLatitude;

	@RemoteProperty
	@Column(name = "marker_coordinate",length=255,nullable=true,updatable=true)
	private String markerCoordinate;


	public void setMapId(Integer mapId){
		this.mapId = mapId;
	}
	public Integer getMapId(){
		return this.mapId;
	}
	public void setMarkerId(Integer markerId){
		this.markerId = markerId;
	}
	public Integer getMarkerId(){
		return this.markerId;
	}
	public void setMarkerName(String markerName){
		this.markerName = markerName;
	}
	public String getMarkerName(){
		return this.markerName;
	}
	public void setMarkerLongitude(String markerLongitude){
		this.markerLongitude = markerLongitude;
	}
	public String getMarkerLongitude(){
		return this.markerLongitude;
	}
	public void setMarkerLatitude(String markerLatitude){
		this.markerLatitude = markerLatitude;
	}
	public String getMarkerLatitude(){
		return this.markerLatitude;
	}
	public void setMarkerCoordinate(String markerCoordinate){
		this.markerCoordinate = markerCoordinate;
	}
	public String getMarkerCoordinate(){
		return this.markerCoordinate;
	}
}