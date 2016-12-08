<%@ page language="java" import="java.util.*,com.tcj.domains.LoginEntity" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>账号管理</title>
    <%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/AccountsManageAction.js'></script>
	<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>
<script type="text/javascript">
	$(function(){
	//查找 用户类别
		AccountsManageAction.getUserType({},{
         	async : false,
			callback : function(data) {
				data.unshift({'VAL':'','TEXT':'全部'});
				$("#userTypeId").combobox("loadData",data);
			}	
         });
	});
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var uid=row.uid;
		var uid=uid.toString();
		var usertype=row.usertype;
	if(uid!=null&&uid!=""){		
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+uid+"&quot;,"+usertype+",this)' >详情/修改</a>";	
	}else  html+=" <a href='javascript:void(0)' onclick='addBtnClick(&quot;"+uid+"&quot;,"+usertype+",this)' >详情/修改</a>";
	  
	return html;
 }	
	//传递查询条件
	function onBeforeLoad(param) {
		param["username"] = $.trim($('#username ').val());
		param["realname"] = $.trim($('#realname ').val());
		param["usertype"] = $("#usertype").combobox("getValue");
		param["status"] = $("#status").combobox("getValue");

	}
	
	//falg 0.已删除，1.未删除
	function delBtnClick(flag){
		var gufalg=0;
		var str="";
		if(flag=="1"){
			str="删除";
		}else { str="恢复";	}
		
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0)
		{
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var uids="";
		var info = "";
		
		$.each(checkedRows,function(i){
			//获取点击的uid,进行判断
			//alert(checkedRows[i]["status"]);
			
	   if(gufalg==0&&flag==checkedRows[i]["status"]){
				info="0";
				gufalg=1;
				$.messager.alert('提示', '不能'+str+'未删除!');
				
				return;
			}else  uids=uids+checkedRows[i]['uid']+",";
		});
		
	if(info!="0"){
		uids=uids.substring(0,uids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				AccountsManageAction.deletes(flag,uids, {
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
}
	
	
	//falg 0.已删除，1.未删除
	function resetPwd(){
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0)
		{
			$.messager.alert('提示', '请选择要重置密码的行。');
			return;
		}
		var uids="";
		$.each(checkedRows,function(i){
         uids=uids+checkedRows[i]['uid']+",";
		});
		uids=uids.substring(0,uids.length-1);
		$.messager.confirm('确认', '确定要重置吗?', function(r) {
			if (r) {
				AccountsManageAction.resetPwd(uids, {
					async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('确认', '密码重置成功!');
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
	function editBtnClick(uid,userType) {
		var url="<%=basePath%>work/accountsManage/accountsDetail.jsp?uid="+uid+"&userType="+userType;
		easyui_openTopWindow("详情/修改",1100,600,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		});
	}
	
	
	//修改
	function addBtnClick() {
		var url="<%=basePath%>work/accountsManage/accountsSubManage.jsp";
		easyui_openTopWindow("新增",1100,600,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		});
	}

</script>
  </head>
  
  <body class="easyui-layout">
  	<div data-options="region:'north',split:false" style="height:70px">
    <div id="search" class="easyui-panel" title="用户信息管理" data-options="iconCls:'icon-search'">
		<div class="box">
			<span>账号名</span> <input id="username"></input>
			<span>真实姓名</span> <input id="realname"></input>
			<span>用户类别</span> 
			<select  id="usertype" name="usertype"  class="easyui-combobox" style="width:100px;">
			 <option value="">全部</option>
			 <option value="2">用户</option>
             <option value="0">管理员</option>
             <option value="1">超级管理员</option>
			</select>
			<span>状态</span> 
			<select  id="status" name="status"  class="easyui-combobox" style="width:100px;">
			 <option value="0"  selected >未删除</option>
			 <option value="1">已删除</option>
			 <option value="">全部</option>		 
			</select>
		</div>
		<span class="aBtn">
			<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="searchBtnClick()">查询</a>
		</span>
	</div>
	</div>
	<div class="clear"></div>
	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="用户信息管理"
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
	                     url:AccountsManageAction.getList">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:80,align:'center'" >checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:80,align:'center'">操作</th>			
					<th data-options="field:'uid',width:120,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center',hidden:true">用户编号</th>
					<th data-options="field:'usertype',width:160,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center',hidden:true">用户类型</th>
					<th data-options="field:'username',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户名</th>
					<th data-options="field:'realname',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">真实姓名</th>
					<th data-options="field:'f_userType',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户类型</th>
				    <th data-options="field:'e_status',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">状态</th>
					<th data-options="field:'mobile',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">手机号</th>																				
			        <th data-options="field:'email',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">电子邮箱</th>
				</tr>
			</thead>
		</table>
		<div id="add" style="height:25px">	
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick('',this)">新增</a>				
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-stop',plain:true" onclick="delBtnClick(1)">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-ok',plain:true" onclick="delBtnClick(0)">恢复</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-ok',plain:true" onclick="resetPwd()">重置密码</a>
		</div>
	</div>
  </body>
</html>
