package com.hrs.dataserver.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import com.hrs.dataserver.component.UpdatedData;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.tool.DateAdapter;
import com.hrs.dataserver.tool.IdAdapter;


public class UserDailyDataDAOImpl implements CustomUserDailyDataDAO {
	
	private MongoOperations operations;
	
	@Autowired
	public UserDailyDataDAOImpl(MongoOperations op){
		Assert.notNull(op, "MongoOperations must not be null!");
		this.operations=op;
	}
	
	//USER LEVEL
	public UserDailyData findUserDailyDataById(String id) {
		UserDailyData userDailyData=null;
		try{
			Query query=new Query();
			query.addCriteria(Criteria.where("_id").is(id));
			userDailyData=operations.findOne(query, UserDailyData.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userDailyData;
	}
	
	
	
	public boolean updateData(String env, String hrsId, String type, long ts, UpdatedData data) {
		boolean isFinished=false;
		try{
			String id=IdAdapter.getUserIdByElements(env, hrsId, type, ts);
			UserDailyData user=findUserDailyDataById(id);
			if(user!=null){
				user.addToUpdatedData(data);
				operations.save(user);
			}
			else{
				UserDailyData newUser=new UserDailyData(env, hrsId, type, ts, data);
				operations.save(newUser);
			}
			isFinished=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return isFinished;
	}

	public List<UserDailyData> findUserDataBySession(String env, String type, String hrsId, String start, 
													 String end) {
		
		
		List<UserDailyData> userDailyDataList=null;
		long startTime=0;
		long endTime=0;
		
		try{
			if(start!=null && start.length()!=0){
				startTime=DateAdapter.fromStringToDate(start).getTime();
			}
			
			if(end!=null && end.length()!=0){
				endTime=DateAdapter.fromStringToDate(end).getTime();
			}
			Query query=new Query();
			query.addCriteria(Criteria.where("env").is(env));
			query.addCriteria(Criteria.where("hrsId").is(hrsId));
			query.addCriteria(Criteria.where("type").is(type));

			if(startTime!=0 && endTime!=0){
				query.addCriteria(Criteria.where("timestamp").gte(startTime).lte(endTime));
			}
			else if(startTime==0 && endTime!=0){
				query.addCriteria(Criteria.where("timestamp").lte(endTime));
			}
			else if(startTime!=0 && endTime==0){
				query.addCriteria(Criteria.where("timestamp").gte(startTime));
			}
			
			userDailyDataList=operations.find(query, UserDailyData.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userDailyDataList;
	}



	
	
	//ENVIRONMENT LEVEL
	
	//TOTAL LEVEL

}
