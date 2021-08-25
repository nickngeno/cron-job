package com.kimmy.cronusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CronUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronUsersApplication.class, args);
	}

}
