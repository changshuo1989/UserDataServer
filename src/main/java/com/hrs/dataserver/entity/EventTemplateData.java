package com.hrs.dataserver.entity;

import java.util.List;

import org.springframework.data.annotation.Id;


public class EventTemplateData {
	@Id
	String name;
	String schema;
	List<String> endPoints;
	
	long timestamp;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public List<String> getEndPoints() {
		return endPoints;
	}
	public void setEndPoints(List<String> endPoints) {
		this.endPoints = endPoints;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
