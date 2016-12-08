package com.tcj.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Category;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tcj.common.EgladServiceException;
import com.tcj.common.enums.CategoryType;

public class FileUtil {
	private static final String UPLOAD_FOLD = "uploadfiles";

	public static void convert2PDF(File inputFile, String outputFilePath)
			throws Exception {
		try {
			DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

			String officeHome = getOfficeHome();
			// System.out.println(officeHome);
			config.setOfficeHome(officeHome);
			OfficeManager officeManager = config.buildOfficeManager();
			officeManager.start();
			OfficeDocumentConverter converter = new OfficeDocumentConverter(
					officeManager);

			if (inputFile.exists()) {// 找不到源文件, 则返回
				File outputFile = new File(outputFilePath);
				if (!outputFile.getParentFile().exists()) { // 假如目标路径不存在, 则新建该路径
					outputFile.getParentFile().mkdirs();
				}
				converter.convert(inputFile, outputFile);
			} else {
				throw new EgladServiceException("源文件不存在");
			}
			officeManager.stop();
		} catch (Exception ex) {
			throw new EgladServiceException(ex.getMessage());
		}
	}

	public static String getOfficeHome() {
		String osName = System.getProperty("os.name");
		if (Pattern.matches("Linux.*", osName)) {
			return "/opt/openoffice.org3";
		} else if (Pattern.matches("Windows.*", osName)) {
			return "C:\\Program Files\\OpenOffice 4";
		} else if (Pattern.matches("Mac.*", osName)) {
			return "/Application/OpenOffice.org.app/Contents";
		}
		return null;
	}

	public static String getOutputFilePath(String inputFilePath) {
		String outputFilePath = inputFilePath.replaceAll(".doc", ".pdf");
		return outputFilePath;
	}

