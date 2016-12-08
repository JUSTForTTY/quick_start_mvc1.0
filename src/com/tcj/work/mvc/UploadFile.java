package com.tcj.work.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.tcj.common.util.FileUtil;
import com.tcj.work.NewsManage.service.NewsManageService;

@Controller()
@RequestMapping("/file")
public class UploadFile {

	@Autowired
	@Qualifier("newsManageServiceImpl")
	private NewsManageService newsService;

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
		//System.out.println("upload file ........." + request.getParameter("nid") + "-" + request.getParameter("path"));
		//System.out.println("miniture====="+request.getParameter("miniture"));
		//System.out.println("images====="+request.getParameter("images"));
		Map map = new HashMap();
		try {
			map = FileUtil.saveFile(request);
			String result = (String) map.get("result");
			if (result.equals("success")){
				String nid = request.getParameter("nid");
				String path = request.getParameter("path");
				String miniture = request.getParameter("miniture");
				String images = request.getParameter("images");
				//System.out.println("success"+miniture);
				//System.out.println("success"+images);
				Map param = new HashMap();

				param.put("nid", nid);
				param.put("image", (String) map.get("filePath"));
				if (nid != null && !nid.equals("")) {
					//System.out.println("保存数据");
					if (null != miniture && !("").equals(miniture)) {
						//System.out.println("保存图片miniture到数据库"+miniture);
						newsService.updateMiniature(param);
					} else {
						//System.out.println("保存图片image到数据库"+images);
						newsService.updateImage(param);
					}
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
