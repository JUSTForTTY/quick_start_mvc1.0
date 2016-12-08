<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>活动管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/messageManageAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">

	function searchBtnClick() {
		$('#dg').datagrid('load');//重新加载页面。
	}	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";	
		var id=row.id;
		var idstring=id.toString();
        return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["username"] = $.trim($('#username').val());
		param["providername"] = $.trim($('#providername').val());
       
	}
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="条件查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>用户名称:</span> <input id="username"></input>
				<span>留言至公司单位:</span> <input id="providername"></input>
			
			</div>
			<span class="aBtn">
				<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
					style="margin-left:100px;" data-options="iconCls:'icon-search'"
					onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="数据维护"
			data-options="
						fitColumns:false,
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     toolbar: '#add',
	                     pagination:true,
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     onBeforeLoad:onBeforeLoad,
	                     url:messageManageAction.getListMessage ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
                    <th data-options="field:'username',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">用户名称</th>
                    <th data-options="field:'sex',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">性别</th>
                    <th data-options="field:'tel',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">手机号</th>
                    <th data-options="field:'email',width:120,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">邮箱</th>
                    <th data-options="field:'providerId',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">公司单位编号</th>
                    <th data-options="field:'providername',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,50]'}},align:'center',halign:'center'">留言至公司单位</th>
					<th data-options="field:'visitor_feedback',width:400,editor:{type:'validatebox',options:{required:true,validType:'length[1,50]'}},align:'center',halign:'center'">留言内容</th>
					<th data-options="field:'time',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">留言时间</th>				
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
<!-- 			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"  -->
<%-- 				data-options="iconCls:'icon-add',plain:true" onclick="addActivity('<%=request.getParameter("cid") %>',this)">新增活动</a>  --%>
		</div>
	</div>
</body>
</html>
