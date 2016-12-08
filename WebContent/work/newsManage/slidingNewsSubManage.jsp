<%@ page language="java" import="java.util.*,com.tcj.common.util.*"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>课程管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
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
	src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
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
var nid=para.nid;
var caid=para.caid;
 
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
			uploadParam:{nid:nid,path:$("#image").val()},
			onUpload :function(){ 
				var path=$("#image").val();
				var pm ={nid:nid,path:path,caid:caid};
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

.lableWidth120{width:72px;float:left;white-space: nowrap;margin-top:5px}

.lableLine350{width:520px;float:left;margin-top:15px;}


	.lableLine530{width:640px;float:left;margin-top:15px;}
		
		
		.lableLine650{width:740px;float:left;margin-top:15px;}






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

//根据aid查询数据,做预修改处理。
var map={};
$(function(){
	//如果aid不等于空,aid传向后台。
	if(caid!=null&&nid!=null){		
	//	alert("nid="+nid);
	//	alert("caid="+caid);
		newsManageAction.getBynid(nid,caid,{
    			async : false,
				callback : function(data) {
					if(data.success){
						$('#fm').form('load',data.data);
						$('#imageView').attr('src',data.data.image);//把image值放到id为imageView下的src中
						$('#imageView').css("visibility","");
						//副文本框中得到值要用data.data.content
						   CKEDITOR.instances.content.setData(data.data.content);
						map=data.data;
						showButton(data.data.status);
					}else{
						$.messager.alert("提示",data.msg);
					}
				}	
    		});
	}else{
		showButton(status)
	}
	
});

function showButton(status){
	//alert(status);
	//如果状态为删除1
	if(status=="1"){
		$("#btnSave").css('visibility','hidden');
		$("#btnCancel").css('visibility','hidden');
		
	}else{
		$("#btnRestore").css('visibility','hidden');
		//$(filePath+"View").css('visibility','visible');
	}
}
		
		
		
		
		
		
		
		
// 		//新增或者修改保存
		function save(){
			
			var isValid=$("#fm").form('validate');
		
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			if($.trim($('#image').val())=="")
			{
				$.messager.alert('警告', "请上传图片！",'warning');	
				return;
			}
			if(CKEDITOR.instances.content.getData()=="")
			{
				$.messager.alert('警告', "请输入内容！",'warning');	
				return;
			}	
			map['title']=$.trim($('#title').val());
			/*map['englishTitle']=$.trim($('#englishTitle').val());	*/
	        map['content']=CKEDITOR.instances.content.getData();
	        map['onhome']=$.trim($('#onhome').val());
	    	
	      map['briefintro']=$.trim($('#briefintro').val());
	        /*map['url']=$.trim($('#url').val());*/
	        map['image']=$.trim($('#image').val());
	        map['miniture']=$.trim($('#miniture').val());
	 
	        		map['caid']=$.trim($('#caid').val());	

			
	
				map['nid']=nid;
				map['caid']=caid;
				newsManageAction.saveOrUpdate(map,{
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
		function cancel(){
			easyui_closeTopWindow("详情页面",function(result){
				if(result){
					$("#dg").datagrid('load');
				}
			});
			 
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
  <form id="fm" method="post">
	  <table border="0">
	  <tr>
		<td>
		<div class="lableLine350">
			<div class="lableWidth120">
			标题
			</div>
			<input name="title" id="title"  style="width:350px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,200]'" />
		</div>
	    <div class="clear"></div>
	    
<!-- 	    <div class="lableLine350"> -->
<!-- 			<div class="lableWidth120"> -->
<!-- 			英文标题 -->
<!-- 			</div> -->
<!-- 			<input name="englishTitle" id="englishTitle"  style="width:350px;" class="easyui-validatebox" data-options="required:false,validType:'length[1,200]'" /> -->
<!-- 		</div> -->
<!-- 	    <div class="clear"></div> -->
	    
	    
<!-- 	    <div class="lableLine350"> -->
<!-- 			<div class="lableWidth120"> -->
<!-- 			详情链接 -->
<!-- 			</div> -->
<!-- 			<input name="url" id="url"  style="width:350px;" class="easyui-validatebox" data-options="required:false,validType:'length[1,200]'" /> -->
<!-- 		</div> -->
<!-- 	    <div class="clear"></div> -->
	      <div class="lableLine350">
			<div class="lableWidth120">
				是否首页显示
			</div>		 
		 <select name="onhome"  id="onhome"  class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'" >
			 <option value="1" selected="selected">首页显示</option>
			 <option value="0" >首页不显示</option>  
			 </select>
		</div>
	   <div class="clear"></div>
	   </td>
	   <td>
	  	<img style="width:150px;height:90px; visibility:hidden;" id="imageView"/>
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
	                              <p style="color:red; float:right;">图片要求1920像素*1140像素</p>
								</li>	
							</ul>
							<input type="hidden" id="image" name="image" />
						</div>	
	   </td>
	   </tr>
	   <tr>
	   <td colspan="2">
		 <div class="lableLine650">
			<div class="lableWidth120">
				内容
			</div>
			<div class="lableLine530">
			<input name="content" id="content" class="ckeditor" />
		</div>
		</div>
		 <input id="caid" name="caid" type="hidden"  value="<%=request.getParameter("caid") %>" />
		<div class="clear"></div>
		</td></tr>
		<tr>
		  <td colspan="2">
	    <div style="float:right">
<!-- 	    	<a href="javascript:void(0)" class="easyui-linkbutton" -->
<!-- 				style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="" data-options="iconCls:'icon-undo'" -->
<!-- 				onclick="resetBtnClick()">重置</a> -->
			<a href="javascript:void(0)" style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="btnSave"
	             class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
	        <a  href="javascript:void(0)"  style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="btnCancel"
	             class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="cancel()" >取消</a>
	             <a href="javascript:void(0)" style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="btnRestore"
	             class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="restore(1)">恢复</a>
		</div>
	  </td>
	  </tr>
	   
	  </table>
	  
	</form>
	<script type="text/javascript">
	//<![CDATA[
	  
 
	CKEDITOR.replace( 'content',
		{
			 
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
