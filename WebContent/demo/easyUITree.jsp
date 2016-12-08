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
<script language="javascript" type="text/javascript" src='/SafeLease/dwr/interface/keyValueAction.js'></script>
<script>
	var trees;
	$(function() {
		keyValueAction.getList({tableId:"T_BASE_DEPARTMENT",key:"ORG_ID",value:"ORG_NAME",length:2},function(data){
		//keyValueAction.getList({tableId:"T_BASE_DEPARTMENT",key:"ORG_ID",value:"ORG_NAME",pid:"PARENT_ORG_ID"},function(data){
		
		trees = $("#tree").tree({
			//data : initData,
			data : data,
			animate : true
		});
		
		//默认选中根节点
		var root = trees.tree("getRoot");
		if(root){
			trees.tree("select",root.target);
		}
		
		var selectItem=function(e){
			e=e||window.event;
			//38:↑ 40:↓ 37:← 39:→
			if(e.keyCode==38||e.keyCode==40||e.keyCode==37||e.keyCode==39){	
				switch(e.keyCode){
					case 38:move("prev");break;
					case 40:move("next");break;
					case 37:action("collapse");break;
					case 39:action("expand");break;
					default:break;
				}			
				if (e&&e.preventDefault){ 
					e.preventDefault();
				    e.stopPropagation();
				}
				else{ 
				     e.returnvalue=false;  
				     return false;     
				} 
			}			
		};
		document.onkeydown=selectItem;
		});
	});

	//↑
	function prevNode(selected) {		
		//选择与自己同级的上个对象
		var obj = $(selected.target).parent().prev();
		var nodeId = obj.find(".tree-node").attr("node-id");
		var node = trees.tree("find", nodeId);
		var selectedNode = null;
		//对象存在
		if (node) {
			selectedNode = node.target;
			//是否展开且该对象是否有子元素，有的话选中最后一个
			if (node.state == "open") {
				var childrenNodes = trees.tree("getChildren", node.target);
				if (childrenNodes && childrenNodes.length > 0) {
					
					var closedChildNode = null;					
					for(var i in childrenNodes){
						if(childrenNodes[i].state == "closed"){
							closedChildNode = childrenNodes[i];
							break;
						}
					}
					if(closedChildNode){
						selectedNode = closedChildNode.target;
					}
					else{
						selectedNode = childrenNodes[childrenNodes.length - 1].target;
					}
				}
			}
		} else {
			//如果不存在，转向父元素
			var parentNode = trees.tree("getParent", selected.target);
			//trees.tree("select", parentNode.target);
			if(parentNode){
				selectedNode = parentNode.target;
			}
		}
		
		if(selectedNode){
			trees.tree("select", selectedNode);
			var $container = $("#container");
			var offset = $(selectedNode).offset();
			if(offset.top < ($(selectedNode).height() + 10)){
				//设置滑块位置
				$container.scrollTop($container.scrollTop() - $(selectedNode).height());
			}
		}
	}
	
	//递归获取父级下一个的对象
	function getPrev(obj){
		var parentNode = trees.tree("getParent", obj.target);
		if(!parentNode){
			return null;
		}
		var nextParentNode = trees.tree("find", $(parentNode.target).parent().next().find(".tree-node").attr("node-id"));
		if(nextParentNode){
			return nextParentNode
		}
		return getParentNext(parentNode);
	}

	//↓
	function nextNode(selected) {
		var selectedNode = null;
		//判断当前选中项有没有打开
		if (selected.state == "open") {
			//选中子项
			var childrenNodes = trees.tree("getChildren", selected.target);
			if (childrenNodes.length > 0) {
				selectedNode = childrenNodes[0].target;
			}
		} else {
			//选择与自己同级的下个对象
			var obj = $(selected.target).parent().next();
			var nodeId = obj.find(".tree-node").attr("node-id");
			var node = trees.tree("find", nodeId);
			//对象存在，选中
			if (node) {
				selectedNode = node.target;
			} else {
				//不存在时，询问父节点的下个对象
				var nextParentNode = getParentNext(selected);
				if(nextParentNode){
					selectedNode = nextParentNode.target;					
				}
			}
		}
		
		if(selectedNode){
			trees.tree("select", selectedNode);
			var $container = $("#container");
			var offset = $(selectedNode).offset();
			if(offset.top > ($container.height() - 10 )){
				//设置滑块位置
				$container.scrollTop($container.scrollTop() + $(selectedNode).height());
			}
		}
	}

	//递归获取父级下一个的对象
	function getParentNext(obj){
		var parentNode = trees.tree("getParent", obj.target);
		if(!parentNode){
			return null;
		}
		var nextParentNode = trees.tree("find", $(parentNode.target).parent().next().find(".tree-node").attr("node-id"));
		if(nextParentNode){
			return nextParentNode
		}
		return getParentNext(parentNode);
	}
	
	//移动
	function move(str) {
		var selected = trees.tree("getSelected");
		if (selected) {			
			switch(str){
				case "prev":prevNode(selected);break;
				case "next":nextNode(selected);break;
				default:break;
			}
		}
	}
	
	//展开或折叠
	function action(str){
		if(trees.tree("getSelected")){
			trees.tree(str,trees.tree("getSelected").target);
		}
	}
</script>
</head>
<body>
	<div id="container" style="width:350px;height:500px;overflow:auto" >
	 <ul id="tree"></ul>
	</div>
</body>
</html>