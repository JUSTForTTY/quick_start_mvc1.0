<%@page import="com.tcj.domains.LoginEntity"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	LoginEntity user = ((LoginEntity)session.getAttribute("LogInDemoEntity")); 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUserName = "";
	String loginUserId = ""; 
	String loginUserTypeFlag = "";
	String idCardNo = "";
	String cellTel = "";
	if(user != null){
		loginUserName = user.getUserName();
		loginUserId = user.getUserId();
		loginUserTypeFlag = user.getUserTypeFlag();
		idCardNo=user.getIdCardNo();
		cellTel=user.getCellTel();
	}
%>
<title>安全平安屋</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
<style type="text/css">
	li a{
		font-size:10px;
	}
}	
</style>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/easyui/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/flexigrid.css"/>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/css/base/jquery.ui.all.css"/>


<script type="text/javascript" src="<%=basePath%>/script/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flexigrid.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/jquery.customplugin.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/easyui/jquery.easyui.min.js"></script>
<%--<script type="text/javascript" src="<%=basePath%>/script/easyui/jquery.edatagrid.js"></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>/script/easyui/dwrloader.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/easyui/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/util.js"></script>
<script type="text/javascript" src='<%=basePath%>/dwr/engine.js'></script>

<script  type="text/javascript" src="<%=basePath%>/js/jquery.ui.core.js"></script>
<script  type="text/javascript" src="<%=basePath%>/js/jquery.ui.widget.js"></script>
<script  type="text/javascript" src="<%=basePath%>/js/jquery.ui.position.js"></script>
<script  type="text/javascript" src="<%=basePath%>/js/jquery.ui.autocomplete.js"></script>
<script type="text/javascript" src="<%=basePath%>script/easyui/common.datagrid.js"></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/commonMethodAction.js'></script>
<%--<script type='text/javascript' src='<%=basePath%>/dwr/util.js'></script>--%>
<script language="javascript" type="text/javascript">
$(function(){

});	
</script>