package cn.qlq.bean.weixin;

/**
 * 语音消息
 * 
 * @author Administrator
 *
 */
public class VoiceMessage extends AbstractMessage {

	/**
	 * 开发者微信号
	 */
	private String ToUserName;

	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String FromUserName;

	/**
	 * 消息创建时间 （整型）
	 */
	private long CreateTime;

	/**
	 * voice
	 */
	private String MsgType;

	/**
	 * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
	 */
	private String MediaId;

	/**
	 * 语音格式，如amr，speex等
	 */
	private String Format;

	/**
	 * 语音识别结果，UTF8编码(需要到接口权限开通语音)
	 */
	private String Recognition;

	/**
	 * 消息id，64位整型
	 */
	private String MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	@Override
	public String toString() {
		return "VoiceMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", MediaId=" + MediaId + ", Format=" + Format + ", Recognition="
				+ Recognition + ", MsgId=" + MsgId + "]";
	}

}
