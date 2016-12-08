<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>公告明细</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterNoticeAction.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/serviceCenterCarouselAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<style type="text/css">
.lableWidth120 {
	width: 72px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}

.lableLine350 {
	width: 250px;
	float: left;
	padding-top: 8px
}

.hidden {
	display: none;
	visibility: hidden
}

input {
	width: 150px;
}
</style>
<script type="text/javascript">
		var para= easyui_getRequestPara();
		var id=para.id;
		$(function(){
			if(id==undefined) id="";
			serviceCenterCarouselAction.getServiceCenter({
	        	async : false,
				callback : function(data) {
					if(data!=null&&data.length>0){
						$("#serviceCenterId").combobox("loadData",data);
						$("#serviceCenterId").combobox('setValue',data[0]['SERVICE_CENTER_ID']);
					}else{
						$.messager.alert('提示','没有站点,请完善人个信息！','',function(f){
							easyui_closeTopWindow();
						});
					}
				}
			});
			//修改的
			if(id!=""){
				serviceCenterNoticeAction.getCenterNoticeById({id:id},{
            			async : false,
						callback : function(data) {
							if(data!=null){
								$("#serviceCenterId").combobox("setValue",data.SERVICE_CENTER_ID);
								$("#noticeTitle").val(data.NOTICE_TITLE);
								$("#noticeContent").html(data.NOTICE_CONTENT);
							}
						}	
            		});
			}
			$(".validatebox-invalid").removeClass("validatebox-invalid");
		});
		
		
		function save(){
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			var map={};
			map.serviceCenterNoticeId=id;
			map.serviceCenterId=$("#serviceCenterId").combobox("getValue");
			map.noticeTitle=$("#noticeTitle").val();
			map.noticeContent=$("#noticeContent").val();
            serviceCenterNoticeAction.saveCenterNotice(map,{
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
	</script>
</head>
<body style="font-size: 12px;">
	<form id="fm" method="post">
		<div style="width: auto">
			<div class="lableLine350">
				<div class="lableWidth120">站点</div>
				<select id="serviceCenterId"  class="easyui-combobox" style="width:150px;" data-options="editable:false,panelHeight:'auto',valueField: 'SERVICE_CENTER_ID',textField: 'SERVICE_CENTER_NAME'">
		
				</select>
			</div>
			<div class="clear"></div>
			<div class="lableLine350" style="width:540px;">
				<div class="lableWidth120">公告标题</div>
				<input class="easyui-validatebox" id="noticeTitle" data-options="required:true,validType:'length[1,100]'" style="width:449px;" />
			</div>
			<div class="lableLine350" style="width:540px;">
				<div class="lableWidth120">公告内容</div>
				<textarea class="easyui-validatebox" id="noticeContent" data-options="required:true" rows="15" cols="61"></textarea>
			</div>
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-right: 25px; margin-top: 27px" id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
			</div>
		</div>
	</form>
</body>
</html>
