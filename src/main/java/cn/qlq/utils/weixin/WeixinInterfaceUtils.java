package cn.qlq.utils.weixin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.qlq.utils.HttpClientUploadFile;
import cn.qlq.utils.HttpUtils;

public class WeixinInterfaceUtils {

	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	public static String UPLOAD_FILE = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=image";

	public static String getAccessToken() {
		Map<String, Object> param = new HashMap<>();
		param.put("grant_type", "client_credential");
		param.put("appid", "wx227d2efbe51db6f1e9");
		param.put("secret", "7a2238556ae47b7eb185097f6f61444377");

		String responseStr = HttpUtils.doGetWithParams(ACCESS_TOKEN_URL, param);
		if (StringUtils.isNotBlank(responseStr)) {
			JSONObject parseObject = JSONObject.parseObject(responseStr);
			if (parseObject.containsKey("access_token")) {
				return parseObject.getString("access_token");
			}

			return "";
		}

		return "26_SBxIMnz1IwgZy1z5ICLqt1uVjf_ifds7fGZsizmiCLhDndIEQ7dm1NGUwpt8PtmqwTy0oi3XtnlitUWb3Rtoy16sZXQrgJRtdmRCBV7fbEdcx3Cz5E7FuD_bJAZG8PuudHsX7dcWIaqH6M5SAPAfAIAMBM";
	}

	public static JSONObject uploadFile(String filePath) {
		String replacedUrl = UPLOAD_FILE.replace("ACCESS_TOKEN", getAccessToken());
		String uploadFileResult = HttpClientUploadFile.uploadFile(filePath, replacedUrl);
		if (StringUtils.isNotBlank(uploadFileResult)) {
			// "item":[] -> "item": 11
			uploadFileResult = uploadFileResult.replace("[", "1").replace("]", "1");
			return JSONObject.parseObject(uploadFileResult);
		}

		return null;
	}

	public static void main(String[] args) {
		JSONObject uploadFile = uploadFile("G:/yzm.png");
		System.out.println(uploadFile);
	}
}
