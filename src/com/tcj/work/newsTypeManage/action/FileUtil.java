package com.tcj.work.newsTypeManage.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tcj.common.EgladServiceException;

public class FileUtil {
	
	private Log log = LogFactory.getLog(getClass());
	
	private static final String UPLOAD_FOLD = "uploadfiles";

	public static Map saveFile(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("msg", "文件上传失败！");
		map.put("result", "failure");
		String id = request.getParameter("id");
		try {
			// 保存文件的路径
			ServletContext c = request.getSession().getServletContext();
			String savePath = c.getRealPath("/");
			savePath +="images/newsType/";
			System.out.println("保存路径为:" + savePath);
			
			File fileImage = new File(savePath);
			if (!(fileImage.exists())) {
				fileImage.mkdirs();
			}

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileList = multipartRequest.getFiles("fileData");
			
			Iterator<MultipartFile> it = fileList.iterator();
			String name = "";
			String extName = "";
			while (it.hasNext()) {
				MultipartFile item = it.next();
				//获取上传图片的宽高
				  BufferedImage bi =ImageIO.read(item.getInputStream());
				  System.out.println(id);
				  bi.getWidth();
				  bi.getHeight();
				name = item.getOriginalFilename();
				// 扩展名格式：
//				if (name.lastIndexOf(".") >= 0) {
//					extName = name.substring(name.lastIndexOf("."));
//				}
				File file = null;
				do {
					// 生成文件名：
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置时间格式
//					name = df.format(new Date())+"_"+extName;
					name = df.format(new Date())+"_"+name;
					file = new File(savePath + name);
				} while (file.exists());
				System.out.println("图片保存名为" + savePath + name);
				File saveFile = new File(savePath + name);
				try {
					// 保存文件
					item.transferTo(saveFile);
					
//					String path = request.getContextPath();
//					String basePath = request.getScheme() + "://"
//							+ request.getServerName() + ":" + request.getServerPort()
//							+ path + "/";
					
					map.put("filePath","images/newsType/"+name);
					map.put("result", "success");
					
					
					
					
					map.put("msg", "文件上传成功！");
				} catch (Exception e) {
					return map;
				}
			}
		} catch (Exception e) {
			return map;
		}
		return map;
	}

	/**
	 * 删除文件
	 * @param fileName
	 * @param context
	 */
	public static void deleteFile(String fileName, HttpServletRequest request) {
		if (fileName != null && !fileName.equals("")) {
			String filePath = request.getSession().getServletContext()
					.getRealPath("/");
			filePath +=File.separator+".."+File.separator;
//			System.out.println(filePath);
			String realPath = filePath + fileName;
//			System.out.println("realpath" + realPath);
			File file = new File(realPath);
			if (file.exists()) {
				file.delete();
			} 
		}
	}
}
