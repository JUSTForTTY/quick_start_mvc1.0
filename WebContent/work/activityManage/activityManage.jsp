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

//easyui接收jsp页面传值,点击修改传过来的cid
var para = easyui_getRequestPara();
var cid = para.cid;
// alert("cid="+cid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');//重新加载页面。
	}
	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";
		
		
		
		var aid=row.aid;
		var cid=row.cid;

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
	
		var aidstring=aid.toString();
		var cidstring=cid.toString();
  
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+aidstring+"&quot;,&quot;"+cidstring+"&quot;,this)' >修改活动</a>";
		html += " <a href='javascript:void(0)' onclick='editBtnClickPro(&quot;"+aidstring+"&quot;,this)' >活动项目</a>";
		html += " <a href='javascript:void(0)' onclick='editBtnClickAddition(&quot;"+aidstring+"&quot;,this)' >附加项目</a>";
	    html += " <a href='javascript:void(0)' onclick='editBtnClickComment(&quot;"+aidstring+"&quot;,this)' >评论管理</a>";
		html += " <a href='javascript:void(0)' onclick='editBtnClickHistory(&quot;"+aidstring+"&quot;,this)' >精彩回顾</a>";   
// 		html += " <a href='javascript:void(0)' onclick='editBtnClickSchoolTimetable(&quot;"+aidstring+"&quot;,this)' >课程表管理</a>";   
	
        return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["name"] = $.trim($('#name').val());
        //查询前传值aid到后台，作为查询条件。
		var map = easyui_getRequestPara();
//  	    alert(map["aid"]);
        param["cid"] = map["cid"];
        
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
			ids=ids+checkedRows[i]['aid']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				activityManageAction.deletes(ids, {
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
	
	//活动修改
	function editBtnClick(aid,cid,obj) {
		var url="<%=basePath%>work/activityManage/activitySubManage.jsp?aid="+aid+"&cid="+cid;
		easyui_openTopWindow("活动数据维护",1300,1500,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//活动新增
	function addActivity(cid,obj) {
		var url="<%=basePath%>work/activityManage/activitySubManage.jsp?cid="+cid;
		easyui_openTopWindow("活动数据维护",1300,1500,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//活动项目
	function editBtnClickPro(aid,obj) {
		var url="<%=basePath%>work/activityManage/projectManage.jsp?aid="+aid;
		easyui_openTopWindow("活动项目维护",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//评论管理
	function editBtnClickComment(aid,obj) {
		var url="<%=basePath%>work/commentManage/Comment.jsp?aid="+aid;
		easyui_openTopWindow("评论管理",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//附加项目
	function editBtnClickAddition(aid,obj) {
		var url="<%=basePath%>work/activityManage/additionProjectManage.jsp?aid="+aid;
		easyui_openTopWindow("附加项目维护",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//课程表
// 	function editBtnClickSchoolTimetable(aid,obj) {
<%-- 		var url="<%=basePath%>work/activityManage/schoolTimetable.jsp?aid="+aid; --%>
// 		easyui_openTopWindow("附加项目维护",1500,800,url,function(returnValue)
// 		{
// 			$('#dg').datagrid('reload');//重新加载页面。
// 		}); 
// 	}
	//已删除数据查询
		function searchRecover(aid,obj) {
		
		var url="<%=basePath%>work/activityManage/activityRecoverManage.jsp?aid="+aid;
		easyui_openTopWindow("已删除活动附件维护",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="活动条件查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>活动名称</span> <input id="name"></input>
			
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
		<table id="dg" class="easyui-datagrid" title="活动数据维护"
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
					<th id="editId" data-options="field:'operations',formatter:operationFmt,align:'center'">修改</th>
<!-- 					<th data-options="field:'aid',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">课程ID</th> -->
                    <th data-options="field:'name',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">活动名称</th>
					<th data-options="field:'subtitle',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动子标题</th>
					<th data-options="field:'day',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动用时</th>
					<th data-options="field:'age',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">适合年龄</th>
					<th data-options="field:'stick',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">是否置顶</th>
<!-- 					<th data-options="field:'logo',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">课程logoURL</th> -->
<!-- 					<th data-options="field:'official_url',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">课程官网URL</th> -->
<!-- 					<th data-options="field:'site',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">课程举办地址</th> -->
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
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="addActivity('<%=request.getParameter("cid") %>',this)">新增活动</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除活动</a>
			<a id="serDelBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-search',plain:true" onclick="searchRecover('',this)">查询已删除活动</a>
		</div>
	</div>
</body>
</html>
