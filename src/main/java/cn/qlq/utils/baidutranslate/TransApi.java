package cn.qlq.utils.baidutranslate;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class TransApi {

	private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = "1";
	private static final String SECURITY_KEY = "1";

	public static String translate(String keyWords) {
		return translate(keyWords, "auto", "auto");
	}

	public static String translate(String keyWords, String from, String to) {
		String transResult = getTransResult(keyWords, from, to);
		if (StringUtils.isBlank(transResult)) {
			return "";
		}

		JSONObject parseObject = JSONObject.parseObject(transResult);
		if (parseObject != null && parseObject.containsKey("trans_result") && parseObject.get("trans_result") != null) {
			return parseObject.get("trans_result").toString();
		}

		return "";
	}

	private static String getTransResult(String query, String from, String to) {
		Map<String, String> params = buildParams(query, from, to);
		return HttpGet.get(TRANS_API_HOST, params);
	}

	private static Map<String, String> buildParams(String query, String from, String to) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("q", query);
		params.put("from", from);
		params.put("to", to);

		params.put("appid", APP_ID);

		// 随机数
		String salt = String.valueOf(System.currentTimeMillis());
		params.put("salt", salt);

		// 签名
		String src = APP_ID + query + salt + SECURITY_KEY; // 加密前的原文
		params.put("sign", MD5.md5(src));

		return params;
	}

}
