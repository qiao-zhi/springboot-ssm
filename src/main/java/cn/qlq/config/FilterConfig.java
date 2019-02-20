package cn.qlq.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import cn.qlq.filter.MyFilter;
import cn.qlq.filter.MyFilter2;

/**
 * 注册filter,setOrder可以控制顺序
 * 
 * @author Administrator
 *
 */
// @Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean registMyFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());
		registration.addUrlPatterns("/*");
		registration.setName("myFilter");
		registration.setOrder(2);
		return registration;
	}

	@Bean
	public FilterRegistrationBean registMyFilter2() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter2());
		registration.addUrlPatterns("/*");
		registration.setName("myFilter2");
		registration.setOrder(1);
		return registration;
	}
}