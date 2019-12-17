package cn.qlq.utils.file;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileHandleUtil {

	public static final String LANGUAGE = "zh";

	public static final String COUNTRY = "CN";

	private static String getProperties(String baseName, String section) {
		String retValue = "";
		try {
			Locale locale = new Locale(LANGUAGE, COUNTRY);
			ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
			retValue = (String) rb.getObject(section);
		} catch (Exception e) {
		}
		return retValue;
	}

	public static String getValue(String fileName, String key) {
		String value = getProperties(fileName, key);
		return value;
	}

	public static boolean deletePlainFile(String propertiesFileName, String fileName) {
		if (fileName == null) {
			return false;
		}

		String fileDir = StringUtils.defaultIfBlank(FileHandleUtil.getValue("path", "picture"), "E:/picture/");
		try {
			FileUtils.deleteQuietly(new File(fileDir + fileName));
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean uploadSpringMVCFile(MultipartFile multipartFile, String fileName) throws Exception {
		String fileDir = StringUtils.defaultIfBlank(FileHandleUtil.getValue("path", "picture"), "E:/picture/");

		if (!new File(fileDir).exists()) {
			new File(fileDir).mkdirs();
		}
		multipartFile.transferTo(new File(fileDir + fileName));// 保存文件

		return true;
	}

	public static File getFileByName(String path) {
		String fileDir = StringUtils.defaultIfBlank(FileHandleUtil.getValue("path", "picture"), "E:/picture/");
		return new File(fileDir+path);
	}
}