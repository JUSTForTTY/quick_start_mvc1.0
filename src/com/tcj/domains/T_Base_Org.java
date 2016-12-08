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
@Table(name = "t_base_org")
@DataTransferObject
public class T_Base_Org implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "ORG_ID",length=10,updatable=true)
	private Integer orgId;

	@RemoteProperty
	@Column(name = "CANCEL_DATE",length=0,nullable=true,updatable=true)
	private Date cancelDate;

	@RemoteProperty
	@Column(name = "CELL_TEL",length=255,nullable=true,updatable=true)
	private String cellTel;

	@RemoteProperty
	@Column(name = "CONTACT_PERSON",length=255,nullable=true,updatable=true)
	private String contactPerson;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATOR",length=40,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=15,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "F_ORG_ID",length=10,nullable=true,updatable=true)
	private Integer fOrgId;

	@RemoteProperty
	@Column(name = "FOUND_DATE",length=0,nullable=true,updatable=true)
	private Date foundDate;

	@RemoteProperty
	@Column(name = "GROUP_SORT",length=10,nullable=true,updatable=true)
	private Integer groupSort;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=255,nullable=true,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "MEMO",length=255,nullable=true,updatable=true)
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
	@Column(name = "ORG_ADDRESS",length=255,nullable=true,updatable=true)
	private String orgAddress;

	@RemoteProperty
	@Column(name = "ORG_CODE",length=255,nullable=true,updatable=true)
	private String orgCode;

	@RemoteProperty
	@Column(name = "ORG_EMAIL",length=255,nullable=true,updatable=true)
	private String orgEmail;

	@RemoteProperty
	@Column(name = "ORG_FAX",length=255,nullable=true,updatable=true)
	private String orgFax;

	@RemoteProperty
	@Column(name = "ORG_FULL_NAME",length=255,nullable=true,updatable=true)
	private String orgFullName;

	@RemoteProperty
	@Column(name = "ORG_NAME",length=255,nullable=true,updatable=true)
	private String orgName;


	public void setOrgId(Integer orgId){
		this.orgId = orgId;
	}

		return this.orgId;
	}

		this.cancelDate = cancelDate;
	}

		return this.cancelDate;
	}

		this.cellTel = cellTel;
	}

		return this.cellTel;
	}

		this.contactPerson = contactPerson;
	}

		return this.contactPerson;
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

		this.fOrgId = fOrgId;
	}

		return this.fOrgId;
	}

		this.foundDate = foundDate;
	}

		return this.foundDate;
	}

		this.groupSort = groupSort;
	}

		return this.groupSort;
	}

		this.isDeleted = isDeleted;
	}

		return this.isDeleted;
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

		this.orgAddress = orgAddress;
	}

		return this.orgAddress;
	}

		this.orgCode = orgCode;
	}

		return this.orgCode;
	}

		this.orgEmail = orgEmail;
	}

		return this.orgEmail;
	}

		this.orgFax = orgFax;
	}

		return this.orgFax;
	}

		this.orgFullName = orgFullName;
	}

		return this.orgFullName;
	}

		this.orgName = orgName;
	}

		return this.orgName;
	}
