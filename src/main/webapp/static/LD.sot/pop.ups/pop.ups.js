/** 
 * pop.ups
 * author: 胡永伟
 * date: 2015-05-10
 * version: 2.2.0
 */
var g_popups_ittid, g_popups_timer = false, $_popups_body, $_popups_lock, $_popups_mask, $_popups_bkgr, $_popups_tips, $_popups_load,
	g_loader_img1 = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAQAAAD9CzEMAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAgY0hSTQAAeiYAAICEAAD6AAAAgOgAAHUwAADqYAAAOpgAABdwnLpRPAAAAAJiS0dEAP+Hj8y/AAAACXBIWXMAAABIAAAASABGyWs+AAADKklEQVRYw+3XzWtcVRiA8d89N5mk6eTDNqalraVqrAWxi4pIEbSIGlCKuBGhFPciFLeCuJKCf4Ir1y1KUSm6E12I4lelcVGlftRY+5HadCQmTO45LjI0M5mZZGZiFwXfwwzMvPc+zz33nHvPe7Lk1ka4xfz/BetHX8dHbrfDdrvwNjIdzo6sg+P2et4zDsoEWe07yCRR3GgP9jvuUA23/IEMmaBPpmqpV8GEN70gSBIyZyw4K6MmWu7NIBbaS9rfogPecZdMEJz2gY9cacgHuT5BEGQWLLThpNbtcLqQrqZr6a90Ij2QtG2lNJzG0h1payq3PqL1aVNpJl1OV9Jv6ek14MstpOG0JY2nO9NYq3yrW7TPKaMyvzrip/UnGco2CTLzbqw/BsNOu1vmkqfMdoSHEZtlMrOrx6L5SX7ZbtG8o13gqagKgi21idxWsM1Loug1013gSa4hKBleW3BMSXTWe13hIarU+tDAbBSMelYUvaXoWsANUdCv3F4wpSQ659Me8KRaH0bbCx4SFU71hIeKXK5cT20WRF/2LKiKgtxga8GQcYWqH3oWsChvFNS/TbeKuKy6AUFVQKm1oKyg+WHvKpK8kVovKER6mqArkclvLktNgnkFjbO46+gXqF9I6wf5kiXRmE0bEAzJhfpRrBdUzSgUJjcgGJHL/dNawHcK0SM948uGBPzdTvCFqPBYz4Ltcrm5+pqpUfC5imjCoZ7w/fYIgj/r/2wULPpQoXCki4pvJSYNyC02Vh+r14MT5kQTnusav9mkXPBjY7W3WlBxUiF60f4ub8+j+gUVfzQmmtfk92vX8KodHeODg8YEyZnm1OpYctxV0aA37OsIP+BJuwTBt66vTrYuHSe9rk+06KSP18GPe1xZIZpuvv72tel9jhkWFWa86/s28BEPu0dUiL5uXYe0L363esXu2skXfWPaL3XZze61x05EhUWfuNAas9YGpOSwJwSFqFAozKooFCZqv5czv/vMXDvIejucbaYckN0ErsiW20Vf+XktQCdbqAH3e9BeAw2C8847p7LeyZ0IVmKnZJN51+tfyP+loIe4/Tfit7/gXwRBUHwpdK/8AAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE0LTA5LTA3VDE0OjIzOjE3KzA4OjAwJzY2ugAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNC0wOS0wN1QxNDoyMzoxNyswODowMFZrjgYAAAAASUVORK5CYII=',
	g_loader_img2 = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAMAAABg3Am1AAACZ1BMVEX////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////k8bjLAAAAzXRSTlMAAWZKMhgCBAX//v37+sF3JgkIBwYD/Pn49/W1LgwLmZjEzPb08/GbFhAPDRVdqvLw79ITESvu7ONNFBJG4uvq5UcOFyTJ6OfYKBsaGc7kwx0c2+EgH1Xg39YiIbTd3IYlPtrRJ9dWKSpl1YkKLC/TvNAxuDTLNjenObPGOzzCPzgtwEFCZL5ERaK7QLmvS7ZjTE5TsVBSq7BPVJ+tix5YWqlcX5yjpaFeYEOBlGdpfZadf2hrbXBzdnmMj5KXlW5xdHqAgoWIjpGQM1sjyayD7AAAAk1JREFUSMdjYBgFEMDIwMDExMzCSpxqNnYOdjZGTi5uHl4+fgGCytkFhYRF2EWBGsR4xCUkpaRl8CuXlZUTFBIB2iCvoKgkLqmsoqqmroFbvZCmlracIAcblKujq6eib2BoxIJLvayxiaa2CIqQqZ6hmbmFJXb1mjpW1tps6KI2tnb2Do7Y1DuxOuvIYhF3cXVz9/DE4n4vbx8nrDYb+zr4+WtjxJV3QKAVI3a3mgbZBvNjOCgkNJANV2iEhUdEOqNFb0hUtBbO4BaNiY2LR7MgwSWUEXcEJQYlJaOaF5AiYI0vCUh7pKahuCg9I50Dn4bMrKxsZH5Obm4K3kSWl59fgMy3zssLwauBw7ewMAeJz1pU5IU/2Rf7+gYgcQNLSrzxa9ArLU1HDqSycgI2VLi6Iuc+r8pK/H5gqKquDkTiatTUZuBVL+ivWIcc7tqJ9Q14NTQ28VWgpNXE5hZZfBpa29pQ02tee4cLPg2dXQXdKALONo7teNJGd3FPrzBq8q5n7kvHXfqoS/dPQBPznjjJUg6XhslTqiow8mj91Gk2OBwlM713xjTMQmbmrLRErJnUe/b0OXPZMcXnpc1fYCOMKe4pX7FQHmvuyluwaPFkK3RRxyVLly3HEeSmK1ZOyFyFUjjJrF4ye/maBFyhEaDbunbdukky0OAK6Vu/QUF94yZj3BGkNXHzlq3btu/YOYFp3Y7sXbv3rNm7b7II3lQWHbb/wEH+Q+sPHzl6bO7xEydXOBOsg7RPhUE1rD4tI0xkrSjoc8bba7RxAAcADoqN6yntii8AAAAASUVORK5CYII=';
