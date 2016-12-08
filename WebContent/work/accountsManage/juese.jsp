<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>角色信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/jueseAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	$(function(){
	//查找 用户类别
		baseUserAction.getUserType({},{
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
		var userId=row.USER_ID;
		var orgName=row.ORG_NAME;
		if(orgName==null) orgName="";
		html += " <a href='javascript:void(0)' orgname='"+orgName+"' onclick='editBtnClick("+userId+",this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		//param["loginId"] = $.trim($('#loginId ').val());
		//param["userName"] = $.trim($('#userName ').val());
		//param["userTypeId"]=$("#userTypeId").combobox("getValue");
	}
	
	//falg 0，删除 1.冻结，2。解冻
	function delBtnClick(flag){
		var str="删除";
		var isLockedText="";
		if(flag=="1"){
			str="冻结";
			isLockedText="1";
		}else if(flag=="2"){
			str="解冻";
			isLockedText="0";
		}
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var userIds="";
		var userName="";
		$.each(checkedRows,function(i){
			userIds=userIds+checkedRows[i]['USER_ID']+",";
			if(isLockedText!=""&&checkedRows[i]['IS_LOCKED']==isLockedText){
				userName=userName+checkedRows[i]['USER_NAME']+",";
			}
		});
		if(userName.length>0){
			$.messager.confirm('提示',userName+"已"+str+"!请重新选择");
			return false;
		}
		userIds=userIds.substring(0,userIds.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				baseUserAction.updateBaseUserState(flag,userIds, {
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
	function editBtnClick(userId,obj) {
		var orgName="";
		if(userId!="") orgName=$(obj).attr("orgname");
		var url="<%=basePath%>work/accountsManage/subBaseUserInfo.jsp?userId="+userId+"&orgName="+orgName;
		easyui_openTopWindow("账号管理",820,310,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		});
			<%-- if(userId==""){
				var url="<%=basePath%>work/accountsManage/subBaseUserInfo.jsp?userId="+userId;
				easyui_openTopWindow("固定资产档案",820,510,url,function(returnValue)
				{
					if(returnValue!=""&&returnValue!==undefined&&returnValue!=null){
						if(returnValue.salvageRate!=null&&returnValue.salvageRate!="")
							returnValue.salvageRate=returnValue.salvageRate*100;
						$('#dg').datagrid('appendRow', returnValue);
					}
				});
			}else{
				var url="<%=basePath%>work/accountsManage/subBaseUserInfo.jsp?userId="+userId;
				easyui_openTopWindow("固定资产档案",820,510,url,function(returnValue)
					{
						if(returnValue!=""&&returnValue!==undefined&&returnValue!=null){
							if(returnValue.salvageRate!=null&&returnValue.salvageRate!="")
								returnValue.salvageRate=returnValue.salvageRate*100;
							$("#dg").datagrid("updateRow",{
									index:index,
									row:returnValue
							});
						}
					});
			} --%>
	}
	

</script>
</head>

<body class="easyui-layout">

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="角色信息管理"
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
	                     url:jueseAction.getJueses
	 ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>
					<th data-options="field:'USER_ID',hidden:true">userId</th>

				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClick('',this)">新增</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		</div>
	</div>
</body>
</html>
