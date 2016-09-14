package com.hrs.dataserver.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.hrs.dataserver.component.UpdatedData;
import com.hrs.dataserver.dao.UserDailyDataDAO;
import com.hrs.dataserver.dto.UserDailyDataDTO;
import com.hrs.dataserver.entity.EventTemplateData;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.representation.UserDailyDataRepresentation;
import com.hrs.dataserver.tool.DateAdapter;
import com.hrs.dataserver.tool.IdAdapter;


public class UserDailyDataService {
	@Autowired
	UserDailyDataDAO patRepo;
	
	@Autowired
	EventTemplateDataService eventTemplateDataService;


	
	
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
	
	private boolean compareDatWithTemplate(EventTemplateData etd, UserDailyDataRepresentation userDailyDataRep){
		boolean valid = false;
		ProcessingReport report = null;
		ObjectMapper mapper = new ObjectMapper();
		try{
			String jsonSchema=etd.getSchema();
			String jsonStr=mapper.writeValueAsString(etd);
			
			JsonNode schemaNode = JsonLoader.fromString(jsonSchema);
			JsonNode data = JsonLoader.fromString(jsonStr);
			
			JsonSchemaFactory factory = JsonSchemaFactory.byDefault(); 
			JsonSchema schema = factory.getJsonSchema(schemaNode);
			report=schema.validate(data);
			valid=report.isSuccess();
			if(valid){
				//TODO: doing operations
				
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	private boolean compareDataWithTemplates(List<EventTemplateData> etdList, UserDailyDataRepresentation userDailyDataRep){
		if(etdList==null){
			return false;
		}
		//TODO test
		for(int i=0; i<etdList.size(); i++){
			EventTemplateData etd=etdList.get(i);
			if(compareDatWithTemplate(etd, userDailyDataRep)){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkEventTemplates(UserDailyDataRepresentation userDailyDataRep){
		List<EventTemplateData> etdList=eventTemplateDataService.getEventTemplates();
		
		return compareDataWithTemplates(etdList, userDailyDataRep);
	}
	
	
	
	private String getIdFromUserDailyDataRepresentation(UserDailyDataRepresentation userDailyDataRep){
		String id="";
		try{
			String env=userDailyDataRep.getEnv();
			String hrsId=userDailyDataRep.getUid();
			String type=userDailyDataRep.getUtype();
			//String date=DateAdapter.fromTimestampToDateString(userDailyDataRep.getTs());
			long ts=DateAdapter.fromTimestampToDateTimestamp(Long.parseLong(userDailyDataRep.getTs()));
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
			//data.setTimestamp(userDailyDataRep.getTs());
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
	
	public List<UserDailyData> getTotalUserData(String start, String end){
		List<UserDailyData> userDailyDataList=patRepo.findTotalUserDataBySession(start, end);
		return userDailyDataList;
	}
	
	
	public List<UserDailyData> getEnvironmentUserData(String env, String start, String end){
		List<UserDailyData> userDailyDataList=patRepo.findEnvironmentUserDataBySession(env, start, end);
		return userDailyDataList;
	}
	
	public boolean updateUserDailyData(UserDailyDataRepresentation userDailyDataRep){
		boolean isFinished=false;
		try{
			if(userDailyDataRep.getEnv()!=null && userDailyDataRep.getUid()!=null 
			   && userDailyDataRep.getUtype()!=null && userDailyDataRep.getTs()!=null){
				
				//check event template list
				boolean foundTemplate=checkEventTemplates(userDailyDataRep);
				
				String env=userDailyDataRep.getEnv();
				String hrsId=userDailyDataRep.getUid();
				String type=userDailyDataRep.getUtype();
				long ts=DateAdapter.fromTimestampToDateTimestamp(Long.parseLong(userDailyDataRep.getTs()));
				UpdatedData data=getUpdatedDataFromUserDailyDataRepresentation(userDailyDataRep);
				patRepo.updateData(env, hrsId, type, ts, data);
				isFinished=true;
			}
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