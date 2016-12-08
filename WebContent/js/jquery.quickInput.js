function accountingSubject(array,param){
	generalMethodAction.getAccountingSubject(param,function(data){//totalAccountManageAction.getAccountList
		for(var i =0;i<array.length;i++){
			accountList(array[i]['obj'],array[i]['objText'],data);
		}
		
	});		
}	

function accountList(obj,objText,data){
	//科目
	obj.autocomplete(
		{
			source :data,
			minLength :1,
			open : function() {
				$(this).removeClass("ui-corner-all").addClass(
						"ui-corner-top");
			},
			close : function() {
				$(this).removeClass("ui-corner-top").addClass(
						"ui-corner-all");
				var sid=obj.val();
				if(sid==null || sid==""){
					objText.html("&nbsp;");
				}
			},
			select: function( event, ui ){
				objText.html(ui.item.value.replace(/&nbsp;/ig,''));
				obj.val(ui.item.label);
				return false;
			},
			change: function(){
				var asId=obj.val();
				if(asId != null && asId != ""){
					var existFlag = false;
					for(var i=0;i<data.length;i++){
						if(asId == data[i]['label']){
							existFlag=true;
							objText.html("&nbsp;"+data[i]['value'].replace(/&nbsp;/ig,''));
							break;
						}
					}
					if(!existFlag){
						objText.html("&nbsp;");
						$.messager.alert('提示信息 ','请选择正确的科目代码！','info');
						obj.val("");
					}
				}
				else{
					objText.html("&nbsp;");
				}
			},
			focus: function( event, ui){
				objText.html("&nbsp;"+ui.item.value.replace(/&nbsp;/ig,''));
				obj.val(ui.item.label);
				return false;
			}
		}).data( "autocomplete" )._renderItem = function( ul, item ) {
			return $( "<li ></li>" )
				.data( "item.autocomplete", item )
				.append( "<a>" + item.label  + item.value + "</a>" )
				.appendTo( ul );
		};
	
}