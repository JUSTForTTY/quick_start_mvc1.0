<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<title>用户类别</title>
		<%@ include file="../../inc.jsp"%>
		<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
		<script type="text/javascript" src='../../script/datePicker/WdatePicker.js'></script>
		<script type="text/javascript" src='<%=basePath%>/dwr/interface/setRoleAction.js'></script>
		<script type="text/javascript">
			var userTypeFlag=[{CODE_VALUE:'1',CODE_NAME:'平台人员'},{CODE_VALUE:'2',CODE_NAME:'政府办公'},{CODE_VALUE:'4',CODE_NAME:'物业人员'},{CODE_VALUE:'5',CODE_NAME:'商圈人员'},{CODE_VALUE:'0',CODE_NAME:'大众人员'}];
			
			function userTypeFlagFmt(value, row, index){
				switch(value)
				{
					case'0':return "大众人员";
					case'4':return "物业人员";
					case'5':return "商圈人员";
					case'1':return "平台人员";
					case'2':return "政府办公";
				}
			}
			
			function onBeforeLoad(param)
			{
				;
			}
			
			//新增
			function add() {
				$('#dg').datagrid('appendRow', {});
				var editIndex = $('#dg').datagrid('getRows').length - 1;
				$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',editIndex);
			}
			
			function delBtnClick(){
				var addDelete=0;
				var rows = $('#dg').datagrid('getRows');
				var checkedRows=$('#dg').datagrid('getChecked');
				if(checkedRows.length==0)
				{
					$.messager.alert('提示', '请选择要删除的行。');
					return;
				}
				$.each(checkedRows,function(){
					var index=$("#dg").datagrid('getRowIndex',this);
					var userTypeId = rows[index].USER_TYPE_ID;
					if(userTypeId==undefined||userTypeId==""){
						$("#dg").datagrid("deleteRow",index);
						addDelete++;
					}
				});
				if(addDelete==checkedRows.length)
					return;
				checkedRows=$('#dg').datagrid('getChecked');
				$.messager.confirm('确认', '确定要删除吗?', function(r) {
					if (r) {
						setRoleAction.delRole(checkedRows, {
							async : false,
							callback : function(data) {
								if (data.success) {
									$.each(checkedRows,function(){
										var index=$("#dg").datagrid('getRowIndex',this);
										$("#dg").datagrid("deleteRow",index);
									});
									$.messager.alert('确认', '删除成功');
								} else {
									$.messager.alert('警告', '删除失败');
								}
							}
						});
					}
				});
			}
			
			//修改
			function editBtnClick(obj) {
				var index=$("#dg").datagrid('getRowIndexByObj',obj);
				var rows=$('#dg').datagrid('getRows');
				var userTypeId=rows[index].USER_TYPE_ID;
				$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
					index);
				var _ed = $("#ed" + userTypeId);
				_ed.html("保存");
				_ed.removeAttr("onclick");
				_ed.attr("onclick", "updateBtnClick(this)");
			}
			
			//格式化操作列	
			function operationFmt(value, row, index) {
				var html = " ";
				var userTypeId=row.USER_TYPE_ID;
				if(userTypeId!=undefined&&userTypeId!=""){
					html += " <a id=ed" + userTypeId
							+ " href='javascript:void(0)' onclick='editBtnClick(this)' >修改</a>";
				}else {
					html += " <a href='javascript:void(0)' onclick='updateBtnClick(this)'>保存</a>";
				}
				return html;
			}
			
			function linkFmt(value, row, index) {
				var html = "";
				if(row.USER_IDS!=null&&row.USER_IDS!=""&&row.USER_IDS!=undefined){
					var USER_IDS=row.USER_IDS.split(',');
					var USERS=row.USERS.split(',');
					var ORG_NAMES=row.ORG_NAMES.split(',');
					for(var i in USER_IDS)
					{
						html+="<a href='javascript:void(0)' id='"+USER_IDS[i]+"' name='"+ORG_NAMES[i]+"' onclick='openSubBaseUserInfo(this)'>"+USERS[i]+"</a>,";
					}
					html=html.substring(0,html.length-1);
					return html;
				}else
					return value;
			}
			
			function openSubBaseUserInfo(obj){
				if(obj.name!="无")
					var url="<%=basePath%>work/accountsManage/subBaseUserInfo.jsp?userId="+obj.id+"&orgName="+obj.name+"&flag=1";
				else
					var url="<%=basePath%>work/accountsManage/subBaseUserInfo.jsp?userId="+obj.id+"&orgName=&flag=1";
				easyui_openTopWindow("账号管理",820,250,url);
			}
			function updateBtnClick(obj) {
				var index=$("#dg").datagrid('getRowIndexByObj',obj);
				var rows = $('#dg').datagrid('getRows');
				var userTypeId=rows[index].USER_TYPE_ID;
				if(userTypeId==undefined)
					userTypeId="";
				if ($('#dg').datagrid('validateRow', index)) {
					$('#dg').datagrid('endEdit', index);
					var map={userTypeId:userTypeId};
					setRoleAction.saveRole(rows[index],map, {
						async : false,
						callback : function(data) {
							if(data.success)
							{
								$("#dg").datagrid("updateRow",{
									index:index,
									row:{
										MODIFY_TIME:data.data.modifyTime,
										USER_TYPE_ID:data.data.userTypeId
									}
								});
							}else
							{
								$.messager.alert('警告', data.msg,'warning');
								$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
									index);
								var _ed = $("#ed"+userTypeId);
								_ed.html("保存");
								_ed.removeAttr("onclick");
								_ed.attr("onclick", "updateBtnClick(this)");
								return;
							}
						}
					});
				}
				else
				{
					$.messager.alert('警告','请将红框内的信息修改正确后再进行保存！','warning');
				}
			}
		</script>
	 </head>
  
	 <body class="easyui-layout">
	   	<table id="dg" class="easyui-datagrid" title="用户类别"
			data-options="
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     toolbar: '#add',
	                     pagination:true,
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     onBeforeLoad:onBeforeLoad,
	                     url:setRoleAction.getRole
	 ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<th data-options="field:'USER_TYPE_ID',hidden:true">USER_TYPE_ID</th>
					<th
						data-options="field:'USER_TYPE_NAME',editor:{type:'validatebox',options:{required:true,validType:'length[1,100]'}},width:220,halign:'center'">类别名称</th>
					<th data-options="field:'USER_IDS',hidden:true">USER_IDS</th>
					<th data-options="field:'ORG_NAMES',hidden:true">ORG_NAMES</th>
					<th data-options="field:'USERS',width:400,halign:'center',formatter:linkFmt">用户</th>
					<th
						data-options="field:'USER_TYPE_FLAG',formatter:userTypeFlagFmt,editor:{type:'combobox',options:{required:true,editable : false, data: userTypeFlag,
						              valueField: 'CODE_VALUE',
						              textField: 'CODE_NAME',panelHeight:'auto'}},width:90,align:'center'">人员类别</th>
					<th
						data-options="field:'MODIFY_TIME',width:100,align:'center'">修改时间</th>
				</tr>
			</thead>
		</table>
		<div id="add" style="height:25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick()">删除</a>
		</div>
	 </body>
</html>
