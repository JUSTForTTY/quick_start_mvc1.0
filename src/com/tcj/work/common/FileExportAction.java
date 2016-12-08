package com.tcj.work.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections.MapUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

@Controller("fileExportAction")
@RemoteProxy(name = "fileExportAction")
public class FileExportAction {

	@RemoteMethod
	public FileTransfer exportPDF(Map<String, String> param) {
		//System.out.println("export pdf");
		FileTransfer exportFile = null;
		try {
			WebContext context = WebContextFactory.get();
			ServletContext application = context.getServletContext();
			String filePath = application.getRealPath("/");
//			String filePath = MapUtils.getString(param, "filePath", "");
			String fileName = MapUtils.getString(param, "fileName", "");
			String type = MapUtils.getString(param, "type", "")+".pdf";
			//System.out.println(type);
			String realPath = filePath + fileName;
			File file = new File(realPath);
	        FileInputStream fis = new FileInputStream(file);  
	        exportFile = new FileTransfer(new String( type.getBytes("utf-8"),"iso8859-1" ), "application/pdf", file.length(),fis); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return exportFile;
	}


	@RemoteMethod
	public FileTransfer exportWord(Map<String, String> param) {
	//System.out.println("export pdf");
	FileTransfer exportFile = null;
	try {
		WebContext context = WebContextFactory.get();
		ServletContext application = context.getServletContext();
		String filePath = application.getRealPath("/");
		String fileName = MapUtils.getString(param, "fileName");
		String type = MapUtils.getString(param, "type", "")+".doc";
		//System.out.println(type);
		String realPath = filePath + fileName;
		File file = new File(realPath);
        FileInputStream fis = new FileInputStream(file);  
        exportFile = new FileTransfer(new String( type.getBytes("utf-8"),"iso8859-1" ), "application/doc", file.length(),fis); 
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return exportFile;
}




}