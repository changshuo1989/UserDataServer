package com.hrs.dataserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.util.Assert;

import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.representation.IFunctionDataRepresentation;
import com.hrs.dataserver.tool.IdAdapter;


public class FunctionDataDAOImpl implements CustomFunctionDataDAO{
	private MongoOperations operations;
	
	@Autowired
	public FunctionDataDAOImpl(MongoOperations op){
		Assert.notNull(op, "MongoOperations must not be null!");
		this.operations=op;
	}
	
	
	private FunctionData findFunctionDataById(String id){
		FunctionData functionData=null;
		try{
			Query query=new Query();
			query.addCriteria(Criteria.where("_id").is(id));
			functionData=operations.findOne(query, FunctionData.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return functionData;
	}
	
	
	
	private Object toUserMongoScript(IFunctionDataRepresentation functionDataRep){
		Object obj=null;
		try{
			
			String collectionName=operations.getCollectionName(functionDataRep.getEntityClass());
			ScriptOperations scriptOps=operations.scriptOps();
			
			String scriptStr=functionDataRep.getMongoScript(collectionName);
			ExecutableMongoScript script=new ExecutableMongoScript(scriptStr);
			obj=scriptOps.execute(script);
			//System.out.println("Object:"+obj.toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	
	
	public FunctionData findFunctionData(String layer, String name){
		FunctionData functionData=null;
		try{
			String id=IdAdapter.getFunctionIdByElements(layer, name);
			functionData=findFunctionDataById(id);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return functionData;
	}
	
	//TODO: FINISH TEST!
	public Object executeFunction(IFunctionDataRepresentation functionDataRep){
		Object obj=null;
		try{
			//FunctionData functionData=getFunctionData(functionDataRep.getFunctionName());
			String id=IdAdapter.getFunctionIdByElements(functionDataRep.getFunctionLayer(), functionDataRep.getFunctionName());
			FunctionData functionData=findFunctionDataById(id);
			if(functionData!=null && functionData.getFunction()!=null){
				ScriptOperations scriptOps=operations.scriptOps();
				ExecutableMongoScript script=new ExecutableMongoScript(functionData.getFunction());
				Object para=toUserMongoScript(functionDataRep);
				obj=scriptOps.execute(script, para);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}			
		return obj;
	}
}
