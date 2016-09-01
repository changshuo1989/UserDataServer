package com.hrs.dataserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.service.FunctionDataService;


@RestController
@RequestMapping("/functions")
public class FunctionDataController {
	
	@Autowired
	FunctionDataService functionDataService;
	
	@RequestMapping(value={""}, method=RequestMethod.POST)
	public boolean createFunctionData(@RequestBody(required=false) FunctionData functionData){
		return functionDataService.CreateFunctionData(functionData);
	}
	
	@RequestMapping(value={""}, method=RequestMethod.GET)
	public List<String> findAllFunctionNames(){
		return functionDataService.findFunctionList();
	}
	
	@RequestMapping(value={"{name}"}, method=RequestMethod.GET)
	public FunctionData findFunctionByName(@PathVariable("name") String name){
		return functionDataService.findFunctionByName(name);
	}
}
