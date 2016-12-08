<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<title>新闻信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
<script type="text/javascript"
	src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	var para= easyui_getRequestPara();
	var caid=para.caid;
	
	//alert("点击菜单传默认类别caid="+caid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var nid=row.nid;
		var caid=row.caid;
	//	var status=row.status;
	//	alert("状态"+status);
		//var onhome=row.onhome;
		 var adminType=row.onhome;
			  if(adminType==1){
				  row.onhome="显示";
				  }   
			  if(adminType==0){
				  row.onhome="不显示";
			  } 
			  
			 var statusType=row.status;
			 if(statusType==1){
				  row.status="已删除";
				  }   
			  if(statusType==0){
				  row.status="未删除";
			  } 
			  
		var idstring=nid.toString();
		var caidstring=caid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,&quot;"+caidstring+"&quot;,this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param["title"] = $.trim($('#title').val());
		param["keywords"] = $.trim($('#keywords').val());
		param["caid"]= $.trim($('#caid').val());//加载之前将caid传到后台
		param["name"]= $.trim($('#name').val());
		param["onhome"]= $.trim($('#onhome').val());
		param["status"]=$.trim($('#status').val());
		//alert(param["caid"]);
	}

	
	
// 	//删除记录
// 	function delBtnClick(flag){
// 		var map={};
// 		map['flag']=flag;
// 		var str;
// 		if(flag=="0"){
// 			 str="删除";
// 		}else if(flag=="1"){
// 			 str="恢复";
// 		}
// 		var checkedRows=$('#dg').datagrid('getChecked');
// 		if(checkedRows.length==0){
// 			$.messager.alert('提示', '请选择要'+str+'的行。');
// 			return;
// 		}
// 		var ids="";
// 		$.each(checkedRows,function(i){
// 			ids=ids+checkedRows[i]['nid']+",";
// 		});
// 		ids=ids.substring(0,ids.length-1);
// 		map['ids']=ids;
// 		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
// 			if (r) {
// 				newsManageAction.deletedata(map, {
// 					async : false,
// 					callback : function(data) {
// 						if (data.success) {
// 							$.messager.alert('确认', str+'成功!');
// 							$('#dg').datagrid('reload');
// 						} else {
// 							$.messager.alert('警告', str+'失败!');
// 						}
// 					}
// 				});
// 			}
// 		});
// 	}
	
	
	
	//删除记录
	function delBtnClick(flag){
		//删除记录
			var str="删除";
			var checkedRows=$('#dg').datagrid('getChecked');
			if(checkedRows.length==0){
				$.messager.alert('提示', '请选择要'+str+'的行。');
				return;
			}
			var ids="";
			$.each(checkedRows,function(i){
				ids=ids+checkedRows[i]['nid']+",";
			});
			ids=ids.substring(0,ids.length-1);
			$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
				if (r) {
					newsManageAction.deletedata(ids, {
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
	function editBtnClick(nid,caid,obj) {
		
		 
		var url="";
		if(caid==1 || caid==2)
		 url = "<%=basePath%>work/newsManage/parentMustReadSubManage.jsp?nid="+nid+"&&caid="+caid;
// 		if(caid==3)
<%-- 			url="<%=basePath%>work/newsManage/courseSubManage.jsp?nid="+nid+"&&caid="+caid; --%>
		if(status==1)
		url="<%=basePath%>work/newsManage/parentMustReadSubManage.jsp?nid="+nid+"&&caid="+caid+"&&status="+status;
			easyui_openTopWindow("内容管理",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	
	
	
	
	
	
	
	//新增(传默认caid)
	function addBtnClick(caid,obj) {
		
	//	alert("点击新增传所属类别caid="+caid);
		var url = "<%=basePath%>work/newsManage/parentMustReadSubManage.jsp?caid="+caid;
		
		
		easyui_openTopWindow("内容管理",800,738,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}

	
	//修改已删除的数据，恢复已删除的数据
	
	//function  
	

	//查询已删除的数据
	
	function searchDate(caid,obj){
		var url="<%=basePath%>work/newsManage/newsRecoveryManage.jsp?caid="+caid; 
		easyui_openTopWindow("内容管理",1500,640,url,function(returnValue)
				{
					$('#dg').datagrid('reload');
				}); 
			}
	
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 70px">
		<div id="search" class="easyui-panel" title=" "
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>标题</span> <input id="title"></input> <input id="caid"
					type="hidden" value="<%=request.getParameter("caid") %>"></input>
			</div>

			<div class="box">
				<span>首页显示</span> <select id="onhome"><option value="">全部</option>
					<option value="1">显示</option>
					<option value="0">不显示</option>
				</select>
			</div>

			<div class="box">
				<span>是否删除</span> <select id="status"><option value="0"
						selected="selected">未删除</option>
					<option value="1">已删除</option>
					<option value=" ">全部</option>
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

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title=""
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
	                     url:newsManageAction.getNewsList">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<th
						data-options="field:'title',width:300,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">标题</th>
					<th
						data-options="field:'englishTitle',width:300,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">英文标题</th>
					<th
						data-options="field:'onhome',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">首页显示</th>
					<th
						data-options="field:'status',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">是否删除</th>
					<th
						data-options="field:'briefintro',width:350,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">概要</th>
				</tr>

			</thead>

		</table>
	</div>
	<div id="add" style="height: 25px">
		<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addBtnClick('<%=request.getParameter("caid") %>',this)">新增</a>
		<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
<!-- 		<a id="redoBtn" href="javascript:void(0)" class="easyui-linkbutton" -->
<!-- 			style="margin-left: 10px; display:" -->
<!-- 			data-options="iconCls:'icon-redo',plain:true" -->
<!-- 			onclick="delBtnClick(1)">恢复</a> -->

	</div>
</body>
</html>
