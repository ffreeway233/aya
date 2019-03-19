(function($){
	$.fn.extend({
		multiupf : function(options) {
			options = $.extend({
				upsLimit : '1MB', dataType : 'json', fileName : 'upload', fileExts : '*'
			}, options);
			return this.each(function() {
				var settingOptions = {}, id = $(this).attr('id'), uploader = options.uploader;
				settingOptions.height = (options.height == null) ? 31 : options.height;
				settingOptions.width = (options.width == null) ? 83 : options.width;
				var defImage = apiPath + '/multiupf/button.png';
				settingOptions.buttonImage = (options.image == null) ? defImage : options.image;
				if (options.isRadius || options.isRadius == 'true') {
					settingOptions.buttonClass = 'on-border-radius';
				} else {
					settingOptions.buttonClass = 'no-border-radius';
				}
				var uploadifyOptions = { 
						'auto' : true, 'method' : 'post', 'multi' : true, uploadLimit : 9999,
						'fileTypeDesc' : options.typeDesc, 'fileTypeExts' : options.fileExts,
						'fileSizeLimit' : options.limit, 'fileObjName' : options.fileName,
				        'swf' : apiPath + '/multiupf/uploadify3/uploadify.swf', 'uploader': uploader, 
				        'queueID' : 'some_file_queue', 'onSelect' : function(file) { 
				        	if ($.isFunction(options.onSelect)) { options.onSelect(); }
				        }, 'onUploadSuccess' : function(file, data, response) {
				        	data = (options.dataType == 'json') ? $.parseJSON(data) : data;
				       		if($.isFunction(options.complete)) { options.complete(data); }
				     	}, 'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
				            if ($.isFunction(options.progress)) { options.progress(Math.round(bytesUploaded / bytesTotal * 100) + "%"); }
				        }, 'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				        	if ($.isFunction(options.onErrors)) { options.onErrors(errorString); }
				     	}
					};
				uploadifyOptions = $.extend(settingOptions, uploadifyOptions);
				$(this).append($("<input type='file' id='sp_multiupf_upload_" + id + "'/>"))
						.find("#sp_multiupf_upload_" + id).uploadify(uploadifyOptions);
			});
		}
	});
})(jQuery);