package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	/**
	 * 通过GET方式发起http请求
	 */
	public void requestByGetMethod() {
		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = getHttpClient();
		try {
			// 用get方法发送http请求
			HttpGet get = new HttpGet("http://www.baidu.com");
			System.out.println("执行get请求:...." + get.getURI());
			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpClient.execute(get);
			try {
				// response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					System.out.println("响应状态码:" + httpResponse.getStatusLine() + "," + HttpStatus.SC_OK);
					System.out.println("-------------------------------------------------");
					System.out.println("响应内容:" + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("-------------------------------------------------");
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * POST方式发起http请求
	 */
	public static void requestByPostMethod(String url) {
		CloseableHttpClient httpClient = getHttpClient();
		try {
			HttpPost post = new HttpPost(url); // 这里用上本机的某个工程做测试
			// 创建参数列表
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			// url格式编码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
			System.out.println("POST 请求...." + post.getURI());
			// 执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					System.out.println("-------------------------------------------------------");
					System.out.println(EntityUtils.toString(entity, "UTF-8"));
					System.out.println("-------------------------------------------------------");
				}
			} finally {
				httpResponse.close();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeHttpClient(httpClient);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	public static void closeHttpClient(CloseableHttpClient client) throws IOException {
		if (client != null) {
			client.close();
		}
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		String response = null;
		// 创建默认的httpClient实例
		CloseableHttpClient httpClient = getHttpClient();
		// 用get方法发送http请求
		HttpGet get = new HttpGet(url);
		try {
			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpClient.execute(get);
			try {
				// response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					// System.out.println("响应状态码:" +
					// httpResponse.getStatusLine() + "," + HttpStatus.SC_OK);
					// System.out.println("-------------------------------------------------");
					// System.out.println("响应内容:" +
					// EntityUtils.toString(entity, "UTF-8"));
					// System.out.println("-------------------------------------------------");
					response = EntityUtils.toString(entity, "UTF-8");
					;
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				get.releaseConnection();
				closeHttpClient(httpClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;

	}

	/**
	 * post请求，带普通参数
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = getHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		HttpPost post = new HttpPost(url);
		String response = null;
		try {
			// 创建参数
			for (String key : keySet) {
				list.add(new BasicNameValuePair(key, params.get(key)));
			}
			// url格式编码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
			// System.out.println("POST 请求...." + post.getURI());
			// 执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					// System.out.println("-------------------------------------------------------");
					// System.out.println(EntityUtils.toString(entity,
					// "UTF-8"););
					// System.out.println("-------------------------------------------------------");
					response = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				post.releaseConnection();
				closeHttpClient(httpClient);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * post请求,参数json
	 * 
	 * @param url
	 * @param json
	 * @return
	 */

	public static String postJson(String token,String url, JSONObject json) {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(url);
		String response = null;
		try {
			post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "UTF-8"));
			httpclient.executeMethod(post);
			InputStream inputStream = post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str.replace("\\", ""));
			}
			response = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
			httpclient.getHttpConnectionManager().closeIdleConnections(0);
		}
		return response;
	}
	
	
	public static String postString(String url, String json) {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(url);
		String response = null;
		try {
			post.setRequestEntity(new StringRequestEntity(json, "application/json", "UTF-8"));
			httpclient.executeMethod(post);
			InputStream inputStream = post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str.replace("\\", ""));
			}
			response = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
			httpclient.getHttpConnectionManager().closeIdleConnections(0);
		}
		return response;
	}
	//信任所有SSL证书
	public static String getRequest(String url, int timeOut) throws Exception {
		URL u = new URL(url);
		if ("https".equalsIgnoreCase(u.getProtocol())) {
			SslUtils.ignoreSsl();
		}
		URLConnection conn = u.openConnection();
		conn.setConnectTimeout(timeOut);
		conn.setReadTimeout(timeOut);
		return IOUtils.toString(conn.getInputStream());
	}
	//信任所有SSL证书
	public static String postRequest(String urlAddress, String args, int timeOut)
			throws Exception {
		URL url = new URL(urlAddress);
		if ("https".equalsIgnoreCase(url.getProtocol())) {
			SslUtils.ignoreSsl();
		}
		URLConnection u = url.openConnection();
		u.setDoInput(true);
		u.setDoOutput(true);
		u.setConnectTimeout(timeOut);
		u.setReadTimeout(timeOut);
		OutputStreamWriter osw = new OutputStreamWriter(u.getOutputStream(),
				"UTF-8");
		osw.write(args);
		osw.flush();
		osw.close();
		u.getOutputStream();
		return IOUtils.toString(u.getInputStream());
	}
	
	public static void main(String[] args) {
		
	}
}
