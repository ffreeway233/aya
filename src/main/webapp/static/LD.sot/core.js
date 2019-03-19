/** 
 * core.js
 * author: 胡永伟
 * date: 2015-04-15
 * version: 1.2.0
 */
/** ******************* String Prototype Extends Begin ******************** */
if (String.prototype.trim === undefined) { String.prototype.trim = function() { return this.replace(/(^\s+)|(\s+$)/g, ""); }; }
if (String.prototype.isEmpty === undefined) { String.prototype.isEmpty = function() { return (this.match(/^\s*$/) != null); }; }
if (String.prototype.isNotBlank === undefined) { String.prototype.isNotBlank = function() { return (this.match(/^\s*$/) == null); }; }
if (String.prototype.startsWith === undefined) { String.prototype.startsWith = function(s) { return (this.match("^" + s) == s); }; }
if (String.prototype.endsWith === undefined) { String.prototype.endsWith = function(s) { return (this.match(s + "$") == s); }; }
if (String.prototype.format === undefined) { String.prototype.format = function(args) { var s = this; for (var i = 0; i < arguments.length; i++) { s = s.replace('%s', arguments[i]); } return s; }; }
/*--------------------------------------------------------------------------*/
if (String.prototype.isEmail === undefined) { String.prototype.isEmail = function() { return (this.match(/^\w+\@\w+(\.\w+)+$/) != null); }; }
if (String.prototype.isMobile === undefined) { String.prototype.isMobile = function() { return (this.match(/^[1][345789][0-9]{9}$/) != null); }; }
if (String.prototype.isTelephone === undefined) { String.prototype.isTelephone = function() { return (this.match(/^[0-9]{4}[-][0-9]{7,8}$/) != null); }; }
if (String.prototype.isIdCard === undefined) { String.prototype.isIdCard = function() { return (this.match(/^([1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3})|([1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|[X|x]))$/) != null); }; }
if (String.prototype.isName === undefined) { String.prototype.isName = function() { return (this.match(/^[A-Za-z\s\u4e00-\u9fa5]+$/) != null); }; }
if (String.prototype.isUsername === undefined) { String.prototype.isUsername = function() { return (this.match(/^\w{4,16}$/) != null); }; }
if (String.prototype.isUrl === undefined) { String.prototype.isUrl = function() { return (this.match(/^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:\/~\+#]*[\w\-\@?^=%&amp;\/~\+#])?$/) != null); }; }
if (String.prototype.isPositive === undefined) { String.prototype.isPositive = function() { return (this.match(/^[0-9]*[1-9][0-9]*$/) != null); }; }
/*--------------------------------------------------------------------------*/
if (String.prototype.encode === undefined) { String.prototype.encode = function() { return encodeURIComponent(this); }; }
if (String.prototype.decode === undefined) { String.prototype.decode = function() { return decodeURI(this.replace(/\+/g, ' ').replace(/%23/gi, '#').replace(/%24/gi, '$').replace(/%26/gi, '&').replace(/%2B/gi, '+').replace(/%2C/gi, ',').replace(/%2F/gi, '/').replace(/%3A/gi, ':').replace(/%3B/gi, ';').replace(/%3D/gi, '=').replace(/%3F/gi, '?').replace(/%40/gi, '@')); }; }
/** ******************** String Prototype Extends End ********************* */

/** ******************** Array Prototype Extends Begin ******************** */
//if (Array.prototype.indexOf === undefined) { Array.prototype.indexOf = function(item) { for ( var i = 0; i < this.length; i++) { if (this[i] == item) { return i; } } return -1; }; }
//if (Array.prototype.remove === undefined) { Array.prototype.remove = function(item) { var index = this.indexOf(item); if (index > -1) { this.splice(index, 1); } return this; }; }
/** ********************* Array Prototype Extends End ********************* */

/** ******************* Number Prototype Extends Begin ******************** */
if (Number.prototype.limit === undefined) { Number.prototype.limit = function(min, max) { return (this < min) ? min : ((this > max) ? max : this); }; }
if (Number.prototype.toSize === undefined) { Number.prototype.toSize = function(digit) { var units = [ 'B', 'KB', 'MB', 'GB', 'TB' ], t; for ( var i = 0; i < units.length; i++) { t = this / ((i == 0) ? 1 : Math.pow(1024, i)); if (t < 1024) { return t.toFixed((digit == null) ? 2 : digit) + units[i]; } } return null; }; }
/** ******************** Number Prototype Extends End ********************* */

/** *********************** HttpScriptRequest Begin *********************** */
function HttpScriptRequest(uri) { this.uri = uri.trim(); };
HttpScriptRequest.prototype.getRequestUri = function() { return this.uri; };
HttpScriptRequest.prototype.getWebAppPath = function() { if (this.webapp == null) { this.webapp = this.uri.substring(0, this.uri.indexOf('/', this.uri.indexOf('/', 7) + 1)); } return this.webapp; };
HttpScriptRequest.prototype.getWebServer = function() { if (this.server == null) { this.server = this.uri.substring(0, this.uri.indexOf('/', 7)); } return this.server; };
HttpScriptRequest.prototype.getQueryString = function() { if (this.query == null) { this.query = (this.uri.indexOf('?') > -1) ? this.uri.substring(this.uri.indexOf('?') + 1, this.uri.length) : ''; } return this.query; };
HttpScriptRequest.prototype.getParameters = function() { if (this.params == null) { this.params = new Object(); var begin = end = 0, array = new Array(), query = this.getQueryString(); while ((end = query.indexOf('&', end)) > begin) { array.push(query.substring(begin, end)); begin = (++end); } array.push(query.substring(begin, query.length)); for ( var i = 0; array != null && i < array.length; i++) { if (array[i].length > 0) { if ((end = array[i].indexOf('=')) > (begin = 0)) { this.params[array[i].substring(begin, end)] = array[i].substring(end + 1, array[i].length); } else if (end == -1) { this.params[array[i]] = ''; } } } } return this.params; };
/** ************************ HttpScriptRequest End ************************ */

/** *************************** Fast Core Begin *************************** */
var LD = new function() {
	var jquery = /^(jquery)((\-\d+)(\.\d+)*)?(\.min)?(\.js)$/i,
		script = { 
			'valid' : function(uri) { if (uri.indexOf('http://') == 0) { return uri.indexOf('/', 7); } else if (uri.indexOf('/') == 0) { return uri.indexOf('/', 1); } else { throw new Error(0, 'fast.script.valid : The uri[' + uri + '] is not valid.'); } }, 
			'exist' : function(uri) { var scripts, script, index, isJQ = false; if (uri.indexOf('.js') > -1) { scripts = document.scripts; } else if (uri.indexOf('.css') > -1) { scripts = document.styleSheets; } else { throw new Error(0, 'fast.script.exist : The uri[' + uri + '] is not a js or css.'); } if ((index = uri.indexOf('?')) > -1) { uri = uri.substring(0, index); } if (uri.substring(uri.lastIndexOf('/') + 1).match(jquery)) { isJQ = true; } for (var i = 0; scripts != null && i < scripts.length; i++) { script = scripts[i].src == null ? scripts[i].href : scripts[i].src; if (script == null) { return false; } if ((index = script.indexOf('?')) > -1) { script = script.substring(0, index); } if (isJQ) { if (script.substring(script.lastIndexOf('/') + 1).match(jquery)) { return true; } } else { if (script.indexOf(uri) > -1 || uri.indexOf(script) > -1) { return true; } } } return false; }
	}; return {
		'core' : {
			'getJsRequest' : function() { return new HttpScriptRequest(document.scripts[document.scripts.length - 1].src); },
			'getUpperPath' : function(uri) { if (script.valid(uri) == -1) { throw new Error(0, 'LD.core.getUpperPath : The uri[' + uri + '] has no upper path.'); } return uri.substring(0, uri.lastIndexOf('/')); },
			'importScript' : function(uri) { if (!script.exist(uri)) { if (uri.indexOf('.js') > -1) { document.write('<script type="text/javascript" src="' + uri + '"></script>'); } else if (uri.indexOf('.css') > -1) { document.write('<link rel="stylesheet" type="text/css" href="' + uri + '"/>'); } } }
		},
		'json' : {
			'parse' : function(template, jsonList, dealWith) { var result = ''; for (var i = 0; i < jsonList.length; i++) { var keys = template.match(/#\w+(\.\w+)*({\d+})?#/g), obj = template; for (var j = 0; j < keys.length; j++) { var idx = keys[j].match(/{\d+}/), end; if (idx != null) { end = keys[j].indexOf(idx[0]); idx = idx[0].match(/\d+/)[0]; } else { end = keys[j].length - 1; } var key = keys[j].substring(1, end), val = jsonList[i], arr = key.split('\.'); for (var k = 1; k < arr.length; k++) { if (arr[k].isNotBlank() && val != null) { val = val[arr[k]]; if ($.isFunction(dealWith)) { val = dealWith(key, val, idx); } if ($.isArray(val) && val.length > 0) { obj = LD.json.subParse(obj, key, val, dealWith); } } } key = (idx == null) ? key : (key + '{' + idx + '}'); obj = obj.split('#' + key + '#').join(val == null ? "" : val); } result += obj; } return result; },
			'subParse' : function(dest, flag, jsonList, dealWith) { var template = $('#' + flag.replace(/\./g, '_') + '_template').html(), result = LD.json.parse(template, jsonList, dealWith); return dest.split('#' + flag + '#').join(result); },
		},
		'util' : {
			'setCookie' : function(key, value, hour) { if (key != null && key != '') { if (hour == null || hour == '' || hour == 0) { document.cookie = key + "=" + value + ";"; } else { var date = new Date(); date.setHours(date.getHours() + hour); document.cookie = escape(key + "=" + value) + "; expires=" + date.toGMTString(); } } },
			'getCookie' : function(key) { var cookies = unescape(document.cookie); if (cookies != null && cookies != '') { var params = cookies.split(/\; /); if (params != null && params.length > 0) { for ( var p in params) { if (params[p].split(/\=/)[0] == key) { return params[p].split(/\=/)[1]; } } } } return null; },
			'getClient' : function() { return { 'width' : window.top.document.documentElement.clientWidth, 'height' : window.top.document.documentElement.clientHeight }; }
		},
		'data' : {
			'insert' : function(database, item) { if ((item != null) && item.isNotBlank()) { item = item.encode(); var data = localStorage.getItem(database); if ((data == null) || (data == '')) { data = item; } else { if (data.indexOf(item) == -1) { data = (item + '&' + data); } } localStorage.setItem(database, data); } },
			'remove' : function(database, item) { var data = localStorage.getItem(database); if ((data != null) && (data != '') && (item != null) && item.isNotBlank()) { item = item.encode(); data = data.replace('&' + item, '').replace(item + '&', '').replace(item, ''); } localStorage.setItem(database, data); },
			'select' : function(database) { var data = localStorage.getItem(database); var result = new Array(); if ((data != null) && data.isNotBlank()) { var list = data.split('&'); for (var i = 0; i < list.length; i++) { result.push(list[i].decode()); } } return result; },
			'clears' : function(database) { localStorage.removeItem(database); }
		}
	};
};
/** **************************** Fast Core End **************************** */

/** ****************************** Import Ex ****************************** */
var request = LD.core.getJsRequest();
var apiPath = LD.core.getUpperPath(request.getRequestUri());
LD.core.importScript(apiPath + '/jquery.js');
/** ****************************** Import Ex ****************************** */