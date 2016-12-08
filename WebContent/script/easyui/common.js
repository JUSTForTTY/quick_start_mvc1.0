/**
 * 右击显示或隐藏列
 * @param {} tableId  对应存放 datagrid的table的id
 */
function easyui_datagrid_rightclick_Menu(tableId){
	if(tableId == null || tableId == "")return;
	tableId = "#"+tableId;
	var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
	var fields = $(tableId).datagrid('getColumnFields');	
	//alert(fields[0])
	for(var i=0; i<fields.length; i++){
		$('<div iconCls="icon-ok" />').html($(tableId).datagrid('getColumnOption',fields[i]).title).appendTo(tmenu).attr('field_id',fields[i]);//
	}
	tmenu.menu({
		onClick: function(item){
			if (item.iconCls=='icon-ok'){
				//for(var i in item)alert(i+":"+item[i])
				$(tableId).datagrid('hideColumn', $(item.target).attr('field_id'));//item.text
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$(tableId).datagrid('showColumn', $(item.target).attr('field_id'));
				tmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
}

/**
 * 弹出窗口
 * @param {} title
 * @param {} width
 * @param {} height
 * @param {} url
 * @param {} callbackFunc
 */
function easyui_openTopWindow(title,width,height,url,callbackFunc){
	var top = "", left = "";
	var maxHeight = window.top.document.body.clientHeight;//parseInt(window.top.screen.availHeight) - parseInt(window.top.screenTop);
	var maxWidth = window.top.document.body.clientWidth;//parseInt(window.top.screen.availWidth) - parseInt(window.top.screenLeft);
	height+=20;
	width+=20;
	if(height > maxHeight) {top = "top:5px;";height = maxHeight - 20;}
	if(width > maxWidth) {left = "left:5px;";width = maxWidth - 20;}
	var date = new Date().pattern("yyyy-MM-dd-HH:mm:ss");
	var win=window.top.document.createElement("div");
	win.setAttribute("name","openTopWindow"+date);
	win.setAttribute("style","padding:5px;"+top+left);
	//var win = window.top.document.createElement("<div name='openTopWindow"+date+"' style='padding:0px;"+top+left+"'></div>");
	window.top.document.body.appendChild(win);
	(function(title,width,height,url,callbackFunc){
		return function(){
			window.top.$(win).window({
				title: title+" ",
				width: width,
				modal: true,
				shadow: false,
				closed: true,
				height: height,
				draggable:true,
				zIndex:90000,
				//inline:true,
				collapsible:true,
				minimizable:false,
				maximizable:true,
				closable:true,
				content:"<iframe scrolling='auto' frameborder='0' src="+url+" style='width:100%;height:99%;'></iframe>",
				onClose:function(){					
					if(callbackFunc != null) {
						var returnV = window.top.$("[name^='openTopWindow']:last").data("returnValue");
						if(returnV == null)returnV = "";
						callbackFunc(returnV);
					}
						
					window.top.$("[name^='openTopWindow']:last").window('destroy',false);
				}
		    })
		}
	}(title,width,height,url,callbackFunc))();
	window.top.$(win).window('open');
}
function easyui_openWindow(title,width,height,url,callbackFunc){
	var left = "";
	var maxHeight = window.top.document.body.clientHeight;//parseInt(window.top.screen.availHeight) - parseInt(window.top.screenTop);
	var maxWidth = window.top.document.body.clientWidth;//parseInt(window.top.screen.availWidth) - parseInt(window.top.screenLeft);
	height+=20;
	width+=20;
	if(height > maxHeight) {height = maxHeight - 20;}
	if(width > maxWidth) {left = "left:5px;";width = maxWidth - 20;}
	var date = new Date().pattern("yyyy-MM-dd-HH:mm:ss");
	var win=window.document.createElement("div");
	win.setAttribute("name","openWindow"+date);
	win.setAttribute("style","padding:5px;"+left);
	//var win = window.top.document.createElement("<div name='openTopWindow"+date+"' style='padding:0px;"+top+left+"'></div>");
	window.parent.document.body.appendChild(win);
	(function(title,width,height,url,callbackFunc){
		return function(){
			window.$(win).window({
				title: title+" ",
				width: width,
				modal: true,
				shadow: false,
				closed: true,
				height: height,
				draggable:true,
				zIndex:90000,
				//inline:true,
				collapsible:true,
				minimizable:false,
				maximizable:true,
				closable:true,
				content:"<iframe scrolling='auto' frameborder='0' src="+url+" style='width:100%;height:99%;'></iframe>",
				onClose:function(){					
					if(callbackFunc != null) {
						var returnV = window.$("[name^='openWindow']:last").data("returnValue");
						if(returnV == null)returnV = "";
						callbackFunc(returnV);
					}
						
					window.$("[name^='openWindow']:last").window('destroy',false);
				}
		    })
		}
	}(title,width,height,url,callbackFunc))();
	window.$(win).window('open');
}

function easyui_closeWindow(returnValue){
	if(window != parent){
		if(returnValue == null)returnValue="";
		parent.$("[name^='openWindow']:last").data("returnValue",returnValue).window('close');
	}
}
function easyui_closeTopWindow(returnValue){
	if(window != top){
		if(returnValue == null)returnValue="";
		window.top.$("[name^='openTopWindow']:last").data("returnValue",returnValue).window('close');
	}
}

/***
 * 打开窗口，去除窗口按键的
 * @param title
 * @param width
 * @param height
 * @param url
 * @param callbackFunc
 */
function easyui_openTopWindowLogin(title,width,height,url,callbackFunc){
	var top = "", left = "";
	$(window.top.document.body).css({overflow:"hidden"});
	window.top.onmousewheel=function(e){
		return true;
	}
	var maxHeight = window.top.document.documentElement.clientHeight;//parseInt(window.top.screen.availHeight) - parseInt(window.top.screenTop);
	var maxWidth = window.top.document.body.clientWidth;//parseInt(window.top.screen.availWidth) - parseInt(window.top.screenLeft);
	if(height > maxHeight) {top = "top:1px;";height = maxHeight - 2;}
	if(width > maxWidth) {left = "left:1px;";width = maxWidth - 2;}
	var date = new Date().pattern("yyyy-MM-dd-HH:mm:ss");
	var win=window.top.document.createElement("div");
	win.setAttribute("name","openTopWindow"+date);
	win.setAttribute("style","padding:0px;"+top+left);
	window.top.document.body.appendChild(win);
	(function(title,width,height,url,callbackFunc){
		return function(){
			window.top.$(win).window({
				title: title+" ",
				width: width,
				modal: true,
				shadow: false,
				closed: true,
				border:false,noheader:true,
				height: height,
				draggable:true,
				zIndex:90000,
				//inline:true,
				collapsible:true,
				minimizable:false,
				maximizable:true,
				closable:true,
				content:"<iframe scrolling='auto' frameborder='0' src="+url+" style='width:100%;height:100%;'></iframe>",
				onClose:function(){					
					if(callbackFunc != null) {
						var returnV = window.top.$("[name^='openTopWindow']:last").data("returnValue");
						if(returnV == null)returnV = "";
						window.top.$("[name^='openTopWindow']:last").window('destroy',false);
						callbackFunc(returnV);
					}else{
						window.top.$("[name^='openTopWindow']:last").window('destroy',false);
					}
				}
		    })
		}
	}(title,width,height,url,callbackFunc))();
	window.top.$(win).window('open');
}
/**
 * 把日期格式化成 yyyy-MM-dd HH(hh):mm:ss
 * var date = new Date(); window.alert(date.pattern("yyyy-MM-dd hh:mm:ss"));
 * @param {} fmt
 * @return {}
 */
Date.prototype.pattern=function(fmt) {
    var o = {     
    "M+" : this.getMonth()+1, //月份     
    "d+" : this.getDate(), //日     
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时     
    "H+" : this.getHours(), //小时     
    "m+" : this.getMinutes(), //分     
    "s+" : this.getSeconds(), //秒     
    "q+" : Math.floor((this.getMonth()+3)/3), //季度     
    "S" : this.getMilliseconds() //毫秒     
    };
    var week = {     
    "0" : "\u65e5",     
    "1" : "\u4e00",     
    "2" : "\u4e8c",     
    "3" : "\u4e09",     
    "4" : "\u56db",     
    "5" : "\u4e94",     
    "6" : "\u516d"    
    };     
    if(/(y+)/.test(fmt)){     
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));     
    }     
    if(/(E+)/.test(fmt)){     
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);     
    }     
    for(var k in o){     
        if(new RegExp("("+ k +")").test(fmt)){     
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));     
        }     
    }     
    return fmt;     
}

