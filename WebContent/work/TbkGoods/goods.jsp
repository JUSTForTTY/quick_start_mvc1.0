
<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<title>商品管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/goodsManageAction.js'></script>
<script type="text/javascript"
	src="../../script/easyui/easyui-validatebox.js"></script>
<style type="text/css">
.box {
	float: left;
}
</style>

<script type="text/javascript">
	var para= easyui_getRequestPara();
	//var caid=para.caid;

	//alert("点击菜单传默认类别caid="+caid);
	//查询按钮
	function searchBtnClick() {
		$('#dg').datagrid('load');
	}
	
	//格式化操作列	
	function operationFmt(value, row, index) {
		var html = " ";
		var id=row.id;
			  

			  var Type=row.status;
				 if(Type==0){
					  row.status="初始状态";
					  }   
				  if(Type==1){
					  row.status="下载订单";
				  }  
				  if(Type==2){
					  row.status="录入多余供应量";
				  }  
				  if(Type==3){
					  row.status="下架";
				  }  
			  
			  
			  
			  
			  
		var idstring=id.toString();
	//	var caidstring=caid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,this)' >修改</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
	//	alert(status);
		param["name"] = $.trim($('#name').val());
		param["title"]=$.trim($('#title').val());
		param["status"]=$.trim($('#status').val());	
		param["type"]=$.trim($('#type').val());	
		param["pname"]=$.trim($('#pname').val());	
	//	param[' activity_start_time']=$("# activity_start_time").datebox('getValue');
	//	param['activity_end_time']=$("#activity_end_time").datebox('getValue');
	}
	//删除记录
	function delBtnClick(flag){
		//删除记录
//alert(id);
		var str="删除";
	
			var checkedRows=$('#dg').datagrid('getChecked');
			if(checkedRows.length==0){
				$.messager.alert('提示', '请选择要'+str+'的行。');
				return;
			}
			var ids="";
			$.each(checkedRows,function(i){
				ids=ids+checkedRows[i]['id']+",";
			});
			ids=ids.substring(0,ids.length-1);
			
			$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+checkedRows.length+"</font>条数据吗？", function(r) {
				if (r) {
					goodsManageAction.deleteGoodsList(ids, {
						async : false,
						callback : function(data) {
							if (data.success) {
								$.messager.alert('确认', str+'成功!');
								$('#dg').datagrid('reload');
							} else {
								$.messager.alert('警告', str+'失败!');
							}
						}
					});
				}
			});
		}
	
	
	//修改
	//nid和上面的nidstring, caid为caidstring传过来的值，obj是this,顺序不能乱
	function editBtnClick(id,obj) {
		
	//	 alert(id);
		// alert("你好，你的id到这了");
		var url="";
		 url = "<%=basePath%>work/TbkGoods/updategoods.jsp?id="+id;

	
			easyui_openTopWindow("商品管理 ",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}
	
	
	
	
	
	
	
//  自动加载js,查询大类别
var map = {};
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

	//查询子类别
	function searCity() {
		
		map['id'] = $("#id").val();
	//	alert(id);
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
	
	
	//按钮
	function openUploadFileDialog(){
		$("#dlg2").dialog('open').dialog('setTitle','批量导入数据');
	}
	//下载模板
	function downloadTemplate(){
		window.open('template/userImporTemplate.xls');
	}
	//上传文件
	function uploadFile(){
		alert(11);
		$("#uploadForm").form("submit",{
			//alert(qww);
			success:function(result){
				var result=eval('('+result+')');
				alert("1111"+result);
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
				}else{
					$.messager.alert("系统提示","上传成功");
					$("#dlg2").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	
	
	
	//新增(传默认caid)
	function addBtnClick() {	
	    var url="";
		 url = "<%=basePath%>work/TbkGoods/Addgoods.jsp";
		easyui_openTopWindow("商品管理",866,592,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}

	
	//修改已删除的数据，恢复已删除的数据
	
	//function  
	

	//查询已删除的数据
	
// 	function searchDate(caid,obj){
<%-- 		var url="<%=basePath%>work/newsManage/newsRecoveryManage.jsp?caid="+caid;  --%>
// 		easyui_openTopWindow("内容管理",1500,640,url,function(returnValue)
// 				{
// 					$('#dg').datagrid('reload');
// 				}); 
// 			}
	
	
	
// 	//设置默认时间
// 	var strDate;
// 	var startStr;
// 	$(function(){   
// 		var curr_time = new Date();   
// 		strDate = curr_time.getFullYear()+"-";   
// 		strDate += curr_time.getMonth()+1+"-";   
// 		strDate += curr_time.getDate(); 
// 		$("#activity_start_time").datebox("setValue", strDate); 
// 	});
	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 70px">
		<div id="search" class="easyui-panel" title="商品管理"
			data-options="iconCls:'icon-search'">
			<div class="box">
				<span>商品名称</span> <input id="name">
			</div>
			
			<div class="box">
				<span>标题</span> <input id="title">
			</div>
				<div class="box">
				<span>会员单位名称</span> <input id="pname">
			</div>
			
<!-- 			<div class="box"> -->
<!-- 				<span>商铺名</span> <input id="shop_name"> -->
<!-- 			</div> -->
<!-- 			<div class="box"> -->
<!-- 			<span>&nbsp;&nbsp;时间&nbsp;&nbsp;</span> -->
<!-- 				<input class="easyui-datebox" name="activity_start_time" id="activity_start_time" editable="false" size="10"/> -- -->
<!-- 				<input class="easyui-datebox" name="activity_end_time" id="activity_end_time" editable="false" size="10"/> -->
<!-- 			      </div>   -->
<!-- 			<div class="box"> -->
<!-- 				<span>活动状态</span> <select id="activity_status"> -->
<!-- 					<option value="0">已推广</option> -->
<!-- 					<option value="1">已结束</option> -->
<!-- 					<option value=" " selected="selected">全部</option> -->
<!-- 				</select> -->
<!-- 			</div> -->
			
			<div class="box">
				<span>状态</span> <select id="status">
					<option value="0" >初始状态</option>
					<option value="1" >下载订单</option>
					<option value="2" >录入多余供应量</option>
					<option value="3" >下载订单</option>
					<option value=" " selected="selected">全部</option>
				</select>
			</div>
			
<!-- 			<div>	 -->
<!-- 				商品大类别 -->
<!--             <select id="id" name="id" onChange="searCity()" style="width:150px;height: 21px;margin-top:3px;" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">          					 -->
<!--              <option value="id" selected>--请选择类别--</option> -->
<!--             </select> -->
<!-- 			商品子类别 -->
<!--             <select id="parentId" name="categoryId" style="width:150p	x;height: 21px" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">          		 -->
<!--               <option value="" selected>--请选择子类别--</option> -->
<!--             </select> -->
	
		
<!-- 		</div> -->
			
			
			<span class="aBtn"> <a id="searchBtn"
				href="javascript:void(0)" class="easyui-linkbutton"
				style="margin-left: 100px;" data-options="iconCls:'icon-search'"
				onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>


	<div class="clear"></div>

	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="商品管理"
			data-options="
						fitColumns:false,
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     toolbar: '#add',
	                     pagination:true,
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     onBeforeLoad:onBeforeLoad,
	                     url:goodsManageAction.getGoodsList">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
					<th
						data-options="field:'name',width:270,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">商品名称</th>			
					
					<th
						data-options="field:'provider_id',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">会员单位编号</th>			
					<th
						data-options="field:'pname',width:270,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">会员单位名称</th>			
					
					
					<th
						data-options="field:'title',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">标题</th>
						
						<th
						data-options="field:'price',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">价格</th>
						
						<th
						data-options="field:'unit',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">单位</th>
						
						
<th
						data-options="field:'status',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">状态</th>

<!-- 					<th -->
<!-- 						data-options="field:'type',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">类型</th> -->
			</tr>

			</thead>

		</table>
	</div>
	<div id="add" style="height: 25px">
		<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-add',plain:true" onclick="addBtnClick()">新增</a>
		<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
			style="margin-left: 10px;"
			data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick(0)">删除</a>
		   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import" 
		   plain="true" onclick="openUploadFileDialog()">用模版批量导入数据</a>

	</div>	
	<div id="dlg2" class="easyui-dialog" style="width:500px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons2">
        <form id="uploadForm"  action="<%=basePath%>excelManage.do" method="post" enctype="multipart/form-data">
        	<table>
        		<tr>
        			<td>下载模版：</td>
        			<td><a href="javascript:void(0)" class="easyui-linkbutton"  onclick="downloadTemplate()">导入模版</a></td>
        		</tr>
        		<tr>
        			<td>上传文件：</td>
        			<td><input type="file" name="userUploadFile"></td>
        		</tr>
        		<tr>
				<td>商品大类别
            <select id="id" name="id" onChange="searCity()" style="width:150px;height: 21px" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'">          					
             <option value="id" selected>--请选择类别--</option>
            </select>
		     </td>
			</tr>
			<tr>
				<td style="margin-top:-10px;">商品子类别
            <select id="parentId" name="categoryId" style="width:150p	x;height: 21px" class="easyui-validatebox" data-options="required:false,validType:'length[1,20]'">          		
              <option value="" selected>--请选择子类别--</option>
            </select>
		</td>
			</tr>	
        	</table>
        </form>
	</div>
    
	<div id="dlg-buttons2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadFile()">上传</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')">关闭</a>
	</div>
	
	
	
	
</body>
</html>
