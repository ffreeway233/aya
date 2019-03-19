/**
 * 数据分页插件 - 2.0.1
 * 作者：胡永伟
 * 时间：2014-12-21
 * 实现：本插件通过ajaxSubmit异步获取后台数据,返回数据为json类型,接口具体返回参数：
 * 		{ 
 * 			tableList : [
 * 				{...}, {...}, {...}, ...
 * 			], 
 * 			pageParam : {
 * 				pageNo : '', 
 * 				pageSize : '',
 * 				totalPages : '',
 * 				totalCount : '',
 * 				psingle : ''
 * 			} 
 * 		}
 * @param options 
 * 	主要参数: ID (默认'list_table')
 * 			columns : 显示列信息[{width : '...', name : '...', field : '...'}, ...]
 * 			process : 单项数据解析回调
 * @returns {DPTLister}
 * 
 * example:
 * 	js-code:
 * 		var cols = [
			{width:"33.3%", name:"名称", field:"name"},
		 	{width:"33.3%", name:"排序", field:"weight"},
		  	{width:"33.3%", name:"操作", field:"id"}
		];
		var dptl = new DPTLister({
			'columns' : columns,
			'process' : function(html, cell) {
				var operate = "<a href='javascript:edit(" + cell.id + ")'>编辑</a>" +
					"&nbsp;&nbsp;&nbsp;<a href='javascript:del(" + cell.id + ")'>删除</a>";
				return html.replace('#id#', operate);
			}
		});
		$(document).ready(function() {
			dptl.init().list(true);
		});
 */
