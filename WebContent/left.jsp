<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<script src="./script/jquery-1.8.3.js"></script>
<script src="./script/tree/jquery.treeview.js"></script>
<script src="./script/index.js"></script>
<script type="text/javascript" src="<%=basePath%>/dwr/engine.js"></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/generalMethodAction.js'></script>
<script type="text/javascript">
		function toLocation(src,pageName){
			var loc      = window.parent.showframe.location +"";
			var prefix  = loc.substring(0,loc.lastIndexOf("/")) ;
			var pageUrl = prefix + "/"+src ;
			if(window.parent.showframe.window.appendPage)
				window.parent.showframe.window.appendPage.call(null,pageUrl,pageName) ;
		}
<%-- 		toLocation($(this).attr("link"),"<%=basePath%>work/statisticsManage/statistics.jsp"); --%>
		function toMenu(menuObj, $ul) {
			var menus = menuObj;
			for(var i in menus){
				var mc = menus[i].text;
				var link = menus[i].link;
				
				// 新增一个菜单
				var $li = $("<li></li>").addClass("open").attr("link",link ).append(
						$("<span></span>").text(mc).attr("link", link).addClass("file"));
				     if (link != null&& link != "" ) {
					$li.find("span").css("cursor", "pointer").click(function() {
						var pageName = $(this).html() ;
					
						toLocation($(this).attr("link"),pageName);
					});
				} 
				$ul.append($li);
	
			var childNodes = menus[i].ChildNodes;
			if (childNodes != null && childNodes.length > 0) {
				// 新增一个子菜单
				$li.find("span").removeClass("file").addClass("folder");
				var _$ul = $("<ul></ul>").appendTo($li);
				toMenu(childNodes, _$ul);
			}
		}
	
		return $ul;
	}
	</script>
<script type="text/javascript">
//清空选中状态的方法
	function clone(obj){
	    var o;
	    if(typeof obj == "object"){
	        if(obj === null){
	            o = null;
	        }else{
	            if(obj instanceof Array){
	                o = [];
	                for(var i = 0, len = obj.length; i < len; i++){
	                    o.push(clone(obj[i]));
	                }
	            }else{
	                o = {};
	                for(var k in obj){
	                    o[k] = clone(obj[k]);
	                }
	            }
	        }
	    }else{
	        o = obj;
	    }
	    return o;
	}
	
	var _treeview = null ; //左边树对象
	//动态下下拉框加载值(codemaster里的值)
	$(function() {	  
             //以树的结构显示数据 
		   function getPage(){
		   var map={};
		           generalMethodAction.getPagelist(map,function(data){
		            	//o.data = data;  
		            	//oldData = clone(data);//数据克隆
		   		        //$("#browser").treeview(o);
		   		        //var $ul = $("<ul></ul>").addClass("filetree").appendTo($("#browser"));	
		   		        if(data.length > 0)
		   		        {					
		   		        	var $ul = $("<ul></ul>").addClass("filetree").appendTo($("#browser"));			
							toMenu(data, $ul);
							_treeview = $ul.treeview();
						}
						<%-- //放在菜单全部读取之后的方法里面，解决同步问题
						rolePageSetAction.getRelByRoleId('<%=user.getRoleId()%>',function(data){
	        			  		//if(data.MenuList.length>0){
		        			 	 if(data.InitPage.menuId != null && typeof(data.InitPage.menuId) != "undefined"){
				           			   toLocation(data.InitPage.menuUrl,data.InitPage.menuName);		       	
				           			   //展开对应的菜单
				           			   expandSth(data.InitPage.menuUrl) ;
				   		    	}	
		   		  		});	 --%>	    
		     	});
		   	}
   		   getPage();//调用方法
   		   
   		   //递归寻找父节点
   		   function findParent(p){
   		   		var _p =p.parent().parent() ;
   		   		if(_p != undefined && _p.length > 0 && _p.is("li")) 
   		   			return _p;
   		   		else 
   		   			return null;
   		   }
   		   //展开当前菜单的节点
   		   function expandSth(defaultMenuUrl){ 
				var p = $("li[src='"+defaultMenuUrl+"']",$("#browser"));
				while((p = findParent(p)) != null){
					p.find(">span").click() ;
				}
   		   }
   		   
   		   
   		   var leftState = "0" ;
		$("#changeleft").click(function(){
			parent.document.getElementsByName("mainframe")[0].cols = parent.document.getElementsByName("mainframe")[0].cols =="12,*" ? "180,*" : "12,*";
			if(leftState == "0"){
				//切换到收起状态
				leftState = "1";
				$("#changeleft") .find("img").attr("src","images/arrowDv-R.png");
				$("#container").hide();
			}
			else
			if(leftState == "1")
			{
				//切换到展开状态
				leftState = "0";
				$("#changeleft").find("img").attr("src","images/arrowDv-L.png");
				$("#container").show();
			}
		});
     });
</script>
</head>
<body scroll="no" style="overflow-y:hidden;overflow-x:hidden;height:100%;">
	<div style="height:100%;width:198px;float:left;overflow-y:auto;overflow-x:hidden;" id="container">
		<div id="browser"></div>
	</div>
	<div style="background-color:#3083d1;height:100%;width:12px;float:left;cursor:pointer;" id="changeleft">
		<img src="images/arrowDv-L.png" 
			style="position:absolute;right:0px;top:50%; margin-top:-6px; width:12px;" />
	</div>
	<!-- <ul id="browser" class="filetree">
	</ul> -->
</body>
</html>
