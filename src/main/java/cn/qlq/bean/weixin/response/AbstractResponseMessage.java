package cn.qlq.bean.weixin.response;

/**
 * 抽象消息
 * 
 * @author Administrator
 *
 */
public abstract class AbstractResponseMessage {
	/**
	 * 开发者微信号
	 */
	protected String ToUserName;

	/**
	 * 发送方帐号（一个OpenID）
	 */
	protected String FromUserName;

	/**
	 * 消息创建时间 （整型）
	 */
	protected long CreateTime;

	/**
	 * 
	 */
	protected String MsgType;

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

}
