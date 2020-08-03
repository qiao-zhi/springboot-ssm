package cn.qlq.mybatisplus.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;

import cn.qlq.config.datasource.DynamicDataSource;

@Configuration
@MapperScan({ "cn.qlq.mapper", "cn.qlq.sharedjdbc.client", "cn.qlq.mybatisplus" })
public class MybatisConfiguration {

	private static final Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);

	@Bean("master")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource master() {
		HikariDataSource druidDataSource = new HikariDataSource();
		return druidDataSource;
	}

	@Bean("slave")
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slave() {
		// return DataSourceBuilder.create().build();
		HikariDataSource druidDataSource = new HikariDataSource();
		return druidDataSource;
	}

	@Bean("dynamicDataSource")
	public DataSource dynamicDataSource() {
		DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("master", master());
		dataSourceMap.put("slave", slave());

		log.info("dynamicDataSource dataSourceMap:{}", dataSourceMap);
		// 将 master 数据源作为默认指定的数据源
		dynamicDataSource.setDefaultDataSource(master());
		// 将 master 和 slave 数据源作为指定的数据源
		dynamicDataSource.setDataSources(dataSourceMap);
		return dynamicDataSource;
	}

	@Bean
	public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		// 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
		sessionFactory.setDataSource(dynamicDataSource());

		// 扫描Model
		// sessionFactory.setTypeAliasesPackage("cn.qlq");
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// 扫描映射文件
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/**/*Mapper.xml"));

		// 添加插件
		Interceptor[] interceptors = getPlugins();
		if (ArrayUtils.isNotEmpty(interceptors)) {
			sessionFactory.setPlugins(interceptors);
		}

		return sessionFactory;
	}

	private Interceptor[] getPlugins() {
		Interceptor[] plugins = new Interceptor[0];

		// PageHelper分页插件
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		pageInterceptor.setProperties(properties);

		plugins = ArrayUtils.add(plugins, pageInterceptor);
		return plugins;
	}
}
