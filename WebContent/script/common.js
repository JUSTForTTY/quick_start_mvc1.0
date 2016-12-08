$(function(){
	
	//将数字转换成有千位分隔符和保留两位小数的金额形式
	$.extend({
		parseToMoney:function(value){
			if(value==null) return "";
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
				return "invalid value";
			}
		}
	});
	/*//对于class为date的input添加日期显示控件
	$(".date").click(WdatePicker);
	
	//隐藏block插件的div
	$(document).click(function(){
		$.unblockUI();
	});*/
	/**
	 * 全角转半角
	 */
	$.fn.fullToHalf=function (){
		this.bind("blur", function() {
			var value="";
			var txt = this.value.split("");
			$.each(txt,function(i,char){
				var toAscii = char.charCodeAt();
				if(toAscii > 65280 && toAscii < 65375) //数字，字母和符号的全角
				{ 
					value = value + String.fromCharCode(toAscii-65248);
				} else if(toAscii == 12288){//空格的全角
					value = value + String.fromCharCode(toAscii-12256);
				}else{//非全角
					value = value + char;
				}
			});
			this.value = value;
	    });
	} ;
	/**
	 * value需格式化数字,dataType数据类型(1普通数据，2金额)
	 */
	$.extend({
		formatNumber:function(value,dataType,currency){
			var currencyType = currency ? currency: "￥";
			if((dataType != 1 && dataType != 2)){
				return value;
			}
			
			var txt;
			if(dataType==1){
				if(value == "" || value == null){
					return "";
				}
				txt = (value + "").split(".");
				while(/\d{4}(,|$)/.test(txt[0]))
					txt[0] = txt[0].replace(/(\d)(\d{3}(,|$))/,"$1,$2");
//				if(txt.length == 1){
//	        		value = txt[0];
//	        	}else{
//		            value = txt[0]+ "."+txt[1];
//	        	}
				
				if(txt.length == 1){
					
	        		value = txt[0]+ parseFloat(0.0).toFixed(2).substr(1);
	        	}else{
		            value = txt[0]+ parseFloat("0."+txt[1]).toFixed(2).substr(1);
	        	}
			}else if(dataType==2){
				if(value == 0){
					return currencyType+"0.00";
				}
				if(value == "" || value == null){
					return "";
				}
				txt = (value + "").split(".");
				while(/\d{4}(,|$)/.test(txt[0]))
					txt[0] = txt[0].replace(/(\d)(\d{3}(,|$))/,"$1,$2");
				if(txt.length == 1){
					
	        		value = currencyType +txt[0]+ parseFloat(0.0).toFixed(2).substr(1);
	        	}else{
		            value = currencyType+txt[0]+ parseFloat("0."+txt[1]).toFixed(2).substr(1);
	        	}
			}
			return value;
		}
	});
	
	/********
	 * 限定大小的整数型，>=min，<=max
	 */
	$.fn.integerLimit = function(min,max){
		if(min > max)return;
		//$(this).floatInput(max.length,0,1);
		$(this).integerOnly();
		this.bind("keyup", function() {
	    	var stmp = "";
			if($(this).val()==stmp) return;
			if($(this).val() < min || $(this).val() > max){
				$(this).val("");
			}
	    });
		this.bind("keydown", function() {
	    	var stmp = "";
			if($(this).val()==stmp) return;
			if($(this).val() < min || $(this).val() > max){
				$(this).val("");
			}
	    });
		this.bind("blur", function(){
	    	var stmp = "";
			if($(this).val()==stmp) return;
			if($(this).val() < min || $(this).val() > max){
				$(this).val("");
			}
	    });
		this.bind("paste", function() {
	        if (window.clipboardData) {//火狐、IE禁用了，有待解决
	            var s = clipboardData.getData('text');
	            if (!isNaN(s)) {
	            	if(s.indexOf(".")!=-1){
	            		var r = s.substring(0,s.indexOf("."));
	            		if(r < min || r > max){
	            			$(this).val("");
	            		}
	            	}else{
	            		if(s < min || s > max){
	            			$(this).val("");
	            		}
	            	}
	            }
	        }
	    });
	};
	
	/**
	 * fieldLen数据长度，小数位数tailLen，数据类型dataType（1为正整数，2为正小数，5为金额）
	 */
	$.fn.floatInput = function(fieldLen,tailLen,dataType) {
		if(dataType != 1 && dataType != 2 && dataType != 5){
			return false;
		}
	    $(this).css("text-align","left").css("ime-mode", "disabled");
	    this.bind("keypress", function(e) {
	        if (e.charCode === 0) return true;  //非字符键 for firefox
	        var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE
	        if (code >= 48 && code <= 57) {
	            var pos = getCurPosition(this);
	            var selText = getSelectedText(this);
	            
	            var dotPos = this.value.indexOf(".");
	            if (dotPos >= 0 && pos > dotPos) {
            		//$(this).attr("maxlength",fieldLen+1);
	                if (pos > dotPos + tailLen) return false;
	                if (selText.length > 0 || this.value.substr(dotPos + 1).length < tailLen)
	                    return true;
	                else
	                    return false;
	            }else if(dotPos < 0){
	            	if(this.value.replace(/,/g,"").length >= fieldLen-tailLen){
		            	return false;
		            }
	            }else if(dotPos >= 0 && pos <= dotPos){
	            	if (this.value.substr(0,dotPos).replace(/,/g,"").length >= fieldLen-tailLen){
	            		return false;
	            	}
	            }
	            return true;
	        }
	        //输入"."
	        if (code == 46) {
	        	if(dataType == "1"){
	        		return false;
	        	}
	            var selText = getSelectedText(this);
	            if (selText.indexOf(".") > 0) return true; //选中文本包含"."
	            //else if (/^[0-9]+\.$/.test(this.value + String.fromCharCode(code)))
	            return true;
	        }
	        return false;
	    });
	    this.bind("blur", function() {
	    	if(dataType == "1"){
	    		return false;
	    	}
	        if (this.value.lastIndexOf(".") == (this.value.length - 1)) {
	            this.value = this.value.substr(0, this.value.length - 1);
	        }
	        if (dataType == "5" && this.value){
	        	this.value = this.value.replace(/￥/g,"");
	        	if(this.value.indexOf(".") < 0){
	        		this.value = "￥" + this.value + parseFloat(0.0).toFixed(tailLen).substr(1);
	        	}else{
		            this.value = "￥" + this.value.substr(0,this.value.indexOf(".")) + parseFloat("0"+this.value.substr(this.value.indexOf("."))).toFixed(tailLen).substr(1);
	        	}
	        }
	        //$(this).trigger("input");
	    });
	    this.bind("paste", function() {
	        if (window.clipboardData) {
	            var s = clipboardData.getData('text');
	            if (!isNaN(s)) {
	                value = parseFloat(s);
	                return true;
	            }
	        }
	        return false;
	    });
	    this.bind("dragenter", function() {
	        return false;
	    });
	    this.bind("keyup", function() {
	    	var stmp = "";
			if($(this).val()==stmp) return;
			var len = tailLen == 0 ? 1 : tailLen;
			eval("var re = /(\.\d{" + len + "}).+$/;");  
			var ms = $(this).val().replace(/[^\d\.]/g,"").replace(re,"$1").replace(/^0+([1-9])/,"$1").replace(/^0+$/,"0");
			var txt = ms.split(".");
			while(/\d{4}(,|$)/.test(txt[0]))
				txt[0] = txt[0].replace(/(\d)(\d{3}(,|$))/,"$1,$2");
			$(this).val(stmp = txt[0]+(txt.length>1?"."+txt[1]:""));
	    });
	    /*this.bind("propertychange", function(e) {
	        if (isNaN(this.value))
	            this.value = this.value.replace(/[^0-9\.]/g, "");
	    });*/
	    /*this.bind("input", function(e) {
	        if (isNaN(this.value))
	            this.value = this.value.replace(/[^0-9\.]/g, "");
	    });*/
	    this.bind("focus",function(){
	    	var value=$(this).val();
			var leng=value.length;
		
			if(leng>0){
				$(this).val(value.replace(/￥/g,"")); 
			}
	    });
	};
	$.fn.moneyInput = function(fieldLen) {
	    $(this).css("text-align","right").css("ime-mode", "disabled");
	    this.bind("keypress", function(e) {
	        if (e.charCode === 0) return true;  //非字符键 for firefox
	        var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE
	        if (code >= 48 && code <= 57) {
	            var pos = getCurPosition(this);
	            var selText = getSelectedText(this);
	            
	            var dotPos = this.value.indexOf(".");
	            if (dotPos >= 0 && pos > dotPos) {
            		//$(this).attr("maxlength",fieldLen+1);
	                if (pos > dotPos + 2) return false;
	                if (selText.length > 0 || this.value.substr(dotPos + 1).length < 2)
	                    return true;
	                else
	                    return false;
	            }else if(dotPos < 0){
	            	if(this.value.replace(/,/g,"").length >= fieldLen-2){
		            	return false;
		            }
	            }else if(dotPos >= 0 && pos <= dotPos){
	            	if (this.value.substr(0,dotPos).replace(/,/g,"").length >= fieldLen-2){
	            		return false;
	            	}
	            }
	            return true;
	        }
	        //输入"."
	        if (code == 46) {
	            var selText = getSelectedText(this);
	            if (selText.indexOf(".") > 0) return true; //选中文本包含"."
	            //else if (/^[0-9]+\.$/.test(this.value + String.fromCharCode(code)))
	            return true;
	        }
	        return false;
	    });
	    /*this.bind("blur", function() {
	        if (this.value.lastIndexOf(".") == (this.value.length - 1)) {
	            this.value = this.value.substr(0, this.value.length - 1);
	        } else if (isNaN(this.value)) {
	            this.value = "";
	        }
	        if (this.value)
	            this.value = parseFloat(this.value).toFixed(tailLen);
	        $(this).trigger("input");
	    });*/
	    this.bind("paste", function() {
	        if (window.clipboardData) {
	            var s = clipboardData.getData('text');
	            if (!isNaN(s)) {
	                value = parseFloat(s);
	                return true;
	            }
	        }
	        return false;
	    });
	    this.bind("dragenter", function() {
	        return false;
	    });
	    this.bind("keyup", function() {
	    	var stmp = "";
			if($(this).val()==stmp) return;
			var ms = $(this).val().replace(/[^\d\.]/g,"").replace(/(\.\d{2}).+$/,"$1").replace(/^0+([1-9])/,"$1").replace(/^0+$/,"0");
			var txt = ms.split(".");
			while(/\d{4}(,|$)/.test(txt[0]))
				txt[0] = txt[0].replace(/(\d)(\d{3}(,|$))/,"$1,$2");
			$(this).val(stmp = txt[0]+(txt.length>1?"."+txt[1]:""));
	    });
	    /*this.bind("propertychange", function(e) {
	        if (isNaN(this.value))
	            this.value = this.value.replace(/[^0-9\.]/g, "");
	    });*/
	    /*this.bind("input", function(e) {
	        if (isNaN(this.value))
	            this.value = this.value.replace(/[^0-9\.]/g, "");
	    });*/
	    this.bind("focus",function(){
	    	var value=$(this).val();
			var leng=value.length;
		
			if(leng>0){
				if (value.substr(0,1)=="￥"){
					$(this).val(value.substr(1,leng)); 
				}
			}
	    });
	};
	//文本框只能输入正整数
	$.fn.integerOnly = function(){
		$(this).css("text-align","right");
		this.bind("keypress", function(e) {
			//alert(window.event.keyCode);  
		    var e = $(this).event || window.event;  
		    var code = parseInt(e.keyCode);  
		    if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {  
		        return true;  
		    } else {  
		        return false;  
		    }  
		});
		this.bind("keyup", function() {
			var stmp = "";
			if($(this).val()==stmp) return;
			eval("var re = /(\.\d{2}).+$/;");  
			var ms = $(this).val().replace(/[^\d\.]/g,"").replace(re,"$1").replace(/^0+([1-9])/,"$1").replace(/^0+$/,"0");
			while(/\d{4}(,|$)/.test(ms)){
				ms = ms.replace(/(\d)(\d{3}(,|$))/,"$1,$2");
			}
			$(this).val(stmp = ms);
		});
	};
	//文本框可以输入小数,fieldLen数据长度(不包括小数点),tailLen小数长度
	$.fn.decimalInput = function(fieldLen,tailLen) {
	    $(this).css("text-align","right").css("ime-mode", "disabled");
	    this.bind("keypress", function(e) {
	        if (e.charCode === 0) return true;  //非字符键 for firefox
	        var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE
	        if (code >= 48 && code <= 57) {
	            var pos = getCurPosition(this);
	            var selText = getSelectedText(this);
	            
	            var dotPos = this.value.indexOf(".");
	            
	            if (dotPos >= 0 && pos > dotPos) {
            		$(this).attr("maxlength",fieldLen+1);
	                if (pos > dotPos + tailLen) return false;
	                if (selText.length > 0 || this.value.substr(dotPos + 1).length < tailLen)
	                    return true;
	                else
	                    return false;
	            }else if(dotPos < 0){
	            	if(this.value.length >= fieldLen-tailLen){
		            	return false;
		            }
	            }else if(dotPos >= 0 && pos <= dotPos){
	            	if (this.value.substr(0,dotPos).length >= fieldLen-tailLen){
	            		return false;
	            	}
	            }
	            return true;
	        }
	        //输入"."
	        if (code == 46) {
	            var selText = getSelectedText(this);
	            if (selText.indexOf(".") > 0) return true; //选中文本包含"."
	            else if (/^[0-9]+\.$/.test(this.value + String.fromCharCode(code)))
	                return true;
	        }
	        return false;
	    });
	    this.bind("blur", function() {
	        if (this.value.lastIndexOf(".") == (this.value.length - 1)) {
	            this.value = this.value.substr(0, this.value.length - 1);
	        } else if (isNaN(this.value)) {
	            this.value = "";
	        }
	        if (this.value)
	            this.value = parseFloat(this.value).toFixed(tailLen);
	        $(this).trigger("input");
	    });
	    this.bind("paste", function() {
	        if (window.clipboardData) {
	            var s = clipboardData.getData('text');
	            if (!isNaN(s)) {
	                value = parseFloat(s);
	                return true;
	            }
	        }
	        return false;
	    });
	    this.bind("dragenter", function() {
	        return false;
	    });
	    this.bind("keyup", function() {
	    	var stmp = "";
			if($(this).val()==stmp) return;
			var ms = $(this).val().replace(/[^\d\.]/g,"").replace(/(\.\d{4}).+$/,"$1").replace(/^0+([1-9])/,"$1").replace(/^0+$/,"0");
			var txt = ms.split(".");
			while(/\d{4}(,|$)/.test(txt[0]))
				txt[0] = txt[0].replace(/(\d)(\d{3}(,|$))/,"$1,$2");
			$(this).val(stmp = txt[0]+(txt.length>1?"."+txt[1]:""));
	    });
	    /*this.bind("propertychange", function(e) {
	        if (isNaN(this.value))
	            this.value = this.value.replace(/[^0-9\.]/g, "");
	    });
	    this.bind("input", function(e) {
	        if (isNaN(this.value))
	            this.value = this.value.replace(/[^0-9\.]/g, "");
	    });*/
	};

	 

	//获取当前光标在文本框的位置
	function getCurPosition(domObj) {
	    var position = 0;
	    if (domObj.selectionStart || domObj.selectionStart == '0') {
	        position = domObj.selectionStart;
	    }
	    else if (document.selection) { //for IE
	        domObj.focus();
	        var currentRange = document.selection.createRange();
	        var workRange = currentRange.duplicate();
	        domObj.select();
	        var allRange = document.selection.createRange();
	        while (workRange.compareEndPoints("StartToStart", allRange) > 0) {
	            workRange.moveStart("character", -1);
	            position++;
	        }
	        currentRange.select();
	    }

	    return position;
	}
	//获取当前文本框选中的文本
	function getSelectedText(domObj) {
	    if (domObj.selectionStart || domObj.selectionStart == '0') {
	        return domObj.value.substring(domObj.selectionStart, domObj.selectionEnd);
	    }
	    else if (document.selection) { //for IE
	        domObj.focus();
	        var sel = document.selection.createRange();
	        return sel.text;
	    }
	    else return '';
	}
});
//获取地址栏的参数
function request(strParame) { 
	var args = new Object( ); 
	var query = location.search.substring(1); 

	var pairs = query.split("&"); // Break at ampersand 
	for(var i = 0; i < pairs.length; i++) { 
	var pos = pairs[i].indexOf('='); 
	if (pos == -1) continue; 
	var argname = pairs[i].substring(0,pos); 
	var value = pairs[i].substring(pos+1); 
	value = decodeURIComponent(value); 
	args[argname] = value; 
	} 
	return args[strParame]; 
} 