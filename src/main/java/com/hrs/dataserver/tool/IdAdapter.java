package com.hrs.dataserver.tool;

public class IdAdapter {
	
	public static String getUserIdByElements(String env, String hrsId, String type, long timestamp) throws Exception{
		if(hrsId==null || hrsId.length()==0){
			throw new Exception("Patient hrsId shouldn't be null or empty!");
		}
		if(env==null || env.length()==0){
			throw new Exception("env shouldn't be null or empty!");
		}
		if(type==null || type.length()==0){
			throw new Exception("type shouldn't be null or empty!");
		}
		if(timestamp<=0){
			throw new Exception("date shouldn't be null or empty!");
		}
		String res=env+"_"+type+"_"+hrsId+"_"+timestamp;
		return res;
	}
	
	public static String getFunctionIdByElements(String layer, String name) throws Exception{
		if(layer==null || layer.length()==0){
			throw new Exception("Function layer shouldn't be null or empty!");
		}
		if(name==null || name.length()==0){
			throw new Exception("Function name shouldn't be null or empty!");
		}
		String res=layer+"_"+name;
		return res;
	}
	
	
}
