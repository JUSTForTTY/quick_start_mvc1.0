<%@ page language="java" import="java.util.*,com.tcj.common.enums.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<title>新闻信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
<script type="text/javascript"
	src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	var para= easyui_getRequestPara();
	var caid=16;
	
	//alert("点击菜单传默认类别caid="+caid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	//图片缩放
	function AutoResizeImage(maxWidth,maxHeight,objImg){
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth ==0 && maxHeight==0){
	Ratio = 1;
	}else if (maxWidth==0){//
	if (hRatio<1) Ratio = hRatio;
	}else if (maxHeight==0){
	if (wRatio<1) Ratio = wRatio;
	}else if (wRatio<1 || hRatio<1){
	Ratio = (wRatio<=hRatio?wRatio:hRatio);
	}
	if (Ratio<1){
	w = w * Ratio;
	h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
	}
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var nid=row.nid;
		var caid=row.caid;
		var idstring=nid.toString();
		var caidstring=caid.toString();
	//	alert(caidstring);
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,&quot;"+caidstring+"&quot;,this)' >修改</a>";
		return html;
	}
	//格式化图片
	function operationFmtImg(val,row){
		var	html="";
		var nid=row.nid;
		var caid=row.caid;
		var idstring=nid.toString();
		var caidstring=caid.toString();
		html+="<img width='200px' height='50px' src=<%=basePath%>" + row.image + "  onload='AutoResizeImage(200,50,this)' alt='图片加载失败'/>";
		return html;
	}

	function onLoad(param) {
		param["caid"] = $.trim($('#caid').val());
	}

	//传递查询条件
	function onBeforeLoad(param) {
		param["title"] = $.trim($('#title').val());
		param["caid"] = $.trim($('#caid').val());//加载之前将caid传到后台
		//alert(param["caid"]);
	}
	//新增(传默认caid)
	function addBtnClick(caid,obj) {
		
	//	alert("点击新增传所属类别caid="+caid);
		 
		var url="<%=basePath%>work/newsManage/scrollnewsSubManage.jsp?caid="+caid;
		
		easyui_openTopWindow("滚动新闻管理",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 70px">
		<div id="search" class="easyui-panel" title="news"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>标题</span> <input id="title"></input> <input id="caid"
					type="hidden" value="<%=16%>"></input>
			</div>
			<span class="aBtn"> <a id="searchBtn"
				href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 100px;" data-options="iconCls:'icon-search'"
				onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	<div class="clear"></div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="news"
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
	                     url:newsManageAction.getNewsList">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<th
						data-options="field:'title',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">标题</th>
					<th
						data-options="field:'englishTitle',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">英文标题</th>
					<th
						data-options="field:'image',formatter:operationFmtImg,align:'center'">图片</th>
					<!--                     <th data-options="field:'content',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">内容</th> -->
					<!-- 				 <th data-options="field:'name',width:250,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">新闻名称</th> -->


				</tr>
			</thead>
		</table>
		<div id="add" style="height: 25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 10px;"
				data-options="iconCls:'icon-add',plain:true"
				onclick="addBtnClick('<%=16%>',this)">新增</a>
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		</div>
	</div>
</body>
</html>
