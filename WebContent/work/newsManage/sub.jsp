<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>站点管理</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>    <link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">
serviceCenterId=0
$(function() {  
	$('#file_upload').uploadPlug({
			uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/imageFile/uploadFile.do', 
			uploadParam:{serviceCenterId:serviceCenterId},
			onUpload :function(){
				
			},
			'onComplete':function(event, queueItem,ID, fileObj, response,data) {
				alert(response);
				
				
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
	
</head>
<body>
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
</body>
</html>