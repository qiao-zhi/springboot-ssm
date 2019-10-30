package cn.qlq.bean.weixin;

/**
 * 上报地理位置事件
 * 
 * @author Administrator
 *
 */
public class LocationEventMessage extends EventMessage {

	/**
	 * 地理位置纬度
	 */
	private String Latitude;

	/**
	 * 地理位置经度
	 */
	private String Longitude;

	/**
	 * 地理位置精度
	 */
	private String Precision;

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	@Override
	public String toString() {
		return "LocationEventMessage [Latitude=" + Latitude + ", Longitude=" + Longitude + ", Precision=" + Precision
				+ ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + ", Event=" + Event + "]";
	}

}
