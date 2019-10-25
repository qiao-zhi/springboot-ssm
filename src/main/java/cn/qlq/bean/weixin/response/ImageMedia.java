package cn.qlq.bean.weixin.response;

/**
 * 图片媒体(用于嵌入ImageResponseMessage)
 * 
 * @author Administrator
 *
 */
public class ImageMedia {

	/**
	 * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
	 */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
