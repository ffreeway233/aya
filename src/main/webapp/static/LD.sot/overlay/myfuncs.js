/** 
 * overlay
 * author: 胡永伟
 * date: 2015-04-18
 * version: 1.0.0
 */
var g_succ_msg = '\u64cd\u4f5c\u6210\u529f', g_fail_msg = '\u64cd\u4f5c\u5931\u8d25',
	g_succ_ico = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QzlBRDM3NTM0MDQ2MTFFMkFDNjJEMTAxN0EwRjQ3ODAiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QzlBRDM3NTQ0MDQ2MTFFMkFDNjJEMTAxN0EwRjQ3ODAiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpDOUFEMzc1MTQwNDYxMUUyQUM2MkQxMDE3QTBGNDc4MCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpDOUFEMzc1MjQwNDYxMUUyQUM2MkQxMDE3QTBGNDc4MCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PoNHeT0AAAK8SURBVHja7Nv9UcJAEIfh0AElWAIt2IElSAl2YgehA+3AEqQEO5AOYs65aESS3Mfe7d7ub2d2HP5hmPdh8oGwG4ahw/DNDgAAAAAGAADAAAAAmODZj/s27nncIwB44h/841MOAgDy4ne5CADIj5+FAACa+MkIAKCLn4QAANr40QgAoI8fhQCAMvGnefUIFwDUjz+Nu1m7X0IAQNn4mwgAKB9/FQEAdeIvIgCgXvybCNYBasf/h2AZgCv+HwSrAC56zxj/B8EiwMG/8/cCXsvJGoCo+O4u2RKAuPiWroJExrcCIDa+BQDR8bUDiI9PBdD7v0fEj4v/PQ4gY/vhd/rM56Law7ifg4zZbEIVXwpCU/Hdph6C3GHnMeTTPhx21icFYC0+F0KT8VMAQuLXRmg2fixATPxaCE3HjwFIiV8aofn4oQA58UshqIgfAkARnxpBTfwtAMr4VAiS4j+N+5z7JEsAJeLnIkiKf/Tv/q4EQMn4qQgq498CqBE/FkFt/GuAmvFDEVTHnwNwxN9CUB9/AuCMv4RgIv4E8N7xf0FpjuBey4uF+BMA91f05vMx7l0nY4rHn58DJCGYiX99FbT3J+MHxK8Tn/NGDPEZP4pA/AAAawgs8bcArCCwxQ8B0I7AGj8UQCsCe/wYAG0IIuLHAmhBEBM/BaB1BFHxUwFaRRAXPwegNQSR8XMBWkEQG58CQDqC6PhUAFIRxMenBJCEcPHxX1s4OVH/SI8bwcV3/9Y8t3JpVuJXklwIzcUvBcCB0GT8kgA1EZqNXxqgBkLT8WsAlERoPn4tgBIIKuLXBKBEUBO/NgAFgqr4HAA5COricwF0HqC3Hp8TIAZBbXxugBAE1fElAKwhqI8vBeAWgon4kgDmCGbiSwOYEM5W4ksEMDcAAAAAUAEAAMAAAAAYAAAAAwAAYCrOlwADALOcgG80xOrxAAAAAElFTkSuQmCC',
	g_fail_ico = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAYAAADimHc4AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OEM0MDU1QkE0MDRBMTFFMkFDNjJEMTAxN0EwRjQ3ODAiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OEM0MDU1QkI0MDRBMTFFMkFDNjJEMTAxN0EwRjQ3ODAiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpDOUFEMzc1OTQwNDYxMUUyQUM2MkQxMDE3QTBGNDc4MCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpDOUFEMzc1QTQwNDYxMUUyQUM2MkQxMDE3QTBGNDc4MCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PpV29yAAAAM3SURBVHja7N3hcdNAEMVxxRWYEujAlJBKUEqgAzpIqECmEkowHVCC6UCcJsqQMZhYd/vevj12Z+4rsv+/eIguyuVunuchx2/uEiABEiAnARIgJwESICcBEiAnARIghzc70nXGss5lHYJ0Oayvd4RfafkEgNc4/55zWQfCNVvWYX2dLzMir4d+M4/zn6OMcBkfjoB8M9N8fRQRrsWHInjEV0R4Kz4MwSu+EsKt8SEInvEVELbGN0fwju+JUBvfFEEhvgdCa3wzBJX4TASr+CYIrVsRE+Bu8WdZ92V9B93hfitrb/zvPpR1ZO8FTcBbdQQCKn4TQi3ARNgnsURAx69GqAFgxLdEYMWvQqgBOJF3NVsQ2PGH9XV+QALs1zeljuAV/359vdD/A9QRQsRv/S5IFSFMfIsfSaohhIpv9TNhFYRw8a0AFBBCxrcE8ET4VNZjxPjWAF4I7DGLjwDoHcE0PgqgVwTz+EiA3hAg8dEAvSDA4jMAoiNA47MAoiLA4zMBoiFQ4rMBoiDQ4nsAqCNQ43sBqCLQ43sCqCG4xPcGUEFwi68A4I1wHJ6fYnCbncDHf/nK++p07S/eb17hEzAOz88aeeGjHoMMAeAZXwLBE0AhvjuCF4BSfFcEDwDF+G4IbADl+C4ITIAI8ekILIBI8akIDICI8WkIaIDI8SkISIAe4sMRUAA9xYciIAB6jP8aYXkW9agK4BF/ibInX/PBCmEXPP4S4f3A38Ox+01RwLFkrJleXX9f1snhNUicFeEdH3VuBQWhp/ghEXqLHw6hx/ihEHqNHwah5j7gTP6++zi0PTrCPFzk5b7kHfI+gPkQU2t805umDdsV8Dthxu/lWsRnfhKq9opatiKQCNbx0QjVG3Wte0EIBFR8FELbLqnYKYTTzDm20uq7o+ZTHpWOgmTFt0IwOWJT5TxOdvxWhB9W55sqHIrqFb8W4bTuvsqenr4FwTv+VgTT+Mi/H3ALgkr8WxHM46P/gsa/ENTiv4UAic/4GzJ/Q1CNfw0BFp8BcImgHv8SARrf4vT0LXfMH9dHOqLM57Keho7OishJgATISYAEyEmABMhJgATISYAE+O/nlwADAADMVJX9TVBJAAAAAElFTkSuQmCC',
	g_spinner_opt = {
		rotate		: 00,
		speed		: 01,
		corners		: 01,
		width		: 04,
		length		: 08,
		lines		: 12,
		radius		: 16,
		trail		: 60,
		className	: 'spinner',
		color		: '#FFF',
		left		: 'auto',
		top			: 'auto',
		hwaccel		: false,
		shadow		: false,
		zIndex		: 99999,
	};
