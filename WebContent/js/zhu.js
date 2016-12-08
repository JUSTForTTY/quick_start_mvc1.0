$(function(){
		//登录与注册
		checkLogin();
		$("#login").click(function(){
			var url=basePath+"/login.jsp";
			layer.open({
			    type: 2,
			    title: '用户登录/注册',
			    shadeClose: false,
			    shade: 0.4,
			    area: ['600px', '450px'],
			    content: url,
			    end :function(layero, index){
			    	if(loginFlag){
			    		loginFlag=false;
						if(loingRval!=""){
						indexAction.getUser({
							async:false,
							callback:function(data){
								if(data != null){
									loginUserName = data['userName'];
									checkLogin();
								}
							}	
						});		
						}
					}
				}
			});
		});
		$("#logo").click(function(){
			window.top.location.href="index.jsp";
		});
		$("#registration").click(function(){
			var url=basePath+"/declaration.jsp";
			layer.open({
			    type: 2,
			    title: '用户注册',
			    shadeClose: false,
			    shade: 0.4,
			    area: ['600px', '450px'],
			    content: url,
			    end :function(layero, index){
			    	if(loginFlag){
			    		loginFlag=false;
			    		if(loingRval!=null&&loingRval!="")
							location.reload();
			    	}
			   	 }
			    }); 
		});
		$("#mailImg").live("click",function(){
			var url=basePath+"/work/toRent/messageInfo.jsp";
			layer.open({
			    type: 2,
			    title: '邮件',
			    shadeClose: false,
			    shade: 0.4,
			    area: ['950px', '650px'],
			    content: url,
			    end :function(layero, index){
			    	commonMethodAction.getSumMessage({},function(data){
						if(data>0){
							$("#mailImg").attr("src",""+basePath+"/images/MailGif.gif");
						}else{
							$("#mailImg").attr("src",""+basePath+"/images/Mail.gif");
						} 
					});
			    }
			}); 
		});
		//邮件
		commonMethodAction.getSumMessage({},{
			async : true,
			callback : function(data) {
			if(data>0){
				$("#mailImg").attr("src",""+basePath+"/images/MailGif.gif");
			}	
			}
		});
		//站点轮播图,统计信息
		if(zhan!=undefined&&zhan!=""){
			
			//站点图标
			baseUserAction.getUserServiceCenterById(zhan,{
				async : true,
				callback : function(data) {
					if(data!=null){
						document.getElementById("logo").style.backgroundImage="url("+basePath+data['path']+")";
						$("#logoTitle").html(data[ 'name']+"平安屋超市");
					}
					
				}
			});
			commonMethodAction.getcenterCarousel(zhan,{
				async : true,
					callback : function(data) {
						for(var i=0;i<data.length;i++){
							$("#img"+data[i]["CAROUSEL_SORT"]).attr("src",basePath+data[i]['CAROUSEL_PATH']);
							
						}
					}
				});
			commonMethodAction.getCenterNotice({rows:8,serviceCenterId:zhan},{
				async : true,
				callback : function(data) {
					var html="";
					for(var i=0;i<data.length;i++){
						html+="<li id='"+data[i]['SERVICE_CENTER_NOTICE_ID']+"'>"+data[i]['NOTICE_TITLE']+"</li>"
					}
					$(".gDetailed").html(html);
				}
			});
			indexAction.getInfoSum(zhan,{async : true,
				callback : function(data) {
					if(data['flag']){
						$("#sumHouse").text(data["sumHouse"]);
						$("#sumNeed").text(data["sumNeed"]);
						$("#sumDeal").text(data["sumDeal"]);
					}
				}
			});
			
		}else{
			$(".top-image,.sumMeun").remove();
		}
		//公告阅读
		$(".gonggao ul li").live("click",function(){
			var url=basePath+"/subCenterNotice.jsp?id="+$(this).attr("id");
			layer.open({
			    type: 2,
			    title: '公告明细',
			    shadeClose: false,
			    shade: 0.4,
			    area: ['600px', '450px'],
			    content: url,
			}); 
		});
		
		//更多
		$(".gRight").live("click",function(){
			var url=basePath+"/centerNotice.jsp?id="+1;
			layer.open({
			    type: 2,
			    title: '公告明细',
			    shadeClose: false,
			    shade: 0.4,
			    area: ['800px', '750px'],
			    content: url,
			}); 
		});
		
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
		if(loginUserName !="" ){
			$("#login").hide();
			$("#registration").hide();
			var html="<a style='height:30px;width:35px;'><image id='mailImg' style='padding-top:8px;' src='"+basePath+"/images/Mail.gif'/></a> <a style='width:100px;'>用户名:"+loginUserName+"</a> <a id='logout' href='javascript:void(0)' onclick='cancleOut()'>退出</a>";
			$("#lrg").append(html);
		};
	}
	function cancleOut()
	{
		baseUserAction.logout( {
			async : false,
			callback : function(data) {
				location.reload();
			}
		});
	}