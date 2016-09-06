package com.hrs.dataserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrs.dataserver.dao.FunctionDataDAO;
import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.representation.FunctionDataRepresentation;

public class FunctionDataService {
	@Autowired
	FunctionDataDAO functionRepo;
	
	public FunctionData findFunctionByName(String name){
		FunctionData functionData=functionRepo.findOne(name);
		return functionData;
	}
	
	public List<String> findFunctionList(){
		List<FunctionData> fList = functionRepo.findAll();
		List<String> list=new ArrayList<String>();
		for(FunctionData fData: fList){
			list.add(fData.getName());
		}
		return list;
	}
	
	public boolean CreateFunctionData(FunctionData functionData){
		boolean isFinished=false;
		try{
			functionRepo.save(functionData);
			isFinished=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFinished;
	}
	
	public Object executeFunction(String functionName, FunctionDataRepresentation functionDataRep){
		if(functionName.equals(functionDataRep.getFunctionName())){
			return functionRepo.executeFunction(functionDataRep);
		}
		return null;
	}
}
