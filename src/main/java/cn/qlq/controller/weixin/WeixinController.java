package cn.qlq.controller.weixin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlq.controller.UserController;
import cn.qlq.utils.weixin.WeixinCheckUtils;

@Controller
@RequestMapping("weixin")
public class WeixinController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ResponseBody
	@RequestMapping("index")
	public String index(@RequestParam(required = false) String signature,
			@RequestParam(required = false) String timestamp, @RequestParam(required = false) String nonce,
			@RequestParam(required = false) String echostr) {

		logger.debug("signature: {}, timestamp: {}, nonce: {}, echostr: {}", signature, timestamp, nonce, echostr);

		if (StringUtils.isNoneBlank(signature, timestamp, nonce)
				&& WeixinCheckUtils.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}

		return "error";
	}
}
