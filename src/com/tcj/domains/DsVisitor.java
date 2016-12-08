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
@Table(name = "ds_visitor")
@DataTransferObject
public class DsVisitor implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "name",length=255,nullable=true,updatable=true)
	private String name;

	@RemoteProperty
	@Column(name = "sex",length=255,nullable=true,updatable=true)
	private String sex;

	@RemoteProperty
	@Column(name = "tel",length=255,nullable=true,updatable=true)
	private String tel;

	@RemoteProperty
	@Column(name = "provider_id",length=19,nullable=true,updatable=true)
	private Integer providerId;
	
	
	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	@RemoteProperty
	@Column(name = "time",length=255,nullable=true,updatable=true)
	private String time;

	@RemoteProperty
	@Column(name = "visitor_feedback",length=65535,nullable=true,updatable=true)
	private String visitorFeedback;

	@RemoteProperty
	@Column(name = "count",length=10,nullable=true,updatable=true)
	private Integer count;

	@RemoteProperty
	@Column(name = "email",length=255,nullable=true,updatable=true)
	private String email;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.name = name;
	}

		return this.name;
	}

		this.sex = sex;
	}

		return this.sex;
	}

		this.tel = tel;
	}

		return this.tel;
	}

		this.time = time;
	}

		return this.time;
	}

		this.visitorFeedback = visitorFeedback;
	}

		return this.visitorFeedback;
	}

		this.count = count;
	}

		return this.count;
	}

		this.email = email;
	}

		return this.email;
	}
