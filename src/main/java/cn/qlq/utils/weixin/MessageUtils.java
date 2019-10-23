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
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<>();
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
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xStream = new XStream();
		// 将xml的根元素替换成xml
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}
}
