<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>后台管理系统</title>
<%@ include file="inc.jsp"%>
<link href="htyxStyle.css" rel="stylesheet" type="text/css" />

<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
	<script type="text/javascript" src='<%=basePath%>/dwr/interface/baseUserAction.js'></script>
<script type="text/javascript" src='script/datePicker/WdatePicker.js'></script>
<script type='text/javascript' src='<%=basePath%>/js/layer/layer.js'></script>

<style type="text/css">
#mainLeft {
	width: 304px;
	float: left;
	margin-right: 10px;
}

#mainLeft a {
	width: 145px;
	line-height: 145px;
	height: 145px;
	display: block;
	float: left;
	text-align: center;
	font-size: 16px;
	border: 1px solid #E8E8E8;
	cursor: pointer;
}

#mainLeft  a:hover, #mainLeft  .hover {
	background-color: #62AF00;
	color: #fff;
	border: 1px solid #62AF00
}

._cl {
	clear: both;
}

#serviceCenter {
	width: 352px;
	line-height:145px;
	height: 145px;
	border: 1px solid #E8E8E8;
	float: left;
}

#serviceCenter ul {
	margin: 58px auto;
}

#serviceCenter ul li {
	display: block;
	float: left;
	height: 28px;
	text-align: left;
	line-height: 28px;
	font-size: 14px;
	margin: 0 0 10px 10px;
	cursor: pointer;
	padding: 0 5px;
}

#serviceCenter ul .hover, #serviceCenter ul li:hover {
	color: #fff;
	background-color: #8AB923;
}

#mainRights {
	border: 1px solid #E8E8E8;
	height: 145px;
	width: 300px;
	float: left;
	margin-left: 10px;
}

#mainRights img {
	float: left;
	margin: 7px 0 0 10px
}

#mainRights .text {
	background-color: #F6FFE7;
	float: right;
	width: 157px;
	height: 145px;
}

#mainRights .text h1 {
	line-height: 42px;
	display: block;
	font-size: 16px;
	color: #8AB923;
	text-align: center;
	border-bottom: 1px solid #E8E8E8
}

#mainRights .text p {
	text-align: center;
}

.footClass {
	text-decoration: none;
	color: white;
	font: 14px '宋体';
	cursor: pointer;
}

.title {
	overflow: hidden;
	height: 21px;
}
</style>
<script language="javascript" type="text/javascript">
	var loginFlag=false;
	var loingRval="";

	$(function(){
		checkLogin();
		$("#login").click(function(){
			var url="<%=basePath%>login.jsp";
			easyui_openTopWindowLogin("登录,",500,500,url,function(returnValue)
			{
				if(returnValue=="null"||returnValue=="0"){
					alert("null");
					easyui_openTopWindow("选择,",532,387,"<%=basePath%>work/popularPages/selectPage.jsp",function(returnValue)
					{
					});
				}else {
					window.top.location.href="userIndex.jsp";
				}
			});
		});
		$("#registration").click(function(){
			/* $("body").css({overflow:"hidden"});   */
			var url="<%=basePath%>registration.jsp";
			easyui_openTopWindowLogin("注册",10000,10000,url,function(returnValue)
			{
				if(returnValue!=null&&returnValue!="")
					location.reload();/* parent.location.href = "index.jsp"; */
			});
		});
		$("#logout").click(function(){
			baseUserAction.logout( {
					async : false,
					callback : function(data) {
						parent.location.href = "index.jsp";
					}
				});
		});		
		
		var loginUserMap={num:10,pageNum:1};
		var loginUserId='<%=loginUserId%>';
	});
	
	function add_favorite(a, title, url) {
		url = url;
		title = title;
		try{ // IE
			window.external.addFavorite(url, title);
		} catch(e) {
			try{ // Firefox
				window.sidebar.addPanel(title, url, ""); 
				
			} catch(e) {
				if (/Opera/.test(window.navigator.userAgent)) { // Opera
					a.rel = "sidebar";
					a.href = url;
				}
				layer.open({
				    content: '加入收藏失败，请使用 Ctrl+D 进行添加。',
				    yes: function(index){
				        layer.close(index); //一般设定yes回调，必须进行手工关闭
				    }
				});  
				/* $.messager.alert('提示', '加入收藏失败，请使用 Ctrl+D 进行添加。'); */
				/* alert('加入收藏失败，请使用 Ctrl+D 进行添加'); */
			}
		}
	}
	function set_homepage(a, url) {
		var tip = '您的浏览器不支持此操作\n请使用浏览器的“选项”或“设置”等功能设置首页。';
		url = url;
		try {
			a.style.behavior = 'url(#default#homepage)';
			a.setHomePage(url);
		} catch(e) {
			try { 
				if(window.netscape)
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
				else
					layer.open({
					    content: tip,
					    yes: function(index){
					        layer.close(index); //一般设定yes回调，必须进行手工关闭
					    }
					});  
					/* $.messager.alert('提示', tip); */
			} 
			catch (e) {
				layer.open({
				    content: tip,
				    yes: function(index){
				        layer.close(index); //一般设定yes回调，必须进行手工关闭
				    }
				}); 
				/* $.messager.alert('提示', tip); */
			/* alert(tip); */
			}
		}
	}
	function checkLogin(){
		var loginUserName='<%=loginUserName%>';
		if(loginUserName !="" ){
			$("#login").hide();
			$("#registration").hide();
			var html="<a style='height:30px;width:35px;' onclick='openMessage()'><image id='mailImg' style='padding-top:8px;' src='"+'<%=basePath%>'+"images/Mail.gif'/></a> <a style='width:100px;'>用户名:"+loginUserName+"</a> <a id='logout' href='javascript:void(0)' onclick='cancleOut()'>退出</a>";
			$("#lrg").append(html);
		};
	}
	function cancleOut()
	{
		baseUserAction.logout( {
			async : false,
			callback : function(data) {
				parent.location.href = "index.jsp";
			}
		});
	}
	
