<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>评论区</title>
<%@ include file="../../inc.jsp"%>
<link href="<%=basePath%>css/comment.css" rel="stylesheet"	type="text/css" />
<script type='text/javascript' src='<%=basePath%>/script/easyui/dwrloader.js'></script>
<script type="text/javascript" src='<%=basePath%>/dwr/interface/commentManageAction.js'></script>
<!--  <script src="../../script/bootstrap.min.js"></script>-->

<script type="text/javascript">
var aid="<%=request.getParameter("aid")%>";

//aid="jk2";
var pageN=1;
var pNum=3;//一页显示记录数

$(function(){
	pageN=1;

	commentManageAction.getActivityName({"aid":aid},{
		async : false,
		callback : function(data){
		   $('#J_NewsTitle').html(data[0].name);	
			}
			
		});

	var com_num=loadComment();
	//alert(com_num);
	$('#J_Comment_More_Latest').css('visibility','hidden');
	if(com_num==0)
		$('.tab_content .J_Comment_List_Hot').html("<div class='com_tip'>暂时还没有人评论！</div");
	else if(com_num==pNum) $('#J_Comment_More_Latest').css('visibility','visible');

   	$('.navtab a').click(function(event){
		if($(this)){
			pageN=1;
			event.preventDefault();
			var id=$(this).attr('href');
			
			$('.tab_content').children('.tab-pane').removeClass('active');
			$(id).addClass('active');
			
			$(this).parents('ul').children('li').removeClass('active');
			$(this).parent('li').addClass('active');
			var com_num=loadComment();
			$('#J_Comment_More_Latest').css('visibility','hidden');
			if(com_num==0)
				$('.tab_content .J_Comment_List_Hot').html("<div class='com_tip'>暂时还没有人评论！</div");
			else if(com_num==pNum){$('#J_Comment_More_Latest').css('visibility','visible');
				$('#J_Comment_More_Latest a').html("点击加载更多评论");
				}
			
			if(id=="#all_comment"){
				countData("all",aid);}
			else countData("filter",aid);			
		}
	});
   	countData("filter",aid);	

   	$('#top_btn').fadeOut(1);
	$(window).scroll(function(){
		if($(window).scrollTop()>100){
			$('#top_btn').fadeIn();
		}
		else $('#top_btn').fadeOut();

		});

	$('#top_btn').click(function(){
		 $('body,html').animate({scrollTop:0},500); 

		});


	commentManageAction.getBestList({"aid":aid},{
		async:false,
		callback:function(data){
			var list=data.rows;
			$('#J_TopListWrap .blk_01').empty();
			$.each(list,function(i,obj){    
				var content=obj.content;
				if(content!=null&&content.length>25){content=content.substring(0,25)+"...";}
				var html="<div class='blk_list clearfix'>"
		 		 +"<div class='blk_num'><a href='javascript:void(0)'>"+obj.username+"</a></div>"
		 		 +"<div class='blk_txt'><a href='javascript:void(0)'>"+content+"</a></div>"
		 		 +"<div class='blk_btn' id='blk_btn_"+obj.acid+"'><span onclick='markCommentClick("+obj.acid+",\"jh\",1)'>取消</span></div>"
		 		 +"</div>";
				
		 		$('#J_TopListWrap .blk_01').append(html);

				});
		}
		});
	
   	
   	
});
	function countData(con,topic){
	   	commentManageAction.countList({'con':con,'aid':topic},{
	   		async : false,
			callback : function(data) {

	   		//alert(data[0].com_num);   		
			$('#J_Post_Box_Count span.f_red:eq(0)').empty();
			$('#J_Post_Box_Count span.f_red:eq(0)').append(data[0].com_num);

			$('#J_Post_Box_Count span.f_red:eq(1)').empty();
			$('#J_Post_Box_Count span.f_red:eq(1)').append(data[0].member);
	   	     }

	   	});
	}
		
   function loadComment(){

	var dataCon="";
	var listlen=0,f_listlen=0;
	if($("#filter_comment").hasClass('active'))  dataCon="filter"; 
	commentManageAction.getList({"page":pageN,"con":dataCon,"aid":aid},{
     	async : false,
		callback : function(data) {
			//var list=data.rows;
			var list=data;

				
 			var pre_acid="-1",f_pre_acid="-1";
 			var pre_obj=null;
 			var fl=false;
			var index=0,f_index=0,lou=1,f_lou=1;
			var best="",top="";
			var delhtml="",r_delhtml="",cn="",r_cn="",out_cn="";

			if(pageN==1)
				$(".J_Comment_List_Hot").empty();

			var comItem=$("#filter_comment .J_Comment_List_Hot").children('.comment_item');
			if(comItem!=null) f_index=comItem.length;

			comItem=$("#all_comment .J_Comment_List_Hot").children('.comment_item');
			if(comItem!=null) index=comItem.length;

			
        	$.each(list,function(i,obj){        
    		
        	if(obj.status==2)	
    				best="<span class='com_jh jh_checked'>精华</span><a href='javascript:void(0)' class='jh cancel' onclick='markCommentClick("+obj.acid+",\"jh\",1)'><span>取消</span></a>";
    		else best="<a href='javascript:void(0)' class='comment_ding_link set' onclick='markCommentClick("+obj.acid+",\"jh\",0)'><span class='com_jh unchecked'>设置精华</span></a>";	
			if(obj.stick==1)
				top="<span class='com_top top_checked'>置顶</span><a href='javascript:void(0)' class='top cancel' onclick='markCommentClick("+obj.acid+",\"zd\",1)'><span>取消</span></a>";
    		else top="<a href='javascript:void(0)' class='comment_ding_link set' onclick='markCommentClick("+obj.acid+",\"zd\",0)'><span class='com_top unchecked'>设置置顶</span></a>";	

    		delhtml="<a href='javascript:void(0)' class='comment_reply_link del_a' ";

    		r_delhtml="<button class='comment_reply_link del_a' ";

			if(obj.status==3){
				delhtml+="onclick='deleteClick("+obj.acid+",this)'>恢复</a>";
				cn=" deleted";
			}
			else{
				delhtml+="onclick='deleteClick("+obj.acid+",this)'>删除</a>";	
				cn="";
			}

			/*class delete or not*/
  			if(obj.r_status==3){
  				r_delhtml+="onclick='deleteClick("+obj.r_acid+",this)'>恢复</button>";
			   	r_cn=" deleted";
  	  		}else {
  	  			r_delhtml+="onclick='deleteClick("+obj.r_acid+",this)'>删除</button>";
				r_cn="";
  	  	  	}

  		if(obj.acid!=pre_acid) lou=1;
  		if(obj.acid!=f_pre_acid) f_lou=1;
		var commentHTML="",r_commentHTML="",f_commentHTML="",f_r_commentHTML="";
		if(obj.r_acid!=null){
			f_r_commentHTML=r_commentHTML=""
			+"<div class='orig_cont clearfix re_com"+r_cn+"'>"
			+"<span class='orig_index'>";
			r_commentHTML+=lou;
			f_r_commentHTML+=f_lou;
			var lpart="</span>"
			+"<div class='orig_user'><a href='javascript:void(0)'>"+obj.r_username+"</a></div>"
			+"<div class='orig_content'>"+obj.r_content+"</div>"
			+"<div class='reply'>"
				+"<span class='orig_time J_Comment_Time'>"+obj.r_create_time+"</span>"
				+"<span class='cmnt_report'><a href='javascript:void(0)'>举报</a></span>"
				+"<span class='reply-right'>"
					+"<button class='comment_ding_link'><span>赞<em>("+obj.r_agree_num+")</em></span></button>"
					+"<button class='comment_ding_link'><span>踩<em>("+obj.r_disagree_num+")</em></span></button>"
					+r_delhtml
					+"<button class='comment_reply_link' onclick='reply_comment("+obj.acid+",this)'>回复</button>"
				+"</span>"
			+"</div>"
		   +"</div>"
		   ;
		  r_commentHTML+=lpart;
		  f_r_commentHTML+=lpart;
		  if(obj.r_status==3){f_r_commentHTML="";}
		  else f_lou++;
	
		}
		 
		f_commentHTML=commentHTML="<div class='comment_item_cont clearfix"+cn+"'>"
		    +"<div class='comment_top'><div class='jh_block jh_block_"+obj.acid+"'>"+best+"</div><div class='zd_block zd_block_"+obj.acid+"'>"+top+"</div></div>"					
			+"<div class='J_Comment_Face t_face'><img src='../../images/"+obj.image+"'/></div>"
			+"<div class='t_content'>"
				+"<div class='J_Comment_Info'>"
					+"<div class='t_info'>"
						+"<span class='t_username t_mobile'><a href='javascript:void(0)'>"+obj.username+"</a></span>"								
					+"</div>"
				+"</div>"
				
				+"<div class='comment_content J_Comment_Txt clearfix'>"
				+"<div class='t_txt'>"+obj.content+"</div>"
				  +"<div class='reply'>"
					+"<span class='datetime J_Comment_Time'>"+obj.create_time+"</span>"
					+"<span class='cmnt_report'><a href='javascript:void(0)'>举报</a></span>"
					+"<span class='reply-right'>"
						+"<button class='comment_ding_link'><span>赞<em>("+obj.agree_num+")</em></span></button>"
						+"<button class='comment_ding_link'><span>踩<em>("+obj.disagree_num+")</em></span></button>"
						+delhtml
						+"<button class='comment_reply_link' onclick='reply_comment("+obj.acid+",this)'>回复</button>"
					+"</span>"
				  +"</div>"
			    +"</div>"
			
				+"<div class='J_Comment_Reply'>"
					+"<div class='comment_orig_content'>";
					/*	
						+"<div class='orig_cont clearfix pare_comment' >"										
							+"<span class='orig_index'>1</span>"
							+"<div class='comment_top'><div class='jh_block' id='jh_block_"+obj.acid+"'>"+best+"</div><div class='zd_block' id='zd_block_"+obj.acid+"'>"+top+"</div></div>"
							
							+"<div class='orig_user'><a href='javascript:void(0)'>"+obj.username+"</a></div>"
							+"<div class='orig_content'>"+obj.content+"</div>"
							+"<div class='reply'>"
								+"<span class='orig_time J_Comment_Time'>"+obj.create_time+"</span>"
								+"<span class='cmnt_report'><a href='javascript:void(0)'>举报</a></span>"
								+"<span class='reply-right'>"
									+"<a href='javascript:void(0)' class='comment_ding_link'><span>赞<em>("+obj.agree_num+")</em></span></a>"
									+"<a href='javascript:void(0)' class='comment_ding_link'><span>踩<em>("+obj.disagree_num+")</em></span></a>"
									+delhtml
									+"<a href='javascript:void(0)' class='comment_reply_link'>回复</a>"
								+"</span>"
							+"</div>"
						+"</div>"
						*/
					
				commentHTML+=r_commentHTML+"</div>"+"</div>"+"</div>"+"</div>";			
				f_commentHTML+=f_r_commentHTML+"</div>"+"</div>"+"</div>"+"</div>";	
	
			  
		           	  if(obj.acid==pre_acid){
						$("#comment_item_"+index+" .comment_orig_content").append(r_commentHTML);
						/*$("#comment_item_"+index+" .re_com .orig_index").each(function(){
							  var orig_index=parseInt($(this).text())+1;
							  $(this).empty();
							  $(this).append(orig_index);
							  });
						  */
					   }else{
						    $("#all_comment .J_Comment_List_Hot").append("<div class='comment_item' id='comment_item_"+(++index)+"'></div>"); 
							$("#comment_item_"+index).append(commentHTML);
							  pre_acid=obj.acid;
							  listlen++;
						}
				       lou++;
				       if(obj.status!=3){
				    	   

				    	   if(obj.acid==f_pre_acid){
							   if(obj.r_status!=3){		   
								 	$("#comment_item_filter_"+f_index+" .comment_orig_content").append(f_r_commentHTML);
								  }
							}else{
	                    		$("#filter_comment .J_Comment_List_Hot").append("<div class='comment_item' id='comment_item_filter_"+(++f_index)+"'></div>");
	                    		$("#comment_item_filter_"+f_index).append(f_commentHTML);
	                    		f_pre_acid=obj.acid;
	                    		f_listlen++;
							}

					   }
				       /*
					   if(obj.acid==f_pre_acid){
						   if(obj.r_status!=3){		   
							 	 $("#comment_item_filter_"+f_index+" .comment_orig_content").append(r_commentHTML);
							  }
						}else{
	                    	if(obj.status!=3&&obj.r_status!=3){
	                    		$("#filter_comment .J_Comment_List_Hot").append("<div class='comment_item' id='comment_item_filter_"+(++f_index)+"'></div>");
	                    		$("#comment_item_filter_"+f_index).append(commentHTML);
	                    		f_pre_acid=obj.acid;
	                    		fl=true;
	                          }else if(obj.status!=3){
	                        	  pre_obj=obj;
	                          }

					     }*/
	
	   
  /*			
			if(obj.r_acid==null){
			var commentHTML=noReplayHTML(obj);

			$("#all_comment .J_Comment_List_Hot").append("<div class='comment_item' id='comment_item_"+(++index)+"'></div>");
			$("#comment_item_"+index).append(commentHTML);

			if(obj.status!=3){
				$("#filter_comment .J_Comment_List_Hot").append("<div class='comment_item' id='comment_item_filter_"+(++f_index)+"'></div>");
				$("#comment_item_filter_"+f_index).append(commentHTML);
			  }
			}
			else {
				   out_cn="";
				   if(obj.status==3){
					   r_delhtml+="onclick='deleteClick("+obj.r_acid+",this)'>恢复</button>";
					   r_cn=cn;
				   }else {
					   if(obj.r_status==3){
							r_delhtml+="onclick='deleteClick("+obj.r_acid+",this)'>恢复</button>";
							if(obj.acid==pre_acid)
								r_cn=" deleted";
							else {r_cn=" deleted_opacity";
								  out_cn=" deleted";
							}
					   }	
						else{							
							r_delhtml+="onclick='deleteClick("+obj.r_acid+",this)'>删除</button>";
							r_cn="";
						}
				     }
          
}*/			 			   
			   if(obj.status==3) {$("#comment_item_"+index).find('.reply-right button').attr("disabled",true);
			   					  $("#comment_item_filter_"+f_index).find('.reply-right button').attr("disabled",true);
			   }
			   
            });  	
		}	
     });
    if($('#all_comment').hasClass('active'))
	    return listlen;
    else return f_listlen;
}

   	function markCommentClick(id,label,value){
		commentManageAction.update({"acid":id,"label":label,"value":value},{
				async : false,
				callback : function(data){
			
			if (data.success) {
				
				//$.messager.alert('确认', '成功!');
				if(label=='jh'){
					if(value){
						$(".jh_block_"+id).empty();
						$(".jh_block_"+id).append("<a href='javascript:void(0)' class='comment_ding_link set' onclick='markCommentClick("+id+",\"jh\",0)'><span class='com_jh unchecked'>设置精华</span></a>");

						//热榜
						$("#blk_btn_"+id).html("<span onclick='markCommentClick("+id+",\"jh\",0)'>设置</span>");
						
					  }else{
						$(".jh_block_"+id).empty();
						$(".jh_block_"+id).append("<span class='com_jh jh_checked'>精华</span><a href='javascript:void(0)' class='jh cancel' onclick='markCommentClick("+id+",\"jh\",1)'><span>取消</span></a>");
						$("#blk_btn_"+id).html("<span onclick='markCommentClick("+id+",\"jh\",1)'>取消</span>");
					}
				}else if(label=='zd'){
					if(value){	
						$(".zd_block_"+id).empty();
						$(".zd_block_"+id).append("<a href='javascript:void(0)' class='comment_ding_link set' onclick='markCommentClick("+id+",\"zd\",0)'><span class='com_top unchecked'>设置置顶</span></a>");
					}else{
						$(".zd_block_"+id).empty();
						$(".zd_block_"+id).append("<span class='com_top top_checked'>置顶</span><a href='javascript:void(0)' class='top cancel' onclick='markCommentClick("+id+",\"zd\",1)'><span>取消</span></a>");
						
					}
				}
				//loadComment();
			} else {
				$.messager.alert('警告', '失败!');
			}

		 }
		});

	 	}

	function deleteClick(id,obj){
		var s=3;
		if($(obj).text()=="恢复")
			s=1;
		commentManageAction.deletes({"id":id,"status":s},{
			async:true,
			callback:function(data){
			if(data.success){
				if($(obj).text()=="删除"){
					$(obj).empty();
					$(obj).append("恢复");

					if($(obj).is('a')){
		
						$(obj).empty();
						$(obj).append("恢复");
						$(obj).parents('.comment_item').find('.reply-right button').attr("disabled",true);
												
					} else $(obj).parents('.reply-right').find('button:eq(3)').attr("disabled",true);					
				}else if($(obj).text()=="恢复"){
					$(obj).empty();
					$(obj).append("删除");
					if($(obj).is('a')){
			
						$(obj).empty();
						$(obj).append("删除");
						$(obj).parents('.comment_item').find('.reply-right button').attr("disabled",false);
					}else $(obj).parents('.reply-right').find('button:eq(3)').attr("disabled",false);
					
				}
				if($(obj).is('a')){
					$(obj).parents('.comment_item_cont').toggleClass('deleted');
									
				}else{
					 $(obj).parents('.re_com').toggleClass('deleted');
			}
							
			}else{
				$.messager.alert('警告', '删除失败!');
			}
			
			}
			});

	}

	function sumbitComment(acid,element){

		var parent_id=acid;
		
		var content=$(element).parents('.post_box').find('textarea').val();

		
		if($(element).prop("tagName")=='SPAN')
			$('.cmnt_inline_post_box').remove();

		if(content==""){
			$(element).parents('.post_box').find('.J_Comment_Tip.post_tip').fadeIn(1000);
			$(element).parents('.post_box').find('.J_Comment_Tip.post_tip').fadeOut(2000);
			return false;
		}
		
		$('.J_Comment_List_Hot .com_tip').remove();
		commentManageAction.save({"aid":aid,"parent_id":parent_id,"content":content},{
			async:true,
			callback:function(data){

			if(data!=null){
					$.messager.alert('确认', '评论成功!');
					var obj=data[0];			
					if(parent_id==-1){
					$('textarea').val('');		
					var best="<a href='javascript:void(0)' class='comment_ding_link set' onclick='markCommentClick("+obj.acid+",\"jh\",0)'><span class='com_jh unchecked'>设置精华</span></a>";	
					var top="<a href='javascript:void(0)' class='comment_ding_link set' onclick='markCommentClick("+obj.acid+",\"zd\",0)'><span class='com_top unchecked'>设置置顶</span></a>";	

					var commentHTML="<div class='comment_item_cont clearfix'>"
				    +"<div class='comment_top'><div class='jh_block jh_block_"+obj.acid+"'>"+best+"</div><div class='zd_block zd_block_"+obj.acid+"'>"+top+"</div></div>"					
					+"<div class='J_Comment_Face t_face'><img src='../../images/"+obj.image+"'/></div>"
					+"<div class='t_content'>"
						+"<div class='J_Comment_Info'>"
							+"<div class='t_info'>"
								+"<span class='t_username t_mobile'><a href='javascript:void(0)'>"+obj.username+"</a></span>"								
							+"</div>"
						+"</div>"
						
						+"<div class='comment_content J_Comment_Txt clearfix'>"
						+"<div class='t_txt'>"+content+"</div>"
						  +"<div class='reply'>"
							+"<span class='datetime J_Comment_Time'>"+obj.create_time+"</span>"
							+"<span class='cmnt_report'><a href='javascript:void(0)'>举报</a></span>"
							+"<span class='reply-right'>"
								+"<button class='comment_ding_link'><span>赞<em>(0)</em></span></button>"
								+"<button class='comment_ding_link'><span>踩<em>(0)</em></span></button>"
								+"<a href='javascript:void(0)' class='comment_reply_link del_a' onclick='deleteClick("+obj.acid+",this)'>删除</a>"
								+"<button class='comment_reply_link' onclick='reply_comment("+obj.acid+",this)'>回复</button>"
							+"</span>"
						  +"</div>"
					    +"</div>"
					
						+"<div class='J_Comment_Reply'>"
							+"<div class='comment_orig_content'>"
							+"</div>"
						+"</div>"
						
						+"</div>"
						+"</div>";

					if($('#filter_comment').hasClass('active')){$("#filter_comment .J_Comment_List_Hot").prepend("<div class='comment_item_temp' id='comment_item_filter_0'>"+commentHTML+"</div>");
					}else {$("#all_comment .J_Comment_List_Hot").prepend("<div class='comment_item' id='comment_item_0'>"+commentHTML+"</div>");}
				 }else{

					var r_commentHTML="<div class='orig_cont clearfix re_com'>"
					+"<span class='orig_index'>"
				    +($(element).parents('.comment_item').find('.re_com').length+1)
					+"</span>"
					+"<div class='orig_user'><a href='javascript:void(0)'>"+obj.username+"</a></div>"
					+"<div class='orig_content'>"+content+"</div>"
					+"<div class='reply'>"
						+"<span class='orig_time J_Comment_Time'>"+obj.create_time+"</span>"
						+"<span class='cmnt_report'><a href='javascript:void(0)'>举报</a></span>"
						+"<span class='reply-right'>"
							+"<button class='comment_ding_link'><span>赞<em>(0)</em></span></button>"
							+"<button class='comment_ding_link'><span>踩<em>(0)</em></span></button>"
							+"<button class='comment_reply_link del_a' onclick='deleteClick("+obj.acid+",this)'>删除</button>"
							+"<button class='comment_reply_link' onclick='reply_comment("+obj.acid+",this)'>回复</button>"
						+"</span>"
					+"</div>"
				   +"</div>";

				 $(element).parents('.comment_item').find('.comment_orig_content').append(r_commentHTML);
				 $('.cmnt_inline_post_box').remove();
				}
					
			}else 	$.messager.alert('警告', '删除失败!');

			}
			});
		
		return ;
	}

	function reply_comment(acid,obj){
     
		var flag=false;
		var html="<div class='cmnt_inline_post_box'>"
			+"<div class='box_border_top_cont'>"
			+"<div class='box_border_top'>"
				+"<em>◆</em>"
				+"<span>◆</span>"
			+"</div>"
		+"</div>"	
		+"<div class='post_box post_box_show'>"
			+"<form class='J_Comment_Form' name='post_form_inline'>"
				+"<div class='post_box_cont clearfix'>"
				+"<textarea name='content' placeholder='请输入评论内容' class='J_Comment_Content'></textarea></div>"

				+"<div class='J_Comment_Tip post_tip' style='display: none;'>"
					+"<p class='post_tip_error'>请输入评论内容</p>"
				+"</div>"

			+"</form>"
			+"<div class='cmnt_user_cont clearfix'>"
				+"<a class='J_Comment_Submit post_inline_comment post_inline_comment_disbled' href='javascript:void(0);' onclick='return sumbitComment(\""+acid+"\",this)'></a>"
				/*+"<div class='cmnt_user_other J_WeiboLogined'><a class='ccto_link' href='#url'>@TA</a><label class='share_wt J_Comment_ToWeibo_Wrap' action-type='simcheck' action-data='type=toweibo'><span class='to_mb J_Comment_ToWeibo' toweibo='0'></span>分享到微博</label></div>"*/
		
			+"</div>"					
		+"</div>"
		
		+"<div style='clear:both'></div>"
	+"</div>";
		
		if($(obj).parents('.re_com').length>0){
			if($(obj).parents('.re_com').find('.cmnt_inline_post_box').length==0){flag=true;}
			$('.cmnt_inline_post_box').remove();
			if(flag)
				$(obj).parents('div.re_com').append(html);
						
			
		}else{
			if($(obj).parents('.t_content').children('.cmnt_inline_post_box').length==0){flag=true;}
			$('.cmnt_inline_post_box').remove();
			if(flag)
				$(obj).parents('div.J_Comment_Txt').after(html);

		}

		

	}


	function getMore(){
		pageN++;
		$("#J_Comment_More_Latest a").html("加载中..."); 
		if(loadComment()<pNum){
			 $("#J_Comment_More_Latest").css("visibility","visible");
			 $("#J_Comment_More_Latest a").html("亲，没有更多评论了~~");
		}
		else  $("#J_Comment_More_Latest a").html("点击加载更多评论");
		
	}

	
	
