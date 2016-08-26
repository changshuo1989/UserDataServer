package com.hrs.dataserver.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hrs.dataserver.entity.UserDailyData;

public interface UserDailyDataDAO extends MongoRepository<UserDailyData, String>, CustomUserDailyDataDAO{
	
}
