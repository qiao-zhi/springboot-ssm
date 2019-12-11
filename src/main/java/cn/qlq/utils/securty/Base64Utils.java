package cn.qlq.utils.securty;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64就是一种基于64个可打印字符来表示二进制数据的方法。
 * 
 * @author Administrator
 *
 */
public class Base64Utils {

	public static String encode(String str) {
		byte[] encode = Base64.encodeBase64(str.getBytes());
		return new String(encode);
	}

	public static String decode(String str) {
		byte[] decode = Base64.decodeBase64(str);
		return new String(decode);
	}

	public static void main(String[] args) {
		System.out.println(encode("qlqlqlq"));
		System.out.println(decode(encode("qlqlqlq")));
	}
}
