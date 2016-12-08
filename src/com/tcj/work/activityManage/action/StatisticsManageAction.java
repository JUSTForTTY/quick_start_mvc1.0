package com.tcj.work.activityManage.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.star.bridge.oleautomation.Date;
import com.tcj.common.dao.model.Page;
import com.tcj.domains.StatisticsEntity;
import com.tcj.work.activityManage.biz.StatisticsManageBiz;



/**
 * @date 2016.8.7
 * @author panyf
 * @version 1.0
 * @dscription 活动统计
 */
@Controller("statisticsManageAction")
@RemoteProxy(name = "statisticsManageAction")
public class StatisticsManageAction {
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("statisticsManageBizImpl")
	private StatisticsManageBiz statisticsManageBiz;
	
	
	/*
	 * 
	 * 查询(条件查询)
	 * 按课程查询
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		return statisticsManageBiz.getList(param);
	}
	
	
	//导出excel
		@ResponseBody
		@RemoteMethod
		public List<StatisticsEntity> exportExcel(Map<String, String> params,HttpServletRequest request){
			System.out.println("asdasd");	
		
			List<StatisticsEntity> list =statisticsManageBiz.exportExcel(params);
			/*if(list!=null){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getActivity_project_name()==null)list.get(i).setActivity_project_name(" ");
					if(list.get(i).getActivityName()==null)list.get(i).setActivityName(" ");
					if(list.get(i).getActMemberName()==null)list.get(i).setActMemberName(" ");
					if(list.get(i).getEnd_time()==null)list.get(i).setEnd_time(" ");
					if(list.get(i).getStatus()==null)list.get(i).setStatus("未付款");
					if(list.get(i).getPay_type()==null)list.get(i).setPay_type("支付宝支付");
					if(list.get(i).getPayment()==null)list.get(i).setPayment(" ");
					if(list.get(i).getStart_time()==null)list.get(i).setStart_time(" ");

				}
			}*/
			
				request.getSession().setAttribute("list", list);
				
			return list;
		}
}
