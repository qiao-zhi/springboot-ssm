package cn.qlq.utils.weixin;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qlq.bean.weixin.EventMessage;
import cn.qlq.bean.weixin.ImageMessage;
import cn.qlq.bean.weixin.LinkMessage;
import cn.qlq.bean.weixin.LocationMessage;
import cn.qlq.bean.weixin.TextMessage;
import cn.qlq.bean.weixin.VideoMessage;
import cn.qlq.bean.weixin.VoiceMessage;
import cn.qlq.bean.weixin.response.AbstractResponseMessage;
import cn.qlq.bean.weixin.response.ImageResponseMessage;
import cn.qlq.bean.weixin.response.NewsResponseMessage;
import cn.qlq.bean.weixin.response.NewsResponseMessageArticle;
import cn.qlq.bean.weixin.response.NewsResponseMessageArticleItem;
import cn.qlq.bean.weixin.response.TextResponseMessage;
import cn.qlq.utils.BeanUtils;

public class MessageHandler {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";// 事件
	public static final String MESSAGE_SUBSCRIBE = "subscribe";// 关注
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";// 取消关注
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";

	private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

	public static AbstractResponseMessage handlMessage(Map<String, Object> messageMap) {
		if (MapUtils.isEmpty(messageMap)) {
			logger.error("message is empty");
			return null;
		}

		String msgType = (String) messageMap.get("MsgType");
		if (MESSAGE_TEXT.equals(msgType)) {
			return handleTextMessage(messageMap);
		} else if (MESSAGE_IMAGE.equals(msgType)) {
			return handleImageMessage(messageMap);
		} else if (MESSAGE_LOCATION.equals(msgType)) {
			return handleLocationMessage(messageMap);
		} else if (MESSAGE_EVENT.equals(msgType)) {
			return handleEventMessage(messageMap);
		} else if (MESSAGE_LINK.equals(msgType)) {
			return handleLinkMessage(messageMap);
		} else if (MESSAGE_VOICE.equals(msgType)) {
			return handleVoiceMessage(messageMap);
		} else if (MESSAGE_VIDEO.equals(msgType)) {
			return handleVideoMessage(messageMap);
		}

		return null;
	}

	private static AbstractResponseMessage handleVideoMessage(Map<String, Object> messageMap) {
		VideoMessage message = BeanUtils.map2Bean(messageMap, VideoMessage.class, true);

		String thumbMediaId = message.getThumbMediaId();
		// 可以用图片路径做其他操作
		if (StringUtils.isNotBlank(thumbMediaId)) {
			System.out.println("您接收到视频消息, thumbMediaId为: " + thumbMediaId);
		}

		String responseMsg = "您发送了一条视频消息，thumbMediaId为: " + thumbMediaId;
		return MessageUtils.initTextMessage(message.getToUserName(), message.getFromUserName(), responseMsg);
	}

	private static AbstractResponseMessage handleVoiceMessage(Map<String, Object> messageMap) {
		VoiceMessage message = BeanUtils.map2Bean(messageMap, VoiceMessage.class, true);

		String recognition = message.getRecognition();
		String format = message.getFormat();
		// 可以用图片路径做其他操作
		if (StringUtils.isNotBlank(recognition)) {
			System.out.println("您接收到语音消息， 格式为: " + format + ", 转换后的文字为: " + recognition);
		}

		String responseMsg = "您发送了一条语音消息，格式为: " + format + ", 转换后的文字为: " + recognition;
		return MessageUtils.initTextMessage(message.getToUserName(), message.getFromUserName(), responseMsg);
	}

