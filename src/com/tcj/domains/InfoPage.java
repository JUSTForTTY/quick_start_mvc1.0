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
@Table(name = "infopage")
@DataTransferObject
public class InfoPage implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "infotrunk_id",length=10,updatable=true)
	private Integer infotrunkId;

	@RemoteProperty
	@Column(name = "pageno",length=10,updatable=true)
	private Integer pageno;

	@RemoteProperty
	@Column(name = "content",length=65535,updatable=true)
	private String content;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.infotrunkId = infotrunkId;
	}

		return this.infotrunkId;
	}

		this.pageno = pageno;
	}

		return this.pageno;
	}

		this.content = content;
	}

		return this.content;
	}
