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
@Table(name = "t_supply_info")
@DataTransferObject
public class T_Supply_Info implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "SID",length=10,updatable=true)
	private Integer sid;

	@RemoteProperty
	@Column(name = "ADDRESS",length=100,updatable=true)
	private String address;

	@RemoteProperty
	@Column(name = "BACKUP",length=20,updatable=true)
	private String backup;

	@RemoteProperty
	@Column(name = "CHARGEMAN",length=20,updatable=true)
	private String chargeman;

	@RemoteProperty
	@Column(name = "COMMENT",length=255,updatable=true)
	private String comment;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=32,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "MOBILE",length=20,updatable=true)
	private String mobile;

	@RemoteProperty
	@Column(name = "ORGAN_CODE",length=20,updatable=true)
	private String organCode;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=1,updatable=true)
	private String roleId;

	@RemoteProperty
	@Column(name = "SNAME",length=30,updatable=true)
	private String sname;


	public void setSid(Integer sid){
		this.sid = sid;
	}

		return this.sid;
	}

		this.address = address;
	}

		return this.address;
	}

		this.backup = backup;
	}

		return this.backup;
	}

		this.chargeman = chargeman;
	}

		return this.chargeman;
	}

		this.comment = comment;
	}

		return this.comment;
	}

		this.loginPwd = loginPwd;
	}

		return this.loginPwd;
	}

		this.mobile = mobile;
	}

		return this.mobile;
	}

		this.organCode = organCode;
	}

		return this.organCode;
	}

		this.roleId = roleId;
	}

		return this.roleId;
	}

		this.sname = sname;
	}

		return this.sname;
	}
