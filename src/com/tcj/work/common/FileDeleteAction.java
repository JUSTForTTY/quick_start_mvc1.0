package com.tcj.work.common;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

import com.tcj.common.util.FileUtil;



@Controller("fileDeleteAction")
@RemoteProxy(name = "fileDeleteAction")
public class FileDeleteAction {
	@RemoteMethod
	public void deleteFile(String fileName) {
		WebContext context = WebContextFactory.get();
		//FileUtil.deleteFile(fileName, context);
		// String pdfFile = fileName.substring(0, fileName.lastIndexOf("."))
		// + ".pdf";
		// ServletContext application = context.getServletContext();
		// String filePath = application.getRealPath("/");
		// String realPath = filePath + fileName;
		// File file = new File(realPath);
		// if (file.exists()) {
		// file.delete();
		// }
		// realPath = filePath + pdfFile;
		// file = new File(realPath);
		// if (file.exists()) {
		// file.delete();
		// }
	}
}
