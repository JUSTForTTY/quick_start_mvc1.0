<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>账号管理明细</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/messageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<style type="text/css">
.lableWidth120 {
	width: 72px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableLine350 {
	width: 250px;
	float: left;
	padding-top: 8px
}

.hidden {
	display: none;
	visibility: hidden
}

input {
	width: 150px;
}
</style>
<script type="text/javascript">
		var para= easyui_getRequestPara();
		var id=para.id;
		$(function(){
				messageAction.getMessageInfoById(id,{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
							}else{
								$.messager.alert("提示",data.msg);
							}
							
						}	
            		});
		});
		function openClose(){
			easyui_closeTopWindow();
		}
	</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine350">
				<div class="lableWidth120">接收人</div>
				<input name="SEND_USER" class="easyui-validatebox"/>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120">发送人</div>
				<input name="RECEIVE_USER" class="easyui-validatebox"/>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width: 74px">内容</div>
				<div style="margin-left:70px;">
					<textarea rows="10" cols="100" name="MESSAGE_CONTENT">
				
					</textarea>
				</div>
			</div>
			<div class="clear"></div>
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-right: 25px; margin-top: 27px" id="btnSave" class="easyui-linkbutton" data-options="" onclick="openClose()">关闭</a>
			</div>
		</div>
	</form>
</body>
</html>
