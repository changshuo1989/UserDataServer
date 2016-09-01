package com.hrs.dataserver.dto;

import java.util.List;
import java.util.Map;


public class UserDailyDataDTO {
	private String env;
	
	private String hrsId;
	
	private String type;
	
	private String date;
	
	//private List<String> data;
	private Map<String, List<Map<String, String>>> data;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getHrsId() {
		return hrsId;
	}

	public void setHrsId(String hrsId) {
		this.hrsId = hrsId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, List<Map<String, String>>> getData() {
		return data;
	}

	public void setData(Map<String, List<Map<String, String>>> data) {
		this.data = data;
	} 
	
	
	
	
}
