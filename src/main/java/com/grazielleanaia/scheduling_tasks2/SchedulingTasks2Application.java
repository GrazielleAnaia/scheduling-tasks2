package com.grazielleanaia.scheduling_tasks2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SchedulingTasks2Application {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingTasks2Application.class, args);
	}

}
