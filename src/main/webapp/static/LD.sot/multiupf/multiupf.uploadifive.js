/** 
 * MultiUpf for UploadiFive
 * author: LD.HU
 * date: 2014-12-12
 * version: 1.0.0
 */
(function($){
	$.fn.extend({
		multiupf : function(options) {
			options = $.extend({ limit : '1MB', fileName : 'upload', 
				dataType : 'json', fileType : 'image/*' }, options);
			return this.each(function() {
				var generateId = $(this).attr('id'), settingOpts = new Object();
				settingOpts.height = (options.height == null) ? 31 : options.height;
				settingOpts.width = (options.width == null) ? 83 : options.width;
				settingOpts.buttonText = ''; settingOpts.buttonClass = 'ld_cz_bt';
				var uploadifiveOpts = { 
					'multi' : true, 'fileType' : options.fileType, 'uploadLimit' : 999,
					'fileSizeLimit' : options.limit, 'uploadScript' : options.uploader,
					'fileObjName' : options.fileName, 'method' : 'post', 'auto' : true,
					'queueID' : 'files_queue_lines',
			        'onError' : function(errorType, file) { 
			        	if ($.isFunction(options.onErrors)) { 
			        		options.onErrors(errorType); 
			        	} 
			        }, 'onProgress' : function(file, e) { 
			        	if ($.isFunction(options.progress)) {
			        		options.progress(Math.round((e.loaded / e.total) * 100) + "%"); 
			        	} 
			        }, 'onUploadComplete' : function(file, data) { 
			        	if ($.isFunction(options.complete)) { 
			        		data = (options.dataType == 'json') ? $.parseJSON(data) : data;
			        		options.complete(data); 
			        	} 
			        }
				};
				if ($("#files_queue_lines").length == 0) {
					$(document.body).append($("<div id='files_queue_lines'><div>"));
					$("#files_queue_lines").hide();
				}
				generateId = 'ld_multiupf_uploadifive_' + generateId;
				settingOpts = $.extend(settingOpts, uploadifiveOpts);
				$(this).append(
					$("<input type='file' id='" + generateId + "'/>")
				).find("#" + generateId).uploadifive(settingOpts);
			});
		}
	});
})(jQuery);