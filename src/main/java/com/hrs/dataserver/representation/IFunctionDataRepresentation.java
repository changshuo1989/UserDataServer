package com.hrs.dataserver.representation;


public interface IFunctionDataRepresentation {
	
	public String getFunctionLayer();
	
	public String getFunctionName();
	
	public Class<?> getEntityClass();
	
	public String getMongoScript(String collectionName);
	
}
