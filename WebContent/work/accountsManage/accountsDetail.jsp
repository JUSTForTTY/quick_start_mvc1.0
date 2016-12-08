<%@page import="com.sun.mail.imap.protocol.UID"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>User管理</title>
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
var map=easyui_getRequestPara();
var uid=map["uid"];
var  userType=map["userType"];
var see=easyui_getRequestPara()["see"];
		$(function(){
			if(uid!=null&&uid!=""){			
				AccountsManageAction.getDetail(uid,{
            			async : false,
						callback : function(data) {
							 if(data.success){
								$('#fm').form('load',data.data);
							}else{
								//alert("failed!");
								$.messager.alert("提示",data.msg);
							} 														
						}	
            	});
			}	
		});
		
		//重置
		function reset() {
			//fm是form的id名 
			$('#fm').form('clear');
		}			
		//新增
		function save() {					
			var map = {};
			map['uid'] = uid;//用来判断tid验证船舶是否存在则判断是为添加还是修改
			map['realname'] = $.trim($('#realname').val());
			map["mobile"] = $("#mobile").val();
			map["userType"] = $("#userType").combobox("getValue");
			map['email'] = $.trim($('#email').val());
			AccountsManageAction.update(map, {
				async : true,//和form表单提交同时操作
				callback : function(data) {
					if (data.success) {
						$.messager.alert('提示', '保存成功!', '', function(f) {
							easyui_closeTopWindow(data.data);
						});
					} else {
						$.messager.alert('警告', data.msg, 'warning');
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
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">登录账号：</div>
						<input id="username" name="username" class="easyui-validatebox"  disabled/>&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left" >真实姓名：</div>
						<input name="realname"  id="realname" class="easyui-validatebox" data-options="required:true"  />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">电子邮箱：</div>
						<input name="email" id="email" class="easyui-validatebox" 
						validType="email"
						data-options="required:true" />
					</div>
				</td>	
			</tr>
			
			<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
						      所属省份：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="proviceId" class="easyui-validatebox"  disabled/>
					</div>
				</td>						
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							所属城市：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="cityId" class="easyui-validatebox"  disabled/>
					</div>
				</td>			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							详细地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="address" class="easyui-validatebox" disabled />
					</div>
				</td>
			</tr>
			<tr>
			<td>
			<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">手机号码：</div>
						<input name="mobile" 
						validType="phone"
						id="mobile" class="easyui-validatebox" data-options="required:true"/>
					</div>
			</td>
			<td>
			<span>用户类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 
			<select  id="userType" name="userType"  class="easyui-combobox" style="width:100px;">
			 <option value="0"  selected >管理员</option>
			 <option value="2">用户</option>			 	 
			</select>		
			</td>
			<td>
			<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">等级：</div>
						<input name="level" class="easyui-validatebox" disabled />
					</div>
			</td>
			</tr>
			<tr>
			 <td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							创建人：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="createUser" class="easyui-validatebox" disabled />
					</div>
				</td> 
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
						      创建时间：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="a_create_time" class="easyui-validatebox"
							disabled />
					</div>
				</td>		
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							修改时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="a_modify_time" class="easyui-validatebox" disabled />
					</div>
				</td> 
			</tr>
			<tr>
			 <td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							邀请码：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="inviteCode" class="easyui-validatebox" disabled />
					</div>
				</td>  
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							预存款：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="prepay" class="easyui-validatebox" disabled />
					</div>
				</td>
				 <td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							积分：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="score" class="easyui-validatebox" disabled />
					</div>
				</td> 			
				
			</tr> 
		</table>
		<div class="clear"></div>
			<div style="float: right">

				<a
					href="javascript:void(0)"
					style="width: 80px; height: 30px; margin-top: 15px; margin-right: 20px;"
					id="btnSave" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" onclick="save()">保存</a> 

			</div>
	</form>
</body>
</html>
