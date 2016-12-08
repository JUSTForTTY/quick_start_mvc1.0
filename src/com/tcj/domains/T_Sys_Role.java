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
@Table(name = "t_sys_role")
@DataTransferObject
public class T_Sys_Role implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "ROLE_ID",length=10,updatable=true)
	private Integer roleId;

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
	@Column(name = "IS_DEFAULT_ROLE",length=1,nullable=true,updatable=true)
	private String isDefaultRole;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "MEMO",length=200,nullable=true,updatable=true)
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
	@Column(name = "ROLE_NAME",length=100,updatable=true)
	private String roleName;


	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}

		return this.roleId;
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

		this.isDefaultRole = isDefaultRole;
	}

		return this.isDefaultRole;
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

		this.roleName = roleName;
	}

		return this.roleName;
	}
