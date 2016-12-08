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
@Table(name = "building")
@DataTransferObject
public class Building implements Serializable {
//��¥��

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "building_name",length=100,updatable=true)
	private String buildingName;

	@RemoteProperty
	@Column(name = "city_code",length=10,nullable=true,updatable=true)
	private String cityCode;

	@RemoteProperty
	@Column(name = "status",length=3,updatable=true)
	private Integer status;

	@RemoteProperty
	@Column(name = "create_time",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "modify_time",length=0,nullable=true,updatable=true)
	private Date modifyTime;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "remark",length=255,updatable=true)
	private String remark;

	@RemoteProperty
	@Column(name = "remark2",length=255,nullable=true,updatable=true)
	private String remark2;

	@RemoteProperty
	@Column(name = "title",length=255,nullable=true,updatable=true)
	private String title;

	@RemoteProperty
	@Column(name = "logo",length=255,nullable=true,updatable=true)
	private String logo;

	@RemoteProperty
	@Column(name = "sublogo",length=255,nullable=true,updatable=true)
	private String sublogo;

	@RemoteProperty
	@Column(name = "property_note",length=20,nullable=true,updatable=true)
	private String propertyNote;

	@RemoteProperty
	@Column(name = "property_note_bg",length=256,nullable=true,updatable=true)
	private String propertyNoteBg;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.buildingName = buildingName;
	}

		return this.buildingName;
	}

		this.cityCode = cityCode;
	}

		return this.cityCode;
	}

		this.status = status;
	}

		return this.status;
	}

		this.createTime = createTime;
	}

		return this.createTime;
	}

		this.modifyTime = modifyTime;
	}

		return this.modifyTime;
	}

		this.remark = remark;
	}

		return this.remark;
	}

		this.remark2 = remark2;
	}

		return this.remark2;
	}

		this.title = title;
	}

		return this.title;
	}

		this.logo = logo;
	}

		return this.logo;
	}

		this.sublogo = sublogo;
	}

		return this.sublogo;
	}

		this.propertyNote = propertyNote;
	}

		return this.propertyNote;
	}

		this.propertyNoteBg = propertyNoteBg;
	}

		return this.propertyNoteBg;
	}
