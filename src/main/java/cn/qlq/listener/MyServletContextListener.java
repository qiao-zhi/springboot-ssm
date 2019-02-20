package cn.qlq.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("容器创建");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("容器销毁");
	}

}