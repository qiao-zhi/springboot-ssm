package cn.qlq.utils.securty;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * MD5算法（Message Digest Algorithm 5） 可以保证数据传输完整性和一致性 摘要后长度为16字节 摘要信息中不包含原文信息
 * 
 * 所有加密结果不可逆（无法解密） 一般在传送文件时 对源文件进行md5 hash 传送到对方后 检测hash值是否相等 如果相等文件传输正确;如果不相等
 * 说明文件被篡改（加入木马）或者未传送完成
 * 
 * (16字节 = 128bit， 换成16进制就是32位串)
 * 
 * @author Administrator
 *
 */
public class MD5Utils {

	private MD5Utils() {
		// ignore
	}

	public static String md5(String source) {
		return md5(source, "");
	}

	/**
	 * md5加密
	 * 
	 * @param source
	 *            原串
	 * @param salt
	 *            需要加的盐
	 * @return 32位16进制小写字符串
	 */
	public static String md5Hex(String source, String salt) {
		if (StringUtils.isBlank(source)) {
			throw new IllegalArgumentException();
		}

		if (StringUtils.isNotBlank(salt)) {
			source += salt;
		}

		// md5hex是以32位16进制的小写字符串形式返回
		return DigestUtils.md5Hex(source);
	}

	/**
	 * md5加密
	 * 
	 * @param source
	 *            原串
	 * @param salt
	 *            需要加的盐
	 * @return 32位16进制小写字符串
	 */
	public static String md5(String source, String salt) {
		if (StringUtils.isBlank(source)) {
			throw new IllegalArgumentException();
		}

		if (StringUtils.isNotBlank(salt)) {
			source += salt;
		}

		// 返回byte数组，length为16。需要调用编码工具转为16进制的char[]
		byte[] md5 = DigestUtils.md5(source);
		return new String(Hex.encodeHex(md5));
	}

	/**
	 * 调用原生的Java实现md5(这是一个原生实现的参考案例)
	 * 
	 * @param source
	 * @param salt
	 * @return
	 */
	public static String md5Java(String source, String salt) {
		if (StringUtils.isBlank(source)) {
			throw new IllegalArgumentException();
		}

		if (StringUtils.isNotBlank(salt)) {
			source += salt;
		}

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digest = md.digest(source.getBytes());
			return bytesToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
		}

		return "";
	}

	/**
	 * 将十六进制的字符串转换成字节数组
	 *
	 * @param hexString
	 * @return
	 */
	private static byte[] hexStrToByteArrs(String hexString) {
		if (StringUtils.isEmpty(hexString)) {
			return null;
		}

		hexString = hexString.replaceAll(" ", "");
		int len = hexString.length();
		int index = 0;

		byte[] bytes = new byte[len / 2];

		while (index < len) {
			String sub = hexString.substring(index, index + 2);
			bytes[index / 2] = (byte) Integer.parseInt(sub, 16);
			index += 2;
		}

		return bytes;
	}

	/**
	 * 数组转换成十六进制字符串
	 * 
	 * @param byte[]
	 * @return HexString
	 */
	private static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(md5Java("qlq", null));
		System.out.println(md5("qlq"));
	}
}
