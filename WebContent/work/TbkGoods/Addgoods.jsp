<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>商品管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/goodsManageAction.js'></script>
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
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>

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
a {
	color: blue;
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

.lableWidth120 {
	width: 84px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableWidth888 {
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
	margin-top: 0px;
	border: 0px solid red;
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

input.f {
	width: 380px;
}
</style>
<script type="text/javascript">
      
//easyui接收jsp页面传值,点击修改传过来的aid
var para = easyui_getRequestPara();
var id = para.id;
//var cid = para.cid;
//<!---------------课程增加、修改JS start--------------->
//自动加载js
var map = {};
$(function() {
	
// 	根据aid查询数据,做修改前处理。
		if (id !=null&&id!="") {
			goodsManageAction.getById(id, {
				async : false,
				callback : function(data) {
					if (data.success) {
						$('#fm').form('load', data.data);
						//后台处理后,时间单独加载
// 						$("#activityStartTime").attr('value',data.activityStartTime);
// 						$("#activityEndTime").attr('value',data.activityEndTime);
// 						$("#couponStartTime").attr('value',data.couponStartTime);
// 						$("#couponEndTime").attr('value',data.couponEndTime);

						//如果logo不为空,把logo放到id为imageView下的src中
						if(data.data.miniature!=null&&data.data.miniature!=""){
						$('#miniatureView').attr('src',data.data.miniature);
						$('#miniatureView').css("visibility","");

						}
						map = data.data;
						//富文本单独处理
						CKEDITOR.instances.detailintro.setData(data.data.detailintro);
					//	CKEDITOR.instances.mobileContent.setData(data.data.mobileContent);
					} else {

						$.messager.alert("提示", data.msg);
					}
				}
			});

		}

});

//新增或者修改
function baocun() {
	
// alert(112);
    //校验表单
	var isValid = $("#fm").form('validate');
	if (!isValid) {
		$.messager.alert('警告', "请将红框内的数据修改正确！", 'warning');
		return;
	}
	//遍历表单数据
	$.each($("#fm").find("input[name],textarea[name],select[name]"), function() {
		//控件类型
		if ($(this).hasClass("easyui-combobox")
				&& $(this).combobox("getValue") != "") {
			map[$(this).attr("name")] = $.trim($(this).combobox("getValue"));
		} else {
			map[$(this).attr("name")] = $.trim($(this).val());
		}
	});
   //富文本框的值存入map
	map['detailintro']=CKEDITOR.instances.detailintro.getData();
//	map['mobileContent']=CKEDITOR.instances.mobileContent.getData();
	//新增时传社团cid
	map['id'] = id;
// 	alert(112);
// 	alert(id+"这个id没定义，新增");
	goodsManageAction.saveOrUpdate(map, {

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
			uploadUrl:'<%=basePath%>/goodsfileupload/uploadimage.do', 
			uploadParam:{id:id,path:$("#miniature").val()},
			onUpload :function(){ 
				var path=$("#miniature").val();
				var pm ={id:id,path:path};
				//alert(pm);
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
							folder : '/uploads',
							method : 'POST',
							multi : false,
							queueSizeLimit : 5,
							auto : false,
							fileExt : '*.png;*.jpg;*.gif',
							fileDesc : '图片文件(*.png;*.jpg;*.gif)',
							height : 26,
							width : 92,
							sizeLimit : (1024 * 1024 * 100),
							fileNameLen : 20,
							queueID : 'custom-queue',
							removeCompleted : false
						});
	});

	//  自动加载js,查询省
	$(function() {
		goodsManageAction.getCategoryId({}, {
			async : false,
			callback : function(data) {
				// 				var jsonObj=eval("("+data+")"); //转Obj 	
				$.each(data, function(i, item) {

					$("#id").append(
							"<option value='"+item.id+"'>"
									+ item.name + "</option>");
				});
			}
		});
	});

	//根据省provinceId查询市
	function searCity() {

		map['id'] = $("#id").val();
		$("#parentId").empty();//清空以选择
	goodsManageAction.getParaentId(map, {
			async : false,
			callback : function(data) {
				$.each(data, function(i, item) {

					$("#parentId").append(
							"<option value='"+item.id+"'>" + item.name
									+ "</option>");

				});
			}
		});
	}
//<!---------------课程增加、修改JS end--------------->
</script>


<script type="text/javascript"> 
// 	//表单js失焦校验 
// 	function validate() {
// 		//课时整型校验
// 		var day = $("#day").val();
// 		var r = /^\+?[1-9][0-9]*$/;
// 		if (!r.test(day)) {
// 			alert("课时是一个整数!");
// 			$("#day").focus();
// 			return false;
// 		}
// 	}
// 	$(function() {
// 		//时间校验,获取当前时间。
// 		var curr_time = new Date();
// 		strDate = curr_time.getFullYear() + "-";
// 		strDate += curr_time.getMonth() + 1 + "-";
// 		strDate += curr_time.getDate() + " ";
// 		strDate = new Date(strDate.replace(/-/g, "/"));
// 		//报名开始时间校验
// 		$('#activityStartTime').datebox(
// 				{
// 					onSelect : function(date) {
// 						var activityStartTime = new Date(
// 								$("#activityStartTime").datebox('getValue').replace(/-/g, "/"));
// 						var activityEndTime = new Date($("#activityEndTime").datebox('getValue').replace(/-/g, "/"));
// 						if (activityEndTime < activityStartTime) {

// 							$.messager.alert('警告！', '活动开始时间大于活动结束时间！',
// 									'warning');
// 							return false;
// 						}
// 					}
// 				});
// 		//活动结束时间校验
// 		$('#activityEndTime').datebox(
// 				{
// 					onSelect : function(date) {
// 						var activityStartTime = new Date(
// 								$("#activityStartTime").datebox('getValue').replace(/-/g, "/"));
// 						var activityEndTime = new Date($("#activityEndTime").datebox('getValue').replace(/-/g, "/"));
// 						if (activityStartTime > activityEndTime) {
// 							$.messager.alert('警告！', '优惠券结束时间小于优惠券开始时间！',
// 									'warning');
// 							return false;
// 						}
// 						if (strDate > activityEndTime) {
// 							$.messager
// 									.alert('警告！', '优惠券结束时间小于当前时间！', 'warning');
// 							return false;
// 						}
// 					}
// 				});

// 		//课程开始时间校验
// 		$('#couponStartTime').datebox(
// 				{
// 					onSelect : function(date) {
// 						var couponStartTime = new Date($("#couponStartTime").datebox('getValue').replace(/-/g, "/"));
// 						var couponEndTime = new Date($("#couponEndTime").datebox('getValue').replace(/-/g, "/"));
// 						if (couponEndTime < couponStartTime) {
// 							$.messager.alert('警告！', '优惠券开始时间大于优惠券结束时间！',
// 									'warning');
// 							return false;
// 						}
// 					}
// 				});
// 		//课程结束时间校验
// 		$('#couponEndTime').datebox(
// 				{
// 					onSelect : function(date) {
// 						var couponStartTime = new Date($("#couponStartTime").datebox('getValue').replace(/-/g, "/"));
// 						var couponEndTime = new Date($("#couponEndTime").datebox('getValue').replace(/-/g, "/"));
// 						if (couponStartTime > couponEndTime) {
// 							$.messager.alert('警告！', '优惠券结束时间小于优惠券开始时间！',
// 									'warning');
// 							return false;
// 						}
// 						if (strDate > couponEndTime) {
// 							$.messager.alert('警告！', '优惠券结束时间小于优惠券开始时间！',
// 									'warning');
// 							return false;
// 						}
// 					}
// 				});
// 	});
 </script>
<script type="text/javascript">

function validate() {
	//课时整型校验
		var nihao = $("#price").val();
		var r = /^\+?[1-9][0-9]*$/;
		if (!r.test(nihao)) {
			alert("价格是一个整数!");
			$("#price").focus();
			return false;
		}
	}


</script>

<script type="text/javascript">

function validateproviderId() {
	//课时整型校验
		var goodsprovider = $("#providerId").val();
		var r = /^\+?[1-9][0-9]*$/;
		if (!r.test(goodsprovider)) {
			alert("会员单位编号填写一个整数!");
			$("#providerId").focus();
			return false;
		}
	}


</script>





<script type="text/javascript">
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
		 $("#pp").show();
}	
else if(usertype==1){
		$("#pp").show();
	}else{
		$("#pp").hide();
	}
});
</script>









