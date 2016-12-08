<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>站点信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var serviceCenterId=row.serviceCenterId;
		html += " <a href='javascript:void(0)' onclick='editBtnClick("+serviceCenterId+",this)' >修改</a>";
		return html;
	}
	
	function getColumn(value, row){
		var type='';
		if(value==row.cqrdzwtsTpPath){
			type='产权人代租委托书';
		}else if (value==row.aqxfxyTpPath){
			type='安全消防协议';
		}else if (value==row.zazrbzsTpPath){
			type='治安责任保证书';
		}else if (value==row.fwzlwtsTpPath){
			type='房屋租赁委托书';
		}else if (value==row.fwzlhtTpPath){
			type='房屋租赁合同';
		}else if (value==row.jzzblsqdTpPath){
			type='居住证办理申请单';
		}else if (value==row.blcrktzdTpPath){
			type='办理出入卡通知单';
		}
		return type;
	}
	
	//格式化查看文挡
	function documentsFmt(value, row, index) {
		
		var html = " ";
		var type = "";
		if(value!=null && value!=""){
			type= getColumn(value,row);
			value = value.substring(0, value.lastIndexOf("."))+".pdf";
			var serviceCenterId=row.serviceCenterId;
			html += " <a href='javascript:void(0)' onclick='viewClick(\"" + value + "\",this,\""+type+"\")' >查看</a>";
		}
		return  html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["serviceCenterName"] = $.trim($('#serviceCenterName').val());
	}
	
	//删除记录
	function delBtnClick(flag){
		var str="删除";
		
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var serviceCenterIds="";
		$.each(checkedRows,function(i){
			serviceCenterIds=serviceCenterIds+checkedRows[i]['serviceCenterId']+",";
		});
		serviceCenterIds=serviceCenterIds.substring(0,serviceCenterIds.length-1);

		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				serviceCenterAction.deleteServiceCenters(serviceCenterIds, {
					async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('确认', str+'成功!');
							$('#dg').datagrid('reload');
						} else {
							$.messager.alert('警告', str+'失败!');
						}
					}
				});
			}
		});
	}
	
	//修改
	function editBtnClick(serviceCenterId,obj) {
		//var orgName="";
		//if(userId!="") orgName=$(obj).attr("orgname");
		var url="<%=basePath%>work/accountsManage/subServiceCenter.jsp?serviceCenterId="+serviceCenterId;
		easyui_openTopWindow("站点管理",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	
	function viewClick(docName, obj, type){
		//alert(cols);
		var url="<%=basePath%>work/accountsManage/serviceCenterPdf.jsp?docName=" + docName + "&date="+new Date().getTime();
		//if(cols=1);
		easyui_openTopWindow(type,820,610,url); 
	}
	

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="站点管理"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>站点名称</span> <input id="serviceCenterName"></input>
				<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
					style="margin-left:10px;" data-options="iconCls:'icon-search'"
					onclick="searchBtnClick()">查询</a>
			</div>		
		</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="站点信息管理"
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
	                     url:serviceCenterAction.getServiceCenters
	 ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>
					<th data-options="field:'serviceCenterId',align:'right',halign:'center',width:80,hidden:true">站点ID</th>
					<th data-options="field:'serviceCenterName',align:'left',halign:'center',width:100">站点名称</th>
					<th data-options="field:'serviceCenterAdd',align:'left',halign:'center',width:200">站点地址</th>
					<th data-options="field:'serviceCenterTel',align:'left',halign:'center',width:90">站点电话</th>
					<th data-options="field:'areaName',align:'left',halign:'center',width:90">所属区域</th>
					<th data-options="field:'propertyUserName',align:'left',halign:'center',width:70">产权审核人</th>
					<th data-options="field:'checkUserName',align:'left',halign:'center',width:70">房屋审核人</th>
					<th data-options="field:'publishUserName',align:'left',halign:'center',width:60">发布人</th>
					<th data-options="field:'cqrdzwtsTpPath',formatter:documentsFmt, align:'center',halign:'center',width:110">产权人代租委托书</th>
					<th data-options="field:'aqxfxyTpPath',formatter:documentsFmt, align:'center',halign:'center',width:100">安全消防协议</th>
					<th data-options="field:'zazrbzsTpPath',formatter:documentsFmt, align:'center',halign:'center',width:100">治安责任保证书</th>
					<th data-options="field:'fwzlwtsTpPath',formatter:documentsFmt, align:'center',halign:'center',width:100">房屋租赁委托书</th>
					<th data-options="field:'fwzlhtTpPath',formatter:documentsFmt, align:'center',halign:'center',width:100">房屋租赁合同</th>
					<th data-options="field:'jzzblsqdTpPath',formatter:documentsFmt, align:'center',halign:'center',width:110">居住证办理申请单</th>
					<th data-options="field:'blcrktzdTpPath',formatter:documentsFmt, align:'center',halign:'center',width:110">办理出入卡通知单</th>
					<th data-options="field:'intentionAppointUserName',align:'left',halign:'center',width:100">看房预约处理人</th>
					<th data-options="field:'accompanyUserName',align:'left',halign:'center',width:100">现场看房处理人</th>
					<th data-options="field:'leaseTransactionUserName',align:'left',halign:'center',width:80">租赁处理人</th>
					<th data-options="field:'memo',align:'left',halign:'center',width:220">备注</th>
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClick('',this)">新增</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		</div>
	</div>
</body>
</html>
