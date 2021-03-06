<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>活动项目数据维护</title>
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

//easyui接收jsp页面传值,点击修改传过来的aid
var para = easyui_getRequestPara();
var aid = para.aid;
// alert("aid="+aid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');//重新加载页面。
	}
	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";
		var aid=row.aid;
		var apid=row.apid;
//         alert("aid="+aid);
//         alert("apid="+apid);
		var aidstring=aid.toString();
		var apidstring=apid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+aidstring+"&quot;,&quot;"+apidstring+"&quot;,this)' >修改活动项目</a>"
// 		alert("ddd");
		return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["name"] = $.trim($('#name ').val());
        //查询前传值aid到后台，作为查询条件。
		var map = easyui_getRequestPara();
//  	    alert(map["aid"]);
        param["aid"] = map["aid"];
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
			ids=ids+checkedRows[i]['apid']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				activityManageAction.deletesProject(ids, {
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
	
	//课程项目修改.
	function editBtnClick(aid,apid,obj) {
// 		alert("aid"+aid);
// 		alert("apid"+apid);
		var url="<%=basePath%>work/activityManage/projectSubManage.jsp?aid="+aid+"&apid="+apid;
		easyui_openTopWindow("活动项目维护",550,330,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//课程项目新增
	function addBtnClick(aid,obj) {

		var url="<%=basePath%>work/activityManage/projectSubManage.jsp?aid="+aid;
		easyui_openTopWindow("活动项目维护",550,330,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	
	//已删除数据查询
	function searchRecover(aid,obj) {
	
	var url="<%=basePath%>work/activityManage/projectRecoverManage.jsp?aid="+aid;
	easyui_openTopWindow("已删除活动项目维护",1500,800,url,function(returnValue)
	{
		$('#dg').datagrid('reload');//重新加载页面。
	}); 
}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="活动项目查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>活动项目名称</span> <input id="name"></input>
			
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
		<table id="dg" class="easyui-datagrid" title="活动项目维护"
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
	                     url:activityManageAction.getProjectList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:100,align:'center'">修改</th>
<!-- 					<th data-options="field:'apid',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">课程项目ID</th> -->
                    <th data-options="field:'name',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">活动项目名称</th>
					<th data-options="field:'refprice',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动项目原价</th>
					<th data-options="field:'price',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动项目现价</th>
<!-- 					<th data-options="field:'project_num',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动项目数量</th> -->
<!-- 					<th data-options="field:'start_time',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动项目开始时间</th> -->
<!-- 					<th data-options="field:'end_time',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">活动项目结束时间</th> -->
					<th data-options="field:'create_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建人</th>
					<th data-options="field:'create_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建时间</th>
					<th data-options="field:'modify_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新人</th>
					<th data-options="field:'modify_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新时间</th>
				
					
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick('<%=request.getParameter("aid") %>',this)">新增活动项目</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除活动项目</a>
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-search',plain:true" onclick="searchRecover('<%=request.getParameter("aid") %>',this)">查询已删除活动项目</a>
		</div>
	</div>
</body>
</html>
