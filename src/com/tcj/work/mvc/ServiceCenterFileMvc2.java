package com.tcj.work.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tcj.common.util.FileUtil;
import com.tcj.work.NewsManage.service.NewsManageService;

@Controller()
@RequestMapping("/imageFile")
public class ServiceCenterFileMvc2 {

	@Autowired
	@Qualifier("newsManageServiceImpl")
	private NewsManageService newsService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		// 注册日期转换
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:dd"), true));
	}


	/**
	 * 上传站点相关文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody Map uploadCenterFile(HttpServletRequest request) {
		System.out.println("upload file..............");
		Map map = new HashMap();
		try {
			map=FileUtil.saveFile(request);
			String result = (String) map.get("result");
			if (result.equals("success")) {
				String nid = request.getParameter("nid");
//				LoginEntity entity = (LoginEntity) request.getSession()
//						.getAttribute("LoginEntity");
//				String loginId = entity.getUserId().toString();
//				String loginIp = entity.getIp();
				Map param = new HashMap();
//				param.put("loginId", loginId);
//				param.put("loginIp", loginIp);
				param.put("nid", nid);
				param.put("value", (String) map.get("filePath"));
				if (nid != null && !nid.equals(""))
					;
					//newsService.updateImage(param);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "文件上传失败！");
			map.put("result", "failure");
		}

		return map;
		
	}
	
	

}