	/**
	 * 处理链接消息(回复一条图文消息)
	 * 
	 * @param message
	 * @return
	 */
	private static AbstractResponseMessage handleLinkMessage(Map<String, Object> messageMap) {
		LinkMessage message = BeanUtils.map2Bean(messageMap, LinkMessage.class, true);

		String desc = message.getDescription();
		String title = message.getTitle();
		String url = message.getUrl();
		// 可以用图片路径做其他操作
		if (StringUtils.isNotBlank(url)) {
			System.out.println("您接收到链接消息， title: " + title + ", desc: " + desc + ", url: " + url);
		}

		// 回复一条图文消息
		NewsResponseMessage news = new NewsResponseMessage();
		news.setCreateTime(System.currentTimeMillis());
		news.setFromUserName(message.getToUserName());
		news.setToUserName(message.getFromUserName());
		news.setArticleCount("1");
		news.setMsgType("news");

		NewsResponseMessageArticle article = new NewsResponseMessageArticle();
		news.setArticles(article);

		// 创建多条图文消息
		for (int i = 0; i < 1; i++) {
			NewsResponseMessageArticleItem item = new NewsResponseMessageArticleItem();
			item.setTitle("18年写的面试心得");
			item.setPicUrl("https://images.cnblogs.com/cnblogs_com/qlqwjy/1031659/o_9.bmp");
			item.setUrl("https://www.cnblogs.com/qlqwjy/p/9194434.html");
			item.setDescription("18年毕设心血来潮写的毕设心得，1年后再看有点东西。");

			article.addNewsResponseMessageArticleItem(item);
		}

		return news;
	}

	/**
	 * 处理事件消息(订阅和取消订阅)
	 * 
	 * @param messageMap
	 * @return
	 */
	private static AbstractResponseMessage handleEventMessage(Map<String, Object> messageMap) {
		EventMessage message = BeanUtils.map2Bean(messageMap, EventMessage.class, true);

		String event = message.getEvent();
		if (StringUtils.isNotBlank(event)) {
			System.out.println("您接收到事件消息， 事件类型为: " + event);
		}

		if (MESSAGE_SUBSCRIBE.equals(event)) {
			// 关注的时候
			System.out.println("这里可以向数据库插入数据");

			String responseMsg = MessageUtils.subscribeWelcomeText();
			return MessageUtils.initTextMessage(message.getToUserName(), message.getFromUserName(), responseMsg);
		} else {
			// 取消关注(不用回传消息.需要将用户产生的数据删除)
			System.out.println("这时需要从数据删除  " + message.getFromUserName() + " 用户产生的相关数据");

			return null;
		}
	}

	private static AbstractResponseMessage handleLocationMessage(Map<String, Object> messageMap) {
		LocationMessage message = BeanUtils.map2Bean(messageMap, LocationMessage.class, true);

		String label = message.getLabel();
		if (StringUtils.isNotBlank(label)) {
			System.out.println("您接收到位置消息， 地理位置信息为: " + message);
		}

		String responseMsg = "您发送了一条位置消息， 您的地理位置信息为:" + label;
		return MessageUtils.initTextMessage(message.getToUserName(), message.getFromUserName(), responseMsg);
	}

	/**
	 * 处理图片消息(回复一条图片消息)
	 * 
	 * @param message
	 * @return
	 */
	private static AbstractResponseMessage handleImageMessage(Map<String, Object> message) {
		ImageMessage imageMessage = BeanUtils.map2Bean(message, ImageMessage.class, true);

		String url = imageMessage.getPicUrl();
		// 可以用图片路径做其他操作
		if (StringUtils.isNotBlank(url)) {
			System.out.println("您接收到的图片消息url为: " + url);
		}

		// 回传一条图片消息
		ImageResponseMessage responseMessage = new ImageResponseMessage();
		responseMessage.setCreateTime(System.currentTimeMillis());
		responseMessage.setFromUserName(imageMessage.getToUserName());
		responseMessage.setToUserName(imageMessage.getFromUserName());
		responseMessage.setMediaId(imageMessage.getMediaId());
		responseMessage.setMsgType(MESSAGE_IMAGE);

		return responseMessage;
	}

	/**
	 * 处理文本消息
	 * 
	 * @param message
	 * @return
	 */
	private static AbstractResponseMessage handleTextMessage(Map<String, Object> message) {
		TextMessage textMessage = BeanUtils.map2Bean(message, TextMessage.class, true);

		String content = textMessage.getContent();
		// 可以根据文本消息去查库或者进行其他操作
		if (StringUtils.isNotBlank(content)) {
			System.out.println("您接收到的文本消息内容为: " + content);
		}

		// 设置回传的消息内容
		TextResponseMessage responseMessage = new TextResponseMessage();
		responseMessage.setContent("服务器已接收到您的消息，内容为: " + content);
		responseMessage.setCreateTime(new Date().getTime());
		responseMessage.setFromUserName(textMessage.getToUserName());
		responseMessage.setToUserName(textMessage.getFromUserName());
		responseMessage.setMsgType(MESSAGE_TEXT);

		return responseMessage;
	}

}
