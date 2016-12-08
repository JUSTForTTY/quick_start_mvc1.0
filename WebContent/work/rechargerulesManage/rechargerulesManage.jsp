<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>奖励规则管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/rechargerulesAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<script type="text/javascript">

	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.reid;
		var idstring=id.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+id+"&quot;,this)' >修改</a>";
		return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) { 
		param["amount"] = $.trim($('#amount ').val());
		param["reward"] = $.trim($('#reward ').val());
	<%-- 	param["is"] = <%=CommonDefine.ID_DELETE_FALSE%>;
		 param["loginId"] = $.trim($('#loginId ').val());
		param["userName"] = $.trim($('#userName ').val());
		param["userTypeId"]=$("#userTypeId").combobox("getValue");  --%>
	}
	
	//删除记录
	function delBtnClick(flag){
		var str="删除";
		
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var ids="";
		$.each(checkedRows,function(i){
			ids=ids+"'"+checkedRows[i]['reid']+"',";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				rechargerulesAction.deletes(ids, {
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
	function editBtnClick(id,obj) {
		var url="<%=basePath%>work/rechargerulesManage/rechargerulesSubManage.jsp?id="+id;
		easyui_openTopWindow("奖励规则管理",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	

</script>
</head>
<body>
<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="奖励规则管理"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>充值奖励条件</span> <input id="amount"></input>
			
			</div>
			<div class="box">
				<span>奖励天才币</span> <input id="reward"></input>
			
			</div>
			<span class="aBtn">
				<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
					style="margin-left:100px;" data-options="iconCls:'icon-search'"
					onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'" style="height:500px">
		<table id="dg" class="easyui-datagrid" title="代码列表"
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
	                     url:rechargerulesAction.getList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>			
					<th data-options="field:'amount',width:350,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">充值奖励条件</th>
                    <th data-options="field:'reward',width:350,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">奖励天才币</th>
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