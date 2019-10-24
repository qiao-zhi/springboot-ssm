package cn.qlq.utils.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import cn.qlq.bean.weixin.AbstractMessage;
import cn.qlq.bean.weixin.TextMessage;

public class MessageUtils {

	/**
	 * xml数据转map
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, Object> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, Object> map = new HashMap<>();
		SAXReader reader = new SAXReader();

		InputStream inputStream = request.getInputStream();
		Document document = reader.read(inputStream);

		Element root = document.getRootElement();
		List<Element> list = root.elements();

		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}

		inputStream.close();
		return map;
	}

	/**
	 * 将文本消息对象转换成xml
	 *
	 * @param message
	 * @return
	 */
	public static String messageToXml(AbstractMessage message) {
		XStream xStream = new XStream();
		// 将xml的根元素替换成xml
		xStream.alias("xml", message.getClass());
		return xStream.toXML(message);
	}

	/**
	 * 将文本消息对象转换成xml
	 *
	 * @param message
	 * @return
	 */
	public static String messageToXml2(AbstractMessage message) {
		XStream xStream = new XStream();
		// 将xml的根元素替换成xml
		xStream.alias("xml", message.getClass());
		return xStream.toXML(message);
	}

	/**
	 * 订阅后的欢迎信息
	 * 
	 * @return
	 */
	public static String subscribeWelcomeText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，这里是乔治个人平台：\n\n");
		sb.append("1.推荐一些优秀的文章\n");// \n代表换行
		sb.append("2.记录一些美好时刻\n\n");

		return sb.toString();
	}

	/**
	 * 生成文本消息
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	public static TextMessage initTextMessage(String fromUserName, String toUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(fromUserName);
		textMessage.setToUserName(toUserName);
		textMessage.setMsgType(MessageHandler.MESSAGE_TEXT);
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setContent(content);

		return textMessage;
	}

}
