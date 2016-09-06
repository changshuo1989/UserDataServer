package com.hrs.dataserver.component;

import java.util.HashMap;
import java.util.Map;


public class UpdatedData {

	private String name;
	
	private Map<String, String> value;

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
}
