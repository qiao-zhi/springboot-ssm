package cn.qlq.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 发布到独立的Tomcat需要继承SpringBootServletInitializer类并重写configure方法

@Configuration // 通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "cn.qlq") // 配置扫描包
public class SpringConfig extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 设置启动类，用于独立tomcat运行的入口
		return builder.sources(SpringConfig.class);
	}

}