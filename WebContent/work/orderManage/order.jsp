<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!doctype html>
<html>
<head>
<title>订单管理</title>
<%@ include file="../../inc.jsp"%>
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/orderAction.js'></script>
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
			  
			 var statusType=row.status;
			 if(statusType==3321){
				  row.status="已下单（支付宝）";
				  }   
			  if(statusType==3221){
				  row.status="已下单（货到付款）";
			  } 
			  if(statusType==3421){
				  row.status="已下单（预存款）";
			  } 
			  if(statusType== 3521){
				  row.status="已下单（云库存）";
			  } 
			  
			  if(statusType==3322){
				  row.status="支付宝付款后备货中";
			  } 
			  
			  if(statusType==3222){
				  row.status="货到付款备货";
			  }   
			  
			  if(statusType==3422){
				  row.status="预付款备货";
			  }   
			  if(statusType==3522){
				  row.status="云库存备货";
			  }  
			  if(statusType==3323){
				  row.status="支付宝配送中";
			  }   
			  
			  if(statusType==3223){
				  row.status="货到付款配送中";
			  }   
			  
			  if(statusType==3423){
				  row.status="预付款配送中";
			  }   
			  
			  if(statusType==3523){
				  row.status="云库存配送中";
			  }   
			  if(statusType==3324){
				  row.status="支付宝已交货";
			  }
			  if(statusType==3224){
				  row.status="货到付款已交货";
			  }
			  if(statusType==3424){
				  row.status="预付款已交货";
				  
			  }
			  if(statusType==3524){
				  row.status="云库存已交货";
				  
			  }  if(statusType==4425){
				  row.status="已评论";
				  
			  }
			  if(statusType==1121){
				  row.status="订单未付款";
			  }			  
			  var payType=row.pay_type;
			  if(payType==1){
				  row.pay_type="预存款支付";
			  } 
			  if(payType==4){
				  row.pay_type="货到付款";
			  }
			  if(payType==5){
				  row.pay_type="积分支付";
			  } 
			  if(payType==6){
				  row.pay_type="支付宝";
			  } 
			  if(payType==8){
				  row.pay_type="云库存取货";
			  }
			  
		var idstring=id.toString();
	//	var caidstring=caid.toString();
		html += " <a href='javascript:void(0)' onclick='editBtnClick(&quot;"+idstring+"&quot;,this)' >详情</a>";
		return html;
	}
	//传递查询条件
	function onBeforeLoad(param) {
		param["user_name"] = $.trim($('#user_name').val());
		param["status"]=$.trim($('#status').val());
		param["pay_type"]=$.trim($('#pay_type').val());
		param["name"]=$.trim($('#name').val());
		param['sday']=$("#sday").datebox('getValue');
		param['eday']=$("#eday").datebox('getValue');
		
	}

	
	//修改
	//nid和上面的nidstring, caid为caidstring传过来的值，obj是this,顺序不能乱
	function editBtnClick(id,obj) {
		
		// alert(id);
		// alert("你好，你的id到这了");
		var url="";
		 url = "<%=basePath%>work/orderManage/orderDetail.jsp?id="+id;

	
			easyui_openTopWindow("订单详情 ",828,640,url,function(returnValue)
		{
			$('#dg').datagrid('reload');
		}); 
	}

</script>

<script type="text/javascript">
var usertype = ${LogInDemoEntity.userTypeFlag};
$(document).ready(function() {
	if(usertype==0){
		 $("#mem").show();
}	
else {
	 $("#mem").hide();
}
});



//设置默认时间
var strDate;
var startStr;
$(function(){   
	var curr_time = new Date();   
	strDate = curr_time.getFullYear()+"-";   
	strDate += curr_time.getMonth()+1+"-";   
	strDate += curr_time.getDate(); 
	
// 	curr_time.setDate(curr_time.getDate());
// 	startStr = curr_time.getFullYear()+"-0";
// 	startStr+= curr_time.getMonth()+1+"-"; 
// 	startStr+=curr_time.getDate();
	
	$("#sday").datebox("setValue", strDate); 
//	$("#sday").datebox("setValue", startStr ); 
});


