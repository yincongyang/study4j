package com.yincongyang.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot 应用启动类
 */
// Spring Boot 应用的标识
@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		// 程序启动入口
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
	}

}
