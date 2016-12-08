<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>社团管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/clubManageAction.js'></script>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
//判断用户类型
//'0'超级管理员;'1'快车网管理员;'2'会员单位管理;'3'社团管理员;'4'普通会员;
// var usertype = ${LogInDemoEntity.userTypeFlag};
// $(document).ready(function() {
// 	if(usertype==1){
// 	$("#providernameId").hide();
// 	$("#providername").hide();
// 	$("#providers").attr('disabled','disabled');
// 	}
// 	if(usertype==2){
// 	$("#providernameId").hide();
// 	$("#providername").hide();
// 	$("#providers").attr('disabled','true');
// 	}
// });

	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');//重新加载页面。
	}
	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";
		var cid=row.cid;
// 		alert("status="+row.create_user);
       if(row.clubstatus==1){
    	   row.clubstatus="审核通过"
       }else{
    	   row.clubstatus="待审核"
       }
       
		var idstring=cid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClickClub(&quot;"+idstring+"&quot;,this)' >社团修改</a>";
		html += " <a href='javascript:void(0)' onclick='editBtnClickClubAnnouncement(&quot;"+idstring+"&quot;,this)' >社团公告</a>";
		html += " <a href='javascript:void(0)' onclick='editBtnClickActivity(&quot;"+idstring+"&quot;,this)' >查看活动</a>";
        return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["name"] = $.trim($('#name').val());
		param["providername"] = $.trim($('#providername').val());
      
	}
	
	//删除记录
	function delBtnClick(flag){
		var str="删除";
		var checkedRows=$('#dg').datagrid('getChecked');//校验是否选中要删除的记录。
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var ids="";
		$.each(checkedRows,function(i){//遍历被选中的记录id。
			ids=ids+checkedRows[i]['cid']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				clubManageAction.deletes(ids, {
					async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('确认', str+'成功!');
							$('#dg').datagrid('reload');//重新加载页面。
						} else {
							$.messager.alert('警告', str+'失败!');
						}
					}
				});
			}
		});
	}
	
	//查询活动
	function editBtnClickActivity(cid,obj) {
		var url="<%=basePath%>work/activityManage/activityManage.jsp?cid="+cid;
		easyui_openTopWindow("活动数据维护",1300,1500,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//社团新增or修改
	function editBtnClickClub(cid,obj) {
		var url="<%=basePath%>work/clubManage/clubSubManage.jsp?cid="+cid;
		easyui_openTopWindow("社团数据维护",980,270,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	
	//已删除数据查询
		function searchRecover(cid,obj) {
		
		var url="<%=basePath%>work/clubManage/clubRecoverManage.jsp?cid="+cid;
		easyui_openTopWindow("已删除社团维护",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//待审核社团查询
		function searchPendingClub(cid,obj) {
		
		var url="<%=basePath%>work/clubManage/clubPendingManage.jsp?cid="+cid;
		easyui_openTopWindow("待审核社团维护",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="社团条件查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>社团名称</span> <input id="name"></input>		
				<span id="providernameId">所属会员单位</span> <input id="providername"></input>    
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
		<table id="dg" class="easyui-datagrid" title="社团数据维护"
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
	                     url:clubManageAction.getList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th id="editId" data-options="field:'operations',formatter:operationFmt,align:'center'">操作</th>
                    <th data-options="field:'name',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">社团名称</th>
                    <th id="providers" data-options="field:'providername',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">所属会员单位</th>
<!--                     <th data-options="field:'type',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">社团类别</th> -->
                    <th data-options="field:'clubstatus',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">社团状态</th>
                    <th data-options="field:'club_member_sum',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">社团会员数量</th>
<!--                     <th data-options="field:'fee_circle',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">会费季度</th> -->
                    <th data-options="field:'administrator',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">管理员</th>
                    <th data-options="field:'mobile',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">手机号</th>
                    <th data-options="field:'slogan',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">社团口号</th>
<!--                     <th data-options="field:'operator',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">操作员</th> -->

<!--                     <th data-options="field:'create_id',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">创建者id</th> -->
<!--                     <th data-options="field:'fee_start_date',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">会费开始时间</th> -->
					<th data-options="field:'city',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">所在市</th>
					<th data-options="field:'create_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建人</th>
					<th data-options="field:'create_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建时间</th>
					<th data-options="field:'modify_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新人</th>
					<th data-options="field:'modify_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新时间</th>
				
					
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClickClub('',this)">新增社团</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除社团</a>
			<a id="serDelBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-search',plain:true" onclick="searchRecover('',this)">已删除社团</a>
			<a id="serDelBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-search',plain:true" onclick="searchPendingClub('',this)">待审核社团</a>
		</div>
	</div>
</body>
</html>
