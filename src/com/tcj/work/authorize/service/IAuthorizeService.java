package com.tcj.work.authorize.service;

import com.tcj.common.EgladServiceException;
import com.tcj.domains.LoginEntity;


public interface IAuthorizeService {
	public LoginEntity getLoginUser() throws EgladServiceException;
	
}
