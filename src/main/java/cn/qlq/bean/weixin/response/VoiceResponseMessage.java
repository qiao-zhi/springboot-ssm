package cn.qlq.bean.weixin.response;

/**
 * 语音消息(回复)
 * 
 * @author Administrator
 *
 */
public class VoiceResponseMessage extends AbstractResponseMessage {

	private voiceMedia Voice;

	public VoiceResponseMessage() {
		Voice = new voiceMedia();
	}

	public void setMediaId(String mediaId) {
		Voice.setMediaId(mediaId);
	}

}
