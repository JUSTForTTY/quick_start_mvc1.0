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
@Table(name = "timingrecord")
@DataTransferObject
public class TimingRecord implements Serializable {
//��ʱִ�м�¼��

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "timing_id",length=10,nullable=true,updatable=true)
	private Integer timingId;

	@RemoteProperty
	@Column(name = "modify_time",length=0,nullable=true,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "modify_name",length=50,nullable=true,updatable=true)
	private String modifyName;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.timingId = timingId;
	}

		return this.timingId;
	}

		this.modifyTime = modifyTime;
	}

		return this.modifyTime;
	}

		this.modifyName = modifyName;
	}

		return this.modifyName;
	}
