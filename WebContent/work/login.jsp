<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>管理系统</title> 
<%@ include file="../../inc.jsp"%>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="images/login.js"></script>
<link href="css/login2.css" rel="stylesheet" type="text/css" />

<link rel='stylesheet' type='text/css' href='<%=basePath%>css/style.css' />
<script type='text/javascript'
	src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript"
	src='<%=basePath%>/dwr/interface/baseUserAction.js'></script>
<script type='text/javascript' src='<%=basePath%>/js/layer/layer.js'></script>
<style type="text/css">
</style>
<script type="text/javascript">
$(function(){
	<%
	session.removeAttribute("maritimeID");
	%>
});
	function loadimage(){
		document.getElementById("randImage").src = "image.jsp?"+Math.random();
	}
		$(function(){
			$("#loginId").focus(function(){
				$(this).removeClass("psBg");
			});
			$("#loginId").blur(function(){
				if($(this).val()=="")
					$(this).addClass("psBg");
			});
			$("#loginPwd").focus(function(){
				$(this).removeClass("pswBg");
			});
			$("#loginPwd").blur(function(){
				if($(this).val()=="")
					$(this).addClass("pswBg");
			});
		});
		function btnClick(){
			var url="<%=basePath%>registration.jsp";

		easyui_openTopWindowLogin("注册", 10000, 10000, url,
				function(returnValue) {
					$(window.top.document.body).css({
						overflow : "hidden"
					});
					if (returnValue != null && returnValue != "") {
						parent.location.reload();/* parent.location.href = "index.jsp"; */
					}
				});
	}

	function enterPwd() {
		var event = arguments.callee.caller.arguments[0] || window.event;//消除浏览器差异  
		if (event.keyCode == 13) {
			$("#loginPwd").focus();
		}
	}
	function enterSumbit() {
		var event = arguments.callee.caller.arguments[0] || window.event;//消除浏览器差异  
		if (event.keyCode == 13) {
			login();
		}
	}
	function login() {
		var map = {};
		map['loginId'] = $("#loginId").val().trim();
		map['loginPwd'] = $("#loginPwd").val().trim();
		map['imageCode'] = $("#imageCode").val().trim(); 
		if (map['loginId'] == "" || map['loginPwd'] == "") {
			layer.open({
				content : '请输入用户名和密码',
				yes : function(index) {
					layer.close(index); //一般设定yes回调，必须进行手工关闭
					if (map['loginId'] == "") {
						$("#loginId").focus();
					} else {
						$("#loginPwd").focus();
					}
				}
			});
			return;
		}
		if (map['imageCode'] == "") {
			layer.open({
				content : '请输入验证码',
				yes : function(index) {
					layer.close(index); //一般设定yes回调，必须进行手工关闭
					if (map['imageCode'] == "") {
						$("#imageCode").focus();
					}
				}
			});
			return;
		}
		baseUserAction.getLoginInfo(map, {
			async : false,
			callback : function(data) {
				//console.log("log-----"+data.success);
				if (data.success) {
// 					$(window.top.document.body).css({
// 						overflow : "auto"
// 					});
// 					easyui_closeTopWindow();
					window.top.location.href="userIndex.jsp";
				} else {
					layer.open({
						content : data.msg,
						yes : function(index) {
							layer.close(index); //一般设定yes回调，必须进行手工关闭
							$("#loginId").focus();
						}
					});
// 					$.messager.alert('警告', data.msg, 'warning', function() {
// 						$("#loginId").focus();
// 					});
				}
			}
		});
	}

	function cancle() {
		parent.loginFlag = true;
		parent.layer.closeAll();

		$(window.top.document.body).css({overflow:"auto"});
		easyui_closeTopWindow();
	}
</script>


</head>
<body>
<div style="float: left;"><img src="" style="width:100px;height:120px;" />
</div><div><h1>后台管理系统<sup>V2016</sup></h1></div>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 300px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
               
               
               <div class="login-box">
    
            
			<div class="login_form">
				<form name="loginform"  id="login_form" class="loginForm" method="post"><input type="hidden" name="did" value="0"/>
               <input type="hidden" name="to" value="log"/>
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号：</label>
                <div class="inputOuter" id="uArea">
                    
                    <input type="text" id="loginId" name="loginId" class="inputstyle" onkeydown="enterPwd()"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">密码：</label> 
               <div class="inputOuter" id="pArea">
                    
                    <input type="password" id="loginPwd" name="loginPwd" class="inputstyle" onkeydown="enterSumbit()"/>
                </div>
                </div>
               <div class="uinArea" id="uinArea"><label class="input-tips">验证：</label> 
			<div class="inputOuter" id="uArea">
			<input type="text" class="inputstyle" value="${imageCode }" name="imageCode"
				id="imageCode" size="10" style="width: 80px" />&nbsp; 
			<img
				onclick="javascript:loadimage();" title="换一张试试" 
				name="randImage" id="randImage" 
				src="<%=basePath%>image.jsp" width="50" height="25"
				style="margin-left:20px;margin-top:0px;"
				border="1">
				</div>
			</div>
                <div style="padding-left:50px;margin-top:20px;"><input onclick="login()" value="登 录" style="width:150px;" class="button_blue"/></div>
              </form>
           </div>       
            	</div>
               
            </div>
            <!--登录end-->
  </div>

</div>

</body>
</html>