$.ios = {
	succ : function(msg) {
		var l_succ_msg = msg == null ? g_succ_msg : msg;
		iosOverlay({
			icon 		: g_succ_ico,
			duration	: 1.5e3,
			text		: l_succ_msg
		});
	},
	fail : function(msg) {
		var l_fail_msg = msg == null ? g_fail_msg : msg;
		iosOverlay({
			icon 		: g_fail_ico,
			duration	: 1.5e3,
			text		: l_fail_msg
		});
	},
	load : function(opts) {
		if (opts == null) { opts = new Object(); }
		var target = document.createElement("div");
		document.body.appendChild(target);
		var spinner = new Spinner(g_spinner_opt).spin(target);
		var text = opts.text == null ? 'Loading' : opts.text;
		var overlay = iosOverlay({
			text	: text,
			spinner	: spinner
		});
		if ($.isFunction(opts.call)) {
			try {
				var back = opts.call();
				if (back != null) {
					var flag = back.flag;
					if (flag == true || flag == 'true' || flag=='succ') {
						window.setTimeout(function() {
							var l_succ_msg = opts.succ == null ? g_succ_msg : opts.succ;
							overlay.update({
								icon 	: g_succ_ico,
								text	: l_succ_msg
							});
						}, 5e2);
					} else if (flag == false || flag == 'false' || flag=='fail') {
						window.setTimeout(function() {
							var l_fail_msg = opts.fail == null ? g_fail_msg : opts.fail;
							overlay.update({
								icon 	: g_fail_ico,
								text	: l_fail_msg
							});
						}, 5e2);
					}
					window.setTimeout(function() {
						overlay.hide();
						if ($.isFunction(back.call)) {
							window.setTimeout(function() {
								back.call(flag);
							}, 5e2);
						}
					}, 1e3);
				}
			} catch (e) {
				var l_warn_msg = opts.warn == null ? g_warn_msg : opts.warn;
				window.setTimeout(function() {
					overlay.update({
						icon	: g_fail_ico,
						text	: l_warn_msg
					});
				}, 5e2);
			}
		}
		window.setTimeout(function() {
			overlay.hide();
		}, 1e3);
	},
	ajax : function(opts) {
		if (opts == null) { opts = new Object(); }
		if (opts.msg == null) { opts.msg = new Object(); }
		if (opts.cus == null) { opts.cus = new Object(); }
		opts.cus.call = function() {
			var _opts = $.extend({
				dataType : 'json', type : 'post', timeout : 3e4,
				async : false, error : function(e) { throw e; }
			}, opts);
			var back = null;
			_opts.success = function(data) {
				if ($.isFunction(opts.success)) {
					back = opts.success(data);
				}
			};
			$.ajax(_opts);
			return back;
		};
		$.ios.load({
			text	: opts.msg.text,
			succ	: opts.msg.succ,
			fail	: opts.msg.fail,
			warn	: opts.msg.warn,
			call	: opts.cus.call
		});
	}
};