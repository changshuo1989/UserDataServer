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
import com.hrs.dataserver.representation.FunctionDataRepresentation;
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
	
	
	//TODO: FINISH TEST!
	public Object executeFunction(FunctionDataRepresentation functionDataRep){
		Object obj=null;
		try{
			FunctionData functionData=getFunctionData(functionDataRep.getFunctionName());
			if(functionData!=null && functionData.getFunction()!=null){
				ScriptOperations scriptOps=operations.scriptOps();
				ExecutableMongoScript script=new ExecutableMongoScript(functionData.getFunction());
				obj=scriptOps.execute(script);
			}
			//ScriptOperations scriptOps=operations.scriptOps();
			//ExecutableMongoScript script=new ExecutableMongoScript("function(x){return x;}");
			//Object res=scriptOps.execute(script);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
}
