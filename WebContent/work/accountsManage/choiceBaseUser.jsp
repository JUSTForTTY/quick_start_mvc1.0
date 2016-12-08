<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>站点人员选择</title>
     <%@ include file="../../inc.jsp"%>
     <script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
     <script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterAction.js'></script>
<script type="text/javascript">
	var para= easyui_getRequestPara();
	var serviceCenterId=para.serviceCenterId;
	$(function(){
	
	});
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param["userName"] = $.trim($('#userName').val());
		param["idCardNo"] = $.trim($('#idCardNo').val());
		param["cellTel"] = $.trim($('#cellTel').val());
		param["serviceCenterId"] = serviceCenterId;
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
  		<div class="easyui-panel" title="站点人员选择" data-options="iconCls:'icon-search',fit:true,">
		<div class="box">
			<span>姓名</span>
			<input id="userName"></input>
			<span>身份证号</span>
			<input id="idCardNo"></input>
			<span>手机/电话</span>
			<input id="cellTel"></input>
		</div>
		<span class="aBtn">
			<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="searchBtnClick()">查询</a>
		</span>
	</div>	
	</div>
	<div class="clear"></div>
	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="站点人员选择"
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
	                     url:serviceCenterAction.getUsers
	 ">
			<thead>
				<tr>
				<!-- 表中数据 普通数据居左，时间居中，数值居右。 -->
					<th data-options="field:'USER_ID',hidden:true">userId</th>
					<th data-options="field:'USER_NAME',align:'left',halign:'center',width:120">姓名</th>
					<th data-options="field:'USER_SEX_TEXT',align:'left',halign:'center',width:80">性别</th>
					<th data-options="field:'CELL_TEL',align:'left',halign:'center',width:120">手机/电话</th>
					<th data-options="field:'ID_CARD_NO',align:'left',halign:'center',width:140">身份证号</th>
					<th data-options="field:'SERVICE_CENTER_NAME',align:'left',halign:'center',width:120">所在站点</th>
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
