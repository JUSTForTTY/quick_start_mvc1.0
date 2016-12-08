<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>角色权限</title>
	<%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='../../script/datePicker/WdatePicker.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/roleMenuAction.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/roleAction.js'></script>
	<script type="text/javascript">
		function onSelect(rowIndex, rowData){
	        var checked=$("#tr").tree('getChecked');
	        for(var i=0;i<checked.length;i++){
	            var node = $("#tr").tree('find',checked[i]['id']);
	            $("#tr").tree('uncheck', node.target);
	        }
	        roleMenuAction.getPermission(rowData.ROLE_ID,{
	            async : false,
				callback : function(data){
					for(var i=0; i<data.length;i++){
						var node = $("#tr").tree('find',data[i]['functionId']);
						if(node.children==null){
							$("#tr").tree('check', node.target);
						}
					} 
				}
	       	});
	    }
	    
	    function onBeforeLoad(param){
	    	;
	    }
	    
	    function saveBtnClick(){
	        var row=$("#dg").datagrid('getSelected');
	        var checked=$("#tr").tree('getChecked',['checked','indeterminate']);
	        var menuIds=new Array(checked.length);
	        for(var i=0;i<checked.length;i++){
	            menuIds[i]=checked[i]['id'];
	        }
	        if(row==null){
	            $.messager.alert('确认', '请选择角色');
	            return;
	        }
	        roleMenuAction.saveRoleMenu(row,menuIds,{
	            async : false,
				callback : function(data){
				      if(data.success){
				          $.messager.alert('确认', '保存成功');
					  } else {
						  $.messager.alert('警告', '保存失败');
					  }
			     }
	        });
	    }
	</script>
  </head>
  
  <body class="easyui-layout">
    <div id="save" data-options="region:'north'"
		style="height:80px;padding:10px;line-height:16px;"
		class="easyui-panel" title="角色权限"
		data-options="iconCls:'icon-save'">
		<div style="float:right">
		       <a id="searchBtn" href="javascript:void(0)"
				class="easyui-linkbutton" style="margin-right:60px;"
				data-options="iconCls:'icon-save'" onclick="saveBtnClick()">保存</a>
		</div>
	</div>
	<div data-options="region:'center'">
        <table id="dg" class="easyui-datagrid" title="角色:"
			data-options="
			iconCls: 'icon-edit',
			fit:true,
               onBeforeLoad:onBeforeLoad,
               onSelect: onSelect,
           	singleSelect:true,
           	url:roleAction.getRole,
			rownumbers:true,
			pagination:true ">
			<thead>
				<tr>
					<th data-options="field:'ROLE_ID',hidden:true">roleId</th>
					<th data-options="field:'check',checkbox:true"></th> 
					<th data-options="field:'ROLE_NAME',halign:'center',width:100">角色名称</th>
					<th
						data-options="field:'USERS',width:300,align:'center'">操作人员</th>
				</tr>
			</thead>
	    </table>
    </div>
    <div data-options="region:'east'" style="width:500px;">
        <div id="menu" class="easyui-panel" title="菜单权限"
			data-options="iconCls:'icon-edit',fit:true">
		     <ul id="tr" class="easyui-tree" data-options="checkbox:true,url:roleMenuAction.getMenuList"> 
		     </ul>
	     </div>
	</div>
  </body>
</html>
