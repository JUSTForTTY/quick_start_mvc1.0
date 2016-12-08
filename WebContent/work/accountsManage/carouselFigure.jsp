<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>站点管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterCarouselAction.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/fileDeleteAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">

</script>

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
	$(function(){
		serviceCenterCarouselAction.getServiceCenter({
        	async : false,
			callback : function(data) {
				if(data!=null&&data.length>0){
					$("#serviceCenterId").combobox("loadData",data);
					$("#serviceCenterId").combobox('setValue',data[0]['SERVICE_CENTER_ID']);
					$("#son").attr("src","subCarouselFigure.jsp?serviceCenterId="+data[0]['SERVICE_CENTER_ID']);
				}
			}
		});
		
		$("#serviceCenterId").combobox({onChange: function (newValue, oldValue) {
			$("#son").attr("src","subCarouselFigure.jsp?serviceCenterId="+newValue);
		}});
		
	});
	
</script>
</head>
<body class="easyui-layout" style="font-size: 12px;">
	<div class="box">
		<span>站点</span>
		<select  id="serviceCenterId"  class="easyui-combobox" style="width:150px;" data-options="editable:false,valueField: 'SERVICE_CENTER_ID',textField: 'SERVICE_CENTER_NAME'">
		
		</select>
		<span >*轮播图大小为 700 * 241 </span>
	</div>
	<div class="clear"></div>
	<iframe  id="son" src="" style="width:100%;height:100%;">
	</iframe>
</body>
</html>
