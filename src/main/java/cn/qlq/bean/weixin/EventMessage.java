package cn.qlq.bean.weixin;

/**
 * 事件消息
 * 
 * @author Administrator
 *
 */
public class EventMessage {

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
	 * event
	 */
	protected String MsgType;

	/**
	 * 事件类型subscribe(订阅)、unsubscribe(取消订阅),CLICK(点击事件),VIEW(点击菜单跳转链接)
	 */
	protected String Event;

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

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	@Override
	public String toString() {
		return "EventMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
				+ CreateTime + ", MsgType=" + MsgType + ", Event=" + Event + "]";
	}

}
