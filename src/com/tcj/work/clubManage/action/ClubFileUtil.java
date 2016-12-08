package com.tcj.work.clubManage.action;

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


import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tcj.common.EgladServiceException;
import com.tcj.common.util.CommonDefine;

public class ClubFileUtil {
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
				 
				int width=bi.getWidth();
				int height=bi.getHeight();
				  
//				  judgeImageSize(width,height,map);//图片做限制大小处理
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

	private static void judgeImageSize(int width, int height, Map map) throws EgladServiceException {
		// TODO Auto-generated method stub
		if(width >= CommonDefine.ACTIVITYLOGO_WIDTH  || height >= CommonDefine.ACTIVITYLOGO_HEIGHT){
			map.put("msg", "文件大小不符合要求！");
			map.put("result", "failure");
			throw new EgladServiceException();
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
