(function($) {
	$.extend({
		spanToggle : new function() {
			var toggleDivClass = function($this, $div) {
				if ($div.is(":visible")) {
					$this.addClass("arrowDB-V").removeClass("arrowDB-H");
				} else {
					$this.addClass("arrowDB-H").removeClass("arrowDB-V");
				}
			};
			this.construct = function() {
				return this.each(function() {
					var $this = $(this);
					var $div = $this.nextAll("div.box:eq(0)");
					// 判断当前是否可见，如果可见 添加对应的css
					toggleDivClass($this, $div);

					// 绑定点击事件
					$this.css({
						cursor : "pointer",
						width : "auto",
						"padding-left" : "25px",
						"margin-bottom" : "8px",
						"line-height" : "14px",
						"float" : "none",
						"display" : "block"
					}).click(function() {
						var $this = $(this);
						var $div = $this.nextAll("div.box:eq(0)");
						$div.toggle("normal", "swing", function() {
							var _$div = $(this);
							// 判断当前是否可见，如果可见 添加对应的css
							toggleDivClass($this, _$div);
						});
					});
				});
			}
		}
	});

	$.fn.extend({
		spanToggle : $.spanToggle.construct
	});
})(jQuery);