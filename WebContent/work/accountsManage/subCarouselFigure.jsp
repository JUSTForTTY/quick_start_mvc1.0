<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>站点管理</title>
<%@ include file="../../inc.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterCarouselAction.js'></script>
<script type="text/javascript">
var para= easyui_getRequestPara();
var serviceCenterId=para.serviceCenterId;
$(function() {  
	$('#file_upload').uploadPlug({
			uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/centerFile/uploadCenterImage.do', 
			uploadParam:{serviceCenterId:serviceCenterId,carouselSort:1},
			onUpload :function(){

			},
			'onComplete':function(event, queueItem,ID, fileObj, response,data) {
				var obj = jQuery.parseJSON(response);
				$.messager.alert("提示",obj.msg);
				$("#fileView1").css('visibility','').attr('url',obj.picPath);
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
			fileExt:'*.jpg;*.bmp;*.gif;*.ico;*.pcx;*.jpeg;*.tif;*.png;*.raw;*.tga;*.JPG;*.BMP;*.GIF;*.ICO;*.PCX;*.JPEG;*.TIF;*.PNG;*.RAW;*.TGA',
			fileDesc:'Word (*.doc;*.docx)',
			height:26,
			width:92,
			sizeLimit:(1024 * 1024 * 100),
			fileNameLen:20,
			queueID:'custom-queue', 
			removeCompleted: false
	});	 
	$('#file_upload2').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterImage.do', 
		uploadParam:{serviceCenterId:serviceCenterId,carouselSort:2},
		onUpload :function(){

		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response);
			$.messager.alert("提示",obj.msg);
			$("#fileView2").css('visibility','').attr('url',obj.picPath);
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
		fileExt:'*.jpg;*.bmp;*.gif;*.ico;*.pcx;*.jpeg;*.tif;*.png;*.raw;*.tga;*.JPG;*.BMP;*.GIF;*.ICO;*.PCX;*.JPEG;*.TIF;*.PNG;*.RAW;*.TGA',
		fileDesc:'Word (*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	 
	$('#file_upload3').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterImage.do', 
		uploadParam:{serviceCenterId:serviceCenterId,carouselSort:3},
		onUpload :function(){

		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response);
			$.messager.alert("提示",obj.msg);
			$("#fileView3").css('visibility','').attr('url',obj.picPath);
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
		fileExt:'*.jpg;*.bmp;*.gif;*.ico;*.pcx;*.jpeg;*.tif;*.png;*.raw;*.tga;*.JPG;*.BMP;*.GIF;*.ICO;*.PCX;*.JPEG;*.TIF;*.PNG;*.RAW;*.TGA',
		fileDesc:'Word (*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
	});	 
	$('#file_upload4').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
		uploadUrl:'<%=basePath%>/centerFile/uploadCenterImage.do', 
		uploadParam:{serviceCenterId:serviceCenterId,carouselSort:4},
		onUpload :function(){

		},
		'onComplete':function(event, queueItem,ID, fileObj, response,data) {
			var obj = jQuery.parseJSON(response);
			$.messager.alert("提示",obj.msg);
			$("#fileView4").css('visibility','').attr('url',obj.picPath);
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
		fileExt:'*.jpg;*.bmp;*.gif;*.ico;*.pcx;*.jpeg;*.tif;*.png;*.raw;*.tga;*.JPG;*.BMP;*.GIF;*.ICO;*.PCX;*.JPEG;*.TIF;*.PNG;*.RAW;*.TGA',
		fileDesc:'Word (*.doc;*.docx)',
		height:26,
		width:92,
		sizeLimit:(1024 * 1024 * 100),
		fileNameLen:20,
		queueID:'custom-queue', 
		removeCompleted: false
});	 
	if(serviceCenterId!=""&&serviceCenterId!=undefined){
		serviceCenterCarouselAction.getcenterCarousel(serviceCenterId,{
        	async : false,
			callback : function(data) {
				for(var i =0 ;i<data.length;i++){
					$("#fileView"+data[i]['CAROUSEL_SORT']).attr("url",data[i]['CAROUSEL_PATH']).css('visibility','');
				}
			}
        });
	}
	
	
});	
function viewClick(obj){
	window.open('<%=basePath %>'+$(obj).attr('url'));
}
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
		$(function(){
			
		});
	</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
			<div class="Koy" style="width:700px; border:solid #cccccc 0px; padding:1px; margin-left:0px;margin-top:4px;">
				<div class="disc clearfix" style="border: 0px solid red;">
					<div class="discRight" style=" width:700px; float:right; border-left:solid #e8e8e8 1px;">
						<div class="ShausTop" style="height: auto;width:700px;border:0px solid green;" id="custom-queue">
							
						<!-- ------ 文件1  ------ -->
						<div id="div_file1">
							<ul class="PoyBt FileItem file1" style="padding-left:0px;" id="file1">
								<li style="height: 32px;text-align:left" class="Poy_yi"> 轮播图1</li>
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
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="fileView1" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick(this)">查看</a>
								</li>
								
							</ul>
						</div>	
						
						<!-- ------ 文件2  ------ -->
						<div id="div_file2">
							<ul class="PoyBt FileItem file2" id="file2" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									轮播图2
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
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="fileView2" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick(this)">查看</a>
								</li>
							</ul>
						</div>
						
						<!-- ------ 文件3  ------ -->
						<div id="div_file3">
							<ul class="PoyBt FileItem file3" id="file3" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									轮播图3
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
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="fileView3" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick(this)">查看</a>
								</li>
							</ul>
						</div>
						<!-- ------ 文件4  ------ -->
						<div id="div_file4">
							<ul class="PoyBt FileItem file4" id="file4" style="padding-left:0px;" >
								<li style="height: 32px;" class="Poy_yi">
									轮播图4
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
									<a href="javascript:void(0)" style="float:left; margin-left:6px; width: 57px; line-height: 23px; height: 23px; margin-top: 4px;visibility:hidden;" id="fileView4" class="easyui-linkbutton" data-options="iconCls:'icon-export'" onclick="viewClick(this)">查看</a>
								</li>
							</ul>
						</div>
						</div>
					</div>
				</div>
			</div>
	</form>
</body>
</html>
