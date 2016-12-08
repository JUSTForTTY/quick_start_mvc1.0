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
@Table(name = "orderprocessentity")
@DataTransferObject
public class OrderProcessEntity implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "GID",length=10,updatable=true)
	private Integer gid;

	@RemoteProperty
	@Column(name = "cid",length=10,nullable=true,updatable=true)
	private Integer cid;

	@RemoteProperty
	@Column(name = "cname",length=255,nullable=true,updatable=true)
	private String cname;

	@RemoteProperty
	@Column(name = "ctime",length=255,nullable=true,updatable=true)
	private String ctime;

	@RemoteProperty
	@Column(name = "gname",length=255,nullable=true,updatable=true)
	private String gname;

	@RemoteProperty
	@Column(name = "pack",length=10,nullable=true,updatable=true)
	private Integer pack;

	@RemoteProperty
	@Column(name = "quantity",length=12,nullable=true,updatable=true)
	private Float quantity;

	@RemoteProperty
	@Column(name = "type",length=255,nullable=true,updatable=true)
	private String type;

	@RemoteProperty
	@Column(name = "uid",length=10,nullable=true,updatable=true)
	private Integer uid;

	@RemoteProperty
	@Column(name = "uname",length=255,nullable=true,updatable=true)
	private String uname;

	@RemoteProperty
	@Column(name = "unit",length=255,nullable=true,updatable=true)
	private String unit;


	public void setGid(Integer gid){
		this.gid = gid;
	}

		return this.gid;
	}

		this.cid = cid;
	}

		return this.cid;
	}

		this.cname = cname;
	}

		return this.cname;
	}

		this.ctime = ctime;
	}

		return this.ctime;
	}

		this.gname = gname;
	}

		return this.gname;
	}

		this.pack = pack;
	}

		return this.pack;
	}

		this.quantity = quantity;
	}

		return this.quantity;
	}

		this.type = type;
	}

		return this.type;
	}

		this.uid = uid;
	}

		return this.uid;
	}

		this.uname = uname;
	}

		return this.uname;
	}

		this.unit = unit;
	}

		return this.unit;
	}
