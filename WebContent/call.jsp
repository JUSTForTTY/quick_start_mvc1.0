<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>
	后台管理系统
	</title>
	<%@ include file="../../inc.jsp"%>
    <script src="./script/jquery-1.8.3.js"></script>
	<script src="./script/tree/jquery.treeview.js"></script>
	<script src="./script/index.js"></script>
	<script language="javascript" type="text/javascript">
		var t = function(){
				$("#container").attr("src","container.jsp");
			};
			$(function(){
				//window.location.reload();
				t();
			});
	</script>
	<!-- 	 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	</head>
	<frameset rows="85,*" frameborder="no"  name="topframe">
		<frame src="top.jsp" scrolling="no"  noresize="noresize"/>
		<frameset cols="180,*"  name="mainframe">
			<frame src="left.jsp" scrolling="auto" noresize="noresize"/>
			<frame id="container"  name="showframe" noresize="noresize" />
		</frameset>
	</frameset>
</html>	
