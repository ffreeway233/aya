/** 
 * jquery.sot.js
 * author: 胡永伟
 * date: 2014-11-15
 * version: 1.1.0
 */
(function($) {
	$.LD = {
		ajax : function(opts) {
			var _opts = $.extend({ 
				dataType : 'json', timeout : 30000, async : true,
				type : 'post', error : function (e) { throw e; }
			}, opts);
			$.ajax(_opts);
		}
	};
})(jQuery);

(function($) {
	$.fn.extend({
		fillin : function(obj) {
			return this.each(function() {
				$(this).html(LD.json.parse(
						$(this).html(), [ obj ])).show();
			});
		},
		pushin : function(opt) {
			return this.each(function() {
				if (opt.jsonData != null) {
					var jsonList = $.isArray(opt.jsonData) 
							? opt.jsonData : [ opt.jsonData ];
					if (opt.isReload) {
						$(this).html(LD.json.parse(
								opt.template, jsonList, opt.dealWith));
					} else {
						$(this).append(LD.json.parse(
								opt.template, jsonList, opt.dealWith));
					}
				} else {
					if (opt.isReload) { $(this).html(""); }
				}
			});
		}
	});
})(jQuery);