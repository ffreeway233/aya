/**
 * 
 */
package util;

import java.net.URLEncoder;

import com.util.HttpRequestUtil;

/**
 * Title: photography<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017 <br>
 * Create DateTime: 2017-9-13 上午8:54:03 <br>
 * 
 * @author freeway
 */
public class HongYouZiDuanXinUtil {

	public static String sends(String phone) {
		
		int yzm = Tool.getYzm(100000, 999999);
		// 红柚子短信
		String value = "【Overtime】验证码：" + yzm
				+ "，请在2分钟之内输入完成，为保护您的账号安全，请勿转发他人。";
		try {
			String get = HttpRequestUtil.sendGet(
					"http://smsapi.redyouzi.com/sms-partner/httpserver/"
							+ GetWX.getPro("CLIENTID") + "/sendsms",
					"ver=2.0&password=" + GetWX.getPro("PASSWORD") + "&mobile="
							+ phone + "&smstype=4" + "&content="
							+ URLEncoder.encode(value, "utf-8") + "&sendtime="
							+ "&extend=" + "&batchid=");
			System.out.println(get);
			return yzm+"";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return yzm+"";
		}
	}

}
