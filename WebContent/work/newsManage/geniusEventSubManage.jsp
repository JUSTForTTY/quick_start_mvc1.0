<%@ page language="java" import="java.util.*,com.tcj.common.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>天才纪大事件子界面</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/newsManageAction.js'></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
<script type="text/javascript" src="<%=basePath%>/script/flash/artDialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/flash/photoEditor.js"></script>

<link href="<%=basePath%>/script/uploadPlug/css/uploadPlug.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>/script/uploadPlug/js/uploadPlug.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css"
	      href="<%=basePath%>/script/flash/skin/idialog.css" id="artDialogSkin" />
	<script type="text/javascript"
	      src="<%=basePath%>/script/flash/artDialog.js"></script>
    <script type="text/javascript"
	      src="<%=basePath%>/script/flash/photoEditor.js"></script>
	      <!--	富文本框样式-->       
	<script type="text/javascript" src="<%=basePath%>/script/ckeditor/ckeditor.js"></script>	
	<script type="text/javascript" src="<%=basePath%>/script/ckfinder/ckfinder.js"></script> 


<script type="text/javascript">
var para= easyui_getRequestPara();
var nid=para.nid;
var caid=para.caid;

</script>

<style type="text/css">

.lableWidth120{width:72px;float:left;white-space: nowrap;margin-top:5px}

.lableLine350{float:left;margin-top:15px;}


	.lableLine530{width:640px;float:left;margin-top:15px;}
		
		
		.lableLine650{width:740px;float:left;margin-top:15px;}






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

/* .lableWidth120 { */
/* 	width: 84px; */
/* 	float: left; */
/* 	white-space: nowrap; */
/* 	padding-top: 4px */
/* } */

.lableWidth160 {
	width: 120px;
	float: left;
	white-space: nowrap;
	padding-top: 4px
}


/* .lableLine350 { */
/* 	width: 260px; */
/* 	float: left; */
/* 	padding-top: 8px */
/* } */

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

		//保存用户信息
		var map={};
	
		$(function(){
			//修改的
			
		if(caid!=null&&nid!=null){		
			newsManageAction.getBynid(nid,caid,{
            			async : false,
						callback : function(data) {
							if(data.success){
								$('#fm').form('load',data.data);
								//alert(data.data.date);
								$("#date").attr("disabled",true);
								$("#date").combobox("setValue",data.data.date);
// 								CKEDITOR.instances.actDetail.setData(data.data.actDetail);
								map=data.data;
								showButton(data.data.status);
							}else{
								
								$.messager.alert("提示",data.msg);
							}
						}	
            		});
				
			}else{
				showButton(status)
			}
			
		});
		
		
		
		function showButton(status){
			//alert(status);
			//如果状态为删除1
			if(status=="1"){
				$("#btnSave").css('visibility','hidden');
				$("#btnCancel").css('visibility','hidden');
				
			}else{
				$("#btnRestore").css('visibility','hidden');
				//$(filePath+"View").css('visibility','visible');
			}
		}	
		
		
		
		
		
		
		
		
		
		
		
		//新增或者修改保存
		function save(){	
			var isValid=$("#fm").form('validate');
			if(!isValid)
			{
				$.messager.alert('警告', "请将红框内的数据修改正确！",'warning');
				return;
			}
			var map = {};
			map['nid']=nid;//用来判断tid验证船舶是否存在则判断是为添加还是修改
			map['caid']=caid;
			map['date']=$('#date').combobox('getText');
	
 			//组装map
			map['title']=$.trim($('#title').val());
				newsManageAction.geniussaveOrUpdate(map,{
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
		 
		//恢复
		function restore(flag){
			
		//	var map={};
			//map['flag']=flag;
			//map['nid']=nid;
			//alert(flag);
		//	alert(nid);
			var str;
				 str="恢复";
			$.messager.confirm('确认', '确定要'+str+'吗?', function(r) {
				if (r) {
					newsManageAction.Recovery(map,{
						async : false,
						callback : function(data) {
							if (data.success) {
								$.messager.alert('确认', str+'成功!');
								
								easyui_closeTopWindow(data.data);
							} else {
								$.messager.alert('警告', data.msg,'warning');
							}
						}
					});
				}
			});
		}
	 
		
		
		
		
		
		
		
		
		
	</script>
  </head>
  <body style="font-size: 12px;">
  <form id="fm" method="post">
	  <table border="0">
		<tr>
		<td>
		<div class="lableLine350">
			<div class="lableWidth120">
			发生事件
			</div>
			<input name="title" id="title"  style="width:350px;" class="easyui-validatebox" data-options="required:false,validType:'length[1,200]'" />
		</div>
	    <div class="clear"></div>
	    <div class="lableLine350">
			<div class="lableWidth120">
			发生时间
			</div>
			<input class="easyui-combobox" id="date" name="date" style="width:350px;" >
		</div>
	    <div class="clear"></div>
	   </td>
	   </tr>
		<tr>
		<td>
	    <div style="text-align: right">
			<a href="javascript:void(0)" style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="btnSave"
	             class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a>
	        <a  href="javascript:void(0)"  style="width:80px;height:30px;margin-right: 5px;margin-top:17px" id="btnCancel"
	             class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="cancel()" >取消</a>
					<a href="javascript:void(0)" style="width:80px;height:30px;margin-right: 25px;margin-top:17px" id="btnRestore"
	             class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="restore(1)">恢复</a>
		</div>
	  </td>
	  </tr>
	   
	  </table>
	</form>
			<script type="text/javascript">
			$("#date").combobox({   
				valueField:'year',    
				textField:'year',  
				panelHeight:'auto'
				});
				var data = [];//创建年度数组
				var startYear=1998;//起始年份
				var thisYear=new Date().getUTCFullYear();//今年
				var endYear=thisYear+1;//结束年份
				//数组添加值（2012-2016）//根据情况自己修改
				for(y=endYear;y>=startYear;y--){
					data.push({"year":y});
				}
				$("#date").combobox("loadData", data);//下拉框加载数据
				$("#date").combobox("setValue",thisYear);//设置默认值为今年
				//alert($("#date").combobox("getValue"));
			</script>
			
			<script type="text/javascript">
			$(function(){
				$('#date').combobox({
					required:true,
				multiple:true,
				editable:false,
				});
				
			});
			
			
			</script>
  </body>
</html>
