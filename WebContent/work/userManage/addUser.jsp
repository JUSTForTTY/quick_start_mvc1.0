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
var para= easyui_getRequestPara();
var username=para.username;
//var codeno=para.codeno;
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
		//保存用户信息
		var map={};
		$(function(){
			//修改的
			if(username!=""){
				//userManageAction.getModify({codetype:codetype,codeno:codeno},{
				userManageAction.getModify({username:username},{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
								map=data.data;
								map['username']=$("#username").val();
								//map['codeNoOld']=$("#CodeNo").val();
							}else{
								alert("failed!");
								$.messager.alert("提示",data.msg);
							}
						}	
            		});
			}
		
		});
		
		function save(){
			
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			
			//组装map
			$.each($("#fm").find("input[name]"), function () {
                if ($(this).hasClass("easyui-combobox") && $(this).combobox("getValue") != "") {
                  	map[$(this).attr("name")] = $.trim($(this).combobox("getValue"));
                } else{
                	 	map[$(this).attr("name")] = $.trim($(this).val());
                }    
         	});
			
			map['memo']=$("#memo").val();
			map['codeType']=$("#CodeType").val();
			map['codeNo']=$("#CodeNo").val();
			map['codeName']=$("#CodeName").val();
			map['codeDescription']=$("#CodeDescription").val();

			codeMasterAction.saveCodeMaster(map,{
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
		<div style="width: auto">			
			<div class="lableLine350">
				<div class="lableWidth120">账号名</div>
				<input id="CodeType" name="CodeType" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,50]'"  style="margin-left: 1px; width: 140px;"/>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120"></div>
				<input id="CodeNo" class="easyui-validatebox" name="CodeNo" data-options="required:true,validType:'length[1,100]'" value=""  style="margin-left: 1px; width: 140px;"/>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120">Code名称</div>
				<input name="CodeName" id="CodeName" style="margin-left: 1px; width: 140px;" class="easyui-validatebox"  data-options="required:true,validType:['length[1,100]']"/> 
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120">Code备注</div>
				<input name="CodeDescription" id="CodeDescription" style="margin-left: 1px; width: 140px;" class="easyui-validatebox"  data-options="required:true,validType:['length[1,1020]']" /> 
			</div>
			<div class="clear"></div>
			<div class="lableLine220">
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-top:0px;margin-right: 10px;" id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
