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

		return this.id;
	}

		this.age = age;
	}

		return this.age;
	}

		this.username = username;
	}

		return this.username;
	}
