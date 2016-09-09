package com.hrs.dataserver.representation;

import org.springframework.data.mongodb.core.MongoOperations;

public interface IFunctionDataRepresentation {
	
	public String getFunctionLayer();
	
	public String getFunctionName();
	
	public Class<?> getEntityClass();
	
	public String getMongoScript(String collectionName);
	
}
