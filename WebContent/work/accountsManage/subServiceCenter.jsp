<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>站点管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterAction.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/fileDeleteAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">
var para= easyui_getRequestPara();
var serviceCenterId=para.serviceCenterId;

function dealFile(filePath, obj){
	if(obj.result=="failure"){
		$.messager.alert("提示",obj.msg);
		$(filePath+"View").css('visibility','hidden');
	}else{
		if($(filePath).val()!=null && $(filePath).val()!=""){
			fileDeleteAction.deleteFile($(filePath).val());
		}
		$(filePath).val(obj.filePath);
		$(filePath+"View").css('visibility','visible');
	}
}


$(function() {  
	$('#file_upload').uploadPlug({
			uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
			uploadParam:{serviceCenterId:serviceCenterId,field:"CQRDZWTS_TP_PATH",path:$("#cqrdzwtsTpPath").val()},
			onUpload :function(){
				var path1=$("#cqrdzwtsTpPath").val();
				var pm ='{serviceCenterId:'+serviceCenterId+',field:"CQRDZWTS_TP_PATH",path1:"'+path1+'"}';
				$('#file_upload').uploadPlugSettings('uploadParam',pm);
			},
			'onComplete':function(event, queueItem,ID, fileObj, response,data) {
				var obj = jQuery.parseJSON(response);
				dealFile("#cqrdzwtsTpPath", obj);
				/*if(obj.result=="failure"){
					$.messager.alert("提示",obj.msg);
					$("#cqrdzwtsTpPathView").css('visibility','hidden');
				}
				else{
					$("#cqrdzwtsTpPath").val(obj.filePath);
					$("#cqrdzwtsTpPathView").css('visibility','visible');
				}*/	
				
			},
			'onAddComplete':function(event,item,ID,fileObj,data){
			},
			uniqueCodeName:'crccod',
			cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
			buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
			folder:'/uploads',
			method:'POST',
			multi:false,
			queueSizeLimit: 5,
			auto: false,
			fileExt:'*.doc;*.docx',
			fileDesc:'Word 文挡(*.doc;*.docx)',
			height:26,
			width:92,
			sizeLimit:(1024 * 1024 * 100),
			fileNameLen:20,
			queueID:'custom-queue', 
			removeCompleted: false
	});	 
	$('#file_upload2').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
		uploadParam:{serviceCenterId:serviceCenterId,field:"AQXFXY_TP_PATH"},
		onUpload :function(){
		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response); 
			dealFile("#aqxfxyTpPath", obj);
			/*if(obj.result=="failure"){
				$.messager.alert("提示",obj.msg);
				$("#aqxfxyTpPathView").css('visibility','hidden');
			}
			else{
				$("#aqxfxyTpPath").val(obj.filePath);
				$("#aqxfxyTpPathView").css('visibility','visible');
			}*/
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
		},
		uniqueCodeName:'crccod',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:false,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.doc;*.docx',
		fileDesc:'Word 文挡(*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	
	 
	$('#file_upload3').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
		uploadParam:{serviceCenterId:serviceCenterId,field:"ZAZRBZS_TP_PATH"},
		onUpload :function(){
		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response); 
			dealFile("#zazrbzsTpPath", obj);
			/*if(obj.result=="failure"){
				$.messager.alert("提示",obj.msg);
				$("#zazrbzsTpPathView").css('visibility','hidden');
			}
			else{
				$("#zazrbzsTpPath").val(obj.filePath);
				$("#zazrbzsTpPathView").css('visibility','visible');
			}*/
			
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
		},
		uniqueCodeName:'crccod',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:false,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.doc;*.docx',
		fileDesc:'Word 文挡(*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	
	$('#file_upload4').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
		uploadParam:{serviceCenterId:serviceCenterId,field:"FWZLWTS_TP_PATH"},
		onUpload :function(){
		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response); 
			dealFile("#fwzlwtsTpPath", obj);
			/*if(obj.result=="failure"){
				$.messager.alert("提示",obj.msg);
				$("#fwzlwtsTpPathView").css('visibility','hidden');
			}
			else{
				$("#fwzlwtsTpPath").val(obj.filePath);
				$("#fwzlwtsTpPathView").css('visibility','visible');
			}*/
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
		},
		uniqueCodeName:'crccod',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:false,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.doc;*.docx',
		fileDesc:'Word 文挡(*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	
	
	$('#file_upload5').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
		uploadParam:{serviceCenterId:serviceCenterId,field:"FWZLHT_TP_PATH"},
		onUpload :function(){
		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response); 
			dealFile("#fwzlhtTpPath", obj);
			/*if(obj.result=="failure"){
				$.messager.alert("提示",obj.msg);
				$("#fwzlhtTpPathView").css('visibility','hidden');
			}
			else{
				$("#fwzlhtTpPath").val(obj.filePath);
				$("#fwzlhtTpPathView").css('visibility','visible');
			}*/
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
		},
		uniqueCodeName:'crccod',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:false,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.doc;*.docx',
		fileDesc:'Word 文挡(*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	
	
	$('#file_upload6').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
		uploadParam:{serviceCenterId:serviceCenterId,field:"JZZBLSQD_TP_PATH"},
		onUpload :function(){
		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response); 
			dealFile("#jzzblsqdTpPath", obj);
			/*if(obj.result=="failure"){
				$.messager.alert("提示",obj.msg);
				$("#jzzblsqdTpPathView").css('visibility','hidden');
			}
			else{
				$("#jzzblsqdTpPath").val(obj.filePath);
				$("#jzzblsqdTpPathView").css('visibility','visible');
			}*/
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
		},
		uniqueCodeName:'crccod',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:false,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.doc;*.docx',
		fileDesc:'Word 文挡(*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	
	
	$('#file_upload7').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
		uploadParam:{serviceCenterId:serviceCenterId,field:"BLCRKTZD_TP_PATH"},
		onUpload :function(){
		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response);
			dealFile("#blcrktzdTpPath", obj);
			/*if(obj.result=="failure"){
				$.messager.alert("提示",obj.msg);
				$("#blcrktzdTpPathView").css('visibility','hidden');
			}else{
				$("#blcrktzdTpPath").val(obj.filePath);
				$("#blcrktzdTpPathView").css('visibility','visible');
			}*/
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
		},
		uniqueCodeName:'crccod',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:false,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.doc;*.docx',
		fileDesc:'Word 文挡(*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	
});	
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
			if(serviceCenterId!=""){
				serviceCenterAction.getServiceCenterById({serviceCenterId:serviceCenterId},{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
								//alert(data.data['servicePhotoPath']);
								$("#cutImg").attr("src",'<%=basePath%>'+data.data['servicePhotoPath']);
								$("#cutImg").attr("url",data.data['servicePhotoPath']);
								map=data.data;
							}else{
								$.messager.alert("提示",data.msg);
							}
							
						}	
            		});
				//alert($("#cqrdzwtsTpPath").val());
				if($("#cqrdzwtsTpPath").val()!=null && $("#cqrdzwtsTpPath").val()!="")
					$("#cqrdzwtsTpPathView").css('visibility','visible');
				if($("#aqxfxyTpPath").val()!=null && $("#aqxfxyTpPath").val()!="")
					$("#aqxfxyTpPathView").css('visibility','visible');
				if($("#zazrbzsTpPath").val()!=null && $("#zazrbzsTpPath").val()!="")
					$("#zazrbzsTpPathView").css('visibility','visible');
				if($("#fwzlwtsTpPath").val()!=null && $("#fwzlwtsTpPath").val()!="")
					$("#fwzlwtsTpPathView").css('visibility','visible');
				if($("#fwzlhtTpPath").val()!=null && $("#fwzlhtTpPath").val()!="")
					$("#fwzlhtTpPathView").css('visibility','visible');
				if($("#jzzblsqdTpPath").val()!=null && $("#jzzblsqdTpPath").val()!="")
					$("#jzzblsqdTpPathView").css('visibility','visible');
				if($("#blcrktzdTpPath").val()!=null && $("#blcrktzdTpPath").val()!="")
					$("#blcrktzdTpPathView").css('visibility','visible');
				
			}
			//如果是添加功能
			if(serviceCenterId==""){
				//alert('<%=basePath%>'+'images/SysLogo.png');
				$("#cutImg").attr("src",'<%=basePath%>'+'images/headImages/defaultScLogo.png');
				$("#cutImg").attr("url",'/images/headImages/defaultScLogo.png');
			}
			/*if(serviceCenterId!=""){
				//获得所有用户名称
				serviceCenterAction.getServiceCenterUserName({serviceCenterId:serviceCenterId},{
	          		async : false,
					callback : function(data) {
						if(data.length>0){
							//alert(data[0].accompanyUserName);
							$("#checkUserName").val(data[0].checkUserName);
							$("#publishUserName").val(data[0].publishUserName);
							$("#intentionAppointUserName").val(data[0].intentionAppointUserName);
							$("#accompanyUserName").val(data[0].accompanyUserName);
							$("#leaseTransactionUserName").val(data[0].leaseTransactionUserName);
						}	
					}	
	          	});
			}*/
			//所属区域
			$("#areaSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/serviceCenterArea.jsp";
				var this_=this;
				easyui_openTopWindow("所属区域选择",470,570,url,function(returnValue){
					if(returnValue!=""){
						$("#areaName").val(returnValue.text);
						$("#areaId").val(returnValue.id);
						$("#areaName").validatebox("validate"); 
					}
				});
			});
			
			//产权审核人
			$("#propertyUserSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceBaseUser.jsp?serviceCenterId="+serviceCenterId;
				var this_=this;
				easyui_openTopWindow("产权审核人选择",770,570,url,function(returnValue){
					if(returnValue!=""){
						$("#propertyUserName").val(returnValue.USER_NAME);
						$("#propertyUserId").val(returnValue.USER_ID);
					}
				});
			});
			//产权审核人清除
			$("#propertyUserDel").click(function(){
				$("#propertyUserName").val("");
				$("#propertyUserId").val("");
			});
			
			//房屋审核人
			$("#checkUserSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceBaseUser.jsp?serviceCenterId="+serviceCenterId;
				var this_=this;
				easyui_openTopWindow("房屋审核人选择",770,570,url,function(returnValue){
					if(returnValue!=""){
						$("#checkUserName").val(returnValue.USER_NAME);
						$("#checkUserId").val(returnValue.USER_ID);
					}
				});
			});
			//房屋审核人清除
			$("#checkUserDel").click(function(){
				$("#checkUserName").val("");
				$("#checkUserId").val("");
			});
			
			//发布人
			$("#publishUserSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceBaseUser.jsp?serviceCenterId="+serviceCenterId;
				var this_=this;
				easyui_openTopWindow("发布人选择",770,570,url,function(returnValue){
					if(returnValue!=""){
						$("#publishUserName").val(returnValue.USER_NAME);
						$("#publishUserId").val(returnValue.USER_ID);
					}
				});
			});
			//发布人清除
			$("#publishUserDel").click(function(){
				$("#publishUserName").val("");
				$("#publishUserId").val("");
			});
			
			//看房预约处理人
			$("#intentionAppointUserSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceBaseUser.jsp?serviceCenterId="+serviceCenterId;
				var this_=this;
				easyui_openTopWindow("看房预约处理人选择",770,570,url,function(returnValue){
					if(returnValue!=""){
						$("#intentionAppointUserName").val(returnValue.USER_NAME);
						$("#intentionAppointUserId").val(returnValue.USER_ID);
					}
				});
			});
			//看房预约处理人清除
			$("#intentionAppointUserDel").click(function(){
				$("#intentionAppointUserName").val("");
				$("#intentionAppointUserId").val("");
			});
			
			//现场看房处理人
			$("#accompanyUserSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceBaseUser.jsp?serviceCenterId="+serviceCenterId;
				var this_=this;
				easyui_openTopWindow("现场看房处理人选择",770,570,url,function(returnValue){
					if(returnValue!=""){
						$("#accompanyUserName").val(returnValue.USER_NAME);
						$("#accompanyUserId").val(returnValue.USER_ID);
					}
				});
			});
			//现场看房处理人清除
			$("#accompanyUserDel").click(function(){
				$("#accompanyUserName").val("");
				$("#accompanyUserId").val("");
			});
			
			//租赁处理人
			$("#leaseTransactionUserSearch").click(function(){
				var url ="<%=basePath%>work/accountsManage/choiceBaseUser.jsp?serviceCenterId="+serviceCenterId;
				var this_=this;
				easyui_openTopWindow("租赁处理人选择",770,570,url,function(returnValue){
					if(returnValue!=""){
						$("#leaseTransactionUserName").val(returnValue.USER_NAME);
						$("#leaseTransactionUserId").val(returnValue.USER_ID);
					}
				});
			});
			//租赁处理人清除
			$("#leaseTransactionUserDel").click(function(){
				$("#leaseTransactionUserName").val("");
				$("#leaseTransactionUserId").val("");
			});
		
		
		});
		
		function viewClick(docName, type){
			//alert(docName);
			docName = docName.substring(0, docName.lastIndexOf("."))+".pdf";
			var url="<%=basePath%>work/accountsManage/serviceCenterPdf.jsp?docName=" + docName + "&date="+new Date().getTime();;
			easyui_openTopWindow(type,820,610,url); 
		}
		
		
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
			
			//头像路径
            map['servicePhotoPath']=$("#cutImg").attr("url");
			map['memo']=$("#memo").val();

			serviceCenterAction.saveServiceCenter(map,{lastLoginTime:map.lastLoginTime},{
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
		function teacUpHeadPic(src){
			//如果是默认图片
			if(src=="/images/SysLogo.png")
				src="";
			showPhotoEditor(
				"<%=basePath%>/script/flash/photoEditor.swf",  // flash头像编辑器地址
				"<%=basePath%>/common/serviceCenter.do", // 上传地址
				serviceCenterId + "," + src, // 上传的同时向后台传递的参数
				"PNG", // "PNG"或"JPG"格式的图片
				"120",
				"120"
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
		<div class="img_box" style="width: 190px;float:left; height: 120px;">
				<img id="cutImg" src="" height="120px" width="120px">
		</div>
		<div style="float:left;margin-top:115px;margin-left:10px;">
			<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick='teacUpHeadPic($("#cutImg").attr("url"))'>选择图片</a>
		</div>
	<div class="clear"></div>
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine320">
				<div class="lableWidth100">站点名称</div>
				<input id="serviceCenterName" name="serviceCenterName" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine350" id="passwordDiv1">
				<div class="lableWidth120">站点地址</div>
				<input id="serviceCenterAdd" class="easyui-validatebox" name="serviceCenterAdd" data-options="required:true,validType:'length[1,100]'" value="" />
			</div>
			<div class="lableLine350" id="passwordDiv2">
				<div class="lableWidth120">站点电话</div>
				<input name="serviceCenterTel" id="serviceCenterTel" style="margin-left: 1px; width: 140px;" class="easyui-validatebox"  data-options="required:true,validType:['mobile','length[1,20]']" invalidMessage="电话号码不正确"/> 
			</div>
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">所属区域</div>
				<input id="areaName" name="areaName" data-options="required:true" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="areaId" name="areaId" type="text" class="hidden" />
				<a id="areaSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">产权审核人</div>
				<input id="propertyUserName" name="propertyUserName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="propertyUserId" name="propertyUserId" type="text" class="hidden" />
				<a id="propertyUserSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="propertyUserDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">房屋审核人</div>
				<input id="checkUserName" name="checkUserName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="checkUserId" name="checkUserId" type="text" class="hidden" />
				<a id="checkUserSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="checkUserDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">发布人</div>
				<input id="publishUserName" name="publishUserName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="publishUserId" name="publishUserId" type="text" class="hidden" />
				<a id="publishUserSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="publishUserDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">看房预约处理人</div>
				<input id="intentionAppointUserName" name="intentionAppointUserName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="intentionAppointUserId" name="intentionAppointUserId" type="text" class="hidden" />
				<a id="intentionAppointUserSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="intentionAppointUserDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120">现场看房处理人</div>
				<input id="accompanyUserName" name="accompanyUserName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="accompanyUserId" name="accompanyUserId" type="text" class="hidden" />
				<a id="accompanyUserSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="accompanyUserDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">租赁处理人</div>
				<input id="leaseTransactionUserName" name="leaseTransactionUserName" class="easyui-validatebox" readonly="readonly" style="margin-left: 1px; width: 110px" />
				<input id="leaseTransactionUserId" name="leaseTransactionUserId" type="text" class="hidden" />
				<a id="leaseTransactionUserSearch" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"></a>
				<a id="leaseTransactionUserDel" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'"></a>
			</div>
			<div class="clear">
			</div>
				<!-- 产权人代租委托书 -->
				<input type="text" class="hidden" id="cqrdzwtsTpPath" name="cqrdzwtsTpPath">
				<!-- 安全消防协议 -->
				<input type="text" class="hidden" id="aqxfxyTpPath" name="aqxfxyTpPath">
				<!-- 治安责任保证书 -->
				<input type="text" class="hidden" id="zazrbzsTpPath" name="zazrbzsTpPath">
				<!-- 房屋租赁委托书 -->
				<input type="text" class="hidden" id="fwzlwtsTpPath" name="fwzlwtsTpPath">
				<!-- 房屋租赁合同 -->
				<input type="text" class="hidden" id="fwzlhtTpPath" name="fwzlhtTpPath">
				<!-- 居住证办理申请单-->
				<input type="text" class="hidden" id="jzzblsqdTpPath" name="jzzblsqdTpPath">
				<!-- 办理出入卡通知单 -->
				<input type="text" class="hidden" style="cursor:default;visibility:hidden;" id="blcrktzdTpPath" name="blcrktzdTpPath">
			
			
			<div class="Koy" style="width:700px; border:solid #cccccc 0px; padding:1px; margin-left:0px;margin-top:4px;">
				<div class="disc clearfix" style="border: 0px solid red;">
					<div class="discRight" style=" width:700px; float:right; border-left:solid #e8e8e8 1px;">
						<div class="ShausTop" style="height: auto;width:700px;border:0px solid green;" id="custom-queue">
							
						<!-- ------ 文件1  ------ -->
						<div id="div_file1">
							<ul class="PoyBt FileItem file1" style="padding-left:0px;" id="file1">
								<li style="height: 32px;text-align:left" class="Poy_yi">产权人代租委托书</li>
								<li class="Poy_sa ProgressBar" id="">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi" >
									<a href="javascript:void(0);"> 
										<input id="file_upload" name="file1" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="cqrdzwtsTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#cqrdzwtsTpPath').val(),'产权人代租委托书')">查看</a>
								</li>
								
							</ul>
						</div>	
						
						<!-- ------ 文件2  ------ -->
						<div id="div_file2">
							<ul class="PoyBt FileItem file2" id="file2" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									安全消防协议
								</li>
								<li class="Poy_sa ProgressBar">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi">
									<a href="javascript:void(0);"> 
										<input id="file_upload2" name="file2" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="aqxfxyTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#aqxfxyTpPath').val(),'安全消防协议')">查看</a>
								</li>
							</ul>
						</div>
						
						
						<!-- ------ 文件3  ------ -->
						<div id="div_file3">
							<ul class="PoyBt FileItem file3" id="file3" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									治安责任保证书
								</li>
								<li class="Poy_sa ProgressBar">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi">
									<a href="javascript:void(0);"> 
										<input id="file_upload3" name="file3" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="zazrbzsTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#zazrbzsTpPath').val(),'治安责任保证书')">查看</a>
								</li>
							</ul>
						</div>
						
						
						<!-- ------ 文件4  ------ -->
						<div id="div_file4">
							<ul class="PoyBt FileItem file4" id="file4" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									房屋租赁委托书
								</li>
								<li class="Poy_sa ProgressBar">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi">
									<a href="javascript:void(0);"> 
										<input id="file_upload4" name="file4" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="fwzlwtsTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#fwzlwtsTpPath').val(),'房屋租赁委托书')">查看</a>
								</li>
							</ul>
						</div>
						
						<!-- ------ 文件5  ------ -->
						<div id="div_file5">
							<ul class="PoyBt FileItem file5" id="file5" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									房屋租赁合同
								</li>
								<li class="Poy_sa ProgressBar">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi">
									<a href="javascript:void(0);"> 
										<input id="file_upload5" name="file5" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="fwzlhtTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#fwzlhtTpPath').val(),'房屋租赁合同')">查看</a>
								</li>
							</ul>
						</div>
						
						<!-- ------ 文件6  ------ -->
						<div id="div_file6">
							<ul class="PoyBt FileItem file6" id="file6" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									居住证办理申请单
								</li>
								<li class="Poy_sa ProgressBar">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi">
									<a href="javascript:void(0);"> 
										<input id="file_upload6" name="file6" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="jzzblsqdTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#jzzblsqdTpPath').val(),'居住证办理申请单')">查看</a>
								</li>
							</ul>
						</div>
						
						<!-- ------ 文件7  ------ -->
						<div id="div_file7" style="border:0px solid red;">
							<ul class="PoyBt FileItem file7" id="file7" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									办理出入卡通知单
								</li>
								<li class="Poy_sa ProgressBar">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi">
									<a href="javascript:void(0);"> 
										<input id="file_upload7" name="file7" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;border:0px solid red;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="blcrktzdTpPathView" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick($('#blcrktzdTpPath').val(),'办理出入卡通知单')">查看</a>
								</li>
							</ul>
						</div>
						
						
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="lableLine1050">
				<div class="lableWidth180">备注</div>
				<textarea id="memo" style="width:540px;height:55px;" name="memo" class="textarea easyui-validatebox" ></textarea>
				
			</div>
			<div class="clear"></div>
			<div class="lableLine1050">
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-top:0px;margin-right: 10px;" id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
