package com.tcj.work.NewsManage.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.CRC32;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.directwebremoting.io.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller()
@RequestMapping("/test")
public class TestMvc {
	
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		//注册日期转换
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:dd"), true));
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.POST)
	public @ResponseBody Map getUserInfo(HttpServletRequest request) {
		System.out.println("asdasd");
		Map map = new HashMap();
		map.put("msg", "头像上传成功！");
		try {

			System.out.println("path-->" + request.getServletPath() // 请求页面或其他地址
					+ "?" + (request.getQueryString())); // 参数 );
			// 文件扩展名
			String userId = request.getParameter("id");
			String content = request.getContentType();

			String picPath = "D:\\11.pdf";
			userId = request.getParameter("kehu");
			if (content != null) {
				// 文件名称
				try {
					File file = new File(picPath);
					//IOUtil.streamToFile(request.getInputStream(), file);
					map.put("picPath", picPath);
				} catch (Exception ex) {
					map.put("msg", "文件上传成功！");
					ex.printStackTrace();
				}
			}
			
			String savePath="D:\\";
			 DiskFileItemFactory fac = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(fac);
		        upload.setHeaderEncoding("utf-8");
		        List fileList = null;
		        try {
		            fileList = upload.parseRequest(request);
		        } catch (FileUploadException ex) {
		        	ex.printStackTrace();
		            return null;
		        }
		        Iterator<FileItem> it = fileList.iterator();
		        String name = "";
		        String extName = "";
		        while (it.hasNext()) {
		            FileItem item = it.next();
		            if (!item.isFormField()) {}
		                name = item.getName();
		                long size = item.getSize();
		                String type = item.getContentType();
		                System.out.println(size + " " + type);
		                if (name == null || name.trim().equals("")) {
		                    continue;
		                }
		                //扩展名格式：
		                if (name.lastIndexOf(".") >= 0) {
		                    extName = name.substring(name.lastIndexOf("."));
		                }
		                File file = null;
		                do {
		                    //生成文件名：
		                    name = UUID.randomUUID().toString();
		                    file = new File(savePath + name + extName);
		                } while (file.exists());
		                File saveFile = new File(savePath + name + extName);
		                try {
		                    item.write(saveFile);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }

		           }
		                
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
