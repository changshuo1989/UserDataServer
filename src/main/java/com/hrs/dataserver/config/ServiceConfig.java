package com.hrs.dataserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hrs.dataserver.service.UserDailyDataService;

@Configuration
public class ServiceConfig {

	@Bean(name="userDailyDataService")
	public UserDailyDataService userService(){
		return new UserDailyDataService();
	}
}
