<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!doctype html>
<html>
<head>
<title>新闻信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	var para= easyui_getRequestPara();
	var caid=para.caid;
	
//	alert("点击菜单传默认类别caid="+caid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var nid=row.nid;
		var caid=row.caid;
		
		//var onhome=row.onhome;
		 var adminType=row.onhome;
			  if(adminType==1){
				  row.onhome="显示";
				  }   
			  if(adminType==0){
				  row.onhome="不显示";
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
		 param["isDelete"]=1;
		//alert(param["caid"]);
	}	
	//删除记录
	function recoverDate(flag){
		var isDelete=0;
		var str="恢复";
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
				newsManageAction.deletedata(ids,isDelete, {
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
	
	

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title=" "
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>标题</span> <input id="title"></input>
				<input id="caid" type="hidden" value="<%=request.getParameter("caid") %>"></input>
			</div>
			
		<div class="box">
						<span>首页显示</span>
						<select id="onhome" ><option value="">全部</option>
							<option value="1">显示</option>
							<option value="0">不显示</option>
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
 					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
<!-- 					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>		 -->
                    <th data-options="field:'title',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">标题</th>
                   <th data-options="field:'englishTitle',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">英文标题</th>
                    <th data-options="field:'onhome',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">首页显示</th> 
				 <th data-options="field:'briefintro',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">概要</th> 
	               
					
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-back',plain:true" onclick="recoverDate(0)">数据恢复</a> 
		</div>
	</div>
</body>
</html>