</head>

<body style="font-size: 12px; height: 400px">
	<!-- 课程修改界面start-->
	<form id="fm" method="post">
		<div style="width: auto; margin-top: 0px">
			<div class="lableLine320">
				<div class="lableWidth120" style="width:70px;">商品名称</div>
				<input id="name" name="name" class="easyui-validatebox"
					style="height: 19px"
					data-options="required:true,validType:'length[1,50]'" />
			</div>

			<div class="lableLine320">
				<div class="lableWidth120" style="width:70px;">标题</div>
				<input id="title" name="title" class="easyui-validatebox"
					style="height: 19px"
					data-options="required:true,validType:'length[1,50]'" />
			</div>
			
			<div class="lableLine320" id="pp">
				<div class="lableWidth120" style="width:70px;">会员单位编号</div>
				
				<input id="providerId" name="providerId"   class="easyui-validatebox"
					style="height: 19px"
					data-options="required:false,validType:'length[1,50]'" />
			</div>

			<div class="lableLine320">
				<div class="lableWidth120" style="width:70px;">商品大类别</div>
				<select id="id" name="id" onChange="searCity()"
					style="width: 150px; height: 21px" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,20]'">
					<option value="id" selected>--请选择类别--</option>
				</select>
			</div>
			<div class="lableLine320">
				<div class="lableWidth120" style="width:70px;">商品子类别</div>
				<select id="parentId" name="categoryId"
					style="width: 150px; height: 21px" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,20]'">
					<option value="" selected>--请选择子类别--</option>
				</select>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width:70px;">类型</div>

				<select name="type" id="type" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,20]'"
					style="width: 150px;">
					<option value="normal" selected="selected">普通商品</option>
					<option value="sale">促销商品</option>
					<option value="provider">企业</opption>
					<option value="society">社团</option>
					<option value="house">房产</option>
					<option value="auction">竞价</option>
					<option value="score">积分</option>

				</select>
			</div>
			<div class="lableLine350">
				<div class="lableWidth120" style="width:70px;">状态</div>
				<select name="status" id="status" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,20]'"
					style="width: 150px;">
					<option value="0">初始状态</option>
					<option value="1">下载订单</option>
					<option value="2">供应量</option>
					<option value="3">下架</option>
				</select>
			</div>
			<div class="lableLine320">
				<div class="lableWidth120" style="width:46px;">价格&nbsp;&nbsp;</div>
				<input id="price" name="price"  onblur="validate()"class="easyui-validatebox"
					style="height: 19px;width:150px;"
					data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120" style="width:46px;">单位</div>
				<input id="unit" name="unit" class="easyui-validatebox"
					style="height: 19px;width:150px;"
					data-options="required:false,validType:'length[1,50]'" style="margin-left:50px;" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120" style="width:70px;">重量</div>
				<input id="unit" name="unit" class="easyui-validatebox"
					style="height: 19px;width:150px;"
					data-options="required:false,validType:'length[1,50]'" style="margin-left:50px;" />
			</div>
				<div class="lableLine320">
				<div class="lableWidth120" style="width:70px;">大小</div>
				<input id="size" name="size" class="easyui-validatebox"
					style="height: 19px;width:150px;"
					data-options="required:false,validType:'length[1,50]'" style="margin-left:50px;" />
			</div>
				<img src="" style="width:100px;height:100px;visibility:hidden;margin-left:-70px;margin-top:100px;" id="miniatureView" >
				<div id="div_file1">
							<ul class="PoyBt FileItem file1"  style="padding-left:0px;border:0px solid blue;margin-top:-70px;"  id="file1">
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
			
			
			
			
			
				<div class="lableLine650">
						<div class="lableWidth120">商品简介</div>
						<div class="lableLine530">
							<input name="detailintro" id="detailintro" class="ckeditor" />
						</div>
					</div>
					<div class="clear"></div>
			

		</div>

		<div style="margin-left: 450px;">
			<a href="javascript:void(0)"
				style="width: 80px; height: 30px; margin-right: 125px; margin-top: 90px"
				id="btnSave" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="baocun()">保存</a> <a
				href="javascript:void(0)"
				style="width: 80px; height: 30px; margin-left: -80px; margin-top: 90px"
				id="" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="cancel()">取消</a>
		</div>
		</div>
	</form>
	<!-- 	<script type="text/javascript"> -->
	<script type="text/javascript">
		//<![CDATA[

		CKEDITOR
				.replace(
						'detailintro',
						
						{
							height:'500px',
							width: '100%',
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
