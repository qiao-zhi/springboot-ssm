package cn.qlq.bean.weixin.response;

/**
 * 语音媒体(用于嵌入VoiceResponseMessage)
 * 
 * @author Administrator
 *
 */
public class voiceMedia {

	/**
	 * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
	 */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
