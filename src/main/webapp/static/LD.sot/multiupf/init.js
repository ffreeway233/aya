/** 
 * MultiUpf for Uploadify2
 * author: LD.HU
 * date: 2014-12-12
 * version: 1.0.0
 */
var _multiupf_ver = LD.core.getJsRequest().getParameters().ver;
if (_multiupf_ver == "5") {
	LD.core.importScript(apiPath + "/multiupf/uploadifive/uploadifive.css");
	LD.core.importScript(apiPath + "/multiupf/uploadifive/jquery.uploadifive.js");
	LD.core.importScript(apiPath + "/multiupf/multiupf.uploadifive.js");
} else if (_multiupf_ver == "3") {
	LD.core.importScript(apiPath + "/multiupf/uploadify3/uploadify.css");
	LD.core.importScript(apiPath + "/multiupf/uploadify3/jquery.uploadify.js");
	LD.core.importScript(apiPath + "/multiupf/multiupf.uploadify3.js");
} else {
	LD.core.importScript(apiPath + "/multiupf/uploadify2/uploadify.css");
	LD.core.importScript(apiPath + "/multiupf/uploadify2/jquery.uploadify.js");
	LD.core.importScript(apiPath + "/multiupf/uploadify2/swfobject.js");
	LD.core.importScript(apiPath + "/multiupf/multiupf.uploadify2.js");
}