package com.tcj.work.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.tcj.common.dao.model.Page;
import com.tcj.work.common.service.CommonMethodService;

@Controller("commonMethodAction")
@RemoteProxy(name = "commonMethodAction")
public class CommonMethodAction {
	@Autowired()
	@Qualifier("commonMethodService")
	private CommonMethodService commonMethodService;

	/***
	 * @author jiayy
	 * @param key
	 *            形式： OCCUPATION_ID,POST_ID,HOBBIES_ID
	 * @param return
	 *            返回值：Map<String, List>
	 */
	public Map<String, List> getCodeMaster(String key) {
		try {
			return commonMethodService.getCodeMaster(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * @author jiayy
	 * @param
	 * @param return
	 *            返回值：Map<String, List> 查询站点
	 */
	public Page getServiceCenter(Map map) {
		try {
			return commonMethodService.getServiceCenter(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/***
	 * @author jiayy
	 * @param
	 * @param return
	 *            返回值：Map<String, List> 查询站点 轮播图
	 */
	// public List<Map> getcenterCarousel(String serviceCenterId){
	// try {
	// return serviceCenterCarousel.getcenterCarousel(serviceCenterId);
	// } catch ( Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// public Page getMessageList(Map param){
	// try {
	// return messageService.getMessageList(param);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// public Integer getSumMessage(Map param){
	// try {
	// return messageService.getSumMessage(param);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }

	/**
	 * 查询公告
	 */
	// public List<Map> getCenterNotice(Map param){
	// try {
	// return serviceCenterNotice.getCenterNotice(param).getRows();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

}