//导出
function download() {
	var allparams={};
	var username=$("#username").val();	
	var encodeparam = encodeURI(username) ;
location.href="/exportOrderLog.do?username="+encodeparam+" ";
}



</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 100px">
		<div id="search" class="easyui-panel" title="订单管理"
			data-options="iconCls:'icon-search'">
			<div class="box" >
				<span>下单账号</span> <input id="user_name">
			</div>
				<div class="box" id="mem">
				<span>会员单位名称</span> <input id="name">
			</div>
			<div class="box">
				<span>订单状态</span> <select id="status">
					<option value="1" >已下单</option>
					<option value="2">备货中</option>
					<option value="3" >配送中</option>
					<option value="4">交货</option>
					<option value="5">订单未付款</option>	
					<option value="6">已评论</option>								
					<option value=" " selected="selected">全部</option>	
				</select>
			</div>
			
			<div class="box">
				<span>支付类型</span> <select id="pay_type">
					<option value="1" >预存款支付</option>
					<option value="4">货到付款</option>
					<option value="5" >积分支付</option>
					<option value="6">支付宝</option>
					<option value="8" >云库存取货</option>							
					<option value=" " selected="selected">全部</option>	
				</select>
			</div>
			
			<div class="box" style="margin-left:-500px;margin-top:30px;">
			   <span>&nbsp;&nbsp;时&nbsp;间&nbsp;</span>
				<input class="easyui-datebox" name="sday" id="sday" editable="false" size="10"/> --
				<input class="easyui-datebox" name="eday" id="eday" editable="false" size="10"/>
			      </div>  
			
			
			<span class="aBtn"> <a id="searchBtn"
				href="javascript:void(0)" class="easyui-linkbutton"
			 data-options="iconCls:'icon-search'"
				onclick="searchBtnClick()">查询</a>
			</span>
		</div>
	</div>

	<div class="clear"></div>
	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid" title="订单管理"
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
	                     url:orderAction.getList">
			<thead>
				<tr>
					<th
						data-options="field:'checked',checkbox:true,width:50,align:'center'">checked</th>
					<th
						data-options="field:'operations',formatter:operationFmt,width:50,align:'center'">操作</th>					
					<th
						data-options="field:'id',width:120,editor:{type:'validatebox',options:{required:true,validType:'length[1,20]'}},align:'center',halign:'center'">订单号</th>
					<th
						data-options="field:'user_name',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">下单账号</th>
					<th
						data-options="field:'name',width:200,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">会员单位名称</th>
					
					<th
						data-options="field:'status',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">订单状态</th>
					<th
						data-options="field:'pay_type',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">支付类型</th>
					
					<th
						data-options="field:'order_time',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">下单时间</th>
					<th
						data-options="field:'total_price',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">总价</th>
					<th
						data-options="field:'total_score',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">总积分</th>
<!-- 					<th -->
<!-- 						data-options="field:'goods_type_count',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">商品种类</th> -->
					<th
						data-options="field:'goods_count',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">商品总数</th>
					<th
						data-options="field:'receiver_name',width:150,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">收货人名称</th>
					<th
						data-options="field:'receiver_address',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">收货人地址</th>
					<th
						data-options="field:'receiver_mobile',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">收货人手机号</th>
					
					<th
						data-options="field:'referee',width:100,editor:{type:'validatebox',options:{required:true,validType:'length[1,11]'}},align:'center',halign:'center'">推荐人</th>

				</tr>

			</thead>

		</table>
	</div>
	<div id="add" style="height: 25px"> 
        <span id="downloadBtn" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left:10px;"
			data-options="iconCls:'icon-sum',plain:true" onclick="download()">导出excel文件</span>
	</div>
</body>
</html>
