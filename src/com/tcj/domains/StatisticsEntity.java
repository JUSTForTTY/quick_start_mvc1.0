package com.tcj.domains;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class StatisticsEntity {
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivity_project_name() {
		return activity_project_name;
	}
	public void setActivity_project_name(String activity_project_name) {
		this.activity_project_name = activity_project_name;
	}
	public String getActMemberName() {
		return actMemberName;
	}
	public void setActMemberName(String actMemberName) {
		this.actMemberName = actMemberName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	String activityName;
	String activity_project_name;
	String actMemberName;
	String status;
	String mobile;
	String pay_type;
	String payment;
	String start_time;
	String end_time;
}
