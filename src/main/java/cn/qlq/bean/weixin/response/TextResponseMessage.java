package cn.qlq.bean.weixin.response;

/**
 * 文字消息(图片)
 * 
 * @author Administrator
 *
 */
public class TextResponseMessage extends AbstractResponseMessage {

	/**
	 * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示,\n是换行）
	 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	@Override
	public String toString() {
		return "TextResponseMessage [Content=" + Content + ", ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
	}

}
