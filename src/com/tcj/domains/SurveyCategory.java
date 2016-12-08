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
@Table(name = "survey_category")
@DataTransferObject
public class SurveyCategory implements Serializable {


	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "Id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "name",length=255,nullable=true,updatable=true)
	private String name;

	@RemoteProperty
	@Column(name = "picLoc1",length=255,nullable=true,updatable=true)
	private String picloc1;

	@RemoteProperty
	@Column(name = "picLoc2",length=255,nullable=true,updatable=true)
	private String picloc2;

	@RemoteProperty
	@Column(name = "picLoc3",length=255,nullable=true,updatable=true)
	private String picloc3;

	@RemoteProperty
	@Column(name = "picLoc4",length=255,nullable=true,updatable=true)
	private String picloc4;

	@RemoteProperty
	@Column(name = "picLoc5",length=255,nullable=true,updatable=true)
	private String picloc5;

	@RemoteProperty
	@Column(name = "content",length=65535,nullable=true,updatable=true)
	private String content;

	@RemoteProperty
	@Column(name = "index_id",length=255,nullable=true,updatable=true)
	private String indexId;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.name = name;
	}

		return this.name;
	}

		this.picloc1 = picloc1;
	}

		return this.picloc1;
	}

		this.picloc2 = picloc2;
	}

		return this.picloc2;
	}

		this.picloc3 = picloc3;
	}

		return this.picloc3;
	}

		this.picloc4 = picloc4;
	}

		return this.picloc4;
	}

		this.picloc5 = picloc5;
	}

		return this.picloc5;
	}

		this.content = content;
	}

		return this.content;
	}

		this.indexId = indexId;
	}

		return this.indexId;
	}