function DPTLister(options) {
	this.options = $.extend({ 
		ID : 'list_table', oddColor : '#f6f8fa', evenColor : '#ffffff', 
		sortWay : '', colSort : false, scFixed : false, txFixed : true, 
		btFixed : 100, defined : false
	}, options);
	this.init = function() {
		var self = this, options = self.options, $root = $("#" + options.ID),
			node = "", href = "href='javascript:void(0);'";
		if (options.defined || options.defined == 'true') {
			node += "<div id='ld_dptl_data_list_box' style='width:100%;'></div>";
		} else {
			var cols = options.columns, tp, ths = tds = "", attrs = [], i = 0, j, 
				$st, al, cv, mh, sh = 35, size = 1, vs, attr;
			for (; cols != null && i < cols.length; i++) {
				if (cols[i].sort || cols[i].sort == 'true' || 
						options.colSort || options.colSort == 'true') {
					ths += ("<th class='ld-dptl-mouse-hand' width='" + 
									cols[i].width + "' key='" + cols[i].field + "'>" +
								"<div class='ld-dptl-fl'></div>" + cols[i].name +
								"<div type='sort' class='ld-dptl-fr ld-dptl-fr-normal'></div>" +
							"</th>");
				} else { 
					ths += ("<th width='" + cols[i].width + "' key='" + 
								cols[i].field + "'>" + cols[i].name + "</th>"); 
				}
				if (cols[i].align == "left") { 
					al = "td-align-left"; 
				} else if (cols[i].align == "right") { 
					al = "td-align-right"; 
				} else { 
					al = "td-align-center"; 
				}
				cv = (cols[i].value == null) ? "#" + cols[i].field + "#" : cols[i].value ;
				tds += ("<td width='" + cols[i].width + "' class='" + al + "'><div>" + cv + "</div></td>");
				vs = cv.match(/#[^#]+#/g);
				for (j = 0; vs != null && j < vs.length; j++) {
					attr = vs[j].substr(1, vs[j].length - 2);
					if (!(attr in attrs)) { 
						attrs.push(attr); 
					} 
				}
			}
			options.attrs = attrs;
			mh = document.documentElement.clientHeight - $root.offset().top - options.btFixed;
			while (sh <= mh) { 
				sh += 35; 
				size ++; 
			};
			options.size = (--size);
			node += "<div id='ld_dptl_data_title' style='height: 36px;'>" + 
						"<table class='ld-dptl-title' border='0' cellspacing='0' cellpadding='0'>" +
							"<tr>" + ths + "</tr>" +
						"</table>" +
					"</div>" + 
					"<div id='ld_dptl_data_root'>" + 
						"<table class='ld-dptl-table' border='0' cellspacing='0' cellpadding='0'>" +
							"<tbody id='ld_dptl_data_list_table'></tbody>" +
						"</table>" +
					"</div>" +
					"<table style='display: none'>" +
						"<tbody id='ld_dptl_data_list_model'><tr>" + tds + "</tr></tbody>" +
					"</table>";
		}
		node += "<div id='ld_dptl_page_root' class='ld-dptl-page'>" +
					"<input type='hidden' id='ld_dptl_page_num' name='pageNo' value='0'/>" +
					"<input type='hidden' id='ld_dptl_page_total' value='0'/>" +
					"<input type='hidden' id='ld_dptl_sort_key' name='sortWay' value=''/>" +
					"<input type='hidden' name='fuzzies' value='" + options.fuzzies + "'/>" +
					"<span id='ld_dptl_record_info' class='record'></span>" +
					"<div id='ld_dptl_page_root_operate' style='float: right;'>" +
						"<a " + href + " id='ld_dptl_first_page'>\u9996\u3000\u9875</a>" +
						"<a " + href + " id='ld_dptl_previou_page'>\u4e0a\u4e00\u9875</a>" +
						"<span id='ld_dptl_page_info' class='pages'></span>" +
						"<a " + href + " id='ld_dptl_next_page'>\u4e0b\u4e00\u9875</a>" +
						"<a " + href + " id='ld_dptl_last_page'>\u5c3e\u3000\u9875</a>" +
						"<input type='text' id='ld_dptl_page_input' />" +
						"<a " + href + " id='ld_dptl_input_page'>\u8df3\u3000\u8f6c</a>" +
					"</div>" +
				"</div>" +
				"<div id='ld_dptl_no_data_tip' class='ld-dptl-tip'>" +
					"<span>\u672a\u641c\u7d22\u5230\u76f8\u5173\u6570\u636e...</span>" +
				"</div>";
		$root.append($(node)).find("#ld_dptl_page_root a").click(function() { 
				dptlso.goTargetPage(self, this); 
		});
		if (!options.defined && options.defined != 'true') {
			$root.find(".ld-dptl-title tr th.ld-dptl-mouse-hand").click(function() {
				$st = $(this).find("div[type='sort']");
				if ($st.hasClass("ld-dptl-fr-normal")) { 
					tp = 1;
				} else if ($st.hasClass("ld-dptl-fr-up")) { 
					tp = 2;
				} else if ($st.hasClass("ld-dptl-fr-down")) { 
					tp = 3; 
				}
				$root.find(".ld-dptl-title tr th div[type='sort']")
					.removeClass("ld-dptl-fr-up ld-dptl-fr-down")
					.addClass("ld-dptl-fr-normal");
				switch (tp) {
					case 1 : 
						$st.removeClass("ld-dptl-fr-normal").addClass("ld-dptl-fr-up"); 
						$root.find("#ld_dptl_sort_key").val($(this).attr("key") + " asc");
						break;
					case 2 : 
						$st.removeClass("ld-dptl-fr-up").addClass("ld-dptl-fr-down");
						$root.find("#ld_dptl_sort_key").val($(this).attr("key") + " desc"); 
						break;
					case 3 : 
						$st.removeClass("ld-dptl-fr-down").addClass("ld-dptl-fr-up");
						$root.find("#ld_dptl_sort_key").val($(this).attr("key") + " asc");
						break; 
				}
				self.list(true); 
			}); 
		}
		$root.find("#ld_dptl_sort_key").val(options.sortWay);
		if ($("#ld_dptl_data_load_div").length == 0) {
			$("body").append($("<div id='ld_dptl_data_load_div' class='ld-dptl-load'></div>")); 
		}
		return self;
	};
	this.list = function(toFirst) {
		var self = this, $root = $("#" + self.options.ID);
		if (toFirst) { 
			$root.find("#ld_dptl_page_num").val(1); 
		}
		$("#ld_dptl_data_load_div").show();
		$root.parents("form").ajaxSubmit({
			dataType: 'json',
			success: function(data) { 
				$("#ld_dptl_data_load_div").hide();
				dptlso.setPageInfo(self, data.pageParam);
				if (self.options.defined || self.options.defined == 'true') {
					var pushinOption = {
						'template' : self.options.temlStr,
						'isReload' : true,
						'jsonData' : data.tableList,
					};
					if ($.isFunction(self.options.process)) {
						pushinOption.dealWith = self.options.process;
					}
					$("#ld_dptl_data_list_box").pushin(pushinOption);
				} else {
					var objs = data.tableList, result = "";
					if (objs != null && objs.length > 0) {
						var template = $root.find("#ld_dptl_data_list_model").html(), 
						 	cell, p, val, attrs = self.options.attrs, i = 0;
						for (; i < objs.length; i++) { 
							cell = template;
							if ($.isFunction(self.options.process)) { 
								cell = self.options.process(cell, objs[i]); 
							}
							for (p in attrs){
								val = (objs[i][attrs[p]] == null) ? "" : objs[i][attrs[p]];
								cell = cell.split("#" + attrs[p] + "#").join(val); 
							} 
							result += cell; 
						} 
					}
					var $table = $root.find("#ld_dptl_data_list_table").html(result);
					$table.find("tr:even").css("background-color", self.options.evenColor);
					$table.find("tr:odd").css("background-color", self.options.oddColor);
					if (self.options.txFixed || self.options.txFixed == 'true') {
						$.each($table.find("div"), function() {
							$(this).css('width', $(this).parent().css('width'));
							$(this).css('white-space', 'nowrap');
						});
					}
				}
				if ($.isFunction(self.options.callback)) { 
					self.options.callback(); 
				}
			}
		});
		return self;
	};
}

var dptlso = {
	setPageInfo : function(dptl, data) {
		var $root = $("#" + dptl.options.ID), curRecord,
			$dataRoot = $root.find("#ld_dptl_data_root"),
			$dataTitle = $root.find("#ld_dptl_data_title"),
			$pageRoot = $root.find("#ld_dptl_page_root"),
			$pageTip = $root.find("#ld_dptl_no_data_tip"),
			$fp = $root.find("#ld_dptl_first_page,#ld_dptl_previou_page"),
			$nl = $root.find("#ld_dptl_next_page,#ld_dptl_last_page"),
			$ip = $root.find("#ld_dptl_input_page,#ld_dptl_page_input"),
			pageSize = data.pageSize, totalPages = data.totalPages,
			pageNo = data.pageNo, totalCount = data.totalCount;
		if (totalCount == 0) { 
			$pageRoot.hide(); 
			$pageTip.show();
			if (!dptl.options.defined && dptl.options.defined != 'true') {
				$dataRoot.hide(); 
				$dataTitle.css('overflow-y', 'hidden'); 
			}
			return; 
		}
		var curRecord = (totalCount - pageNo * pageSize < 0) 
							? totalCount % pageSize : pageSize;
		$root.find("#ld_dptl_page_num").val(pageNo);
		$root.find("#ld_dptl_page_total").val(totalPages);
		$pageTip.hide(); 
		$pageRoot.show(); 
		if (!dptl.options.defined && dptl.options.defined != 'true') {
			$dataRoot.show(); 
			if (dptl.options.scFixed || dptl.options.scFixed == 'true') {
				if (curRecord <= dptl.options.size) {
					$dataTitle.css('overflow-y', 'hidden');
					$dataRoot.css('overflow-y', 'hidden');
					$dataRoot.css('height', 35 * curRecord);
				} else { 
					$dataTitle.css('overflow-y', 'scroll');
					$dataRoot.css('overflow-y', 'scroll'); 
					$dataRoot.css('height', 35 * dptl.options.size); 
				} 
			}
		}
		$root.find("#ld_dptl_record_info").html(
				"[\u641c\u7d22\u7ed3\u679c\uff1a\u5171\u641c\u7d22\u5230" +
				"[<Strong>" + totalCount + "</Strong>]" +
				"\u6761\u8bb0\u5f55,\u5f53\u524d\u663e\u793a" +
				"[<Strong>" + curRecord + "</Strong>]" + "\u6761\u8bb0\u5f55]");
		$root.find("#ld_dptl_page_info").html(
				"<Strong>&nbsp;" + pageNo + "&nbsp;/&nbsp;" + 
				totalPages + "&nbsp;</Strong>\u9875&nbsp;");
		if (pageNo <= 1) { 
			$fp.attr('disabled',true); 
		} else { 
			$fp.attr('disabled',false); 
		}
		if (pageNo >= totalPages) { 
			$nl.attr('disabled',true);
		} else { 
			$nl.attr('disabled',false); 
		}
		if (totalPages <= 1) { 
			$ip.attr('disabled',true);
		} else { 
			$ip.attr('disabled',false); 
		}
		if (data.psingle) { 
			$root.find("#ld_dptl_page_root_operate").hide(); 
			return; 
		}
	},
	goTargetPage : function(dptl, btn) {
		var $root = $("#" + dptl.options.ID), key = btn.id;
			pageNo = parseInt($root.find('#ld_dptl_page_num').val());
			totalPages = parseInt($root.find('#ld_dptl_page_total').val());
		if (key == 'ld_dptl_first_page') { 
			pageNo = 1;
		} else if (key == 'ld_dptl_previou_page') { 
			pageNo = pageNo > 1 ? (--pageNo) : pageNo;
		} else if (key == 'ld_dptl_next_page') { 
			pageNo = pageNo < totalPages ? (++pageNo) : pageNo;
		} else if (key == 'ld_dptl_last_page') { 
			pageNo = totalPages;
		} else if (key == 'ld_dptl_input_page') { 
			var $pageIpt = $root.find('#ld_dptl_page_input'), 
				pageNoIpt = $pageIpt.val(), iPageNumIpt = parseInt(pageNoIpt);
			if (pageNoIpt == iPageNumIpt) {
				if (iPageNumIpt < 1 || iPageNumIpt > totalPages) {
					alert('\u9875\u7801\u4e0d\u5728\u8303\u56f4\u5185'); 
					$pageIpt.val(""); 
					return;
				} else { 
					pageNo = iPageNumIpt; 
				}
			} else { 
				alert('\u8f93\u5165\u9875\u7801\u6709\u8bef,\u8bf7\u91cd\u65b0\u8f93\u5165!'); 
				$pageIpt.val(""); 
				return; 
			}
		} else { 
			alert('\u9519\u8bef\u7684\u6307\u4ee4\u64cd\u4f5c'); 
			return; 
		}
		$root.find('#ld_dptl_page_num').val(pageNo); 
		dptl.list(false);
	}
};