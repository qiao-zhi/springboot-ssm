package cn.qlq.utils;

import java.io.File;

/**
 * 验证工具类
 * 
 * @author QiaoLiQiang
 * @time 2017年9月18日上午9:09:47
 */
public class ValidateCheck {

	public static void checkFile(String filePath) {
		File target = new File(filePath);
		if (target.exists()) {
			target.delete();
		}
	}

	public static boolean isNotNull(String string) {
		return string != null && !("").equals(string.trim()) && !("null").equals(string);
	}

	public static boolean NotNvl(String str) {
		return (str != null) && (!("").equals(str.trim()));
	}

	public static boolean isNull(String string) {

		return string == null || ("").equals(string.trim()) || ("null").equals(string);

	}
}
