<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>站点选择</title>
     <%@ include file="../../inc.jsp"%>
     <script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
     <script type="text/javascript" src='<%=basePath%>/dwr/interface/commonMethodAction.js'></script>
<script type="text/javascript">
	$(function(){
	
	});
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param["serviceCenterName"] = $.trim($('#serviceCenterName ').val());
	}
	//行双击事件
	function onDblClickRow(rowIndex, rowData){
		easyui_closeTopWindow(rowData);
	}
	function btnOk(){
		var row=$('#dg').datagrid('getSelected');
		if(row==null) row="";
		easyui_closeTopWindow(row);
	}
	
</script>
  </head>
  
  <body class="easyui-layout">
  	<div data-options="region:'north',split:false" style="height:70px">
  		<div class="easyui-panel" title="站点选择" data-options="iconCls:'icon-search',fit:true,">
		<div class="box">
			<span>站点名称</span>
			<input id="serviceCenterName"></input>
		</div>
		<span class="aBtn">
			<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="searchBtnClick()">查询</a>
		</span>
	</div>	
	</div>
	<div class="clear"></div>
	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="站点选择"
			data-options="
						fitColumns:false,
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     pagination:true,
	                     toolbar: '#add',
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     onBeforeLoad:onBeforeLoad,
	                     onDblClickRow:onDblClickRow,
	                     url:commonMethodAction.getServiceCenter
	 ">
			<thead>
				<tr>
					<th data-options="field:'SERVICE_CENTER_NAME',align:'right',halign:'center',width:180">站点名称</th>
					<th data-options="field:'SERVICE_CENTER_ADD',align:'right',halign:'center',width:180">站点地址</th>
					<th data-options="field:'AREA_FULL_NAME',align:'right',halign:'center',width:280">区域</th>
				</tr>
			</thead>
		</table>
		<div id="add" style="height:25px">
			<a id="addOk" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-ok',plain:true" onclick="btnOk(this)">确定</a>
		</div>		
	</div>
  </body>
</html>
