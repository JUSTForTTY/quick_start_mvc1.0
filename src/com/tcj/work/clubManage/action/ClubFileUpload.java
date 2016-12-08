package com.tcj.work.clubManage.action;

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

import com.tcj.work.activityManage.action.FileUtil;

import com.tcj.work.clubManage.service.ClubManageService;

@Controller()
@RequestMapping("/ClubfileImage")
public class ClubFileUpload {

	@Autowired
	@Qualifier("clubManageServiceImpl")
	private ClubManageService clubManageService;

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
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public @ResponseBody Map uploadCenterFile(HttpServletRequest request) {
		System.out.println("upload file ........." + request.getParameter("cid") + "-" + request.getParameter("path"));

		Map map = new HashMap();
		try {
			map = FileUtil.saveFile(request);
			String result = (String) map.get("result");
			if (result.equals("success")) {
				String cid = request.getParameter("cid");
				String path = request.getParameter("path");
				Map param = new HashMap();
				param.put("cid", cid);
				param.put("image", (String) map.get("filePath"));
				if (cid != null && !cid.equals("")) {			
						clubManageService.updateImage(param);
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
