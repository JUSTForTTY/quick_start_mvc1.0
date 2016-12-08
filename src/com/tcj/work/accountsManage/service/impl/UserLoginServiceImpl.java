package com.tcj.work.accountsManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tcj.common.dao.SplitPageDao;
import com.tcj.common.util.CommonDefine;
import com.tcj.domains.User;
import com.tcj.work.accountsManage.service.UserloginService;

@Component("userLoginServiceImpl")
public class UserLoginServiceImpl implements UserloginService {
	@Autowired
	@Qualifier("splitPageDao")
	private SplitPageDao splitPageDao;
	@Override
	public void loginOut(User user) {
	}

	@Override
	public User checkUser(String username, String password) {
		String hql = "from User where username =? and passwd =? and usertype <="+'3' +"and status='1'";
		ArrayList<String> al = new ArrayList<String>();
		al.add(username);
		al.add(password);
		Object[] objs = al.toArray();
		List<User> list = splitPageDao.query(hql, objs);
		if(null!=list&&list.size()>0)
			return list.get(0);
		else
			return null;
	}

}
