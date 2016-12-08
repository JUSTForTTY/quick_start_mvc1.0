<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>公告管理</title>
    <%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterCarouselAction.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterNoticeAction.js'></script>
	<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>
<script type="text/javascript">
	$(function(){
		var toDay="";
		var now=new Date();
	  	var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
        toDay +=year+"-";
        if(month < 10)
        	toDay += "0";
        toDay += month + "-";
        if(day < 10)
        	toDay += "0";
        toDay += day + "";
        $("#startTime").val(toDay);
        $("#endTime").val(toDay);
        serviceCenterCarouselAction.getServiceCenter({
        	async : false,
			callback : function(data) {
				if(data!=null&&data.length>0){
					$("#serviceCenterId").combobox("loadData",data);
					$("#serviceCenterId").combobox('setValue',data[0]['SERVICE_CENTER_ID']);
				}else{
					$.messager.alert('提示','没有站点,请完善人个信息！','',function(f){
						easyui_closeTopWindow();
					});
				}
			}
		});
        $("#dg").datagrid({
			url:'serviceCenterNoticeAction.getCenterNotice'});
	});
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.SERVICE_CENTER_NOTICE_ID;
		html += " <a href='javascript:void(0)' onclick='editBtnClick("+id+",this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param['startTime']=$("#startTime").val();
		param['endTime']=$("#endTime").val();
		param['serviceCenterId']=$("#serviceCenterId").combobox("getValue");
	}
	
	function delBtnClick(){
		var ids="";
		var checkedRows=$('#dg').datagrid('getChecked');
		$.each(checkedRows,function(i){
			ids=ids+checkedRows[i]['SERVICE_CENTER_NOTICE_ID']+",";
		});
		ids=ids.substring(0,ids.length-1);
		$.messager.confirm('确认', '确定要删除吗?', function(r) {
			if (r) {
				serviceCenterNoticeAction.delCenterNoticeById({id:ids}, {
					async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('确认', '删除成功!');
							$('#dg').datagrid('reload');
						} else {
							$.messager.alert('警告', '删除失败!');
						}
					}
				});
			}
		});
	}
	
	//修改
	function editBtnClick(id,obj) {
		var url="<%=basePath%>work/accountsManage/subCenterNotice.jsp?id="+id;
		easyui_openTopWindow("公告管理",620,400,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		});
	}
	

</script>
  </head>
  
  <body class="easyui-layout">
  	<div data-options="region:'north',split:false" style="height:70px">
    <div id="search" class="easyui-panel" title="用户信息管理" data-options="iconCls:'icon-search'">
		<div class="box">
			<span>发布时间</span>
			<input type="text" id="startTime" class="Wdate" style="width:85px" onclick="WdatePicker()"/>
			<span>~</span>
			<input type="text" id="endTime" class="Wdate" style="width:85px" onclick="WdatePicker()"/>
			<span>站点</span> 
			<select id="serviceCenterId"  class="easyui-combobox" style="width:150px;" data-options="editable:false,panelHeight:'auto',valueField: 'SERVICE_CENTER_ID',textField: 'SERVICE_CENTER_NAME'">
		
			</select>
		</div>
		<span class="aBtn">
			<a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="searchBtnClick()">查询</a>
		</span>
	</div>
	</div>
	<div class="clear"></div>
	<div data-options="region:'center'">
		<table id="dg"  title="公告管理"
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
	                     onBeforeLoad:onBeforeLoad
	                    
	 ">
			<thead>
				<tr>
					<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">修改</th>
					<th data-options="field:'NOTICE_TITLE',align:'left',halign:'center',width:280">标题</th>
					<th data-options="field:'NOTICE_DATE',align:'center',halign:'center',width:90">发布时间</th>
					<th data-options="field:'NOTICE_USER',align:'right',halign:'center',width:100">发布者</th>
					<th data-options="field:'SERVICE_CENTER_NAME',align:'right',halign:'center',width:100">发布站点</th>
				</tr>
			</thead>
		</table>
		<div id="add" style="height:25px">
			<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-add',plain:true" onclick="editBtnClick('',this)">新增</a>
			<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left:10px;"
				data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		</div>
	</div>
  </body>
</html>
