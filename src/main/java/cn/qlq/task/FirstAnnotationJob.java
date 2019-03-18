package cn.qlq.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class FirstAnnotationJob {
	private static int count;

	@Scheduled(fixedRate = 10000)
	public void cron() {
		try {
			Thread.sleep(2000);
			System.out.println("spring anno task execute times " + count++);
		} catch (InterruptedException e) {
			System.err.println("InterruptedException " + e);
			Thread.currentThread().interrupt();
		}
	}
}