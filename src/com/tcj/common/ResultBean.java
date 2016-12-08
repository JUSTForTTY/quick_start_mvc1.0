package com.tcj.common;

import java.io.Serializable;

import org.directwebremoting.annotations.DataTransferObject;
/**
 * 前台调用dwr的共通返回bean
 * @author rebort
 *
 */
@DataTransferObject
public class ResultBean implements Serializable{
	public ResultBean() {
		success = true;
		msg = "";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 前台调用是否成功
	 */
	private boolean success;
	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 返回数据
	 */
	private Object data;
	private String registerStart;
	private String registerEnd;
	private String startTime;
	private String endTime;
	
	private String activityStartTime;
	private String activityEndTime;
	private String couponStartTime;
	private String couponEndTime;
	
	
	
	
	public String getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public String getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public String getCouponStartTime() {
		return couponStartTime;
	}
	public void setCouponStartTime(String couponStartTime) {
		this.couponStartTime = couponStartTime;
	}
	public String getCouponEndTime() {
		return couponEndTime;
	}
	public void setCouponEndTime(String couponEndTime) {
		this.couponEndTime = couponEndTime;
	}
	public String getRegisterStart() {
		return registerStart;
	}
	public void setRegisterStart(String registerStart) {
		this.registerStart = registerStart;
	}
	public String getRegisterEnd() {
		return registerEnd;
	}
	public void setRegisterEnd(String registerEnd) {
		this.registerEnd = registerEnd;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}	
}
