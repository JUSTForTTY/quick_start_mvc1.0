<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>活动管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/activityManageAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">

	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');//重新加载页面。
	}
	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";
		
		var aid=row.aid;
		if(row.stick==0){
			row.stick="不置顶"
		}else{
			row.stick="置顶"
		}
		var status=row.status;
		if(row.status==0){
			row.status="正常"
		}
		if(row.status==1){
			row.status="已取消"
		}
		if(row.status==2){
			row.status="已结束"
		}
		return html;
		
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["name"] = $.trim($('#name ').val());
		//向后台传值isDelete=1,作为查询已删除活动的判断条件
		param["isDelete"] = 1;
        
	}
	
	//恢复记录
	function recoveryData(flag){

 		//向后台传值isDelete=0,作为逻辑恢复的判断条件
       var isDelete="0";
		var str="恢复";
		var checkedRows=$('#dg').datagrid('getChecked');//校验是否选中要删除的记录。
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var ids="";
		$.each(checkedRows,function(i){//遍历被选中的记录id。
			ids=ids+checkedRows[i]['aid']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				activityManageAction.deletes(ids,isDelete, {
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

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="已删除活动查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>已删除活动名称</span> <input id="name"></input>
			
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
		<table id="dg" class="easyui-datagrid" title="已删除活动恢复"
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
	                     url:activityManageAction.getList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:180,hidden:'ture',align:'center'">修改</th>
<!-- 					<th data-options="field:'aid',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">活动ID</th> -->
                    <th data-options="field:'name',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">活动名称</th>
					<th data-options="field:'subtitle',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动子标题</th>
					<th data-options="field:'day',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动用时</th>
					<th data-options="field:'age',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">适合年龄</th>
					<th data-options="field:'stick',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">是否置顶</th>
<!-- 					<th data-options="field:'logo',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">活动logoURL</th> -->
<!-- 					<th data-options="field:'official_url',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动官网URL</th> -->
<!-- 					<th data-options="field:'site',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动举办地址</th> -->
					<th data-options="field:'province',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">所在省</th>
					<th data-options="field:'city',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">所在市</th>
					<th data-options="field:'address',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">详细地址</th>
					<th data-options="field:'register_start',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动报名开始时间</th>
					<th data-options="field:'register_end',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动报名结束时间</th>
					<th data-options="field:'start_time',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动开始时间</th>
					<th data-options="field:'end_time',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动结束时间</th>
					<th data-options="field:'status',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动状态</th>
					<th data-options="field:'act_detail_url',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动图文介绍url</th>
					<th data-options="field:'follow_sum',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">关注人数</th>
					<th data-options="field:'click_num',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">点击次数</th>
					<th data-options="field:'agree_num',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">赞的次数</th>
					<th data-options="field:'disagree_num',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">踩的次数</th>
					<th data-options="field:'agree_num_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">点赞时间</th>
					<th data-options="field:'create_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建人</th>
					<th data-options="field:'create_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建时间</th>
					<th data-options="field:'modify_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新人</th>
					<th data-options="field:'modify_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新时间</th>
									
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-back',plain:true" onclick="recoveryData(0)">恢复活动</a>
		</div>
	</div>
</body>
</html>
