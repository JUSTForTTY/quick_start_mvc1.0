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
@Table(name = "assoc_building_news")
@DataTransferObject
public class AssocBuildingNews implements Serializable {
//��Ʒ�����Ź�ϵ��

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "Id",length=10,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "news_id",length=19,nullable=true,updatable=true)
	private Integer newsId;

	@RemoteProperty
	@Column(name = "building_id",length=19,nullable=true,updatable=true)
	private Integer buildingId;


	public void setId(Integer id){
		this.id = id;
	}

		return this.id;
	}

		this.newsId = newsId;
	}

		return this.newsId;
	}

		this.buildingId = buildingId;
	}

		return this.buildingId;
	}
