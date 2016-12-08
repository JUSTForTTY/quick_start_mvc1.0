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
<script language="javascript" type="text/javascript"
	src='/SafeLease/dwr/interface/dwrAction.js'></script>
<style type="text/css">
.width100p {
	width: 100%;
}
</style>
<script language="javascript" type="text/javascript">
	$(function() {
		//收缩默认后面第一个class为box的div
		$(".boxToggle").spanToggle();

		//表格编辑控件
		var tab1 = $(".tab_s").flexEditRow({
			added : function(newTr) {
				$(newTr).find(".date").click(WdatePicker);
			} ,
			saved : function(newTr, returnValue) {
				var _date = returnValue['date'];

				var getStr = function(i) {
					return _date.split('-')[i];
				};

				var $date = new Date(getStr(0), getStr(1) - 1, getStr(2));
				returnValue['date'] = $date.getTime() ;

				dwrAction.saveDemo(returnValue, {
					async : false,
					callback : function(demo) {
						$("[item][name=id]", newTr).val(demo['id']);
					}
				});

				return true;
			},
			deleted : function(returnValue) {
				dwrAction.deleteDemo(returnValue, function() {
					alert("数据删除成功！");
				});
			}
		});

		//表格显示控件(分页)
		var flexigridTable = $('.tab_s').flexigrid({
			striped : false,
			usepager : true,
			useRp : true,
			rp : 15,
			loadFunc : function(param,gridProp) { 
				var _this = this ;
				dwrAction.getDemoPageList(param, function(page) {
					//更新页面页码
					_this.updateState(page);
					//加载页面数据
					tab1.clearData();
					var data = page['data'];
					for (i = 0; i < data.length; i++) {
						if(data[i]['date'] != null)
							data[i]['date'] = data[i]['date'].format("yyyy-MM-dd");
						tab1.addData([ data[i] ]);
					}
				});
			},
			width : 700,
			height : 200
		});

		//表格显示控件
		$('.tab').flexigrid({
			striped : false,
			usepager : false,
			useRp : false,
			rp : 15,
			width : 700,
			height : 200
		});

		$("#clear").click(function() {
			tab.clearData();
		});

		//表格编辑控件
		var tab = $(".tab").flexEditRow({
			added : function(newTr) {
				$(newTr).find(".date").click(WdatePicker);
			},
			saved : function(newTr, returnValue) {
				var _date = returnValue['date'];

				var getStr = function(i) {
					return _date.split('-')[i];
				};

				var $date = new Date(getStr(0), getStr(1) - 1, getStr(2));
				returnValue['date'] = $date.getTime();

				dwrAction.saveDemo(returnValue, {
					async : false,
					callback : function(demo) {
						$("[item][name=id]", newTr).val(demo['id']);
					}
				});

				return true;
			},
			deleted : function(returnValue) {
				dwrAction.deleteDemo(returnValue, function() {
					alert("数据删除成功！");
				});
			}
		});

		//读取表格数据
		dwrAction.getDemoList({
			Page_Num : ""
		}, function(data) {
			for (i = 0; i < data.length; i++) {
				var _data = data[i];
				var _date = _data['date'];
				_data['date'] = _date.format("yyyy-MM-dd");
				tab.addData([ _data ]);
			}

		});
	});
</script>
</head>

