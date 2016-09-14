package com.hrs.dataserver.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hrs.dataserver.entity.EventTemplateData;

public interface EventTemplateDataDAO extends MongoRepository<EventTemplateData, String>, CustomEventTemplateDataDAO{
	
}
