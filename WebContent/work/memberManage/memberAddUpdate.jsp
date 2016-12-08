<%@ page language="java" import="java.util.*,com.tcj.common.util.*"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>会员单位管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<!-- <script type='text/javascript' -->
<%-- 	src='<%=basePath%>/script/easyui/dwrloader.js'></script> --%>
<%-- 	<script type="text/javascript" src='<%=basePath%>/js/jquery-1.8.3.min.js'></script> --%>
	
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/memberManageAction.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/fileDeleteAction.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript"
	src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/memberManageAction.js'></script>
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<!--	富文本框样式-->
<script type="text/javascript"
	src="<%=basePath%>/script/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=basePath%>/script/ckfinder/ckfinder.js"></script> 
<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>

<script type="text/javascript">
var para= easyui_getRequestPara();
var id=para.id;
//var caid=para.caid;
//var status=para.status;

//alert("id"+id);
//alert("caid"+caid); 
function dealFile(filePath, obj){
	if(obj.result=="failure"){
		$.messager.alert("提示",obj.msg);
		$(filePath+"View").css('visibility','hidden');
	}else{
		$(filePath).val(obj.filePath);
		$(filePath+"View").attr("src",obj.filePath);
		$(filePath+"View").css('visibility','visible');
		
	}
}

