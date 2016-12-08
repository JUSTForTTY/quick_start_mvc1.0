<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>代码类型管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/codeMasterAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<script type="text/javascript">

	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.id;
		var idstring=id.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,this)' >修改</a>";
		return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["codetype"] = $.trim($('#codetype ').val());
		param["codedescription"] = $.trim($('#codedescription ').val());
		/* param["loginId"] = $.trim($('#loginId ').val());
		param["userName"] = $.trim($('#userName ').val());
		param["userTypeId"]=$("#userTypeId").combobox("getValue"); */
	}
	
	//修改
	function editBtnClick(id,obj) {
		var url="<%=basePath%>work/codemasterManage/codetypeSubManage.jsp?id="+id;
		easyui_openTopWindow("代码类型管理",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	

</script>
</head>
<body>
<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="代码类型管理"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>代码编号</span> <input id="codetype"></input>
			
			</div>
			<div class="box">
				<span>代码类型</span> <input id="codedescription"></input>
			
			</div>
			<span class="aBtn">
				<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
					style="margin-left:100px;" data-options="iconCls:'icon-search'"
					onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'" style="height:520px">
		<table id="dg" class="easyui-datagrid" title="代码类型列表"
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
	                     url:codeMasterAction.getTypeList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>			
					<th data-options="field:'codetype',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">代码编号</th>
					<th data-options="field:'codedescription',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">代码类型</th>
					
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClick('',this)">新增</a> 
			
		</div>
	</div>
</body>
</html>