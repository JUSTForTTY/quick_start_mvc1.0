<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>密码修改</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/baseUserAction.js'></script>
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
		
		$(function(){
				
			
		});
		
		
		function save(){
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			var map={};
			map.pwd=$("#password").val();
			map.newPwd=$("#newPassword").val();
			map.logPwd=$("#loginPwd").val();
			if(map.newPwd==map.logPwd){
				$.messager.alert('警告', "两次输入的密码不一致！",'warning');
				return;
			}
            baseUserAction.updateBaseUserPwd(map,{
            	async : false,
				callback : function(data) {
					if (data.success) {
						$.messager.alert('提示','密码修改成功，请重新登录!','',function(f){
							easyui_closeTopWindow(true);
						});
					}else{
						$.messager.alert('警告', data.msg,'warning');
					}
				}
            });
		}
	</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine350" id="passwordDiv1">
				<div class="lableWidth120">原密码</div>
				<input type="password" id="password" class="easyui-validatebox" name="loginPwd" data-options="required:true,validType:'safepass'" value="" />
			</div>
			<div class="clear"></div>
			<div class="lableLine350" id="passwordDiv1">
				<div class="lableWidth120">新密码</div>
				<input type="password" id="newPassword" class="easyui-validatebox" name="loginPwd" data-options="required:true,validType:'safepass'" value="" />
			</div>
			<div class="clear"></div>
			<div class="lableLine350" id="passwordDiv2">
				<div class="lableWidth120">确认密码</div>
				<input type="password" name="loginPwd" id="repassword"  class="easyui-validatebox" data-options="required:true,validType:'safepass'" validType="equalTo['#newPassword']" invalidMessage="两次输入密码不匹配" /> 
			</div>
			<div class="clear"></div>
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-right: 25px; margin-top: 27px" id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
			</div>
		</div>	
	</form>
</body>
</html>
