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
	src='<%=basePath%>/dwr/interface/newsAction.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/fileDeleteAction.js'></script>
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
	src='<%=basePath%>/dwr/interface/newsAction.js'></script>
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<!--	富文本框样式-->
<script type="text/javascript"
	src="<%=basePath%>/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/ckfinder/ckfinder.js"></script>
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
			uploadUrl:'<%=basePath%>/fileupload/uploadimage.do', 
			uploadParam:{id:id,path:$("#image").val()},
			onUpload :function(){ 
				var path=$("#image").val();
				var pm ={id:id,path:path};
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

<script type="text/javascript">
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
		 $("#nihao").show();
}	
else if(usertype==1){
		$("#nihao").show();
	}
else{
	$("#nihao").hide();

}
	
	
	
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
	border: 0px solid red;
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

//表单js失焦校验 
function validate(){
	//课时整型校验
	var provider_id=$("#provider_id").val();
	var r = /^\+?[1-9][0-9]*$/;
    if(!r.test(provider_id)){
        alert("会员单位编号填写一个整数!");
        $("#provider_id").focus();
        return false;
    }
}



</script>


<script type="text/javascript">
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
		 $("#type").show();
}	
else if(usertype==1){
		
	$("#tigong").hide();
	$("#xuqiu").hide();
	$("#jingcai").hide();
	$("#qiye").hide();
	$("#zixun").hide();
	}
else{
	$("#xiazai").hide();
	$("#zonghe").hide();
	$("#kuaiche").hide();
	$("#huodong").hide();
	$("#fangchan").hide();
	$("#kuaijie").hide();
	$("#kefu").hide();
	
}
});
</script>





