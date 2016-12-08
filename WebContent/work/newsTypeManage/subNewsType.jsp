
<%@ page language="java" import="java.util.*,com.tcj.common.util.*"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>新闻类别信息管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsTypeAction.js'></script>
<script type="text/javascript">
	var para = easyui_getRequestPara();
	var id = para.id;
</script>

<!-- 上传图片控件样式 -->
<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>



<style type="text/css">
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

.lableWidth160 {
	width: 120px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableLine350 {
	width: 260px;
	float: left;
	padding-top: 8px
}

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


<!-- 上传图片js代码 -->
<script type="text/javascript">
function dealFile(filePath, obj){
	if(obj.result=="failure"){
		$.messager.alert("提示",obj.msg);
		$(filePath+"View").css('visibility','hidden');
	}else{
		$(filePath).val(obj.filePath);
		$(filePath+"View").attr("src","<%=basePath%>"+obj.filePath);//将返回的图片显示
		$(filePath+"View").css('visibility','visible');
	}
}

$(function() {  
	$('#file_upload').uploadPlug({
		uploader:'<%=basePath%>/script/uploadPlug/images/uploadPlug.swf',
			uploadUrl:'<%=basePath%>/newsTypeFile/upload.do', 
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

//	查找新闻类型
$(function(){
	newsTypeAction.getCategoryType({},{
		async : false,//async 属性规定一旦脚本可用，则会异步执行。
		callback : function(data){//回调函数
			$.each(data,function(i,val){
// 				alert(val.VAL+" "+val.TEXT);
    			$("#type").append("<option value='"+val.VAL+"'>"+val.TEXT+"</option>");
    		});
          }		
	});
});


$(function() {
	//查找新闻类别父类别
	newsTypeAction.getParentName({}, {
		async : false,//async 属性规定一旦脚本可用，则会异步执行。
		callback : function(data) {//回调函数
			data.unshift({
				'VAL' : '',
				'TEXT' : '-请选择-'
			});
			$("#parent_name").combobox("loadData", data);
		}
	});
});



//保存用户信息
var map = {};
$(function() {
	//修改的
	if (id != "") {
		newsTypeAction.getNewsTypeById(id, {
			async : false,
			callback : function(data) {
					$('#fm').form('load', data);
// 					alert(data.onHome);
					if(data.icon!=null){
				 		$("#imageView").attr('src',"<%=basePath%>"+data.icon); 
				 	}
			}
		});

	}

});


//新增或者修改保存
function save() {
	var isValid = $("#fm").form('validate');
	if (!isValid) {
		$.messager.alert('警告', "请将红框内的数据修改正确！", 'warning');
		return;
	}
	//组装map

	var map = {};
	map['id'] = id;//用来判断tid验证船舶是否存在则判断是为添加还是修改

	$.each($("#fm").find("input[name],textarea[name]"), function() {
		//控件类型
		if ($(this).hasClass("easyui-combobox")
				&& $(this).combobox("getValue") != "") {
			map[$(this).attr("name")] = $
					.trim($(this).combobox("getValue"));
		} else {
			map[$(this).attr("name")] = $.trim($(this).val());
		}
	});

	map['name'] = $.trim($('#name').val());
	map["type"] = $("#type").val();
	map["parentName"] = $("#parent_name").combobox("getValue");
	map['description'] = $.trim($('#description').val());
	map['onHome'] = $.trim($('#onHome').val());
	map['is_delete'] = $.trim($('#is_delete').val());
// 	alert(map.onHome);
	map['image'] = $("#image").val();
	map['create_user'] = $("#create_user").val();
	map['create_time']=$("#create_time").val();;
	
	
//		alert(map.onHome);
	newsTypeAction.saveNewsType(map, {
		async : true,//和form表单提交同时操作
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


//重置
function reset() {
	//fm是form的id名 
	$('#fm').form('clear');
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
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine320" hidden="hidden">
				<div class="lableWidth100">编号</div>
				<input id="caid" class="easyui-validatebox" name="caid"
					data-options="required:false,validType:'length[1,10]'" />
			</div>
			<div class="lableLine320">
				<div class="lableWidth100">名称</div>
				<input id="name" name="name" class="easyui-validatebox"
					data-options="required:true,validType:'length[1,50]'" />
			</div>
			<!-- 			<div class="lableLine320" hidden="hidden"> -->
			<!-- 				<div class="lableWidth100">楼宇名称</div> -->
			<!-- 				<input id="bid" name="bid" class="easyui-validatebox" -->
			<!-- 					data-options="required:false,validType:'length[1,50]'" /> -->
			<!-- 			</div> -->
			<div class="lableLine320">
				<div class="lableWidth100">新闻类型</div>
				<select id="type" name="codeNo" style="width: 152px; height: 21px"></select>
			</div>
			<div class="lableLine320">
				<div class="lableWidth100">父类别</div>
				<select id="parent_name" name="parent_id" class="easyui-combobox"
					style="width: 135px"
					data-options="editable:false,valueField:'VAL',textField:'TEXT'"></select>
			</div>
<!-- 			<div class="lableLine320"> -->
<!-- 				<div class="lableWidth100">排序</div> -->
<!-- 				<input id="sort" name="sort" class="easyui-validatebox" -->
<!-- 					data-options="required:true,validType:'length[1,50]'" /> -->
<!-- 			</div> -->
			<div class="lableLine320">
				<div class="lableWidth100">描述</div>
				<textarea id="description" name="description"
					class="easyui-validatebox" style="width: 150px;height: 80px"
					data-options="required:false,validType:'length[1,50]'" ></textarea>
			</div>
			<div class="lableLine320">
				<div class="lableWidth100">首页是否显示</div>
				<select id="onHome" name="onHome" style="width: 132px; height: 21px;margin-left: 20px">
				  <option value="0" selected="selected" >否</option>
				  <option value="1">是</option>
				</select>
			</div>
			<div class="lableLine320" hidden="hidden">
				<div class="lableWidth100">是否删除</div>
				<input id=is_delete name="is_delete" class="easyui-validatebox" value="0"
					data-options="required:false,validType:'length[1,50]',"/>
			</div>
			<!-- 			<div class="lableLine320"> -->
			<!-- 				<div class="lableWidth100">首页显示规则</div> -->
			<!-- 				<input id="onHomeType" name="onHomeType" class="easyui-validatebox" -->
			<!-- 					data-options="required:true,validType:'length[1,50]'" /> -->
			<!-- 			</div> -->
			<!-- 			<div class="lableLine320"> -->
			<!-- 				<div class="lableWidth100">级别</div> -->
			<!-- 				<input id="grade" name="grade" class="easyui-validatebox" -->
			<!-- 					data-options="required:true,validType:'length[1,50]'" /> -->
			<!-- 			</div> -->
			<!-- 			<div class="lableLine320"> -->
			<!-- 				<div class="lableWidth100">引用地址</div> -->
			<!-- 				<input id="url" name="url" class="easyui-validatebox" -->
			<!-- 					data-options="required:true,validType:'length[1,50]'" /> -->
			<!-- 			</div> -->
			<div class="lableLine320" hidden="hidden">
				<div class="lableWidth150">创建人</div>
				<input id="create_user" name="create_user" class="easyui-validatebox"  
				    data-options="editable:false,required:false,validType:'length[1,50]'"/>
			</div>
			<div class="lableLine320"  hidden="hidden">
				<div class="lableWidth150">创建时间</div>
				<input id="create_time" name="create_time" class="easyui-validatebox" 
				    data-options="editable:false,required:false,validType:'length[1,50]'"/>
			</div>
				<div class="clear"></div>
  			<div style="height: 50px"></div>
					<div id="div_file1">
							<ul class="PoyBt FileItem file1"  style="padding-left:0px;border:0px solid blue; margin-top:100px;"  id="file1">
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
										<input id="file_upload" name="file1" type="file" />
									</a>
								</li>
								<li class="Poy_si" style="padding-top: 0px; height: 32px;width:170px;">
									<p class="Btn UploadBtn NomalUploadBtn" style="cursor: default;"></p>
									<img style="float:top; margin-left:70px; width: 200px;height:200px; line-height: 23px;  margin-top: -200px;visibility:;" id="imageView">
								</li>
							</ul>
							<input type="hidden" id="image" name="icon"/>
						</div>	

 			

          
			<div class="clear"></div>
			<div style="float: right">
				<a href="javascript:void(0)"
					style="width: 80px; height: 30px; margin-top: 120px; margin-right: 20px;"
					id="btnSave" class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick="reset()">重置</a> <a
					href="javascript:void(0)"
					style="width: 80px; height: 30px; margin-top: 120px; margin-right: 20px;"
					id="btnSave" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" onclick="save()">保存</a> <a
					href="javascript:void(0)"
					style="width: 80px; height: 30px; margin-top: 120px; margin-right: 20px;"
					id="btnSave" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" onclick="cancel()">取消</a>
			</div>
		</div>

		</div>
	</form>
</body>
</html>
