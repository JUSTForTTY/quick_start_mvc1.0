/**
* 重写友好提示框
*/
function showUploadPlugMsg(info){
    alert(info);
}



/**
*
*   文件上传插件 UploadPlug V1.0
*   扩展一个jQuery文件上传插件,本插件支持批量文件上传,检查文件唯一性等等
*   创建人：谭勇德
*   创建日期: 2012-05-14
*   最后修改: 2012-06-07
*   
*   使用前必读(全局操作元素ID命名规则)
*   queueID 文件队列ID(必填)，以custom-queue为例
*   全部上传按钮ID  custom-queue-UploadAllBtn 
*   全部取消按钮ID  fcustom-queue-CancelAllBtn 
*   文件队列项模板ID  custom-queue-item
*   
*   文件队列项中各元素在CSS中的class定义如下
*   QueueItem 一个文件队列项
*   FileName 显示文件名
*   ProgressBar 进度条
*   ProgressBarIcon 显示进度条提示图标
*   ProgressBarValue 显示进度条宽度
*   ProgressBarMsg 显示进度条提示信息
*   CancelBtn 删除、取消按钮
*   UploadBtn 上传按钮
*   FastUploadBtn 快速上传按钮
*
*
*   使用前必读(插件详细API)
*   uploader:       uploadPlug.swf 文件的相对路径，该swf文件是一个带有文字BROWSE的按钮，点击后淡出打开文件对话框，默认值：/images/uploadPlug.swf。 
*   uploadUrl:      上传文件请求后台处理的地址。默认值：uploadPlug.html
*   uploadParam:    上传文件后台所需参数
*   checkExistUrl:  检查文件是否存在时请求后台的地址，默认值：false
*   checkExistParam:检查文件是否存在时请求后台所需参数
*   fileDataName:   设置一个名字，在服务器处理程序中根据该名字来取上传文件的数据。默认为Filedata 
*   uniqueCodeName: 设置一个名字，在服务器处理程序中根据该名字来获取检查是否存在的唯一标识,采用CRC加密
*   method:         提交方式POST 或GET 默认为POST 
*   expressInstall:	遇 flashplayer 版本冲突时,flashplayer安装文件所在路径,默认值:/images/expressInstall.swf
*   buttonImg:      浏览、添加文件按钮的图片路径
*   cancelImg:      选择文件到文件队列中后的每一个文件上的关闭按钮图片路径。
*   width:          设置浏览按钮的宽度 ，默认值：120。 
*   height:         设置浏览按钮的高度 ，默认值：30。 
*   wmode:          设置该项为transparent 可以使浏览按钮的flash背景文件透明，并且flash文件会被置为页面的最高层。 默认值：opaque 。 
*   scriptAccess:   flash脚本文件的访问模式，如果在本地测试设置为always，默认值：sameDomain(允许脚本访问)
*   queueID:        文件队列的容器ID，该ID与存放文件队列的div的ID一致。 
*   queueSizeLimit: 文件队列中未上传的文件最大允许的个数,默认值999。 
*   sizeLimit:      单个文件上传大小限制，默认值 20M ,建议最大不要超过 500M。 
*   simUploadLimit: 允许同时上传的数量。设置为 1 为单线程,当允许多文件同时上传时，设置一个大于1的整数,默认值。 
*   multi:          设置为true时可以上传多个文件。 
*   auto:           设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传 。 
*   fileNameLen: 	文件队列中显示的文件名最大长度,按单字节计算,默认值,20。
*   showFileExt:	文件队列中显示的文件名是否包含文件后缀,默认值 false(表示不包含)。
* 	overString:		文件队列中显示的文件名超出指定长度的部分用指定的字符代替。默认值 '...'
*   fileDesc:       这个属性值必须设置fileExt属性后才有效，用来设置选择文件对话框中的提示文本，如设置fileDesc为“请选择rar doc pdf文件”
*   fileExt:        设置可以选择的文件的类型，格式如：'*.doc;*.pdf;*.rar' 。 
*   hideButton:     设置为true则隐藏浏览按钮的图片 。 
*   buttonText:     浏览按钮的文本，隐藏按钮图片是生效，默认值： '浏览 ...' 。 
*   rollover:       值为true和false，设置为true时当鼠标移到浏览按钮上时有反转效果。 
*   removeCompleted:单个文件上传完成后,是否从队列中删除。默认设置为 true,表示上传成功后删除。
*   queueItemClassName:		文件队列项在CSS中class选择器的名称,默认值 'QueueItem'
*   fileNameClassName: 		显示文件名的class选择器名称,默认值 'FileName'
*   progressBarClassName: 	显示进度条进度的class选择器名称，默认值 'ProgressBar'
*   progressBarValueClassName:显示进度条提示文字的class选择器名称，默认值 'ProgressBarValue'
*   progressBarMsgClassName:显示进度条提示文字的class选择器名称，默认值 'ProgressBarMsg'
*   progressBarIconClassName:显示进度条提示图标的class选择器名称，默认值 'ProgressBarIcon'
*   cancelBtnClassName: 	文件队列中取消按钮的class选择器名称，默认值 'CancelBtn'
*   uploadBtnClassName: 	文件队列中上传按钮的class选择器名称，默认值 'UploadBtn'
*   
*
*   接下来要介绍的所有内置函数，可以在选择文件、出错或其他一些操作的时候返回一些信息给用户，以便用户的个性化操作。
*   onInit：         上传插件初始化时调用,如果返回false,则不执行内置操作。
*   onSelect：       选择文件时触发，该函数有 event, item,ID, fileObj,data 五个参数
*                   event:事件对象，有以下几个属性:
*                       target 事件源对象
*                   item:已创建好的文件队列项,如果返回false,则不可操作
*                   ID：文件的唯一标识，由6为随机字符组成。 
*                   fileObj：选择的文件对象，有以下五个属性：
*                   		name: 文件名称
*                   		size: 文件大小
*                   		creationDate: 创建日期
*                   		modificationDate: 最后修改日期
*                   		type 文件后缀
*                   data:反馈数据,有六个属性{
*						fileIndex:	文件在队列中的索引位置
*						fileCount : 当前文件队列
*						filesSelected : filesSelected,
*						filesReplaced : filesReplaced,
*						allBytesTotal : allBytesTotal,
*						addValidate : addValidate}
*                   
*	onAddComplete:	当文件成功添加到队列时调用,该函数有 event,item,ID, fileObj,data 五个参数,参数内容同 onSelect
*
*   onQueueFull:    当设置了queueSizeLimit并且选择的文件个数超出了queueSizeLimit的值时触发。如果返回false,则无任何操作。
*                   该函数有两个参数event和queueSizeLimit。
*                   
*   onCheck:		开始检测文件唯一性时调用,该函数有event, item, ID, fileObj四个参数			
*                   
*   onCheckComplete: 文件唯一性检测完毕时调用,该方法有五个参数 event, item, ID,uniquecode, fileObj,response 
*   				uniquecode 返回CRC校验后，生成的唯一标识。
*   				response 从服务器返回的结果.根据结果判断，
*   						 如果该文件在服务器已经存在,用户可执行 event.setFastUploadFlag(ID,true);实现快速上传.
*
*   onCancel:       当点击文件队列中文件的关闭按钮或点击取消上传时触发。该函数有event, item,ID, fileObj, data, clearFast六个参数，前四个参数同onSelect
*                   data对象有两个属性
*                       allBytesTotal : 队列中所有文件的总大小。
*                       fileCount：取消一个文件后，文件队列中剩余文件的个数。 
*                       allBytesTotal：取消一个文件后，文件队列中剩余文件的大小。 
*                   clearFast: 是否快速清除
*   
*   onCancelComplete: 取消完成后触发,该函数有event, item,ID, fileObj, data, clearFast六个参数,参数说明同 onCancel
*
*   onClearQueue:   清空队列中的文件时触发。event, clearFast两个参数，同onCancel 中的两个对应参数。
*
*   onUpload:       点击上传时触发，如果auto设置为true则是选择文件时触发，如果有多个文件上传则遍历整个文件队列。
*                   该函数有event, item,ID,uniquecode,fileObj,data，参数的解释同onCheckComplete。
*                    data 对象有一个属性，分别为
                   		fastFlag 如果为true,表示快速上传
*
*   onProgress:     点击上传时触发，如果auto设置为true则是选择文件时触发，如果有多个文件上传则遍历整个文件队列，在onOpen之后触发。
*                   该函数有event, item,ID, fileObj, data五个参数，前三个参数的解释同onCheckComplete。
*                   data对象有四个属性percentage、bytesLoaded、allBytesLoaded、speed：
*                       percentage：当前完成的百分比 
*                       bytesLoaded：当前上传的大小 
*                       allBytesLoaded：文件队列中已经上传完的大小 
*                       speed：上传速率 kb/s 
*
*  onComplete:     文件上传完成后触发。
*                   该函数有四个参数event, item,ID, fileObj, response, data六个参数，前三个参数同上。
*                   response为后台处理程序返回的值，在上面的例子中为1或0，
*                   data有四个属性，分别为
                       completeCount：已上传完成的文件个数
                       waitCount:剩余没有上传完成的文件的个数。 
                       speed：文件上传的平均速率 kb/s 
                       fastFlag： 是否快速上传
*                   注：fileObj对象和上面讲到的有些不太一样，onComplete 的fileObj对象有个filePath属性可以取出上传文件的路径。
*   
*   onUploadAll:    点击全部上传按钮时调用,如果返回false,则不执行内置方法,该方法有一个参数 event
*
*   onAllComplete：  文件队列中所有的文件上传完成后触发。该函数有event和data两个参数，
*                   data有四个属性，分别为：
*                       filesUploaded :上传的所有文件个数。 
*                       errors ：出现错误的个数。 
*                       allBytesLoaded ：所有上传文件的总大小。 
*                       speed ：平均上传速率 kb/s 
*
*                   
*	onError:        当上传过程中发生错误时触发。该函数有event、ID、fileObj、errorObj四个参数，其中前三个参数同上，
*                   errorObj对象有type和info两个属性。
*                       type：错误的类型，有三种‘HTTP’, ‘IO’, or ‘Security’ 
*                       info：错误的描述 
*                       
*   uploadPlugSettings：可以动态修改上面介绍的那些key值，如下面代码
*                   $('#file_upload').uploadPlugSettings('folder','Uploads');
*                   如果上传按钮的事件写成下面这样，文件将会上传到uploadPlugSettings定义的目录中
*                   <a href="javascript:$('#file_upload').uploadPlugSettings('folder','Uploads');
*                   
*  
*  例子:
  
   资源下载:
  
	UploadPlug-v1.0.zip

   页面代码:
 
	<!-- 上传插件文件域 -->
	<input id="file_upload" type="file" />

	<!-- 文件队列项模板 -->
	<ul style="display: none;" id="custom-queue-item">
		<li class="FileName"></li>
		<li class="ProgressBar">
			<p class="ProgressBarIcon"></p>
			<p class="ProgressBarValue"></p>
			<p class="ProgressBarMsg"></p>
		</li>
		<li>
			<p class="UploadBtn" ></p>
			<p class="CancelBtn" ></p>
		</li>
	</ul>
	
	<!-- 文件队列显示区域 -->
	<div id="custom-queue">
		<ul>
			<li>文件名</li>
			<li>上传进度</li>
			<li>操作</li>
		</ul>
	</div>
	
	<!-- 全部上传按钮 -->
	<div>
		<a id="custom-queue-UploadAllBtn" href="javascript:void(0);">全部上传</a>
	</div>
	

  javaScript代码:
  
  <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
  <script type="text/javascript" src="/js/swfobject.js"></script>
  <script type="text/javascript" src="/js/uploadPlug.min.js"></script>
  <script type="text/javascript" src="/js/uploadPlug.util.js"></script>
  <script type="text/javascript">
   $(function() {  
		$('#file_upload').uploadPlug({
			'uploader'       : '/images/uploadPlug.swf',
			'uploadUrl'      : '/fileupload/uploadFile.html', 
			'uploadParam' : {},
			'checkExistUrl'	 : '/fileupload/checkFileExist.html',
			'checkExistParam': {},
			'uniqueCodeName' : 'uniquecode',
			'buttonImg'      : '/images/addFile.gif', 
			'method'		 : 'POST',
			'multi'          : true,
			'queueSizeLimit' : 10,
			'auto'           : false,
			'fileExt'        : '*.*',
			'fileDesc' 	     : '所有文件(*.*)',
			'height'         : 26,
			'width'          : 92,
			'sizeLimit'		 : (1024 * 1024 * 20),
			'fileNameLen'	 : 30,
			'queueID'        : 'custom-queue', 
			'removeCompleted': false,
			'onCheck'		 : function(event, item, ID, fileObj){
				$(item).find(".ProgressBar").find(".ProgressBarMsg").text("正在分析文件,请稍候 ..."); 
			},
			'onCheckComplete' :function(event, item, ID,uniquecode, fileObj,response){
				event.setFastUploadFlag(ID,true);
				$(item).find(".ProgressBar").find(".ProgressBarMsg").text("服务器上已经存在该文件!"); 
			},
			'onSelect'   	 : function(event, item,ID, fileObj,data){
				alert('文件名:' + fileObj.name + 
                     ' 文件大小'	+ fileObj.size + 
                     '创建日期' + fileObj.creationDate +
                     '最后修改日期' + fileObj.modificationDate + 
                     '文件类型' + fileObj.type);
			},
			'onUpload'		 :function(event, item,ID,uniquecode,fileObj,data){
				alert(data.fastFlag ? '快速上传' : '普通上传');
			}
		});	
	}); 
  </script>

*                     
*/
if(jQuery)(
    function(jQuery){
        jQuery.extend(jQuery.fn,{
        /**
        * 定义插件配置信息
        */
            uploadPlug:function(options) {
                jQuery(this).each(function(){
                    var settings = jQuery.extend({
                    id: jQuery(this).attr('id'), //文件域的ID(文件域必须有ID)
                    uploader: '/images/uploadPlug.swf', //上传插件,swf文件所在路径
                    uploadUrl: 'upload.html', //服务器端的上传文件请求地址
                    checkExistUrl: false,//检查文件是否存在时请求后台的地址
                    fileDataName: 'fileData', //服务器端获取上传文件集合对象的名称
                    uniqueCodeName: 'uniquecode', //设置一个名字，在服务器处理程序中根据该名字来获取检查是否存在的唯一标识
                    expressInstall: '/images/expressInstall.swf',  //遇 flashplayer 版本冲突时,flashplayer安装文件所在路径
                    folder: '', //服务器端上传文件临时文件夹
                    width: 120, //flash上传按钮的宽度
                    height: 30, //flash上传按钮的高度
                    cancelImg: '', //取消文件文件队列按钮的默认图片
                    wmode: 'opaque', //flash加载模式,可解决中文乱码问题
                    scriptAccess: 'sameDomain', //允许脚本访问
                    method: 'POST', //请求服务器端方式
                    queueID: false, //文件队列队列容器可选ID
                    queueSizeLimit: 999, //文件队列的最大大小
                    sizeLimit: (1024 * 1024 * 20), //单个文件上传限制，默认 20M
                    simUploadLimit: 1, //允许同时上传的数量。设置为 1 为单线程,设置大于 1 为多线程同时上传
                    fileNameLen: 20,
                    showFileExt:false,//是否显示文件后缀
 					overString:'...',//文件长度截取后添加的字符
                    addFileBtnIdSuff: '-AddFileBtn',
                    cancelAllBtnIdSuff: '-CancelAllBtn',
                    uploadAllBtnIdSuff: '-UploadAllBtn',
                    queueItemDefinedIdSuff: '-item',
                    queueItemClassName: 'QueueItem',
                    fileNameClassName: 'FileName',
                    progressBarClassName: 'ProgressBar',
                    progressBarValueClassName: 'ProgressBarValue',
                    progressBarIconClassName: 'ProgressBarIcon',
                    progressBarMsgClassName: 'ProgressBarMsg',
                    cancelBtnClassName: 'CancelBtn',
                    uploadBtnClassName: 'UploadBtn',
                    fastUploadBtnClassName:'FastUploadBtn',
                    percentageClassName     : 'Percentage', //设置为“速度”，显示上传速度在默认的队列项
                    removeCompleted : true, //单个文件上传完成后,是否从队列中删除。默认设置为 true,表示上传成功后删除。
                    onInit          : function() {}, //上传插件初始化时调用
                    onSelect        : function() {}, //当文件选择完成时调用
                    onAddComplete	: function() {}, //文件成功添加到队列中调用
                    onQueueFull     : function() {}, //当文件队列数量到达上限时
                    onCheck         : function() {}, //检查服务器上是否存在重复文件
                    onCheckComplete : function() {}, //检测完成
                    onCancel        : function() {}, //取消队列中的一个文件
                    onCancelComplete: function() {}, //取消队列中的一个文件
                    onUpload        : function() {}, //开始上传时调用
                    onClearQueue    : function() {}, //清空队列中的所有文件
                    onError         : function() {}, //上传文件发生错误时调用
                    onProgress      : function() {}, //上传进度发生改变时调用
                    onComplete      : function() {}, //单个文件上传完成时调用
                    onUploadAll     : function() {}, //开始全部上传时调用
                    onAllComplete   : function() {}, //所有文件上传完成时调用
                    onCancelAll     : function() {}, //开始全部清空时调用
                    onCalcelAllComplete: function(){} //全部清空完成时调用
                    //onFastUpload    : function() {}  //快速上传
                }, options);
                jQuery(this).data('settings',settings);
                var pagePath = location.pathname;
                pagePath = pagePath.split('/');
                pagePath.pop();
                pagePath = pagePath.join('/') + '/';
                settings.filesQueue = {};
                settings.filesQueueIds = new Array(0);
                var data = {};
                data.uploadPlugID = settings.id;
                data.pagepath = pagePath;
                if (settings.buttonImg) data.buttonImg = escape(settings.buttonImg);
                if (settings.buttonText) data.buttonText = escape(settings.buttonText);
                if (settings.rollover) data.rollover = true;
                data.uploadUrl = settings.uploadUrl;
                data.folder = escape(settings.folder);
                //上传文件是请求后台所需参数
                if (settings.uploadParam) {
                    data.uploadParam = parseUrlParam(settings.uploadParam);
                }
                //检查文件是否存在时,请求后台所需参数
                if (settings.checkExistParam) {
                    data.checkExistParam = parseUrlParam(settings.checkExistParam);
                }
                
                
                data.width          = settings.width;
                data.height         = settings.height;
                data.wmode          = settings.wmode;
                data.method         = settings.method;
                data.queueSizeLimit = settings.queueSizeLimit;
                data.simUploadLimit = settings.simUploadLimit;
                if (settings.hideButton)   data.hideButton   = true;
                if (settings.fileDesc)     data.fileDesc     = settings.fileDesc; 
                if (settings.fileExt)      data.fileExt      = settings.fileExt;
                if (settings.multi)        data.multi        = true;
                if (settings.auto)         data.auto         = true;
                if (settings.sizeLimit)    data.sizeLimit    = settings.sizeLimit;  
                if (settings.checkExistUrl)  data.checkExistUrl  = settings.checkExistUrl;
                if (settings.fileDataName) data.fileDataName = settings.fileDataName;
                if (settings.uniqueCodeName) data.uniqueCodeName = settings.uniqueCodeName;
                if (settings.queueID)      data.queueID      = settings.queueID;
                if (settings.onInit() !== false) {
                    jQuery(this).css('display','none');
                    jQuery(this).after('<div id="' + jQuery(this).attr('id') + 'Uploader"></div>');
                    swfobject.embedSWF(settings.uploader, settings.id + 'Uploader', settings.width, settings.height, '9.0.24', settings.expressInstall, data, {'quality':'high','wmode':settings.wmode,'allowScriptAccess':settings.scriptAccess},{},function(event) {
                        if (typeof(settings.onSWFReady) == 'function' && event.success) settings.onSWFReady();
                    });
                    if (settings.queueID == false) {
                        jQuery("#" + jQuery(this).attr('id') + "Uploader").after('<div id="' + jQuery(this).attr('id') + 'Queue" class="uploadPlugQueue"></div>');
                    } else {
                        jQuery("#" + settings.queueID).addClass('uploadPlugQueue');
                    }
                    if(settings.uploadAllBtnIdSuff != ''){
                        jQuery("#" + settings.queueID + settings.uploadAllBtnIdSuff).click(function(event){
                            jQuery("#" + settings.id).trigger("onUploadAll",event);
                        });
                    }
                } 
                jQuery(this).bind("uploadPlugSelect", {'action': settings.onSelect, 'queueID': settings.queueID,'afterAdd':settings.onAddComplete}, function(event, ID, fileObj,data) {
                	//var queueItem = jQuery("#" + settings.queueID + settings.queueItemDefinedIdSuff).clone(true);
                	var queueItem = jQuery("." + jQuery(this).attr('name'));//.clone(true);
                    if (event.data.action(event, queueItem,ID, fileObj,data) !== false) {
                        var queueChild = {};
                        queueChild.size =fileObj.size;
                        queueChild.filename = fileObj.name;
                        settings.filesQueue[ID]=queueChild;  
                        settings.filesQueueIds[settings.filesQueueIds.length]=ID;  
                        if(data.addValidate){
                        	//处理文件名
                          	var filesName = fileObj.name.split(".");
                            var fileExt = filesName[filesName.length-1];
                            var fileName = "";
                            var byteSize = convertSize(fileObj.size);
                            for (var i = 0 ; i < filesName.length -1 ; i ++){
                                fileName = fileName + filesName[i];
                            } 
                            var byteLength = fileName.replace(/[^x00-xFF]/g,'**').length;
                            
                            if(byteLength > settings.fileNameLen){
                                fileName = subStringByteLength(fileName,settings.fileNameLen) + settings.overString;
                            }
                            fileName = fileName + (settings.showFileExt ? ("." + fileExt) : '');
                            
                            queue = '#' + jQuery(this).attr('id') + 'Queue';
                            if (event.data.queueID) {
                                queue = '#' + event.data.queueID;
                            } 
                            var view_id = jQuery(this).attr('id') + ID;
                            queueItem.attr("id",view_id + settings.queueItemClassName).find("." + settings.fileNameClassName).attr("id",view_id + settings.fileNameClassName).html(fileName).attr("title",fileObj.name);
                            queueItem.find("." + settings.uploadBtnClassName).attr("id",view_id + settings.uploadBtnClassName).click(function(e){
                                jQuery("#" + settings.id).uploadPlugStartUpload(ID);
                            });
                            queueItem.find("." + settings.cancelBtnClassName).attr("id",view_id + settings.cancelBtnClassName).click(function(e){
                            	jQuery("#" + settings.id).uploadPlugStartCancel(ID);
                            });
                            queueItem.find("." + settings.progressBarClassName).attr("id",view_id + settings.progressBarClassName).find("." + settings.progressBarMsgClassName)
                                                        .attr("id",view_id + settings.progressBarMsgClassName).text('0%(' + byteSize + ')');
                            queueItem.find(".ProgressBarValue").removeAttr("style");
                            //jQuery("." + jQuery(this).attr('name')).remove();
                            //jQuery(queue).append(queueItem.show());
                           // jQuery("#div_"+jQuery(this).attr('name')).append(queueItem.show());
                            if(event.data.afterAdd(event,queueItem,ID, fileObj,data) !== false){
                            	if (settings.checkExistUrl === undefined ||  settings.checkExistUrl == '' || settings.checkExistUrl == null || settings.checkExistUrl ==false) {
                            		if(settings.auto){
                            			 jQuery(this).uploadPlugStartUpload(null);
                            		}
                            	}
                            }
                            
                        }
                    }
    
                });
                
                jQuery(this).bind("uploadPlugQueueFull", {'action': settings.onQueueFull}, function(event, queueSizeLimit) {
                	jQuery("#" + jQuery(this).attr('id') + queueSizeLimit + "QueueItem").fadeOut(250, function() {jQuery(this).remove() });
                    if (event.data.action(event, queueSizeLimit) !== false) {
                    
                    }
                });
                
                jQuery(this).bind("uploadPlugCheckExist", {'action': settings.onCheck}, function(event, ID, fileObj) {
                	var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    event.data.action(event, item, ID, fileObj);
                });
                
                jQuery(this).bind("uploadPlugCheckExistComplete", {'action': settings.onCheckComplete}, function(event, ID,uniquecode,fileObj,response) {
                    var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    var view_id = jQuery(this).attr('id') + ID;
                    if(event.data.action(jQuery(this), item, ID,uniquecode, fileObj,response) !== false){
                        //如果设置为自动上传
                        if (settings.auto) {
                            jQuery(this).uploadPlugStartUpload(null);
                        }
                    }
                });
                
                jQuery(this).bind("uploadPlugStartUpload", {'action': settings.onUpload}, function(event,ID,uniquecode,fileObj,data) {
                    var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    if (event.data.action(event, item,ID,uniquecode,fileObj,data) !== false) {
                        jQuery(this).setUploadValidationFlag(ID,true);
                    }
                });
                
                jQuery(this).bind("uploadPlugProgress", {'action': settings.onProgress}, function(event, ID, fileObj, data) {
                	var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                	
                    if (event.data.action(event, item,ID, fileObj, data) !== false) {
                		var progressBar = item.find("." + settings.progressBarClassName);
                    	var byteSize = convertSize(fileObj.size);
                    	progressBar.find("." + settings.progressBarValueClassName).animate({'width': data.percentage + '%'},250,function() {
                            if (data.percentage == 100) {
                            	progressBar.find("." + settings.progressBarIconClassName).addClass("ProgressSuccessIcon");
                                progressBar.find("." + settings.progressBarMsgClassName).html("(" + byteSize + ") ");
                            }else{
                                progressBar.find("." + settings.progressBarMsgClassName).html(data.percentage + '%(' + convertSize(data.speed * 1024) + '/s)(' + byteSize + ')');
                            }
                        });
              
                    }
                });
    
                jQuery(this).bind("uploadPlugComplete", {'action': settings.onComplete}, function(event, ID, fileObj, response, data) {
                    var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    if (event.data.action(event, item,ID, fileObj, unescape(response), data) !== false) {
                        if (settings.removeCompleted) {
                            jQuery("#" + jQuery(event.target).attr('id')).uploadPlugCancel(ID);
                            jQuery("#" + jQuery(event.target).attr('id') + ID + "QueueItem").fadeOut(250,function() {jQuery(this).remove();});
                        }
                        //jQuery("#" + jQuery(event.target).attr('id') + ID + "QueueItem").addClass('completed');
                    }
                });
                
                /*
                jQuery(this).bind("uploadPlugFastUpload", {'action': settings.onFastUpload}, function(event, ID, fileObj) {
                    var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    if(event.data.action(event, item,ID, fileObj) !== false){
                    	jQuery(this).setUploadValidationFlag(ID,true);
                    }
                });
                */

                jQuery(this).bind("onUploadAll",{'action':settings.onUploadAll},function(event){
                    if(event.data.action(event) !== false){
                        jQuery(this).uploadPlugStartUpload(null);
                    }
                });
                
                
                
                jQuery(this).bind("uploadPlugStartCancel", {'action': settings.onCancel}, function(event, ID, fileObj, data, remove, clearFast) {
                	/*var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    if (event.data.action(event, item,ID, fileObj, data, clearFast) !== false) {
                        jQuery(this).uploadPlugCancelUpload(ID);
                    }*/
                });
                
                jQuery(this).bind("uploadPlugCancelComplete", {'action': settings.onCancelComplete}, function(event, ID, fileObj, data, remove, clearFast) {
                    var item = jQuery("#" + jQuery(this).attr('id') + ID + settings.queueItemClassName);
                    if (event.data.action(event, item,ID, fileObj, data, clearFast) !== false) {
                        if (remove) { 
                            var fadeSpeed = (clearFast == true) ? 0 : 250;
                            jQuery("#" + jQuery(this).attr('id') + ID + "QueueItem").fadeOut(fadeSpeed, function() {jQuery(this).remove() });
                        }
                    }
                });

                jQuery(this).bind("uploadPlugClearQueue", {'action': settings.onClearQueue}, function(event, clearFast) {
                    var queueID = (settings.queueID) ? settings.queueID : jQuery(this).attr('id') + 'Queue';
                    if (clearFast) {
                        jQuery("#" + queueID).find('.QueueItem').remove();
                    }
                    if (event.data.action(event, clearFast) !== false) {
                        jQuery("#" + queueID).find('.QueueItem').each(function() {
                            var index = jQuery('.QueueItem').index(this);
                            jQuery(this).delay(index * 100).fadeOut(250, function() { jQuery(this).remove(); });
                        });
                    }
                });
                
                var errorArray = [];
                jQuery(this).bind("uploadPlugError", {'action': settings.onError}, function(event, ID, fileObj, errorObj) {
                    if (event.data.action(event, ID, fileObj, errorObj) !== false) {
                        var fileArray = new Array(ID, fileObj, errorObj);
                        errorArray.push(fileArray);
                        
                        //showUploadPlugMsg("发生错误:" + errorObj.type);
                        //jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(" - " + errorObj.type + " Error");
                        jQuery("#" + jQuery(this).attr('id') + ID).find('.uploadPlugProgress').hide();
                        //jQuery("#" + jQuery(this).attr('id') + ID).addClass('uploadPlugError');
                    }
                });
                
                if (typeof(settings.onAllComplete) == 'function') {
                    jQuery(this).bind("uploadPlugAllComplete", {'action': settings.onAllComplete}, function(event, data) {
                        if (event.data.action(event, data) !== false) {
                            errorArray = [];
                        }
                    });
                }
            });
        },
        
        uploadPlugSettings:function(settingName, settingValue, resetObject) {
            var returnValue = false;
            jQuery(this).each(function() {
                
                if (settingName == 'uploadParam' && settingValue != null) {
                    if (resetObject) {
                        var uploadParam = settingValue;
                    } else {
                        var uploadParam = jQuery.extend(jQuery(this).data('settings').uploadParam, settingValue);
                    }
                    settingValue = parseUrlParam(uploadParam);
                }
                
                if (settingName == 'checkExistParam' && settingValue != null) {
                    if (resetObject) {
                        var checkExistParam = settingValue;
                    } else {
                        var checkExistParam = jQuery.extend(jQuery(this).data('settings').checkExistParam, settingValue);
                    }
                    settingValue = parseUrlParam(checkExistParam);
                }
                
                returnValue = document.getElementById(jQuery(this).attr('id') + 'Uploader').updateSettings(settingName, settingValue);
            });
            if (settingValue == null) {
                if (settingName == 'uploadParam') {
                    returnValue = unParseUrlParam(returnValue);
                }
                if (settingName == 'checkExistParam') {
                    returnValue = unParseUrlParam(returnValue);
                }
            }
            return returnValue;
        },
        uploadPlugStartUpload:function(ID) {
            document.getElementById(jQuery(this).attr('id') + 'Uploader').startUpload(ID);
        },
        checkRemain:function(){
            var settings = jQuery(this).data('settings');
            if(settings.filesQueueIds.length <= 0)return 0; //未选择任何文件
            var sumSize = 0;
            for(var i = 0; i < settings.filesQueueIds.length; i++){
                var o = settings.filesQueue[settings.filesQueueIds[i]];
                if(o != null) sumSize += o.size;
                if(o.size>settings.sizeLimit)return -2; //单个文件大小超出
                if(o.size == 0)return -3; //上传空文件
            }
            if(sumSize > settings.sizeLimit){
                return -1; //文件总大小超出
            }
            return 99; //正确
        },
        uploadPlugStartCancel:function(ID){
            document.getElementById(jQuery(this).attr('id') + 'Uploader').startCancelUpload(ID, true, true, false);
        },
        uploadPlugCancelUpload:function(ID) {
            
            var settings = jQuery(this).data('settings');
            settings.filesQueue[ID] = null; 
            var arr = new Array(0),j = 0; 
            for(var i = 0; i < settings.filesQueueIds.length; i++){
                if(ID != settings.filesQueueIds[i]){arr[j++] = settings.filesQueueIds[i];}
            } 
            settings.filesQueueIds = arr;
            
            //jQuery(this).each(function() {
                document.getElementById(jQuery(this).attr('id') + 'Uploader').cancelUpload(ID, true, true, false);
            //});
        },
        uploadPlugClearQueue:function() {
            var settings = jQuery(this).data('settings');
            settings.filesQueue = {};
            settings.filesQueueIds = new Array(0);
            jQuery(this).each(function() {
                document.getElementById(jQuery(this).attr('id') + 'Uploader').clearFileUploadQueue(false);
            });
        },
        setFastUploadFlag:function(ID,flag){
            document.getElementById(jQuery(this).attr('id') + 'Uploader').setFastUploadFlag(ID,flag);
        },
        setUploadValidationFlag:function(ID,flag){
            document.getElementById(jQuery(this).attr('id') + 'Uploader').setUploadValidationFlag(ID,flag);
        },
        setCancelValidationFlag:function(ID,flag){
            document.getElementById(jQuery(this).attr('id') + 'Uploader').setCancelValidationFlag(ID,flag);
        }
        /*
        setUploadCompleteFlag:function(ID,flag){
            document.getElementById(jQuery(this).attr('id') + 'Uploader').setUploadCompleteFlag(ID,flag);
        }
        */
    })
})(jQuery);



