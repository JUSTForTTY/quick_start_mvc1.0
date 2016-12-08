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
@Table(name = "t_lease_house_info")
@DataTransferObject
public class T_Lease_House_Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "HOUSE_INFO_ID",length=10,updatable=true)
	private Integer houseInfoId;

	@RemoteProperty
	@Column(name = "BUILDING_NO",length=4,updatable=true)
	private String buildingNo;

	@RemoteProperty
	@Column(name = "BUILDING_ROOM",length=6,updatable=true)
	private String buildingRoom;

	@RemoteProperty
	@Column(name = "BUILDING_TYPE",length=4,updatable=true)
	private String buildingType;

	@RemoteProperty
	@Column(name = "BUILDING_UNIT",length=2,updatable=true)
	private String buildingUnit;

	@RemoteProperty
	@Column(name = "CHECK_OPINION",length=100,updatable=true)
	private String checkOpinion;

	@RemoteProperty
	@Column(name = "CHECK_RESULT",length=1,updatable=true)
	private String checkResult;

	@RemoteProperty
	@Column(name = "CHECK_TIME",length=0,updatable=true)
	private Date checkTime;

	@RemoteProperty
	@Column(name = "CHECK_USER_ID",length=10,updatable=true)
	private Integer checkUserId;

	@RemoteProperty
	@Column(name = "CQRDZWTS_PATH",length=100,updatable=true)
	private String cqrdzwtsPath;

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
	@Column(name = "DECORATION",length=4,updatable=true)
	private String decoration;

	@RemoteProperty
	@Column(name = "END_DATE",length=0,updatable=true)
	private Date endDate;

	@RemoteProperty
	@Column(name = "FLOOR_CNT",length=10,updatable=true)
	private Integer floorCnt;

	@RemoteProperty
	@Column(name = "FLOOR_NUM",length=10,updatable=true)
	private Integer floorNum;

	@RemoteProperty
	@Column(name = "HAVE_AC",length=1,updatable=true)
	private String haveAc;

	@RemoteProperty
	@Column(name = "HAVE_BED",length=1,updatable=true)
	private String haveBed;

	@RemoteProperty
	@Column(name = "HAVE_KITCHEN_BATH",length=1,updatable=true)
	private String haveKitchenBath;

	@RemoteProperty
	@Column(name = "HAVE_NETWORK",length=1,updatable=true)
	private String haveNetwork;

	@RemoteProperty
	@Column(name = "HAVE_REFRIGERATOR",length=1,updatable=true)
	private String haveRefrigerator;

	@RemoteProperty
	@Column(name = "HAVE_TV",length=1,updatable=true)
	private String haveTv;

	@RemoteProperty
	@Column(name = "HAVE_WASHING_MACHINE",length=1,updatable=true)
	private String haveWashingMachine;

	@RemoteProperty
	@Column(name = "HAVE_WATER_HEATER",length=1,updatable=true)
	private String haveWaterHeater;

	@RemoteProperty
	@Column(name = "HOUSE_ADD",length=200,updatable=true)
	private String houseAdd;

	@RemoteProperty
	@Column(name = "HOUSE_CODE",length=11,updatable=true)
	private String houseCode;

	@RemoteProperty
	@Column(name = "HOUSE_IMAGE_PATH1",length=100,updatable=true)
	private String houseImagePath1;

	@RemoteProperty
	@Column(name = "HOUSE_IMAGE_PATH2",length=100,updatable=true)
	private String houseImagePath2;

	@RemoteProperty
	@Column(name = "HOUSE_IMAGE_PATH3",length=100,updatable=true)
	private String houseImagePath3;

	@RemoteProperty
	@Column(name = "HOUSE_TYPE",length=4,updatable=true)
	private String houseType;

	@RemoteProperty
	@Column(name = "IS_DELETED",length=1,updatable=true)
	private String isDeleted;

	@RemoteProperty
	@Column(name = "LEASE_STATUS",length=1,updatable=true)
	private String leaseStatus;

	@RemoteProperty
	@Column(name = "LEASE_TYPE",length=4,updatable=true)
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
	@Column(name = "ORIENTATION",length=4,updatable=true)
	private String orientation;

	@RemoteProperty
	@Column(name = "P_O_ID_CARD_NO",length=30,updatable=true)
	private String pOIdCardNo;

	@RemoteProperty
	@Column(name = "P_O_PHONE",length=20,updatable=true)
	private String pOPhone;

	@RemoteProperty
	@Column(name = "PAYMENT_WAY",length=4,updatable=true)
	private String paymentWay;

	@RemoteProperty
	@Column(name = "PROCESS_STATUS",length=2,updatable=true)
	private String processStatus;

	@RemoteProperty
	@Column(name = "PROPERTY_OWNER",length=40,updatable=true)
	private String propertyOwner;

	@RemoteProperty
	@Column(name = "PROPORTION_M3",length=22,updatable=true)
	private String proportionM3;

	@RemoteProperty
	@Column(name = "PUBLISH_TIME",length=0,updatable=true)
	private Date publishTime;

	@RemoteProperty
	@Column(name = "PUBLISH_USER_ID",length=10,updatable=true)
	private Integer publishUserId;

	@RemoteProperty
	@Column(name = "RENT_INTENTION",length=22,updatable=true)
	private String rentIntention;

	@RemoteProperty
	@Column(name = "RENTAL_NUM",length=10,updatable=true)
	private Integer rentalNum;

	@RemoteProperty
	@Column(name = "SERVICE_CENTER_ID",length=10,updatable=true)
	private Integer serviceCenterId;

	@RemoteProperty
	@Column(name = "START_DATE",length=0,updatable=true)
	private Date startDate;

	@RemoteProperty
	@Column(name = "TENANT_USER_ID",length=10,updatable=true)
	private Integer tenantUserId;

	@RemoteProperty
	@Column(name = "TENANT_WAY",length=4,updatable=true)
	private String tenantWay;

	@RemoteProperty
	@Column(name = "USING_PROPERTIES",length=4,updatable=true)
	private String usingProperties;

	@RemoteProperty
	@Column(name = "VILLAGE_NAME",length=50,updatable=true)
	private String villageName;


	public void setHouseInfoId(Integer houseInfoId){
		this.houseInfoId = houseInfoId;
	}
	public Integer getHouseInfoId(){
		return this.houseInfoId;
	}
	public void setBuildingNo(String buildingNo){
		this.buildingNo = buildingNo;
	}
	public String getBuildingNo(){
		return this.buildingNo;
	}
	public void setBuildingRoom(String buildingRoom){
		this.buildingRoom = buildingRoom;
	}
	public String getBuildingRoom(){
		return this.buildingRoom;
	}
	public void setBuildingType(String buildingType){
		this.buildingType = buildingType;
	}
	public String getBuildingType(){
		return this.buildingType;
	}
	public void setBuildingUnit(String buildingUnit){
		this.buildingUnit = buildingUnit;
	}
	public String getBuildingUnit(){
		return this.buildingUnit;
	}
	public void setCheckOpinion(String checkOpinion){
		this.checkOpinion = checkOpinion;
	}
	public String getCheckOpinion(){
		return this.checkOpinion;
	}
	public void setCheckResult(String checkResult){
		this.checkResult = checkResult;
	}
	public String getCheckResult(){
		return this.checkResult;
	}
	public void setCheckTime(Date checkTime){
		this.checkTime = checkTime;
	}
	public Date getCheckTime(){
		return this.checkTime;
	}
	public void setCheckUserId(Integer checkUserId){
		this.checkUserId = checkUserId;
	}
	public Integer getCheckUserId(){
		return this.checkUserId;
	}
	public void setCqrdzwtsPath(String cqrdzwtsPath){
		this.cqrdzwtsPath = cqrdzwtsPath;
	}
	public String getCqrdzwtsPath(){
		return this.cqrdzwtsPath;
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
	public void setDecoration(String decoration){
		this.decoration = decoration;
	}
	public String getDecoration(){
		return this.decoration;
	}
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	public Date getEndDate(){
		return this.endDate;
	}
	public void setFloorCnt(Integer floorCnt){
		this.floorCnt = floorCnt;
	}
	public Integer getFloorCnt(){
		return this.floorCnt;
	}
	public void setFloorNum(Integer floorNum){
		this.floorNum = floorNum;
	}
	public Integer getFloorNum(){
		return this.floorNum;
	}
	public void setHaveAc(String haveAc){
		this.haveAc = haveAc;
	}
	public String getHaveAc(){
		return this.haveAc;
	}
	public void setHaveBed(String haveBed){
		this.haveBed = haveBed;
	}
	public String getHaveBed(){
		return this.haveBed;
	}
	public void setHaveKitchenBath(String haveKitchenBath){
		this.haveKitchenBath = haveKitchenBath;
	}
	public String getHaveKitchenBath(){
		return this.haveKitchenBath;
	}
	public void setHaveNetwork(String haveNetwork){
		this.haveNetwork = haveNetwork;
	}
	public String getHaveNetwork(){
		return this.haveNetwork;
	}
	public void setHaveRefrigerator(String haveRefrigerator){
		this.haveRefrigerator = haveRefrigerator;
	}
	public String getHaveRefrigerator(){
		return this.haveRefrigerator;
	}
	public void setHaveTv(String haveTv){
		this.haveTv = haveTv;
	}
	public String getHaveTv(){
		return this.haveTv;
	}
	public void setHaveWashingMachine(String haveWashingMachine){
		this.haveWashingMachine = haveWashingMachine;
	}
	public String getHaveWashingMachine(){
		return this.haveWashingMachine;
	}
	public void setHaveWaterHeater(String haveWaterHeater){
		this.haveWaterHeater = haveWaterHeater;
	}
	public String getHaveWaterHeater(){
		return this.haveWaterHeater;
	}
	public void setHouseAdd(String houseAdd){
		this.houseAdd = houseAdd;
	}
	public String getHouseAdd(){
		return this.houseAdd;
	}
	public void setHouseCode(String houseCode){
		this.houseCode = houseCode;
	}
	public String getHouseCode(){
		return this.houseCode;
	}
	public void setHouseImagePath1(String houseImagePath1){
		this.houseImagePath1 = houseImagePath1;
	}
	public String getHouseImagePath1(){
		return this.houseImagePath1;
	}
	public void setHouseImagePath2(String houseImagePath2){
		this.houseImagePath2 = houseImagePath2;
	}
	public String getHouseImagePath2(){
		return this.houseImagePath2;
	}
	public void setHouseImagePath3(String houseImagePath3){
		this.houseImagePath3 = houseImagePath3;
	}
	public String getHouseImagePath3(){
		return this.houseImagePath3;
	}
	public void setHouseType(String houseType){
		this.houseType = houseType;
	}
	public String getHouseType(){
		return this.houseType;
	}
	public void setIsDeleted(String isDeleted){
		this.isDeleted = isDeleted;
	}
	public String getIsDeleted(){
		return this.isDeleted;
	}
	public void setLeaseStatus(String leaseStatus){
		this.leaseStatus = leaseStatus;
	}
	public String getLeaseStatus(){
		return this.leaseStatus;
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
	public void setOrientation(String orientation){
		this.orientation = orientation;
	}
	public String getOrientation(){
		return this.orientation;
	}
	public void setPOIdCardNo(String pOIdCardNo){
		this.pOIdCardNo = pOIdCardNo;
	}
	public String getPOIdCardNo(){
		return this.pOIdCardNo;
	}
	public void setPOPhone(String pOPhone){
		this.pOPhone = pOPhone;
	}
	public String getPOPhone(){
		return this.pOPhone;
	}
	public void setPaymentWay(String paymentWay){
		this.paymentWay = paymentWay;
	}
	public String getPaymentWay(){
		return this.paymentWay;
	}
	public void setProcessStatus(String processStatus){
		this.processStatus = processStatus;
	}
	public String getProcessStatus(){
		return this.processStatus;
	}
	public void setPropertyOwner(String propertyOwner){
		this.propertyOwner = propertyOwner;
	}
	public String getPropertyOwner(){
		return this.propertyOwner;
	}
	public void setProportionM3(String proportionM3){
		this.proportionM3 = proportionM3;
	}
	public String getProportionM3(){
		return this.proportionM3;
	}
	public void setPublishTime(Date publishTime){
		this.publishTime = publishTime;
	}
	public Date getPublishTime(){
		return this.publishTime;
	}
	public void setPublishUserId(Integer publishUserId){
		this.publishUserId = publishUserId;
	}
	public Integer getPublishUserId(){
		return this.publishUserId;
	}
	public void setRentIntention(String rentIntention){
		this.rentIntention = rentIntention;
	}
	public String getRentIntention(){
		return this.rentIntention;
	}
	public void setRentalNum(Integer rentalNum){
		this.rentalNum = rentalNum;
	}
	public Integer getRentalNum(){
		return this.rentalNum;
	}
	public void setServiceCenterId(Integer serviceCenterId){
		this.serviceCenterId = serviceCenterId;
	}
	public Integer getServiceCenterId(){
		return this.serviceCenterId;
	}
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	public Date getStartDate(){
		return this.startDate;
	}
	public void setTenantUserId(Integer tenantUserId){
		this.tenantUserId = tenantUserId;
	}
	public Integer getTenantUserId(){
		return this.tenantUserId;
	}
	public void setTenantWay(String tenantWay){
		this.tenantWay = tenantWay;
	}
	public String getTenantWay(){
		return this.tenantWay;
	}
	public void setUsingProperties(String usingProperties){
		this.usingProperties = usingProperties;
	}
	public String getUsingProperties(){
		return this.usingProperties;
	}
	public void setVillageName(String villageName){
		this.villageName = villageName;
	}
	public String getVillageName(){
		return this.villageName;
	}
}