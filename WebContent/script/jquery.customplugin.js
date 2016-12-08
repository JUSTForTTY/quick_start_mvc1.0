(function($) {
	var tooltipWidth = 16; 
	
	/*
	 * 提示框插件 需配合jquery ui
	 * 
	 * 万利
	 * 
	 * 2013/2/22 
	 ******/
	jQuery.extend(jQuery, {
		  // jQuery UI alert弹出提示
		  jqalert: function(text, title, fn) {
		    var html =
		    '<div class="dialog" id="dialog-message">' +
		    '  <p>' +
		    '    <span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 0 0;"></span>' + text +
		    '  </p>' +
		    '</div>';
		    return $(html).dialog({
		      //autoOpen: false,
		      resizable: false,
		      modal: true,
		      show: {
		        effect: 'fade',
		        duration: 300
		      },
		      title: title || "提示信息",
		      buttons: {
		        "确定": function() {
		          var dlg = $(this).dialog("close");
		          fn && fn.call(dlg);
		        }
		      }      
		    });
		  },
		  // jQuery UI alert弹出提示,一定间隔之后自动关闭
		  jqtimeralert: function(text, title, fn, timerMax) {
		    var dd = $(
		    '<div class="dialog" id="dialog-message">' +
		    '  <p>' +
		    '    <span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 0 0;"></span>' + text +
		    '  </p>' +
		    '</div>');
		    dd[0].timerMax = timerMax || 3;
		    return dd.dialog({
		      //autoOpen: false,
		      resizable: false,
		      modal: true,
		      show: {
		        effect: 'fade',
		        duration: 300
		      },
		      open: function(e, ui) {
		        var me = this,
		          dlg = $(this),
		          //btn = dlg.parent().find(".ui-button-text").text("确定(" + me.timerMax + ")");
		          btn = dlg.parent().find("button").text("确定(" + me.timerMax + "s)");
		        --me.timerMax;
		        me.timer = window.setInterval(function() {
		          btn.text("确定(" + me.timerMax + "s)");
		          if (me.timerMax-- <= 0) {
		            dlg.dialog("close");
		            fn && fn.call(dlg);
		            window.clearInterval(me.timer); // 时间到了清除计时器
		          }
		        }, 1000);
		      },
		      title: title || "提示信息",
		      buttons: {
		        "确定": function() {
		          var dlg = $(this).dialog("close");
		          fn && fn.call(dlg);
		          window.clearInterval(this.timer); // 清除计时器
		        }
		      },
		      close: function() {
		        window.clearInterval(this.timer); // 清除计时器
				$(this).dialog("destroy"); // 关闭时销毁
		      }
		    });
		  },
		  // jQuery UI confirm弹出确认提示
		  jqconfirm: function(text, title, fn1, fn2) {
		    var html =
		    '<div class="dialog" id="dialog-confirm">' +
		    '  <p>' +
		    '    <span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>' + text +
		    '  </p>' +
		    '</div>';
		    return $(html).dialog({
		      //autoOpen: false,
		      resizable: false,
		      modal: true,
		      show: {
		        effect: 'fade',
		        duration: 300
		      },
		      title: title || "提示信息",
		      buttons: {
		        "确定": function() {
		          var dlg = $(this).dialog("close");
		          fn1 && fn1.call(dlg, true);
		        },
		        "取消": function() {
		          var dlg = $(this).dialog("close");
		          fn2 && fn2(dlg, false);
		        }
		      }
		    });
		  },
		  // jQuery UI 弹出iframe窗口
		  jqopen: function(url, options) {
		    var html =
		    '<div class="dialog" id="dialog-window" title="提示信息">' +
		    ' <iframe src="' + url + '" frameBorder="0" style="border: 0; " scrolling="auto" width="100%" height="100%"></iframe>' +
		    '</div>';
		    return $(html).dialog($.extend({
		      modal: true,
		      closeOnEscape: false,
		      draggable: false,
		      resizable: false,
		      close: function(event, ui) {
		        $(this).dialog("destroy"); // 关闭时销毁
		      }
		    }, options));
		  },
		  // jQuery UI confirm提示
		  confirm: function(evt, text, title) {
		    evt = $.event.fix(evt);
		    var me = evt.target;
		    if (me.confirmResult) {
		      me.confirmResult = false;
		      return true;
		    }
		    jQuery.jqconfirm(text, title, function(e) {
		      me.confirmResult = true;
		      if (e) {
		        if (me.href && $.trim(me.href).indexOf("javascript:") == 0) {
		          $.globalEval(me.href);
		          me.confirmResult = false;
		          return;
		        }
		        var t = me.type && me.type.toLowerCase();
		        if (t && me.name && (t == "image" || t == "submit" || t == "button")) {
		          __doPostBack(me.name, "");
		          me.confirmResult = false;
		          return;
		        }
		        if (me.click) me.click(evt);
		      }
		      return false;
		    });
		    return false;
		  }
		});
	
	$.fn
			.extend({
				/*
				 * table编辑控件
				 * 
				 * 
				 */
				flexEditRow : function(options) {
					/*
					 * //定义默认值 var defaults = { template : null, target : null,
					 * colAttr : "", added : $.noop, edited : $.noop };
					 * 
					 * //合并覆盖默认值 var opts = $.extend({}, defaults, options);
					 * 
					 * //克隆行 if (opts.template) tr = opts.template.clone();
					 * //$.data(); if (opts.target)
					 * opts.target.find("tbody").append(tr);
					 */

					// 定义默认值
					var defaults = {
						addId : "add",
						deleteId : "delete",
						editId : "edit",
						chkAllId : "chkAll",
						template : $(this).find("[t=template]"),
						target : $(this),
						colName : "name",
						added : $.noop,
						saved : $.noop,
						deleted : $.noop,
						beforeCancel : $.noop,
						initData : null,
						showEmptyMsg : true
					};
										
					//创建一个提示对象
					//$('body').append('<div class="tooltipshowpanel4table"></div>');
			        //$(document).mouseover(function(){$('.tooltipshowpanel').hide();});
					
					// 改变按钮显示隐藏
					var changeStatus = function(currentTr, status) {
						switch (status) {
						case "default":
							$("[t=rowEdit]", currentTr).show();
							$("[t=rowSave]", currentTr).hide();
							$("[t=rowCancel]", currentTr).hide();
							disabledEle(currentTr);
							break;
						case "add":
							$("[t=rowEdit]", currentTr).hide();
							$("[t=rowSave]", currentTr).show();
							$("[t=rowCancel]", currentTr).show();
							enabledEle(currentTr);
							break;
						case "edit":
							$("[t=rowEdit]", currentTr).hide();
							$("[t=rowSave]", currentTr).show();
							$("[t=rowCancel]", currentTr).hide();
							enabledEle(currentTr);
							break;
						default:
							break;
						}
					};
					
					//选择性禁用行内容
					var disabledEle = function(tr){
						$("[item][chkItem!=1]", tr).each(function(){
							//attr("disabled","disabled");
							
							if($(this).is("span,:text")){
								$(this).attr("readonly","readonly");
							}
							else{
								$(this).attr("disabled","disabled");
							}
						});
						//文本框只读样式
						tr.find(":text").removeClass("inputEdit").addClass("inputRead");
					};
					
					//选择性禁用行内容
					var enabledEle = function(tr){
						$("[item][chkItem!=1]", tr).removeAttr("disabled").removeAttr("readOnly");
						//文本框可编辑样式
						tr.find(":text").removeClass("inputRead").addClass("inputEdit");
					};
					
					// 合并覆盖默认值
					var opts = $.extend({}, defaults, options);
					
					// 克隆模板行
					if (opts.template)
						tr = opts.template.clone().show();
					opts.template.remove();
					
					// 存放
					opts.target.data("template", tr);

					//重置行号
					var itemNum = function(){
						var currentNum = 0;
						if(typeof(opts.param) != "undefined"){
							currentNum = (opts.param.page - 1) * opts.param.rp;
						} 
						opts.target.find("[itemNum]").each(function(i){
							$(this).text(currentNum + i + 1);
						});
					};
					
					this.resetItemNum = function(param){
						if(typeof(param) != "undefined"){
							opts.param = param;
						}
						itemNum();
					};

					var rowHL = function() {
						opts.target.find(".trSelected").removeClass("trSelected").addClass("hidden");
						$(this).removeClass("hidden").addClass("trSelected");
					};
					
					// 添加行
					var addRow = function(rowData) {
						//var newTr = $.data(opts.target, "template").clone();
						var newTr = opts.target.data("template").clone();
						//行高设置为34px
						newTr.height(34);
						//判断页面里是否有文本框，加上上边距
						//if(newTr.has(":text").length > 0){
							
							newTr.find("a,span").closest("td").addClass("verticalshow");
							//ie8
							if($.browser.msie && $.browser.version.substr(0,1) == "8"){
								newTr.find(":text,select").closest("td").css("padding-top","2px");
							}							
						//}
						
						//点击行，加上样式
						newTr.click(rowHL);
						
						//给与文本框样式
						newTr.find(":text").addClass("inputEdit");
						
						// 禁用单选
						newTr.find("[chkItem=1]").attr("disabled", "disabled");

						// 行取消按钮
						newTr.find("[t=rowCancel]").bind("click", function() {
							$(this).closest("tr").remove();
							itemNum();
						});

						// 行保存按钮
						newTr.find("[t=rowSave]").bind("click",function() {
											/*
											 * var returnValue = $("[item]",
											 * newTr).attr( "disabled",
											 * "disabled").map( function() {
											 * return $(this).attr("name") +
											 * ":'" + $(this).val() + "'";
											 * }).get().join(','); returnValue =
											 * "returnValue={" + returnValue +
											 * "}";
											 */
											var isSubmit = true;
											// 取得值
											var returnValue = {};
											$("[item]", newTr).each(function() {
																/*var thisReg = new RegExp($(this).attr('reg'));
											                    if(!thisReg.test(this.value))
											                    {
$(this).removeClass('tooltipinputok').addClass('tooltipinputerr');
											                        isSubmit = false;
											                    }*/
														var rule = $.Validation.getRule($(this).attr('reg'));
													    if (rule&&!rule.check(this.value)) {
											                        $(this).removeClass('inputEdit tooltipinputok').addClass('tooltipinputerr');
											                        isSubmit = false;
											            }
																returnValue[$(
																		this)
																		.attr(
																				opts.colName)] = $(
																		this)
																		.val();
															});
											if(!isSubmit){
												return;
											}
											
											// 保存事件
											if (opts.saved) {
												
												// 是否通过验证
												if (!opts.saved(newTr,
														returnValue)) {
													return;
												}
											}
											changeStatus(newTr, "default");
											// 启用单选
											newTr.find("[chkItem=1]")
													.removeAttr("disabled",
															"disabled");
										});

						// 编辑(修改数据)
						newTr.find("[t=rowEdit]").bind("click", function() {
							//$("[class^=width]", newTr).removeAttr("disabled");
							changeStatus(newTr, "edit");
						});						
						
						if (opts.target)
							opts.target.find("tbody").append(newTr);itemNum();
						if (opts.added)
							opts.added(newTr,rowData);

						return newTr;
					};
					// 添加
					$("#" + opts.addId).bind("click", addRow);

					// 删除
					var deleteRow = function() {
						//checkbox的value的计划
						var returnValue = new Array();
						//行的集合
						var deleteRows = new Array();
						//
						var returnObjects = new Array();
						var chk = opts.target.find("[item][chkItem=1]:checked");
						chk.each(function(i) {
							if ($(this).val() != null) {
								returnValue[i] = $(this).val();
								deleteRows[i] = $(this).closest("tr");
								var returnObject = {};
								$("[item]", $(this).closest("tr")).each(function() {
									var rule = $.Validation.getRule($(this).attr('reg'));
								    if (rule&&!rule.check(this.value)) {
						                        $(this).removeClass('inputEdit tooltipinputok').addClass('tooltipinputerr');
						                        isSubmit = false;
						            }
								    returnObject[$(
													this)
													.attr(
															opts.colName)] = $(
													this)
													.val();
								});
								returnObjects[i] = returnObject;
							}
						});						
						if (returnValue.length > 0
								&& confirm("确定要删除吗？删除的数据无法恢复！")) {
							chk.closest("tr").remove();itemNum();
							if (opts.deleted)
								opts.deleted(returnValue,returnObjects,deleteRows);
						}
					};
					$("#" + opts.deleteId).bind("click", deleteRow);

					// 编辑
					var editRows = function() {
						var chks = opts.target
								.find("[item][chkItem=1]:checked");
						chks.each(function() {
							var tr = $(this).closest("tr");
							//$("[class^=width]", tr).removeAttr("disabled");
							changeStatus(tr, "edit");
						});
					};
					$("#" + opts.editId).bind("click", editRows);

					// 全选与单选
					var allIds = "";
					// 单选
					var selectOne = function() {
						var selectIds = "";
						var allChecked = true;
						var chkItems = $("[chkItem=1]");

						chkItems.each(function() {
							if (!$(this).attr("checked")
									&& !$(this).attr("disabled")) {
								allChecked = false;
							}
						});
						$("#" + opts.chkAllId).attr("checked", allChecked);
						allIds = chkItems.map(
								function() {
									if ($(this).attr("checked")
											&& !$(this).attr("disabled")
											&& $(this).val() != "") {
										return $(this).val();
									}
								}).get().join(",");
					};
					$("[chkItem=1]", opts.target).live("click", selectOne);
					// 全选
					var selectAll = function() {
						var obj = $(this);
						var selectIds = "";
						var chkItems = $("[chkItem=1]", opts.target);
						allIds = chkItems.map(
								function() {
									if (!$(this).attr("disabled")
											&& $(this).val() != "") {
										if (obj.attr("checked")) {
											$(this).attr("checked", true);
										} else {
											$(this).attr("checked", false);
										}
										return $(this).val();
									}
								}).get().join(",");
					};
					$("#" + opts.chkAllId).live("click", selectAll);

					// 初始化数据
					this.addData = function(list,trCallback,callback ) {
						//无数据时给予提示
						//$.jqtimeralert("这是自动关闭的提示！", "自动关闭提示");				
						if(opts.showEmptyMsg&&(list == null || typeof(list) == "undefined" || list.length < 1)){
							$.jqtimeralert("找不到数据！", "提示", function() {}, 2);
						}
						
						for ( var i in list) {
							var newTr = addRow(list[i]);
							newTr.find("[item]").each(
									function() {
										var $this = $(this) ;
										//显示数据，根据类型不同进行赋值
										var _val  = list[i] ;
										var _propKey = $this.attr(opts.colName) ;
										var _propVal  = _val[_propKey] ;
										
										if(_propVal == null || _propVal == undefined) _propVal = "";
										if($this.is("input")){
											$this.val(_propVal);
										}
										else if($this.is("span")){
											$this.text(_propVal) ;
										}
										else if($this.is("select")){
											$this.prop("disabled", false).val(_propVal).prop("disabled", true);
										}
									});

							if(trCallback)
								trCallback.call(opts.target,newTr,list[i]);
							// 禁用文本框
							newTr.find("[chkItem=1]").removeAttr("disabled",
									"disabled");
							// 启用单选
							/*$("[item][chkItem!=1]", newTr).attr("disabled",
									"disabled");*/							
							disabledEle(newTr);
							
							changeStatus(newTr, "default");
						}
						
						if(callback){
							callback.call(opts.target);
						}
					};
					if (opts.initData) {
						
						this.addData(opts.initData);
					}
					
					//清除数据
					this.clearData = function(){
						opts.target.find("tbody").children().remove();
					};								
					
					//高亮某一行 
					this.highLight = function(targetTr){
						opts.target.find(".trSelected").removeClass("trSelected").addClass("hidden");
						targetTr.removeClass("hidden").addClass("trSelected");
					};
					
					return this;
				},
				flexAdd:function(options){
					var defaults = {
						selector:"input",
						addId : "add",
						deleteId : "delete",
						chkAllId : "chkAll",
						template : $(this).find("[t=template]"),
						target : $(this),
						colName : "name",
						saved : $.noop,
						deleted : $.noop,
						added : $.noop,
						addDataed : $.noop,
						initData : null

					};
					var opts = $.extend({}, defaults, options);
										
					
					// 克隆模板行
					if (opts.template)
						tr = opts.template.clone().show();
					opts.template.remove();
					// 存放
					//$.data(opts.target, "template", tr);
					opts.target.data("template", tr);
					
					//重置行号
					var itemNum = function(){
						opts.target.find("[itemNum]").each(function(i){
							$(this).text(i + 1);
						});
					};
					
					var rowHL = function() {
						opts.target.find(".trSelected").removeClass("trSelected").addClass("hidden");
						$(this).removeClass("hidden").addClass("trSelected");
					};
					
					this.resetItemNum = function(){
						itemNum();
					};
					
					var addRowFunc = function(event) {
						// 回车键
						if (event.which == 13) {
							addRow();
						}
					};
					
					// 添加行
					var addRow = function() { 
						//var newTr = $.data(opts.target, "template").clone();
						var newTr = opts.target.data("template").clone();
						
						//点击行，加上样式
						newTr.click(rowHL);
						
						if (opts.target)
							opts.target.find("tbody").append(newTr);
						
						if (opts.added)
							opts.added(newTr);
						
						//更新页面序号
						itemNum();
						
						$(opts.selector + "[item]",opts.target.find("tbody")).unbind("keydown",addRowFunc);
						//绑定回车新增一行事件
						$(opts.selector + ":last",newTr).bind("keydown",addRowFunc);						
						
						return newTr;
					};
						//表中所有checkbox的选择，与不选择
					$(".choseAll").click(function(){
						if($(this).attr("checked")){
						   $(".cho").attr("checked","checked");
						    editVal();     
						}else{ 
							$(".cho").removeAttr("checked");   
						}
					});
					
					//默认添加一行数据
					addRow();
					
					$("#"+opts.addId).bind("click",addRow);
					
					// 删除
					var deleteRow = function() {
						var returnValue = new Array();

						var chk = opts.target.find("[item][chkItem=1]:checked");
						chk.each(function(i) {
							if ($(this).val() != null) {
								returnValue[i] = $(this).val();
							}
						});

						if (returnValue.length > 0
								&& confirm("确定要删除吗？删除的数据无法恢复！")) {
							chk.closest("tr").remove();itemNum();
							if (opts.deleted)
								opts.deleted(returnValue);
						}
					};
					$("#" + opts.deleteId).bind("click", deleteRow);
					
					// 初始化数据
					this.addData = function(list,trcallback,callback) {
						var newTrs = new Array();
						
						for ( var i in list) {
							var newTr = addRow();
							newTr.find("[item]").each(
									function() {
										$(this).val(
												list[i][$(this).attr(
														opts.colName)]);
									});	
							newTrs[i] = newTr;
							
							//添加数据后，每行的回调函数
							if(trcallback){
								trcallback.call(opts.target,newTr,list[i]) ;
							}
						}
						
						if(callback){
							callback.call(opts.target);
						}
						
						if(opts.addDataed){
							opts.addDataed(newTrs);
						}
					};
					if (opts.initData) {
						this.addData(opts.initData);
					}

					//清除数据
					this.clearData = function(){
						opts.target.find("tbody").children().remove();
					};								

					
					return this;

				},
				/*
				 * 按回车键光标在div内的容器循环获得焦点
				 * 
				 * 
				 */
				tabControl : function(options) {
					var defaults = {
						selector : "input"
					};
					var opts = $.extend({}, defaults, options);
					var container = this;

					//最外层大容器的索引
					var selectDivIndex = 0;
					//容器内控件（input select）的计划
					var controls = $(opts.selector, container
							.eq(selectDivIndex));
					//控件的索引
					var index = 0;
					//控件数
					var len = controls.length;
					// var selectDiv = controls.eq(selectDivIndex);

					//定义容器整体元素数量及索引

					var setIndex = function (){
						selectDivIndex = $(this).closest(
								container.selector).index(container.selector);
						$("#selectDivIndex").text(selectDivIndex);
						controls = $(opts.selector, container
								.eq(selectDivIndex));
						index = $(this).index(
								container.selector + ":eq("
										+ selectDivIndex + ") "
										+ opts.selector);
						$("#index").text(index);
						len = controls.length;
						$("#len").text(len);
						// 控制索引位置
						index++;
						if (index > len - 1)
							index = 0;
					};
					
					var keydownFunc = function(event) {
						// 回车键
						if (event.which == 13) {
							controls.eq(index).focus();
						}
					};
					
					// 获取文本框所在div
					$(opts.selector, container).unbind("focus",setIndex).unbind("keydown",keydownFunc)
					.bind("focus",setIndex).bind("keydown",keydownFunc);
					
					this.resetTabControl = function(){
						//获取文本框所在div
						$(opts.selector, container).unbind("focus",setIndex).unbind("keydown",keydownFunc)
						.bind("focus",setIndex).bind("keydown",keydownFunc);
					};
					
					return this;
				}
			});
	/*
	 * 表单验证
	 * 
	 * 
	 * 
	 */
	// The validation type (class).
	// We adds validation rules for email, required field, password and url.
	var Validation = function() {

		var rules = {

			email : {
				check : function(value) {

					if (value)
						return isValidatedPattern(value,
								/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
					return false;
				},
				msg : "请输入一个正确的mail地址！"
			},
			url : {
				check : function(value) {

					if (value)
						return isValidatedPattern(value,
								/^https?:\/\/(.+\.)+.{2,4}(\/.*)?$/);
					return true;
				},
				msg : "请输入一个正确的URL地址！"
			},
			password : {
				check : function(value) {
					if (value.length < 8 || value.length > 20) {
						return false;
					} else {
						// Check the password strength enough.
						return isValidatedPattern(value,
								/(?=[A-Za-z0-9]{8,20})(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+/);
					}
				},
				msg : "密码必须是8-20位的数字和字母！"
			},
			number : {
				check : function(value){
					if(value)
						return isValidatedPattern(value,
								/^[0-9]+$/ );
						return true;
					},
				msg : "只能输入数字"
			},
			numberLetter : {
				check : function(value){
					if(value)
						return isValidatedPattern(value,
								/^[A-Za-z0-9]+$/ );
						return  true;
				},
				msg : "只包含数字和英文字母"
			},
			required : {
				check : function(value) {

					if (value)
						return true;
					else
						return false;
				},
				msg : "此值必填!"
			}
		};
		var isValidatedPattern = function(value, pattern) {

			var regex = pattern;
			var match = regex.exec(value);
			return match;
		};

		// Returns a publish method, then the user can custom validation rule.
		return {
			// The user can add their custom rule.
			addRule : function(name, rule) {

				rules[name] = rule;
			},
			getRule : function(name) {
				
				if(typeof rules[name] != "undefined"){
					return rules[name];
				}
				else{
					//未定义的验证规则使用自定义验证的方式
					var thisReg = {
							check : function(value){
								 var reg = new RegExp(name);
								 if (reg.test(value)) {
									 return true;
								 }
								 else
								 {
									 return false;
								 }
							}
					};
					
					return thisReg;
				}
			}
		};
	};

	// The form object.
	var Form = function(form) {

		var fields = [];

		// Find the field has the validation attribute.
		form.find("[validation]").each(function() {
			var field = $(this);
			if (field.attr('validation') !== undefined) {
				fields.push(new Field(field));
			}
		});
		this.fields = fields;
	};

	// The prototype of Form.
	Form.prototype = {

		// Validates all the fields in the form object.
		validate : function() {

			for (field in this.fields) {

				this.fields[field].validate();
			}
		},

		// If the field invaild, focus on it.
		isValid : function() {

			for (field in this.fields) {

				if (!this.fields[field].valid) {

					this.fields[field].field.focus();
					return false;
				}
			}
			return true;
		}
	};

	// The Field type.
	var Field = function(field) {

		this.field = field;
		this.valid = false;
		this.attach("change");
	};

	// The prototype of Field type.
	Field.prototype = {

		// Public method.
		attach : function(event) {

			// The object refers to Field object.
			var obj = this;

			// When the field changed, then invoked the validate method.
			if (event == "change") {
				obj.field.bind("change", function() {
					return obj.validate();
				});
			}

			// When Key up, then invoked the validate method.
			if (event == "keyup") {
				obj.field.bind("keyup", function() {
					return obj.validate();
				});
			}
		},

		// Public method.
		validate : function() {

			var obj = this, field = obj.field, errorClass = "errorlist", errorlist = $(
					document.createElement("ul")).addClass(errorClass),

			// We can splits the validation attribute with space.
			// Gets all validation types.
			types = field.attr("validation").split(" "),

			// Gets the fieldgroud object.
			container = field.parent(), errors = [];

			// If there is an errorlist already present
			// remove it before performing additional validation
			field.next(".errorlist").remove();

			for ( var type in types) {

				var rule = $.Validation.getRule(types[type]);

				// If invalid displays the error msg.
				if (!rule.check(field.val())) {

					container.addClass("error");
					errors.push(rule.msg);
				}
			}
			if (errors.length) {

				// Unbinds the keyup event added before.
				obj.field.unbind("keyup");

				// Attaches the keyup event.
				obj.attach("keyup");
				field.after(errorlist.empty());

				// Displays the error msg.
				for (error in errors) {

					errorlist.append("<li>" + errors[error] + "</li>");
				}
				obj.valid = false;
			} else {
				errorlist.remove();
				container.removeClass("error");
				obj.valid = true;
			}
		}
	};

	// Extends jQuery prototype with validation and validate methods.
	$.extend($.fn, {

		validation : function() {

			// Creates a Form instance.
			var validator = new Form($(this));

			// Stores the Form instance in Key/Value collection.
			$.data($(this)[0], 'validator', validator);

			// Binds the submit event.
			$(this).find("[name=submit]").bind("click", function(e) {
				validator.validate();
				if (!validator.isValid()) {
					e.preventDefault();
				}
			});
		},

		// Checks the field is validated or not.
		validate : function() {

			var validator = $.data($(this)[0], 'validator');
			validator.validate();
			return validator.isValid();

		}
	});

	// Creates instance of our object in the jQuery namespace.
	$.Validation = new Validation();

	/*
	 * 表单验证控件2，tooltip;
	 * 
	 * 
	 */
	$.extend({
		tooltip:function(options){
			var defaults = {
					onsubmit : true,
					buttonID : "submit",
					validated : $.noop,
					selector : "select[reg],input[tip],input[reg],textarea[tip],textarea[reg]",
					buttonObj : $("#sumbit")
			};
			var opts = $.extend({}, defaults, options);
			$('body').append('<div class="tooltipshowpanel"></div>');
			
			var selectorStr = "";
			if(opts.formID){
				selectorStr += "#"+opts.formID + " ";				
			}
			selectorStr += opts.selector;
			
			$(selectorStr).live({
				mouseover: function() {
					if ($(this).attr('tip') != undefined) {
						$('.tooltipshowpanel').css({
							left : $.getLeft(this) + 'px',
							top : $.getTop(this) + 'px'
						});
						$('.tooltipshowpanel').html($(this).attr('tip'));
						$('.tooltipshowpanel').fadeIn("fast");
					}
				},
				mouseleave:function() {
					if ($(this).attr('tip') != undefined) {
						$('.tooltipshowpanel').hide();
					}
				},
				focus:function() {
					if ($(this).attr('reg') != undefined) {
						$(this).removeClass('tooltipinputerr');
					}
				},
				blur:function() {
					if ($(this).attr('reg') != undefined) {
						/*var thisReg = new RegExp($(this).attr('reg'));
						if (thisReg.test(this.value)) {
							$(this).removeClass('tooltipinputerr')
									.addClass('tooltipinputok');
						} else {
							$(this).removeClass('tooltipinputok').addClass(
									'tooltipinputerr');
}
						if ($(this).attr('toupper') == 'true') {
							this.value = this.value.toUpperCase();
						}*/
						var rule = $.Validation.getRule($(this).attr('reg'));
						if (rule&&rule.check(this.value)) {
	                        $(this).removeClass('inputEdit tooltipinputerr').addClass('tooltipinputok');
	                    }
	                    else
	                    {
	                        $(this).removeClass('inputEdit tooltipinputok').addClass('tooltipinputerr');
						}
						if ($(this).attr('toupper') == 'true') {
							this.value = this.value.toUpperCase();
						}
					}
				}
			});
			
			if (opts.onFormSubmit) {
				$(selectorStr).submit(
						function() {
							var isSubmit = true;
							$(opts.selector).filter('input[reg]').each(
									function() {
										/*var thisReg = new RegExp($(this)
												.attr('reg'));
										if (!thisReg.test(this.value)) {
											$(this).removeClass('tooltipinputok')
													.addClass('tooltipinputerr');
											isSubmit = false;
										}*/
										var rule = $.Validation.getRule($(this).attr('reg'));
										if (rule&&!rule.check(this.value)) {
											$(this).removeClass('inputEdit tooltipinputok').addClass('tooltipinputerr');
											isSubmit = false;
										}
									});
							return isSubmit;
						});
			} else {
				//$("#" + opts.buttonID).bind(
				opts.buttonObj.unbind( "click" ).bind(
						"click",
						function() {
							var isSubmit = true;
							$(opts.selector).filter('input[reg]').each(
									function() {
										/*var thisReg = new RegExp($(this)
												.attr('reg'));
										if (!thisReg.test(this.value)) {
											$(this).removeClass('tooltipinputok')
													.addClass('tooltipinputerr');
											isSubmit = false;
										}*/
										var rule = $.Validation.getRule($(this).attr('reg'));
										if (rule&&!rule.check(this.value)) {
											$(this).removeClass('inputEdit tooltipinputok').addClass('tooltipinputerr');
											isSubmit = false;
										}
									});
							if (opts.validated) {
								opts.validated(isSubmit,$(this));
							}
						});
			}
		}
	});	

	$.extend({
		getWidth : function(object) {
			return object.offsetWidth;
		},

		getLeft : function(object) {
			var go = object;
			var oParent, oLeft = go.offsetLeft;
			while (go.offsetParent != null) {
				oParent = go.offsetParent;
				oLeft += oParent.offsetLeft;
				if(!($.browser.chrome && oParent.tagName == "BODY")){
					oLeft -= oParent.scrollLeft;
				}
				go = oParent;
			}
			return oLeft;
		},

		getTop : function(object) {
			var go = object;
			var oParent, oTop = go.offsetTop;
			while (go.offsetParent != null) {
				oParent = go.offsetParent;
				oTop += oParent.offsetTop;
				//alert("scrollTop:"+oParent.scrollTop);
				if(!($.browser.chrome && oParent.tagName == "BODY")){
					oTop -= oParent.scrollTop;
				}
				go = oParent;
			}
			return oTop + $(object).height() + 5;
		},

		onsubmit : true
	});
})(jQuery);