/**
 * 打开等待进度条
 * @param {} func 要执行的方法
 */
function easyui_openWaitProgress(func){//打开等待进度条
	$.messager.progress('close');
	$.messager.progress({
		title:'请等待',
		msg:'程序处理中...'
	});
	setTimeout(function(){
		try{
			func();
		}catch(e){
			
		}
		//$.messager.progress('close');//关闭进度条
	},500);
}

/**
 * 获取form中的数据，根据name
 * @param {} formId
 */
function easyui_getFormValue(formId){
	var map = {};
	$("[name]",$("#"+formId)).each(function(_i){
		var tmpName = $(this).attr('name');		
		var type = $(this).attr('type');
		if(type != null){
			switch(type){
				case 'radio':
					$.each($(this),function(){
						if($(this).attr("checked")){
							map[tmpName] = $(this).val();
						}
					});
				break;
				default: map[tmpName] = $(this).val();
			}
		}else{
			map[tmpName] = $(this).val();
		}
	});
	return map;
}
/**
 * 设置form中的数据，根据name
 * @param {} formId
 * @param {} data
 */
function easyui_setFormValue(formId,data){
	$("[name]",$("#"+formId)).each(function(_i){
		var tmpName = $(this).attr('name');
		if(data[tmpName] != null){
			var type = $(this).attr('type');
			//if(tmpName == "ACCOUNTING_SUBJECT_ID")alert($(this).parent().html());
			
			if($(this).attr("class") == "combo-value"){//可能为select             日期控件还没有遇到
				$("#"+tmpName).combobox('select',data[tmpName]);
				//alert($(this).parent().html());
				/*var tmpId = $(this).attr('name').replace(".","_");
				if(jQuery.inArray(tmpId,datetimeboxArr) != -1){//确定是日期控件
					$("#"+tmpId).datetimebox('setValue',eval("row."+tmpName));
				}*/
			}else if($(this).attr("type") == "checkbox"){//checkbox
				if(data[tmpName] == $(this).val()){
					$(this).attr("checked",true);
				} else{
					$(this).attr("checked",false);
				}
			}else if(type == "radio"){//radio
				var radios = $(this);
				$.each(radios,function(){
					if($(this).val() == data[tmpName]){
						$(this).attr("checked",true);
					} else{
						$(this).attr("checked",false);
					}
				});											
			}else if(type == "hidden"){//目前遇到easyui-numberbox
				var len = $(this).parent().find("[numberboxname='"+tmpName+"']").length;
				if(len == 1){
					$("#"+tmpName).numberbox('setValue',data[tmpName]);
				}else{
					$(this).val(data[tmpName]);
				}
			}else{
				$(this).val(data[tmpName]);
			}
		}
	});
}
/**
 * 请用encodeURIComponent对中文数据进行编码
 * @return {}
 */
