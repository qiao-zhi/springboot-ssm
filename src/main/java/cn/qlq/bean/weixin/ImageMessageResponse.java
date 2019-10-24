package cn.qlq.bean.weixin;

/**
 * 图片消息(回复)
 * 
 * @author Administrator
 *
 */
public class ImageMessageResponse extends AbstractMessage {

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
	 * image
	 */
	private String MsgType;

	/**
	 * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
	 */
	private String MediaId;

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

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "ImageMessageResponse [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", MediaId=" + MediaId + "]";
	}

}
