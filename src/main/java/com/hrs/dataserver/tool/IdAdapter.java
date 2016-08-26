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
		String res=env+"_"+hrsId+"_"+type+"_"+timestamp;
		return res;
	}
}
