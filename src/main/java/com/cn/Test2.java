package com.cn;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Test2 {
	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";// 获取access
	// url
	public static final String ACCESS_TOKEN = "JyP5Lvnkltr91O93lhiS_1-xehBKzQ-wfeNkaj2HVE57TxxgxrLK99nzWtESWHPQL0hKzT9c4TsI5Bl7pRXHKTyJn_VdP3mPswQwDCKw";
	public static final String TYPE = "jsapi";

	// https://api.weixin.qq.com/cgi-bin/ticket/getticket?
	// access_token=JyP5LvnEyXkltr91O93lhiS_1-xehBKzQ-wfeNkaj2HVE57TxxgxrLK99nzWtESWHPQL0hKzT9c4TsI5Bl7pRXHKTyJn_VdP3mPswQwDCKw
	// &type=jsapi
	// 获取token
	@SuppressWarnings("finally")
	public static String getToken(String apiurl, String access_token,
			String type) {
		String turl = String.format("%s?access_token=%s&type=%s", apiurl,
				access_token, type);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(turl);
		String result = null;
		try {
			HttpResponse res = client.execute(get);
			String responseContent = null; // 响应内容
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			JSONObject json = new JSONObject(responseContent);
			// 将json字符串转换为json对象
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (json.get("errcode") == null) {
					// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
				} else if ((Integer) json.get("errcode") != 0) {
					result = "false";
				} else {
					// 正常情况下{"errcode":0,"errmsg":"ok","ticket":"sM4AOVdWfPE4DxkXGEs8VGn-qh0Uv1cqlJvSAgSvGooboCOUF3suAh7Jwgc7A-Z7jNVrp3AbxVDsDXjPG9Vsfg","expires_in":7200}
					result = json.get("ticket").toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接 ,释放资源
			get.releaseConnection();
			client.getConnectionManager().shutdown();
			get = null;
			client = null;
			return result;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("=========1获取token=========");
		String accessToken = getToken(GET_TOKEN_URL, ACCESS_TOKEN, TYPE);// 获取token
		if (accessToken != null)
			System.out.println(accessToken);
	}

}