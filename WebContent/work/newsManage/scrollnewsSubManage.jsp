<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>代码管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>


 <link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">
var para= easyui_getRequestPara();
var id=para.id;
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
			if(id!=""){
				
				codeMasterAction.getById(id,{
            			async : false,
						callback : function(data) {
							
							if(data.success){
								
								$('#fm').form('load',data.data);
								map=data.data;
							}else{
								$.messager.alert("提示",data.msg);
							}
						}	
            		});
				
			}
		
		});
	 	//新增或者修改保存
		function save(){
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			//组装map
			
			map['codetype']=$('#codetype option:selected') .val();
			map['codeno']=$.trim($('#codeno').val());	
			map['codename']=$.trim($('#codename').val());	
			map['codedescription']=$.trim($('#codedescription').val());
			if(id!=""){
				map['id']=id;}
				//saveorupdate
				newsManageAction.SaveOrUpdate(map,{
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
	<script type="text/javascript">
//测试传参
id=0;
$(function() {  
	var file_uploadLFD0VIQueueItem="";
	$('#file_upload').uploadPlug({
			uploader:'<%=basePath%>script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>test/userInfo.do', 
			uploadParam:{id:id},
			onUpload :function(){
			},
			'onComplete'   : function(event, queueItem,ID, fileObj, response,data) {
				alert($('#file_upload').uploadPlug("uploadUrl"));
				alert("=====response"+response+"======data"+data);
			},
			'onAddComplete':function(event,item,ID,fileObj,data){
				
			},
			uniqueCodeName:'crccod',
			cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
			buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
			folder:'/uploads',
			method:'POST',
			multi:true,
			queueSizeLimit: 5,
			auto: false,
			fileExt:'*.*',
			fileDesc:'所有文件(*.*)',
			height:26,
			width:92,
			sizeLimit:(1024 * 1024 * 10000),
			fileNameLen:20,
			queueID:'custom-queue', 
			removeCompleted: false
	});	 
<%-- 	$('#file_upload2').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/test/userInfo.do', 
		uploadParam:{},
	/* 	checkExistUrl:'/fileupload/doCheckFileExist.html', */
		checkExistParam:{},
		onUpload :function(){
			
		},
		'onSelect'   : function(event,item,ID,fileObj,data) {
		},
		'onAddComplete':function(event,item,ID,fileObj,data){
			//alert("文件成功添加到队列");
		},
		uniqueCodeName:'crccode',
		cancelImg:' <%=basePath%>script/uploadPlug/images/cancel.png',
		buttonImg:'<%=basePath%>script/uploadPlug/images/addFile.gif', 
		folder:'/uploads',
		method:'POST',
		multi:true,
		queueSizeLimit: 5,
		auto: false,
		fileExt:'*.*',
		fileDesc:'所有文件(*.*)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
});	 --%> 
	
});	 

function ID(){
	alert(id);
	id++
}
</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine320" id="passwordDiv1">
				<div class="lableWidth100">标题</div>
				<input id=title class="easyui-validatebox" name="id=title" data-options="required:true,validType:'length[1,20]'" value="" 
				  
				/>
				 	

			</div>
			<div class="clear"></div>
			<div class="lableLine350">
				<div class="lableWidth100">英文标题</div>
				
			<input id=englishTitle class="easyui-validatebox" name="englishTitle" data-options="required:true,validType:'length[1,20]'" value="" 
				
				/>
			</div>
			<div class="clear"></div>
			<div class="ShausTop" style="height: auto;" id="custom-queue">
					<ul class="PoyTop">
						<li class="Poy_yi" style="height: 28px;">文件名</li>
						<li class="Poy_yi" style="height: 28px;">文件名</li>
						<li class="Poy_sa" style="height: 28px;">上传进度</li>
						<li class="Poy_si" style="padding: 0px; height: 28px;">操作</li>
					</ul>
					<!-- ------ 文件1  ------ -->
				<div id="div_file1">
					<ul class="PoyBt FileItem file1" style="" id="file1">
						<li style="height: 32px;" class="Poy_yi">文件一</li>
						<li style="height: 32px;" class="Poy_yi FileName"  title="">
						</li>
						<li class="poy_2 ProgressBar">
							<div class="song_ti" style="color: #3385d8;">
								<font class="ProgressBarMsg">上传速度</font> <span
									class="ProgressBarIcon"></span>
							</div>
							<div class="song_wei">
								<div class="song_nei ProgressBarValue" style="width: 0%;"></div>
							</div>
						</li>

						<li class="Poy_sa ProgressBar" id="">
							<dl>
							
								<p class="ProgressBarValue">
								</p><p class="ProgressBarIcon"> 
								</p><p class="ProgressBarMsg"></p>
							</dl>
						</li>
						<li style="height: 32px;" class="Poy_yi">
							<a href="javascript:void(0);"> 
								<input id="file_upload" name="file1" type="file" />
							</a>
						</li>
						<li class="Poy_si" style="padding-top: 0px; height: 32px;">
							<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
							<p class="Btn CancelBtn" style="cursor: default;"></p>
						</li>
						
					</ul>
				</div>	
			
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">代码描述</div>
				
					<textarea  id="content" name="content" rows="8" cols="100"></textarea>
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
