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
@Table(name = "goods_collect")
@DataTransferObject
public class GoodsCollect implements Serializable {
//��Ʒ�ղر�

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "type",length=32,nullable=true,updatable=true)
	private String type;

	@RemoteProperty
	@Column(name = "goods_id",length=10,nullable=true,updatable=true)
	private Integer goodsId;

	@RemoteProperty
	@Column(name = "user_name",length=50,nullable=true,updatable=true)
	private String userName;

	@RemoteProperty
	@Column(name = "create_time",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "remark",length=1024,nullable=true,updatable=true)
	private String remark;

	@RemoteProperty
	@Column(name = "tag",length=256,nullable=true,updatable=true)
	private String tag;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.type = type;
	}

		return this.type;
	}

		this.goodsId = goodsId;
	}

		return this.goodsId;
	}

		this.userName = userName;
	}

		return this.userName;
	}

		this.createTime = createTime;
	}

		return this.createTime;
	}

		this.remark = remark;
	}

		return this.remark;
	}

		this.tag = tag;
	}

		return this.tag;
	}
