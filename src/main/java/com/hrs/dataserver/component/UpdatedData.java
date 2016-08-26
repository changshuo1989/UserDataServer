package com.hrs.dataserver.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UpdatedData {
	@Transient
	private String name;
	
	private Map<String, String> value;
	
	private long timestamp;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, String> getValue() {
		return value;
	}
	
	public void setValue(Map<String, String> value) {
		this.value = value;
	}
	
	public void addToValue(String mKey, String mValue){
		if(this.value==null){
			this.value=new HashMap<String, String>();
		}
		this.value.put(mKey, mValue);
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
