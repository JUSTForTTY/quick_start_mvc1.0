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
@Table(name = "t_base_service_center")
@DataTransferObject
public class T_Base_Service_Center implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "SERVICE_CENTER_ID",length=10,updatable=true)
	private Integer serviceCenterId;

	@RemoteProperty
	@Column(name = "ACCOMPANY_USER_ID",length=10,nullable=true,updatable=true)
	private String accompanyUserId;

	@RemoteProperty
	@Column(name = "AQXFXY_TP_PATH",length=100,nullable=true,updatable=true)
	private String aqxfxyTpPath;

	@RemoteProperty
	@Column(name = "BLCRKTZD_TP_PATH",length=100,nullable=true,updatable=true)
	private String blcrktzdTpPath;

	@RemoteProperty
	@Column(name = "CHECK_USER_ID",length=10,nullable=true,updatable=true)
	private Integer checkUserId;

	@RemoteProperty
	@Column(name = "CQRDZWTS_TP_PATH",length=100,nullable=true,updatable=true)
	private String cqrdzwtsTpPath;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATOR",length=40,nullable=true,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=15,nullable=true,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "FWZLHT_TP_PATH",length=100,nullable=true,updatable=true)
	private String fwzlhtTpPath;

	@RemoteProperty
	@Column(name = "FWZLWTS_TP_PATH",length=100,nullable=true,updatable=true)
	private String fwzlwtsTpPath;

	@RemoteProperty
	@Column(name = "INTENTION_APPOINT_USER_ID",length=10,nullable=true,updatable=true)
	private Integer intentionAppointUserId;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,nullable=true,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "JZZBLSQD_TP_PATH",length=100,nullable=true,updatable=true)
	private String jzzblsqdTpPath;

	@RemoteProperty
	@Column(name = "LEASE_TRANSACTION_USER_ID",length=10,nullable=true,updatable=true)
	private String leaseTransactionUserId;

	@RemoteProperty
	@Column(name = "MEMO",length=200,nullable=true,updatable=true)
	private String memo;

	@RemoteProperty
	@Column(name = "MODIFIER",length=40,nullable=true,updatable=true)
	private String modifier;

	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=15,nullable=true,updatable=true)
	private String modifierIp;

	@RemoteProperty
	@Column(name = "MODIFY_TIME",length=0,nullable=true,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "PUBLISH_USER_ID",length=10,nullable=true,updatable=true)
	private Integer publishUserId;

	@RemoteProperty
	@Column(name = "SERVICE_CENTER_ADD",length=100,nullable=true,updatable=true)
	private String serviceCenterAdd;

	@RemoteProperty
	@Column(name = "SERVICE_CENTER_NAME",length=50,nullable=true,updatable=true)
	private String serviceCenterName;

	@RemoteProperty
	@Column(name = "SERVICE_CENTER_TEL",length=20,nullable=true,updatable=true)
	private String serviceCenterTel;

	@RemoteProperty
	@Column(name = "SERVICE_PHOTO_PATH",length=100,nullable=true,updatable=true)
	private String servicePhotoPath;

	@RemoteProperty
	@Column(name = "ZAZRBZS_TP_PATH",length=100,nullable=true,updatable=true)
	private String zazrbzsTpPath;


	public void setServiceCenterId(Integer serviceCenterId){
		this.serviceCenterId = serviceCenterId;
	}

		return this.serviceCenterId;
	}

		this.accompanyUserId = accompanyUserId;
	}

		return this.accompanyUserId;
	}

		this.aqxfxyTpPath = aqxfxyTpPath;
	}

		return this.aqxfxyTpPath;
	}

		this.blcrktzdTpPath = blcrktzdTpPath;
	}

		return this.blcrktzdTpPath;
	}

		this.checkUserId = checkUserId;
	}

		return this.checkUserId;
	}

		this.cqrdzwtsTpPath = cqrdzwtsTpPath;
	}

		return this.cqrdzwtsTpPath;
	}

		this.createTime = createTime;
	}

		return this.createTime;
	}

		this.creator = creator;
	}

		return this.creator;
	}

		this.creatorIp = creatorIp;
	}

		return this.creatorIp;
	}

		this.fwzlhtTpPath = fwzlhtTpPath;
	}

		return this.fwzlhtTpPath;
	}

		this.fwzlwtsTpPath = fwzlwtsTpPath;
	}

		return this.fwzlwtsTpPath;
	}

		this.intentionAppointUserId = intentionAppointUserId;
	}

		return this.intentionAppointUserId;
	}

		this.isDeleted = isDeleted;
	}

		return this.isDeleted;
	}

		this.jzzblsqdTpPath = jzzblsqdTpPath;
	}

		return this.jzzblsqdTpPath;
	}

		this.leaseTransactionUserId = leaseTransactionUserId;
	}

		return this.leaseTransactionUserId;
	}

		this.memo = memo;
	}

		return this.memo;
	}

		this.modifier = modifier;
	}

		return this.modifier;
	}

		this.modifierIp = modifierIp;
	}

		return this.modifierIp;
	}

		this.modifyTime = modifyTime;
	}

		return this.modifyTime;
	}

		this.publishUserId = publishUserId;
	}

		return this.publishUserId;
	}

		this.serviceCenterAdd = serviceCenterAdd;
	}

		return this.serviceCenterAdd;
	}

		this.serviceCenterName = serviceCenterName;
	}

		return this.serviceCenterName;
	}

		this.serviceCenterTel = serviceCenterTel;
	}

		return this.serviceCenterTel;
	}

		this.servicePhotoPath = servicePhotoPath;
	}

		return this.servicePhotoPath;
	}

		this.zazrbzsTpPath = zazrbzsTpPath;
	}

		return this.zazrbzsTpPath;
	}
