package cn.qlq.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.qlq.service.user.TokenService;

@Component
public class DeleteInvalidTokenJob {
	
	private TokenService tokenService;
	
	@Scheduled(fixedRate = 60000)
	public void cron() {
		tokenService.deleteInvalidToken();
	}
}