package com.hrs.dataserver.tool;


//TODO: add more layers here
public class LayerAdapter {
	public static String USER_LAYER="user";
	public static String NONE="none";
	
	
	public static String getLayer(String input){
		if(input.equalsIgnoreCase("user") || input.startsWith("user")){
			return USER_LAYER;
		}
		
		else{
			return "TBD";
		}
	}
}
