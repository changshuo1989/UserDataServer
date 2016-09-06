package com.hrs.dataserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.filter.SessionFilter;
import com.hrs.dataserver.representation.UserDailyDataRepresentation;
import com.hrs.dataserver.service.UserDailyDataService;

@RestController
@RequestMapping("/users")
public class UserDailyDataController {
	
	@Autowired
	UserDailyDataService userDailyDataService;
	
	@RequestMapping(value={"","environments"}, method=RequestMethod.GET)
	public List<UserDailyData> getTotalUserData(SessionFilter filter){
		return userDailyDataService.getTotalUserData(filter.getStart(),filter.getEnd());
	}
	
	@RequestMapping(value={"environments/{env}","environments/{env}/types"}, method=RequestMethod.GET)
	public List<UserDailyData> getEnvironmentUserData(@PathVariable("env") String env, SessionFilter filter){
		return userDailyDataService.getEnvironmentUserData(env, filter.getStart(), filter.getEnd());
	}
	
	@RequestMapping(value={"environments/{env}/types/{type}/hrsid/{hrsId}"}, method=RequestMethod.POST)
	public boolean updateUserDailyData(@RequestBody(required=false) UserDailyDataRepresentation userDailyDataRep){
		return userDailyDataService.updateUserDailyData(userDailyDataRep);
	}
	
	@RequestMapping(value={"environments/{env}/types/{type}/hrsid/{hrsId}"}, method=RequestMethod.GET)
	public List<UserDailyData> getUserData(@PathVariable("env") String env, @PathVariable("type") String type, @PathVariable("hrsId") String hrsId, SessionFilter filter){
		return userDailyDataService.getSpecificUserData(env, type, hrsId, filter.getStart(), filter.getEnd());
	}
}
