
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>框架页</title>
<meta name="description" content="blank page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="<%=basePath%>assets/img/favicon.png"
	type="image/x-icon">

<!--Basic Styles-->
<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link id="bootstrap-rtl-link" href="" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/css/weather-icons.min.css"
	rel="stylesheet" type="text/css" />

<!--Fonts-->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300"
	rel="stylesheet" type="text/css">

<!--Beyond styles-->
<link id="beyond-link" href="<%=basePath%>assets/css/beyond.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>>assets/css/demo.min.css" rel="stylesheet" />
<link href="<%=basePath%>assets/css/typicons.min.css" rel="stylesheet" />
<link href="<%=basePath%>assets/css/animate.min.css" rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />




<!--Basic Scripts-->
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assets/js/slimscroll/jquery.slimscroll.min.js"></script>

<!--Beyond Scripts-->
<script src="<%=basePath%>assets/js/beyond.min.js"></script>

<!--Page Related Scripts-->



<!--Upload-->
<link href="<%=basePath%>assets/css/upload/fileinput.css" media="all"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>assets/js/upload/fileinput.js"
	type="text/javascript"></script>
<script src="<%=basePath%>assets/js/upload/fileinput_locale_zh.js"
	type="text/javascript"></script>

</head>

<body>

	<%-- <label class="control-label">Select File</label>
<input id="input-1" type="file" class="file" class="file-loading" >


<form>
  <div class="form-group">
 <input id="file-1" name="file-1[]" type="file" multiple class="file-loading" data-overwrite-initial="false" data-min-file-count="2">
 </div>

</form>

<script type="text/javascript">
  
    $("#file-1").fileinput({
    	 language: 'zh', //设置语言  
        uploadUrl: 'G:\Books', // you must set a valid URL here else you will get an error//上传的地址or//上传到后台处理的方法
        uploadAsync: false, //设置同步，异步 （同步）  
        allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀,
        overwriteInitial: false, //不覆盖已存在的图片  
        maxFileSize: 1000,
        maxFilesNum: 3,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
	});
   
</script>



				


--------------------------------------------------------------






<form>

   
    <div class="modal-body">
                  
       <input id="file-2" name="file-2" type="file" multiple class="file-loading" />
        
    </div>
</form>






<script type="text/javascript">
  
$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("file-2", "<%=basePath%>demo/upload.htm");
});
//初始化fileinput
var FileInput = function () {
    var oFile = new Object();
    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
    var control = $('#' + ctrlName); 
    //初始化上传控件的样式
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        showUpload: true, //是否显示上传按钮
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        //dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    });

    //导入文件上传完成之后的事件
    $("#file-2").on("fileuploaded", function (event, data, previewId, index) {
        $("#myModal").modal("hide");
        var data = data.response.lstOrderImport;
        if (data == undefined) {
            toastr.error('文件格式类型不正确');
            return;
        }
        //1.初始化表格
        var oTable = new TableInit();
        oTable.Init(data);
        $("#div_startimport").show();
    });
}
    return oFile;
};
</script> --%>

	-------------------------------------------------------------3333333333333


	<form enctype="multipart/form-data">
		<input id="file-0a" class="file" name="file" type="file" multiple
			data-min-file-count="1"> <br>
	</form>


	<script>
    $('#file-0a').fileinput({
        language: 'zh',
        uploadUrl: '<%=basePath%>upload.do',
        allowedPreviewTypes : ['image']
    });
    $('#file-0a').on('fileuploaderror', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
        console.log(data);
        console.log('File upload error');
    });
    $('#file-0a').on('fileerror', function(event, data) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
    });
    $('#file-0a').on('fileuploaded', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
        console.log('File uploaded triggered');
    });
</script>


	--------------------------------------------------------
	<form enctype="multipart/form-data" method="post">
		<input id="file-1" class="file" type="file" > <br>
	</form>

	<script>  
      
    $("#file-1").fileinput({   
        language: 'zh',  
        uploadUrl: '<%=basePath%>upload.do', // you must set a valid URL here else you will get an error  
        allowedFileExtensions : ['xls','jpg', 'png','gif'],  
        maxFileCount: 3,   //同时最多上传3个文件  
        //allowedFileTypes: ['image', 'video', 'flash'],  这是允许的文件类型 跟上面的后缀名还不是一回事  
        //这是文件名替换  
    slugCallback: function(filename) {  
            return filename.replace('(', '_').replace(']', '_');  
        }  
    });   
          //这是提交完成后的回调函数    
     $("#file-1").on("fileuploaded", function (event, data, previewId, index) {  
         top.location.href="processor.jsp";  
     });  
</script>


</body>
</html>