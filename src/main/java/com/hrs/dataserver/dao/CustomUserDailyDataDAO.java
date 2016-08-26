package com.hrs.dataserver.dao;

import java.util.List;

import com.hrs.dataserver.component.UpdatedData;
import com.hrs.dataserver.entity.UserDailyData;

public interface CustomUserDailyDataDAO {
	//USER LEVEL
	public UserDailyData findUserDailyDataById(String id);
	
	public List<UserDailyData> findUserDataBySession(String env, String type, String hrsId, String start, String end);
	
	public boolean updateData(String env, String hrsId, String type, long ts, UpdatedData data);
	
	//ENVIRONMENT LEVEL
	
	
	//TOTAL LEVEL
}
