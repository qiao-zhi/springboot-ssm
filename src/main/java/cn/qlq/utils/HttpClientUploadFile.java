package cn.qlq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * httpclient上传文件(测试没问题)
 * 
 * @author Administrator
 *
 */
public class HttpClientUploadFile {

	public static String uploadFile(String filePath, String url) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response = null;
		String result = "";
		try {
			HttpPost httppost = new HttpPost(url);

			// 可以选择文件，也可以选择附加的参数
			HttpEntity req = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart("docUpload", new FileBody(new File(filePath)))// 上传文件,如果不需要上传文件注掉此行
					.build();
			httppost.setEntity(req);

			response = httpclient.execute(httppost);

			HttpEntity re = response.getEntity();
			System.out.println(response.getStatusLine());
			if (re != null) {
				result = "Response content: " + new BufferedReader(new InputStreamReader(re.getContent())).readLine();
			}
			EntityUtils.consume(re);
		} catch (Exception e) {

		} finally {
			IOUtils.closeQuietly(response);
		}

		return result;
	}

}