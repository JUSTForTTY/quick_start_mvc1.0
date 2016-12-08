package com.tcj.work.accountsManage.biz;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;

public interface AccountManageBiz {
	/**
	 * 查询
	 * @param param
	 * @return
	 */
	Page getList(Map<String, String> param);
	/**
	 * 冻结或解冻
	 * @param flag 0-冻结 1-解冻
	 * @param uids
	 * @return
	 */
	ResultBean delete(String flag,String uids);
	/**
	 * 根据uid查询
	 * @param uid
	 * @return
	 */
	ResultBean getByUid(String uid);
	
	ResultBean resetPwd(String uid);
	/**
	 * 更新
	 * @param map
	 * @return
	 */
	ResultBean update(Map<String, String> map,HttpSession session);
	/**
	 * 新增
	 * @param map
	 * @return
	 */
	ResultBean save(Map<String, String> map);
	
	
	Object getDetail(String uid);
	
	ResultBean updateDetail(String str,String passwd,String uid);

}
