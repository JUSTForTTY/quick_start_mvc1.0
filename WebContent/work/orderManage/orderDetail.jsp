<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
   <title>订单详情</title>
    <!-- 引用共通 js 和 样式  -->
    <%@ include file="../../inc.jsp"%>
    <!-- dwrloader.js dwr 框架必须使用 -->
    <script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<!-- demoAction.js 调用后台方法 -->
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/orderAction.js'></script>
	<style type="text/css">
		.lableWidth120{width:72px;float:left;white-space: nowrap;margin-top:5px}
		.lableLine350{width:250px;float:left;margin-top:15px;}
	</style>
	<script type="text/javascript">
	
	// 接受
	var map=easyui_getRequestPara();
	var id=map["id"];
	
	function onBeforeLoad(param){
		param["id"]=id;
		
	}
	function gshi(val){
	      
		if(val!="undefined"&&val!=null){
			var value= val.toFixed(2);
			return '<span>￥'+value+'</span>';
		}else{
			return '<span></span>';
		}
	}	
	</script>
  </head>
  <body class="easyui-layout">
	<div data-options="region:'center'" style="padding:0px 0 5px 0">
		<table id="dg" class="easyui-datagrid" title="商品详情"
			data-options="
	                     fit:true,
	                     iconCls: 'icon-edit',
	                     rownumbers:true,
	                     toolbar: '#add',
	                     pagination:true,
	                     singleSelect:true,
	                     selectOnCheck:false,
	                     checkOnSelect:false,
	                     onBeforeLoad:onBeforeLoad,
	                     pageSize:10,
	                     pageList:[10,20,30,40],
	                     url:orderAction.getByid">
			<thead>
				<tr>
					<!--  <th data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>-->
					<th data-options="field:'id',align:'right',halign:'center',width:140">子订单编号</th>
					<th data-options="field:'goods_name',align:'right',halign:'center',width:200">商品名称</th>
					<th data-options="field:'unit_price',align:'right',halign:'center',width:150">单位价格</th>
					<th data-options="field:'unit_score',align:'right',halign:'center',width:150,formatter:gshi">单位积分</th>
					<th data-options="field:'goods_count',align:'right',halign:'center',width:150">单位数量</th>
				</tr>
			</thead>
		</table>
		 
	</div>
  </body>
</html>
