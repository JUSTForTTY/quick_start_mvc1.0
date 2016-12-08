<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>User管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/userDataManage.js'></script>

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
		//前一页面传值uid
		var map=easyui_getRequestPara();
		var uid=map["uid"];
		//alert("uid"+uid);
		var see=easyui_getRequestPara()["see"];
		//alert('提示', '1');
		$(function(){			
			if(uid!=""){
				userDataManage.getDetail(uid,{
            			async : false,
						callback : function(data) {
							 if(data.success){
								$('#fm').form('load',data.data);
								//map=data.data;
								//map['uid']=$("#uid").val();
								//map['codeNoOld']=$("#CodeNo").val();
							}else{
								//alert("failed!");
								$.messager.alert("提示",data.msg);
							} 
							
							
						}	
            	});
			}	
		});						
</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
		<table>
			<tr>
				 <td>
					<div class="lableLine350" style="float: left" hidden="hidden">
						<div class="lableWidth120" style="float: left">
							楼宇：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input id="g_building_name" name="g_building_name" class="easyui-validatebox" />&nbsp;&nbsp;
					</div>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							成员：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="member_id" class="easyui-validatebox" disabled />&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">账号名：</div>
						<input id="username" name="username" class="easyui-validatebox" disabled />&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">真实名：</div>
						<input name="realname" class="easyui-validatebox" disabled />
					</div>
				</td>				
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">等级：</div>
						<input name="level" class="easyui-validatebox" disabled />
					</div>
				</td> 
				
		     <tr>				
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							英文名：&nbsp;&nbsp;&nbsp;&nbsp;</div>
							<input type="hidden" name="statusa"maxlength="50" id="statusa" />
						 <input name="englishname" id="englishname" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							昵称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="nickname" class="easyui-validatebox" disabled />
					</div>
				</td>			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							性别：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input type="hidden" name="wharf" id="wharf" /> <input
							name="gender" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">电话号码：</div>
						<input name="tel" class="easyui-validatebox"
							disabled />
					</div>
				</td>
				</tr>
			<tr>				
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							传真：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="fax" class="easyui-validatebox" disabled />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							email：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="email" class="easyui-validatebox" disabled />
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
			<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							微软公司：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="msn" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							qq：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="qq" class="easyui-validatebox"
							disabled />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							手机号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="mobile" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							联系人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="contactor" class="easyui-validatebox" disabled />
					</div>
				</td>
				</tr>
			<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							邮编：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="postcode" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							地址：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="address" class="easyui-validatebox"
							disabled />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							凭据类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="credentialtype" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							凭据：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="credential" class="easyui-validatebox" disabled />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							生日：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="a_birthday" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							用户类型：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="usertype" class="easyui-validatebox"
							disabled />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="status" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							创建人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="creator_id" class="easyui-validatebox" disabled />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							分组：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="group_id" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							邀请人账户名：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="recuser_id" class="easyui-validatebox"
							disabled />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							身份证号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="cardId" class="easyui-validatebox" disabled />
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							微信公众号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="openId" class="easyui-validatebox" disabled />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							创建人：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="create_user" class="easyui-validatebox" disabled />
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
							修改人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="modify_user" class="easyui-validatebox" disabled />
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
						      省：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="proviceId" class="easyui-validatebox"
							disabled />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							市：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="cityId" class="easyui-validatebox" disabled />
					</div>
				</td>
				
			</tr> 
		</table>
	</form>
</body>
</html>
