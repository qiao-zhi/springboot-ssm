package cn.qlq.bean.weixin;

/**
 * 自定义菜单事件(CLICK和VIEW类型)
 * 
 * @author Administrator
 *
 */
public class ClickViewEventMessage extends EventMessage {

	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
