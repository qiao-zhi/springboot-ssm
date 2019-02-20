package cn.qlq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration // 通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
//开启Task
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class SpringTask {

}
