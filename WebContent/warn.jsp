<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>警告</title>
		<script language="javascript">
		function reLogin(){
			var dialog = window.dialogHeight;
			if(dialog != null) {
				 window.top.close();
			}
			else {
				var userAgent = navigator.userAgent;
				if (userAgent.indexOf("Firefox")!= -1 || userAgent.indexOf("Presto") != -1) {
	   				  window.top.location.replace("about:blank");
				} else {
				     window.top.opener = null;
				     window.top.open("login.jsp", "_self");
				    window.top.close();
				}
				<%-- var obj=window;
				if(window.top!=null)obj=window.top;
				obj.location.href = "<%=request.getContextPath()%>/login.jsp"; --%>
			}
		}
		</script>
	</head>
<body>
<div style="width:100%;height:100%;overflow:hidden" align="center">
	<table>
		<tr>
			<td height="150">&nbsp;</td>
		</tr>
	</table>
	<table width="387" height="227" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td background="<%=request.getContextPath()%>/images/warn.gif">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="41">&nbsp;</td></tr>
					<tr>
						<td><div align="center"><img src="<%=request.getContextPath()%>/images/warn01.gif" width="328" height="72"></div></td>
					</tr>
					<tr><td height="24">&nbsp;</td></tr>
					<tr>
						<td height="25">
						<div align="center"><input HIDEFOCUS="true" type="image" src="<%=request.getContextPath()%>/images/warn02.gif" onclick="reLogin();"></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
