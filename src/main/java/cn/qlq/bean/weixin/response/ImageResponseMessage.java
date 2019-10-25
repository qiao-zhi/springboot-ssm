package cn.qlq.bean.weixin.response;

/**
 * 图片消息(回复)
 * 
 * @author Administrator
 *
 */
public class ImageResponseMessage extends AbstractResponseMessage {

	private ImageMedia Image;

	public ImageResponseMessage() {
		Image = new ImageMedia();
	}

	public void setMediaId(String mediaId) {
		Image.setMediaId(mediaId);
	}

}
