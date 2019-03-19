var _BMapOptions = { map : 'map', lng : 'lng', lat : 'lat', address : 'address' };
function BaiduMap(options) {
	_BMapOptions = $.extend(_BMapOptions, options);
	this.getLngLat = function() {
		var $address = $("#" + _BMapOptions.address);
		if ($address.val() != null && $address.val() != '') {
			$.getScript("http://api.map.baidu.com/api?v=1.4&callback=_BMapCallback", null);
		} else {
			alert("\u83b7\u53d6GPS\u524d\u8bf7\u5148\u586b\u5199\u8be6\u7ec6\u5730\u5740");
		}
	};
}
function _BMapCallback() {
	var $lng = $("#" + _BMapOptions.lng);
	var $lat = $("#" + _BMapOptions.lat);
	var $address = $("#" + _BMapOptions.address);
	var map = new BMap.Map(_BMapOptions.map); // 创建地图实例
	var point = new BMap.Point($lng.val(), $lat.val()); // 创建点坐标
	var marker = new BMap.Marker(point); // 创建标注
	map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
	map.enableDragging();// 启用地图拖拽事件，默认启用(可不写)
	map.enableScrollWheelZoom(); // 启用地图滚轮放大缩小
	map.enableDoubleClickZoom(); // 启用鼠标双击放大，默认启用(可不写)
	map.enableKeyboard();// 启用键盘上下左右键移动地图
	map.addOverlay(marker); // 将标注添加到地图中
	map.addEventListener("click", function(e) {
		map.removeOverlay(marker);
		point = new BMap.Point(e.point.lng, e.point.lat);
		marker = new BMap.Marker(point);
		map.addOverlay(marker);
		$lng.val(e.point.lng);
		$lat.val(e.point.lat);
	});
	// 将地址解析结果显示在地图上，并调整地图视野
	var myGeo = new BMap.Geocoder();
	myGeo.getPoint($address.val(), function(point) {
		if (point) {
			map.removeOverlay(marker);
			map.centerAndZoom(point, 16);
			marker = new BMap.Marker(point);
			map.addOverlay(marker);
			$lng.val(point.lng);
			$lat.val(point.lat);
		}
	}, null);
}