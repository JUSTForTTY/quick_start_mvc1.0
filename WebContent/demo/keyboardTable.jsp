<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>sample</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Copyright" content="" />
<meta name="Author" content="" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<style>
.theDiv{filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;z-index:100;}
</style>
<script>
$(function(){

	var table;

	$.fn.extend({
		keyboardTable: function(options){
			// 定义默认值
			var defaults = {
			};
			
			//默认选中行
			var selectedRow = $("tbody tr",$(this)).eq(0);
			
			$("tbody tr",$(this)).click(function(){
				selectedRow = $(this);
			});
			
			//选中高亮
			table.highLight(selectedRow);
			
			var bDiv = $(this).closest(".bDiv");
			
			//加上tabindex就可以响应onkeydown事件
			var obj = $(this).closest(".bDiv").prop("tabindex",-1).get(0);
			$(document).eq(0).focus()
			// 合并覆盖默认值
			var opts = $.extend({}, defaults, options);		
			//上下选择事件			
			var scrollFunc=function(e){
				    e=e||window.event;
				    if(e.keyCode==38||e.keyCode==40){
					    if(e.keyCode==38 && selectedRow.prev("tr").length > 0) {
					        	selectedRow = selectedRow.prev("tr");
					    }
					    else if(e.keyCode==40 && selectedRow.next("tr").length > 0) {
					        	selectedRow = selectedRow.next("tr");
					    }	
					    var offset = selectedRow.offset();
					    if(offset.top > bDiv.height() - 5){
					    	bDiv.scrollTop(bDiv.scrollTop() + selectedRow.height());
					    }
					    else if(offset.top < selectedRow.height() + 5){
					    	bDiv.scrollTop(bDiv.scrollTop() - selectedRow.height());
					    }
				    }
				    			    
					table.highLight(selectedRow);
	
					if ((e.keyCode==38||e.keyCode==40)&&e&&e.preventDefault){ 
				        e.preventDefault();
				        e.stopPropagation();
				   	}else{ 
				     //e.returnvalue=false;  
				     //return false;     
					}  
			};					
			
			obj.onkeydown=scrollFunc;
			document.onkeydown=scrollFunc;
		}
	});
	
	var testData = [ {
			id:123,
			text : "as@163.com",
			name : "http://sdas.dfasd.com",
			type : "aaa33333",
			remark : "aaa4"
		}
		 , {
			text : "bbb",
			name : "bbb",
			type : "bbb",
			remark : "bbb"
		}, {
			text : "ccc",
			name : "ccc",
			type : "ccc",
			remark : "ccc"
		}, {
			text : "ddd",
			name : "ddd",
			type : "ddd",
			remark : "ddd"
		}, {
			text : "eee",
			name : "eee",
			type : "eee",
			remark : "eee"
		}, {
			text : "fff",
			name : "fff",
			type : "fff",
			remark : "fff"
		}, {
			text : "ggg",
			name : "ggg",
			type : "ggg",
			remark : "ggg"
		}, {
			text : "ccc",
			name : "ccc",
			type : "ccc",
			remark : "ccc"
		}, {
			text : "ddd",
			name : "ddd",
			type : "ddd",
			remark : "ddd"
		}, {
			text : "eee",
			name : "eee",
			type : "eee",
			remark : "eee"
		}, {
			text : "fff",
			name : "fff",
			type : "fff",
			remark : "fff"
		}, {
			text : "ggg",
			name : "ggg",
			type : "ggg",
			remark : "ggg"
		}, {
			text : "ccc",
			name : "ccc",
			type : "ccc",
			remark : "ccc"
		}, {
			text : "ddd",
			name : "ddd",
			type : "ddd",
			remark : "ddd"
		}, {
			text : "eee",
			name : "eee",
			type : "eee",
			remark : "eee"
		}, {
			text : "fff",
			name : "fff",
			type : "fff",
			remark : "fff"
		}, {
			text : "ggg",
			name : "ggg",
			type : "ggg",
			remark : "ggg"
		}
		];
	
	//表格控件
		table = $('#tab').flexigrid({
			height:100,
			striped : false,			
			useRp : true,
			rp : 15,
			width : "640",
			autoHeight : "false",
			resizable: true,
		}).flexEditRow({			
			addId : "add",
			colName : "name",
			initData : testData,
		});
		
		table.keyboardTable();
});
</script>
</head>
<body>
	<table id="tab" style="border-collapse:collapse;"  >
		<thead>
			<tr>				
				<th width="30" >id</th>
				<th width="100">名称</th>
			</tr>
		</thead>
		<tbody>
			<tr class="hidden" t=template>
				<td><span itemNum ></span>
				</td>
				<td><span class="width1" item name="id" ></span>
				</td>
				<td><span class="width1" item name="text" ></span>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
