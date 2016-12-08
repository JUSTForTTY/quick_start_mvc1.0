<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>用户管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/AccountsManageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">
var para= easyui_getRequestPara();
var uid=para.uid;
</script>

<style type="text/css">

.lableWidth100 {
	width: 60px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableLine320 {
	width: 237px;
	float: left;
	padding-top: 8px
}

.lableWidth120 {
	width: 84px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableWidth160 {
	width: 120px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}


.lableLine350 {
	width: 260px;
	float: left;
	padding-top: 8px
}

.lableLine1050 {
	width: 750px;
	float: left;
	padding-top: 0px;
	margin-top:0px;
	border:0px solid red;
}

.lableWidth180 {
	width: 100px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}


.hidden {
	display: none;
	visibility: hidden
}

input {
	width: 150px;
}

input.f{
	width:380px;
}
</style>
<script type="text/javascript">
		//新增或者修改保存
		var map={};
		function save(){	
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			var pwd = $.trim($('#password').val());
			var confirmPwd = $.trim($('#confirmPwd').val());
			if(pwd!=confirmPwd){
				$.messager.alert('警告', "密码和确认密码不一致！",'warning');
				return;
			}
			//组装map
			map['username']=$.trim($('#username').val());
			map['usertype']=$("#usertype").combobox("getValue");
			map['password']=$.trim($('#password').val());
			map['mobile']=$.trim($('#mobile').val());
			map['email']=$.trim($('#email').val());
			
				//insert
				AccountsManageAction.save(map,{
	            	async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('提示','保存成功!','',function(f){
								easyui_closeTopWindow(data.data);
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
	
	<table>
			<tr>
				<td>
					<div class="lableLine350" id="passwordDiv1">
			    	<div class="lableWidth120">用户名</div>
				   <input id="username" class="easyui-validatebox"  name="username" data-options="required:true,validType:'length[1,10]'" />
			    </div>
				</td>
				<td>
				<div class="lableLine350" style="float: left">
						<div class="lableWidth120"  style="float:left">用户类型</div>
			<select  id="usertype" name="usertype"  class="easyui-combobox" style="width:100px;">
			 <option value="0">管理员</option>
			 <option value="2">普通用户</option>
             </select>
             </div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">手机号码：</div>
						<input name="mobile" id="mobile" class="easyui-validatebox" 
						validType="phone"
						data-options="required:true" />
					</div>
				</td>	
			</tr>
			
			
			<tr>
				<td>
					<div class="lableLine350" id="passwordDiv1">
			    	<div class="lableWidth120">用户密码</div>
				   <input id="password" class="easyui-validatebox"  name="password" data-options="required:true,validType:'length[1,10]'" />
			    </div>
				</td>
				<td>
					<div class="lableLine350" id="passwordDiv1">
			    	<div class="lableWidth120">确认密码</div>
				   <input id="confirmPwd" class="easyui-validatebox"  name="confirmPwd" data-options="required:true,validType:'length[1,10]'" />
			    </div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">电子邮箱：</div>
						<input name="email" id="email" class="easyui-validatebox" 
						 />
					</div>
				</td>	
			</tr>
			
			</table>
		
			
			
				
			<div class="clear"></div>
			<div class="lableLine1050">
			<div style="float: right">
			<a href="javascript:void(0)" style="margin-top:0px;margin-right: 10px;" 
			id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" 
			onclick="save()">保存</a>

				</div>
			</div>
			
	</form>
</body>
</html>
