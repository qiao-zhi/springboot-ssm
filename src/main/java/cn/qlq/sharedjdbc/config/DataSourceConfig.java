package cn.qlq.sharedjdbc.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
	public static DataSource dataSource() throws SQLException {
		// 配置真实数据源
		Map<String, DataSource> dataSourceMap = new HashMap<>();

		// 配置第一个数据源
		BasicDataSource dataSource0 = createDataSource("jdbc:mysql://localhost:3306/demo_ds_0", "root", "123456");
		dataSourceMap.put("demo_ds_0", dataSource0);

		// 配置第二个数据源
		BasicDataSource dataSource1 = createDataSource("jdbc:mysql://localhost:3306/demo_ds_1", "root", "123456");
		dataSourceMap.put("demo_ds_1", dataSource1);

		// 配置默认数据源
		BasicDataSource defaultDataSource = createDataSource("jdbc:mysql://localhost:3306/test1", "root", "123456");
		dataSourceMap.put("defaultDataSource", defaultDataSource);

		// 配置Order表规则(t_order 是逻辑表名，
		// demo_ds_${0..1}.t_order_${0..1}是实际的节点数(groovy表达式))
		TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order",
				"demo_ds_${0..1}.t_order_${0..1}");
		// 配置分库 + 分表策略
		orderTableRuleConfig.setDatabaseShardingStrategyConfig(
				new ComplexShardingStrategyConfiguration("user_id", new MyDBComplexShardingStrategy()));
		orderTableRuleConfig.setTableShardingStrategyConfig(
				new ComplexShardingStrategyConfiguration("order_id", new MyTableComplexShardingStrategy()));

		// 配置分片规则
		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
		shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
		// 设置默认数据源
		shardingRuleConfig.setDefaultDataSourceName("defaultDataSource");
		// 设置不分片的表
		// shardingRuleConfig.setBindingTableGroups(Arrays.asList("country"));

		// 获取数据源对象
		DataSource createDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,
				new Properties());
		return createDataSource;
		// return defaultDataSource;
	}

	private static BasicDataSource createDataSource(String url, String username, String password) {
		BasicDataSource defaultDataSource = new BasicDataSource();
		defaultDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		defaultDataSource.setUrl(url);
		defaultDataSource.setUsername(username);
		defaultDataSource.setPassword(password);
		return defaultDataSource;
	}
}
