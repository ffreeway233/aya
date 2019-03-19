package com.wingo.util;

import util.GetWX;

public class ConfigUtil {
	public final static String APPID = GetWX.getPro("appid");
	public final static String APP_SECRECT = GetWX.getPro("secret");
	public final static String TOKEN = "weixinCourse";
	public final static String MCH_ID = GetWX.getPro("mchid");
	public final static String API_KEY = GetWX.getPro("apikey");
	public final static String SIGN_TYPE = "MD5";
	public final static String CERT_PATH = GetWX.getPro("path");
	public final static String NOTIFY_URL = "http://" + GetWX.getPro("yuming") + "/user/paySuccess.shtml";
	public final static String SUCCESS_URL = "/front/toReady.shtml?ogno=";
	public final static String REDIRECT_URI = "http://" + GetWX.getPro("yuming") + "/index/red.jsp";
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
}
