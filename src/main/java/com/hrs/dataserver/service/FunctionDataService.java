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
		List<String> list=new ArrayList<String>();
		try{
			List<FunctionData> fList = functionRepo.findAll();
			for(FunctionData fData: fList){
				list.add(fData.getName());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean CreateFunctionData(FunctionData functionData){
		boolean isFinished=false;
		try{
			//make sure we have function name
			if(functionData.getName()!=null && functionData.getName().length()!=0){
				functionRepo.save(functionData);
				isFinished=true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFinished;
	}
	
	public Object executeFunction(String functionName, FunctionDataRepresentation functionDataRep){
		try{
			if(functionName.equals(functionDataRep.getFunctionName())){
				return functionRepo.executeFunction(functionDataRep);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public boolean deleteFunction(String functionName){
		boolean isFinished=false;
		try{
			functionRepo.delete(functionName);
			isFinished=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFinished;
	}
}
