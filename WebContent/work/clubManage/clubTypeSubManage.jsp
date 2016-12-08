<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>社团管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/clubTypeManageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>

<script type="text/javascript"
	src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>

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
var id = para.id;
// alert(id);
//<!---------------社团增加、修改JS start--------------->
//自动加载js
var map = {};
$(function(){
	
// alert(cid);
// 	根据cid查询数据,做修改前处理。
		if (id!=null&&id!="") {
// 			alert("id="+id);
			clubTypeManageAction.getById(id, {
				async : false,
				callback : function(data) {
					if (data.success) {
						$('#fm').form('load', data.data);

						map = data.data;

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

	clubTypeManageAction.saveOrUpdate(map, {

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


 
//<!---------------社团增加、修改JS end--------------->			
	</script>
</head>

<body style="font-size: 12px;height:100px;width:700">
<!-- 社团修改界面start-->
	<form id="fm" method="post">
		<div style="width: auto;">
		<div class="lableLine320" hidden="hidden">
				<div class="lableWidth100">id编号</div>
				<input id="id" name="id" class="easyui-validatebox" 
				    data-options="required:false,validType:'length[1,50]'" />
			</div>
		<div class="lableLine320" >
				<div class="lableWidth100">类别编号</div>
				<input id="clubType" name="clubType" class="easyui-validatebox" 
				    data-options="required:true,validType:'length[1,50]'" />
			</div>
		    <div class="lableLine320">
				<div class="lableWidth120">类别名称</div>
				<input id="description" name="description" class="easyui-validatebox"  style="height: 19px"
				    data-options="required:true,validType:'length[1,50]'" />
			</div>	
<div style="margin-left:125px;">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 80px; height: 30px; margin-right: 0px;margin-top:20px"
							id="" data-options="iconCls:'icon-undo'"
							onclick="resetBtnClick()">重置</a> 
						<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 0px;margin-top:20px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="saveOrUpdate()">保存</a> 
						<a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 0px;margin-top:20px" id=""
							class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
							onclick="cancel()">取消</a>
					</div>
		</div>
	</form>

</body>
</html>
