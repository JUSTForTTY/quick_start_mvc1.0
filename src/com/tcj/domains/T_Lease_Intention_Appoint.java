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
@Table(name = "t_lease_intention_appoint")
@DataTransferObject
public class T_Lease_Intention_Appoint implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "INTENTION_APPOINT_ID",length=10,updatable=true)
	private Integer intentionAppointId;

	@RemoteProperty
	@Column(name = "ASK_USER_ID",length=10,updatable=true)
	private Integer askUserId;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATE_TYPE",length=1,updatable=true)
	private String createType;

	@RemoteProperty
	@Column(name = "CREATOR",length=40,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=15,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "HOUSE_CODE",length=11,updatable=true)
	private String houseCode;

	@RemoteProperty
	@Column(name = "HOUSE_INFO_ID",length=10,updatable=true)
	private Integer houseInfoId;

	@RemoteProperty
	@Column(name = "INFORM_STATUS",length=1,updatable=true)
	private String informStatus;

	@RemoteProperty
	@Column(name = "INTENTION_APPOINT_CD",length=11,updatable=true)
	private String intentionAppointCd;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "LEASE_IS_DEAL",length=1,updatable=true)
	private String leaseIsDeal;

	@RemoteProperty
	@Column(name = "LEASE_TYPE",length=1,updatable=true)
	private String leaseType;

	@RemoteProperty
	@Column(name = "LEASER_USER_ID",length=10,updatable=true)
	private Integer leaserUserId;

	@RemoteProperty
	@Column(name = "MEMO",length=200,updatable=true)
	private String memo;

	@RemoteProperty
	@Column(name = "MODIFIER",length=40,updatable=true)
	private String modifier;

	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=15,updatable=true)
	private String modifierIp;

	@RemoteProperty
	@Column(name = "MODIFY_TIME",length=0,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "S_R_R_TIME",length=0,updatable=true)
	private Date sRRTime;

	@RemoteProperty
	@Column(name = "S_R_RECORDER_USER_ID",length=10,updatable=true)
	private Integer sRRecorderUserId;

	@RemoteProperty
	@Column(name = "SATISFACTION_RESULT_MEMO",length=200,updatable=true)
	private String satisfactionResultMemo;

	@RemoteProperty
	@Column(name = "SOURCE_USER_ID",length=10,updatable=true)
	private Integer sourceUserId;

	@RemoteProperty
	@Column(name = "SOURCE_USER_TYPE",length=1,updatable=true)
	private String sourceUserType;

	@RemoteProperty
	@Column(name = "VERIFY_TIME_APPOINT",length=100,updatable=true)
	private String verifyTimeAppoint;

	@RemoteProperty
	@Column(name = "VERIFY_TIME_PRACTICAL",length=100,updatable=true)
	private String verifyTimePractical;

	@RemoteProperty
	@Column(name = "VERIFY_TIME_WANT",length=100,updatable=true)
	private String verifyTimeWant;


	public void setIntentionAppointId(Integer intentionAppointId){
		this.intentionAppointId = intentionAppointId;
	}

		return this.intentionAppointId;
	}

		this.askUserId = askUserId;
	}

		return this.askUserId;
	}

		this.createTime = createTime;
	}

		return this.createTime;
	}

		this.createType = createType;
	}

		return this.createType;
	}

		this.creator = creator;
	}

		return this.creator;
	}

		this.creatorIp = creatorIp;
	}

		return this.creatorIp;
	}

		this.houseCode = houseCode;
	}

		return this.houseCode;
	}

		this.houseInfoId = houseInfoId;
	}

		return this.houseInfoId;
	}

		this.informStatus = informStatus;
	}

		return this.informStatus;
	}

		this.intentionAppointCd = intentionAppointCd;
	}

		return this.intentionAppointCd;
	}

		this.isDeleted = isDeleted;
	}

		return this.isDeleted;
	}

		this.leaseIsDeal = leaseIsDeal;
	}

		return this.leaseIsDeal;
	}

		this.leaseType = leaseType;
	}

		return this.leaseType;
	}

		this.leaserUserId = leaserUserId;
	}

		return this.leaserUserId;
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

		this.sRRTime = sRRTime;
	}

		return this.sRRTime;
	}

		this.sRRecorderUserId = sRRecorderUserId;
	}

		return this.sRRecorderUserId;
	}

		this.satisfactionResultMemo = satisfactionResultMemo;
	}

		return this.satisfactionResultMemo;
	}

		this.sourceUserId = sourceUserId;
	}

		return this.sourceUserId;
	}

		this.sourceUserType = sourceUserType;
	}

		return this.sourceUserType;
	}

		this.verifyTimeAppoint = verifyTimeAppoint;
	}

		return this.verifyTimeAppoint;
	}

		this.verifyTimePractical = verifyTimePractical;
	}

		return this.verifyTimePractical;
	}

		this.verifyTimeWant = verifyTimeWant;
	}

		return this.verifyTimeWant;
	}
