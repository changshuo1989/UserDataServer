package com.hrs.dataserver.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrs.dataserver.component.UpdatedData;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.tool.DateAdapter;

public class UserDailyDataDtoFactory {
	
	//TODO: finish entity dto transaction
	public UserDailyDataDTO createUserDailyDataDto(UserDailyData userDailyData){
		if(userDailyData==null){
			return null;
		}
		UserDailyDataDTO userDailyDataDto=new UserDailyDataDTO();
		
		userDailyDataDto.setEnv(userDailyData.getEnv());
		userDailyDataDto.setHrsId(userDailyData.getHrsId());
		userDailyDataDto.setType(userDailyData.getType());
		userDailyDataDto.setDate(DateAdapter.fromTimestampToDateString(userDailyData.getTimestamp()));
		
		//data part
		userDailyDataDto.setData(userDailyData.getUpdatedData());
		
		/*
		Map<String, List<UpdatedData>>userDaoDataMap=userDailyData.getUpdatedData();
		
		
		for(String key: userDaoDataMap.keySet()){
			String dtoKey=key;
			List<Map<String, String>> dtoDataList=new ArrayList<Map<String,String>>(); 
			List<UpdatedData> daoDataList=userDaoDataMap.get(key);
			for(int i=0; i<daoDataList.size(); i++){
				UpdatedData daoData=daoDataList.get(i);
				Map<String, String> dtoData=new HashMap<String, String>();
				daoData.setName(key);
				daoData.getValue();
				Map<String, String> values=daoData.getValue();
				for(String vKey : values.keySet()){
					dtoData.put(vKey, values.get(vKey));
				}
				dtoDataList.add(dtoData);
			}
			userDtoDataMap.put(dtoKey, dtoDataList);
			
			userDailyDataDto.setData(userDtoDataMap);
		}
		*/
		return userDailyDataDto;
	}
	
}
