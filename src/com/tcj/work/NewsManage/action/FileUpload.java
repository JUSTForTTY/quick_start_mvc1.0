package com.tcj.work.NewsManage.action;
import org.springframework.web.multipart.MultipartFile;  

import javax.servlet.http.HttpServletRequest;  

import java.io.File;  
import java.io.IOException;  
import java.text.SimpleDateFormat;
import java.util.Date;  
      
    /**  
     * Created by shhao.  
     * Date: 14-9-1  
     * Time:下午4:12  
     */  
    public class FileUpload {  
      
       // public static final String FILE_PATH = "/upload/goods";  
      
        //文件上传  
    	
    	//使用 MultipartFile file 用它来接收传过来的文件
    	//path即图片保存的路径
        public static String uploadFile(MultipartFile file, HttpServletRequest request,String path) throws IOException {  
            String fileName = file.getOriginalFilename();//得到上传时的文件名
             if(file.isEmpty()){//判断文件是否为空
            	 return null; 
             }
             else{
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置时间格式
//            创建文件和目录的关键技术点如下：  
//            1、File类的createNewFile根据抽象路径创建一个新的空文件，当抽象路径指定的文件存在时，创建失败  
//            2、File类的mkdir方法根据抽象路径创建目录  
//            3、File类的mkdirs方法根据抽象路径创建目录，包括创建必需但不存在的父目录  
//            4、File类的createTempFile方法创建临时文件，可以指定临时文件的文件名前缀、后缀及文件所在的目录，如果不指定目录，则存放在系统的临时文件夹下。  
//            5、除mkdirs方法外，以上方法在创建文件和目录时，必须保证目标文件不存在，而且父目录存在，否则会创建失败  
            File tempFile = new File(path, df.format(new Date()) + "_"+String.valueOf(fileName));//文件路径和文件名
            if (!tempFile.getParentFile().exists()) {//判断目标文件所在的目录是否存在
                tempFile.getParentFile().mkdir(); //如果目标文件所在的目录不存在，则创建父目录  
            }   
            if (!tempFile.exists()) {//判断抽象路径指定的文件是否存在
                tempFile.createNewFile();//根据抽象路径创建一个新的空文件
            }  
            file.transferTo(tempFile);//写入文件   
             
            return  "images/homeImages/"+tempFile.getName();//返回保存的文件夹名字和图片的名字，用来保存在数据库中
             
             } 
        }  
      
          
    }  