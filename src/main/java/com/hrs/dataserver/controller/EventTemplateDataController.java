package com.hrs.dataserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.dataserver.entity.EventTemplateData;
import com.hrs.dataserver.service.EventTemplateDataService;


@RestController
@RequestMapping("/templates")
public class EventTemplateDataController {
	@Autowired
	EventTemplateDataService eventTemplateDataService;
	
	@RequestMapping(value={""}, method=RequestMethod.POST)
	public boolean createTemplateData(@RequestBody(required=false) EventTemplateData etd){
		return eventTemplateDataService.CreateEventTemplateDataService(etd);
	}
	
	@RequestMapping(value={"names/{name}"}, method=RequestMethod.GET)
	public EventTemplateData findEventTemplateDataByName(@PathVariable("name") String name) {
		return eventTemplateDataService.findEventTemplateDataByName(name);
	}
	
	@RequestMapping(value={"names/{name}"}, method=RequestMethod.DELETE)
	public boolean deleteEventTemplateDataByName(@PathVariable("name") String name){
		return eventTemplateDataService.deleteEventTemplateDataByName(name);
	}
	
	
}
