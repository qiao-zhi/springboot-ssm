package cn.qlq.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qlq.utils.weixin.MessageHandler;

public class BeanUtils {

	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 内省进行数据转换-javaBean转map
	 * 
	 * @param obj
	 *            需要转换的bean
	 * @return 转换完成的map
	 * @throws Exception
	 */
	public static <T> Map<String, Object> beanToMap(T obj, boolean putIfNull) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// 获取javaBean的BeanInfo对象
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
		// 获取属性描述器
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			// 获取属性名
			String key = propertyDescriptor.getName();
			// 获取该属性的值
			Method readMethod = propertyDescriptor.getReadMethod();
			// 通过反射来调用javaBean定义的getName()方法
			Object value = readMethod.invoke(obj);

			if (value == null && !putIfNull) {
				continue;
			}

			map.put(key, value);
		}

		return map;
	}

	public static <T> List<Map<String, Object>> beansToMaps(List<T> objs, boolean putIfNull) throws Exception {
		return beansToMaps(objs, putIfNull, false);
	}

	public static <T> List<Map<String, Object>> beansToMaps(List<T> objs, boolean putIfNull, boolean addIndex)
			throws Exception {

		List<Map<String, Object>> result = new ArrayList<>();

		Map<String, Object> beanToMap = null;
		int index = 0;
		for (Object obj : objs) {
			beanToMap = beanToMap(obj, putIfNull);
			if (addIndex) {
				beanToMap.put("index", ++index);
			}

			result.add(beanToMap);
		}

		return result;
	}

	public static <T> T map2Bean(Map<String, Object> map, Class<T> clz) throws Exception {
		return map2Bean(map, clz, false);
	}

	/**
	 * Map转bean
	 * 
	 * @param map
	 *            map
	 * @param clz
	 *            被转换的类字节码对象
	 * @return
	 * @throws Exception
	 */
	public static <T> T map2Bean(Map<String, Object> map, Class<T> clz, boolean ignoreCase) {

		try {
			// new 出一个对象
			T obj = clz.newInstance();
			// 获取person类的BeanInfo对象
			BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
			// 获取属性描述器
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				// 获取属性名
				String key = propertyDescriptor.getName();
				Object value = null;

				Set<String> keySets = map.keySet();
				for (String tmpKey : keySets) {
					if (StringUtils.isBlank(tmpKey)) {
						continue;
					}

					if (tmpKey.equals(key) || (ignoreCase && tmpKey.toLowerCase().equals(key.toLowerCase()))) {
						value = map.get(tmpKey);
						break;
					}
				}

				Class<?> propertyType = propertyDescriptor.getPropertyType();
				if (propertyType.equals(long.class) || propertyType.equals(Long.class)) {
					if (value != null) {
						value = Long.valueOf(value.toString());
					} else {
						value = 0L;
					}
				}

				// 通过反射来调用Person的定义的setName()方法
				Method writeMethod = propertyDescriptor.getWriteMethod();
				writeMethod.invoke(obj, value);
			}

			return obj;
		} catch (Exception e) {
			logger.error("map转bean出错", e);
		}

		return null;
	}

	public static <T> List<T> maps2Beans(List<Map<String, Object>> maps, Class<T> clz) throws Exception {
		List<T> result = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			result.add(map2Bean(map, clz));
		}

		return result;
	}

	/**
	 * 复制origin的值到dest上
	 * 
	 * @param dest
	 *            目标对象
	 * @param origin
	 *            元对象
	 * @param setNull
	 *            如果源对象属性为null是否覆盖
	 * @param excludeFieldNames
	 *            排除的属性
	 */
	public static <T> void copyProperties(T dest, T origin, boolean setNull, String[] excludeFieldNames) {
		try {
			// 获取person类的BeanInfo对象
			BeanInfo destBeanInfo = Introspector.getBeanInfo(dest.getClass(), Object.class);

			// 获取目标属性描述器
			PropertyDescriptor[] destBeanInfoPropertyDescriptors = destBeanInfo.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : destBeanInfoPropertyDescriptors) {
				// 获取属性名
				String key = propertyDescriptor.getName();
				if (ArrayUtils.contains(excludeFieldNames, key)) {
					continue;
				}

				// 获取该属性的值
				Method readMethod = propertyDescriptor.getReadMethod();

				// 如果源对象没有对应属性就跳过
				Object srcValue = null;
				try {
					srcValue = readMethod.invoke(origin);
				} catch (Exception ignored) {
					// ignored
					continue;
				}

				// 如果源对象的值null且null不设置的时候跳过
				if (srcValue == null && !setNull) {
					continue;
				}

				// 获取setter方法修改属性
				Method writeMethod = propertyDescriptor.getWriteMethod();
				writeMethod.invoke(dest, srcValue);
			}
		} catch (Exception ignored) {
			// ignored
		}
	}

	public static <T> void copyProperties(T dest, T origin) {
		copyProperties(dest, origin, false, null);
	}

	public static <T> Object getProperty(T object, String proeprty) {
		// 获取javaBean的BeanInfo对象
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(object.getClass(), Object.class);
		} catch (IntrospectionException ignore) {
			return new Object();
		}

		// 获取属性描述器
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			// 获取属性名
			String key = propertyDescriptor.getName();
			if (proeprty.equals(key)) {
				// 获取该属性的值
				Method readMethod = propertyDescriptor.getReadMethod();
				// 通过反射来调用javaBean定义的getName()方法
				try {
					Object value = readMethod.invoke(object);
					return value;
				} catch (Exception ignore) {
					return new Object();
				}
			}
		}

		return new Object();
	}
}
