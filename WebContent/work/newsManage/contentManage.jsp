<%@ page language="java" import="java.util.*,com.tcj.common.util.*"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>内容界面</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript"
	src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/script/flash/photoEditor.js"></script>
<!--	富文本框样式-->
<script type="text/javascript"
	src="<%=basePath%>/script/ckeditor/ckeditor.js"></script>


<script type="text/javascript">
var para= easyui_getRequestPara();
var nid=para.nid;
var caid=para.caid;
 

</script>

<style type="text/css">
.lableWidth120 {
	width: 72px;
	float: left;
	white-space: nowrap;
	margin-top: 5px
}

.lableLine350 {
	width: 720px;
	float: left;
	margin-top: 15px;
}

.lableLine530 {
	width: 100%;
	margin-top: 5px;
	border:0px solid red;
}

.lableLine650 {
	width: 98%;
	min-width:980px;
	height:98%
	margin-top: 5px;
}



.lableLine320 {
	width: 237px;
	float: left;
	padding-top: 8px
}

.lableWidth160 {
	width: 120px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
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
<script type="text/javascript">

//根据aid查询数据,做预修改处理。
var map={};
$(function(){
	//如果aid不等于空,aid传向后台。
	if(caid!=null){		
	//	alert("nid="+nid);
	//	alert("caid="+caid);
		newsManageAction.getByCaid(caid,{
    			async : false,
				callback : function(data) {
					if(data.success){
					//	alert(data.data);
						$('#fm').form('load',data.data);
						//副文本框中得到值要用data.data.content
						CKEDITOR.instances.content.setData(data.data.content);
						map=data.data;
					}else{
						$.messager.alert("提示",data.msg);
					}
				}	
    		});
		
	}
	
});	

		
// 		//新增或者修改保存
		function save(){
			
			var isValid=$("#fm").form('validate');
		
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}

	        map['content']=CKEDITOR.instances.content.getData();
	        map['nid']=$.trim($('#nid').val());
	        //alert("nid"+$.trim($('#nid').val()));
			map['caid']=caid;
			//alert("caid="+caid);
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
		
		//修改
		function editBtnClick(nid,caid,obj) {
			
			 
			var url="<%=basePath%>work/newsManage/courseSubManage.jsp?nid="+nid+"&caid="+caid;
			
			easyui_openTopWindow("课程管理",828,640,url,function(returnValue)
			{
				$('#dg').datagrid('reload');
			}); 
		}
		
		//新增
		function addBtnClick(caid,obj) {
			var url="<%=basePath%>work/newsManage/courseSubManage.jsp?caid=" + caid;
			easyui_openTopWindow("课程管理", 828, 640, url, function(returnValue) {
				$('#dg').datagrid('reload');
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
</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post" style="border:0px solid red;height:100%">
		<table width="96%" height="100%" border="0px">
			<tr>
				<td>
					<div class="lableLine650">
						<div class="lableLine530">
							<input name="content" id="content" class="ckeditor" />
						</div>
					</div> 
					<input id="caid" name="caid" type="hidden" value="<%=request.getParameter("caid")%>" /> 
					<input id="nid" name="nid" type="hidden" value="" />
					<div class="clear"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div style="float: right">
				 <a href="javascript:void(0)"
							style="width: 80px; height: 30px; margin-right: 25px; margin-top: 17px"
							id="btnSave" class="easyui-linkbutton"
							data-options="iconCls:'icon-save'" onclick="save()">保存</a> 
					</div>
				</td>
			</tr>

		</table>

	</form>
	<script type="text/javascript">
		//<![CDATA[
		CKEDITOR
				.replace(
						'content',
						{
							height:'500px',
							width: '100%',
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
