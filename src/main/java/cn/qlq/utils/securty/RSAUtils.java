package cn.qlq.utils.securty;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 非对称加密RSA算法(公钥可以在网络上传输，私钥存在自己服务器。客户端用公钥加密，服务端用私钥解密，解密之后获取自己的数据。)
 * 生成秘钥是非常耗时的，而且解密也是比较耗时的，所以对于一些不必每次都解密的请求最好的办法是一次解密多次使用
 * 
 * (生成的秘钥以及加密解密都是byte数组，加密的处理方法可以将byte数组转为16进制字符串、也可以对byte数组进行base64编码处理，
 * 解密就是对应的逆向方法。下面将公钥私钥进行base64编码解码；对加密后的数据提供了转16进制与base64编码两种方式)
 * 
 * @author Administrator
 *
 */
public class RSAUtils {

	/**
	 * 公钥，应该是在客户端存放，用于加密
	 */
	private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANvfYE3tkvcmFzXkGBvTggczqTtFLCpnpPG5y9pDKz62y2sLhlYu/lLvQjjAfrTDuyEOi1RY7MYv4PN8sKJQPM8CAwEAAQ==";

	/**
	 * 私钥，服务端存放，用于解密(可以存于文件中或者数据库中)
	 */
	private static final String PRIVATE_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA299gTe2S9yYXNeQYG9OCBzOpO0UsKmek8bnL2kMrPrbLawuGVi7+Uu9COMB+tMO7IQ6LVFjsxi/g83ywolA8zwIDAQABAkEAn+cuNL5ouZFcI/dEiUgjMnWhx9cgrZ4+2QWUeaGL28jfDwQPPuJigqTnvFLK4wgsSvWcnUpUO0miIwvYjZpAQQIhAPz9XHZVR366lzzuJIsYW6yXsmLFk9O6nPtXZY9JGniRAiEA3n0i0fusvcFcccg81+sRFHXR+30lv7xW2bme/YwdD18CIHWvDhHlvapP9gZb6RfjLtHshwi3S/rT9i5dC1tJ8ExhAiAi0jDt7lbFyAqA4FbtcCPXMO1xT8/lp0NshgmlIxW1PwIhAI8e7avqMJiX0MBITc7vx4zDd/R7FDFVAgnZesV9U7cb";

	public static String encryptAndBase64Encode(String str) throws Exception {
		return encryptAndBase64Encode(str, PUBLIC_KEY);
	}

