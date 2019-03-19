/** 
 * MultiUpf For Uploadify2
 * author: LD.HU
 * date: 2014-12-13
 * version: 1.0.0
 */
(function($){
	$.fn.extend({
		multiupf : function(options) {
			options = $.extend({ 
				upsLimit : 20971520, dataType : 'json', fileName : 'upload', fileExts : '*.*'
			}, options);
			return this.each(function() {			  
				var generateUID = $(this).attr('id'), optImgUrl = options.imageUrl,
					btnImgUrl = apiPath + '/multiupf/button.png', settingOpts = {};
				var uploadifiveOpts = {
					'sizeLimit'	   : options.upsLimit,
					'fileExt'	   : options.fileExts,
					'fileDataName' : options.fileName,
					'script'	   : options.uploader,
					'fileDesc'	   : options.fileDesc,
					'height'	   : options.height == null ? 31 : options.height,
					'width'		   : options.width == null ? 83 : options.width,
					'uploader'	   : apiPath + '/multiupf/uploadify2/uploadify.swf',
					'cancelImg'	   : apiPath + '/multiupf/cancel.png',
					'buttonImg'	   : ((optImgUrl == null) ? btnImgUrl : optImgUrl),
					'queueID'	   : 'multiupf_queue',
					'multi'		   : true,
					'auto'		   : true,
			        'onError'	   : function(event, queueId, fileObj, errorObj) { 
			        	if ($.isFunction(options.onErrors)) { 
			        		options.onErrors(errorObj);	// errorObj.type | errorObj.info
			        	} 
			        }, 
			        'onProgress'   : function(event, queueId, fileObj, data) { 
			        	if ($.isFunction(options.progress)) {
			        		options.progress(data);	// data.percentage | data.speed
			        	}
			        },
			        'onComplete'   : function(event, queueId, fileObj, response, data) { 
			        	if ($.isFunction(options.complete)) { 
			        		if (options.dataType == 'json') {	// transform
			        			response = $.parseJSON(response);
			        		}
			        		options.complete(response); 
			        	} 
			        },
			        'onSelect':function(event,queueID,fileObj){
			        	if ($.isFunction(options.select)) { 
			        		options.select();
			        	} 
			        }
				};
				if ($("#multiupf_queue").length == 0) {
					$(document.body).append($("<div id='multiupf_queue'><div>"));
					$("#multiupf_queue").hide();
				}
				generateUID = 'ld_multiupf_uploadify_' + generateUID;
				settingOpts = $.extend(settingOpts, uploadifiveOpts);
				$(this).append(
					$("<a href='javascript:;' id='" + generateUID + "'></a>")
				).find("#" + generateUID).uploadify(settingOpts);
			});
		}
	});
})(jQuery);