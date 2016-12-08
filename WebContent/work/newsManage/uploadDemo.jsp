<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <title>demo页面</title>
    <!-- 引用共通 js 和 样式  -->
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
    <link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">
//测试传参
id=0;
$(function() {  
	var file_uploadLFD0VIQueueItem="";
	$('#file_upload').uploadPlug({
			uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/centerFile/uploadCenterFile.do', 
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
	$('#file_upload2').uploadPlug({
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
});	 
	
});	 

function ID(){
	alert(id);
	id++
}
</script>
  </head>
  
  <body>
  <div onclick="ID()">
  	ID++
  </div>
  <div class="main">
  <input type="file" name="file_uploadLFD0VIQueueItem" id="file_uploadLFD0VIQueueItem" /> 
	<div class="Koy">
		<div class="disc clearfix">
			<div class="discRight">
				<div class="Shaus">
				</div>
				<!-- <ul class="PoyBt FileItem" style="display: none;" id="custom-queue-item">
					<li style="height: 32px;" class="Poy_yi"></li>
					<li style="height: 32px;" class="Poy_yi FileName"></li>
					<li class="Poy_sa ProgressBar">
						<dl>
							<p class="ProgressBarValue" />
							<p class="ProgressBarIcon" /> 
							<p class="ProgressBarMsg"/>
						</dl>
					</li>
					<li class="Poy_si" style="padding-top: 0px; height: 32px;">
						<p class="Btn UploadBtn" style="cursor: default;"></p>
						<p class="Btn CancelBtn" style="cursor: default;"></p>
					</li>
				</ul> -->
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
				
				<!-- ------ 文件2  ------ -->
				<div id="div_file2">
					<ul class="PoyBt FileItem file2">
						<li style="height: 32px;" class="Poy_yi">
							文件二
						</li>
						<li style="height: 32px;" class="Poy_yi FileName" ></li>
						<li class="Poy_sa ProgressBar">
							<dl>
								<p class="ProgressBarValue">
								</p><p class="ProgressBarIcon"> 
								</p><p class="ProgressBarMsg"></p>
							</dl>
						</li>
						<li style="height: 32px;" class="Poy_yi">
							<a href="javascript:void(0);"> 
								<input id="file_upload2" name="file2" type="file" />
							</a>
						</li>
						<li class="Poy_si" style="padding-top: 0px; height: 32px;">
							<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
							<p class="Btn CancelBtn" style="cursor: default;"></p>
						</li>
					</ul>
				</div>
				
				</div>
				
			</div>
		</div>
	</div>
</div>
<form id="hiddenFrm" method="post"></form>
  </body>
</html>
