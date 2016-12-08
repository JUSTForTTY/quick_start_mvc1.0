<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>活动管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/activityManageAction.js'></script>
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
      
//easyui接收jsp页面传值,点击修改传过来的aid
var para = easyui_getRequestPara();
var aid = para.aid;
var cid = para.cid;
//<!---------------活动增加、修改JS start--------------->
//自动加载js
var map = {};
$(function() {
	
// 	根据aid查询数据,做修改前处理。
		if (aid !=null&&aid!="") {
			activityManageAction.getById(aid, {
				async : false,
				callback : function(data) {
					if (data.success) {
						$('#fm').form('load', data.data);
						

						//后台处理后,时间单独加载
						$("#registerStart").attr('value',data.registerStart);
						$("#registerEnd").attr('value',data.registerEnd);
						$("#startTime").attr('value',data.startTime);
						$("#endTime").attr('value',data.endTime);

						//如果logo不为空,把logo放到id为imageView下的src中
						if(data.data.logo!=null&&data.data.logo!=""){
						$('#imageView').attr('src',data.data.logo);
						$('#imageView').css("visibility","");

						}
						map = data.data;
						//富文本单独处理
						CKEDITOR.instances.actDetail.setData(data.data.actDetail);
						CKEDITOR.instances.mobileContent.setData(data.data.mobileContent);
					} else {

						$.messager.alert("提示", data.msg);
					}
				}
			});

		}

});

