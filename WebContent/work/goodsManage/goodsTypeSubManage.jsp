<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>商品类别管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/goodsTypeManageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>

<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<!-- 图片上传 -->
<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>

<!--	富文本框样式-->
<script type="text/javascript"
	src="<%=basePath%>/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/ckfinder/ckfinder.js"></script>

<style type="text/css">
a{color:blue;}
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
.lableWidth888{
	width: 72px;
	float: left;
	white-space: nowrap;
	margin-top: 5px
}

.lableWidth150 {
	width: 90px;
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

.lableWidth170 {
	width: 100px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableLine350 {
	width: 260px;
	float: left;
	padding-top: 8px
}
.lableLine650 {
	width: 740px;
	float: left;
	margin-top: 15px;
}
.lableLine1050 {
	width: 750px;
	float: left;
	padding-top: 0px;
	margin-top:0px;
	border:0px solid red;
}
.lableLine530 {
	width: 550px;
	float: left;
	margin-top: 15px;
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
      
//easyui接收jsp页面传值,点击修改传过来的id
var para = easyui_getRequestPara();
var id = para.id;
// alert(id);
//<!---------------商品类别增加、修改JS start--------------->
//自动加载js
var map = {};
$(function(){
	
// alert(cid);
// 	根据cid查询数据,做修改前处理。
		if (id!=null&&id!="") {
			goodsTypeManageAction.getById(id, {
				async : false,
				callback : function(data) {
					if (data.success) {
						$('#fm').form('load', data.data);

						//如果logo不为空,把logo放到id为imageView下的src中
						if(data.data.image!=null&&data.data.image!=""){
						$('#imageView').attr('src',data.data.image);
						$('#imageView').css("visibility","");

						}
						map = data.data;

					} else {

						$.messager.alert("提示", data.msg);
					}
				}
			});

		}

});



//商品类别新增或者修改
function saveOrUpdate() {
	
    //校验表单
	var isValid = $("#fm").form('validate');
	if (!isValid) {
		$.messager.alert('警告', "请将红框内的数据修改正确！", 'warning');
		return;
	}

	//map传值方式
//		map['id'] = id;
	//遍历表单数据
	$.each($("#fm").find("input[name],textarea[name],select[name]"), function() {
		//控件类型
		if ($(this).hasClass("easyui-combobox")
				&& $(this).combobox("getValue") != "") {
			map[$(this).attr("name")] = $
					.trim($(this).combobox("getValue"));
		} else {
			map[$(this).attr("name")] = $.trim($(this).val());
		}
	});

	goodsTypeManageAction.saveOrUpdate(map, {

		async : false,
		callback : function(data) {
			if (data.success) {
				$.messager.alert('提示', '保存成功!', '', function(f) {
					easyui_closeTopWindow(data.data);
				});
			} else {
				$.messager.alert('警告', data.msg, 'warning');
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
 //重置
function resetBtnClick(){
	 
			//fm是form的id名 
			$('#fm').form('clear');
	 
		}
 
 //图片上传
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
			uploadUrl:'<%=basePath%>/ClubfileImage/uploadImage.do',			
			uploadParam:{cid:cid,path:$("#image").val()},
			onUpload :function(){ 
				var path=$("#image").val();
				var pm ={cid:cid,path:path};
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
 
//<!---------------商品类别增加、修改JS end--------------->			
	</script>
<script type="text/javascript">

//表单js失焦校验 
function sortValidate(){
	//会员数量整型校验
	var sort=$("#sort").val();
	var r = /^\+?[1-9][0-9]*$/;
    if(!r.test(sort)){
        alert("类别排序是一个整数!");
        $("#sort").focus();
        return false;
    }
}

</script>
</head>

<body style="font-size: 12px;height:100px;width:700">
<!-- 商品类别修改界面start-->
	<form id="fm" method="post">
		<div style="width: auto;margin-top:-100px">
		<div class="lableLine320" hidden="hidden">
				<div class="lableWidth100">一级类别id</div>
				<input id="id" name="id" class="easyui-validatebox" 
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">一级类别名称</div>
				<input id="name" name="name" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">类别标记</div>
				<input id="remark" name="remark" class="easyui-validatebox" style="height: 19px"
				     data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">类别排序</div>
				<input id="sort" name="sort" class="easyui-validatebox" onblur="sortValidate()" style="height: 19px"
				    data-options="required:true,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">类别url</div>
				<input id="url" name="url" class="easyui-validatebox" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">类别是否显示</div>
            <select id="isopen" name="isopen" style="width:150px;height: 21px" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">          		
							<option value="">--请选择是否显示--</option>
							<option value="1">显示</option>
							<option value="0">不显示</option>	
            </select>
			</div>
	
		    <div class="lableLine320">
				<div class="lableWidth120">tag1</div>
				<input id="tag1" name="tag1" class="easyui-validatebox" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">tag2</div>
				<input id="tag2" name="tag2" class="easyui-validatebox" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">tag3</div>
				<input id="tag3" name="tag3" class="easyui-validatebox" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">tag4</div>
				<input id="tag4" name="tag4" class="easyui-validatebox" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">tag5</div>
				<input id="tag5" name="tag5" class="easyui-validatebox" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
					    <div class="lableLine320">
				<div class="lableWidth120">类别描述</div>     
				<textarea id="description" name="description" rows="3" cols="100" 
				      data-options="required:false,validType:'length[1,200]'" />
				</textarea >
			</div>
			
<!-- 			图片上传  start-->
			<div id="div_file1">
							<ul class="PoyBt FileItem file1"  style="padding-left:0px;border:0px solid blue; margin-top:100px;"  id="file1">
								<li style="height: 32px;text-align:left; border:0px solid red; width:72px" class="Poy_yi">课程logo</li>
								<li class="Poy_sa ProgressBar" style="width:150px;margin-left:12px">
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
								
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:100px;margin-left:0px;margin-top:0px">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: pointer;"></p>
									<img  src="" style="margin-left: -50px; width: 100px; line-height: 23px; height: 100px; margin-top: 50px;visibility:hidden;" id="imageView">
								</li>
								<li>
									<p style="color:red; float:right;margin-right:100px">图片要求宽90像素*高100像素</p>
								</li>
							</ul>
							<input type="hidden" id="image" name="image"/>
							
						</div>	

<!-- 			图片上传  end-->	

<div style="margin-left:350px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 80px; height: 30px; margin-right: 0px; margin-top: 100px"
							id="" data-options="iconCls:'icon-undo'"
							onclick="resetBtnClick()">重置</a> 
						<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 0px; margin-top: 100px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="saveOrUpdate()">保存</a> 
						<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 0px; margin-top: 100px" id=""
							class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="cancel()">取消</a>
					</div>
		</div>
	</form>

</body>
</html>