	/**
	 * 保存用户上传的文挡,并转化为pdf文挡
	 * 
	 * @param request
	 * @return
	 */
	public static Map saveFile(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("msg", "文件上传失败！");
		map.put("result", "failure");
		try {
			// 保存文件的路径
			ServletContext c = request.getSession().getServletContext();
			String savePath = c.getRealPath("/");
			savePath +=File.separator+".."+File.separator +"uploadImage"+File.separator  + UPLOAD_FOLD + File.separator;
			System.out.println("savePath=" + savePath);
			File leaseImage = new File(savePath);
			if (!(leaseImage.exists())) {
				leaseImage.mkdirs();
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
				int width = bi.getWidth();
				int height =  bi.getHeight();
				
				String caid = request.getParameter("caid");
				String images=request.getParameter("images");
				String miniture=request.getParameter("miniture");
				judgeImageSize(caid, width, height, map,miniture,images);
				name = item.getOriginalFilename();
				// 扩展名格式：
				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}
				File file = null;
				do {
					// 生成文件名：
					String uuid = UUID.randomUUID().toString();
					name = uuid + extName;
					file = new File(savePath + name);
				} while (file.exists());
				System.out.println("savePath=" + savePath + name);
				File saveFile = new File(savePath + name);
				try {
					// 保存文件

					item.transferTo(saveFile);
					map.put("filePath", "/uploadImage/" + UPLOAD_FOLD + "/" + name);
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


	public static void judgeImageSize(String caid, int width, int height, Map map,String miniture, String images) throws Exception{

		System.out.println("caid=========="+caid);
		System.out.println("缩略图=========="+miniture);
		System.out.println("图片=========="+images);

		//动态新闻大小限制
		if(caid.equals(CategoryType.CATEGORY_TYPE_DYNAMICNEWS.getCode())){
			if(width>=CommonDefine.DYNAMICNEWS_WIDTH || height>=CommonDefine.DYNAMICNEWS_HEIGHT)
			{
				map.put("msg", "图片大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();
			}
		}
		//家长必读照片大小限制
		if(caid.equals(CategoryType.CATEGORY_TYPE_MUSTREAD.getCode())){
			if(width>=CommonDefine.MUSTREAD_WIDTH||height>=CommonDefine.MUSTREAD_HEIGHT)

			{
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();
			}
		}
		//课程体系背景图照片大小限制
		if(caid.equals(CategoryType.CATEGORY_TYPE_CURRICULUM.getCode()) && images!=null && !("").equals(images)){
				//if(image.equals("")||image.equals(null)){
			if(width>=CommonDefine.CURRICULUM_WIDTH||height>=CommonDefine.CURRICULUM_HEIGHT){
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();	
				//	}
			}
		}
		//课程体系图标照片大小限制
		 if (caid.equals(CategoryType.CATEGORY_TYPE_CURRICULUM.getCode()) && miniture!=null && !("").equals(miniture)){
			//if(miniture.equals("")||miniture.equals(null)){
			if(width>=CommonDefine.minCURRICULUM_WIDTH||height>=CommonDefine.minCURRICULUM_HEIGHT){
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();	
		//	}
		}
		}
		//滑动新闻照片大小限制
		if(caid.equals(CategoryType.CATEGORY_TYPE_ROLLING_NEWS.getCode())){
			if(width>=CommonDefine.ROLLING_NEWS_WIDTH||height>=CommonDefine.ROLLING_NEWS_HEIGHT){
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();	
			}
		}
		//明星学员照片大小
		if(caid.equals(CategoryType.CATEGORY_TYPE_STARMEMBER.getCode()) && images!=null && !("").equals(images)){
			System.out.println("明星学员Image"+images);
			if(width>=CommonDefine.STARMEMBER_WIDTH||height>=CommonDefine.STARMEMBER_HEIGHT){
				System.out.println("明星学员照片大小images"+images);
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();
			}
		}
		//明星学员头像大小
		if(caid.equals(CategoryType.CATEGORY_TYPE_STARMEMBER.getCode()) && miniture!=null && !("").equals(miniture)){
			System.out.println("明星学员miniture"+miniture);
			if(width>=CommonDefine.minSTARMEMBER_WIDTH||height>=CommonDefine.minSTARMEMBER_HEIGHT){
				System.out.println("明星学员头像大小miniture"+miniture);
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();
			}
			
		}
	
		
		
		
		//名师风采照片大小限制
		if(caid.equals(CategoryType.CATEGORY_TYPE_TEACHERS.getCode())){
			if(width>=CommonDefine.TEACHERS_WIDTH||height>=CommonDefine.TEACHERS_HEIGHT){
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();
			}
		}
		//相关视频图片限制大小	

		if(caid.equals(CategoryType.CATEGORY_TYPE_VIDEOS.getCode())){
			if(width>=CommonDefine.VIDEOS_WIDTH||height>=CommonDefine.VIDEOS_HEIGHT){
				map.put("msg", "文件大小不符合要求！");
				map.put("result", "failure");
				throw new EgladServiceException();	
			}
		
	}
		//关于天才纪
if(caid.equals(CategoryType.CATEGORY_TYPE_Genius.getCode())){
	if(width>=CommonDefine.AGENCY_WIDTH||height>=CommonDefine.AGENCY_HEIGHT){
		map.put("msg", "文件大小不符合要求！");
		map.put("result", "failure");
		throw new EgladServiceException();	
	}
	}
//关于董事长
if(caid.equals(CategoryType.CATEGORY_TYPE_CEO.getCode())){
	if(width>=CommonDefine.AGENCY_WIDTH||height>=CommonDefine.AGENCY_HEIGHT){
		map.put("msg", "文件大小不符合要求！");
		map.put("result", "failure");
		throw new EgladServiceException();	
	}
	}
//加入我们
if(caid.equals(CategoryType.CATEGORY_TYPE_JOINWE.getCode())){
		if(width>=CommonDefine.AGENCY_WIDTH||height>=CommonDefine.AGENCY_HEIGHT){
			map.put("msg", "文件大小不符合要求！");
			map.put("result", "failure");
			throw new EgladServiceException();	
		}
		}

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
