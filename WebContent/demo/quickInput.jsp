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
<script language="javascript" type="text/javascript"
	src='/SafeLease/dwr/interface/memonicAction.js'></script>
<style type="text/css">
.width100p {
	width: 100%;
}
</style>
<script language="javascript" type="text/javascript">
	$(function() {
		//收缩默认后面第一个class为box的div
		$(".boxToggle").spanToggle();

		$("#city").autocomplete(
				{
					source : function(request, response) {
						for(var p in request){
							alert(p + "-" + request[p]);
						}
						memonicAction.getMemonicList(request,"",function(data){
							response(data);
						});
						
					},
					minLength : 2,
					select : function(event, ui) {
						$("#selectInput").val("选择的值为:"+ui.item.label + "=" + this.value);
					},
					open : function() {
						$(this).removeClass("ui-corner-all").addClass(
								"ui-corner-top");
					},
					close : function() {
						$(this).removeClass("ui-corner-top").addClass(
								"ui-corner-all");
					}
				}).data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "<br>" + item.value + "</a>" )
						.appendTo( ul );
				};
	});
</script>
</head>

<body onload="reverseAjax()">
	<div class="topTag" style="clear:both;">
		<span class="tagLeft"></span> <span class="tagMid">DWR 助记符控件</span> <span
			class="tagRight"></span> <span class="btnBox"> <span
			class="clear" style="height:1px;">&nbsp;</span> </span>
	</div>
	<span class="clear"></span>
	<span class="boxToggle ">助记符</span>
	<div class="box">
		<input id="city" />
		<input id="selectInput" />
	</div>
</body>
</html>
