package com.hrs.dataserver.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrs.dataserver.component.UpdatedData;
import com.hrs.dataserver.dao.UserDailyDataDAO;
import com.hrs.dataserver.dto.UserDailyDataDTO;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.representation.UserDailyDataRepresentation;
import com.hrs.dataserver.tool.DateAdapter;
import com.hrs.dataserver.tool.IdAdapter;


public class UserDailyDataService {
	@Autowired
	UserDailyDataDAO patRepo;
	
	//TODO: following business logic
	
	
/*
 * 
 * 
 * 
 * private functions
 * 
 * 
 * 
 * 
 * */	
	private String getIdFromUserDailyDataRepresentation(UserDailyDataRepresentation userDailyDataRep){
		String id="";
		try{
			String env=userDailyDataRep.getEnv();
			String hrsId=userDailyDataRep.getUid();
			String type=userDailyDataRep.getUtype();
			//String date=DateAdapter.fromTimestampToDateString(userDailyDataRep.getTs());
			long ts=DateAdapter.fromTimestampToDateTimestamp(userDailyDataRep.getTs());
			id=IdAdapter.getUserIdByElements(env, hrsId, type, ts);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	private UpdatedData getUpdatedDataFromUserDailyDataRepresentation(UserDailyDataRepresentation userDailyDataRep){
		UpdatedData data=new UpdatedData();
		try{
			Map<String, String> map=userDailyDataRep.getData();
			data.setName(userDailyDataRep.getType());
			data.setTimestamp(userDailyDataRep.getTs());
			data.setValue(map);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	
	
/*
 * 
 * 
 * 
 * public functions
 * 
 * 
 * 
 * 
 * */	
	public boolean updateUserDailyData(UserDailyDataRepresentation userDailyDataRep){
		boolean isFinished=false;
		try{
			String env=userDailyDataRep.getEnv();
			String hrsId=userDailyDataRep.getUid();
			String type=userDailyDataRep.getUtype();
			long ts=DateAdapter.fromTimestampToDateTimestamp(userDailyDataRep.getTs());
			UpdatedData data=getUpdatedDataFromUserDailyDataRepresentation(userDailyDataRep);
			patRepo.updateData(env, hrsId, type, ts, data);
			isFinished=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return isFinished;
	}
	
	//TODO using dto instead of entity!!!!
	public List<UserDailyData> getSpecificUserData(String env, String type, String hrsId, String start, String end){

		List<UserDailyData> userDailyDataList=patRepo.findUserDataBySession(env, type, hrsId, start, end);
		return userDailyDataList;
		
		
	}
}