package com.tcj.work.newsTypeManage.biz.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.util.CommonDefine;
import com.tcj.common.util.DateUtil;
import com.tcj.domains.*;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.newsTypeManage.biz.NewsTypeBiz;
import com.tcj.work.newsTypeManage.service.NewsTypeService;
import com.tcj.work.newsTypeManage.service.impl.NewsTypeServiceImpl;

@Component("newsTypeBizImpl")
public class NewsTypeBizImpl implements NewsTypeBiz {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("newsTypeServiceImpl")
	private NewsTypeService newsTypeService;

	@Autowired()
	@Qualifier("newsTypeServiceImpl")
	private NewsTypeServiceImpl newsTypeServiceImpl;

	/*
	 * 
	 * 查询(条件查询)
	 */
	@RemoteMethod
	public Page getList(Map<String, String> param) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("load page!");
			}
			return newsTypeService.getList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 
	 * 删除
	 */
	@RemoteMethod
	public ResultBean delete(Map<String, String> param) {
		ResultBean resultBean = new ResultBean();
		try {			
			newsTypeService.delete(param);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}

	/*
	 * 
	 * 根据id查询
	 */
	@RemoteMethod
	public Object getNewsTypeById(String id) {
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setSuccess(true);
			resultBean.setData(this.newsTypeService.getNewsTypeById(id));
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			resultBean.setMsg(e.getMessage());
		}
		log.debug("查看详情对象："+resultBean.getData());
		return resultBean.getData();
	}

	/*
	 * 
	 * 新增
	 */
	@RemoteMethod
	public ResultBean saveNewsType(Map<String, String> map) {
		ResultBean resultBean = new ResultBean();
		
		WebContext contxt = WebContextFactory.get();
		HttpSession session = contxt.getSession();
		LoginEntity loginEntity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		String user = loginEntity.getUserName();//创建或修改人
		log.debug("保存更新人："+user);
		
		try {
			Category category = new Category();
			String id = MapUtils.getString(map, "id", "");
			if (id != null && !"".equals(id)) {//修改
				log.debug("修改id:"+id);
				category.setCaid(id);
				category.setCreateUser(MapUtils.getString(map, "create_user", ""));//新增人
				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date time=sim.parse(MapUtils.getString(map, "create_time", ""));
				category.setCreateTime(time);//新增时间
				category.setModifyUser(user);//修改人
				category.setModifyTime(DateUtil.newDate());//修改时间
			} else {
				log.debug("新增");
				category.setCreateUser(user);//新增人
				category.setCreateTime(DateUtil.newDate());//新增时间
			}
			
			category.setBid(CommonDefine.JK_BUILDING);//楼宇编号默认为10
			category.setName(MapUtils.getString(map, "name", ""));
			category.setIcon(MapUtils.getString(map, "image",""));//图片
			log.debug("输出图片值："+category.getIcon());
			category.setCodeno(MapUtils.getString(map, "type", ""));
			String parentName = MapUtils.getString(map, "parentName", "");
			if(parentName!=null&&!"".equals(parentName)) category.setParentId(parentName);//如果父类别不为空则写入，否则本身为父类别
			else category.setParentId("-1");
			category.setDescription(MapUtils.getString(map, "description", ""));
			category.setOnhome(MapUtils.getInteger(map, "onHome"));
			log.debug("首页是否显示："+category.getOnhome());
			
			if(MapUtils.getString(map, "is_delete", "").equals("0")){
				//CommonDefine类中的 ID_DELETE_FALSE指未被删除，需转换成String类型
				category.setIsDelete(String.valueOf(CommonDefine.ID_DELETE_FALSE));
			}else{
				//CommonDefine类中的 ID_DELETE_TRUE指已被删除，需转换成String类型
				category.setIsDelete(String.valueOf(CommonDefine.ID_DELETE_TRUE));
			}
			this.newsTypeService.saveNewsType(category);
			resultBean.setData(category);
			resultBean.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setSuccess(false);
			System.out.println(e.getMessage());
			resultBean.setMsg(e.getMessage());
		}
		return resultBean;
	}


	@Override
	public List getCategoryType(Map param) {
		// TODO Auto-generated method stub
		return newsTypeService.getCategoryType(param);
	}

	@Override
	public List getParentName(Map param) {
		// TODO Auto-generated method stub
		return newsTypeService.getParentName(param);
	}
}