</script>

</head>
<body>
	<div class="top_btn" id="top_btn"  style="visibility: visible;"><a title="返回顶部" href="#J_NewsTitle" class="toplink">TOP</a></div>
	<!-- / 高清图评论js daichang 20121214 -->
	<%--  
	<div class="secondaryHeader">
		<div class="sHBorder">
			<div class="sHLogo">
				<span> <a href="#"> <img
						src="" height="31"
						width="41" alt="图片" />
				</a> <a href="#"> <img
						src="" alt="图片" />
				</a>
				</span>
			</div>
			<div class="sHLinks">
				<a href="#">评论首页</a> &nbsp;|&nbsp; <a href="#">新浪首页</a>

			</div>
		</div>
	</div>
	--%>
	<div class="wrap">
		 <h1 id="J_NewsTitle">外包</h1>
			 <ul class="navtab">
			 				<li class="active"><a href="#filter_comment" aria-controls="filter" role="tab" data-toggle="tab">有效评论</a></li>
							<li><a href="#all_comment" aria-controls="all" role="tab" data-toggle="tab">所有评论</a></li>
							
	        </ul>
		<div class="main clearfix">     
			<div class="ml">
				<!-- 高清图评论 daichang 20121214 -->
				<div id="J_Comment_Wrap" class="blkContainerCommentblk" style=""
					data-sudaclick="cmnt_box_01">
					<div class="Mblk_cmnt blkCommentBox" style="position: relative;">
						<div class="post_box post_box_top post_box_showall"
							id="J_Comment_Form_B" cmnt-type="comment" isfixed="0"
							style="position: static; left: 0px; margin-left: 0px; top: 0px;">
							<p id="J_Post_Box_Count" class="post_box_count">
								<span rel="欢迎发表评论" class="more J_Comment_Count_Txt">已有<span
									class="f_red">0</span>条评论，共<span class="f_red">0</span>人参与.
							</p>
							<div class="post_box_cont clearfix">
								<textarea class="J_Comment_Content" placeholder="请输入评论内容"
									name="content"></textarea>
								<div class="J_Comment_Tip post_tip" style="display: none;">
									<p class="post_tip_error">请输入评论内容</p>
								</div>
							</div>
							<div class="cmnt_user_cont clearfix">
								<div class="cmnt-emotion-btns J_Logined">
									<a class="cmnt-emotion-trigger" href="javascript:;"
										action-type="face-toggle" action-data="id=J_Comment_Form_B"></a>
								</div>
								<span class='J_Comment_Submit post_inline_comment post_inline_comment_disbled' onclick="return sumbitComment('-1',this)">发表</span>
							</div>

						</div>
					</div>
					<div class="Mblk_cmnt">
						<div class="b_cont2" id="J_Comment_List_Wrap">
							<div id="J_Comment_Wrap_Latest">
								<div class="c_title">
									<span class="cmenu">最新评论</span> <a href="javascript:;"
										onclick="return false;" class="cmnt-reflash-btn"
										action-type="reload" action-data="type=Latest">刷新</a>
								</div>
								<div class="b_txt" id="J_Comment_List_Latest">
					<div class="comment_item_page_first">
									
									
					  <div class="tab_content">				
