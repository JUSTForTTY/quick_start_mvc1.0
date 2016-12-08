package com.tcj.common.dao.model;

import java.util.List;
import java.util.Map;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class RunningAccount {
	private String nowPage;
	private String pageSize;
	private List<Map> list;
	private Map<String,String> paramMap;
	public String getNowPage() {
		return nowPage;
	}
	
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<Map> getList() {
		return list;
	}

	public void setList(List<Map> list) {
		this.list = list;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
}
