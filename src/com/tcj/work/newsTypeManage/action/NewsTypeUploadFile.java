package com.tcj.work.newsTypeManage.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcj.work.newsTypeManage.service.NewsTypeService;

@Controller()
@RequestMapping("/newsTypeFile")
public class NewsTypeUploadFile {

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("newsTypeServiceImpl")
	private NewsTypeService newsTypeService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		// 注册日期转换
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:dd"), true));
	}

	/**
	 * 上传站点相关文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Map uploadCenterFile(HttpServletRequest request) {
		log.debug("upload file ........." + request.getParameter("id") + "-" + request.getParameter("path"));

		Map map = new HashMap();
		try {
			map = FileUtil.saveFile(request);
			String result = (String) map.get("result");
			if (result.equals("success")) {
				String id = request.getParameter("id");
				String path = request.getParameter("path");
				// LoginEntity entity = (LoginEntity) request.getSession()
				// .getAttribute("LoginEntity");
				// String loginId = entity.getUserId().toString();
				// String loginIp = entity.getIp();
				Map param = new HashMap();
				// param.put("loginId", loginId);
				// param.put("loginIp", loginIp);
				param.put("nid", id);
				param.put("image", (String) map.get("filePath"));
				if (id != null && !"".equals(id)) {
//						newsService.updateImage(param);
				}
				if (null != path && !("").equals(path)) {
					FileUtil.deleteFile(path, request);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "文件上传失败！");
			map.put("result", "failure");
		}

		return map;

	}

}
