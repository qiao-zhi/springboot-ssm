package cn.qlq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("cn")
public class MySpringBootApplication {

	public static void main(String[] args) {
		// 入口运行类
		SpringApplication.run(MySpringBootApplication.class, args);
	}

}
