<%@page import="com.tcj.common.util.CommonDefine"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>课程统计</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/statisticsManageAction.js'></script>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>


<script type="text/javascript">

	//导出excel
	function downloadBtnClick(){
		var param={};
		param['name'] = $.trim($('#name').val());
		param["actname"] = $.trim($('#actname ').val());
		param["paystatus"] =$('#paystatus').combobox("getValue");
		statisticsManageAction.exportExcel(param,{
	        callback:function(data){
	        	window.location.href="<%=basePath%>work/activityManage/excelport.jsp"; 
	        	 
	        },
	        async : false
	    });
	}
	//查询按钮
	function searchBtnClick() {
		
		$('#dg').datagrid('load');//重新加载页面。
	}
	
	
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["name"] = $.trim($('#name ').val());
		param["actname"] = $.trim($('#actname ').val());
		param["paystatus"] =$('#paystatus').combobox("getValue");
		
		}
	
	//操作
	function operationFmt(value, row, index) {
			if(value==0)
			
			 html = "已付款";
			else{
				html = "未付款";
			}
		return html;
	}
	//添加小数点
	function gshi(val){
      
		if(val!="undefined"&&val!=null){
			var value= val.toFixed(2);
			return '<span>￥'+value+'</span>';
		}else{
			return '<span></span>';
		}
	}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="课程统计"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>课程名称</span> <input id="name" name="name"></input>
			
			</div>
			<div class="box">
				<span>缴费项目</span> <input id="actname" name="actname"></input>
			
			</div>
			<div class="box">
				<span>付款状态</span> 
				<select  panelHeight="70px" class="easyui-combobox" id="paystatus">
				<option selected="selected" value="3">全部</option>
				<option  value="<%=CommonDefine.LOG_PAY_STATUS_FALSE%>">未付款</option>
				<option  value="<%=CommonDefine.LOG_PAY_STATUS_TRUE%>">已付款</option>
				</select>
			
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
		<table id="dg" class="easyui-datagrid" title="课程统计"
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
	                     url:statisticsManageAction.getList ">
			<thead>
				<tr>
					<th data-options="field:'activityName',align:'center',halign:'center',width:180">课程名称</th>
					<th data-options="field:'activity_project_name',align:'center',halign:'center',width:130">缴费项目</th>      
					<th data-options="field:'actMemberName',align:'center',halign:'center',width:130">客户姓名</th>
					<th data-options="field:'mobile',align:'center',halign:'center',width:130">联系电话</th>
					<th data-options="field:'status',formatter:operationFmt,align:'center',halign:'center',width:130">付款状态</th>
					<th data-options="field:'pay_type',align:'center',halign:'center',width:130">支付类型</th>
					<th data-options="field:'payment',align:'center',halign:'center',width:130,formatter:gshi">价格</th>
					<th data-options="field:'start_time',align:'center',halign:'center',width:130">课程起始时间</th>
					<th data-options="field:'end_time',align:'center',halign:'center',width:130">课程结束时间</th>
					
					
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">

			<a id="downloadBtn" href="javascript:void(0)"
				class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-sum',plain:true"
				onclick="downloadBtnClick()">导出excel文件</a>
		</div>
	</div>
</body>
</html>
