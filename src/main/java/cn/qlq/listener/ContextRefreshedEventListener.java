package cn.qlq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cn.qlq.service.user.UserService;

/**
 * 上下文更新事件（ContextRefreshedEvent）：该事件会在ApplicationContext被初始化或者更新时发布。
 * 也可以在调用ConfigurableApplicationContext 接口中的refresh()方法时被触发。
 * 
 * @author Administrator
 *
 */
@Component
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("ContextRefreshedEvent=========== 容器启动完成");
		System.out.println(event);
		
		// 创建默认用户
//		userService.addUser(user);
	}

}