$(function() {  
	//alert(11);
	$('#file_upload').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/imageupload/uploadimage.do', 
			uploadParam:{id:id,path:$("#miniature").val(),miniature:1},
			onUpload :function(){ 
				var path=$("#miniature").val();
				var pm ={id:id,path:path};
			//	alert(pm+"你好");
				$('#file_upload').uploadPlugSettings('uploadParam',pm);
			},
			'onComplete':function(event, queueItem,ID, fileObj, response,data) {
				var obj = jQuery.parseJSON(response);
				dealFile("#miniature", obj);
				//alert(response);
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
			fileExt:'*.png;*.jpg;*.gif',
			fileDesc:'图片文件(*.png;*.jpg;*.gif)',
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
.lableWidth120 {
	width: 72px;
	float: left;
	white-space: nowrap;
	margin-top: 5px
}

.lableLine350 {
	width: 520px;
	float: left;
	margin-top: 15px;
	border:0px solid red;
}

.lableLine530 {
	width: 640px;
	float: left;
	margin-top: 15px;
}

.lableLine650 {
	width: 740px;
	float: left;
	margin-top: 15px;
}

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

/* .lableWidth120 { */
/* 	width: 84px; */
/* 	float: left; */
/* 	white-space: nowrap; */
/* 	padding-top: 4px */
/* } */
.lableWidth160 {
	width: 120px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

/* .lableLine350 { */
/* 	width: 260px; */
/* 	float: left; */
/* 	padding-top: 8px */
/* } */
.lableLine1050 {
	width: 750px;
	float: left;
	padding-top: 0px;
	margin-top: 0px;
	border: 0px solid red;
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

input.f {
	width: 380px;
}
</style>
<script type="text/javascript">

		//保存用户信息
		var map={};
	
		$(function(){
			//修改的预查询
			if(id!=null){
				//alert("nid你好，我知道你没定义"+nid);
			//	alert("id你最好"+id);
			memberManageAction.getByid(id,{
				async : false,
						callback : function(data) {
							if(data.success){
							//	alert("content"+data.content);
							   CKEDITOR.instances.detailintro.setData(data.data.detailintro);
							//    CKEDITOR.instances.mobilecontent.setData(data.data.mobilecontent);
							    //	map['content']=CKEDITOR.instances.content.getData();
								$('#fm').form('load',data.data);
								//alert(data.data.image);
								//alert( $('#imageView').src);
								$('#miniatureView').attr('src',data.data.miniature);//把image值放到id为imageView下的src中
								$('#miniatureView').css("visibility","");
						//		$('#content').css("visibility","");
								// $('#imageView')[0].src;
								// alert($('#imageView')[0].src);
							
								//alert($("#imageView").attr("src"));
								//$("img#image").attr("src");
 							//	alert("内容你好"+data.data.content+"===你好");
								map=data.data;
							//	showButton(data.data.status);
							}else{
								$.messager.alert("提示",data.msg);
							}
						}	
            		});
				
			}
			
		});
		//新增或者修改保存
		function save(){
		//	alert("保存操作");
			//$("#picture").submit();
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
// 			if($.trim($('#image1').val())=="")
// 			{
// 				$.messager.alert('警告', "请上传图片！",'warning');	
// 				return;
// 			}
// 			if(CKEDITOR.instances.content.getData()=="")
// 			{
// 				$.messager.alert('警告', "请输入内容！",'warning');	
// 				return;
// 			}
			//组装map
		    map['name']=$.trim($('#name').val());
			map['title']=$.trim($('#title').val());
			map['address']=$.trim($('#address').val());	
			map['longitude']=$.trim($('#longitude').val());	
			map['latitude']=$.trim($('#latitude').val());	
			map['memberurl']=$.trim($('#memberurl').val());	
			map['miniature']=$.trim($('#miniature').val());	
	        map['detailintro']=CKEDITOR.instances.detailintro.getData();  
	        map['image1']=$.trim($('#image1').val());	
	        map['id']=$.trim($('#id').val());	
	     //	alert(image);
	     	//map['image']=$.trim($('#image').val());	
				map['id']=id;
				//alert("去后台"+id);
				memberManageAction.saveOrUpdate(map,{
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
		
	//取消
	function cancel() {
		easyui_closeTopWindow("详情页面", function(result) {
			if (result) {
				$("#dg").datagrid('load');
			}
		});

	}
	
	 
	
</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post"  >
		<table border="0px">	
			<tr>
				<td>		
					<div class="lableLine350">
						<div class="lableWidth120">会员单位名称</div>
						<input name="name" id="name" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:true,validType:'length[1,100]'" />

					</div>
					<div class="lableLine350">
						<div class="lableWidth120">简介</div>
						<input name="title" id="title" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>
				<div class="lableLine350">
						<div class="lableWidth120">公司地址</div>
						<input name="address" id="address" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>
					<div class="lableLine350">
						<div class="lableWidth120">经度</div>
						<input name="longitude" id="longitude" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>
					<div class="lableLine350">
						<div class="lableWidth120">纬度</div>
						<input name="latitude" id="latitude" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>
					<div class="lableLine350">
						<div class="lableWidth120">公司链接</div>
						<input name="memberurl" id="memberurl" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>
					
				</td>
				<td>
					<img src="" style="width:200px;height:200px;visibility:hidden;" id="miniatureView" >
				</td>
			</tr>
			<tr>
				<td colspan="2" >		
					<div id="div_file1">
							<ul class="PoyBt FileItem file1"  style="padding-left:0px;border:0px solid blue;"  id="file1">
								<li style="height: 32px;text-align:left; border:0px solid red; width:72px" class="Poy_yi">图片</li>
								<li class="Poy_sa ProgressBar" id="">
									<dl>
										<p class="ProgressBarValue">
										</p><p class="ProgressBarIcon"> 
										</p><p class="ProgressBarMsg"></p>
									</dl>
								</li>
								<li style="height: 26px;margin-top:4px;" class="Poy_yi" >
									<a href="javascript:void(0);"> 
										<input id="file_upload" name="file1" type="file" data-options="required:true" />
										
									</a>
								</li>
								
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:230px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: pointer;"></p>
									<p style="color:red; float:right;"></p>
								</li>
								
							</ul>
							<input type="hidden" id="miniature" name="miniature" />
						</div>	
				</td>
				
			</tr>
				
			<tr>
			
				<td colspan="2">
					<div class="lableLine650">
						<div class="lableWidth120">公司详情介绍</div>
						<div class="lableLine530">
							<input name="detailintro" id="detailintro" class="ckeditor" />
						</div>
					</div>
					<div class="clear"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div style="float: right;width:550px;">
                         <a href="javascript:void(0)" 
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="save()">保存</a> <a
							href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px" id="btnCancel"
							class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="cancel()">取消</a>
					</div>
				</td>
			</tr>

		</table>
		
	</form>

	<script type="text/javascript">
		//<![CDATA[

		CKEDITOR
				.replace(
						'detailintro',
						{
							image_previewText:'',
							uiColor : '#9AB8F3',
							filebrowserBrowseUrl : '/uploadImage/ckfinder/ckfinder.html',
							filebrowserImageBrowseUrl : '/uploadImage/ckfinder/ckfinder.html?type=Images',
							filebrowserFlashBrowseUrl : '/uploadImage/ckfinder/ckfinder.html?type=Flash',
							filebrowserUploadUrl : '/uploadImage/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
							filebrowserImageUploadUrl : '/uploadImage/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
							filebrowserFlashUploadUrl : '/uploadImage/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
						});
		//]]>
	</script>
	
</body>
</html>