</script>

</head>
<body>
	<div class="outer">
		<div class="top-blue">
			<div class="top-blue-ct">
				<div id="SYandSC">
					<a href="javascript:void(0)"
						onclick="set_homepage(this,'http://www..com/');">设为首页</a>&nbsp; |
					&nbsp;<a href="javascript:void(0)"
						onclick="add_favorite(this,'苏州海事处后台管理','');">收藏本站</a>
				</div>
				<div id="LGandRG">
					<div id="lrg" style="float: right">
						<a id="login" href="javascript:void(0)">登录</a> 
					</div>
				</div>
			</div>
		</div>
		<div class="top-logo">
			<div class="top-logo-ct">
				<!-- <div id="logo">
					
				</div> -->

			</div>
		</div>
		<!--  <div class="top-menu">
  	<div id="menu" class="top-menu-ct"></div>
  </div> -->
		<div class="top-image">
			<div class="loopImage">
				<!--轮播图 js文件-->
				<!-- <script src="js/jquery-1.8.3.min.js"></script> -->
				<script src="js/jquery.luara.0.0.1.min.js"></script>
				<script>
        $(function(){
            /* <!--调用Luara--> */
            $(".example").luara({width:"980",height:"291",interval:2500,selected:"seleted",deriction:"left"});
        });
    </script>

				<!--Luara图片切换骨架begin-->
				<div class="example" style="position: absolute; top: 102px;">
					<ul>
						<li><img src="images/lbt01.jpg" width="980" height="291"
							alt="1" /></li>
						<li><img src="images/lbt02.jpg" width="980" height="291"
							alt="2" /></li>
						<li><img src="images/lbt03.jpg" width="980" height="291"
							alt="3" /></li>
						<li><img src="images/lbt04.jpg" width="980" height="291"
							alt="4" /></li>
					</ul>
					<ol>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ol>
				</div>
				<!--Luara图片切换骨架end-->

			</div>
		</div>
		<!-- <div class="center"> -->
		
		<!-- </div> -->
    	<div class="footer_out">
    <div class="footer">
    		<span><a class="footClass">关于我们</a></span>|
			<span><a class="footClass">联系我们</a></span>|
			<span><a class="footClass">招聘信息</a></span>|
			<span><a class="footClass">法律声明</a></span>|
			<span><a class="footClass">广告服务</a></span>
			<span class="clear"> </span>
        <a>处版权所有   Copyright 2015 All Rights Reserved.</a>
        	<span class="clear"> </span>
        <a style="line-height:10px;">苏州创游科技有限公司提供技术支持</a>
    </div>
  </div> 
</body>
</html>
