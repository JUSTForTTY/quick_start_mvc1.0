<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>User管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/userManageAction.js'></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">

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

var map=easyui_getRequestPara();
var id=map["id"];
//判断用户类型
//'0'超级管理员;'1'快车网管理员;'2'会员单位管理;'3'社团管理员;'4'普通会员;
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
	$("#admin").hide();
	}else if(usertype==1){
    $("#superadmin").hide();	
    $("#admin").hide();		
	}else {
    $("#superadmin").hide();
    $("#wuyeokadmin").hide();
    $("#provideradmin").hide();
    $("#provider_id").hide();
	}	
});
		//保存用户信息
		var map={};
		$(function(){
			//修改的
			if(id!=null&&id!=""){		
// 				alert("id="+id);
				userManageAction.getById(id,{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
								map=data.data;
// 								map['username']=$("#username").val();
								//map['codeNoOld']=$("#CodeNo").val();
							}else{
								alert("failed!");
								$.messager.alert("提示",data.msg);
							}
						}	
            		});
			}
		
		});
		
		function saveOrUpdate(){
			
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			
			//组装map
			$.each($("#fm").find("input[name],select[name]"), function () {
                if ($(this).hasClass("easyui-combobox") && $(this).combobox("getValue") != "") {
                  	map[$(this).attr("name")] = $.trim($(this).combobox("getValue"));
                } else{
                	 	map[$(this).attr("name")] = $.trim($(this).val());
                }    
         	});
			
			
			map['id']=id;
			userManageAction.saveOrUpdate(map,{
            	async : false,
				callback : function(data) {
// 					alert("ss");
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
		//取消
		function cancel(){
			easyui_closeTopWindow("详情页面",function(result){
				if(result){
					$("#dg").datagrid('reload');
				}
			});
			 
				}
		 //重置
		function resetBtnClick(){
			 
					//fm是form的id名 
					$('#fm').form('clear');
			 
				}
		 
	</script>
</head>
<body style="font-size: 12px;">
<form id="fm" method="post">
		<table>
				
			<tr>				
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							帐号：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="username" class="easyui-validatebox" 
						data-options="required:true,validType:'length[1,20]'" />
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							密码：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input type="password" name="password" class="easyui-validatebox"
						data-options="required:true,validType:'length[1,255]'"/>
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							姓名：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="realname" class="easyui-validatebox"
						data-options="required:true,validType:'length[1,20]'"/>
					</div>
				</td>
				 <td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							昵称：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="nickname" class="easyui-validatebox"/>
					</div>
				</td>
				
				</tr>
			<tr>
			    <td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							性别：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<select name="gender" class="easyui-validatebox">
						<option value="男" selected>男</option>
						<option value="女">女</option>
						</select>
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							手机号：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="tel" class="easyui-validatebox"/>
					</div>
				</td>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							邮箱：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="email" class="easyui-validatebox"/>
					</div>
				</td>
			
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							公司单位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="providername" class="easyui-validatebox" disabled/>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							地址：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="address" class="easyui-validatebox"/>
					</div>
				</td>
	
			    <td>
					<div class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							用户类型：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<select name="usertype" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">
						<option value="" selected>--请选择--</option>
						<option id="superadmin"  value="0" >超级管理员</option>
						<option id="wuyeokadmin" value="1">快车网管理员</option>
						<option id="provideradmin" value="2">会员单位管理员</option>
						<option id="admin" value="2">系统管理员</option>
						<option id="clubadmin" value="3">社团管理员</option>
						<option id="user" value="4">普通用户</option>
						</select>
					</div>
				</td>
				<td>
					<div id="provider_id" class="lableLine350" style="float: left">
						<div class="lableWidth120" style="float: left">
							会员单位编号：&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<input name="providerId" class="easyui-validatebox"
						data-options="required:false,validType:'length[1,20]'"/>
					</div>
				</td>
				</tr>
		</table>
		<div style="margin-left:400px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px"
							id="" data-options="iconCls:'icon-undo'"
							onclick="resetBtnClick()">重置</a> <a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="saveOrUpdate()">保存</a> 
							<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px" id=""
							class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="cancel()">取消</a>
					</div>
	</form>

</body>
</html>
