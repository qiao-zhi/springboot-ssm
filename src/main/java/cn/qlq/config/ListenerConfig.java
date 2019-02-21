package cn.qlq.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import cn.qlq.listener.MyHttpSessionListener;
import cn.qlq.listener.MyServletContextListener;

/**
 * 
 * @author Administrator
 *
 */
//@Configuration
public class ListenerConfig {
	@Bean
	public ServletListenerRegistrationBean<MyHttpSessionListener> listenerRegist() {
		ServletListenerRegistrationBean<MyHttpSessionListener> srb = new ServletListenerRegistrationBean<MyHttpSessionListener>();
		srb.setListener(new MyHttpSessionListener());
		return srb;
	}

	@Bean
	public ServletListenerRegistrationBean<MyServletContextListener> listenerRegist2() {
		ServletListenerRegistrationBean<MyServletContextListener> srb = new ServletListenerRegistrationBean<MyServletContextListener>();
		srb.setListener(new MyServletContextListener());
		return srb;
	}

}