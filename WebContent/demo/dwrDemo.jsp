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
	src='/SafeLease/dwr/interface/dwrAction.js'></script>
<style type="text/css">
.width100p {
	width: 100%;
}
</style>
<script language="javascript" type="text/javascript">
	//更新前台页面的进度条
	function updateProgress(uploadInfo) {
		var progressPercent = Math
				.ceil((uploadInfo.bytesRead / uploadInfo.totalSize) * 100);
		$("#progress").progressBar(progressPercent);
	}

	//启动主动ajax
	function reverseAjax() {
		dwr.engine.setActiveReverseAjax(true);
	}

	$(function() {
		//绑定日期控件使用说明
		$(".date").click(WdatePicker);

		//收缩默认后面第一个class为box的div
		$(".boxToggle").spanToggle();
		
		//助记符控件最简单的例子
		$("#editInput").keydown(function() {
			$(".dropdown").show().click(function(e){
				alert("选择操作");
				e.stopImmediatePropagation();
			});
			
			  $(document).bind("click", function(e) {$(".dropdown").hide();});
		});
		
		//最基本的dwr调用例子
		$("#btn").click(
				function() {
					//传递js对象
					var map = {
						username : "asdf"
					};
					//传递js数组
					var list = [ {
						username : "asdf"
					}, {
						username1 : "asdf1"
					} ];
					//传递java pojo对象
					var user = {
						username : "username",
						password : "password"
					};
					//传递java pojo对象数组
					var userlist = [ user, user ];
					dwrAction.helloworld("string", map, list, user, userlist,
							function(e) {
								alert(e['username'] + "----------"
										+ e['password']);
							});
				});

		//文件上传事件
		$("#uploadFileBtn").click(function() {
			$("#progress").text("").progressBar();
			var filename = "文件名.zip"; //$("#uploadFile")[0] 
			dwrAction.uploadFile(uploadFile, filename, function(data) {
				$("filename").text(data);
			}) ;
			//开始监听上传数据 0.6秒推送一次  
			dwrAction.listener();
		});
		$("#downloadBtn").click(function(){
			dwrAction.exportExcel({
			        callback:function(data){
			            dwr.engine.openInDownload(data);
			        },
			        async : false
			    })
		});
	});
</script>
</head>

<body onload="reverseAjax()">
	<div class="topTag" style="clear:both;">
		<span class="tagLeft"></span> <span class="tagMid">DWR 示例页面</span> <span
			class="tagRight"></span> <span class="btnBox"> <span
			class="clear" style="height:1px;">&nbsp;</span> </span>
	</div>
	<span class="clear"></span>
	<span class="boxToggle ">基本信息</span>
	<div class="box form">
		<div class="width3">
			<b>请在jsp源代码中搜索dwrAction.helloworld方法</b>
		</div>
		<button id="btn">基本调用</button>
	</div>
	<div class="boxToggle ">上传下载文件</div>
	<div class="box ">
		<input type="file" id="uploadFile" name="uploadFile" />
		<button id="uploadFileBtn">上传文件</button>
		<span class="progressBar" id="progress"></span><span id="filename"></span>
		<button id="downloadBtn">下载文件(excel文件导出)</button>
	</div>
	<span class="boxToggle ">表格操作</span>
	<div class="box" >
		<!-- 表格控件 -->
		<a href="tableDemo.jsp" >表格例子</a>
	</div>
	<span class="boxToggle ">日期控件</span>
	<div class="box form">
		<div>日期输入</div>
		<input class="date width1" /> <span class="clear" />
	</div>
	<span class="boxToggle ">助记符</span>
	<div class="box form">
		<div>
			<a href="quickInput.jsp">助记符例子</a>
			<input id="editInput" />
			<div class="dropdown hidden">
				<table style="border:1px solid red;">
					<thead>
						<tr>
							<th width="30">编号</th>
							<th width="50">名称</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<span class="clear" />
	</div>
</body>
</html>
