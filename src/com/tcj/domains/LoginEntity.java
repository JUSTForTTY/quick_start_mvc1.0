package com.tcj.domains;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class LoginEntity {
	
	
	private String  userId;
	
	private String logInId;
	
	private String ip;
	
	private String userName;

	private String userTypeFlag;
	
	private String sessionFlag;
	
	private String serviceCenterId;
	
	private String idCardNo;
	
	private String cellTel;
	
	private String path;
	
	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getCellTel() {
		return cellTel;
	}

	public void setCellTel(String cellTel) {
		this.cellTel = cellTel;
	}

	public String getSessionFlag() {
		return sessionFlag;
	}

	public void setSessionFlag(String sessionFlag) {
		this.sessionFlag = sessionFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogInId() {
		return logInId;
	}

	public void setLogInId(String logInId) {
		this.logInId = logInId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTypeFlag() {
		return userTypeFlag;
	}

	public void setUserTypeFlag(String userTypeFlag) {
		this.userTypeFlag = userTypeFlag;
	}

	public String getServiceCenterId() {
		return serviceCenterId;
	}

	public void setServiceCenterId(String serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "LoginEntity [userId=" + userId + ", logInId=" + logInId + ", ip=" + ip + ", userName=" + userName
				+ ", userTypeFlag=" + userTypeFlag + ", sessionFlag=" + sessionFlag + ", serviceCenterId="
				+ serviceCenterId + ", idCardNo=" + idCardNo + ", cellTel=" + cellTel + ", path=" + path + "]";
	}
	
	
}
