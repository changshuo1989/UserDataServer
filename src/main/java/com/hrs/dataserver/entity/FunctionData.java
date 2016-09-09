package com.hrs.dataserver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hrs.dataserver.tool.IdAdapter;

@Document
public class FunctionData {
	@Id
	private String id;

	@Indexed
	private String layer;
	@Indexed
	private String name;
	@Indexed
	private long timestamp;	
	
	private String function;
	
	
	public String getId() {
		return id;
	}
	
	public void setId() {
		try{
			this.id = IdAdapter.getFunctionIdByElements(this.layer, this.name);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
	
	
}
