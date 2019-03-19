package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.util.ResponseUtil;

/**
 * Title: Boerdiqi<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-4-11 下午5:08:53 <br>
 * @author fccy
 */
public class BaseController {

	@ExceptionHandler(value = { IllegalArgumentException.class })
	public void exceptionHandle(HttpServletResponse response,
			IllegalArgumentException argumentException) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hasError", true);
		map.put("errorInfo", argumentException.getMessage());
		ResponseUtil.writeForJSONObject(response, JSONObject.fromObject(map));
	}



	@ExceptionHandler(value = { RuntimeException.class })
	public void exceptionHandle2(HttpServletResponse response,
			RuntimeException argumentException) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hasError", true);
		map.put("errorInfo", argumentException.getMessage());
		ResponseUtil.writeForJSONObject(response, JSONObject.fromObject(map));
	}
	
	@ExceptionHandler(value = { IllegalStateException.class })
	public void exceptionHandle3(HttpServletResponse response,
			IllegalStateException illegalStateException) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hasError", true);
		map.put("errorInfo", illegalStateException.getMessage());
		ResponseUtil.writeForJSONObject(response, JSONObject.fromObject(map));
	}
	@ExceptionHandler(value = { UnsupportedEncodingException.class })
	public void exceptionHandle4(HttpServletResponse response,
			IllegalArgumentException argumentException) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hasError", true);
		map.put("errorInfo", argumentException.getMessage());
		ResponseUtil.writeForJSONObject(response, JSONObject.fromObject(map));
	}
	@ExceptionHandler(value = { Exception.class })
	public void exceptionHandle5(HttpServletResponse response,
			IllegalArgumentException argumentException) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hasError", true);
		map.put("errorInfo", argumentException.getMessage());
		ResponseUtil.writeForJSONObject(response, JSONObject.fromObject(map));
	}
}
