
<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<title>会员单位管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/memberManageAction.js'></script>
<script type="text/javascript"
	src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	var para= easyui_getRequestPara();
	//var caid=para.caid;

	//alert("点击菜单传默认类别caid="+caid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.id; 
		var idstring=id.toString();
		
		
		  var Type=row.status;
			 if(Type==0){
				  row.status="未删除";
				  }   
			  if(Type==1){
				  row.status="已删除";
			  }  
		
		
		
		
		
		
	//	var caidstring=caid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
	//	alert(status);
		param["name"] = $.trim($('#name').val());
		param["title"]=$.trim($('#title').val());
		param["status"]=$.trim($('#status').val());	
	
	//	param[' activity_start_time']=$("# activity_start_time").datebox('getValue');
	//	param['activity_end_time']=$("#activity_end_time").datebox('getValue');
	}
	//删除记录
	function delBtnClick(flag){
		//删除记录
//alert(id);
		var str="删除";
	
			var checkedRows=$('#dg').datagrid('getChecked');
			if(checkedRows.length==0){
				$.messager.alert('提示', '请选择要'+str+'的行。');
				return;
			}
			var ids="";
			$.each(checkedRows,function(i){
				ids=ids+checkedRows[i]['id']+",";
			});
			ids=ids.substring(0,ids.length-1);
			
			$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+checkedRows.length+"</font>条数据吗？", function(r) {
				if (r) {
					memberManageAction.deleteData(ids, {
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
	//nid和上面的nidstring, caid为caidstring传过来的值，obj是this,顺序不能乱
	function editBtnClick(id,obj) {
		
		// alert(id);
		// alert("你好，你的id到这了");
		var url="";
		 url = "<%=basePath%>work/memberManage/memberAddUpdate.jsp?id="+id;

	
			easyui_openTopWindow("会员单位管理 ",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	//新增(传默认caid)
	function addBtnClick() {	
	    var url="";
		 url = "<%=basePath%>work/memberManage/memberAddUpdate.jsp";
		easyui_openTopWindow("会员单位管理",1000,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
</script>
<script type="text/javascript">
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
		 $("#add").show();
}	
else if(usertype==1){
		$("#add").show();
	}
else{
	$("#add").hide();
}
});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 70px">
		<div id="search" class="easyui-panel" title="会员单位"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>会员单位名称</span> <input id="name">
			</div>

			<div class="box">
				<span>公司简介</span> <input id="title">
			</div>

<div class="box">
				<span>状态</span> <select id="status">
					<option value="0" selected="selected">未删除</option>
					<option value="1">已删除</option>

</select>
</div>



			<span class="aBtn"> <a id="searchBtn"
				href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 100px;" data-options="iconCls:'icon-search'"
				onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	<div class="clear"></div>
</div>
	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="会员单位管理"
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
	                     url:memberManageAction.getMemberList">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<th
						data-options="field:'id',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">会员单位编号</th>
					
					
					<th
						data-options="field:'name',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">会员单位名称</th>
					<th
						data-options="field:'title',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">公司简介</th>

					<th
						data-options="field:'address',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">公司地址</th>
					<th
						data-options="field:'status',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">状态</th>
<th
						data-options="field:'create_time',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建时间</th>
<th
						data-options="field:'update_time',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新时间</th>
<th
						data-options="field:'create_user',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建人</th>
<th
						data-options="field:'update_user',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新人</th>

				</tr>

			</thead>

		</table>
	</div>
	<div id="add" style="height: 25px">
		<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick()">新增</a>
		<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		<!-- 		   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import"  -->
		<!-- 		   plain="true" onclick="openUploadFileDialog()">用模版批量导入数据</a> -->

	</div>


	



</body>
</html>
