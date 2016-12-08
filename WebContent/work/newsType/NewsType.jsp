<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<title>新闻类别管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsTypeManageAction.js'></script>
<script type="text/javascript"
	src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	var para= easyui_getRequestPara();
	//var caid=para.caid;

	//alert("点击菜单传默认类别caid="+caid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.id;
			  
			 var statusType=row.status;
			 if(statusType==1){
				  row.status="已删除";
				  }   
			  if(statusType==0){
				  row.status="未删除";
			  } 
			  
			  var newsType=row.type;
				 if(newsType==0){
					  row.type="图片切换新闻";
					  }   
				  if(newsType==1){
					  row.type="下载新闻";
				  }
				  if(newsType==2){
					  row.type="综合资讯新闻";
				  } 
				  if(newsType==3){
					  row.type="快车网资讯新闻";
				  } 
				  if(newsType==4){
					  row.type="活动公告";
				  } 
				  if(newsType==5){
					  row.type="房产信息";
				  } 
				  if(newsType==6){
					  row.type="快捷服务新闻";
				  }  
				  if(newsType==7){
					  row.type="客服中心";
				  } 
			  
			  
			  
		var idstring=id.toString();
	//	var caidstring=caid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param["title"] = $.trim($('#title').val());
		param["status"]=$.trim($('#status').val());
		param["type"]=$.trim($('#type').val());
	}

	
	

	
	//删除记录
	function delBtnClick(flag){
		//删除记录
			var str="删除";
	
			var checkedRows=$('#dg').datagrid('getChecked');
			if(checkedRows.length==0){
				$.messager.alert('提示', '请选择要'+str+'的行。');
				return;
			}
			var ids="";
			$.each(checkedRows,function(i){
				ids=ids+checkedRows[i]['id']+",";
			});
			ids=ids.substring(0,ids.length-1);
			
			$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
				if (r) {
					newsTypeManageAction.deletedata(ids, {
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
	//nid和上面的nidstring, caid为caidstring传过来的值，obj是this,顺序不能乱
	function editBtnClick(id,obj) {
		
		// alert(id);
		// alert("你好，你的id到这了");
		var url="";
		 url = "<%=basePath%>work/newsType/addNewsType.jsp?id="+id;

	
			easyui_openTopWindow("新闻类别管理 ",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	
	
	
	
	
	
	
	//新增(传默认caid)
	function addBtnClick() {	
	    var url="";
		 url = "<%=basePath%>work/newsType/addNewsType.jsp";
		easyui_openTopWindow("新闻类别",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}

	
	//修改已删除的数据，恢复已删除的数据
	
	//function  
	

	//查询已删除的数据
	
	function searchDate(caid,obj){
		var url="<%=basePath%>work/newsManage/newsRecoveryManage.jsp?caid="+caid; 
		easyui_openTopWindow("内容管理",1500,640,url,function(returnValue)
				{
					$('#dg').datagrid('reload');
				}); 
			}
	
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 70px">
		<div id="search" class="easyui-panel" title="新闻类别"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>标题</span> <input id="title">
			</div>

			<div class="box">
				<span>是否删除</span> <select id="status">
					<option value="0" >未删除</option>
					<option value="1">已删除</option>
					<option value=" " selected="selected">全部</option>
				</select>
			</div>
			
			<div class="box">
				<span>新闻类型</span> <select id="type">
					<option value="0" selected="selected">图片切换新闻</option>
					<option value="1">下载新闻</option>
					<option value="2">综合资讯新闻</option>
					<option value="3">快车网资讯新闻</option>
					<option value="4">活动公告</option>
					<option value="5">房产信息</option>
					<option value="6">快捷服务</option>
					<option value="7">客服中心</option>
					<option value=" ">全部</option>
				</select>
			</div>
			
			
			
			<span class="aBtn"> <a id="searchBtn"
				href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 100px;" data-options="iconCls:'icon-search'"
				onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="新闻类别"
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
	                     url:newsTypeManageAction.getNewsTypeList">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<th
						data-options="field:'id',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">编号</th>
					<th
						data-options="field:'title',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">标题</th>
					<th
						data-options="field:'type',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">类型</th>
					<th
						data-options="field:'status',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">是否删除</th>

				</tr>

			</thead>

		</table>
	</div>
	<div id="add" style="height: 25px">
		<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addBtnClick()">新增</a>
		<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		<!-- 		<a id="redoBtn" href="javascript:void(0)" class="easyui-linkbutton" -->
		<!-- 			style="margin-left: 10px; display:" -->
		<!-- 			data-options="iconCls:'icon-redo',plain:true" -->
		<!-- 			onclick="delBtnClick(1)">恢复</a> -->

	</div>
</body>
</html>