/**
* 单位换算
*/
function convertSize (size) {
     if(!size) {
     return '0 Bytes';
     }
     var sizeNames = [' B', ' KB', ' MB', ' GB', ' TB', ' PB', ' EB', ' ZB', ' YB'];
     var i = Math.floor(Math.log(size)/Math.log(1024));
     return (size/Math.pow(1024, Math.floor(i))).toFixed(2) + ' ' + sizeNames[i];
}
/**
* 将参数json对象转换为字符串
*/
function parseUrlParam (paramObj){
    var paramString = '';
    for (var name in paramObj) {
        paramString += '&' + name + '=' + paramObj[name];
    }
    return escape(paramString.substr(1));
}

/**
* 将参数字符串转换为json对象
*/
function unParseUrlParam (paramString){
    var returnSplit = unescape(paramString).split('&');
    var returnObj   = new Object();
    for (var n = 0; n < returnSplit.length; n ++) {
        var iSplit = returnSplit[n].split('=');
        returnObj[iSplit[0]] = iSplit[1];
    }
    return returnObj;
}


function subStringByteLength(b, max) {
	if (typeof b == "undefined")
		return 0;
	if (max > 0) {
		var returnValue = '', byteValLen = 0;
		b = b.split("");
		for (var i = 0; i < b.length; i++) {
			if ((byteValLen += (b[i].match(/[^\x00-\x80]/g) != null) ? 2 : 1) > max)
				break;
			returnValue += b[i]
		}
		return returnValue
	}
	var a = b.match(/[^\x00-\x80]/g);
	return (b.length + (!a ? 0 : a.length))
}