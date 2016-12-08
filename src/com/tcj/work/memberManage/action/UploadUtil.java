package com.tcj.work.memberManage.action;



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
import com.tcj.work.memberManage.service.MemberManageService;
import com.tcj.work.news.service.NewsService;


@Controller
@RequestMapping("/imageupload")
public class UploadUtil {

	@Autowired
	@Qualifier("memberManageServiceImpl")
	private MemberManageService memberManageService;

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
	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
	public @ResponseBody Map uploadCenterFile(HttpServletRequest request) {
		//System.out.println("upload file ........." + request.getParameter("nid") + "-" + request.getParameter("path"));
		System.out.println("miniture====="+request.getParameter("miniture"));
		//System.out.println("images====="+request.getParameter("images"));
		System.out.println("upload file ....===....." + request.getParameter("id"));
		Map map = new HashMap();
		try {
			map = UploadImage.SaveFile(request);
			String result = (String) map.get("result");
			if (result.equals("success")){
				String id = request.getParameter("id");
				System.out.println("news页面的id===="+id);
				String path = request.getParameter("path");
				String miniture = request.getParameter("miniture");
				String images = request.getParameter("images");
				System.out.println("success"+miniture);
				System.out.println("success这个是什么"+images);
				Map param = new HashMap();
				param.put("id", id);
				param.put("miniature", (String) map.get("filePath"));
				if (id != null && !id.equals("")) {
					//System.out.println("保存数据");
				//	if (null != miniture && !("").equals(miniture)) {
						//System.out.println("保存图片miniture到数据库"+miniture);
					//	newsService.updateMiniature(param);
				//	} 
			//	else {
						//System.out.println("保存图片image到数据库"+images);
					memberManageService.updateLoadImage(param);
					}
			//	}
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
