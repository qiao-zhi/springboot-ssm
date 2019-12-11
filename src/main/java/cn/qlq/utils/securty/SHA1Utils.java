package cn.qlq.utils.securty;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 摘要算法：SHA算法Secure Hash Algorithm（安全hash算法） 安全散列算法（hash函数 将原始信息压缩
 * 返回散列值）可以是SHA-1，SHA1是目前最安全 的摘要算法 摘要的长度为 20字节
 * 
 * 其他的SHA 包括 SHA-256（32字节）
 * 
 * 20byte = 160 bit,换成16进制字符串就是40位字符串
 * 
 * @author Administrator
 *
 */
public class SHA1Utils {

	/**
	 * 
	 * @param sourceCode
	 * @return 40位的16进制字符串
	 */
	public static String sha1Hex(String sourceCode) {
		return DigestUtils.sha1Hex(sourceCode);
	}

	/**
	 * 
	 * @param sourceCode
	 * @return length为20的字节数组，如果转为字符串需要new String(Hex.encodeHex(return))
	 */
	public static byte[] sha1(String sourceCode) {
		// length为20的字节数组
		return DigestUtils.sha1(sourceCode);
	}

	public static void main(String[] args) {
		System.out.println(sha1Hex("qlq"));
	}

}
