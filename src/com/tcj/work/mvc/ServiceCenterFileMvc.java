package com.tcj.work.mvc;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

@Controller()
@RequestMapping("/centerFile")
public class ServiceCenterFileMvc {
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
	@RequestMapping(value = "/uploadCenterFile", method = RequestMethod.POST)
	public @ResponseBody Map uploadCenterFile(HttpServletRequest request) {
		System.out.println("hello");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("Filename");
		System.out.println(fileList.size());
		fileList = multipartRequest.getFiles("fileData");
		System.out.println(fileList.size());
		for (int i = 0; i < fileList.size(); i++) {
			MultipartFile file = fileList.get(0);
			System.out.println(file.getName());
			System.out.println(file.getOriginalFilename().toString());
			File f = new File("d:\\1"); 
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		DiskFileItemFactory fac = new DiskFileItemFactory();
		fac.setRepository(new File("d:\\")); // 创建缓存工厂
		fac.setSizeThreshold(1024 * 1024 * 2); // 设置缓存区的大小
		ServletFileUpload upload = new ServletFileUpload(fac); // 高水平的API文件上传处理
		upload.setSizeMax(500 * 1024 * 1024); // 设置文件上传的最大值
		upload.setFileSizeMax(500 * 1024 * 1024); // 设置文件上传的最大值
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList1 = null;
		try {
			fileList1 = upload.parseRequest(request);
			System.out.println("fileList1:" + fileList1.size());
		} catch (FileUploadException ex) {
			ex.printStackTrace();
		}

		Map map = new HashMap();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "文件上传失败！");
			map.put("result", "failure");
		}

		return map;

	}

}
