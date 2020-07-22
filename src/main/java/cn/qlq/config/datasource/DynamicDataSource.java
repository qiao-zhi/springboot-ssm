package cn.qlq.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	public static Map<Object, Object> dynamicTargetDataSources = new HashMap<>();

	private static DynamicDataSource dynamicDataSource = null;

	private DynamicDataSource() {

	}

	public static synchronized DynamicDataSource getInstance() {
		if (dynamicDataSource == null) {
			dynamicDataSource = new DynamicDataSource();
		}

		return dynamicDataSource;
	}

	/**
	 * 如果不希望数据源在启动配置时就加载好，可以定制这个方法，从任何你希望的地方读取并返回数据源
	 * 比如从数据库、文件、外部接口等读取数据源信息，并最终返回一个DataSource实现类对象即可
	 * 
	 * @return
	 */
	@Override
	protected DataSource determineTargetDataSource() {
		return super.determineTargetDataSource();
	}

	/**
	 * 如果希望所有数据源在启动配置时就加载好，这里通过设置数据源Key值来切换数据，定制这个方法
	 * 
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceKey();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		this.dynamicTargetDataSources = targetDataSources;
	}

	/**
	 * 新增数据源
	 * 
	 * @param key
	 *            数据源标识
	 * @param dataSource
	 *            数据源
	 */
	public void addTargetDataSources(Object key, Object dataSource) {
		dynamicTargetDataSources.put(key, dataSource);
		DynamicDataSourceContextHolder.addDataSourceKey(key);
		super.setTargetDataSources(dynamicTargetDataSources);
		super.afterPropertiesSet();
	}

	/**
	 * 设置默认数据源
	 * 
	 * @param defaultDataSource
	 */
	public void setDefaultDataSource(Object defaultDataSource) {
		super.setDefaultTargetDataSource(defaultDataSource);
	}

	public void setDataSources(Map<Object, Object> dataSources) {
		setTargetDataSources(dataSources);
		// TODO 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
		DynamicDataSourceContextHolder.addDataSourceKeys(dataSources.keySet());
	}
}
