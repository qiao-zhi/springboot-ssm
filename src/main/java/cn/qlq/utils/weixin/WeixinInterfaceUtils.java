package cn.qlq.utils.weixin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.qlq.utils.HttpUtils;

public class WeixinInterfaceUtils {

	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	public static String getAccessToken() {
		Map<String, Object> param = new HashMap<>();
		param.put("grant_type", "client_credential");
		param.put("appid", "APPID");
		param.put("secret", "APPSECERT");

		String responseStr = HttpUtils.doGetWithParams(ACCESS_TOKEN_URL, param);
		if (StringUtils.isNotBlank(responseStr)) {
			JSONObject parseObject = JSONObject.parseObject(responseStr);
			if (parseObject.containsKey("access_token")) {
				return parseObject.getString("access_token");
			}

			return "";
		}

		return "";
	}

	public static void main(String[] args) {
		String accessToken = getAccessToken();
		System.out.println(accessToken);
	}
}
