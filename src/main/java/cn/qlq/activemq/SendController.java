package cn.qlq.activemq;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class SendController {
	@Autowired
	private Producer producer;

	@Test
	public void contextLoads() throws InterruptedException {
		Destination destination = new ActiveMQQueue("myQueue");

		for (int i = 0; i < 5; i++) {
			producer.sendMessage(destination, "message" + i);
		}
	}
}
