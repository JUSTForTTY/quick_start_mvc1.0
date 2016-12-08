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

		return this.tid;
	}

		this.comment = comment;
	}

		return this.comment;
	}

		this.parentId = parentId;
	}

		return this.parentId;
	}

		this.pic = pic;
	}

		return this.pic;
	}

		this.smallPic = smallPic;
	}

		return this.smallPic;
	}

		this.tname = tname;
	}

		return this.tname;
	}
