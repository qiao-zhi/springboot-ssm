package cn.qlq.controller.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.qlq.utils.HttpUtils;
import cn.qlq.utils.weixin.WeixinConstants;

@Controller
@RequestMapping("weixin/auth")
public class WeixinAuthController {

	/**
	 * 首页，跳转到index.html,index.html有一个连接会访问下面的login方法
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "weixinauth/index";
	}

	/**
	 * (一)微信授权：重定向到授权页面
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/login")
	public String authorize() throws UnsupportedEncodingException {
		// 回调地址必须在公网可以访问
		String recirectUrl = URLEncoder.encode("http://6965ee39.ngrok.io/weixin/auth/calback.html", "UTF-8");

		// 授权地址
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		url = url.replace("APPID", WeixinConstants.APPID).replace("REDIRECT_URI", recirectUrl);

		// 参数替换之后重定向到授权地址
		return "redirect:" + url;
	}

	/**
	 * (二)用户同意授权; (三)微信会自动重定向到该页面并携带参数code和state用于换取access_token和openid; (四)
	 * 用access_token和openid获取用户信息(五)如果有必要可以进行登录，两种:第一种是直接拿微信号登录；
	 * 第二种是根据openid和nickname获取账号进行登录
	 * 
	 * @param code
	 * @param state
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/calback")
	@ResponseBody
	public String calback(String code, String state) throws UnsupportedEncodingException {
		// 获取access_token和openid
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID", WeixinConstants.APPID).replace("SECRET", WeixinConstants.APP_SECRET).replace("CODE",
				code);
		String doGet = HttpUtils.doGet(url);

		if (StringUtils.isNotBlank(doGet)) {
			JSONObject parseObject = JSONObject.parseObject(doGet);
			System.out.println(parseObject);

			// 获取两个参数之后获取用户信息
			String accessToken = parseObject.getString("access_token");
			String openid = parseObject.getString("openid");
			String getUserInfoURL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
			getUserInfoURL = getUserInfoURL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
			String doGet2 = HttpUtils.doGet(getUserInfoURL);

			// 可以用获取到的用户信息进行两种方式的登录
			System.out.println(doGet2);

			return doGet2;
		}

		return "";
	}
}
