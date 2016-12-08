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
@Table(name = "stock_log")
@DataTransferObject
public class StockLog implements Serializable {
//������־��

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "importBillID",length=255,updatable=true)
	private String importbillid;

	@RemoteProperty
	@Column(name = "user_name",length=255,nullable=true,updatable=true)
	private String userName;

	@RemoteProperty
	@Column(name = "goodkinds",length=255,nullable=true,updatable=true)
	private String goodkinds;

	@RemoteProperty
	@Column(name = "totalPrice",length=10,nullable=true,updatable=true)
	private Integer totalprice;

	@RemoteProperty
	@Column(name = "create_time",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "create_user",length=255,nullable=true,updatable=true)
	private String createUser;

	@RemoteProperty
	@Column(name = "update_time",length=0,nullable=true,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "update_user",length=255,nullable=true,updatable=true)
	private String updateUser;


	public void setImportbillid(String importbillid){
		this.importbillid = importbillid;
	}

		return this.importbillid;
	}

		this.userName = userName;
	}

		return this.userName;
	}

		this.goodkinds = goodkinds;
	}

		return this.goodkinds;
	}

		this.totalprice = totalprice;
	}

		return this.totalprice;
	}

		this.createTime = createTime;
	}

		return this.createTime;
	}

		this.createUser = createUser;
	}

		return this.createUser;
	}

		this.updateTime = updateTime;
	}

		return this.updateTime;
	}

		this.updateUser = updateUser;
	}

		return this.updateUser;
	}
