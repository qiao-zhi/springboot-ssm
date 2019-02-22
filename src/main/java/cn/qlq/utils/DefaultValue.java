package cn.qlq.utils;

/**
 * 默认值
 * 
 * @author Administrator
 *
 */
public class DefaultValue {

	/**
	 * 每页数量
	 */
	public static final int PAGE_SIZE = 6;

	/**
	 * 移动端token的默认失效时间
	 */
	public static final int TOKEN_DEFAULT_TIME = 2;

	/**
	 * 移动端token的更新时间(也就是如果更新时间+当前时间大于预计失效时间就更新token)
	 */
	public static final int TOKEN_UPDATE_TIME = 1;

}
