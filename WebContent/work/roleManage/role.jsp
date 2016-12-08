<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>角色设置</title>
    <%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='../../script/datePicker/WdatePicker.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/roleAction.js'></script>
	<script type="text/javascript">
		//格式化操作列	
		function operationFmt(value, row, index) {
			var html = " ";
			var roleId=row.ROLE_ID;
			var isDefaultRole=row.IS_DEFAULT_ROLE;
			if(roleId!=undefined&&roleId!=""){
				if(isDefaultRole!=1)
				{
					html += " <a id=ed" + roleId
							+ " href='javascript:void(0)' onclick='editBtnClick(this)' >修改</a>";
					html += " <a href='javascript:void(0)' onclick='delBtnClick(this)'>删除</a>";
				}else{
					html += "<a style='color:gray' href='javascript:void(0)'>修改</a>";
					html += " <a style='color:gray' href='javascript:void(0)' >删除</a>";
				}
			}else{
				html += " <a href='javascript:void(0)' onclick='updateBtnClick(this)'>保存</a>";
				html += " <a style='color:gray' href='javascript:void(0)' >删除</a>";
			}
			return html;
		}
		
		function delBtnClick(obj){
			var index=$("#dg").datagrid('getRowIndexByObj',obj);
			var rows = $('#dg').datagrid('getRows');
			var roleId = rows[index].ROLE_ID;
			if(roleId==undefined){
				$("#dg").datagrid("deleteRow",index);
				return;
			}
			$.messager.confirm('确认', '确定要删除吗?', function(r) {
				if (r) {
					roleAction.delRole(roleId, {
						async : false,
						callback : function(data) {
							if (data.success) {
								$.messager.alert('确认', '删除成功');
								$("#dg").datagrid("deleteRow",index);
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
			var roleId=rows[index].ROLE_ID;
			$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
				index);
			var _ed = $("#ed" + roleId);
			_ed.html("保存");
			_ed.removeAttr("onclick");
			_ed.attr("onclick", "updateBtnClick(this)");
			//$(obj).closest("tr").find("td").eq(3).find("input").attr("maxLength","100");
		}
		
		function onBeforeLoad(param)
		{
			;
		}
		
		//新增
		function append() {
			$('#dg').datagrid('appendRow', {});
			var editIndex = $('#dg').datagrid('getRows').length - 1;
			$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',editIndex);
		}
		
		function updateBtnClick(obj) {
			var index=$("#dg").datagrid('getRowIndexByObj',obj);
			var rows = $('#dg').datagrid('getRows');
			var roleId=rows[index].ROLE_ID;
			if(roleId==undefined)
				roleId="";
			if ($('#dg').datagrid('validateRow', index)) {
				$('#dg').datagrid('endEdit', index);
				var map={roleId:roleId};
				roleAction.saveRole(rows[index],map, {
					async : false,
					callback : function(data) {
						if(data.success)
						{
							$("#dg").datagrid("updateRow",{
								index:index,
								row:{
									ROLE_ID:data.data.roleId
								}
							});
						}else
						{
							$.messager.alert('警告', data.msg,'warning');
							$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
								index);
							var _ed = $("#ed"+roleId);
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
    <table id="dg" class="easyui-datagrid" title="角色设置"
			data-options="
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     toolbar: '#tb',
	                     pagination:true,
	                     singleSelect:true,
	                     url:roleAction.getRole,
	                     onBeforeLoad:onBeforeLoad
	 ">
			<thead>
				<tr>
					<th data-options="field:'ROLE_ID',hidden:true">roleId</th>
					<th data-options="field:'IS_DEFAULT_ROLE',hidden:true">IS_DEFAULT_ROLE</th>
					<th
						data-options="field:'ROLE_NAME',editor:{type:'validatebox',options:{required:true,validType:'length[1,100]'}},width:220,halign:'center'">角色名</th>
					<th
						data-options="field:'MEMO',editor:{type:'validatebox',options:{validType:'length[0,200]'}},width:270,halign:'center'">备注</th>
					 <th data-options="field:'USERS',width:400,halign:'center'">操作人员</th>
                     <th data-options="field:'operations',align:'center',formatter:operationFmt,width:80">操作</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="height:25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
		</div>
  </body>
</html>
