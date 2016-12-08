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
@Table(name = "t_base_user_type")
@DataTransferObject
public class T_Base_User_Type implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "USER_TYPE_ID",length=10,updatable=true)
	private Integer userTypeId;

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
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

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
	@Column(name = "USER_TYPE_DESC",length=200,nullable=true,updatable=true)
	private String userTypeDesc;

	@RemoteProperty
	@Column(name = "USER_TYPE_FLAG",length=1,updatable=true)
	private String userTypeFlag;

	@RemoteProperty
	@Column(name = "USER_TYPE_NAME",length=40,updatable=true)
	private String userTypeName;


	public void setUserTypeId(Integer userTypeId){
		this.userTypeId = userTypeId;
	}

		return this.userTypeId;
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

		this.isDeleted = isDeleted;
	}

		return this.isDeleted;
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

		this.userTypeDesc = userTypeDesc;
	}

		return this.userTypeDesc;
	}

		this.userTypeFlag = userTypeFlag;
	}

		return this.userTypeFlag;
	}

		this.userTypeName = userTypeName;
	}

		return this.userTypeName;
	}
