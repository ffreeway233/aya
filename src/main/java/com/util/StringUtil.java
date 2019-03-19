package com.util;

/**
 * @author HanShuai
 * @project_name Hadmin
 * @date 2015-7-28
 * @time 下午1:54:44
 */
public class StringUtil {

	/**
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim()) || "null".equals(str);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str.trim()) && !"null".equals(str);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}
}
