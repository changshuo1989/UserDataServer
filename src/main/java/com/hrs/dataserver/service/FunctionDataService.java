package com.hrs.dataserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hrs.dataserver.dao.FunctionDataDAO;
import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.representation.UserFunctionDataRepresentation;
import com.hrs.dataserver.tool.IdAdapter;
import com.hrs.dataserver.tool.LayerAdapter;

public class FunctionDataService {
	@Autowired
	FunctionDataDAO functionRepo;

	
	
	public FunctionData findFunction(String layer, String name){
		FunctionData functionData=functionRepo.findFunctionData(layer, name);
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
			//make sure we have function name and layers
			if(functionData.getName()!=null && functionData.getName().length()!=0 && 
			   functionData.getLayer()!=null && functionData.getLayer().length()!=0 &&
			   functionData.getFunction()!=null && functionData.getFunction().length()!=0){
				
				String layerInput=functionData.getLayer();
				functionData.setLayer(LayerAdapter.getLayer(layerInput));
				functionRepo.save(functionData);
				isFinished=true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFinished;
	}
	
	public Object executeFunction(String functionName, UserFunctionDataRepresentation functionDataRep){
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
