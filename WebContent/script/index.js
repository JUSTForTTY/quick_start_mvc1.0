$(function(){

	/*递推解析xml*/
	function toMenu(menuObj,$ul){
		var menus =  $(menuObj).find(">menu");
		menus.each(function(){
			var $this = $(this);
			var mc  = $this.attr("mc");
			var src = $this.attr("src");
			//新增一个菜单
			var $li = $("<li></li>").append($("<span></span>").text(mc).attr("src",src).addClass("file"));
			if(src!=null && src != ""){
				$li.find(">span").css("cursor","pointer").click(function(){
					window.open($(this).attr("src"),'showframe');
				});
			}
			$ul.append($li);
			
			var childrenMenu = $this.find(">menu");
			if(childrenMenu.length > 0){
				//新增一个子菜单  
				$li.find("span").removeClass("file").addClass("folder");
				var _$ul = $("<ul></ul>").appendTo($li);
				toMenu($this,_$ul) ;
			}
		});
		
		return $ul ;
	}
	
	/*$(function() {
		$.ajax({ 
		url: "menu.xml", 
		success: function(xml){
			toMenu(xml,$("#browser"));
			$("#browser").treeview();
		}
	});*/
	
});