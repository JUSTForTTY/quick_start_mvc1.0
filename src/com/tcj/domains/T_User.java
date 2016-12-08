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
@Table(name = "t_user")
@DataTransferObject
public class T_User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=32,updatable=true)
	private String id;

	@RemoteProperty
	@Column(name = "age",length=32,nullable=true,updatable=true)
	private String age;

	@RemoteProperty
	@Column(name = "userName",length=32,nullable=true,updatable=true)
	private String username;


	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setAge(String age){
		this.age = age;
	}
	public String getAge(){
		return this.age;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
}