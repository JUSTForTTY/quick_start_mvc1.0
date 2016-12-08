package com.tcj.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.tcj.domains.LoginEntity;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 判断session是否过期
		boolean flag = req.getSession().getAttribute("LogInDemoEntity") == null;
		if (!flag) {
			LoginEntity entity = (LoginEntity) req.getSession().getAttribute(
					"LogInDemoEntity");
			if (entity.getUserTypeFlag().equals("null")
					) {
				// 大众用户(权限与末登录相同)
				flag = true;
			}
		}
		String root = req.getContextPath();
		String strURI = req.getRequestURI();

		if (strURI.endsWith("/common_ui.html")) {

		}

		// 满足以下条件时，直接跳转(无需要登录)
		if (strURI.endsWith("login.jsp") // 登录页面
				|| strURI.endsWith("index.jsp")// 首页面
				
				|| strURI.endsWith("image.jsp")// 首页面
				|| strURI.endsWith("qiuzu.jsp")// 首页面
				|| strURI.endsWith("chuzu.jsp")// 首页面
				|| strURI.endsWith("registration.jsp")// 注册页面
				|| strURI.endsWith("timeDetail.jsp")
				|| strURI.endsWith("declaration.jsp")// 协议页面
				|| strURI.endsWith("centerNotice.jsp")// 协议页面
				|| strURI.endsWith("subCenterNotice.jsp")// 协议页面
				
				|| strURI.endsWith(".jpg")
				|| strURI.endsWith(".gif")
				|| strURI.endsWith(".png")
				|| strURI.endsWith(".js")
				|| strURI.endsWith(".css")
				|| strURI.endsWith(".flv")
				|| strURI.endsWith(".mp4")
				|| strURI.endsWith(".apk")
				|| strURI.indexOf("dwr") != -1
				|| strURI.indexOf(".swf") != -1
				|| strURI.indexOf(".do") != -1
				|| strURI.indexOf("services") != -1
				|| strURI.indexOf("/video/") != -1
				|| strURI.indexOf("demo") != -1
						|| strURI.indexOf("demoManage") != -1
				|| strURI.indexOf("wharfManage") != -1
				|| strURI.indexOf("codemaster") != -1
				|| strURI.indexOf("newsTypeManage") != -1
				|| strURI.indexOf("activityManage") != -1
				|| strURI.indexOf("clubManage") != -1
				
				 || !flag
		// || (strURI.indexOf("/work/website") != -1)//前台页面不过滤
		// || (strURI.indexOf("/myoffice/office/myoffice_head2.jsp") !=
		// -1)//用于和其他系统的接口，不过滤session
		// || (strURI.indexOf("setPageAction.getValues.dwr") != -1)
		// || (strURI.indexOf("loginAction.logout.dwr") != -1)
		) {
			// log4j取session
			HttpSession session = req.getSession();
			if (session == null) {

			} else {

				/*
				 * LogInDemoEntity
				 * customer=(LogInDemoEntity)session.getAttribute
				 * ("LogInDemoEntity"); if (customer==null){
				 * MDC.put("companyId",-1); MDC.put("subCompanyId",-1);
				 * MDC.put("loginId", -1); } elseS {
				 * MDC.put("companyId",customer.getParentCompanyId());
				 * MDC.put("subCompanyId",customer.getSubCompanyId());
				 * MDC.put("loginId", customer.getLogInId());
				 * MDC.put("employeeName", customer.getEmployeeName()); }
				 */
			}

			// 截止
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(root + "/login.jsp");
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
