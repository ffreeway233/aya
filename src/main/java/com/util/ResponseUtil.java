package com.util;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ResponseUtil {

	public static void write(HttpServletResponse response, Object o) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(o.toString());
		out.flush();
		out.close();
	}

	public static void writeForJSONObject(HttpServletResponse response, JSONObject jsonObject) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	public static void write(HttpServletResponse response, String callback, Object o) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (callback != null && !"".equals(callback)) {
			out.print(callback += "(" + o.toString() + ")");
		} else {
			out.print(o.toString());
		}
		out.flush();
		out.close();
	}

	public static void write(HttpServletResponse response, Map<String, Object> map, JsonConfig jsonConfig)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObject = null;
		if (jsonConfig == null) {
			jsonObject = JSONObject.fromObject(map);
		} else {
			jsonObject = JSONObject.fromObject(map, jsonConfig);
		}
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	public static void write(HttpServletResponse response, String callback, Map<String, Object> map,
			JsonConfig jsonConfig) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObject = null;
		if (jsonConfig == null) {
			jsonObject = JSONObject.fromObject(map);
		} else {
			jsonObject = JSONObject.fromObject(map, jsonConfig);
		}
		PrintWriter out = response.getWriter();
		if (callback != null && !"".equals(callback)) {
			out.print(callback += "(" + jsonObject.toString() + ")");
		} else {
			out.print(jsonObject.toString());
		}
		out.flush();
		out.close();
	}
}
