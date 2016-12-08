<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<title>新闻信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsAction.js'></script>
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
			
			//  alert(usertype+"用户类型");
			  var newsType = row.type;
			
					if (newsType == 0) {
						row.type = "图片切换新闻";
					}
					if (newsType == 2) {
						row.type = "综合资讯（通知）";
					}
					if (newsType == 3) {
						row.type = "快车网资讯新闻";
					}
					if (newsType == 5) {
						row.type = "房产信息";
					}
					if (newsType == 6) {
						row.type = "快捷服务新闻";
					}
					if (newsType == 7) {
						row.type = "客服中心";
					}
					if (newsType == 8) {
						row.type = "提供新闻";
					}
					if (newsType == 9) {
						row.type = "需求新闻";
					}
					if (newsType == 10) {
						row.type = "精彩回顾";
					}
					if (newsType == 11) {
						row.type = "企业公告";
					}
					if (newsType == 12) {
						row.type = "企业咨询";
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
		param["name"]=$.trim($('#name').val());
	}

	
	

	
	// 删除
	function delBtnClick() {
		//判断是否为新增的
		var addDelete=0;
		var ids="0";
		var checkedRows=$('#dg').datagrid('getChecked');
		if(checkedRows.length==0)
		{
			$.messager.alert('提示', '请选择要删除的行。');
			return;
		}
		$.each(checkedRows,function(i){
			var index=$("#dg").datagrid('getRowIndex',this);
			var id = checkedRows[i].id;
			if(id==undefined||id==""){
				$("#dg").datagrid("deleteRow",index);
				addDelete++;
			}else{
				ids=ids+","+id;
			}
		});
		if(addDelete==checkedRows.length)
			return;
		//选中的行去除 新增的
		checkedRows=$('#dg').datagrid('getChecked');
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+checkedRows.length+"</font>条数据吗？", function(r) {
			if (r) {
				newsAction.deleteNews(ids, {
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
	//nid和上面的nidstring, caid为caidstring传过来的值，obj是this,顺序不能乱
	function editBtnClick(id,obj) {
		
		// alert(id);
		// alert("你好，你的id到这了");
		var url="";
		 url = "<%=basePath%>work/news/breakingaddUpdate.jsp?id="+id;

	
			easyui_openTopWindow("大事记 ",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	
	
	
	
	
	
	
	//新增(传默认caid)
	function addBtnClick() {	
	    var url="";
		 url = "<%=basePath%>work/news/breakingaddUpdate.jsp";
		easyui_openTopWindow("大事记",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}

	
	//修改已删除的数据，恢复已删除的数据
	
	//function  
	

	//查询已删除的数据
	
	function searchDate(caid,obj){
		var url="<%=basePath%>work/newsManage/newsRecoveryManage.jsp?caid="+ caid;
		easyui_openTopWindow("内容管理", 1500, 640, url, function(returnValue) {
			$('#dg').datagrid('reload');
		});
	}
</script>

<script type="text/javascript">
	var usertype = $
	{
		LogInDemoEntity.userTypeFlag
	};
	$(document).ready(function() {
		if (usertype == 0) {
			$("#type").show();
		} else if (usertype == 1) {

			$("#tigong").hide();
			$("#xuqiu").hide();
			$("#jingcai").hide();
			$("#qiye").hide();
			$("#zixun").hide();
		} else {
			$("#xiazai").hide();
			$("#zonghe").hide();
			$("#kuaiche").hide();
			$("#huodong").hide();
			$("#fangchan").hide();
			$("#kuaijie").hide();
			$("#kefu").hide();

		}
	});
</script>




</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 70px">
		<div id="search" class="easyui-panel" title="新闻管理"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>大事记标题</span> <input id="title">
			</div>
			<div class="box">
				<span>会员单位名称</span> <input id="pname">
			</div>
<!-- 			<div class="box"> -->
<!-- 				<span>新闻类型</span> <select id="type"> -->
<!-- 					<option value=" " selected="selected">全部</option> -->
<!-- 					<option value="0" id="qiehuan">图片切换新闻</option> -->
<!-- 					<option value="2" id="zonghe">综合资讯（通知）</option> -->
<!-- 					<option value="3" id="kuaiche">快车网资讯新闻</option> -->
<!-- 					<option value="5" id="fangchan">房产信息</option> -->
<!-- 					<option value="6" id="kuaijie">快捷服务</option> -->
<!-- 					<option value="7" id="kefu">客服中心</option> -->
<!-- 					<option value="8" id="tigong">提供新闻</option> -->
<!-- 					<option value="9" id="xuqiu">需求新闻</option> -->
<!-- 					<option value="10" id="jingcai">精彩回顾</option> -->
<!-- 					<option value="11" id="qiye">企业公告</option> -->
<!-- 					<option value="12" id="zixun">企业咨询</option> -->
<!-- 				</select> -->
<!-- 			</div> -->
			<span class="aBtn"> <a id="searchBtn"
				href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 100px;" data-options="iconCls:'icon-search'"
				onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>
	</div>


	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="新闻管理"
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
	                     url:newsAction.breakingNews">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<!-- 					<th -->
					<!-- 						data-options="field:'id',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">编号</th> -->



					<th
						data-options="field:'title',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">大事记标题</th>

					<th
						data-options="field:'pid',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">会员单位编号</th>

					<th
						data-options="field:'pname',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">会员单位名称</th>




					<th
						data-options="field:'date',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">大事记时间</th>



				</tr>

			</thead>

		</table>
	</div>
	<div id="add" style="height: 25px">
		<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick()">新增</a>
		<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick()">删除</a>

	</div>
</body>
</html>