	/**
	 * 加密之后Base64编码
	 * 
	 * @param str
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptAndBase64Encode(String str, String publicKey) throws Exception {
		byte[] encrypt = encrypt(str.getBytes(), publicKey);
		byte[] encode = Base64.encodeBase64(encrypt);
		return new String(encode, "UTF-8");
	}

	public static String encryptAndToHexStr(String str) throws Exception {
		return encryptAndToHexStr(str, PUBLIC_KEY);
	}

	/**
	 * 加密之后转为hex字符串
	 * 
	 * @param str
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encryptAndToHexStr(String str, String publicKey) throws Exception {
		byte[] encrypt = encrypt(str.getBytes(), publicKey);
		return Hex.encodeHexString(encrypt);
	}

	/**
	 * 加密字节数组
	 * 
	 * @param bytes
	 *            需要加密的字节数组(未经BASE64编码之后的)
	 * @param publicKey
	 *            Base64编码的公钥串
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] bytes, String publicKey) throws Exception {
		Cipher cip = Cipher.getInstance("RSA");
		cip.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		byte[] mw = cip.doFinal(bytes);
		return mw;
	}

	public static String base64DecodeAndDecrypt(String str) throws Exception {
		return base64DecodeAndDecrypt(str, PRIVATE_KEY);
	}

	/**
	 * Base64解码之后解密
	 * 
	 * @param str
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String base64DecodeAndDecrypt(String str, String privateKey) throws Exception {
		byte[] base64Decoded = Base64.decodeBase64(str);
		byte[] decryptedBytes = decrypt(base64Decoded, privateKey);
		return new String(decryptedBytes, "UTF-8");
	}

	public static String hexStrToBytesAndDecrypt(String str) throws Exception {
		return hexStrToBytesAndDecrypt(str, PRIVATE_KEY);
	}

	/**
	 * 将16进制的字符串转为bytes后解密
	 * 
	 * @param str
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String hexStrToBytesAndDecrypt(String str, String privateKey) throws Exception {
		byte[] bytes = Hex.decodeHex(str.toCharArray());
		byte[] decryptedBytes = decrypt(bytes, privateKey);
		return new String(decryptedBytes, "UTF-8");
	}

	/**
	 * 解密
	 * 
	 * @param bytes
	 *            需要解密的字节数组
	 * @param privateKey
	 *            Base64编码的私钥串
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] bytes, String privateKey) throws Exception {
		Cipher cip = Cipher.getInstance("RSA");
		cip.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
		byte[] mw = cip.doFinal(bytes);
		return mw;
	}

	/**
	 * 生成秘钥对(公钥和私钥经过Base64编码)
	 * 
	 * @throws Exception
	 */
	public static Map<String, String> generateBase64EncodedKey() throws Exception {
		// 生成RSA密钥对
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair kp = kpg.generateKeyPair();
		PublicKey pk = kp.getPublic();
		PrivateKey prk = kp.getPrivate();

		// 将公钥私钥用Base64编码为能读懂的英文字符串
		// 获取公钥采用的算法
		// String algorithm = pk.getAlgorithm();
		// 获取到code，可以调用Hex.encodeHexString(encoded)转为16进制字符串或者调用Base64进行编码
		byte[] pkEncoded = pk.getEncoded();
		byte[] pkEncodedBase64Encode = Base64.encodeBase64(pkEncoded);
		String pkEncodedBase64EncodeStr = new String(pkEncodedBase64Encode);

		byte[] prkEncoded = prk.getEncoded();
		byte[] prkEncodedBase64Encode = Base64.encodeBase64(prkEncoded);
		String prkEncodedBase64EncodeStr = new String(prkEncodedBase64Encode);

		Map<String, String> result = new LinkedHashMap<>();
		result.put("publicKey", pkEncodedBase64EncodeStr);
		result.put("privateKey", prkEncodedBase64EncodeStr);

		return result;
	}

	/**
	 * 根据公钥字符串获取公钥对象
	 * 
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(String publicKey) throws Exception {
		byte[] bytes = publicKey.getBytes();
		byte[] decode = Base64.decodeBase64(bytes);
		return getPublicKey(decode);
	}

	/**
	 * 读取公钥字节数组转换为对象
	 * 
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(byte[] publicKeyBytes) throws Exception {
		X509EncodedKeySpec x = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory fac = KeyFactory.getInstance("RSA");
		return fac.generatePublic(x);
	}

	/**
	 * 根据私钥字符串获取私钥对象
	 * 
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] bytes = privateKey.getBytes();
		byte[] decode = Base64.decodeBase64(bytes);
		return getPrivateKey(decode);
	}

	/**
	 * 读取私钥字节数组转换为对象
	 * 
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(byte[] bt) throws Exception {
		PKCS8EncodedKeySpec x = new PKCS8EncodedKeySpec(bt);
		KeyFactory fac = KeyFactory.getInstance("RSA");
		return fac.generatePrivate(x);
	}

	public static void main(String[] args) {
		try {
			// Map<String, String> generateBase64EncodedKey =
			// generateBase64EncodedKey();
			// System.out.println(generateBase64EncodedKey);

			String encryptAndBase64Encode = encryptAndBase64Encode("qlq");
			System.out.println(encryptAndBase64Encode);
			System.out.println(base64DecodeAndDecrypt(encryptAndBase64Encode));

			String encryptAndToHex = encryptAndToHexStr("qlq");
			System.out.println(encryptAndToHex);
			System.out.println(hexStrToBytesAndDecrypt(encryptAndToHex));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