$.pop = {
	init : function() {
		$_popups_body = $(window.top.document.body);
		with (g_popups_style) {
			// init lock
			$_popups_lock = $_popups_body.find("#ld_pop_lock");
			if ($_popups_lock.length == 0) {
				$_popups_body.append($("<div id='ld_pop_lock' " + lock + "></div>"));
				$_popups_lock = $_popups_body.find("#ld_pop_lock");
			}
			// init mask
			$_popups_mask = $_popups_body.find("#ld_pop_mask");
			if ($_popups_mask.length == 0) {
				$_popups_body.append($("<div id='ld_pop_mask' " + mask + "></div>"));
				$_popups_mask = $_popups_body.find("#ld_pop_mask");
			}
			// init bkgr
			$_popups_bkgr = $_popups_body.find("#ld_pop_bkgr");
			if ($_popups_bkgr.length == 0) {
				$_popups_body.append($("<div id='ld_pop_bkgr' " + bkgr + "></div>"));
				$_popups_bkgr = $_popups_body.find("#ld_pop_bkgr");
			}
			// init tips
			$_popups_tips = $_popups_body.find("#ld_pop_tips");
			if ($_popups_tips.length == 0) {
				with (tips) {
					$_popups_body.append($(
						"<div id='ld_pop_tips' " + ibox + ">" +
							"<div " + itxt + "></div>" + 
						"</div>"
					));
					$_popups_tips = $_popups_body.find("#ld_pop_tips");
				}
			}
			// init load
			with (load) {
				$_popups_load = $_popups_body.find("#ld_pop_load");
				if ($_popups_load.length == 0) {
					$_popups_body.append($(
						"<div id='ld_pop_load' " + ibox + ">" +
							"<div " + iims + ">" +
								"<img " + iimg + " src='" + g_loader_img1 + "'>" +
								"<img " + iimg + " src='" + g_loader_img2 + "'>" +
							"</div>" +
							"<div id='ld_pop_load_txt' " + itxt + "></div>" +
						"</div>"
					));
					$_popups_load = $_popups_body.find("#ld_pop_load");
				}
			}
		}
	},
	lock : function(flag) {
		if (flag) {
			$_popups_lock.show();
		} else {
			$_popups_lock.hide();
		}
	},
	mask : function(flag) {
		if (flag) {
			$_popups_mask.show();
		} else {
			$_popups_mask.hide();
		}
	},
	bkgr : function(flag) {
		if (flag) {
			$_popups_bkgr.show();
		} else {
			$_popups_bkgr.hide();
		}
	},
	tips : function(text, call, time) {
		// set tips box position center
		$_popups_tips.find("div").html(text);
		var height = $_popups_tips.css('height');
		height = parseInt(height.match(/\d+/)[0]);
		var client = LD.util.getClient().height;
		var top = client / 2 - height;
		$_popups_tips.css('top', top + 'px');
		// show tips box
		$_popups_tips.show();
		// backcall
		time = ((time == parseFloat(time)) ? time : 2) * 3e2;
		setTimeout(function() {
			$_popups_tips.fadeOut('fast', function() {
				if ($.isFunction(call)) {
					call();
				}
			});
		}, time);
	},
	load : function(flag, call) {
		if (flag == true || flag == 'true' || flag == 'show') {
			var deg1 = deg2 = 0, $ims = $_popups_load.find("img");
			if (!g_popups_timer) {
				g_popups_timer = true;
				g_popups_ittid = setInterval(function() {
					$($ims[0]).css('transform',
						'rotate(' + (deg1 -= 3) + 'deg)');
					$($ims[1]).css('transform',
						'rotate(' + (deg2 += 5) + 'deg)');
				}, 20);
			}
			$_popups_load.find("#ld_pop_load_txt").html(call);
			$_popups_lock.show();
			$_popups_load.show();
		} else if (flag == false || flag == 'false' || flag == 'hide') {
			clearInterval(g_popups_ittid);
			g_popups_timer = false;
			$_popups_lock.hide();
			$_popups_load.hide();
			if ($.isFunction(call)) {
				call();
			}
		}
	},
	chio : function(opts) {
		with (g_popups_style.chio) {
			// create chio box
			$_popups_body.append($(
				"<div id='ld_pop_chio' " + ibox + ">" + 
					"<div id='iopr' " + iopr + ">" +
						"<div id='left' " + left + ">" + opts.left.text + "</div>" + 
						"<div id='rite' " + rite + ">" + opts.rite.text + "</div>" + 
					"</div>" +
				"</div>"
			));
			var $chio = $_popups_body.find("#ld_pop_chio");
			// set chio close event
			var $iopr = $chio.find("div:first");
			$iopr.find("div:first").click(function() {
				$chio.fadeOut(100, function() {
					$_popups_mask.hide();
					if ($.isFunction(opts.left.call)) { 
						opts.left.call(); 
					}
				}).remove();
			});
			$iopr.find("div:last").click(function() {
				$chio.fadeOut(100, function() {
					$_popups_mask.hide();
					if ($.isFunction(opts.rite.call)) { 
						opts.rite.call(); 
					}
				}).remove();
			});
			// append text and note
			if (opts.text != null && opts.note != null) {
				$iopr.before($(
					"<div " + itxt + ">" + 
						"<div " + text + ">" + opts.text + "<div/>" + 
						"<div " + note + ">" + opts.note + "<div/>" + 
					"</div>"
				));
			} else {
				if (opts.text != null) {
					$iopr.before($(
						"<div " + jtxt + ">" + 
							"<div " + text + ">" + opts.text + "<div/>" + 
						"</div>"
					));
				} else {
					$iopr.before($(
						"<div " + itxt + ">" + 
							"<div " + note + ">" + opts.note + "<div/>" + 
						"</div>"
					));
				}
			}
			// set hint box position center
			var height = $chio.css('height');
			height = parseInt(height.match(/\d+/)[0]);
			var client = LD.util.getClient().height;
			var top = client / 2 - height;
			$chio.css('top', top + 'px');
			// show hint box
			$_popups_mask.show();
			$chio.fadeIn('fast');
		}
	},
	hint : function(opts) {
		with (g_popups_style.chio) {
			// create hint box
			$_popups_body.append($(
				"<div id='ld_pop_hint' " + ibox + ">" + 
					"<div " + iopr + ">\u786e\u8ba4</div>" +
				"</div>"
			));
			var $hint = $_popups_body.find("#ld_pop_hint");
			// set hint close event
			var $iopr = $hint.find("div").click(function() {
				$hint.fadeOut(100, function() {
					$_popups_mask.hide();
					if ($.isFunction(opts.call)) { 
						opts.call(); 
					}
				}).remove();
			});
			// append text and note
			if (opts.text != null && opts.note != null) {
				$iopr.before($(
					"<div " + itxt + ">" + 
						"<div " + text + ">" + opts.text + "<div/>" + 
						"<div " + note + ">" + opts.note + "<div/>" + 
					"</div>"
				));
			} else {
				if (opts.text != null) {
					$iopr.before($(
						"<div " + jtxt + ">" + 
							"<div " + text + ">" + opts.text + "<div/>" + 
						"</div>"
					));
				} else {
					$iopr.before($(
						"<div " + itxt + ">" + 
							"<div " + note + ">" + opts.note + "<div/>" + 
						"</div>"
					));
				}
			}
			// set hint box position center
			var height = $hint.css('height');
			height = parseInt(height.match(/\d+/)[0]);
			var client = LD.util.getClient().height;
			var top = client / 2 - height;
			$hint.css('top', top + 'px');
			// show hint box
			$_popups_mask.show();
			$hint.fadeIn('fast');
		}
	},
	pull : function() {
		with (g_popups_style.pull) {
			// create pull box
			$_popups_body.append($(
				"<div id='ld_pop_pull' " + ibox + ">" + 
					"<div " + lose + ">\u53d6&nbsp;\u6d88</div>" +
				"</div>"
			));
			var $pull = $_popups_body.find("#ld_pop_pull");
			// find lose button
			var $lost = $pull.find("div").click(function() {
				$pull.slideUp(100, function() {
					$_popups_bkgr.hide(); $(this).remove();
				});
			});
			// cycle all button
			var args = arguments;
			for (var x = 0; x < args.length; x++) {
				$lost.before($("<ul " + list + "></ul>"));
				var $list = $pull.find("ul:last");
				for (var y = 0; y < args[x].length; y++) {
					if (args[x][y].note != null) {
						$list.append($(
							"<a " +  atag + ">" +
								"<li " + note + ">" + args[x][y].note + "</li>" +
							"</a>"
						));
					} else {
						// set click event
						var href = "href='javascript:void(0);' ",
							serial_x = "serial_x='" + x + "' ",
							serial_y = "serial_y='" + y + "' ",
							text = args[x][y].text;
						if (args[x][y].itel != null) {
							var tel = args[x][y].itel.match(/#\d+(\-?\d+)*#/gi)[0];
							tel = tel.substring(1, tel.length - 1);
							href = "href='tel:" + tel + "' ";
							text = args[x][y].itel.replace(/#\d+(\-?\d+)*#/gi, tel);
							serial_x = serial_y = "";
						}
						$list.append($(
							"<a " + serial_x + serial_y + href + atag + ">" +
								"<li " + item + ">" + text + "</li>" +
							"</a>"
						));
					}
					var $cell = $list.find("a:last");
					if (y == args[x].length - 1) {
						$cell.find("li").css("border-bottom", "none");
					}
					// set click event
					if (args[x][y].note == null) {
						$cell.click(function() {
							$pull.slideUp(100, function() {
								$_popups_bkgr.hide(); $(this).remove();
							});
							var serial_x = $(this).attr("serial_x");
							var serial_y = $(this).attr("serial_y");
							if ($.isFunction(args[serial_x][serial_y].call)) {
								args[serial_x][serial_y].call();
							}
						});
					}
				}
			}
			// click background and remove pull
			$_popups_bkgr.click(function() {
				$pull.slideUp(100, function() {
					$_popups_bkgr.hide(); $(this).remove();
				});
			}).show();
			$pull.slideDown('fast');
		}
	},
	safe : function(opts) {
		with (g_popups_style.sbox) {
			// create safe box
			$_popups_body.append($(
				"<ul id='ld_pop_safe' " + ibox + ">" + 
					"<li " + head + ">\u9ad8\u548c\u7545\u652f\u4ed8\u5bc6\u7801</li>" + 
					"<li " + item + ">\u7b2c\u4e00\u6b65\uff1a\u77ed\u4fe1\u9a8c\u8bc1</li>" + 
					"<li " + item + ">\u624b\u673a\u53f7\u7801\uff1a" + opts.imob + "</li>" + 
					"<li " + item + "><div " + unit + "></div></li>" + 
					"<li " + item + ">\u7b2c\u4e8c\u6b65\uff1a\u652f\u4ed8\u5bc6\u7801</li>" + 
					"<li " + item + "><div " + unit + "></div></li>" + 
					"<li " + item + ">\u5fd8\u8bb0\u4e86\u652f\u4ed8\u5bc6\u7801?&nbsp;" +
						"<a " + atag + " href='" + opts.link + "'>\u524d\u5f80\u91cd\u7f6e</a>" + 
					"</li>" + 
					"<li " + foot + "><div " + unit + "></div></li>" + 
				"</ul>"
			));
			var $safe = $_popups_body.find("#ld_pop_safe");
			var _pswd = _code = _done = '';
			_code = opts.code == null ? '' : opts.code;
			$($safe.find("li")[1]).css({'font-weight' : 'bold'});
			$($safe.find("li")[4]).css({'font-weight' : 'bold'});
			// init smsc
			var $code = $($safe.find("li")[3]).find("div").code({
				imob : opts.imob,
				send : opts.send,
				code : opts.code,
				time : opts.time,
				done : function(done) {
					_done = done;
				},
				call : function(code) {
					_code = code;
				}
			});
			// init pswd
			var $pswd = $($safe.find("li")[5]).find("div").pswd(function(pswd) {
				_pswd = pswd;
			});
			// init oper
			$($safe.find("li")[7]).find("div").oper({
				left : {
					text : '\u53d6\u6d88',
					call : function() {
						$safe.fadeOut(100, function() {
							$_popups_bkgr.hide(); $(this).remove();
							// smsc give up
							$.pop.chio({
								text : '\u662f\u5426\u653e\u5f03\u652f\u4ed8?',
								left : {
									text : '\u5426',
									call : function() {
										opts.auto = false;
										$.pop.safe(opts);
									}
								},
								rite : {
									text : '\u662f',
									call : function() {
										if ($.isFunction(opts.lose)) {
											opts.lose();
										}
									}
								}
							});
						});
					}
				},
				rite : {
					text : '\u786e\u8ba4',
					call : function() {
						// check value
						if (_code == '') {
							$.pop.tips('\u8bf7\u8f93\u5165\u77ed\u4fe1\u9a8c\u8bc1\u7801');
							$code.find('input').focus();
							return;
						}
						if (_pswd == '') {
							$.pop.tips('\u8bf7\u8f93\u5165\u652f\u4ed8\u5bc6\u7801');
							$pswd.find('input').focus();
							return;
						}
						var isok = opts.isok;
						if ($.isFunction(opts.isok)) {
							isok = opts.isok(_code);
						}
						if (isok || isok == 'true') {
							$safe.fadeOut(100, function() {
								$_popups_bkgr.hide(); $(this).remove();
								if ($.isFunction(opts.call)) {
									opts.call(_code, _pswd);
								}
							});
						} else {
							$.pop.hint({
								text : '\u63d0\u793a',
								note : '\u77ed\u4fe1\u9a8c\u8bc1\u7801\u4e0d\u6b63\u786e!',
								call : function() {
									$code.find('input').val('').focus();
								}
							});
						}
					}
				}
			});
			// set hint box position center
			var height = $safe.css('height');
			height = parseInt(height.match(/\d+/)[0]);
			var client = LD.util.getClient().height;
			var top = (client - height) / 2;
			$safe.css('top', top + 'px');
			$_popups_bkgr.show();
			$safe.fadeIn('fast');
			setTimeout(function() {
				if (opts.auto || opts.auto == 'true' || opts.auto == 'auto') {
					_done({
						imob : opts.imob,
						send : opts.send,
						send : opts.time,
						call : function(code) {
							_code = code;
						}
					});
				}
			}, 5e2);
		}
	},
	smsc : function(opts) {
		with (g_popups_style.sbox) {
			// create safe box
			$_popups_body.append($(
				"<ul id='ld_pop_smsc' " + ibox + ">" + 
					"<li " + head + ">\u77ed\u4fe1\u9a8c\u8bc1</li>" + 
					"<li " + text + ">\u624b\u673a\u53f7\u7801\uff1a" + opts.imob + "</li>" + 
					"<li " + item + "><div " + unit + "></div></li>" + 
					"<li " + foot + "><div " + unit + "></div></li>" + 
				"</ul>"
			));
			var $smsc = $_popups_body.find("#ld_pop_smsc");
			// init pswd
			var rest_code = '';
			var $code = $($smsc.find("li")[2]).find("div").code({
				imob : opts.imob,
				send : opts.send,
				call : function(code) {
					rest_code = code;
				}
			});
			$($smsc.find("li")[3]).find("div").oper({
				left : {
					text : '\u53d6\u6d88',
					call : function() {
						$smsc.fadeOut(100, function() {
							$_popups_bkgr.hide(); $(this).remove();
							// smsc give up
							$.pop.chio({
								text : '\u662f\u5426\u653e\u5f03\u9a8c\u8bc1?',
								left : {
									text : '\u5426',
									call : function() {
										$.pop.smsc(opts);
									}
								},
								rite : {
									text : '\u662f',
									call : function() {
										if ($.isFunction(opts.lose)) {
											opts.lose();
										}
									}
								}
							});
						});
					}
				},
				rite : {
					text : '\u786e\u8ba4',
					call : function() {
						// check value
						if (rest_code != '') {
							var isok = opts.isok;
							if ($.isFunction(opts.isok)) {
								isok = opts.isok(rest_code);
							}
							if (isok || isok == 'true') {
								$smsc.fadeOut(100, function() {
									$_popups_bkgr.hide(); $(this).remove();
									if ($.isFunction(opts.call)) {
										opts.call(rest_code);
									}
								});
							} else {
								$.pop.hint({
									text : '\u63d0\u793a',
									note : '\u77ed\u4fe1\u9a8c\u8bc1\u7801\u4e0d\u6b63\u786e!',
									call : function() {
										$code.find('input').val('').focus();
									}
								});
							}
						} else {
							$.pop.tips('\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801');
						}
					}
				}
			});
			// set smsc box position center
			var height = $smsc.css('height');
			height = parseInt(height.match(/\d+/)[0]);
			var client = LD.util.getClient().height;
			var top = client / 2 - height;
			$smsc.css('top', top + 'px');
			$_popups_bkgr.show();
			$smsc.fadeIn('fast');
		}
	},
	ajax : function(opts) {
		var _opts = $.extend({
			type : 'post', timeout : 3e4, dataType : 'json',
			beforeSend : function() {
				var msg = 'Loading...';
				msg = (opts.msg == null) ? msg : opts.msg;
				$.pop.load(true, msg);
			},
			error : function() {
				$.pop.load(false, function() {
			    	setTimeout(function() {
			    		$.pop.tips('\u7cfb\u7edf\u7e41\u5fd9');
			    	}, 666);
			    });
			}
		}, opts);
		_opts.success = function (response) {
			var flag = 'hide';
			if (opts.load == 'keep') {
				flag = 'show';
			}
			$.pop.load(flag, function() {
				if ($.isFunction(opts.success)) {
					opts.success(response);
				}
			});
		};
		$.ajax(_opts);
	}
};
(function($) {
	$.fn.extend({
		pswd : function(call) {
			return this.each(function() {
				with (g_popups_style.pswd) {
					$(this).html(
						"<div " + ibox + ">" + 
							"<input " + hide + " type='tel' maxlength='6'>" + 
							"<div>" + 
								"<div " + left + "></div>" + 
								"<div " + item + "></div>" + 
								"<div " + item + "></div>" + 
								"<div " + item + "></div>" + 
								"<div " + item + "></div>" + 
								"<div " + rite + "></div>" + 
							"</div>" + 
						"</div>"
					).show();
					var $pswd = $(this).find("div:first");
					var $hide = $pswd.find("input:first").val('');
					var $ipts = $pswd.find("div:first").find("div").html('');
					$ipts.focus(function(e) { $hide.focus(); });
					$ipts.click(function(e) { $hide.focus(); });
					$hide.keyup(function(e) {
						var length = 0, pswd = $hide.val();
						for (var i = 0; i < $ipts.length; i++) {
							if (i < pswd.length) {
								$($ipts[i]).html('\u25cf');
								length++;
							} else {
								$($ipts[i]).html(' ');
							}
						}
						if (length == $ipts.length) {
							$hide.blur();
							if ($.isFunction(call)) {
								call(pswd);
							}
						}
					});
					$pswd.show();
				}
			});
		},
		code : function(opts) {
			return this.each(function() {
				with (g_popups_style.code) {
					$(this).html(
						"<div " + ibox + ">" + 
							"<input placeholder='\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801' " + into + " type='tel' maxlength='6'>" + 
							"<div " + send + ">\u53d1\u9001\u9a8c\u8bc1\u7801</div>" + 
						"</div>"
					).show();
					var $code = $(this).find("div:first");
					var $into = $code.find("input").val(opts.code == null ? '' : opts.code);
					var $send = $code.find("div");
					$into.keyup(function(e) {
						if ($.isFunction(opts.call)) {
							opts.call($into.val());
						}
					});
					var sending = function(opts) {
						if ($.isFunction(opts.send)) {
							$send.html('\u53d1\u9001\u4e2d...');
							$send.unbind('click');
							try {
								opts.send(opts.imob);
								var _dono = dono.substring(7, dono.length - 1);
								$send.attr('style', _dono).unbind('click');
								var time = opts.time;
								if (time == null) {
									time = 60;
								}
								$send.html(time + '&nbsp;\u79d2');
								var timer = null;
								timer = setInterval(function() {
									if (time > 1) {
										$send.html((--time) + '&nbsp;\u79d2').unbind('click');
									} else {
										clearInterval(timer);
										$send.attr('style', send.substring(7, dono.length - 1));
										$send.click(function(e) {
											sending(opts);
										});
										$send.html('\u53d1\u9001\u9a8c\u8bc1\u7801');
									}
								}, 1000);
							} catch (e) {
								$.pop.tips(e);
								$send.attr('style', send.substring(7, dono.length - 1));
								$send.click(function(e) {
									sending(opts);
								});
								$send.html('\u53d1\u9001\u9a8c\u8bc1\u7801');
							}
						}
					};
					if ($.isFunction(opts.done)) {
						opts.done(sending);
					}
					$send.click(function(e) {
						sending(opts);
					});
					$code.show();
				}
			});
		},
		oper : function(opts) {
			return this.each(function() {
				with (g_popups_style.oper) {
					$(this).html(
						"<div " + ibox + ">" + 
							"<div " + left + ">" + opts.left.text + "</div>" + 
							"<div " + rite + ">" + opts.rite.text + "</div>" + 
						"</div>"
					).show();
					var $oper = $(this).find("div:first");
					var $btns = $oper.find("div");
					$($btns[0]).click(function(e) {
						if ($.isFunction(opts.left.call)) {
							opts.left.call();
						}
					});
					$($btns[1]).click(function(e) {
						if ($.isFunction(opts.rite.call)) {
							opts.rite.call();
						}
					});
					$oper.show();
				}
			});
		}
	});
})(jQuery);