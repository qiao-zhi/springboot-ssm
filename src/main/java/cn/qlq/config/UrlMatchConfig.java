package cn.qlq.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 设置匹配指定后缀*.action *.do的路径(导致静态资源文件失败，没有解决)
 * 
 * @author Administrator
 *
 */
// @Configuration
public class UrlMatchConfig extends WebMvcConfigurationSupport {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// setUseSuffixPatternMatch 后缀模式匹配
		configurer.setUseSuffixPatternMatch(true);
		// setUseTrailingSlashMatch 自动后缀路径模式匹配
		configurer.setUseTrailingSlashMatch(true);
	}

	/**
	 * 设置匹配*.action后缀请求
	 * 
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean servletServletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
		// 参数接受可变类型的多个参数支持多种后缀的匹配
		servletServletRegistrationBean.addUrlMappings("*.action", "*.do");
		return servletServletRegistrationBean;
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
}