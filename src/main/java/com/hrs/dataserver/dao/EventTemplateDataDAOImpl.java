package com.hrs.dataserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import com.hrs.dataserver.entity.EventTemplateData;

public class EventTemplateDataDAOImpl implements CustomEventTemplateDataDAO {
	private MongoOperations operations;
	
	@Autowired
	public EventTemplateDataDAOImpl(MongoOperations op){
		Assert.notNull(op, "MongoOperations must not be null!");
		this.operations=op;
	}
	
	
	private EventTemplateData findEventTemplateDataByName(String name){
		EventTemplateData etd=null;
		
		try{
			Query query=new Query();
			query.addCriteria(Criteria.where("_id").is(name));
			etd=operations.findOne(query, EventTemplateData.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return etd;
	}
}
