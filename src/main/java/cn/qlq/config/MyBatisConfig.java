package cn.qlq.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.pagehelper.PageInterceptor;

@Configuration
public class MyBatisConfig {

	@Bean
	@ConditionalOnMissingBean // 当容器里没有指定的Bean的情况下创建该对象
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 设置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);
		// 设置mybatis的主配置文件
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource mybatisConfigXml = resolver.getResource("classpath:mybatis/SqlMapConfig.xml");
		sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
		// 设置别名包
		sqlSessionFactoryBean.setTypeAliasesPackage("cn.qlq.bean");
		
		/**********S    当spring-boot配置文件未集成pageHelper可以用下面方法************/
        //分页插件
		PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("params", "pageNum=pageNum;pageSize=pageSize");
        pageInterceptor.setProperties(properties);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
		
        /**********E    当spring-boot配置文件未集成pageHelper可以用上面方法************/
        
		return sqlSessionFactoryBean;
	}
	
	
	
	
}
