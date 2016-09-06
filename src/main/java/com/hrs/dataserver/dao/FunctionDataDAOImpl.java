package com.hrs.dataserver.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.util.Assert;

import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.representation.FunctionDataRepresentation;
import com.hrs.dataserver.tool.DateAdapter;
import com.mongodb.Function;

public class FunctionDataDAOImpl implements CustomFunctionDataDAO{
	private MongoOperations operations;
	
	@Autowired
	public FunctionDataDAOImpl(MongoOperations op){
		Assert.notNull(op, "MongoOperations must not be null!");
		this.operations=op;
	}
	
	
	
	private FunctionData getFunctionData(String functionName){
		FunctionData functionData=null;
		try{
			Query query=new Query();
			query.addCriteria(Criteria.where("name").is(functionName));
			functionData=operations.findOne(query, FunctionData.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return functionData;
	}
	
	
	private Object toMongoScript(FunctionDataRepresentation functionDataRep){
		StringBuilder sb=new StringBuilder();
		Object obj=null;
		try{
			String collectionName=operations.getCollectionName(UserDailyData.class);
			ScriptOperations scriptOps=operations.scriptOps();
			sb.append("function(){var doc=db."+collectionName+".find({");
			if(functionDataRep.getEnvironments()!=null && functionDataRep.getEnvironments().size()!=0){
					sb.append("env:{$in:[");
					for(int i=0; i<functionDataRep.getEnvironments().size(); i++){
						sb.append("'"+functionDataRep.getEnvironments().get(i)+"',");
					}
					sb.append("]},");
			}
			if(functionDataRep.getUserTypes()!=null && functionDataRep.getUserTypes().size()!=0){
					sb.append("type:{$in:[");
					for(int i=0; i<functionDataRep.getUserTypes().size(); i++){
						sb.append("'"+functionDataRep.getUserTypes().get(i)+"',");
					}
					sb.append("]},");
			}
			if(functionDataRep.getStartDate()!=null && functionDataRep.getEndDate()!=null){
				long startTimestamp=DateAdapter.fromStringToDate(functionDataRep.getStartDate()).getTime();
				long endTimestamp=DateAdapter.fromStringToDate(functionDataRep.getEndDate()).getTime();
				sb.append("timestamp:{$gte:"+startTimestamp+",$lte:"+endTimestamp+"},");
			}
			else if(functionDataRep.getStartDate()!=null && functionDataRep.getEndDate()==null){
				long startTimestamp=DateAdapter.fromStringToDate(functionDataRep.getStartDate()).getTime();
				sb.append("timestamp:{$gte:"+startTimestamp+"},");
			}
			else if(functionDataRep.getStartDate()==null && functionDataRep.getEndDate()!=null){
				long endTimestamp=DateAdapter.fromStringToDate(functionDataRep.getEndDate()).getTime();
				sb.append("timestamp:{$lte:"+endTimestamp+"},");
			}
			
			sb.append("}).toArray(); return doc;");
			System.out.println(sb.toString());
			ExecutableMongoScript script=new ExecutableMongoScript(sb.toString());
			obj=scriptOps.execute(script);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return obj;
	}
	
	
	//TODO: FINISH TEST!
	public Object executeFunction(FunctionDataRepresentation functionDataRep){
		Object obj=null;
		try{
			FunctionData functionData=getFunctionData(functionDataRep.getFunctionName());
			if(functionData!=null && functionData.getFunction()!=null){
				ScriptOperations scriptOps=operations.scriptOps();
				ExecutableMongoScript script=new ExecutableMongoScript(functionData.getFunction());
				Object para=toMongoScript(functionDataRep);
				obj=scriptOps.execute(script, para);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		//ScriptOperations scriptOps=operations.scriptOps();
		//String collectionName=operations.getCollectionName(UserDailyData.class);
		//System.out.println(collectionName);
		//ExecutableMongoScript script=new ExecutableMongoScript("function(){var ret=db.userDailyData.find({env:'test1'}).toArray(); return ret;}");
		//obj=scriptOps.execute(script);
		//UserDailyData udd=(UserDailyData)obj;
		//System.out.println(obj);
		
		return obj;
	}
}
