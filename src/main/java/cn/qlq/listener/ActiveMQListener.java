package cn.qlq.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQListener implements ServletContextListener {
	private static final BrokerService brokerService = new BrokerService();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			brokerService.stop();
			System.out.println("broker 停止");
		} catch (Exception e) {

		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			brokerService.setUseJmx(true);
			brokerService.addConnector("tcp://localhost:61616");
			brokerService.start();
			System.out.println("broker 启动");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
