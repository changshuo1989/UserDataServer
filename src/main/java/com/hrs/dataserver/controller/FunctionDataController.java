package com.hrs.dataserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.representation.UserFunctionDataRepresentation;
import com.hrs.dataserver.service.FunctionDataService;


@RestController
@RequestMapping("/functions")
public class FunctionDataController {
	
	@Autowired
	FunctionDataService functionDataService;
	
	
	//TODO:Maybe create a representation to hold input data
	@RequestMapping(value={""}, method=RequestMethod.POST)
	public boolean createFunctionData(@RequestBody(required=false) FunctionData functionData){
		return functionDataService.CreateFunctionData(functionData);
	}
	
	@RequestMapping(value={""}, method=RequestMethod.GET)
	public List<String> findAllFunctionNames(){
		return functionDataService.findFunctionList();
	}
	
	@RequestMapping(value={"types/{type}/names/{name}"}, method=RequestMethod.GET)
	public FunctionData findFunctionByName(@PathVariable("type") String layer ,@PathVariable("name") String name){
		return functionDataService.findFunction(layer,name);
	}
	
	@RequestMapping(value={"types/{type}/names/{name}"}, method=RequestMethod.POST)
	public Object executeFunctionByName(@PathVariable("type") String layer, @PathVariable("name") String name,
			                            @RequestBody(required=false) UserFunctionDataRepresentation functionDataRep){
		return functionDataService.executeFunction(layer, name, functionDataRep);
	}
	
	@RequestMapping(value={"types/{type}/names/{name}"}, method=RequestMethod.DELETE)
	public boolean deleteFunctionByName(@PathVariable("type") String layer, @PathVariable("name") String name){
		return functionDataService.deleteFunction(layer ,name);
	}
}