<!-- 评论内容 -->						
									<div class="tab-pane active" id="filter_comment">
										<div class="b_txt J_Comment_List_Hot"></div>
									</div>
									<div class="tab-pane" id="all_comment">
										<div class="b_txt J_Comment_List_Hot"></div>
									</div>							
					</div>

			        </div><!--end comment_item_page_first-->		
			    </div><!-- end  comment_item_page_first-->
								
				<div id="J_Comment_More_Latest" class="comment_item comment_more">			
					<a href="javascript:void(0)" onclick="getMore()">点击加载更多评论</a>		
				</div>
							
			</div><!-- end J_Comment_Wrap_Latest -->
			</div>				
		</div><!--end Mblk_cmnt -->
		</div>

			</div><!-- end ml -->
	
			<div class="mr">
			 <div id="J_TopListWrap">
			 	<div class="tit tit_01"> <h2>热评排行</h2> </div>
			 	
			 	<div class="blk_01">
			 		<div class="blk_list clearfix">	 		 
			 		</div>
			 	</div>
			 	
			 </div>
		
			</div><!-- end mr -->
				
		</div>
	<%-- 
		<div class="footer" id="J_Footer">
			<p>
				<a target="_blank"
					href="#">意见反馈</a>
				电话：400-690-0000 欢迎批评指正
			</p>
			<p>
				<a href="#" target="_blank" id="testfun">简介</a>┊<a
					href="#" target="_blank">About Sina</a>┊<a
					href="#" target="_blank">广告服务</a>┊<a
					href="#" target="_blank">联系我们</a>┊<a
					href="#" target="_blank">招聘信息</a>┊<a
					href="#" target="_blank">网站律师</a>┊<a
					href="#">SINA English</a>┊<a
					href="#" target="_blank">通行证注册</a>┊<a
					href="#" target="_blank">产品答疑</a>
			</p>
			<p>
				Copyright <span style="font-family: arial;">&copy; </span>1996-2014
				SINA Corporation, All Rights Reserved
			</p>
			<p>
				新浪公司 <a href="#"
					target="_blank">版权所有</a>
			</p>
		</div>
	--%>
	</div>	
</body>
</html>