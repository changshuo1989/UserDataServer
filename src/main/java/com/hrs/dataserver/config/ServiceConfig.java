package com.hrs.dataserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hrs.dataserver.service.EventTemplateDataService;
import com.hrs.dataserver.service.FunctionDataService;
import com.hrs.dataserver.service.UserDailyDataService;

@Configuration
public class ServiceConfig {

	@Bean(name="userDailyDataService")
	public UserDailyDataService userService(){
		return new UserDailyDataService();
	}
	
	@Bean(name="functionDataService")
	public FunctionDataService functionDataService(){
		return new FunctionDataService();
	}
	
	@Bean(name="eventTemplateDataService")
	public EventTemplateDataService eventTemplateDataService(){
		return new EventTemplateDataService();
	}
}
