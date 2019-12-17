package cn.qlq.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作properties文件的工具类(此工具类的file都是src目录下的properties文件，编译之后在build目录下)
 * 
 * @author QiaoLiQiang
 * @time 2018年11月3日下午12:05:32
 */
public class PropertiesFileUtils {

	private static final Logger log = LoggerFactory.getLogger(PropertiesFileUtils.class);

	/**
	 * 构造函数私有化
	 */
	private PropertiesFileUtils() {
	}

	public static void saveOrUpdateProperty(String key, String value) {
		saveOrUpdateProperty("settings.properties", key, value);
	}

	/**
	 * 保存或更新properties文件中的key
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void saveOrUpdateProperty(String fileName, String key, String value) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
			inputStream = new FileInputStream(new File(path));
			log.debug("path -> {}", path);

			properties.load(inputStream);
			properties.setProperty(key, value);

			// 保存到文件中(如果有的话会自动更新，没有会创建)
			outputStream = new FileOutputStream(new File(path));
			properties.store(outputStream, "");
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}

	public static String getPropertyValue(String key) {
		return getPropertyValue("settings.properties", key);
	}

	/**
	 * 获取文件中对应的key的名称
	 * 
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String fileName, String key) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		String value = "";

		try {
			String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
			log.info("path -> {}", path);
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);
			value = properties.getProperty(key);
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return value;
	}

	/**
	 * 获取Properties
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		Properties properties = new Properties();
		InputStream inputStream = null;

		try {
			String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
			log.info("path -> {}", path);
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return properties;
	}

	/**
	 * 删除key
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties removeProperty(String fileName, String key) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			String path = PropertiesFileUtils.class.getClassLoader().getResource(fileName).getPath();
			log.info("path -> {}", path);
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);
			log.info("properties -> {}", properties);
			if (properties != null && properties.containsKey(key)) {
				log.info("remove key:{}", key);
				properties.remove(key);
			}

			// 保存到文件中(将properties保存到文件)
			outputStream = new FileOutputStream(new File(path));
			properties.store(outputStream, "");
		} catch (FileNotFoundException e) {
			log.error("saveOrUpdateProperty error", e);
		} catch (IOException e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}

		return properties;
	}

	/**
	 * 保存或更新properties文件中的key
	 * 
	 * @param path
	 *            文件全路径
	 * @param key
	 * @param value
	 */
	public static void saveOrUpdatePropertyByFilePath(String path, String key, String value) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);
			properties.setProperty(key, value);

			// 保存到文件中(如果有的话会自动更新，没有会创建)
			outputStream = new FileOutputStream(new File(path));
			properties.store(outputStream, "");
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}

	/**
	 * 获取Properties
	 * 
	 * @param path
	 *            文件全路径
	 * @param key
	 * @return
	 */
	public static String getPropertyValueByFilePath(String path, String key) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		String value = "";
		try {
			log.info("path -> {}", path);
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);

			value = properties.getProperty(key);
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return value;
	}

	/**
	 * 获取Properties
	 * 
	 * @param path
	 *            文件全路径
	 * @return
	 */
	public static Properties getPropertiesByFilePath(String path) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			log.info("path -> {}", path);
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return properties;
	}

	/**
	 * 获取Properties
	 * 
	 * @param path
	 *            文件全路径
	 * @param key
	 *            key值
	 * @return
	 */
	public static Properties removePropertyByFilePath(String path, String key) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			log.info("path -> {}", path);
			inputStream = new FileInputStream(new File(path));
			properties.load(inputStream);
			log.info("properties -> {}", properties);
			if (properties != null && properties.containsKey(key)) {
				log.info("remove key:{}", key);
				properties.remove(key);
			}

			// 保存到文件中(将properties保存到文件)
			outputStream = new FileOutputStream(new File(path));
			properties.store(outputStream, "");
		} catch (Exception e) {
			log.error("saveOrUpdateProperty error", e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}

		return properties;
	}

	public static void main(String[] args) {
		System.out.println(getPropertyValue("application.properties", "logging.level.cn.qs.mapper"));
	}

}