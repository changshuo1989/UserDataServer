package com.hrs.dataserver.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hrs.dataserver.entity.FunctionData;

public interface FunctionDataDAO extends MongoRepository<FunctionData, String>, CustomFunctionDataDAO{
	
}
