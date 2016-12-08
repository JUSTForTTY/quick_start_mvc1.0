package com.tcj.work.NewsManage.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.enums.CategoryType;
import com.tcj.common.util.FileUtil;
import com.tcj.common.util.IOUtil;
import com.tcj.domains.Category;
import com.tcj.domains.News;
import com.tcj.work.NewsManage.biz.NewsManageBiz;

import net.sf.json.JSONObject;
import oracle.sql.DATE;

/**
 * News基础方法.
 * 
 * @author zxs
 * @date 2016-7-27 中午12:36:15
 * @version 1.0
 * @history
 */

@Controller("newsManageAction")
@RemoteProxy(name = "newsManageAction")
public class NewsManageAction {

	@Autowired
	@Qualifier("newsManageBizImpl")
	private NewsManageBiz newsManageBiz;

	/**
	 * 
	 * 分页条件查询 家长必读 动态新闻
	 */
	@RemoteMethod
	public Page getNewsList(Map<String, String> param) {

		// System.out.println(news.caid());
		// List<Category> caid=new List<>;

		return newsManageBiz.getNewsList(param);
	}

	/**
	 * 新增和修改课程体系，名师， 动态新闻，滚动新闻，明星学员，家长必读，视频的新增和修改
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map<String, String> param, HttpServletRequest request) {

		// saveorupdate是用map传，开始用bean传保存后报error,说时间错误，现在先用map,在下面在用new一个bean，把需要
		// 修改的值都拿到，防止更新是数据都更新了一遍

		News news = new News();
		news.setNid(param.get("nid"));
		// System.out.println(param.get("nid"));
		news.setCaid(param.get("caid"));
		// System.out.println(param.get("caid"));
		news.setTitle(param.get("title"));
		// System.out.println("标题" + param.get("title"));
		news.setEnglishTitle(param.get("englishTitle"));
		news.setContent(param.get("content"));
		news.setUrl(param.get("url"));
		news.setMobilecontent(param.get("mobilecontent"));
		// String onhome=param.get("onhome");
		news.setBriefintro(param.get("briefintro"));
		Integer onhome = new Integer(param.get("onhome"));
		news.setOnhome(onhome);//
		String image = MapUtils.getString(param, "image", "");
		news.setImage(image);
		// System.out.println("年后，你好"+image);
		String miniture = MapUtils.getString(param, "miniture", "");
		news.setMiniture(miniture);
		// System.out.println("你好，miniature"+miniture);
		// news.setDate(param.get("date"));
		// System.out.println("你好，news新闻");

		return newsManageBiz.saveOrUpdate(news);
	}

	/**
	 * 
	 * 天才纪大事件新增和修改
	 * 
	 */
	@RemoteMethod
	public ResultBean geniussaveOrUpdate(Map<String, String> param) {

		News news = new News();
		news.setNid(param.get("nid"));
		System.out.println("新闻idnihao " + param.get("nid"));
		news.setTitle(param.get("title"));
		news.setCaid(param.get("caid"));
		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, Integer.parseInt(MapUtils.getString(param,
		// "date", "")));

		news.setDate(param.get("date"));

		return newsManageBiz.geniussaveOrUpdate(news);
	}

	/**
	 * 
	 * 根据nid与类别id查询
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean getBynid(String nid, String caid) {

		return this.newsManageBiz.getBynid(nid, caid);
	}

	/**
	 * 
	 * 根据nid与类别id查询
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean getByCaid(String caid) {

		return newsManageBiz.getByCaid(caid);
	}

	/**
	 * 删除（逻辑删除）
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean deletedata(String ids) {
		return this.newsManageBiz.delete(ids);
	}

	/**
	 * 课程体系 英文标题，标题，内容，简介，图片
	 *
	 */
	@RemoteMethod
	public Page getCourseList(Map<String, String> param) {

		// System.out.println(news.caid());
		// List<Category> caid=new List<>;

		return newsManageBiz.getCourseList(param);
	}

	/**
	 * 
	 * 课程体系介绍,市场前景,商业模式,加盟优势,加盟流程,走进天才纪,天才纪发展 分页查询,只查询一个title
	 * 
	 */

	@RemoteMethod
	public Page getallContentList(Map<String, String> param) {

		// System.out.println(news.caid());
		// List<Category> caid=new List<>;

		return newsManageBiz.getallContentList(param);
	}

	/**
	 * 
	 * 名星学员 分页查询 Star Student;
	 * 
	 * 
	 */
	@RemoteMethod
	public Page getStarStudent(Map<String, String> param) {
		return newsManageBiz.getStarStudent(param);
	}

	/**
	 * 
	 * 
	 * 名师 famous teacher
	 */
	@RemoteMethod
	public Page getFamousTeacher(Map<String, String> param) {
		CategoryType.CATEGORY_TYPE_TEACHERS.getCode();

		return newsManageBiz.getFamousTeacher(param);
	}

	/**
	 * 
	 * 视频 video
	 */

	@RemoteMethod
	public Page getVideo(Map<String, String> param) {

		return newsManageBiz.getVideo(param);
	}

	/**
	 * 
	 * 个性化辅导流程 return Personalized auxiliary process
	 * 
	 * @RemoteMethod这个注解很重要，不要忘
	 */
	@RemoteMethod
	public Page getPersonProcess(Map<String, String> param) {
		return newsManageBiz.getPersonProcess(param);
	}

	/**
	 * 
	 * 天才纪大事件 查詢，分頁
	 * 
	 */
	@RemoteMethod
	public Page geniusEvent(Map<String, String> param) {

		return newsManageBiz.geniusEvent(param);
	}

	/**
	 * 课程体系介绍，市场前景，商业模式，加盟优势，走进天才纪，天才纪发展内容的保存和修改
	 * 
	 * 
	 */

	@RemoteMethod
	public ResultBean courseContentsaveOrUpdate(Map<String, String> param) {
		News news = new News();
		news.setNid(MapUtils.getString(param, "nid", ""));
System.out.println("nid是"+MapUtils.getString(param, "nid", ""));
		System.out.println("内容的idnihao " + MapUtils.getString(param, "nid", ""));
		news.setCaid(param.get("caid"));
		news.setTitle(param.get("title"));
		news.setContent(param.get("content"));
		news.setImage(param.get("image"));
		return newsManageBiz.contentsaveOrUpdate(news);
	}

	/**
	 * 
	 * 个性化流程新增和修改
	 * 
	 */
	@RemoteMethod
	public ResultBean personProcessSaveOrUpdate(Map<String, String> param) {

		News news = new News();

		news.setNid(param.get("nid"));
		System.out.println(param.get("nid"));
		news.setCaid(param.get("caid"));
		news.setTitle(param.get("title"));
		news.setContent(param.get("content"));

		return newsManageBiz.saveOrUpdate(news);
	}

	/**
	 * 
	 * 删除和恢复
	 * 
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean Recovery(Map<String, String> param) {
		News news = new News();
		news.setNid(param.get("nid"));
		news.setCaid(param.get("caid"));
		news.setTitle(param.get("title"));
		news.setEnglishTitle(param.get("englishTitle"));
		return newsManageBiz.Recovery(news);
	}

}