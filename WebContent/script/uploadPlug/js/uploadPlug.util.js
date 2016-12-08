/**
* 解析后台服务器返回过来的错误码
*/
function showErrorCode(filename,code){ 
     code = parseInt(code);
     if(code == 10000){
     	showUploadPlugMsg(filename+",剩余容量不足."); 
     }else if(code == 10001){
     	showUploadPlugMsg(filename+",参数错误.");  
     }
 }

var allComplete = false;
//上传完成后调用
function onAllComplete(event,data){
	allComplete = true;
	if(data.errors > 0){
		uploadBtnDisable(true);
		showUploadPlugMsg("上传失败,网络异常....");
	}else{ 
		saveData();
	}
}

function setParam(){
	var data =  {};
	try {
		data = parent.getParam();
		var f = $("#backf");
		for(var key in data)
	     {
			 var input = $("<input>");
			 input.attr("type","hidden");
			 input.attr("name",key);
			 input.attr("value",data[key]);  
			 f.append(input);
	     }
	}catch(e){}
}
//Object对象转换为string
 function obj2str(o){  
        var r = [], i, j = 0, len;  
        if(o == null) {  
            return o;  
        }  
        if(typeof o == 'string'){  
            return '"'+o+'"';  
        }  
        if(typeof o == 'object'){  
            if(!o.sort){  
                r[j++]='{';  
                for(i in o){  
                    r[j++]= '"';  
                    r[j++]= i;  
                    r[j++]= '":';  
                    r[j++]= obj2str(o[i]);  
                    r[j++]= ',';  
                }  
                //可能的空对象  
                //r[r[j-1] == '{' ? j:j-1]='}';  
                r[j-1] = '}';  
            }else{  
                r[j++]='[';  
                for(i =0, len = o.length;i < len; ++i){  
                    r[j++] = obj2str(o[i]);  
                    r[j++] = ',';  
                }  
                //可能的空数组  
                r[len==0 ? j:j-1]=']';  
            }  
            return r.join('');  
        }  
        return o.toString();  
 }  

/**
* 上传文件成功后，返回
*/
function saveData (){
		$.ajax({
			url:'/fileupload/saveData.html',
			type:'POST',
			scriptCharset: 'utf-8',
			dataType:'json',
			data:{},
			success:function(jsonp){
				alert(eval(jsonp));
				var msg = obj2str(jsonp);//转换对象为string
				if(msg != null && msg != undefined){
                var data = eval("("+msg+")");
                if(data != null && data != undefined){ 
                    if(data["state"]){
                        $("#backdata").val($.toJSON(data["data"]));
                        setParam();
                        $("#backdata").closest("form").submit();
                    }else{
                    	alert(data["msg"]);
                    }
                 }
	            
            	}
			}
		});
} 

/**
* 上传文件时，将一些按钮禁用或激活
*/
function disableBtn(state){
	$("#custom-queue-UploadAllBtn").attr("disabled",state);
	$("#file_upload").attr("disabled",state);
}



/**
* 文件上传之前验证
*/
function preUploadFile(event){
	var remain = $('#file_upload').checkRemain();
	 if(remain == 99){
		disableBtn(false);
	}else if(remain == 0){
		disableBtn(true);
		showUploadPlugMsg("请选择上传的文件.");
	}else if(remain == -1){
		disableBtn(true);
		showUploadPlugMsg("文件总大小超出限制");
	}else if(remain == -2){
		disableBtn(true);
		showUploadPlugMsg("文件大小超出限制");
	}else if(remain == -3){
		disableBtn(true);
		showUploadPlugMsg("请不要上传空文件!");
	}
}



var defaultVal="填写标签";

/**
 * 添加标签
 * @param self
 */
