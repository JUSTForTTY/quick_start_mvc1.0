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
	private static final long serialVersionUID = 1L;

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
	public Integer getServiceCenterId(){
		return this.serviceCenterId;
	}
	public void setAccompanyUserId(String accompanyUserId){
		this.accompanyUserId = accompanyUserId;
	}
	public String getAccompanyUserId(){
		return this.accompanyUserId;
	}
	public void setAqxfxyTpPath(String aqxfxyTpPath){
		this.aqxfxyTpPath = aqxfxyTpPath;
	}
	public String getAqxfxyTpPath(){
		return this.aqxfxyTpPath;
	}
	public void setBlcrktzdTpPath(String blcrktzdTpPath){
		this.blcrktzdTpPath = blcrktzdTpPath;
	}
	public String getBlcrktzdTpPath(){
		return this.blcrktzdTpPath;
	}
	public void setCheckUserId(Integer checkUserId){
		this.checkUserId = checkUserId;
	}
	public Integer getCheckUserId(){
		return this.checkUserId;
	}
	public void setCqrdzwtsTpPath(String cqrdzwtsTpPath){
		this.cqrdzwtsTpPath = cqrdzwtsTpPath;
	}
	public String getCqrdzwtsTpPath(){
		return this.cqrdzwtsTpPath;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}
	public String getCreator(){
		return this.creator;
	}
	public void setCreatorIp(String creatorIp){
		this.creatorIp = creatorIp;
	}
	public String getCreatorIp(){
		return this.creatorIp;
	}
	public void setFwzlhtTpPath(String fwzlhtTpPath){
		this.fwzlhtTpPath = fwzlhtTpPath;
	}
	public String getFwzlhtTpPath(){
		return this.fwzlhtTpPath;
	}
	public void setFwzlwtsTpPath(String fwzlwtsTpPath){
		this.fwzlwtsTpPath = fwzlwtsTpPath;
	}
	public String getFwzlwtsTpPath(){
		return this.fwzlwtsTpPath;
	}
	public void setIntentionAppointUserId(Integer intentionAppointUserId){
		this.intentionAppointUserId = intentionAppointUserId;
	}
	public Integer getIntentionAppointUserId(){
		return this.intentionAppointUserId;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setJzzblsqdTpPath(String jzzblsqdTpPath){
		this.jzzblsqdTpPath = jzzblsqdTpPath;
	}
	public String getJzzblsqdTpPath(){
		return this.jzzblsqdTpPath;
	}
	public void setLeaseTransactionUserId(String leaseTransactionUserId){
		this.leaseTransactionUserId = leaseTransactionUserId;
	}
	public String getLeaseTransactionUserId(){
		return this.leaseTransactionUserId;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	public String getMemo(){
		return this.memo;
	}
	public void setModifier(String modifier){
		this.modifier = modifier;
	}
	public String getModifier(){
		return this.modifier;
	}
	public void setModifierIp(String modifierIp){
		this.modifierIp = modifierIp;
	}
	public String getModifierIp(){
		return this.modifierIp;
	}
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	public Date getModifyTime(){
		return this.modifyTime;
	}
	public void setPublishUserId(Integer publishUserId){
		this.publishUserId = publishUserId;
	}
	public Integer getPublishUserId(){
		return this.publishUserId;
	}
	public void setServiceCenterAdd(String serviceCenterAdd){
		this.serviceCenterAdd = serviceCenterAdd;
	}
	public String getServiceCenterAdd(){
		return this.serviceCenterAdd;
	}
	public void setServiceCenterName(String serviceCenterName){
		this.serviceCenterName = serviceCenterName;
	}
	public String getServiceCenterName(){
		return this.serviceCenterName;
	}
	public void setServiceCenterTel(String serviceCenterTel){
		this.serviceCenterTel = serviceCenterTel;
	}
	public String getServiceCenterTel(){
		return this.serviceCenterTel;
	}
	public void setServicePhotoPath(String servicePhotoPath){
		this.servicePhotoPath = servicePhotoPath;
	}
	public String getServicePhotoPath(){
		return this.servicePhotoPath;
	}
	public void setZazrbzsTpPath(String zazrbzsTpPath){
		this.zazrbzsTpPath = zazrbzsTpPath;
	}
	public String getZazrbzsTpPath(){
		return this.zazrbzsTpPath;
	}
}