package com.tcj.common.dao.model;

import java.util.List;

import org.directwebremoting.annotations.DataTransferObject;
@DataTransferObject
public class SubjectPage<T> {
	
	protected Long total;
	
	
	private double sumDebits;//借方总额
	private double sumCredits;//贷方总额
	private double sumOpeningBalance;//总期初余额
	private double sumLastDebits;//去年同期借方总额
	private double sumLastCredits;//去年同期贷方总额
	private double sumLastOpeningBalance;//去年同期总期初余额
	protected List<T> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public double getSumDebits() {
		return sumDebits;
	}
	public void setSumDebits(double sumDebits) {
		this.sumDebits = sumDebits;
	}
	public double getSumCredits() {
		return sumCredits;
	}
	public void setSumCredits(double sumCredits) {
		this.sumCredits = sumCredits;
	}
	public double getSumOpeningBalance() {
		return sumOpeningBalance;
	}
	public void setSumOpeningBalance(double sumOpeningBalance) {
		this.sumOpeningBalance = sumOpeningBalance;
	}
	public double getSumLastDebits() {
		return sumLastDebits;
	}
	public void setSumLastDebits(double sumLastDebits) {
		this.sumLastDebits = sumLastDebits;
	}
	public double getSumLastCredits() {
		return sumLastCredits;
	}
	public void setSumLastCredits(double sumLastCredits) {
		this.sumLastCredits = sumLastCredits;
	}
	public double getSumLastOpeningBalance() {
		return sumLastOpeningBalance;
	}
	public void setSumLastOpeningBalance(double sumLastOpeningBalance) {
		this.sumLastOpeningBalance = sumLastOpeningBalance;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
