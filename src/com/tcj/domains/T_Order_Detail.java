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
@Table(name = "t_order_detail")
@DataTransferObject
public class T_Order_Detail implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "OID",length=10,updatable=true)
	private Integer oid;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "GID",length=10,updatable=true)
	private Integer gid;

	@RemoteProperty
	@Column(name = "BRAND",length=30,updatable=true)
	private String brand;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_USER",length=10,updatable=true)
	private Integer createUser;

	@RemoteProperty
	@Column(name = "GNAME",length=100,updatable=true)
	private String gname;

	@RemoteProperty
	@Column(name = "GRADE",length=10,updatable=true)
	private String grade;

	@RemoteProperty
	@Column(name = "MIN_NUM",length=12,updatable=true)
	private Float minNum;

	@RemoteProperty
	@Column(name = "PIC",length=255,updatable=true)
	private String pic;

	@RemoteProperty
	@Column(name = "PRICE",length=12,updatable=true)
	private Float price;

	@RemoteProperty
	@Column(name = "PRODUCER",length=50,updatable=true)
	private String producer;

	@RemoteProperty
	@Column(name = "QUANTITY",length=12,updatable=true)
	private Float quantity;

	@RemoteProperty
	@Column(name = "SALE_FLAG",length=1,updatable=true)
	private String saleFlag;

	@RemoteProperty
	@Column(name = "SPECIAL_PRICE",length=12,updatable=true)
	private Float specialPrice;

	@RemoteProperty
	@Column(name = "STEP",length=12,updatable=true)
	private Float step;

	@RemoteProperty
	@Column(name = "STOCK",length=12,updatable=true)
	private Float stock;

	@RemoteProperty
	@Column(name = "TID",length=10,updatable=true)
	private Integer tid;

	@RemoteProperty
	@Column(name = "UNIT",length=20,updatable=true)
	private String unit;

	@RemoteProperty
	@Column(name = "UNIT_COMMENT",length=50,updatable=true)
	private String unitComment;

	@RemoteProperty
	@Column(name = "UPDATE_TIME",length=0,updatable=true)
	private Date updateTime;

	@RemoteProperty
	@Column(name = "UPDATE_USER",length=10,updatable=true)
	private Integer updateUser;


	public void setOid(Integer oid){
		this.oid = oid;
	}

		return this.oid;
	}

		this.gid = gid;
	}

		return this.gid;
	}

		this.brand = brand;
	}

		return this.brand;
	}

		this.createTime = createTime;
	}

		return this.createTime;
	}

		this.createUser = createUser;
	}

		return this.createUser;
	}

		this.gname = gname;
	}

		return this.gname;
	}

		this.grade = grade;
	}

		return this.grade;
	}

		this.minNum = minNum;
	}

		return this.minNum;
	}

		this.pic = pic;
	}

		return this.pic;
	}

		this.price = price;
	}

		return this.price;
	}

		this.producer = producer;
	}

		return this.producer;
	}

		this.quantity = quantity;
	}

		return this.quantity;
	}

		this.saleFlag = saleFlag;
	}

		return this.saleFlag;
	}

		this.specialPrice = specialPrice;
	}

		return this.specialPrice;
	}

		this.step = step;
	}

		return this.step;
	}

		this.stock = stock;
	}

		return this.stock;
	}

		this.tid = tid;
	}

		return this.tid;
	}

		this.unit = unit;
	}

		return this.unit;
	}

		this.unitComment = unitComment;
	}

		return this.unitComment;
	}

		this.updateTime = updateTime;
	}

		return this.updateTime;
	}

		this.updateUser = updateUser;
	}

		return this.updateUser;
	}
