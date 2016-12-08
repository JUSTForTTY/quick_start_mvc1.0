<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>站点管理-所属区域选择</title>
<%@ include file="../../inc.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/script/easyui/easyui-validatebox.js"></script>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/serviceCenterAction.js'></script>
<script type="text/javascript">
	var id = null;
	$(function() {
		$('#tree').tree({
			onLoadSuccess : function(node, data) {
				/*if (data.length != 0) {
					node = $('#tree').tree('find', id);
					$('#tree').tree('select', node.target);
				}*/
			},
			onDblClick: function(node) {
				//alert(node.id);
				//alert(node.text);
				easyui_closeTopWindow(node);
			}
		})
	});
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div id=treePanel class="easyui-panel" title="所属区域"
			data-options="fit:true,iconCls:'icon-search'">
			<ul class="easyui-tree" id="tree"
				data-options="url:serviceCenterAction.getAreas">
			</ul>
		</div>
	</div>
</body>
</html>