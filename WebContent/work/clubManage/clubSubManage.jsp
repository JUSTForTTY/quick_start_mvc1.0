<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>社团管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/clubManageAction.js'></script>
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
      
//判断用户类型
//'0'超级管理员;'1'快车网管理员;'2'会员单位管理;'3'社团管理员;'4'普通会员;
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){	
	$("#providerId").show();
	}else{
		$("#providerId").hide();
	} 
});
//easyui接收jsp页面传值,点击修改传过来的aid
var para = easyui_getRequestPara();
var cid = para.cid;
// alert(cid);
//<!---------------社团增加、修改JS start--------------->
//自动加载js
var map = {};
$(function(){
	
// alert(cid);
// 	根据cid查询数据,做修改前处理。
		if (cid!=null&&cid!="") {
			
			clubManageAction.getById(cid, {
				async : false,
				callback : function(data) {
					if (data.success) {
						$('#fm').form('load', data.data);

// 						alert("time"+data.startTime);
						//后台处理后,时间单独加载
						$("#feeStartDate").attr('value',data.startTime);

						//如果logo不为空,把logo放到id为imageView下的src中
						if(data.data.logo!=null&&data.data.logo!=""){
						$('#imageView').attr('src',data.data.logo);
						$('#imageView').css("visibility","");

						}
						map = data.data;

					} else {

						$.messager.alert("提示", data.msg);
					}
				}
			});

		}

//	 	加载会费开始时间
		$('#feeStartDate').datebox({
			"required":false
		});
});



//新增或者修改
function saveOrUpdate() {
	

    //校验表单
	var isValid = $("#fm").form('validate');
	if (!isValid) {
		$.messager.alert('警告', "请将红框内的数据修改正确！", 'warning');
		return;
	}

	//map传值方式
//		map['cid'] = cid;
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

	clubManageAction.saveOrUpdate(map, {

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
 
//<!---------------社团增加、修改JS end--------------->			
	</script>
<script type="text/javascript">

//表单js失焦校验 
function clubMemberSumValidate(){
	//会员数量整型校验
	var clubMemberSum=$("#clubMemberSum").val();
	var r = /^\+?[1-9][0-9]*$/;
    if(!r.test(clubMemberSum)){
        alert("会员数量是一个整数!");
        $("#clubMemberSum").focus();
        return false;
    }
}
//手机号校验
function checkMobile(){ 
	var mobile=$("#mobile").val();
    if(!(/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/.test(mobile))){ 
        alert("不是完整的11位手机号"); 
        $("#mobile").focus();
        return false; 
    } 
} 

</script>
</head>

<body style="font-size: 12px;height:100px;width:700">
<!-- 社团修改界面start-->
	<form id="fm" method="post">
		<div style="width: auto;margin-top:-100px">
		<div class="lableLine320" hidden="hidden">
				<div class="lableWidth100">社团id</div>
				<input id="cid" name="cid" class="easyui-validatebox" 
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">社团名称</div>
				<input id="name" name="name" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:true,validType:'length[1,50]'" />
			</div>
<!-- 			<div class="lableLine320"> -->
<!-- 				<div class="lableWidth120">社团类型</div> -->
<!-- 				<input id="type" name="type" class="easyui-validatebox" style="height: 19px" -->
<!-- 				     data-options="required:false,validType:'length[1,50]'" /> -->
<!-- 			</div> -->
						<div class="lableLine320">
				<div class="lableWidth120">社团会员数量</div>
				<input id="clubMemberSum" name="clubMemberSum" class="easyui-validatebox" onblur="clubMemberSumValidate()" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">管理员</div>
				<input id="administrator" name="administrator" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>	
			<div class="lableLine320">
				<div class="lableWidth120">手机号</div>
				<input id="mobile" name="mobile" class="easyui-validatebox"  onblur="checkMobile()" style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>	
			<div class="lableLine320">
				<div class="lableWidth120">操作员</div>
				<input id="operator" name="operator" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>	

			<div class="lableLine320">
				<div class="lableWidth120">社团口号</div>
				<input id="slogan" name="slogan" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>	

						<div class="lableLine320">
				<div class="lableWidth120">城市</div>
				<input id="city" name="city" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:false,validType:'length[1,50]'" />
			</div>	
			
			<div id="providerId" class="lableLine320">
				<div class="lableWidth120">会员单位Id</div>
				<input id="provider_id" name="provider_id" class="easyui-validatebox" style="height: 19px" 
				  data-options="required:false,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">公司单位</div>
				<input id="providername" name="providername" class="easyui-validatebox" style="height: 19px" 
				  data-options="required:false,validType:'length[1,50]'" disabled/>
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">创建人</div>
				<input id="create_user" name="create_user" class="easyui-validatebox" style="height: 19px" 
				  data-options="required:false,validType:'length[1,50]'" disabled/>
			</div>
<!-- 			<div class="lableLine320"> -->
<!-- 				<div class="lableWidth120">会费季度</div> -->
<!-- 				<input id="feeCircle" name="feeCircle" class="easyui-validatebox" style="height: 19px"  -->
<!-- 				  data-options="required:false,validType:'length[1,50]'" /> -->
<!-- 			</div> -->
         			
<!-- 			<div class="lableLine320"> -->
<!-- 				<div class="lableWidth120">会费开始时间</div> -->
<!-- 				<input id="feeStartDate" name="feeStartDate"   -->
<!-- 				    data-options="editable:false,required:true,validType:'length[1,50]'" /> -->
<!-- 			</div>			 -->
			
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
<!-- 								<li> -->
<!-- 									<p style="color:red; float:right;margin-right:100px">图片要求宽90像素*高100像素</p> -->
<!-- 								</li> -->
							</ul>
							<input type="hidden" id="image" name="image"/>
							
						</div>	

<!-- 			图片上传  end-->	
           <div class="lableLine320">
				<div class="lableWidth120">社团简介</div>     
				<textarea id="brief" name="brief" rows="3" cols="100" 
				      data-options="required:false,validType:'length[1,200]'" />
				</textarea >
			</div>	
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
