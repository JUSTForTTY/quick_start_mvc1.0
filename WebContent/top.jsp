<%@page import="com.tcj.domains.LoginEntity"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	LoginEntity user = ((LoginEntity)session.getAttribute("LogInDemoEntity")); 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>标题</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="" />
<meta name="Author" content="" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<script language="javascript" type="text/javascript"
	src="script/jquery-1.8.3.js"></script>
<jsp:include page="inc.jsp" flush="true" />
<script language="javascript" type="text/javascript"
	src="script/index.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/baseUserAction.js'></script>
<link href="css/WBSstyle.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
	function hideshow() {
		parent.document.getElementsByName("topframe")[0].rows = parent.document.getElementsByName("topframe")[0].rows == "18,*" ? "85,*": "18,*";
		if($("#minPanel").is(":visible")){
			$("#minPanel").hide();
			$("#maxPanel").show();
			$(".toggleMenu").attr("src","images/Icofold-1.png");
			
		}
		else
		{
			$("#minPanel").show();
			$("#maxPanel").hide();
			$(".toggleMenu").attr("src","images/Icofold.png");
		}
	}
	
	$(function() {
// 		$("[name='toggle']").css("cursor", "pointer").click(hideshow);
// 		baseUserAction.getUserServiceCenter({
// 			async : false,
// 			callback : function(data) {
<%-- 				document.getElementById("logo").style.backgroundImage="url("+'<%=basePath%>'+data+")"; --%>
// 			}
// 		});
// 	 	baseUserAction.getCount({},{
//            	async : false,
// 			callback : function(data) {
// 				if(data>0){
<%-- 					$("#mailImg").attr("src",""+'<%=basePath%>'+"images/MailGif.gif"); --%>
// 				}
// 			}
//          });
// 	 	 $("#mailImg").click(function(data){
//         	 	var loc      = window.parent.showframe.location +"";
// 				var prefix  = loc.substring(0,loc.lastIndexOf("/")) ;
// 				var pageUrl = prefix + "/"+"work/accountsManage/messageInfo.jsp" ;
// 				if(window.parent.showframe.window.appendPage)
// 					window.parent.showframe.window.appendPage.call(null,pageUrl,"消息查看") ;
<%-- 				$("#mailImg").attr("src",""+'<%=basePath%>'+"images/Mail.gif"); --%>
//         });
		$("[name='logout']").css("cursor", "pointer").click(function(){
			baseUserAction.logout( {
				async : false,
				callback : function(data) {
					window.top.location.href = "<%=basePath%>login.jsp";
				}
			});
		});
		$("[name='pwd']").css("cursor", "pointer").click(function(){
			var url="<%=basePath%>work/accountsManage/updatePwd.jsp";
			easyui_openTopWindow("密码修改",420,200,url,function(returnValue)
			{
				if(returnValue){
					$("[name='logout']").css("cursor", "pointer").click();
				}
			});
		});	
	});
</script>
</head>
<body id="topBody">
	<div id="maxPanel" >
	<%-- <div id="logo" style="background-image:<%=basePath%>images/headImages/defaultHead.png"></div> --%>
	 <div id="logoText"></div>
		<div id="topRight" style="float:right;width:500px;">
			<table width="500" height="80" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					 <td align="right">
					</td>
				</tr>
				<tr>
					<td align="right">
					<span style="cursor:pointer;" >&nbsp;&nbsp;<image id='mailImg' src="<%=basePath%>images/Mail.gif"  align="absmiddle"/></span>
					|<span name="pwd" >&nbsp;<img src="<%=basePath%>/script/easyui/themes/icons/setting.png" alt="" width="16" height="16" align="absmiddle" />密码修改</span>
					|&nbsp;用户：<%=user.getUserName()%>
					<!-- <span name="toggle" ><img src="images/Icofold-1.png" width="16" height="16" align="absmiddle" class="toggleMenu" />&nbsp;收起&nbsp;&nbsp;</span> -->
					|<span name="logout" >&nbsp;<img src="images/exit.png" alt="" width="16" height="16" align="absmiddle" />退出&nbsp;</span></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="minPanel" style="float:right;color:#fff;;display:none;"><span name="toggle" >
		<!-- <img src="images/Icofold.png"  class="toggleMenu" width="16" height="16" align="absmiddle" id="toggleMenu" />&nbsp;展开&nbsp;&nbsp; </span> -->
		<span >用户姓名&nbsp;&nbsp;&nbsp;<%=user.getUserName()%></span>
		|<span name="logout" >&nbsp;&nbsp; <img src="images/exit.png" alt="" width="16" height="16" align="absmiddle" />&nbsp;退出&nbsp;</span>
	</div>
</body>
</html>