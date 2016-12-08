
/**
 * 2011-12-27 xueqiang
 * 
 */
/** 弹出框 */
var artDg;
/** document.getElementById简化函数 */

var _$ = function (id){
	
	return 'string' == typeof id ? document.getElementById(id) : id;
};

/** 插入的flash地址 */
var flashSrcURL = "";
/** 传递到后台的参数(测试) */
var flash_params="";
/** 接收图片的servlet */
var flash_uploadURL="/space/updateHeadPic.html";
/** 图片扩展名 */
var flash_fileExt = "PNG";
/** flash的嵌入脚本 */
var flashCode;
var flash_img_width = 160;
var flash_img_height = 160;

/**
 * 刷新前台传到后台的参数
 */
function freshParam(){
	var uploadURL = flash_uploadURL+"?id="+flash_params;
	var flashvars = "uploadURL="+uploadURL + 
					"&fileExt="+flash_fileExt +
					"&&img_width=" + flash_img_width + 
					"&&img_height=" + flash_img_height;
	flashCode = 
		'<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" '+
			'codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" '+
			'WIDTH="325" HEIGHT="350" id="photoEditor"> '+
			'<PARAM NAME=movie VALUE='+flashSrcURL+'> '+
			'<PARAM NAME=quality VALUE=high> '+
			'<PARAM NAME=bgcolor VALUE=#FFFFFF> '+
			'<PARAM NAME=flashvars VALUE='+flashvars+'> '+
			'<EMBED src="'+flashSrcURL+'" quality=high bgcolor=#FFFFFF WIDTH="325" HEIGHT="350"  '+
				'NAME="photoEditor" TYPE="application/x-shockwave-flash" flashvars="'+flashvars+'" '+
				'PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer"> '+
			'</EMBED> '+
		'</OBJECT> ';
}

/**
 * 回调函数
 * @param {Object} state 1为上传成功;0为上传失败
 * @param {Object} mess
 */
function uploadStage(state,mess){
	artDg.close();
	var jsonstr = $.parseJSON(mess);
	var msg = jsonstr.msg;
	var status = jsonstr.status;
	var path =jsonstr.data;
	if(status == "1"){
		alert("头像修改失败！");
	}else{
		alert(msg);
	}
}

/**
 * 
 * @param {Object} flashSrcURL   如XXX.swf
 * @param {Object} uploadURL 上传地址,如http://localhost/photoEditor/Upload
 * @param {Object} params 传递到后台的参数,如  param1=XXX&&param2=XXX
 * @param {Object} fileExt 图片扩展名:"PNG"或"JPG"
 */
function showPhotoEditor(flashSrcURL,uploadURL,params,fileExt,img_width,img_height){
	this.flashSrcURL = flashSrcURL;
	flash_uploadURL = uploadURL;
	flash_params = params;
	if(img_width){flash_img_width = img_width};
	if(img_height){flash_img_height = img_height};
	// 将所有的"&&"替换为"%26%26"
	flash_params = flash_params.replace(new RegExp("&&","gm"),"%26%26");
	if(fileExt){
		flash_fileExt = fileExt;
	}
	/**** 传递参数 ****/
	freshParam();
	/**** 弹出窗口 ****/
	artDg = art.dialog({
		padding: 0,
		id:'artDg', 
		title:'请编辑您的头像', 
		content:flashCode, 
		lock:true,
		drag:false
	});
}



