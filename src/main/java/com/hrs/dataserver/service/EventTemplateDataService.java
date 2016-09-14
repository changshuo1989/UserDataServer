package com.hrs.dataserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrs.dataserver.dao.EventTemplateDataDAO;
import com.hrs.dataserver.entity.EventTemplateData;



public class EventTemplateDataService {
	@Autowired
	EventTemplateDataDAO eventTempRepo;
	
	
	
	public boolean CreateEventTemplateDataService(EventTemplateData etd){
		boolean isFinished=false;
		try{
			if(etd.getName()!=null && etd.getSchema()!=null && etd.getTimestamp()>0){
				eventTempRepo.save(etd);
			}
			isFinished=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFinished;
	}
	
	
	public EventTemplateData findEventTemplateDataByName(String name){
		EventTemplateData etd=null;
		try{
			etd=eventTempRepo.findOne(name);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return etd;
	}
	
	
	public boolean deleteEventTemplateDataByName(String name){
		boolean isFinished=false;
		try{
			eventTempRepo.delete(name);
			isFinished=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFinished;
	}
	
	
	public List<EventTemplateData> getEventTemplates(){
		List<EventTemplateData> list=null;
		try{
			list=eventTempRepo.findAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
