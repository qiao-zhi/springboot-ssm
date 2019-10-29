package cn.qlq.utils.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.qlq.utils.HttpClientUploadFile;
import cn.qlq.utils.HttpUtils;

public class WeixinInterfaceUtils {

	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	/**
	 * 上传临时素材
	 */
	public static String URL_UPLOAD__TEMPEORARY_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	/**
	 * 上传永久素材(图片（image）、语音（voice）、视频（video）和缩略图（thumb）)
	 */
	public static String URL_UPLOAD__PERMANENT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

	/**
	 * 上传永久图文素材
	 */
	public static String URL_UPLOAD__PERMANENT_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

	/**
	 * 获取临时素材
	 */
	public static String URL_GET__TEMPEORARY_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	public static String getAccessToken() {

		// Map<String, Object> param = new HashMap<>();
		// param.put("grant_type", "client_credential");
		// param.put("appid", "wx7d2efbe51db6f1e9");
		// param.put("secret", "7a38556ae47b7eb185097f6f61444377");
		//
		// String responseStr = HttpUtils.doGetWithParams(ACCESS_TOKEN_URL,
		// param);
		// if (StringUtils.isNotBlank(responseStr)) {
		// JSONObject parseObject = JSONObject.parseObject(responseStr);
		// if (parseObject.containsKey("access_token")) {
		// return parseObject.getString("access_token");
		// }
		//
		// return "";
		// }

		return "26_afp945RcqvIYUDRAG4_hO5PloOWDW9mXaXT4-jN0OHQMmVu7HzsPBl8Yx4DFNV76cmjkA6KML7K7yrkTmi1DX3GIZ0vNKlvxEQtfi-WQngKVz7AFjfbrRvjMadc0PTb4mKfEZWeAoKZ9xCM-SVNiAHAMPH";
	}

	/**
	 * 上传临时素材文件
	 * 
	 * @param filePath
	 *            本地资源路径
	 * @param type
	 *            类型:有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 */
	public static JSONObject uploadTemporaryMaterial(String filePath, String type) {
		String replacedUrl = URL_UPLOAD__TEMPEORARY_MATERIAL.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE",
				type);
		String uploadFileResult = HttpClientUploadFile.uploadFile(replacedUrl, filePath, "media");
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 上传永久素材文件
	 * 
	 * @param filePath
	 *            本地资源路径
	 * @param type
	 *            类型:有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 */
	public static JSONObject uploadPermanentMaterial(String filePath, String type) {
		String replacedUrl = URL_UPLOAD__PERMANENT_MATERIAL.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE",
				type);
		String uploadFileResult = HttpClientUploadFile.uploadFile(replacedUrl, filePath, "media");
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 
	 * @param title
	 *            标题
	 * @param thumb_media_id
	 *            图文消息的封面图片素材id（必须是永久mediaID）
	 * @param author
	 *            作者
	 * @param digest
	 *            图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
	 * @param show_cover_pic
	 *            是否显示封面，0为false，即不显示，1为true，即显示
	 * @param content
	 *            图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源
	 *            "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
	 * @param ontent_source_url
	 *            图文消息的原文地址，即点击“阅读原文”后的URL
	 * @param need_open_comment
	 *            Uint32 是否打开评论，0不打开，1打开
	 * @param only_fans_can_comment
	 *            Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
	 * @return
	 */
	public static JSONObject uploadPermanentNews(String title, String thumb_media_id, String author, String digest,
			String show_cover_pic, String content, String ontent_source_url, String need_open_comment,
			String only_fans_can_comment) {

		Map<String, Object> params = new HashMap<>();
		params.put("title", title);
		params.put("thumb_media_id", thumb_media_id);
		params.put("author", author);
		params.put("digest", digest);
		params.put("title", title);
		params.put("show_cover_pic", show_cover_pic);
		params.put("content", content);
		params.put("ontent_source_url", ontent_source_url);
		params.put("need_open_comment", need_open_comment);
		params.put("only_fans_can_comment", only_fans_can_comment);

		List<String> articles = new ArrayList<>();
		articles.add(JSONObject.toJSONString(params));

		HashMap<Object, Object> param = new HashMap<>();
		param.put("articles", articles);

		String replacedUrl = URL_UPLOAD__PERMANENT_NEWS.replace("ACCESS_TOKEN", getAccessToken());
		String doPost = HttpUtils.doPost(replacedUrl, param);
		if (StringUtils.isNotBlank(doPost)) {
			return JSONObject.parseObject(doPost);
		}

		return null;
	}

	public static void main(String[] args) {
		uploadPermanentMaterialTest();
	}

	private static void uploadPermanentNewsTest() {
		JSONObject uploadFile = uploadPermanentNews("18年写的面试心得", "56gT8viUy_wLQ8Q2s5H6L0hQSMue9SKgr6FlwDTBF1E",
				"qiaozhi", "摘要", "1", "<html><body>111222</body><html>",
				"https://www.cnblogs.com/qlqwjy/p/9194434.html", "1", "1");
		System.out.println(uploadFile);
	}

	private static void getAccessTokenTest() {
		String accessToken = getAccessToken();
		System.out.println(accessToken);
	}

	private static void uploadPermanentMaterialTest() {
		JSONObject uploadPermanentMaterial = uploadPermanentMaterial("G:/0101.jpg", "image");
		System.out.println(uploadPermanentMaterial);
	}
}
