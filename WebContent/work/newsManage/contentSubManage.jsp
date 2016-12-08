<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>内容子界面</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css"
	      href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
	<script type="text/javascript"
	      src="<%=basePath%>/script/flash/artDialog.js"></script>
    <script type="text/javascript"
	      src="<%=basePath%>/script/flash/photoEditor.js"></script>
	      <!--	富文本框样式-->       
	<script type="text/javascript" src="<%=basePath%>/script/ckeditor/ckeditor.js"></script>	
	<script type="text/javascript" src="<%=basePath%>/script/ckfinder/ckfinder.js"></script> 


<script type="text/javascript">
var para= easyui_getRequestPara();
var nid=para.nid;
var caid=para.caid;

</script>

<style type="text/css">

.lableWidth120{width:72px;float:left;white-space: nowrap;margin-top:5px}

.lableLine350{width:720px;float:left;margin-top:15px;}


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

		//保存用户信息
		var map={};
	
		$(function(){
			//修改的
			
		if(caid!=null&&nid!=null){	
				
			newsManageAction.getByCaid(nid,caid,{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
								CKEDITOR.instances.content.setData(data.data.content);
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
			map['title']=$.trim($('#title').val());
			map['englishTitle']=$.trim($('#englishTitle').val());	
	        map['content']=CKEDITOR.instances.content.getData();
	        map['onhome']=$.trim($('#onhome').val());
	     //  map['briefintro']=$.item($('#briefintro').val());

	       //   map['image']=$.trim($('#image').val());	
	    //   map['onHome']=$.trim($('#onHome').val());
	        //       map['content']=CKEDITOR.instances.content.getData();
	        		map['caid']=$.trim($('#caid').val());	
		//	map['hyperlink']=$.trim($('#hyperlink').val());
		//	map['url']=$.trim($('#url').val());	
		//	map['keywords']=$.trim($('#keywords').val());	
	//	map['createUser']=$.trim($('#createUser').val());	
			
				map['nid']=nid;
				map['caid']=caid;
				newsManageAction.courseContentsaveOrUpdate(map,{
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
		
		
		

		function teacUpHeadPic(){
			showPhotoEditor(
				"<%=basePath%>/script/flash/photoEditor.swf","<%=basePath%>/picLoc.do","1","640","320");//id用来判别图片所属
		}
			
			function uploadStage(state,mess){
				artDg.close();
				var jsonstr = $.parseJSON(mess);
				var msg = jsonstr.msg;
				var picPath=jsonstr.picPath;
				{
				$("#cutImg").attr("src",'<%=basePath%>' + picPath);
				$("#cutImg").attr("url",picPath);
				$("#image").val(picPath);
			 
			$.messager.alert('提示', msg);
		}
	}
		
		
		//取消
		function cancel(){
			easyui_closeTopWindow("详情页面",function(result){
				if(result){
					$("#dg").datagrid('load');
				}
			});
			 
				}
		 //重置
		function resetBtnClick(){
			 
					//fm是form的id名 
					$('#fm').form('clear');
			 
				}
	</script>
  </head>
  <body style="font-size: 12px;">
  <form id="fm" method="post">
	  <table>
		<tr>
		<td>
		<div class="lableLine350">
			<div class="lableWidth120">
			标题
			</div>
			<input name="title" id="title"  style="width:350px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,200]'" />
		</div>
	    <div class="clear"></div>
	   </td>
	   </tr> 
	   <tr>
	   <td>
		 <div class="lableLine650">
			<div class="lableWidth120">
				内容
			</div>
			<div class="lableLine530">
			<input name="content" id="content" class="ckeditor" />
		</div>
		</div>
		 
		<div class="clear"></div>
		</td></tr>
		<tr>
		<td>
	    <div style="float:right">
<!-- 	    	<a href="javascript:void(0)" class="easyui-linkbutton" -->
<!-- 				style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="" data-options="iconCls:'icon-undo'" -->
<!-- 				onclick="resetBtnClick()">重置</a> -->
			<a href="javascript:void(0)" style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="btnSave"
	             class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
	        <a  href="javascript:void(0)"  style="margin-right: 150px;margin-top:17px" id=""
	             class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="cancel()" >取消</a>
		</div>
	  </td>
	  </tr>
	   
	  </table>
	  </div>
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
