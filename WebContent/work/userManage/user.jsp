<%@ page language="java" import="java.util.*,com.tcj.common.enums.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>User信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/userDataManage.js'></script>
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
		var uid=row.uid.toString();
	if(uid!=undefined&&uid!=""){	
		 html +=" <a  href='javascript:void(0)' onclick='editBtnClick(&quot;"+uid+"&quot;,this)' >修改/详情</a>";
		 return html;
	  }
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["username"] = $.trim($('#username ').val());
		param["realname"] = $.trim($('#realname ').val());
		param["status"]=$("#status").combobox("getValue");
		/* param["loginId"] = $.trim($('#loginId ').val());
		param["userName"] = $.trim($('#userName ').val());
		param["userTypeId"]=$("#userTypeId").combobox("getValue"); */
	}
	
	//删除记录
	function delBtnClick(flag){
		var str=" ";
		if(flag==<%=EnumUserStatus.STATUS_NO.getCode()%>){
			str="恢复";			
		}else  str="删除",flag=<%=EnumUserStatus.STATUS_YES.getCode()%>;
		
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var uids="";
		$.each(checkedRows,function(i){
			uids=uids+"'"+checkedRows[i]['uid']+"',";
		});
		uids=uids.substring(0,uids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				userDataManage.deletes(uids,flag, {
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
	function editBtnClick(uid) {
		//$.messager.alert('提示', '1');
		var url="<%=basePath%>work/userManage/UserDetail.jsp?uid="+uid;
		easyui_openTopWindow("修改/详情",1100,800,url,function(result){
			if(result){
				$("#dg").datagrid('load');
			}
		}); 
	}
	//新增
	function  addBtnClick(){		
		var url="<%=basePath%>work/userManage/addUser.jsp";
		easyui_openTopWindow("新增",1100,800,url,function(result){
			if(result){
				$("#dg").datagrid('load');
			}
		}); 
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="user"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>账号名:</span> <input id="username"></input>
			    <span>真实名:</span> <input id="realname"></input>
			 <select  id="status" name="status"  class="easyui-combobox" style="width:100px;">
				<option value="<%=EnumUserStatus.STATUS_YES.getCode()%>">已删除</option>
				<option value="<%=EnumUserStatus.STATUS_NO.getCode()%>" selected="selected">未删除</option>
				<option value="2">全部</option>
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
	                     url:userDataManage.getList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:80,align:'center'" >checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:80,align:'center'">修改</th>			
					<th data-options="field:'uid',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户编号</th>
                    <th data-options="field:'g_building_name',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">楼宇</th>
					<th data-options="field:'username',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户名</th>
					<th data-options="field:'realname',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">真实姓名</th>
					<th data-options="field:'f_userType',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">用户类型</th>
				   <th data-options="field:'e_status',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">状态</th>
					<th data-options="field:'mobile',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">手机号</th>																				
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick('',this)">新增</a> 
		    <a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick('0',this)">删除</a> 
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="delBtnClick('1',this)">恢复</a> 	
		</div>
	</div>
</body>
</html>
