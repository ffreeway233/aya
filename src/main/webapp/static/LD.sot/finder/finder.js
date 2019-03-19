var finderPath = apiPath + '/finder';
var $finder, $sear, $idel, $iipt, $data, fkutid, ftimer;
$.finder = {
	init : function() {
		with (ld_finder_style) {
			$finder = $(window.top.document.body).append($((
				"<div id='ld_finder' " + sbox + ">" + 
					"<div " + head + ">" + 
						"<div " + left + ">" +
							"<div " + ibox + ">" + 
								"<div " + icon + "></div>" + 
								"<div " + ifix + ">" + 
									"<input id='ld_finder_iipt' " + iipt + " type='text' placeholder='\u8f93\u5165\u5173\u952e\u5b57'/>" + 
								"</div>" + 
								"<div id='ld_finder_idel' " + idel + "></div>" + 
							"</div>" + 
						"</div>" + 
						"<div " + ibtn + ">" + 
							"<a id='ld_finder_sear' type='0' href='javascript:;' " + ibat + ">\u53d6\u6d88</a>" + 
						"</div>" +
					"</div>" +
					"<ul id='ld_finder_data' " + body + "></ul>" + 
					"<div " + foot + ">" + 
						"<a id='ld_finder_iclr' href='javascript:;' " + ftat + "></a>" + 
					"</div>" +
				"</div>").replace(/#imgPath#/gi, finderPath))).find("#ld_finder");
			$sear = $finder.find("#ld_finder_sear");
			$idel = $finder.find("#ld_finder_idel");
			$iipt = $finder.find("#ld_finder_iipt");
			$data = $finder.find("#ld_finder_data");
			$iclr = $finder.find("#ld_finder_iclr");
			$.finder.listen();
		}
	},
	listen : function() {
		$iipt.focus(function() {
			if (!ftimer) {
				ftimer = true;
				fkutid = setInterval(function() {
					if ($iipt.val().isEmpty()) {
						$sear.html("\u53d6\u6d88").attr('type', '0');
						$idel.hide();
					} else {
						$sear.html("\u641c\u7d22").attr('type', '1');
						$idel.show();
					}
				}, 100);
			}
		}).blur(function() {
			clearInterval(fkutid);
			ftimer = false;
		});
		$idel.click(function() {
			$iipt.val('');
			$sear.html("\u53d6\u6d88").attr('type', '0');
			$(this).hide();
		});
	},
	initData : function(database, callback) {
		$iipt.val('');
		$sear.html("\u53d6\u6d88").attr('type', '0');
		$idel.hide();
		var array = LD.data.select(database), dest = '';
		for (var i = 0; i < array.length; i++) {
			with (ld_finder_style) {
				dest += "<li " + cell + ">" + 
							"<div class='ld_finder_txt' " + itxt + ">" + array[i] + "</div>" + 
							"<div class='ld_finder_sel' " + isel + "></div>" + 
						"</li>";
			}
		}
		dest = dest.replace(/#imgPath#/gi, imgPath);
		$data.html(dest).find(".ld_finder_sel").click(function() {
			$iipt.val($(this).parent().find(".ld_finder_txt").html());
			$sear.html("\u641c\u7d22").attr('type', '1');
			$idel.show();
		});
		$data.find(".ld_finder_txt").click(function() {
			if ($.isFunction(callback)) {
				callback($(this).html(), $finder);
			}
		}).mouseLongDown(function($this) {
			$.pop.confirm('清除该条记录?', function() {
				LD.data.remove(database, $this.html());
				$this.parent().remove();
				if ($data.find("li").length == 0) {
					$iclr.html('\u6682\u65e0\u5386\u53f2\u8bb0\u5f55...');
					$iclr.click(function() { return; });
				}
			});
		});
		if ($data.find("li").length > 0) {
			$iclr.click(function() {
				$.pop.confirm('清空历史记录?', function() {
					$data.empty();
					LD.data.clear(database);
					$(this).html('\u6682\u65e0\u5386\u53f2\u8bb0\u5f55...');
				});
			}).html('\u6e05\u7a7a\u5386\u53f2\u8bb0\u5f55');
		} else {
			$iclr.html('\u6682\u65e0\u5386\u53f2\u8bb0\u5f55...');
		}
		$finder.show();
	},
	addEvent : function(database, callback) {
		$sear.click(function() {
			if ($(this).attr('type') == '0') {
				$finder.hide();
			} else if ($(this).attr('type') == '1') {
				LD.data.insert(database, $iipt.val());
				if ($.isFunction(callback)) {
					callback($iipt.val(), $finder);
				}
			}
		});
	}
};
(function($) {
	$.fn.extend({
		finder : function(database, callback) {
			return this.each(function() {
				$.finder.addEvent(database, callback);
				$(this).focus(function() {
					$.finder.initData(database, callback);
				});
			});
		}
	});
})(jQuery);