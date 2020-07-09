package cn.qlq.mybatisplus.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageInterceptor;

@Configuration
@MapperScan({ "cn.qlq" })
public class MybatisConfiguration {

	private static final Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);

	// @Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		// 扫描Model
		sessionFactory.setTypeAliasesPackage("cn.qlq.bean");
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
