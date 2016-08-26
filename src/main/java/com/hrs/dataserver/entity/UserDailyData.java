package com.hrs.dataserver.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.hrs.dataserver.component.UpdatedData;

import com.hrs.dataserver.tool.IdAdapter;

@Document
public class UserDailyData {
	@Id
	private String id;

	//index related
	private String env;
	
	private String hrsId;
	
	private String type;
	
	private long timestamp;
	

	//data related
	private Map<String, List<UpdatedData>> data;	
	
	public UserDailyData() {
	}
	
	public UserDailyData(String env, String hrsId, String type,
					     long ts, Map<String, List<UpdatedData>> updatedData) {
		try{
			this.id=IdAdapter.getUserIdByElements(env, hrsId, type, ts);
			this.hrsId = hrsId;
			this.env = env;
			this.type = type;
			this.timestamp=ts;
			this.data = updatedData;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public UserDailyData(String env, String hrsId, String type, long ts){
		try{
			this.id=IdAdapter.getUserIdByElements(env, hrsId, type, ts);
			this.hrsId = hrsId;
			this.env = env;
			this.type = type;
			this.timestamp = ts;
			this.data=new HashMap<String, List<UpdatedData>>();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public UserDailyData(String env, String hrsId, String type, long ts, UpdatedData data){
		try{
			this.id=IdAdapter.getUserIdByElements(env, hrsId, type, ts);
			this.hrsId = hrsId;
			this.env = env;
			this.type = type;
			this.timestamp=ts;
			addToUpdatedData(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId() throws Exception{
		this.id=IdAdapter.getUserIdByElements(env, hrsId, type, timestamp);
	}

	public String getHrsId() {
		return hrsId;
	}

	public void setHrsId(String hrsId) {
		this.hrsId = hrsId;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Map<String, List<UpdatedData>> getUpdatedData() {
		return data;
	}

	public void setUpdatedData(Map<String, List<UpdatedData>> updatedData) {
		this.data = updatedData;
	}
	
	public void addToUpdatedData(UpdatedData newData){
		if(data==null){
			this.data=new HashMap<String, List<UpdatedData>>();
		}
		String dataName=newData.getName();
		if(data.containsKey(dataName)){
			List<UpdatedData> list=data.get(dataName);
			list.add(newData);
			this.data.put(dataName, list);
		}
		else{
			List<UpdatedData> list=new ArrayList<UpdatedData>();
			list.add(newData);
			this.data.put(dataName, list);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	//TODO:rewrite this function
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Id: "+this.id);
		return sb.toString();
	}
}
