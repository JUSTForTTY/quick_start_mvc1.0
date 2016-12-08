package com.tcj.work.commentManage.biz;

import java.util.List;
import java.util.Map;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;


/*DemoBiz接口.
 * 
 * @author tty 1547970885@qq.com
 * @date 2016-7-21 下午1:20:15
 * @version 1.0
 * 
 */
public interface CommentManageBiz {

	public List getList(Map<String, String> param);
	public List getActivityName(Map<String, String> param);
	public Page getBestList(Map<String, String> param);

	public ResultBean update(Map<String, String> param);
	public ResultBean delete(Map<String, String> param);
	public List countList(Map<String, String> param);
	public List save(Map<String, String> param);
	
}
