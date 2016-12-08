package com.tcj.work.common.service;

import java.util.List;
import java.util.Map;

import com.tcj.common.dao.model.Page;



public interface CommonMethodService {
	public Map<String, List> getCodeMaster(String key) throws Exception;
	
	public Page getServiceCenter(Map map) throws Exception;
}
