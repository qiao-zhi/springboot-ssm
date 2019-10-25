package cn.qlq.controller.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.qlq.bean.weixin.response.AbstractResponseMessage;
import cn.qlq.controller.UserController;
import cn.qlq.utils.weixin.MessageHandler;
import cn.qlq.utils.weixin.MessageUtils;
import cn.qlq.utils.weixin.WeixinCheckUtils;

@Controller
@RequestMapping("weixin")
public class WeixinController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8"); // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
		response.setCharacterEncoding("UTF-8"); // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；

		String method = request.getMethod().toLowerCase();
		logger.info("method: {}", method);

		// 验证是否是微信请求
		if ("get".equals(method)) {
			doGet(request, response);
			return;
		}

		// POST请求接收消息，且给客户响应消息
		doPost(request, response);
	}

	/**
	 * Post请求用于接收消息且处理消息之后回传消息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			// 消息转map
			Map<String, Object> map = MessageUtils.xmlToMap(request);
			logger.info("接收到的消息map: {}", map);

			// 调用工具类处理完之后显示回传消息
			AbstractResponseMessage responseMessage = MessageHandler.handlMessage(map);
			logger.info("回传的消息responseMessage: {}", responseMessage);

			if (responseMessage == null) {
				return;
			}

			String messageToXml = MessageUtils.messageToXml(responseMessage);
			logger.info("回传的消息responseMessage messageToXml: {}", messageToXml);
			out.print(messageToXml);// 把消息发送到客户端
		} catch (DocumentException e) {
			logger.error("dispose post request error", e);
		} finally {
			out.close();
		}
	}

	/**
	 * Get请求用于微信配置验证
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		logger.info("signature: {}, timestamp: {}, nonce: {}, echostr: {}", signature, timestamp, nonce, echostr);

		if (StringUtils.isNoneBlank(signature, timestamp, nonce)
				&& WeixinCheckUtils.checkSignature(signature, timestamp, nonce)) {
			response.getWriter().write(echostr);
		}
	}
}
