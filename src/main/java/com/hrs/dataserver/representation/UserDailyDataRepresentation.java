package com.hrs.dataserver.representation;

import java.util.Map;


public class UserDailyDataRepresentation {
	private String env;
	private String utype;
	private String uid;
	private String type;
	private String ts;
	private Map<String, String> data;
	
	public String getEnv() {
		return env;
	}
	
	public void setEnv(String env) {
		this.env = env;
	}
	
	public String getUtype() {
		return utype;
	}
	
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTs() {
		return ts;
	}
	
	public void setTs(String ts) {
		this.ts = ts;
	}
	
	public Map<String, String> getData() {
		return data;
	}
	
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	/*
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("env: "+env);
		sb.append("utype: "+utype);
		sb.append("uid: "+uid);
		sb.append("type: "+type);
		for(int i=0; i<data.size(); i++){
			sb.append("data "+i+": "+data.get(i).toString());
		}
		return sb.toString();
	}
	*/
}
