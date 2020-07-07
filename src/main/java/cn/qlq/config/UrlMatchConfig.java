package cn.qlq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 设置匹配指定后缀*.action *.do的路径(导致静态资源文件失败，没有解决)
 * 
 * @author Administrator
 *
 */
@Configuration
public class UrlMatchConfig extends WebMvcConfigurationSupport {

	// 解决返回JSON类型报错
	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// 开启支持扩展名功能(关闭根据请求后缀设置contentType)
		configurer.favorPathExtension(false)
				// 开启内容协商的请求参数功能,默认没有开启
				.favorParameter(false);
	}

	/**
	 * 设置匹配*.action后缀请求
	 * 
	 * @param dispatcherServlet
	 * @return
	 */
	/*
	 * @Bean public ServletRegistrationBean
	 * servletRegistrationBean(DispatcherServlet dispatcherServlet) {
	 * ServletRegistrationBean servletServletRegistrationBean = new
	 * ServletRegistrationBean(dispatcherServlet); // 参数接受可变类型的多个参数支持多种后缀的匹配
	 * servletServletRegistrationBean.addUrlMappings("*.html", "*.do"); return
	 * servletServletRegistrationBean; }
	 */

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/")
				.addResourceLocations("classpath:/public/").addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}