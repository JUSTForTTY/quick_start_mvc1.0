<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>账号管理</title>
    <%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/baseUserAction.js'></script>
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
		var serviceCenterName=row.SERVICE_CENTER_NAME;
		if(serviceCenterName==null) serviceCenterName="";
		html += " <a href='javascript:void(0)' serviceCenterName='"+serviceCenterName+"' onclick='editBtnClick("+userId+",this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param["loginId"] = $.trim($('#loginId ').val());
		param["userName"] = $.trim($('#userName ').val());
		param["userTypeId"]=$("#userTypeId").combobox("getValue");
	}
	
	//falg 0，删除 1.冻结，2.解冻,3.待租
	function delBtnClick(flag){
		var str="删除";
		var isLockedText="";
		if(flag=="1"){
			str="冻结";
			isLockedText="1";
		}else if(flag=="2"){
			str="解冻";
			isLockedText="0";
		}else if(flag=="3"){
			str="待租";
		}
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0)
		{
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
		var serviceCenterName=$(obj).attr("serviceCenterName");
		var url="<%=basePath%>work/accountsManage/subBaseUserInfo.jsp?userId="+userId+"&serviceCenterName="+serviceCenterName;
		easyui_openTopWindow("账号管理",820,400,url,function(returnValue)
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
			<span>账号</span> <input id="loginId"></input>
			<span>姓名</span> <input id="userName"></input>
			<span>用户类别</span> 
			<select  id="userTypeId" name="userTypeId"  class="easyui-combobox" style="width:100px;" data-options="editable:false,valueField: 'VAL',textField: 'TEXT'">
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
	                     url:baseUserAction.getBaseUserInfos
	 ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>
					<th data-options="field:'USER_ID',hidden:true">userId</th>
					<th data-options="field:'LOGIN_ID',align:'right',halign:'center',width:80">账号</th>
					<th data-options="field:'USER_NAME',align:'right',halign:'center',width:90">姓名</th>
					<th data-options="field:'USER_SEX_TEXT',align:'right',halign:'center',width:60">性别</th>
					<th data-options="field:'USER_TYPE_NAME',align:'right',halign:'center',width:100">用户类别</th>
					<th data-options="field:'ROLE_NAME',align:'right',halign:'center',width:100">角色</th>
					<th data-options="field:'CELL_TEL',align:'right',halign:'center',width:120">手机/电话</th>
					<th data-options="field:'ID_CARD_NO',align:'right',halign:'center',width:220">身份证号码</th>
					<th data-options="field:'DOMICILE_PLACE',align:'right',halign:'center',width:180">户籍地</th>
					<th data-options="field:'SERVICE_CENTER_NAME',align:'right',halign:'center',width:220">所属站点</th>
					<th data-options="field:'USER_EMAIL',align:'right',halign:'center',width:140">电子邮箱</th>
					<th data-options="field:'OTHER_CON',align:'right',halign:'center',width:140">其他联系方式</th>
					<th data-options="field:'POST_TEXT',align:'right',halign:'center',width:100">职务</th>
					<th data-options="field:'USER_BIRTHDAY',align:'center',width:100">生日</th>
					<th data-options="field:'LAST_LOGIN_TIME',align:'center',width:130">最近登录时间</th>
					<th data-options="field:'LOGIN_TIMES',align:'center',width:100">累计登录次数</th>
					<th data-options="field:'IS_LOCKED_TEXT',align:'center',width:100">是否冻结</th>
					<th data-options="field:'OCCUPATION_TEXT',align:'right',halign:'center',width:100">行业</th>
					<!-- <th data-options="field:'HOBBIES_TEXT',align:'left',halign:'center',width:100">兴趣</th> -->
				</tr>
			</thead>
		</table>
		<div id="add" style="height:25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClick('',this)">新增</a>
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-stop',plain:true" onclick="delBtnClick(1)">冻结</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-ok',plain:true" onclick="delBtnClick(2)">解冻</a>	
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-tip',plain:true" onclick="delBtnClick(3)">待租</a>	
		</div>
	</div>
  </body>
</html>
