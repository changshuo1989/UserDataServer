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
	
	private String type;
	
	private String hrsId;
	
	private long timestamp;
	

	//data related
	//private Map<String, List<UpdatedData>> data;	
	private Map<String, List<Map<String, String>>> data;
	
	
	public UserDailyData() {
		
	}
	
	public UserDailyData(String env, String hrsId, String type,
					     long ts, Map<String, List<Map<String, String>>> updatedData) {
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
			this.data=new HashMap<String, List<Map<String, String>>>();
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
			addUpdatedData(data);
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


	public Map<String, List<Map<String, String>>> getUpdatedData() {
		return data;
	}

	public void setUpdatedData(Map<String, List<Map<String, String>>> updatedData) {
		this.data = updatedData;
	}
	
	
	
	//private Map<String, List<UpdatedData>> data;	
	//private Map<String, List<Map<String, String>>> data;
	/*
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
	 */
	
	//private Map<String, List<Map<String, String>>> data;
	public void addUpdatedData(UpdatedData updatedData){
		if(data==null){
			this.data=new HashMap<String, List<Map<String,String>>>();
		}
		String metricName=updatedData.getName();
		Map<String, String> map=updatedData.getValue();
		if(data.containsKey(metricName)){
			boolean isDuplicate=true;
			List<Map<String, String>> list=data.get(metricName);
			for(int i=0; i<list.size(); i++){
				Map<String, String> valueMap=list.get(i);
				for(String key : valueMap.keySet()){
					if(map.containsKey(key)){
						if(map.get(key).equals(valueMap.get(key))){
							continue;
						}
						else{
							isDuplicate=false;
							break;
						}
					}
					else{
						isDuplicate=false;
						break;
					}
				}
				if(!isDuplicate){
					break;
				}
			}
			if(!isDuplicate){
				list.add(map);
				this.data.put(metricName, list);
			}
		}
		else{
			List<Map<String, String>> list=new ArrayList<Map<String,String>>();
			list.add(map);
			this.data.put(metricName, list);
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
