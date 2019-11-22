package cn.qlq.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http工具类的使用
 * 
 * @author Administrator
 *
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * get请求
	 * 
	 * @return
	 */
	public static String doGet(String url) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 定义HttpClient
			client = HttpClientBuilder.create().build();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			// 执行请求
			response = client.execute(request);

			return getResponseResult(response);
		} catch (Exception e) {
			logger.error("execute error,url: {}", url, e);
		} finally {
			IOUtils.closeQuietly(response);
			IOUtils.closeQuietly(client);
		}

		return "";
	}

	/**
	 * get请求携带参数
	 * 
	 * @return
	 */
	public static String doGetWithParams(String url, Map<String, String> params) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 定义HttpClient
			client = HttpClientBuilder.create().build();

			// 1.转化参数
			if (params != null && params.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext();) {
					String name = iter.next();
					String value = params.get(name);
					nvps.add(new BasicNameValuePair(name, value));
				}
				String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
				url += "?" + paramsStr;
			}

			HttpGet request = new HttpGet(url);
			response = client.execute(request);

			return getResponseResult(response);
		} catch (IOException e) {
			logger.error("execute error,url: {}", url, e);
		} finally {
			IOUtils.closeQuietly(response);
			IOUtils.closeQuietly(client);
		}

		return "";
	}

	public static String doPost(String url, Map<String, String> params) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 定义HttpClient
			client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(url);

			// 1.转化参数
			if (params != null && params.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext();) {
					String name = iter.next();
					String value = params.get(name);
					nvps.add(new BasicNameValuePair(name, value));
				}
				request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			}

			response = client.execute(request);
			return getResponseResult(response);
		} catch (IOException e) {
			logger.error("execute error,url: {}", url, e);
		} finally {
			IOUtils.closeQuietly(response);
			IOUtils.closeQuietly(client);
		}

		return "";
	}

	public static String doPost(String url, String params) {
		return doPost(url, params, false);
	}

	/**
	 * post请求（用于请求json格式的参数）
	 * 
	 * @param url
	 * @param params
	 * @param isJsonData
	 * @return
	 */
	public static String doPost(String url, String params, boolean isJsonData) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			// 定义HttpClient
			client = HttpClientBuilder.create().build();

			HttpPost request = new HttpPost(url);
			StringEntity entity = new StringEntity(params, HTTP.UTF_8);
			request.setEntity(entity);

			if (isJsonData) {
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-Type", "application/json");
			}

			response = client.execute(request);

			return getResponseResult(response);
		} catch (IOException e) {
			logger.error("execute error,url: {}", url, e);
		} finally {
			IOUtils.closeQuietly(response);
			IOUtils.closeQuietly(client);
		}

		return "";
	}

	/**
	 * 上传文件携带参数发送请求
	 * 
	 * @param url
	 *            URL
	 * @param fileName
	 *            neme，相当于input的name
	 * @param filePath
	 *            本地路径
	 * @param params
	 *            参数
	 * @return
	 */
	public static String doPostWithFile(String url, String fileName, String filePath, Map<String, String> params) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		try {
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

			// 上传文件,如果不需要上传文件注掉此行
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE).addPart(fileName,
					new FileBody(new File(filePath)));

			if (params != null && params.size() > 0) {
				Set<Entry<String, String>> entrySet = params.entrySet();
				for (Entry<String, String> entry : entrySet) {
					multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(),
							ContentType.create(HTTP.PLAIN_TEXT_TYPE, StandardCharsets.UTF_8));
				}
			}

			HttpEntity httpEntity = multipartEntityBuilder.build();

			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(httpEntity);

			response = httpclient.execute(httppost);
			return getResponseResult(response);
		} catch (Exception e) {
			logger.error("execute error,url: {}", url, e);
		} finally {
			IOUtils.closeQuietly(response);
			IOUtils.closeQuietly(httpclient);
		}

		return "";
	}

	private static String getResponseResult(CloseableHttpResponse response) throws ParseException, IOException {
		/** 请求发送成功，并得到响应 **/
		if (response != null) {
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "utf-8");
			} else {
				logger.error("getResponseResult code error, code: {}", response.getStatusLine().getStatusCode());
			}
		}

		return "";
	}

	public static void main(String[] args) {
		String doGet = doGet("http://localhost:8088//weixin/test/test.html?name=zs&age=25");
		System.out.println(doGet);

		Map<String, String> map = new HashMap<String, String>();
		map.put("xx", "xxx");
		String doGetWithParams = doGetWithParams("http://localhost:8088//weixin/test/test.html", map);
		System.out.println(doGetWithParams);

		String doPost = doPost("http://localhost:8088//weixin/test/test.html?name=zs&age=25", "");
		System.out.println(doPost);

		String doPost2 = doPost("http://localhost:8088//weixin/test/test.html?name=zs&age=25", map);
		System.out.println(doPost2);
	}
}