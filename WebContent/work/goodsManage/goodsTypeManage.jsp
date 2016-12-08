<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>商品类别管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/goodsTypeManageAction.js'></script>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">


	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');//重新加载页面。
	}
	
	//格式化操作列(每次必加载...)
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.id;

       if(row.isopen==1){
    	   row.isopen="显示"
       }else{
    	   row.isopen="不显示"
       }
       
		var idstring=id.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClickGoodsType(&quot;"+idstring+"&quot;,this)' >一级类别修改</a>";
		html += " <a href='javascript:void(0)' onclick='editBtnClickSecondGoodsType(&quot;"+idstring+"&quot;,this)' >查看二级类别</a>";
        return html;
	}
	
	//传递查询条件
	function onBeforeLoad(param) {
		param["name"] = $.trim($('#name').val());
      
	}
	
	//删除记录
	function delBtnClick(flag){
		var str="删除";
		var checkedRows=$('#dg').datagrid('getChecked');//校验是否选中要删除的记录。
		if(checkedRows.length==0){
			$.messager.alert('提示', '请选择要'+str+'的行。');
			return;
		}
		var ids="";
		$.each(checkedRows,function(i){//遍历被选中的记录id。
			ids=ids+checkedRows[i]['id']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				goodsTypeManageAction.deletes(ids, {
					async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('确认', str+'成功!');
							$('#dg').datagrid('reload');//重新加载页面。
						} else {
							$.messager.alert('警告', str+'失败!');
						}
					}
				});
			}
		});
	}
	
	//查询商品二级类别
	function editBtnClickSecondGoodsType(id,obj) {
		var url="<%=basePath%>work/goodsManage/goodsSecondTypeManage.jsp?id="+id;
		easyui_openTopWindow("商品二级类别数据维护",1300,1500,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	//商品一级类别新增or修改
	function editBtnClickGoodsType(id,obj) {
		var url="<%=basePath%>work/goodsManage/goodsTypeSubManage.jsp?id="+id;
		easyui_openTopWindow("商品一级类别数据维护",980,270,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}
	
	//已删除数据查询
		function searchRecover(id,obj) {
		
		var url="<%=basePath%>work/goodsManage/goodsTypeRecoverManage.jsp?id="+id;
		easyui_openTopWindow("已删除商品一级类别维护",1500,800,url,function(returnValue)
		{
			$('#dg').datagrid('reload');//重新加载页面。
		}); 
	}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height:70px">
		<div id="search"
			class="easyui-panel" title="商品一级类别条件查询"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>一级类别名称</span> <input id="name"></input>
			
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
		<table id="dg" class="easyui-datagrid" title="商品类别数据维护"
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
	                     url:goodsTypeManageAction.getList ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th id="editId" data-options="field:'operations',formatter:operationFmt,align:'center'">操作</th>
                    <th data-options="field:'name',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">一级类别名称</th>
                    <th data-options="field:'remark',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">类别标记</th>
                    <th data-options="field:'description',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">类别描述</th>
                    <th data-options="field:'sort',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">类别排序</th>
                    <th data-options="field:'image',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">类别logo</th>
                    <th data-options="field:'url',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">类别url</th>
                    <th data-options="field:'isopen',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">否显示类别</th>
                    <th data-options="field:'tag1',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">tag1</th>
                    <th data-options="field:'tag2',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">tag2</th>
                    <th data-options="field:'tag3',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">tag3</th>
                    <th data-options="field:'tag4',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">tag4</th>
                    <th data-options="field:'tag5',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">tag5</th>            
					<th data-options="field:'create_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建人</th>
					<th data-options="field:'create_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">创建时间</th>
					<th data-options="field:'modify_user',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新人</th>
					<th data-options="field:'modify_time',width:140,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">更新时间</th>
				
					
				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;" 
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClickGoodsType('',this)">新增一级类别</a> 
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除一级类别</a>
			<a id="serDelBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 10px;"
				data-options="iconCls:'icon-search',plain:true" onclick="searchRecover('',this)">已删除一级类别</a>
		</div>
	</div>
</body>
</html>