<script type="text/javascript">

		//保存用户信息
		var map={};
	
		$(function(){
			//修改的预查询
			
			if(id!=null && id!=""){
			//	alert("这里的id不为空"+id);
				//alert("nid你好，我知道你没定义"+nid);
			//	alert("caid你最好"+caid);
			newsAction.getByid(id,{
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
							alert(data.data.status+"你好");
							//	if(data.data.status==1){
							//	$('#memqq').css("visibility","hidden");
							//}
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
			map['isScroll']=$.trim($('#isScroll').val());
	        map['content']=CKEDITOR.instances.content.getData();
	        map['status']=$.trim($('#status').val());
	        map['url']=$.trim($('#url').val());
	        map['type']=$.trim($('#type').val());
	        map['provider_id']=$.trim($('#provider_id').val());
	      //  map['mobilecontent']=CKEDITOR.instances.mobilecontent.getData();
	      //  map['onhome']=$.trim($('#onhome').val());
	     	map['briefintro']=$.trim($('#briefintro').val());
	     	//map['id']$.trim($('#id').val());
	     //	alert(image);
	   	   map['image']=$.trim($('#image').val());
	     //	alert($('#image').val());
	    //  map['caid']=$.trim($('#caid').val());	
		
				map['id']=id;
				//alert(id);
				//alert("注意id到这了。这里的id为更新的id")
			//	map['caid']=caid;
				newsAction.SaveOrUpdate(map,{
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
<script type="text/javascript">
$(document).ready(function(){ 
	$('#status').change(function(){ 
	//alert($(this).children('option:selected').val()); 
	if($(this).children('option:selected').val()==1){
		$('#memqq').hide();
	}else{
		$('#memqq').show();
	}
	
	}) 
}) 
</script>










</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
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


					<div class="lableLine350" id="nihao">
						<div class="lableWidth120">会员单位id</div>
						<input name="provider_id" id="provider_id" onblur="validate()"
							style="width: 350px;" class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />

					</div>

					<div class="clear"></div>

					<div class="lableLine350">
						<div class="lableWidth120">是否置顶</div>
						<select name="isScroll" id="isScroll" class="easyui-validatebox"
							data-options="required:true,validType:'length[1,20]'">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>

						</select>
					</div>
					<div class="clear"></div>

					<div class="lableLine350">
						<div class="lableWidth120">新闻状态</div>
						<select name="status" id="status" class="easyui-validatebox"
							data-options="required:true,validType:'length[1,20]'">
							<option value="">请选择</option>
							<option value="0">链接类型</option>
							<option value="1">普通类型</option>
						</select>
					</div>
					<div class="clear"></div>
					<div class="lableLine350 " id="memqq">
						<div class="lableWidth120">链接地址</div>
						<input name="url" id="url" style="width: 350px;"
							class="easyui-validatebox"
							data-options="required:false,validType:'length[1,100]'" />
					</div>
					<div class="clear"></div>

					<div class="lableLine350">
						<div class="lableWidth120">新闻类型</div>
						<select name="type" id="type" class="easyui-validatebox"
							data-options="required:true,validType:'length[1,20]'">
							<option value="">请选择</option>
							<option value="0" id="qiehuan">图片切换新闻</option>
							<option value="2" id="zonghe">综合资讯（通知）</option>
							<option value="3" id="kuaiche">快车网资讯新闻</option>
							<option value="5" id="fangchan">房产信息</option>
							<option value="6" id="kuaijie">快捷服务</option>
							<option value="7" id="kefu">客服中心</option>
							<option value="8" id="tigong">提供新闻</option>
							<option value="9" id="xuqiu">需求新闻</option>
							<option value="10" id="jingcai">精彩回顾</option>
							<option value="11" id="qiye">企业公告</option>
							<option value="12" id="zixun">企业咨询</option>
						</select>
					</div>
					<div class="clear"></div> <!-- 					<div class="lableLine350"> -->
					<!-- 					<div class="lableWidth120" >是否滚动</div> --> <!-- 					<select name="is_scroll" id="is_scroll" class="easyui-validatebox" -->
					<!-- 							data-options="required:true,validType:'length[1,20]'" style="width:70px;"> -->
					<!-- 							<option value="N" selected="selected">否</option> --> <!-- 							<option value="Y" >是</option> -->
					<!-- 					</select> --> <!-- 					</div> --> <!-- 						<div class="clear"></div> -->
					<div class="lableLine350">
						<div class="lableWidth120">简介</div>
						<textarea id="briefintro" rows="5" cols="67" style="width: 350px;"
							name="briefintro" class="textarea easyui-validatebox"
							data-options="required:true,validType:'length[1,250]'"></textarea>
					</div>
				<td><img src=""
					style="width: 200px; height: 200px; visibility: hidden;"
					id="imageView"></td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="div_file1">
						<ul class="PoyBt FileItem file1"
							style="padding-left: 0px; border: 0px solid blue;" id="file1">
							<li
								style="height: 32px; text-align: left; border: 0px solid red; width: 72px"
								class="Poy_yi">图片</li>
							<li class="Poy_sa ProgressBar" id="">
								<dl>
									<p class="ProgressBarValue"></p>
									<p class="ProgressBarIcon"></p>
									<p class="ProgressBarMsg"></p>
								</dl>
							</li>
							<li style="height: 26px; margin-top: 4px;" class="Poy_yi"><a
								href="javascript:void(0);"> <input id="file_upload"
									name="file1" type="file" data-options="required:true" />

							</a></li>

							<li class="Poy_si"
								style="padding-top: 0px; height: 32px; width: 230px;">
								<p class="Btn UploadBtn NomalUploadBtn" style="cursor: pointer;"></p>
								<p style="color: red; float: right;"></p>
							</li>

						</ul>
						<input type="hidden" id="image" name="image" />
					</div>
				</td>

			</tr>

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
				<td colspan="2"><input type="hidden" name="id" id="id" />
					<div style="float: right; width: 550px;">
						<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-left: 300px; margin-top: 17px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="save()">保存</a> <a
							href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-left: 60px; margin-top: 17px"
							id="btnCancel" class="easyui-linkbutton"
							data-options="iconCls:'icon-cancel'" onclick="cancel()">取消</a>

					</div></td>
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
