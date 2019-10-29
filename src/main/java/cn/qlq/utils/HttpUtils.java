package cn.qlq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
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
	public static String doGet(String url, Map params) {
		try {
			HttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);

			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity(), "utf-8");
				return strResult;
			}
		} catch (IOException e) {
			logger.debug("get data error");
		}

		return null;
	}

	/**
	 * get请求携带参数(注意只能是英文参数)
	 * 
	 * @return
	 */
	public static String doGetWithParams(String url, Map params) {
		try {
			HttpClient client = new DefaultHttpClient();

			// 如果携带参数，重新拼接get参数
			if (params != null && params.size() > 0) {
				StringBuffer sb = new StringBuffer(url);
				sb.append("?");
				for (Object key : params.keySet()) {
					sb.append(key + "=" + params.get(key) + "&");
				}
				url = sb.toString().substring(0, sb.length() - 1);
			}

			// 发送get请求
			HttpGet request = new HttpGet(url);

			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/
				String strResult = EntityUtils.toString(response.getEntity(), "utf-8");
				return strResult;
			}
		} catch (IOException e) {
			logger.error("get data error");
		}

		return null;
	}

	/**
	 * post请求(用于key-value格式的参数)
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map params) {
		BufferedReader in = null;
		try {
			// 定义HttpClient
			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));

			// 设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();

				return sb.toString();
			} else { //
				System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * post请求（用于请求json格式的参数）
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String params) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;

		try {

			response = httpclient.execute(httpPost);
			StatusLine status = response.getStatusLine();
			int state = status.getStatusCode();
			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				return jsonString;
			} else {
				logger.error("请求返回:" + state + "(" + url + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 *            上传的URL
	 * @param filePath
	 *            本地路径
	 * @param fileName
	 *            上传的name(相当于input框的name属性)
	 * @return
	 */
	public static String uploadFile(String url, String filePath, String fileName) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		String result = "";
		try {
			HttpPost httppost = new HttpPost(url);

			// 可以选择文件，也可以选择附加的参数
			HttpEntity req = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart(fileName, new FileBody(new File(filePath)))// 上传文件,如果不需要上传文件注掉此行
					.build();
			httppost.setEntity(req);

			response = httpclient.execute(httppost);

			HttpEntity re = response.getEntity();
			if (re != null) {
				result = new BufferedReader(new InputStreamReader(re.getContent())).readLine();
			}
			EntityUtils.consume(re);
		} catch (Exception e) {

		} finally {
			IOUtils.closeQuietly(response);
		}

		return result;
	}
}