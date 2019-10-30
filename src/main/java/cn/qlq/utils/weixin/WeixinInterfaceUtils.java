package cn.qlq.utils.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.qlq.bean.weixin.menu.Button;
import cn.qlq.bean.weixin.menu.ClickButton;
import cn.qlq.bean.weixin.menu.Menu;
import cn.qlq.bean.weixin.menu.ViewButton;
import cn.qlq.utils.HttpUtils;

public class WeixinInterfaceUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinInterfaceUtils.class);

	/**
	 * 获取ACCESS_TOKEN
	 */
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
	 * 获取永久素材列表
	 */
	public static String URL_GET__PERMANENT_MATERIAL_LIST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

	/**
	 * 获取永久素材
	 */
	public static String URL_GET__PERMANENT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";

	/**
	 * 获取用户信息
	 */
	public static String URL_GET__USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 批量获取用户信息
	 */
	public static String URL_GET__USER_INFO_BATCH = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 */
	public static String URL_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 获取菜单
	 */
	public static String URL_GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	/**
	 * 删除菜单
	 */
	public static String URL_DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	// 用于管理token
	/**
	 * 获取到的accessToken
	 */
	private static String accessToken;

	/**
	 * 最后一次获取Access_Token的时间
	 */
	private static Date lastGetAccessTokenTime;

	public static String getAccessToken() {
		if (StringUtils.isBlank(accessToken) || isExpiredAccessToken()) {
			accessToken = null;
			lastGetAccessTokenTime = null;

			Map<String, Object> param = new HashMap<>();
			param.put("grant_type", "client_credential");
			param.put("appid", WeixinConstants.APPID);
			param.put("secret", WeixinConstants.APP_SECRET);

			String responseStr = HttpUtils.doGetWithParams(ACCESS_TOKEN_URL, param);
			if (StringUtils.isNotBlank(responseStr)) {
				JSONObject parseObject = JSONObject.parseObject(responseStr);
				if (parseObject != null && parseObject.containsKey("access_token")) {
					accessToken = parseObject.getString("access_token");
					lastGetAccessTokenTime = new Date();
					LOGGER.debug("调用接口获取accessToken，获取到的信息为: {}", parseObject.toString());
				}
			}
		} else {
			LOGGER.debug("使用未过时的accessToken: {}", accessToken);
		}

		return accessToken;
	}

	private static boolean isExpiredAccessToken() {
		if (lastGetAccessTokenTime == null) {
			return true;
		}

		// 1.5小时以后的就算失效
		long existTime = 5400000L;
		long now = System.currentTimeMillis();
		if (now - lastGetAccessTokenTime.getTime() > existTime) {
			return true;
		}

		return false;
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
		String uploadFileResult = HttpUtils.uploadFile(replacedUrl, filePath, "media");
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
		String uploadFileResult = HttpUtils.uploadFile(replacedUrl, filePath, "media");
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

		List<Map<String, Object>> articles = new ArrayList<>();
		articles.add(params);

		HashMap<Object, Object> param = new HashMap<>();
		param.put("articles", articles);

		String jsonString = JSONObject.toJSONString(param);
		System.out.println(jsonString);

		String replacedUrl = URL_UPLOAD__PERMANENT_NEWS.replace("ACCESS_TOKEN", getAccessToken());
		String doPost = HttpUtils.doPost(replacedUrl, jsonString);
		if (StringUtils.isNotBlank(doPost)) {
			return JSONObject.parseObject(doPost);
		}

		return null;
	}

	/**
	 * 获取素材列表
	 * 
	 * @param type
	 *            素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param offset
	 *            从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count
	 *            返回素材的数量，取值在1到20之间
	 * @return
	 */
	public static JSONObject listPermanentMaterial(String type, int offset, int count) {
		String replacedUrl = URL_GET__PERMANENT_MATERIAL_LIST.replace("ACCESS_TOKEN", getAccessToken());

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("type", type);
		params.put("offset", offset);
		params.put("count", count);

		String uploadFileResult = HttpUtils.doPost(replacedUrl, JSONObject.toJSONString(params));
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 获取永久素材信息
	 * 
	 * @param MEDIA_ID
	 *            mediaId
	 * @return
	 */
	public static JSONObject getPermanentMaterial(String mediaId) {
		String replacedUrl = URL_GET__PERMANENT_MATERIAL.replace("ACCESS_TOKEN", getAccessToken());

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("media_id", mediaId);

		String uploadFileResult = HttpUtils.doPost(replacedUrl, JSONObject.toJSONString(params));
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param openId
	 *            普通用户的标识，对当前公众号唯一
	 * @return JSON数据格式的用户信息
	 */
	public static JSONObject userInfo(String openId) {
		String replacedUrl = URL_GET__USER_INFO.replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openId);
		String uploadFileResult = HttpUtils.doGet(replacedUrl, null);
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 批量获取用户信息的
	 * 
	 * @return JSON数据格式的用户信息
	 */
	public static JSONObject batchGetUserInfo(String... openIds) {
		List<Map<String, Object>> userOpenIds = new LinkedList<>();
		Map<String, Object> tmpMap = null;
		for (String openId : openIds) {
			tmpMap = new HashMap<>();
			tmpMap.put("openid", openId);
			// 默认就是zh_CN,可以不传
			tmpMap.put("lang", "zh_CN");

			userOpenIds.add(tmpMap);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("user_list", userOpenIds);
		String param = JSONObject.toJSONString(params);
		System.out.println(param);

		String replacedUrl = URL_GET__USER_INFO_BATCH.replace("ACCESS_TOKEN", getAccessToken());
		String uploadFileResult = HttpUtils.doPost(replacedUrl, param);
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            JSON格式的菜单数据
	 * @return
	 */
	public static JSONObject createMenu(String menu) {
		String replacedUrl = URL_CREATE_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String uploadFileResult = HttpUtils.doPost(replacedUrl, menu);
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 获取自定义菜单
	 * 
	 * @return
	 */
	public static JSONObject getMenu() {
		String replacedUrl = URL_GET_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String uploadFileResult = HttpUtils.doGet(replacedUrl, null);
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 删除自定义菜单
	 * 
	 * @return
	 */
	public static JSONObject deleteMenu() {
		String replacedUrl = URL_DELETE_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String uploadFileResult = HttpUtils.doGet(replacedUrl, null);
		if (StringUtils.isNotBlank(uploadFileResult)) {
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	/**
	 * 组装菜单
	 * 
	 * @return
	 */
	private static Menu initMenu() {
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://b4a819d0.ngrok.io/index.html");

		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");

		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[] { button31, button32 });

		menu.setButton(new Button[] { button11, button21, button });
		return menu;
	}

	public static void main(String[] args) {
		createMenuTest();
		getMenuMenuTest();
	}

	private static void deleteMenuTest() {
		JSONObject deleteMenu = deleteMenu();
		System.out.println(deleteMenu);
	}

	private static void getPermanentMaterialTest() {
		JSONObject permanentMaterial = getPermanentMaterial("56gT8viUy_wLQ8Q2s5H6L2DeqyqigQj3EdOsec4pRdw");
		System.out.println(permanentMaterial);
	}

	private static void listPermanentMaterialTest() {
		JSONObject listPermanentMaterial = listPermanentMaterial("news", 0, 20);
		System.out.println(listPermanentMaterial);
	}

	private static void batchGetUserInfoTest() {
		JSONObject batchGetUserInfo = batchGetUserInfo("o_KK7s3-B6HhwSW7hVEAt4DK329s");
		System.out.println(batchGetUserInfo);
	}

	private static void getMenuMenuTest() {
		JSONObject menu = getMenu();
		System.out.println(menu);
	}

	private static void createMenuTest() {
		Menu initMenu = initMenu();
		JSONObject createMenu = createMenu(JSONObject.toJSONString(initMenu));
		System.out.println(createMenu);
	}

	private static void userInfoTest() {
		JSONObject userInfo = userInfo("o_KK7s3-B6HhwSW7hVEAt4DK329s");
		System.out.println(userInfo);
	}

	private static void uploadTemporaryMaterialTest() {
		JSONObject uploadPermanentMaterial = uploadTemporaryMaterial("G:/yzm.png", "image");
		System.out.println(uploadPermanentMaterial);
	}

	private static void uploadPermanentNewsTest() {
		JSONObject uploadFile = uploadPermanentNews("18年写的面试心得", "56gT8viUy_wLQ8Q2s5H6LxrbLx9I-ystQZ1Dv7IkNRo",
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