function insertTag(self){
	var oldObj = $(self).parent(".Poy_er").clone(true);
	var parObj = $(self).parent(".Poy_er");
	if(0 == parObj.children("input").length)
	{
		var text = parObj.html();
		parObj.html("");
		//创建一个新的文本框
		var text = oldObj.find(".TagText").text();
		var inputObj =  $("<INPUT type='text' class='as'>")
						.val(defaultVal == $.trim(text)? "" : text)
						.appendTo(parObj)
						.trigger("focus")
						.select();
		
		inputObj.focus(function(){
			$(this).val($(this).val());
		});
		inputObj.blur(function(){
			var selfText = $(this).val();
			if(validataTag(selfText)){
				oldObj.find(".TagText").text("" == $.trim(selfText) ? defaultVal : selfText);
				parObj.html(oldObj.html());
			}else{
				this.focus();
			}
		});
		//处理文本框上回车和 ESC 的处理
		//如果按下回车，则表示确认输入
		//如果按下 ESC 表示取消操作
		
		inputObj.keyup(function(event){
			var keycode = event.which;
			//回车键的键值为13 ， ESC 的键值为 27
			if(13 == keycode)
			{
				var selfText = $(this).val();
				if(validataTag(selfText)){
					oldObj.find(".TagText").text(selfText);
					parObj.html(oldObj.html());
				}else{
					this.focus();
				}
			}
			if(27 == keycode)
			{
				parObj.html(text);
			}
		});
	}
}

function validataTag(tagVal){
	var tagPatten = /(^[,，]+)|([,，]{2,})|([,，]+$)/;
	return !tagPatten.test(tagVal);
}

function dittoValue(self){
	var prevItem = $(self).parent(".Poy_er").parent(".FileItem").prev();
	if(-1 != prevItem.attr("class").indexOf("FileItem")){
		var prevText = prevItem.find(".Poy_er").find(".TagText").text();
		if(validataTag(prevText) && "" != prevText){
			$(self).parent(".Poy_er").find(".TagText").text(prevText);
		}
	}
}

/**
* 当文件开始校验时,提示信息
*/
function onCheck(event, item, ID, fileObj){ 
	$(item).find(".ProgressBar").find(".ProgressBarMsg").text("正在分析文件,请稍候 ..."); 
}

/**
* 当校验完成时,提示信息
*/
function onCheckComplete(event,item,ID,uniquecode,fileObj,response){
	if("true" == response){
		$(item).find(".UploadBtn").removeClass("NomalUploadBtn").addClass("FastUploadBtn"); 
		$(item).find(".ProgressBar").find(".ProgressBarMsg").text("服务器上已经存在该文件!"); 
		event.setFastUploadFlag(ID,true);
	}else{
		$(item).find(".ProgressBar").find(".ProgressBarMsg").text("0% (" + convertSize(fileObj.size) + ")"); 
	}
}

/**
* 当文件选择完成时
*/
function onSelectOnce(event,item,ID,fileObj,data) {
	var tagNameArray = ["电子书","音乐","视频","压缩文件","幻灯片","歌词","图片","Java文件"];
	var pattenArray = [
		/(.*)\.(txt|doc|docx|pdf|xls|chm|epub|mobi|umd|wps|TXT|DOC|DOCX|PDF|XLS|CHM|EPUB|MOBI|UMD|WPS)$/, //电子书
		/(.*)\.(mp3|wma|mid|midi|wav|vqf|MP3|WMA|MID|MIDI|WAV|VQF)$/, //音乐格式
		/(.*)\.(rm|3gp|mp4|rmvb|avi|wmv|flv|vob|exe|mkv|swf|RM|3GP|MP4|RMVB|AVI|WMV|FLV|VOB|EXE|MKV|SWF)$/, //视频格式
		/(.*)\.(rar|zip|7zip|tgz|RAR|ZIP|7ZIP|TGZ)$/, //压缩格式
		/(.*)\.(ppt|pptx|PPT|PPTX)$/, //幻灯片
		/(.*)\.(lrc|krc|LRC|KRC)$/, //歌词
		/(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga|JPG|BMP|GIF|ICO|PCX|JPEG|TIF|PNG|RAW|TGA)$/,  //图片
		/(.*)\.(java|jar|war|class|JAVA|JAR|WAR|CLASS)$/  //Java文件
		];
	
	//匹配标签类型
	$.each(pattenArray,function(i,patten){
		if(patten.test(fileObj.type)){
			$(item).find(".TagText").text(tagNameArray[i]);
			return false;
		}
	});
	
	//如果是第一个元素,则去掉 “同上” 按钮
	if(data.fileIndex == 0){
		$(item).find(".CopyPrev").remove();
	}
}

/**
* 当删除一个文件时
*/
function onCancelComplete(event, item,ID, fileObj, data, clearFast){
	if(data.fileIndex == 0){
		$(item).next().find(".CopyPrev").remove();
	}
}

function onUploadOnce(){
	return true;
}


function onUpload(){
	return true;
}