<body>
	<div class="topTag" style="clear:both;">
		<span class="tagLeft"></span> <span class="tagMid">DWR Table控件</span>
		<span class="tagRight"></span> <span class="btnBox"> <span
			class="clear" style="height:1px;">&nbsp;</span> </span>
	</div>
	<span class="clear"></span>
	<span class="boxToggle ">表格操作(有分页)</span>
	<div class="box">
		<button class="add">添加</button>
		<button class="delete">删除</button>
		<!-- 表格控件 -->
		<table class="tab_s" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th width="50"><input type="checkbox" class="checkall" /></th>
					<th width="50">序号</th>
					<th width="50" />
					<th width="100">用户名</th>
					<th width="100">密码</th>
					<th width="100">日期</th>
				</tr>
			</thead>
			<tbody>
				<tr class="hidden" t=template>
					<td><input type="checkbox" item name="id" chkItem="1" value="" />
					</td>
					<td><span itemNum></span></td>
					<td><a t=rowEdit class="hidden" href="#">编辑</a> <a t="rowSave"
						href="#">保存</a> <a t=rowCancel href="#">取消</a>
					</td>
					<td><input class="width1" item name="username" />
					</td>
					<td><input class="width1" item name="password" />
					</td>
					<td><input class="width1 date" item name="date" />
					</td>
				</tr>
			</tbody>
		</table>
		<span class="clear"> </span>
	</div>
	<span class="boxToggle ">表格操作(无分页)</span>
	<div class="box hidden">
		<button id="add">添加</button>
		<button id="delete">删除</button>
		<button id="clear">清空行</button>
		<!-- 表格控件 -->
		<table class="tab" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th width="50"><input type="checkbox" class="checkall" /></th>
					<th width="50">序号</th>
					<th width="50" />
					<th width="100">用户名</th>
					<th width="100">密码</th>
					<th width="100">日期</th>
				</tr>
			</thead>
			<tbody>
				<tr class="hidden" t=template>
					<td><input type="checkbox" item name="id" chkItem="1" value="" />
					</td>
					<td><span itemNum></span></td>
					<td><a t=rowEdit class="hidden" href="#">编辑</a> <a t="rowSave"
						href="#">保存</a> <a t=rowCancel href="#">取消</a>
					</td>
					<td><input class="width1" item name="username" />
					</td>
					<td><input class="width1" item name="password" />
					</td>
					<td><input class="width1 date" item name="date" />
					</td>
				</tr>
			</tbody>
		</table>
		<span class="clear"> </span>
	</div>
	<span class="boxToggle">表格的所有源代码(此处用来调试该表格的样式，不做其他用途)</span>
	<div class="box hidden">
		<div style="width: 700px;" class="flexigrid ie">
			<div class="nBtn" title="Hide/Show Columns">
				<div></div>
			</div>
			<div
				style="top: 0px; width: auto; height: auto; margin-bottom: -200px; display: none;"
				class="nDiv">
				<table cellSpacing="0" cellPadding="0">
					<tbody>
						<tr>
							<td class="ndcol1"><input class="togCol" value="0"
								type="checkbox">
							</td>
							<td class="ndcol2">委托编号</td>
						</tr>
						<tr>
							<td class="ndcol1"><input class="togCol" value="1"
								type="checkbox">
							</td>
							<td class="ndcol2">条形码</td>
						</tr>
						<tr>
							<td class="ndcol1"><input class="togCol" value="2"
								type="checkbox">
							</td>
							<td class="ndcol2">报支单号</td>
						</tr>
						<tr>
							<td class="ndcol1"><input class="togCol" value="3"
								type="checkbox">
							</td>
							<td class="ndcol2">委托客户</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tDiv">
				<div class="tDiv2">
					<div class="fbutton">
						<div>
							<span style="padding-left: 20px;" class="btn">保存</span>
						</div>
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="hDiv">
				<div class="hDivBox">
					<table cellSpacing="0" cellPadding="0">
						<thead>
							<tr>
								<th axis="col0"><div style="width: 100px;">委托编号</div></th>
								<th axis="col1"><div style="width: 100px;">条形码</div></th>
								<th axis="col2"><div style="width: 100px;">报支单号</div></th>
								<th axis="col3"><div style="width: 100px;">委托客户</div></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div style="top: 1px;" class="cDrag">
				<div style="height: 200px; display: none;"></div>
				<div style="height: 200px; display: none;"></div>
				<div style="height: 200px; display: none;"></div>
				<div style="height: 200px; display: none;"></div>
			</div>
			<div style="height: 200px;" class="bDiv">
				<table style="border-collapse: collapse;" border="0" cellSpacing="0"
					cellPadding="0">

					<tbody>
						<tr>
							<td><div style="width: 100px;">
									<input class="width100p" />
								</div></td>
							<td><div style="width: 100px;">
									<input class="width100p" />
								</div></td>
							<td><div style="width: 100px;">
									<input class="width100p" />
								</div></td>
							<td><div style="width: 100px;">
									<input class="width100p" value="费用结算客户" />
								</div></td>
						</tr>
					</tbody>
				</table>
				<div style="display: none;" class="iDiv"></div>
			</div>
			<div class="pDiv">
				<div class="pDiv2">
					<div class="pGroup">
						<select name="rp"><option value="10">10&nbsp;&nbsp;</option>
							<option selected="selected" value="15">15&nbsp;&nbsp;</option>
							<option value="20">20&nbsp;&nbsp;</option>
							<option value="30">30&nbsp;&nbsp;</option>
							<option value="50">50&nbsp;&nbsp;</option>
						</select>
					</div>
					<div class="btnseparator"></div>
					<div class="pGroup">
						<div class="pFirst pButton">
							<span></span>
						</div>
						<div class="pPrev pButton">
							<span></span>
						</div>
					</div>
					<div class="btnseparator"></div>
					<div class="pGroup">
						<span class="pcontrol">Page <input value="1" size="4"
							type="text"> of <span> 1 </span>
						</span>
					</div>
					<div class="btnseparator"></div>
					<div class="pGroup">
						<div class="pNext pButton">
							<span></span>
						</div>
						<div class="pLast pButton">
							<span></span>
						</div>
					</div>
					<div class="btnseparator"></div>
					<div class="pGroup">
						<div class="pReload pButton">
							<span></span>
						</div>
					</div>
					<div class="btnseparator"></div>
					<div class="pGroup">
						<span class="pPageStat"></span>
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
		</div>
		<!-- 表格源代码结束 -->
		<span class="clear"> </span>
	</div>
</body>
</html>
