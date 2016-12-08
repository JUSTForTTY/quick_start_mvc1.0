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
@Table(name = "t_base_user")
@DataTransferObject
public class T_Base_User implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "USER_ID",length=10,updatable=true)
	private Integer userId;

	@RemoteProperty
	@Column(name = "CELL_TEL",length=255,nullable=true,updatable=true)
	private String cellTel;

	@RemoteProperty
	@Column(name = "CREATE_TIME",length=0,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "CREATOR",length=255,updatable=true)
	private String creator;

	@RemoteProperty
	@Column(name = "CREATOR_IP",length=255,updatable=true)
	private String creatorIp;

	@RemoteProperty
	@Column(name = "DOMICILE_PLACE",length=255,nullable=true,updatable=true)
	private String domicilePlace;

	@RemoteProperty
	@Column(name = "HOBBIES_ID",length=255,nullable=true,updatable=true)
	private String hobbiesId;

	@RemoteProperty
	@Column(name = "ID_CARD_NO",length=255,nullable=true,updatable=true)
	private String idCardNo;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "IS_LOCKED",length=255,nullable=true,updatable=true)
	private String isLocked;

	@RemoteProperty
	@Column(name = "LAST_LOGIN_TIME",length=0,nullable=true,updatable=true)
	private Date lastLoginTime;

	@RemoteProperty
	@Column(name = "LOGIN_ID",length=255,nullable=true,updatable=true)
	private String loginId;

	@RemoteProperty
	@Column(name = "LOGIN_PWD",length=255,nullable=true,updatable=true)
	private String loginPwd;

	@RemoteProperty
	@Column(name = "LOGIN_TIMES",length=10,nullable=true,updatable=true)
	private Integer loginTimes;

	@RemoteProperty
	@Column(name = "MARITAL_STATUS",length=1,nullable=true,updatable=true)
	private String maritalStatus;

	@RemoteProperty
	@Column(name = "MODIFIER",length=255,updatable=true)
	private String modifier;

	@RemoteProperty
	@Column(name = "MODIFIER_IP",length=255,updatable=true)
	private String modifierIp;

	@RemoteProperty
	@Column(name = "MODIFY_TIME",length=0,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "OCCUPATION_ID",length=255,nullable=true,updatable=true)
	private String occupationId;

	@RemoteProperty
	@Column(name = "ORG_ID",length=10,nullable=true,updatable=true)
	private Integer orgId;

	@RemoteProperty
	@Column(name = "OTHER_CON",length=255,nullable=true,updatable=true)
	private String otherCon;

	@RemoteProperty
	@Column(name = "PHOTO_PATH",length=255,nullable=true,updatable=true)
	private String photoPath;

	@RemoteProperty
	@Column(name = "POST_ID",length=255,nullable=true,updatable=true)
	private String postId;

	@RemoteProperty
	@Column(name = "REG_RESOURCE",length=255,nullable=true,updatable=true)
	private String regResource;

	@RemoteProperty
	@Column(name = "REG_RESOURCE_CD",length=255,nullable=true,updatable=true)
	private String regResourceCd;

	@RemoteProperty
	@Column(name = "ROLE_ID",length=10,nullable=true,updatable=true)
	private Integer roleId;

	@RemoteProperty
	@Column(name = "SERVICE_CENTER_ID",length=10,nullable=true,updatable=true)
	private Integer serviceCenterId;

	@RemoteProperty
	@Column(name = "USER_BIRTHDAY",length=0,nullable=true,updatable=true)
	private Date userBirthday;

	@RemoteProperty
	@Column(name = "USER_EMAIL",length=255,nullable=true,updatable=true)
	private String userEmail;

	@RemoteProperty
	@Column(name = "USER_NAME",length=255,nullable=true,updatable=true)
	private String userName;

	@RemoteProperty
	@Column(name = "USER_SEX",length=255,nullable=true,updatable=true)
	private String userSex;

	@RemoteProperty
	@Column(name = "USER_TYPE_FLAG",length=255,nullable=true,updatable=true)
	private String userTypeFlag;

	@RemoteProperty
	@Column(name = "USER_TYPE_ID",length=10,nullable=true,updatable=true)
	private Integer userTypeId;


	public void setUserId(Integer userId){
		this.userId = userId;
	}

		return this.userId;
	}

		this.cellTel = cellTel;
	}

		return this.cellTel;
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

		this.domicilePlace = domicilePlace;
	}

		return this.domicilePlace;
	}

		this.hobbiesId = hobbiesId;
	}

		return this.hobbiesId;
	}

		this.idCardNo = idCardNo;
	}

		return this.idCardNo;
	}

		this.isDeleted = isDeleted;
	}

		return this.isDeleted;
	}

		this.isLocked = isLocked;
	}

		return this.isLocked;
	}

		this.lastLoginTime = lastLoginTime;
	}

		return this.lastLoginTime;
	}

		this.loginId = loginId;
	}

		return this.loginId;
	}

		this.loginPwd = loginPwd;
	}

		return this.loginPwd;
	}

		this.loginTimes = loginTimes;
	}

		return this.loginTimes;
	}

		this.maritalStatus = maritalStatus;
	}

		return this.maritalStatus;
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

		this.occupationId = occupationId;
	}

		return this.occupationId;
	}

		this.orgId = orgId;
	}

		return this.orgId;
	}

		this.otherCon = otherCon;
	}

		return this.otherCon;
	}

		this.photoPath = photoPath;
	}

		return this.photoPath;
	}

		this.postId = postId;
	}

		return this.postId;
	}

		this.regResource = regResource;
	}

		return this.regResource;
	}

		this.regResourceCd = regResourceCd;
	}

		return this.regResourceCd;
	}

		this.roleId = roleId;
	}

		return this.roleId;
	}

		this.serviceCenterId = serviceCenterId;
	}

		return this.serviceCenterId;
	}

		this.userBirthday = userBirthday;
	}

		return this.userBirthday;
	}

		this.userEmail = userEmail;
	}

		return this.userEmail;
	}

		this.userName = userName;
	}

		return this.userName;
	}

		this.userSex = userSex;
	}

		return this.userSex;
	}

		this.userTypeFlag = userTypeFlag;
	}

		return this.userTypeFlag;
	}

		this.userTypeId = userTypeId;
	}

		return this.userTypeId;
	}
