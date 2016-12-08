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
	private static final long serialVersionUID = 1L;

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
	public Integer getIntentionAppointId(){
		return this.intentionAppointId;
	}
	public void setAskUserId(Integer askUserId){
		this.askUserId = askUserId;
	}
	public Integer getAskUserId(){
		return this.askUserId;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateType(String createType){
		this.createType = createType;
	}
	public String getCreateType(){
		return this.createType;
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
	public void setHouseCode(String houseCode){
		this.houseCode = houseCode;
	}
	public String getHouseCode(){
		return this.houseCode;
	}
	public void setHouseInfoId(Integer houseInfoId){
		this.houseInfoId = houseInfoId;
	}
	public Integer getHouseInfoId(){
		return this.houseInfoId;
	}
	public void setInformStatus(String informStatus){
		this.informStatus = informStatus;
	}
	public String getInformStatus(){
		return this.informStatus;
	}
	public void setIntentionAppointCd(String intentionAppointCd){
		this.intentionAppointCd = intentionAppointCd;
	}
	public String getIntentionAppointCd(){
		return this.intentionAppointCd;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setLeaseIsDeal(String leaseIsDeal){
		this.leaseIsDeal = leaseIsDeal;
	}
	public String getLeaseIsDeal(){
		return this.leaseIsDeal;
	}
	public void setLeaseType(String leaseType){
		this.leaseType = leaseType;
	}
	public String getLeaseType(){
		return this.leaseType;
	}
	public void setLeaserUserId(Integer leaserUserId){
		this.leaserUserId = leaserUserId;
	}
	public Integer getLeaserUserId(){
		return this.leaserUserId;
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
	public void setSRRTime(Date sRRTime){
		this.sRRTime = sRRTime;
	}
	public Date getSRRTime(){
		return this.sRRTime;
	}
	public void setSRRecorderUserId(Integer sRRecorderUserId){
		this.sRRecorderUserId = sRRecorderUserId;
	}
	public Integer getSRRecorderUserId(){
		return this.sRRecorderUserId;
	}
	public void setSatisfactionResultMemo(String satisfactionResultMemo){
		this.satisfactionResultMemo = satisfactionResultMemo;
	}
	public String getSatisfactionResultMemo(){
		return this.satisfactionResultMemo;
	}
	public void setSourceUserId(Integer sourceUserId){
		this.sourceUserId = sourceUserId;
	}
	public Integer getSourceUserId(){
		return this.sourceUserId;
	}
	public void setSourceUserType(String sourceUserType){
		this.sourceUserType = sourceUserType;
	}
	public String getSourceUserType(){
		return this.sourceUserType;
	}
	public void setVerifyTimeAppoint(String verifyTimeAppoint){
		this.verifyTimeAppoint = verifyTimeAppoint;
	}
	public String getVerifyTimeAppoint(){
		return this.verifyTimeAppoint;
	}
	public void setVerifyTimePractical(String verifyTimePractical){
		this.verifyTimePractical = verifyTimePractical;
	}
	public String getVerifyTimePractical(){
		return this.verifyTimePractical;
	}
	public void setVerifyTimeWant(String verifyTimeWant){
		this.verifyTimeWant = verifyTimeWant;
	}
	public String getVerifyTimeWant(){
		return this.verifyTimeWant;
	}
}