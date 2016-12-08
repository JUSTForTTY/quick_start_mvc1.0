<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<title>信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/userManageAction.js'></script>
<script type="text/javascript"
	src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">


	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('reload');
	}	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.userId;


		if(row.userstatus=='1'){
			row.userstatus="未删除"
		}
		if(row.userstatus=='0'){
			row.userstatus="已删除"
		}
       if(row.usertype=='0'){
    	   row.usertype="超级管理员"
       }
       if(row.usertype=='1'){
    	   row.usertype="快车网管理员"
       }
       if(row.usertype=='2'){
    	   row.usertype="会员单位管理员"
       }
       if(row.usertype=='3'){
    	   row.usertype="社团管理员"
       }
       if(row.usertype=='4'){
    	   row.usertype="普通会员"
       }
       
		var idstring=id.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,this)' >修改</a>";
        return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["username"] = $.trim($('#username ').val());
		param["realname"] = $.trim($('#realname ').val());
		param["status"]=$("#status").combobox("getValue");
// 		param["loginId"] = $.trim($('#loginId ').val());
		param["providername"] = $.trim($('#providername ').val());
		param["serchUserType"]=$("#serchUserType").combobox("getValue"); 
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
			ids=ids+checkedRows[i]['userId']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
// 				alert(ids);
				userManageAction.deletes(ids, {
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
	//修改
	function editBtnClick(id) {
		//$.messager.alert('提示', '1');
		var url="<%=basePath%>work/User/subUserManage.jsp?id="+id;
			easyui_openTopWindow("修改/详情",1100,200,url,function(returnValue)
					{
						$('#dg').datagrid('reload');//重新加载页面。
					}); 
	}
	//新增
	function  addBtnClick(){		
		var url="<%=basePath%>work/User/subUserManage.jsp";
		easyui_openTopWindow("新增",1100,200,url,function(returnValue)
				{
					$('#dg').datagrid('reload');//重新加载页面。
				}); 
	}
	
	
	
	//导出
	function download() {
		var allparams={};
		var username=$("#username").val();	
		var encodeparam = encodeURI(username) ;
    location.href="/exportlog.do?username="+encodeparam+" ";
	}

</script>
<script type="text/javascript">
//判断用户类型
//'0'超级管理员;'1'快车网管理员;'2'会员单位管理;'3'社团管理员;'4'普通会员;
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
	$("#wuyeokadmin").hide();
	$("#provideradmin").hide();
	}else if(usertype==1){
  $("#superadmin").hide();	
  $("#provideradmin").hide();	
	}else {
  $("#superadmin").hide();
  $("#wuyeokadmin").hide();
	}	
});
</script>
</head>
<body class="easyui-layout">
		<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="用户条件查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>账号名:</span> <input id="username" style="width:100px;"></input>
			    <span>真实姓名:</span> <input id="realname" style="width:100px;"></input>
			    <span>公司单位名称:</span> <input id="providername"></input>  	  
			    <span>用户状态:</span> 
			    <select  id="status"   class="easyui-combobox" style="width:80px;">		       
						<option  value="1" selected>未删除</option>
						<option  value="0" >已删除</option>					
			    </select>
			    </div>
			    <div id="superadmin" class="box">	
			    <span>用户类型:</span> 
			    <select id="serchUserType" class="easyui-combobox" style="width:100px;">
					 <option value="" selected="selected">--全部--</option>
						<option  value="0">超级管理员</option>
						<option  value="1">快车网管理员</option>
						<option  value="2">会员单位管理员</option>		
						<option  value="3">社团管理员</option>
						<option  value="4">普通用户</option>
				</select>
			   </div>
			    <div id="wuyeokadmin" class="box">	
			    <span>用户类型:</span> 
			    <select id="serchUserType" class="easyui-combobox" style="width:100px;">
					 <option value="" selected="selected">--全部--</option>
						<option  value="1">快车网管理员</option>
						<option  value="2">会员单位管理员</option>		
						<option  value="3">社团管理员</option>
						<option  value="4">普通用户</option>
				</select>
			   </div>
			    <div id="provideradmin" class="box">	
			    <span>用户类型:</span> 
			    <select id="serchUserType" class="easyui-combobox" style="width:100px;">
					 <option value="" selected="selected">--全部--</option>	
						<option  value="2">系统管理员</option>		
						<option  value="3">社团管理员</option>
						<option  value="4">普通用户</option>
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
		<table id="dg" class="easyui-datagrid" title="user"
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
	                     url:userManageAction.getList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:80,align:'center'" >checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>			
					<th data-options="field:'userId',hidden:true,width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户id</th>
                    <th data-options="field:'username',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">帐号名</th>
					<th data-options="field:'realname',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">真实姓名</th>
					<th data-options="field:'gender',width:50,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">性别</th>		
					<th data-options="field:'usertype',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户类型</th>
					<th data-options="field:'userstatus',width:150,editor:{type:'validatebox',hidden:true,options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">状态</th>
					<th data-options="field:'provider_id',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">公司单位编号</th>
					<th data-options="field:'providername',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">公司单位名称</th>				
					<th data-options="field:'tel',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">手机</th>
					<!-- 				   <th data-options="field:'birthday',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">生日</th> -->
<!-- 					<th data-options="field:'createtime',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建时间</th>																				 -->
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px"> 
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick('',this)">新增</a> 
		    <a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick('0',this)">删除</a> 
<!-- 			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"  -->
<!-- 				data-options="iconCls:'icon-add',plain:true" onclick="delBtnClick('1',this)">恢复</a> 	 -->
	 <span id="downloadBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left:10px;"
			data-options="iconCls:'icon-sum',plain:true" onclick="download()">导出excel文件</span>
	
	
	
	</div> 
	</div>
</body>
</html>