//新增或者修改
function saveOrUpdate() {
	

    //校验表单
	var isValid = $("#fm").form('validate');
	if (!isValid) {
		$.messager.alert('警告', "请将红框内的数据修改正确！", 'warning');
		return;
	}
	
	//时间校验
	var curr_time = new Date();
	strDate = curr_time.getFullYear()+"-";
	strDate +=curr_time.getMonth()+1+"-";
	strDate +=curr_time.getDate()+" ";
	strDate = new Date(strDate.replace(/-/g,"/"));		
	var startTime = new Date($("#startTime").datebox('getValue').replace(/-/g,"/"));
	var endTime = new Date($("#endTime").datebox('getValue').replace(/-/g,"/"));
	if(startTime>endTime){
		$.messager.alert('警告！','活动结束时间小于活动开始时间！','warning');
		return false;
	}
	var registerStart = new Date($("#registerStart").datebox('getValue').replace(/-/g,"/"));
	var registerEnd = new Date($("#registerEnd").datebox('getValue').replace(/-/g,"/"));
	if(registerStart>registerEnd){
		$.messager.alert('警告！','报名结束时间小于报名开始时间！','warning');
		return false;
	}
	
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
   //富文本框的值存入map
	map['actDetail']=CKEDITOR.instances.actDetail.getData();
	map['mobileContent']=CKEDITOR.instances.mobileContent.getData();
	//新增时传社团cid
	map['cid'] = cid;
	activityManageAction.saveOrUpdate(map, {

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
			uploadUrl:'<%=basePath%>/fileImage/uploadImage.do', 
			uploadParam:{aid:aid,path:$("#image").val()},
			onUpload :function(){ 
				var path=$("#image").val();
				var pm ={aid:aid,path:path};
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
 

//  自动加载js,查询省
 	$(function(){
 		activityManageAction.getProvince({},{
			async : false,
			callback : function(data){
// 				var jsonObj=eval("("+data+")"); //转Obj 	
				$.each(data,function(i,item){

	    			$("#provinceId").append("<option value='"+item.provinceid+"'>"+item.province+"</option>");
	    		});
	          }		
		});
	});

 //根据省provinceId查询市
 function searCity(){ 
 
 map['provinceId'] = $("#provinceId").val();
 $("#cityId").empty();//清空以选择
	activityManageAction.getCity(map,{
		async : false,
		callback : function(data){
			$.each(data,function(i,item){
			
    			$("#cityId").append("<option value='"+item.cityid+"'>"+item.city+"</option>");
    	
			});
          }		
	});
 }
 
//<!---------------活动增加、修改JS end--------------->			
	</script>
	

	<script type="text/javascript">
	
	//表单js失焦校验 
	function validate(){
		//活动用时整型校验
		var day=$("#day").val();
		var r = /^\+?[1-9][0-9]*$/;
	    if(!r.test(day)){
	        alert("活动用时是一个整数!");
	        $("#day").focus();
	        return false;
	    }
 }
	$(function(){
		//时间校验,获取当前时间。
		var curr_time = new Date();
		strDate = curr_time.getFullYear()+"-";
		strDate +=curr_time.getMonth()+1+"-";
		strDate +=curr_time.getDate()+" ";
		strDate = new Date(strDate.replace(/-/g,"/"));		
		//报名开始时间校验
		$('#registerStart').datebox({
			onSelect: function(date){
				var registerStart = new Date($("#registerStart").datebox('getValue').replace(/-/g,"/"));
				var registerEnd = new Date($("#registerEnd").datebox('getValue').replace(/-/g,"/"));
				if(registerEnd<registerStart){

                   $.messager.alert('警告！','报名开始时间大于报名结束时间！','warning');
					return false;
				}
			}
		});
		//报名结束时间校验
		$('#registerEnd').datebox({
			onSelect: function(date){
				var registerStart = new Date($("#registerStart").datebox('getValue').replace(/-/g,"/"));
				var registerEnd = new Date($("#registerEnd").datebox('getValue').replace(/-/g,"/"));
				if(registerStart>registerEnd){
		              $.messager.alert('警告！','报名结束时间小于报名开始时间！','warning');
					return false;
				}
				if(strDate>registerEnd){
		              $.messager.alert('警告！','报名结束时间小于当前时间！','warning');
					return false;
				}
			}
		});
		//活动开始时间校验
		$('#startTime').datebox({
			onSelect: function(date){
				var startTime = new Date($("#startTime").datebox('getValue').replace(/-/g,"/"));
				var endTime = new Date($("#endTime").datebox('getValue').replace(/-/g,"/"));
				if(endTime<startTime){
					$.messager.alert('警告！','活动开始时间大于活动结束时间！','warning');
					return false;
				}
			}
		});
		//活动结束时间校验
		$('#endTime').datebox({
			onSelect: function(date){
				var startTime = new Date($("#startTime").datebox('getValue').replace(/-/g,"/"));
				var endTime = new Date($("#endTime").datebox('getValue').replace(/-/g,"/"));
				if(startTime>endTime){
					$.messager.alert('警告！','活动结束时间小于活动开始时间！','warning');
					return false;
				}
				if(strDate>endTime){
					$.messager.alert('警告！','活动结束时间小于活动开始时间！','warning');
					return false;
				}
			}
		});
	});
	
	</script>
	
</head>

<body style="font-size: 12px;height:600px">
<!-- 活动修改界面start-->
	<form id="fm" method="post">
		<div style="width: auto;margin-top:-100px">
		<div class="lableLine320" hidden="hidden">
				<div class="lableWidth100">活动id</div>
				<input id="aid" name="aid" class="easyui-validatebox" 
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">活动名称</div>
				<input id="name" name="name" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">活动子标题</div>
				<input id="subtitle" name="subtitle" class="easyui-validatebox" style="height: 19px"
				     data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">活动用时</div>
				<input id="day" name="day" class="easyui-validatebox" onblur="validate()" style="height: 19px"
				    data-options="required:true,validType:'length[1,50]'" />
			</div>
	
			<div class="lableLine320">
				<div class="lableWidth120">所在省</div>
            <select id="provinceId" name="provinceid" onChange="searCity()" style="width:150px;height: 21px" class="easyui-validatebox" data-options="required:false,validType:'length[1,20]'">          					
             <option value="provinceid" selected>--请选择省--</option>
            </select>
			</div>  	

			<div class="lableLine320">
				<div class="lableWidth120">所在市</div>
            <select id="cityId" name="cityId" style="width:150px;height: 21px" class="easyui-validatebox" data-options="required:false,validType:'length[1,20]'">          		
              <option value="" selected>--请选择市--</option>
            </select>
			</div>
<!-- 			<div class="lableLine320"> -->
<!-- 				<div class="lableWidth120">活动举办地</div> -->
<!-- 				<input id="site" name="site" class="easyui-validatebox"   -->
<!-- 				    style="height: 19px" data-options="required:true,validType:'length[1,50]'" /> -->
<!-- 			</div> -->
			<div class="lableLine320">
				<div class="lableWidth120">活动具体地址</div>
				<input id="address" name="address" class="easyui-validatebox" style="height: 19px" 
				  data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">图文详情url</div>
				<input id="actDetailUrl" name="actDetailUrl" class="easyui-validatebox" 
				    style="height: 19px" data-options="required:false,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">是否置顶</div>
            <select id="stick" name="stick" style="width:150px;height: 21px" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">          		
							<option value="">--请选择是否置顶--</option>
							<option value="1">置顶</option>
							<option value="0">不置顶</option>	
            </select>
			</div>
            <div class="lableLine320">
				<div class="lableWidth120">活动状态</div>
            <select id="status" name="status" style="width:150px;height: 21px" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">
            			<option value="">--选择活动状态--</option>
							<option value="0">正常</option>
							<option value="1">已取消</option>
							<option value="2">已结束</option>
            </select>
			</div>
			
			<div class="lableLine320">
				<div class="lableWidth120">报名开始时间</div>
				<input id="registerStart" name="registerStart"  value="" style=""
				    data-options="editable:false,required:true,validType:'length[1,50]'" />
			</div>
			<div class="lableLine320"> 
				<div class="lableWidth120">报名结束时间</div>
				<input id="registerEnd" name="registerEnd"  value=""  style=""
				   data-options="editable:false,required:true,validType:'length[1,50]'"/>
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">活动开始时间</div>
				<input id="startTime" name="startTime" value=""  style=""
				    data-options="editable:false,required:true,validType:'length[1,50]'"/>
			</div>
			<div class="lableLine320">
				<div class="lableWidth120" style="width: 84px">活动结束时间</div>
				<input id="endTime" name="endTime" value=""   style=""
				   data-options="editable:false,required:true,validType:'length[1,50]'" />
			</div>
			
<!-- 			图片上传  start-->
			<div id="div_file1">
							<ul class="PoyBt FileItem file1"  style="padding-left:0px;border:0px solid blue; margin-top:100px;"  id="file1">
								<li style="height: 32px;text-align:left; border:0px solid red; width:72px" class="Poy_yi">活动logo</li>
								<li class="Poy_sa ProgressBar" style="width:150px">
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
								
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;margin-left:0px;margin-top:2px">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: pointer;"></p>
									<img  src="" style="margin-left: 10px; width: 100px; line-height: 23px; height: 100px; margin-top: 10px;visibility:hidden;" id="imageView">
								</li>
<!-- 								<li> -->
<!-- 									<p style="color:red; float:right;margin-right:300px">图片要求宽90像素*高100像素</p> -->
<!-- 								</li> -->
							</ul>
							<input type="hidden" id="image" name="image"/>
							
						</div>	

					
<!-- 			图片上传  end-->	
           <div class="lableLine320">
				<div class="lableWidth120">活动简介</div>     
				<textarea id="briefintro" name="briefintro" rows="3" cols="100" 
				      data-options="required:false,validType:'length[1,200]'" />
				</textarea >
			</div>
			<div class="lableLine320">
				<div class="lableWidth120">适合年龄</div>
				<input id="age" name="age" class="easyui-validatebox" style="height: 19px" 
				  data-options="required:true,validType:'length[1,50]'" />
			</div>
		
<!-- 			编辑器 -->
           <tr>
				<td>
				
					<div class="lableLine650" style="margin-top:75px;margin-left:-475px" >
						<div class="lableWidth888">内容</div>
						<div class="lableLine530" style="margin-top:25px;margin-left:-70px">						
							<input name="actDetail" id="actDetail" value="" class="ckeditor" />
						</div>
					</div>

					<div class="clear"></div>
					
					<div class="lableLine650" style="margin-top:-498px;margin-left:600px" >
						<div class="lableWidth888">手机内容</div>
						<div class="lableLine530" style="width:450px;margin-top:25px;margin-left:-70px">						
							<input name="mobileContent" id="mobileContent" value="" class="ckeditor" />
						</div>
					</div>

					<div class="clear"></div>
				</td>	

			</tr>
<!-- 			编辑器 -->
<!-- 			编辑器 -->
<!--             <tr> -->
<!-- 				<td> -->
				
<!-- 					<div class="lableLine650" style="margin-top:75px;margin-left:px" > -->
<!-- 						<div class="lableWidth888">mobile端图文详情</div> -->
<!-- 						<div class="lableLine530" style="margin-top:25px;margin-left:-70px">						 -->
<!-- 							<input name="mobileContent" id="mobileContent" value="" class="ckeditor" /> -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="clear"></div> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			编辑器 -->
<div style="margin-left:450px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px"
							id="" data-options="iconCls:'icon-undo'"
							onclick="resetBtnClick()">重置</a> <a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="saveOrUpdate()">保存</a> 
							<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px" id=""
							class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="cancel()">取消</a>
					</div>
		</div>
	</form>
	<script type="text/javascript">
		//<![CDATA[

		CKEDITOR
				.replace(
						'actDetail',
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
	<script type="text/javascript">
		//<![CDATA[

		CKEDITOR
				.replace(
						'mobileContent',
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
<!-- 活动修改界面end-->	

</body>
</html>
