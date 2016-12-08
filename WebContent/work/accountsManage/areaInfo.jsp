<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
	<title>区域</title>
	<%@ include file="../../inc.jsp"%>
	<script type='text/javascript'
		src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript"
		src='../../script/datePicker/WdatePicker.js'></script>
	<script type="text/javascript"
		src='<%=basePath%>/dwr/interface/areaInfoAction.js'></script>
		<script type="text/javascript" src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
	<script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {
			mnemonicLength: {
		    	validator: function (value, param) {
		    		if( value.length>param[1]||value.length<param[0]){
		    			return false;
		    		}
		            return /^[A-Za-z0-9_]+$/.test(value);
		        },
		        message:'请输入字母，数字或者下划线，且长度为{0}~{1}位之间'
		    }
		});
		
		$.extend($.fn.datagrid.defaults.editors, {
			choseDate : {
		    init: function(container, options)
		    {
				var editorContainer = $('<div/>');
				var input = $("<input type='text'  class='datagrid-editable-input' onClick='WdatePicker()' readonly=readonly>");
				editorContainer.append(input);
				editorContainer.appendTo(container);
				return input;
		    },
		    getValue: function(target,value)
		    {
		           return $(target).parent().find('input').val();
		    },
		    setValue: function(target, value)
		    {
		        $(target).parent().find('input').val(value);
		    },
		    resize: function(target, width)
		    {
		        var span = $(target);
		        if ($.boxModel == true){
		            span.width(width - (span.outerWidth() - span.width()) - 10);
		        } else {
		            span.width(width - 10);
		        }
		    }
		    }
		});
		
		var id=null;
		var alertShow=0;//提示批量修改全部完成后要刷新的全局变量
		
		$(function () 
		{
			$('#tree').tree({
			onLoadSuccess:function(node,data){
				if(data.length!=0)
				{
					if(id==null)
					{
						id=$('#tree').tree('getRoot').id;
						$("#dg").datagrid({
						url:'areaInfoAction.getCheckedDepartment',
						onBeforeLoad:onBeforeLoad
					});
					}
					node = $('#tree').tree('find',id);
					$('#tree').tree('select', node.target);
				}
			},
			onClick:function (node) 
			{
				alertShow=0;
				id=node.id;
				$("#dg").datagrid({
					url:'areaInfoAction.getCheckedDepartment',
					onBeforeLoad:onBeforeLoad
				});
			}
			});
			$('#dg').datagrid({
				onLoadSuccess: function(data){
					for(var i=0;i<data.rows.length;i++)
					{
						if(data.rows[i].foundDate!=null&&data.rows[i].foundDate!=""){
							data.rows[i].foundDate=data.rows[i].foundDate.substr(0,10);
							$("#dg").datagrid("updateRow",{
									index:i,
									row:{foundDate:data.rows[i].foundDate}
							});
						}
					}
				}
			});
		});
		
		function operationFmt(value, row, index) {
			var html = " ";
			var areaId=row.areaId;
			if(areaId!=undefined&&areaId!=""&&areaId!=null){
				html += " <a id=ed" + areaId
						+ " href='javascript:void(0)' onclick='editBtnClick(this)' >修改</a>";
			}else {
				html += " <a href='javascript:void(0)' onclick='updateBtnClick(this)'>保存</a>";
			}
			return html;
		}
		
		//新增
		function add() {
			alertShow++;
			$('#dg').datagrid('appendRow', {});
			var editIndex = $('#dg').datagrid('getRows').length - 1;
			$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',editIndex);
		}
		
		function delBtnClick(){
			var addDelete=0;
			var rows = $('#dg').datagrid('getRows');
			var checkedRows=$('#dg').datagrid('getChecked');
			if(checkedRows.length==0)
			{
				$.messager.alert('提示', '请选择要删除的行。');
				return;
			}
			$.each(checkedRows,function(){
				var index=$("#dg").datagrid('getRowIndex',this);
				var areaId = rows[index].areaId;
				if(areaId==undefined||areaId==""||areaId==null){
					$("#dg").datagrid("deleteRow",index);
					addDelete++;
					alertShow--;
				}
			});
			if(addDelete==checkedRows.length)
			{
				if(alertShow==0)
					reload();
				return;
			}
			checkedRows=$('#dg').datagrid('getChecked');
			$.messager.confirm('确认', '确定要删除吗?其子节点将一并被删除！', function(r) {
				if (r) {
					areaInfoAction.delChildDepartment(checkedRows, {
						async : false,
						callback : function(data) {
							if (data.success) {
								$.each(checkedRows,function(){
									var index=$("#dg").datagrid('getRowIndex',this);
									if(rows[index].editor=='1')
										alertShow--;
									$("#dg").datagrid("deleteRow",index);
								});
								if(alertShow==0)
									reload();
								$('#tree').tree('reload');
								$.messager.alert('确认', '删除成功');
							} else {
								$.messager.alert('警告', '删除失败');
							}
						}
					});
				}
			});
		}
		
		//修改
		function editBtnClick(obj) {
			var index=$("#dg").datagrid('getRowIndexByObj',obj);
			var rows=$('#dg').datagrid('getRows');
			var areaId=rows[index].areaId;
			alertShow++;
			$("#dg").datagrid("updateRow",{
								index:index,
								row:{
									editor:'1'
								}
								});
			$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
				index);
			var _ed = $("#ed" + areaId);
			_ed.html("保存");
			_ed.removeAttr("onclick");
			_ed.attr("onclick", "updateBtnClick(this)");
		}
		
		function updateBtnClick(obj) {
			var index=$("#dg").datagrid('getRowIndexByObj',obj);
			var rows = $('#dg').datagrid('getRows');
			var areaId = rows[index].areaId;
			var sort=rows[index].groupSort;
			var areaName=rows[index].areaName;
			if(areaId==undefined||areaId==null)
				areaId="";
			if ($('#dg').datagrid('validateRow', index)) {
				$('#dg').datagrid('endEdit', index);
				var editRows = $('#dg').datagrid('getRows');
				var editAreaName = editRows[index].areaName;
				var node=$('#tree').tree('getSelected');
				if(node!=null&&node!=undefined)
				{
					var fullName=editAreaName;
					for(;node.id!=$('#tree').tree('getRoot').id;)
					{
						var tempName=node.text;
						fullName=tempName+' '+fullName;
						node=$('#tree').tree('getParent',node.target);
					}
					$("#dg").datagrid("updateRow",{
								index:index,
								row:{
									areaFullName:fullName
								}
								});
					var editAreaCode = editRows[index].areaCode;
					var parentId=$('#tree').tree('getSelected').id;
					var map={editAreaCode:editAreaCode,editAreaName:editAreaName,areaId:areaId,parentId:parentId,sort:sort,areaName:areaName};
				}
				else{
					map={parentId:null};
				}
				areaInfoAction.saveChildDepartment(rows[index],map,{
					async : false,
					callback : function(data) {
						if(data.success)
						{
							alertShow--;
							$('#tree').tree('reload');
							$("#dg").datagrid("updateRow",{
							index:index,
							row:{
								areaId:data.data.areaId,
								editor:null,
								areaCode:data.data.areaCode,
								fAreaId:data.data.fAreaId
							}
							});
							if(alertShow==0)
								reload();
						}
						else{
							$.messager.alert('警告', data.msg,'warning');
							$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
								index);
							var _ed = $("#ed" + areaId);
							_ed.html("保存");
							_ed.removeAttr("onclick");
							_ed.attr("onclick", "updateBtnClick(this)");
							return;
						}
						}
					});
			}
			else
			{
				$.messager.alert('警告','请将红框内的信息修改正确后再进行保存！','warning');
			}
		}
		
		function reload(){
			alertShow=0;
			$("#dg").datagrid('reload');
		}
		
		function onBeforeLoad(param)
		{
			param["id"]=id;
		}
		
		function tooltipFmt(value, row, index)
		{
			if(value!=null&&value!=""&&value!=undefined)
				return "<span title="+value+" class='easyui-tooltip'>"+value+"</span>";
		}
	</script>
  </head>
  
  <body>
     	<div class="easyui-layout" data-options="fit:true">
	    	<div data-options="region:'west'" style="width:280px;">
	        	<div id=treePanel class="easyui-panel" title="区域"
					data-options="iconCls:'icon-edit',fit:true">
		     		<ul class="easyui-tree" id="tree" data-options="url:areaInfoAction.getDepartmentList">    
		     		</ul>
		    	</div>
			</div>
			<div data-options="region:'center'" style="width:50%">
				<table id="dg" class="easyui-datagrid" title="区域:"
						data-options="
						 fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     toolbar: '#add',
	                     pagination:false,
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     remoteSort:true
	                     ">
	                    <thead frozen="true">
		                    	<tr>
		                    		<th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
									<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>
		                    	</tr>
	                    </thead>
						<thead>
								<tr> 
									<!-- <th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
									<th data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th> -->
									<th data-options="field:'editor',hidden:true">editor</th><!-- 标注次行是否为编辑状态 -->
									<th data-options="field:'areaId',hidden:true">areaId</th>
									<!-- <th
										data-options="field:'areaCode',editor:{type:'validatebox',options:{validType:'mnemonicLength[0,10]'}},width:100,halign:'center'">组织机构代码</th> -->
									<th
										data-options="field:'areaCode',width:100,halign:'center',hidden:true">区域代码</th>
									<th
										data-options="field:'areaName',editor:{type:'validatebox',options:{required:true,validType:'length[1,40]'}},width:150,halign:'center'">区域名称</th>
									<th
										data-options="field:'areaFullName',width:350,halign:'center'">区域全名</th>
									<th
										data-options="field:'groupSort',editor:{type:'numberbox',options:{validType:'length[0,4]'}},width:80,align:'center'">同级组织排序</th>
									<th
										data-options="field:'contactPerson',editor:{type:'validatebox',options:{validType:'length[0,40]'}},width:120,halign:'center'">区域联系人</th>
									<th
										data-options="field:'cellTel',editor:{type:'validatebox',options:{validType:'mobile'}},width:120,halign:'center'">手机/电话</th>
									<th
										data-options="field:'areaAddress',formatter:tooltipFmt,editor:{type:'validatebox',options:{validType:'length[0,200]'}},width:200,halign:'center'">地址</th>
									<th
										data-options="field:'foundDate',width:100,align:'center',editor:{type:'choseDate'}">建立日期</th>
									<th
										data-options="field:'areaFax',editor:{type:'validatebox',options:{validType:'ChinaMobile[11,13]'}},width:120,align:'center'">传真</th>
									<th
										data-options="field:'areaEmail',editor:{type:'validatebox',options:{validType:'email'}},width:150,halign:'center'">电子邮箱</th>
									<th
										data-options="field:'memo',formatter:tooltipFmt,editor:{type:'validatebox',options:{validType:'length[0,200]'}},width:200,halign:'center'">备注</th>
								</tr>
						</thead>
			    </table>
			    <div id="add" style="height:25px">
					<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton"
						style="margin-left:10px;"
						data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
					<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton"
						style="margin-left:10px;"
						data-options="iconCls:'icon-no',plain:true" onclick="delBtnClick()">删除</a>
					<a id="reload" data-options="iconCls:'icon-reload',plain:true" href="javascript:void(0)" class="easyui-linkbutton"
						style="margin-left:10px;" onclick="reload()">刷新</a>
				</div>
	    	</div>
		</div>
  	</body>
</html>
