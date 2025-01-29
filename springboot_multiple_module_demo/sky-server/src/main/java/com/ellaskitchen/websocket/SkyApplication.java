package com.ellaskitchen.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SkyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyApplication.class, args);
	}

}
