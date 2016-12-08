<%@ page language="java" import="java.util.*,com.tcj.common.util.*"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>新闻管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<!-- <script type='text/javascript' -->
<%-- 	src='<%=basePath%>/script/easyui/dwrloader.js'></script> --%>
<%-- 	<script type="text/javascript" src='<%=basePath%>/js/jquery-1.8.3.min.js'></script> --%>
	
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
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
	src='<%=basePath%>/dwr/interface/newsTypeManageAction.js'></script>
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
//alert(id+"你好，id，你传到这个updatenews下面了");
//var status=para.status;

//alert("nid"+nid);
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
	$('#file_upload').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/file/upload.do', 
			uploadParam:{id:id,path:$("#image").val()},
			onUpload :function(){ 
				var path=$("#image").val();
				var pm ={id:id,path:path,caid:caid};
				//alert(pm);
				$('#file_upload').uploadPlugSettings('uploadParam',pm);
			},
			'onComplete':function(event, queueItem,ID, fileObj, response,data) {
				var obj = jQuery.parseJSON(response);
				dealFile("#image", obj);
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
			
			if(id!=null && id!=""){
			//	alert("这里的id不为空"+id);
				//alert("nid你好，我知道你没定义"+nid);
			//	alert("caid你最好"+caid);
			newsTypeManageAction.getByid(id,{
				async : false,
						callback : function(data) {
							if(data.success){
							//	alert("content"+data.content);
							    CKEDITOR.instances.content.setData(data.data.content);
							//    CKEDITOR.instances.mobilecontent.setData(data.data.mobilecontent);
							    //	map['content']=CKEDITOR.instances.content.getData();
								$('#fm').form('load',data.data);
								//alert(data.data.image);
								//alert( $('#imageView').src);
								$('#imageView').attr('src',data.data.image);//把image值放到id为imageView下的src中
								$('#imageView').css("visibility","");
						//		$('#content').css("visibility","");
								// $('#imageView')[0].src;
								// alert($('#imageView')[0].src);
							
								//alert($("#imageView").attr("src"));
								//$("img#image").attr("src");
 							//	alert("内容你好"+data.data.content+"===你好");
								map=data.data;
								//showButton(data.data.status);
							}else{
								$.messager.alert("提示",data.msg);
							}
						}	
            		});
				
			}
			
		});
		
// 		function showButton(status){
// 			//alert(status);
// 			//如果状态为删除1
// 			if(status=="1"){
// 				$("#btnSave").css('visibility','hidden');
// 				$("#btnCancel").css('visibility','hidden');
				
// 			}else{
// 				$("#btnRestore").css('visibility','hidden');
// 				//$(filePath+"View").css('visibility','visible');
// 			}
// 		}	
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
			
			if(CKEDITOR.instances.content.getData()=="")
			{
				$.messager.alert('警告', "请输入内容！",'warning');	
				return;
			}
			
			//组装map
			map['title']=$.trim($('#title').val());
			//map['englishTitle']=$.trim($('#englishTitle').val());	
	        map['content']=CKEDITOR.instances.content.getData();
	        map['url']=$.trim($('#url').val());
	        map['type']=$.trim($('#type').val());
	      //  map['mobilecontent']=CKEDITOR.instances.mobilecontent.getData();
	      //  map['onhome']=$.trim($('#onhome').val());
	     	map['briefintro']=$.trim($('#briefintro').val());
	     	//map['id']$.trim($('#id').val());
	     //	alert(image);
	   //	map['image']=$.trim($('#image').val());
	     //	alert($('#image').val());
	    //  map['caid']=$.trim($('#caid').val());	
		
				map['id']=id;
				//alert(id);
				//alert("注意id到这了。这里的id为更新的id")
			//	map['caid']=caid;
				newsTypeManageAction.SaveOrUpdate(map,{
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
	//重置
	function resetBtnClick() {

		//fm是form的id名 
		$('#fm').form('clear');

	}
	
	 
	//恢复
	function restore(flag){
		
	//	var map={};
		//map['flag']=flag;
		//map['nid']=nid;
		//alert(flag);
	//	alert(nid);
		var str;
			 str="恢复";
		$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
			if (r) {
				newsManageAction.Recovery(map,{
					async : false,
					callback : function(data) {
						if (data.success) {
							$.messager.alert('确认', str+'成功!');
							
							easyui_closeTopWindow(data.data);
						} else {
							$.messager.alert('警告', data.msg,'warning');
						}
					}
				});
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
						<div class="lableWidth120">标题</div>
						<input name="title" id="title" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:true,validType:'length[1,100]'" />

					</div> 
					<div class="clear"></div>

					<div class="lableLine350">
						<div class="lableWidth120">链接地址</div>
						<input name="url" id="url" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:true,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>
					<div class="lableLine350">
						<div class="lableWidth120">新闻类型</div>

						<select name="type" id="type" class="easyui-validatebox"
							data-options="required:true,validType:'length[1,20]'">
							<option value="0" selected="selected">图片切换新闻</option>
							<option value="1" >下载新闻</option>
							<option value="2" >综合资讯新闻</option>
							<option value="3" >快车网资讯新闻</option>
							<option value="4" >活动公告</option>
							<option value="5" >房产信息</option>
							<option value="6" >快捷服务新闻</option>
							<option value="7" >客服中心</option>
							
						</select>
					</div>
					<div class="clear"></div>
                         <div class="lableLine350">
						<div class="lableWidth120">简介</div>
						<textarea id="briefintro" rows="5" cols="67" style="width:350px;" name="briefintro" class="textarea easyui-validatebox" data-options="required:true,validType:'length[1,250]'"></textarea>
					</div>
				</td>		
			</tr>
			<tr>
			
				<td colspan="2">
					<div class="lableLine650">
						<div class="lableWidth120">内容</div>
						<div class="lableLine530">
							<input name="content" id="content" class="ckeditor" />
						</div>
					</div> 
					<div class="clear"></div>
				</td>
			</tr>
			
	
			<tr>
				<td colspan="2">
				<input type="hidden" name="id" id="id"/>
					<div style="float: right;width:550px;">
                         <a href="javascript:void(0)" 
							style="width: 80px; height: 30px; margin-left:300px;  margin-top: 17px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="save()">保存</a> <a
							href="javascript:void(0)"
							style="width: 80px; height: 30px;margin-left:60px; margin-top: 17px" id="btnCancel"
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
						'content',
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
