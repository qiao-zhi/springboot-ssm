package cn.qlq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.qlq.interceptor.MyInterceptor1;
import cn.qlq.interceptor.MyInterceptor2;

/**
 * 1.注册拦截器 2.解决JSON返回实体类报错
 * 
 * @author Administrator
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
		registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/th/**").addPathPatterns("/freemarker/**");
		registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/freemarker/**");

		super.addInterceptors(registry);
	}
}