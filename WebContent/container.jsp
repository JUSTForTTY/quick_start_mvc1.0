<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>标题</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="Copyright" content="" />
<meta name="Author" content="" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />

<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css" />
<link href="./css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="./script/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="./script/easyui/themes/icon.css" />
<script src="./script/jquery-1.8.3.js"></script>
<script src="./script/tree/jquery.treeview.js"></script>
<script src="./script/easyui/jquery.easyui.min.js"></script>
<!-- <script src="./script/easyui/jquery-1.8.0.min.js"></script> -->
<script src="./script/index.js"></script>
<script language="javascript" type="text/javascript" src="./script/jquery.contextmenu.r2.js"></script>
<script language="javascript" type="text/javascript">
	if ($.messager){
		$.messager.defaults.ok = '确定';
		$.messager.defaults.cancel = '取消';
	}
	function appendPage(pageUrl, title) {
		$("#useTable").addClass("hidden");
		$(".tabs-header").removeClass("hidden");
		var exists = $('#easyui-tabs').tabs("exists",title) ;
       	//是否已经存在
       	if(exists){
       		$('#easyui-tabs').tabs("select",title) ;
       	}
       	else
           $('#easyui-tabs').tabs('add',{  
               title:title,  
               content: '<iframe scrolling="yes" frameborder="0"  src="'+pageUrl+'" style="width:100%;height:100%;"></iframe>  ',  
               closable: true 
           });  
	}
	//几个关闭事件的实现
    function CloseTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#easyui-tabs");
        if (type === "close") {
            tabs.tabs("close", curTabTitle);
            return;
        }
        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];
        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "Other") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "All") {
                closeTabsTitle.push(opt.title);
            }
        });
        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
    }
    
    function showMenu(e, title) {
         e.preventDefault();
         $('#tabsMenu').menu('show', {
             left : e.pageX,
             top : e.pageY
         }).data("tabTitle", title);
     }
	
	$(function() {
		$(".tabs-header").addClass("hidden");
		//默认查询常用功能
		/* if(window.top.CommonFunctions!=""){
			data=window.top.CommonFunctions;
			var tr="<tr style='height:120px'>";
			var td="";
			for(var i=0;i<data.length;i++){
				if(i%4==0&&i>0){
					td=td+"</tr><tr style='height:120px'>";
				}
				var src=data[i]['MENU_SRC'];
				if(src!=null){
					var menu=src.split("/");
					src=(menu[menu.length-1]);
					src=(src.split("."))[0]+".png";
				}
				src="images/menu/"+src;
				td=td+"<td><a href='#' name='"+data[i]['MENU_SRC']+"' onClick='toLocation(this)'><img  height='68px' width='68px'  src='"+src+"' onerror='defaultImg(this)'></img></br></br>"+data[i]['FUNCTION_NAME']+"</a></td>";	
			};
			tr=tr+td+"</tr>";
			$(tr).appendTo($("#useTable"));
		} */
		//实例化menu的onClick事件
    	$("#tabsMenu").menu({
        	onClick : function (item) {
            	CloseTab(this, item.name);
        	}
    	});
		$("#easyui-tabs").tabs({
			onClose:function (title){
				if($("#easyui-tabs").tabs('tabs')==""){
					$(".tabs-header").addClass("hidden");
					$("#useTable").removeClass("hidden");
				};
			}
		});
		$(window).resize(function(){
			$(".easyui-tabs").tabs("resize");
			var iframe_Height = $(window).height()- $(".tabs-header").height() ;
  			$(".easyui-tabs").find(".tabs-panels") .css("height",iframe_Height).end()
  				.find(".panel-body") .css("height",iframe_Height).end()
  			 .find("iframe:visible") .css("height",iframe_Height) ;
		});
	});
	function defaultImg(T)
	{
		
		T.src = "images/menu/defaultMenuNull.png";//默认图片地址
		$(T).removeAttr("onerror");
	}
	
	function toLocation(obj){
		var src=$(obj).attr("name");
		if(src==""){
			return "";
		}
		var pageName=$(obj).text();
		var loc      = window.top.showframe.location +"";
		var prefix  = loc.substring(0,loc.lastIndexOf("/")) ;
		var pageUrl = prefix + "/"+src ;
		if(window.top.showframe.window.appendPage)
			window.top.showframe.window.appendPage.call(null,pageUrl,pageName) ;
	}
</script>
<style type="text/css">
#welcomeTXT{
	width:668px;
	height:169px;
	position:absolute;
	top:50%;
	margin-top:-180px;
	left:50%;
	margin-left:-412px;
	z-index:-1;
}

#welcomeBG{
	width:617px;
	height:393px;
	position:absolute;
	bottom:0;
	right:20px;
	z-index:-1;
}
</style>
</head>

<body scroll="no" style="overflow-y:hidden;overflow-x:hidden;height:100%;">
	<div class="easyui-tabs" data-options="height:document.body.clientHeight,onContextMenu:showMenu,onAdd:function(title,index){ 
               }"  id="easyui-tabs">     
       <table  id="useTable" style="width:100%;text-align:center;">
   			<tr  style="height:10px">
   				<td style="width:23%">
   				</td>
   				<td style="width:23%">
   				</td>
   				<td style="width:23%">
   				</td>
   				<td>
   				</td>
   			</tr>
       </table>       
    </div>
    <div id="welcomeBG">
<!-- 			<img src="images/welcomeBG.png"  width="636" height="393" /> -->
	</div>
	<div id="welcomeTXT">
		<!-- <img src="images/welcomeTXT.png" width="755" height="187" /> -->
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width:120px;"> 
	    <div name="close">关闭</div> 
	    <div name="Other">关闭其他</div> 
	    <div name="All">关闭所有</div>
	 </div> 
</body>
</html>