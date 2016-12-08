<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>用户角色</title>
    <%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='../../script/datePicker/WdatePicker.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/personRoleAction.js'></script>
	<style type="text/css">
		.box{
			 	float: left;
			}
	</style>
	<script type="text/javascript">
		var userTypeFlag = [ {
			VALUE : '',
			KEY : '全部'
		}, {
			VALUE : '0',
			KEY : '大众人员'
		}, {
			VALUE : '1',
			KEY : '平台人员'
		}];
		var role=[];
		
		function onDgBeforeLoad(param){
	   	   param['userName']=$.trim($('#userName').val());
	   	   param["userTypeFlag"] = $("#userTypeFlag").combobox('getValue');
	   	   personRoleAction.getRoleList({
	   	   		async : false,
				callback : function(data){
				    role=data;
				}
	       });
        }
        
        function userTypeFlagfmt(value, row, index){
			switch(value)
			{
				case'0':return "大众人员";
				case'1':return "平台人员";
			}
			return "无";
		}
		
		function userTypeNamefmt(value, row, index){
			if(value==null)
				return "无";
			return value;
		}
        
        function rolefmt(value,row,index){   //角色format
             for(var i=0;i<role.length;i++)
                 if(role[i].VALUE==value)
                     return role[i].KEY;
             if(value==null){
                 return "无";
             }
        }
        
        function sarBtnClick(){   //查询按钮
             $('#dg').datagrid('reload');
        }
        
        function operationfmt(value, row, index) {   //操作format
        	var html = " ";
        	var userId=row.USER_ID;
            html="<a id=ed"+userId+" href='javascript:void(0)' onclick='editBtnClick(this)' >修改</a>";
    		return html;
        }
        
        function editBtnClick(obj){   //编辑按钮
        	var index=$("#dg").datagrid('getRowIndexByObj',obj);
			var rows=$('#dg').datagrid('getRows');
			var userId=rows[index].USER_ID;
            $('#dg').datagrid('selectRow', index).datagrid('beginEdit',index);
			var _ed = $("#ed" + userId);
			_ed.html("保存");
			_ed.removeAttr("onclick");
			_ed.attr("onclick", "updateBtnClick(this)");
        }
        
        function updateBtnClick(obj){   //保存按钮
            var index=$("#dg").datagrid('getRowIndexByObj',obj);
			$('#dg').datagrid('endEdit', index);
			var rows = $('#dg').datagrid('getRows');
			var roleId=rows[index].ROLE_ID;
			var userId=rows[index].USER_ID;
			if(roleId==undefined||roleId==null)
				;
			else{
				var map={userId:userId,roleId:roleId};
				personRoleAction.savePersonRole(map, {
					async : false,
					callback : function(data) {
						if(!data.success){
							$.messager.alert('警告', data.msg,'warning');
							$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
								index);
							var _ed = $("#ed"+userId);
							_ed.html("保存");
							_ed.removeAttr("onclick");
							_ed.attr("onclick", "updateBtnClick(this)");
						}
					}
				});
			}
        }
	</script>	
  </head>
  
  <body class="easyui-layout">
    <div id="search" data-options="region:'north'" style="height:75px;padding:8px;line-height:10px;"
		class="easyui-panel" title="用户角色" data-options="iconCls:'icon-search'">
		<div class="box">
			<span>用户姓名</span> <input id="userName" name="userName"></input>
			<span>用户类别区分</span> <input id="userTypeFlag" type="text" class="easyui-combobox" data-options="editable : false,valueField: 'VALUE',textField: 'KEY',data: userTypeFlag,panelHeight:'auto'" style="width:100px"></input>
		</div>
		<span class="aBtn">
			<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left:100px;"
				data-options="iconCls:'icon-search'" onclick="sarBtnClick()">查询</a>
		</span>
	</div>

	<div data-options="region:'center'" style="">
		<table id="dg" class="easyui-datagrid" title="用户角色"
			data-options="  
			   fit:true,
               iconCls: 'icon-edit',   
               checkOnSelect:true,
               rownumbers:true,
               pagination:true,
               onBeforeLoad:onDgBeforeLoad,
               url:personRoleAction.getPersonRole
           ">
			<thead>
				<tr>
					<th data-options="field:'USER_ID',hidden:true">USER_ID</th>
					<th data-options="field:'LOGIN_ID',halign:'center',width:100">用户账号</th>
					<th data-options="field:'USER_NAME',halign:'center',width:100">用户姓名</th>
					<th data-options="field:'USER_TYPE_ID',hidden:true">USER_TYPE_ID</th>
					<th data-options="field:'USER_TYPE_NAME',formatter:userTypeNamefmt,halign:'center',width:150">用户类别名称</th>
					<th data-options="field:'USER_TYPE_FLAG',formatter:userTypeFlagfmt,halign:'center',width:150">用户类别区分</th>
					<th data-options="field:'ROLE_ID',editor:{type:'combobox',options:{panelHeight:'auto',editable : false, 
               			valueField: 'VALUE',
               			textField: 'KEY',
               			url:personRoleAction.getRoleList,method:'get'}},width:120,formatter:rolefmt,halign:'center'">角色</th>
					<th data-options="field:'operations',align:'center',formatter:operationfmt,width:50">操作</th>
				</tr>
			</thead>
		</table>
	</div>
  </body>
</html>
