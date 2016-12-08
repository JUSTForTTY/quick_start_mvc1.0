package com.tcj.work.accountsManage.service;

import com.tcj.domains.User;

public interface UserloginService {
	
	public User checkUser(String username,String password);
	public void loginOut(User user);
}