function easyui_getRequestPara(){
	var url = location.search;
	if(url == null) return null;
	var theRequest = new Object();
	if(url.indexOf("?") != -1){ 
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++){ 
			theRequest[strs[i].split("=")[0]]=decodeURIComponent(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
function easyui_setDisabled(ids){
	var components = "input,textarea,a,select,span";
	$(ids).find(components).attr('disabled',true);//输入的值不能修改
}
/**
 * format数据带，
 * @param {} s 要格式化的数据
 * @param {} n 小数位数
 * @return {}
 */
function easyui_fmoney(value, n) {   
	if(value==null) return '0.00';
	else if(value=="heji") return'';
	value = value+"";
	if(/^[-]{0,1}(\d+)[\.]{1}(\d+)$/.test(value)||/^[-]{0,1}(\d+)$/.test(value)){
		var valueHead = value.substring(0,1);
		var valueBody = "";
		if(valueHead=="-") valueBody = value.substring(1,value.length);
		else valueBody = value;
		valueBody=(valueBody+"").replace(/^(\d*)$/,"$1.");
		valueBody=(valueBody+"00").replace(/(\d*\.\d\d)\d*/,"$1");
		valueBody=valueBody.replace(".",",");
		var re=/(\d)(\d{3},)/;
		while(re.test(valueBody)) valueBody=valueBody.replace(re,"$1,$2");
		valueBody=valueBody.replace(/,(\d\d)$/,".$1");
		valueBody = valueBody.replace(/^\./,"0.");
		if(valueHead=="-") valueBody = valueHead + valueBody;
		return valueBody;
	}else{
		return "0.00";
		//return "invalid value";
	}
}
/**
 * 空返回''
 */
function easyui_fmoney_notNull(value, n) {   
	if(value==null||value==""||value==0) return '';
	value = value+"";
	if(/^[-]{0,1}(\d+)[\.]{1}(\d+)$/.test(value)||/^[-]{0,1}(\d+)$/.test(value)){
		var valueHead = value.substring(0,1);
		var valueBody = "";
		if(valueHead=="-") valueBody = value.substring(1,value.length);
		else valueBody = value;
		valueBody=(valueBody+"").replace(/^(\d*)$/,"$1.");
		valueBody=(valueBody+"00").replace(/(\d*\.\d\d)\d*/,"$1");
		valueBody=valueBody.replace(".",",");
		var re=/(\d)(\d{3},)/;
		while(re.test(valueBody)) valueBody=valueBody.replace(re,"$1,$2");
		valueBody=valueBody.replace(/,(\d\d)$/,".$1");
		valueBody = valueBody.replace(/^\./,"0.");
		if(valueHead=="-") valueBody = valueHead + valueBody;
		return valueBody;
	}else{
		return "0.00";
		//return "invalid value";
	}
} 
/**
 * 空返回''
 */
function easyui_isNull(value, n) {   
	if(value==null||value==""||value==0) return '';
	return value;
} 
function easyui_rmoney(s){   
   return parseFloat(s.replace(/[^\d\.-]/g, ""));   
}
/**
 * 日期控件 格式化
 * @param {} date
 * @return {}
 */
function easyui_datebox_formatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function easyui_datebox_first(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+("01");
}

function easyui_datebox_(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
/**
 * 日期控件 格式化
 * @param {} s
 * @return {}
 */
function easyui_datebox_parser(s){
	if (!s) return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
/**
 * 日期控件 取 年月日
 * @param {} s
 * @return {}
 */
function easyui_datebox_substr(s){
	if (!s) return "";
	return s.substring(0,10);
}

/**
 * 去除小数点之后的0
 * @param {} s
 * @return {}
 */
function precisionFormatter(value){
	if(value==""){
		return '';
	}else{
		var _48f= window.parseFloat(value);
			_48f=_48f+"";
			var opts=$(this).numberbox("options");
			var s1=_48f,s2="";
			var dpos=_48f.indexOf(".");
			if(dpos>=0){
				s1=_48f.substring(0,dpos);
				s2=_48f.substring(dpos+1,_48f.length);
			}
			var p=/(\d+)(\d{3})/;
			while(p.test(s1)){
				s1=s1.replace(p,"$1"+","+"$2");
			}
			if(s2)  s1=s1+'.'+s2;
			return s1;
	}
}

/**
 * 选择框 格式化
 */
function easyui_checkbox_parser(value){
	if(value=="0"){
		return "否";
	}else{
		return "是";
	}
}	
/***
 * 将Map 转成对应的实体
 */	
	
function mapToBean(map){
	var newMap={};
	var str=[];
	var key="";
	for(var p in map){
		str=p.split('_'); 
		key=str[0].toLocaleLowerCase();
		for(var i=1;i<str.length;i++){
			key=key+str[i].substring(0,1)+(str[i].substring(1,str[i].length)).toLocaleLowerCase();
		}
		newMap[key]=map[p];
	}
	return newMap;
	
}	
