<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>代码管理</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/codeMasterAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
<script type="text/javascript">
var para= easyui_getRequestPara();
var id=para.id;
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
		//下拉
		codeMasterAction.getCodeType({
    			async : false,
				callback : function(data) {

					$.each(data,function(i,val){
 		 			//	alert(val.VAL+" "+val.TEXT); 
 				$('#codetype').append($("<option/>", {
						value: val.VAL,
						text: val.TEXT
						}));
 
		    			
		    		});					
				}	
    		});
});


		//保存用户信息
		var map={};
		$(function(){
			//修改的
			if(id!=""){
				
				codeMasterAction.getById(id,{
            			async : false,
						callback : function(data) {
							
							if(data.success){
								
								$('#fm').form('load',data.data);
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
			
			map['codetype']=$('#codetype option:selected') .val();
			map['codeno']=$.trim($('#codeno').val());	
			map['codename']=$.trim($('#codename').val());	
			map['codedescription']=$.trim($('#codedescription').val());
			if(id!=""){
				map['id']=id;}
				//saveorupdate
				codeMasterAction.SaveOrUpdate(map,{
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
			<div class="lableLine320" id="passwordDiv1">
				<div class="lableWidth100">代码类型</div>
				<select id="codetype"
					name="codetype"  style="width: 150px;"
					data-options="valueField: 'VAL',textField: 'TEXT'">
				<select> 	

			</div>
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">种类</div>
				
			<input id=codeno class="easyui-validatebox" name="codeno" data-options="required:true,validType:'length[1,6]'" value="" 
				onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" 
				/>
			</div>
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">代码名称</div>
				<input id="codename" name="codename" class="easyui-validatebox"
					style="text-align: LEFT;" data-options="required:true,validType:'length[1,50]'" />
			</div>
			<div class="clear"></div>
			<div class="lableLine320">
				<div class="lableWidth100">代码描述</div>
				
					<textarea maxlength="255" id="codedescription" name="codedescription" rows="8" cols="100"></textarea>
			</div>
			
			
			
			
			<div class="clear"></div>
			<div class="lableLine1050">
			<div style="float: right">
				<a href="javascript:void(0)" style="margin-top:0px;margin-right: 10px;" id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
				</div>
			</div>
			
		</div>
	</form>
</body>
</html>
