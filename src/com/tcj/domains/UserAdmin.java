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
@Table(name = "marine_user")
@DataTransferObject
public class UserAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=10,updatable=false)
	private Integer id;

	@RemoteProperty
	@Column(name = "status",length=3,updatable=false)
	private Integer status;

	@RemoteProperty
	@Column(name = "ships_id",length=255,nullable=true,updatable=false)
	private String shipsId;

	@RemoteProperty
	@Column(name = "username",length=50,updatable=false)
	private String username;

	@RemoteProperty
	@Column(name = "mobile",length=96,nullable=true,updatable=false)
	private String mobile;

	@RemoteProperty
	@Column(name = "birthday",length=0,nullable=true,updatable=false)
	private Date birthday;

	@RemoteProperty
	@Column(name = "userType",length=3,updatable=false)
	private Integer usertype;

	@RemoteProperty
	@Column(name = "job",length=3,nullable=true,updatable=false)
	private Integer job;

	@RemoteProperty
	@Column(name = "eligibility_number",length=255,nullable=true,updatable=false)
	private String eligibilityNumber;

	@RemoteProperty
	@Column(name = "eligibility_valid",length=0,nullable=true,updatable=false)
	private Date eligibilityValid;

	@RemoteProperty
	@Column(name = "Train_valid",length=0,nullable=true,updatable=false)
	private Date trainValid;

	@RemoteProperty
	@Column(name = "passwd",length=50,updatable=false)
	private String passwd;

	@RemoteProperty
	@Column(name = "gender",length=3,nullable=true,updatable=false)
	private String gender;

	@RemoteProperty
	@Column(name = "fax",length=50,nullable=true,updatable=false)
	private String fax;

	@RemoteProperty
	@Column(name = "qq",length=50,nullable=true,updatable=false)
	private String qq;

	@RemoteProperty
	@Column(name = "address",length=255,nullable=true,updatable=false)
	private String address;

	@RemoteProperty
	@Column(name = "prepay",length=8,updatable=false)
	private String prepay;

	@RemoteProperty
	@Column(name = "createtime",length=0,nullable=true,updatable=false)
	private Date createtime;

	@RemoteProperty
	@Column(name = "cardId",length=45,nullable=true,updatable=false)
	private String cardid;

	@RemoteProperty
	@Column(name = "openId",length=255,nullable=true,updatable=false)
	private String openid;

	@RemoteProperty
	@Column(name = "emergency_number",length=255,nullable=true,updatable=false)
	private String emergencyNumber;

	@RemoteProperty
	@Column(name = "createUser",length=255,nullable=true,updatable=false)
	private String createuser;

	@RemoteProperty
	@Column(name = "updateUser",length=255,nullable=true,updatable=false)
	private String updateuser;

	@RemoteProperty
	@Column(name = "email",length=255,nullable=true,updatable=false)
	private String email;

	@RemoteProperty
	@Column(name = "member_id",length=255,nullable=true,updatable=false)
	private String memberId;
	
	@RemoteProperty
	@Column(name = "marineId",length=255,nullable=true,updatable=false)
	private String marineId;

	public String getMarineId() {
		return marineId;
	}

	public void setMarineId(String marineId) {
		this.marineId = marineId;
	}

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setShipsId(String shipsId){
		this.shipsId = shipsId;
	}
	public String getShipsId(){
		return this.shipsId;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public Date getBirthday(){
		return this.birthday;
	}
	public void setUsertype(Integer usertype){
		this.usertype = usertype;
	}
	public Integer getUsertype(){
		return this.usertype;
	}
	public void setJob(Integer job){
		this.job = job;
	}
	public Integer getJob(){
		return this.job;
	}
	public void setEligibilityNumber(String eligibilityNumber){
		this.eligibilityNumber = eligibilityNumber;
	}
	public String getEligibilityNumber(){
		return this.eligibilityNumber;
	}
	public void setEligibilityValid(Date eligibilityValid){
		this.eligibilityValid = eligibilityValid;
	}
	public Date getEligibilityValid(){
		return this.eligibilityValid;
	}
	public void setTrainValid(Date trainValid){
		this.trainValid = trainValid;
	}
	public Date getTrainValid(){
		return this.trainValid;
	}
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	public String getPasswd(){
		return this.passwd;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public String getGender(){
		return this.gender;
	}
	public void setFax(String fax){
		this.fax = fax;
	}
	public String getFax(){
		return this.fax;
	}
	public void setQq(String qq){
		this.qq = qq;
	}
	public String getQq(){
		return this.qq;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setPrepay(String prepay){
		this.prepay = prepay;
	}
	public String getPrepay(){
		return this.prepay;
	}
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}
	public Date getCreatetime(){
		return this.createtime;
	}
	public void setCardid(String cardid){
		this.cardid = cardid;
	}
	public String getCardid(){
		return this.cardid;
	}
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public String getOpenid(){
		return this.openid;
	}
	public void setEmergencyNumber(String emergencyNumber){
		this.emergencyNumber = emergencyNumber;
	}
	public String getEmergencyNumber(){
		return this.emergencyNumber;
	}
	public void setCreateuser(String createuser){
		this.createuser = createuser;
	}
	public String getCreateuser(){
		return this.createuser;
	}
	public void setUpdateuser(String updateuser){
		this.updateuser = updateuser;
	}
	public String getUpdateuser(){
		return this.updateuser;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	public String getMemberId(){
		return this.memberId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", shipsId=" + shipsId
				+ ", username=" + username + ", mobile=" + mobile
				+ ", birthday=" + birthday + ", usertype=" + usertype
				+ ", job=" + job + ", eligibilityNumber=" + eligibilityNumber
				+ ", eligibilityValid=" + eligibilityValid + ", trainValid="
				+ trainValid + ", passwd=" + passwd + ", gender=" + gender
				+ ", fax=" + fax + ", qq=" + qq + ", address=" + address
				+ ", prepay=" + prepay + ", createtime=" + createtime
				+ ", cardid=" + cardid + ", openid=" + openid
				+ ", emergencyNumber=" + emergencyNumber + ", createuser="
				+ createuser + ", updateuser=" + updateuser + ", email="
				+ email + ", memberId=" + memberId + ", marineId=" + marineId
				+ "]";
	}
	
	
}