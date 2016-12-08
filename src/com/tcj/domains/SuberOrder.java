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
@Table(name = "sub_order")
@DataTransferObject
public class SuberOrder implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=19,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "order_id",length=19,nullable=true,updatable=true)
	private Integer orderId;

	@RemoteProperty
	@Column(name = "provider_id",length=19,nullable=true,updatable=true)
	private Integer providerId;

	@RemoteProperty
	@Column(name = "member_name",length=255,nullable=true,updatable=true)
	private String memberName;

	@RemoteProperty
	@Column(name = "subtotal_price",length=19,nullable=true,updatable=true)
	private Integer subtotalPrice;

	@RemoteProperty
	@Column(name = "subtotal_score",length=19,nullable=true,updatable=true)
	private Integer subtotalScore;

	@RemoteProperty
	@Column(name = "goods_type_count",length=10,nullable=true,updatable=true)
	private Integer goodsTypeCount;

	@RemoteProperty
	@Column(name = "goods_count",length=10,nullable=true,updatable=true)
	private Integer goodsCount;

	@RemoteProperty
	@Column(name = "status",length=255,nullable=true,updatable=true)
	private String status;

	@RemoteProperty
	@Column(name = "gmt_create",length=0,nullable=true,updatable=true)
	private Date gmtCreate;

	@RemoteProperty
	@Column(name = "gmt_modify",length=0,nullable=true,updatable=true)
	private Date gmtModify;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.orderId = orderId;
	}

		return this.orderId;
	}


		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public void setMemberName(String memberName){
		this.memberName = memberName;
	}

		return this.memberName;
	}

		this.subtotalPrice = subtotalPrice;
	}

		return this.subtotalPrice;
	}

		this.subtotalScore = subtotalScore;
	}

		return this.subtotalScore;
	}

		this.goodsTypeCount = goodsTypeCount;
	}

		return this.goodsTypeCount;
	}

		this.goodsCount = goodsCount;
	}

		return this.goodsCount;
	}

		this.status = status;
	}

		return this.status;
	}

		this.gmtCreate = gmtCreate;
	}

		return this.gmtCreate;
	}

		this.gmtModify = gmtModify;
	}

		return this.gmtModify;
	}
