<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>邮件管理</title>
    <%@ include file="../../inc.jsp"%>
	<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/messageAction.js'></script>
	<script type="text/javascript" src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
    .datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber
    {
        text-overflow: ellipsis;
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
        $('#dg').datagrid({   
            url:'messageAction.getMessageList'
        });
	});
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var messageId=row.MESSAGE_ID;
		html += " <a href='javascript:void(0)' onclick='openSubMessage("+index+","+messageId+","+row.RECEIVE_USER_ID+")' >查看</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param['isRead']=$("#isRead").combobox("getValue");
		param['startTime']=$("#startTime").val();
		param['endTime']=$("#endTime").val();
	}
	
	function onDblClickRow(rowIndex, rowData){
		openSubMessage(rowIndex,rowData.MESSAGE_ID,rowData.RECEIVE_USER_ID);
	}
	
	function openSubMessage(index,id,receiveUserId){
		var url="<%=basePath%>work/accountsManage/subMessage.jsp?id="+id;
		easyui_openTopWindow("邮件阅读",820,400,url,function(returnValue)
		{
			if('<%=user.getUserId()%>'== receiveUserId)
			$('#dg').datagrid('updateRow',{
				index: index,
				row: {
					IS_READ:'1',
					IS_READ_TEXT: '是',
				}
			});
		});
	}

	  function rowStyler(index,row){   
        if (row.IS_READ==0){   
        	 return 'font-weight:bold;';
        }   
	   }   
</script>
  </head>
  
  <body class="easyui-layout">
  	<div data-options="region:'north',split:false" style="height:70px">
    <div id="search" class="easyui-panel" title="消息管理" data-options="iconCls:'icon-search'">
		<div class="box">
			<span>发送时间</span>
			<input type="text" id="startTime" class="Wdate" style="width:85px" onclick="WdatePicker()"/>
			<span>~</span>
			<input type="text" id="endTime" class="Wdate" style="width:85px" onclick="WdatePicker()"/>
			<span>是否已读</span>
			<select  id="isRead"  class="easyui-combobox" style="width:100px;" data-options="editable:false,valueField: 'VAL',textField: 'TEXT'">
				<option value=''>不限</option>  
				<option value='0'>否</option>  
				<option value='1'>是</option>  
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
		<table id="dg" title="消息信息"
			data-options="
						fitColumns:false,
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     pagination:true,
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     onBeforeLoad:onBeforeLoad,
	                     onDblClickRow:onDblClickRow,
	                     rowStyler:rowStyler
	                  
	 ">
			<thead>
				<tr>
					<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">查看</th>
					<th data-options="field:'SEND_USER',align:'right',halign:'center',width:80">发送人</th>
					<th data-options="field:'RECEIVE_USER',align:'right',halign:'center',width:90">接收人</th>
					<th data-options="field:'MESSAGE_CONTENT',align:'left',halign:'center',width:450">内容</th>
					<th data-options="field:'IS_READ_TEXT',align:'center',halign:'center',width:90">已读</th>
					<th data-options="field:'CREATE_TIME',align:'center',halign:'center',width:150">发送时间</th>
					
				</tr>
			</thead>
		</table>
	</div>
  </body>
</html>
