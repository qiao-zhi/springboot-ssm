package cn.qlq.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "myQueue")
	public void receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:" + text);
	}
}