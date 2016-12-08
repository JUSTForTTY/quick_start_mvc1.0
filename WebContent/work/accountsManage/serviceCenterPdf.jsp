<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title></title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript">
	var para = easyui_getRequestPara();
	var docName = para.docName;
	//打印
	function print_onclick() {
	    Iframe.print();
	}
</script>
</head>
<BODY>
	<div style="float: right">
	<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-right: 0px;margin-top:0px;margin-bottom:5px;"
				data-options="iconCls:'icon-print'" onclick="print_onclick()">打印</a>
	</div>	
	<script type="text/javascript">
	document.write("<iframe name=\"Iframe\" width=\"100%\" height=\"530px\" src=\"<%=basePath%>"+docName+"\"> </iframe>");
	</script>
</HTML>