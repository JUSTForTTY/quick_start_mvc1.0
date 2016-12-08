<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>新闻类别信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/newsTypeAction.js'></script>
<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">

$(function(){
	//查找新闻类型
	newsTypeAction.getCategoryType({},{
		async : false,//async 属性规定一旦脚本可用，则会异步执行。
		callback : function(data){//回调函数
			data.unshift({'VAL':'','TEXT':'-请选择-'});
		    $("#type").combobox("loadData",data);
		}
	});
});

$(function(){
	//查找新闻类别父类别
	newsTypeAction.getParentName({},{
		async : false,//async 属性规定一旦脚本可用，则会异步执行。
		callback : function(data){//回调函数
			data.unshift({'VAL':'','TEXT':'-请选择-'});
		    $("#parent_name").combobox("loadData",data);
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
		var id=row.caid.toString();//row.caid为整形需要转换为字符串类型
// 		&quot在html里是“"”，用法：&quot;
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+id+"&quot;,this)' >修改</a>";
		return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["isDelete"] = $("#isDelete").combobox("getValue"); 
 		param["id"] = $.trim($('#id').val());
 		param["type"]=$("#type").combobox("getValue"); 
 		param["name"] = $.trim($('#name').val());
 		param["onHome"] =$("#onHome").combobox("getValue"); 
 		param["parent_name"] =$("#parent_name").combobox("getValue"); 
	}
	
	//删除记录
	function delBtnClick(flag){
		var map={};
		map['flag']=flag;
		var str;
		if(flag=="1"){
			 str="删除";
		}else if(flag=="0"){
			 str="恢复";
		}
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var info="";
		$.each(checkedRows,function(i){
			if(checkedRows[i]['is_delete']==flag){
				info='您勾选了已'+str+'的数据，无需'+str+"!";
				return;
			}
		});
		if(info!="") $.messager.alert('提示', info);
		else if(info==""){
		var ids="";
		$.each(checkedRows,function(i){
			ids=ids+checkedRows[i]['caid']+",";
		});
		ids=ids.substring(0,ids.length-1);
		map['ids']=ids;
// 		alert(map.ids+" "+map.flag);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				newsTypeAction.deletes(map,{
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
	//修改
	function editBtnClick(id,obj) {
		var url="<%=basePath%>work/newsTypeManage/subNewsType.jsp?id="+id;
		easyui_openTopWindow("新闻类别管理",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	
	
// 	$(document).ready(function () {
// 		$("#isDelete").combobox({
// 			$("#redoBtn").style.display = "";
// 			});
// 		});
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="新闻类别信息查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
<!-- 			   &nbsp;&nbsp; -->
			   <span>编号</span> <input id="id" style="width:100px"></input>
			   <span>新闻类型</span>
<!--            ComboBox（下拉列表框），valueField将基础数据值名称绑定到该下拉列表框，textField将基础数据字段名称绑定到该下拉列表框。 -->
            <select id="type" name="type" class="easyui-combobox" style="width: 100px;margin-top: -5px" data-options="editable:false,valueField:'VAL',textField:'TEXT'"></select>
			<span>所属父类别</span>
            <select id="parent_name" name="parent_name" class="easyui-combobox" style="width: 100px;margin-top: -5px" data-options="editable:false,valueField:'VAL',textField:'TEXT'"></select>
			 <span>新闻类别名称</span> <input id="name" style="width:120px"></input>
<!-- 			 panelHeight用来控制下拉框的高度 -->
			 <span>首页是否显示</span> <select id="onHome" name="onHome" class="easyui-combobox" 
			   style="width: 100px;margin-top: -5px" data-options="editable:false,valueField:'VAL',textField:'TEXT'"  panelHeight="70">
			  <option value="" selected="selected">-请选择-</option>
			  <option value="1">是</option>
			  <option value="0">否</option>
			</select>
			 <span>状态</span> <select id="isDelete" name="isDelete" class="easyui-combobox" 
			   style="width: 100px;margin-top: -5px" data-options="editable:false,valueField:'VAL',textField:'TEXT'" panelHeight="70"
			   onclick="select()">
			  <option selected="selected" value="0">未删除</option>
			  <option value="1">已删除</option>
			  <option value="2">-全部-</option>
			</select>
			</div>
			<span class="aBtn">
				<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'"
					onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="新闻类别信息管理"
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
	                     url:newsTypeAction.getList,
	                     rowStyler:function(index,row){  
                         if (index%2==0){     
                        <!-- return 'background-color:pink;color:blue;font-weight:bold;';       -->
                        return 'background-color:#E0ECFF;';
        }     
    }     
	                     ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>			
					<th data-options="field:'caid',width:80,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">编号</th>
					<th data-options="field:'name',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">名称</th>
					<th data-options="field:'building_name',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">楼宇名称</th>
                    <th data-options="field:'CodeName',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">新闻类型</th>
					<th data-options="field:'parent_name',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">父类别</th>
<!-- 					<th data-options="field:'sort',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">排序</th> -->
					<th data-options="field:'description',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">描述</th>
					<th data-options="field:'onHome',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">首页是否显示</th>
<!-- 					<th data-options="field:'onHomeType',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">首页显示规则</th> -->
<!-- 					<th data-options="field:'grade',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">级别</th> -->
<!-- 					<th data-options="field:'url',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">引用地址</th> -->
<!-- 					<th data-options="field:'icon',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">图标</th> -->
					<th data-options="field:'create_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">创建人</th>
					<th data-options="field:'create_time',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">创建时间</th>
					<th data-options="field:'modify_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">更新人</th>
					<th data-options="field:'modify_time',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">更新时间</th>
					<th data-options="field:'is_delete',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center',hidden:'hidden'">是否删除</th>
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClick('',this)">新增</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(1)">删除</a>
			<a id="redoBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;display: "
				data-options="iconCls:'icon-redo',plain:true" onclick="delBtnClick(0)">恢复</a>
		</div>
	</div>
</body>
</html>
