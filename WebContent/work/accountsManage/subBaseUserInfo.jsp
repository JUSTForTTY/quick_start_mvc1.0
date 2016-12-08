<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>账号管理明细</title>
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
		var para= easyui_getRequestPara();
		var userId=para.userId;
		var serviceCenterName=para.serviceCenterName;
		
		var flag=para.flag;                                   //记录是否是用户类别调用的子页面
		//保存用户信息
		var map={};
		//保存用户类别
		var userTypeData;
		//密码是否更新
		var isPwd=true;
		$(function(){
		if(serviceCenterName=="undefined") serviceCenterName="";
		$("#serviceCenterName").val(serviceCenterName);
		//查找 行业：OCCUPATION_ID 职务：POST_ID  加载到下拉框
			commonMethodAction.getCodeMaster("OCCUPATION_ID,POST_ID",{
          		async : false,
				callback : function(data) {
					data['POST_ID'].unshift({'VAL':'','TEXT':'请选择'});
					$("#postId").combobox("loadData",data['POST_ID']);
					data['OCCUPATION_ID'].unshift({'VAL':'','TEXT':'请选择'});
					$("#occupationId").combobox("loadData",data['OCCUPATION_ID']);
				}	
			});	
				
          
		//查找 用户类别
			baseUserAction.getUserType({},{
          		async : false,
				callback : function(data) {
					/* data.unshift({'VAL':'','TEXT':'请选择'}); */
					if(data!=null)
					{
						var html="";
						$("#userTypeId").combobox("loadData",data);
						userTypeData=data;
					}
				}	
          	});
         //查找 用户角色
			baseUserAction.getSysRole({},{
          		async : false,
				callback : function(data) {
					data.unshift({'VAL':'','TEXT':'请选择'});
					$("#roleId").combobox("loadData",data);
				}	
          	}); 	
          	
          	
			//$("#fm").form('validate');
			//修改的
			if(userId!=""){
				isPwd=false;
				baseUserAction.getBaseUserInfoById({userId:userId},{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
								$("#cutImg").attr("src",'<%=basePath%>'+data.data['photoPath']);
								$("#cutImg").attr("url",data.data['photoPath']);
								$("[name=userBirthday]").val(easyui_datebox_substr($("[name=userBirthday]").val()));
								$("[name=loginPwd]").attr("disabled","disabled");
								map=data.data;
							}else{
								$.messager.alert("提示",data.msg);
							}
							
						}	
            		});
			}
			if(flag!=undefined&&flag!=null&&flag!="")
			{
				$.each($("#fm").find("input[name],select"), function () {
					if($(this).hasClass("easyui-combobox"))
					{
						$(this).next().children().children().attr("style","display:none");
						$(this).next().children().eq(0).attr("disabled","disabled");
						$(this).next().children().eq(0).attr("style","cursor: pointer; width: 148px; height: 20px; line-height: 20px;");
						if($(this).next().children().eq(0).val()=="请选择")
							$(this).next().children().eq(0).val("");
					}
					else
						this.disabled="disabled"; 
            	});
            	$("#passwordDiv1").attr("style","display:none");
            	$("#passwordDiv2").attr("style","display:none");
            	$.each($("#fm").find("a"), function () {
						this.style.display="none";
            	});
			}
			//账号验证
			$("#loginId").focusout(function(data){
				if($(this).val()=="") return;
				baseUserAction.checkLoginId({userId:userId,loginId:$(this).val()},{
            			async : true,
						callback : function(data) {
							if(data.success){
								
							}else{
								$("#loginId").val("");
								$.messager.alert("提示",data.msg);
							}
					}	
            	});
			});
			//所属站点
			$("#btnSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceServiceCenter.jsp";
				var this_=this;
				easyui_openTopWindow("站点选择",680,570,url,function(returnValue){
					if(returnValue!=""){
						$("#serviceCenterName").val(returnValue.SERVICE_CENTER_NAME);
						$("#serviceCenterId").val(returnValue.SERVICE_CENTER_ID);
					}
				});
			});
			//站点清除
			$("#btnDel").click(function(){
				$("#serviceCenterName").val("");
				$("#serviceCenterId").val("");
			});
			//密码重置
			$("#reload").click(function(){
				$.messager.confirm('提示', "是否确定重置密码！",function(r){
					if(r){
						isPwd=true;
						$("[name=loginPwd]").removeAttr("disabled").val("111111");
					}
				});
			});
			$('#userTypeId').combobox({
				onChange: function(newValue,oldValue){
					var temp=$("#userTypeId").combobox("getValue");
					 $.each(userTypeData,(function(i,val){
		            	 	if(val.VAL==temp){
		            	 		if(val.USER_TYPE_FLAG!='1'&&val.USER_TYPE_FLAG!='0'){
		            	 			$('#serviceCenterName').validatebox({   
		            	 			    required:true
		            	 			}); 
		            	 			$('#serviceCenterId').validatebox({   
		            	 			    required:true
		            	 			});
		            	 			$('#serviceCenterId').validatebox("validate");
		            	 		}else
		            	 		{
		            	 			$('#serviceCenterName').validatebox({   
		            	 			    required:false
		            	 			}); 
		            	 			$('#serviceCenterId').validatebox({   
		            	 			    required:false
		            	 			});	
		            	 		}
		            	 		return false;
		            	 	}
		            	 }));
				}
			});

		});
		
		
		function save(){
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			$.each($("#fm").find("input[name]"), function () {
                   if ($(this).hasClass("easyui-combobox") && $(this).combobox("getValue") != "") {
                     	map[$(this).attr("name")] = $.trim($(this).combobox("getValue"));
                   } else{
                   	 	map[$(this).attr("name")] = $.trim($(this).val());
                   }    
            });
            //性别
            map['userSex']=$("[name=userSex]:checked").val();
            //头像路径
            
            map['photoPath']=$("#cutImg").attr("url");
            
            //类别区分
            if(map['userTypeId']!==""){
            	 map['userTypeFlag']=0;
            	 $.each(userTypeData,(function(i,val){
            	 	if(val.VAL==map['userTypeId']){
            	 		map['userTypeFlag']=val.USER_TYPE_FLAG;
            	 		return false;
            	 	}
            	 }));
            }
            baseUserAction.saveBaseUserInfo(map,{lastLoginTime:map.lastLoginTime,isPwd:isPwd},{
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
		//上传图片
		function teacUpHeadPic(){
			showPhotoEditor(
				"<%=basePath%>/script/flash/photoEditor.swf",  // flash头像编辑器地址
				"<%=basePath%>/common/userInfo.do", // 上传地址
				userId, // 上传的同时向后台传递的参数
				"JPG", // "PNG"或"JPG"格式的图片
				"160",
				"160"
			);
		}
		/**
		 * 回调函数
		 * @param {Object} state 1为上传成功;0为上传失败
		 * @param {Object} mess
		 */
		function uploadStage(state,mess){
			artDg.close();
			var jsonstr = $.parseJSON(mess);
			var msg = jsonstr.msg;
			var picPath=jsonstr.picPath;
			{
				$("#cutImg").attr("src",'<%=basePath%>'+picPath);
				$("#cutImg").attr("url",picPath);
				$.messager.alert('提示',msg);
			}
		}
	</script>
</head>
<body style="font-size: 12px;">
		<div class="img_box" style="width: 140px;float:left; height: 140px;">
				<img id="cutImg" src="<%=basePath%>/images/headImages/defaultHead.png" height="140">
		</div>
		<div style="float:left;margin-top:115px;margin-left:10px;">
			<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="teacUpHeadPic()">选择图片</a>
		</div>
	<div class="clear"></div>
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine350">
				<div class="lableWidth120">账号</div>
				<input id="loginId" name="loginId" class="easyui-validatebox"
					data-options="required:true,validType:'loginId'" />
			</div>
			<div class="lableLine350" id="passwordDiv1">
				<div class="lableWidth120">密码</div>
				<input type="password" id="password" class="easyui-validatebox" name="loginPwd" data-options="required:true,validType:'safepass'" value="" />
			</div>
			<div class="lableLine350" id="passwordDiv2">
				<div class="lableWidth120">确认密码</div>
				<input type="password" name="loginPwd" id="repassword" style="margin-left: 1px; width: 140px;" class="easyui-validatebox" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" /> 
				<a id="reload" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'"></a>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120">姓名</div>
				<input name="userName" class="easyui-validatebox" data-options="required:true,validType:'loginName'" />
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">手机/电话</div>
				<input name="cellTel" class="easyui-validatebox" data-options="required:true,validType:'mobile'" />
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">身份证号码</div>
				<input name="idCardNo" class="easyui-validatebox" data-options="validType:'idcard'" />
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120">户籍地</div>
				<input name="domicilePlace" class="easyui-validatebox" data-options="validType:'length[0,20]'" />
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">电子邮箱</div>
				<input name="userEmail" class="easyui-validatebox" data-options="validType:'email'" />
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">其他联系方式</div>
				<input name="otherCon" class="easyui-validatebox" data-options="validType:'length[0,50]'" />
			</div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width: 74px">用户类别</div>
				<select id="userTypeId" name="userTypeId" class="easyui-combobox" style="width: 152px;" data-options="editable:false,valueField: 'VAL',textField: 'TEXT',panelHeight:'auto',required:true">
					<!-- <option value="">请选择</option> -->
				</select>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width: 74px">角色</div>
				<select id="roleId" name="roleId" class="easyui-combobox" style="width: 152px;" data-options="editable:false,valueField: 'VAL',textField: 'TEXT'">
				</select>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">所属站点</div>
				<input id="serviceCenterName" name="serviceCenterName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="serviceCenterId" name="serviceCenterId" type="text" class="hidden" />
				<a id="btnSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="btnDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120">性别</div>
				<input style='width: 15px;' type="radio" value="0" name="userSex" checked> 男 
				<input style='width: 15px;' type="radio" value="1" name="userSex"> 女
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">生日</div>
				<input name="userBirthday" class="easyui-validatebox" onclick="WdatePicker()" />
			</div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width: 74px">职业</div>
				<select id="occupationId" name="occupationId" class="easyui-combobox" style="width: 152px;" data-options="editable:false,valueField: 'VAL',textField: 'TEXT'">
				
				</select>
			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width: 74px">职务</div>
				<select id="postId" name="postId" class="easyui-combobox" style="width: 152px;" data-options="editable:false,valueField: 'VAL',textField: 'TEXT'">
				</select>
			</div>
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-right: 25px; margin-top: 27px" id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
			</div>
		</div>
	</form>
</body>
</html>
