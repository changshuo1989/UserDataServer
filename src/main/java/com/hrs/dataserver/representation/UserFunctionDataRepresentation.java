package com.hrs.dataserver.representation;

import java.util.List;

import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.tool.DateAdapter;
import com.hrs.dataserver.tool.LayerAdapter;

public class UserFunctionDataRepresentation implements IFunctionDataRepresentation{
	private String startDate;
	private String endDate;
	private List<String> userTypes;
	private List<String> environments;
	
	private String functionName;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<String> getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(List<String> userTypes) {
		this.userTypes = userTypes;
	}
	public List<String> getEnvironments() {
		return environments;
	}
	public void setEnvironments(List<String> environments) {
		this.environments = environments;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(this.getStartDate());
		sb.append(this.getEndDate());
		sb.append(this.getFunctionName());
		sb.append(this.getEnvironments());
		sb.append(this.getUserTypes());
		return sb.toString();
	}
	public String getFunctionLayer() {
		
		return LayerAdapter.USER_LAYER;
	}
	
	
	public String getMongoScript(String collectionName) {
		StringBuilder sb=new StringBuilder();
		try{
			sb.append("function(){var doc=db."+collectionName+".find({");
			if(this.getEnvironments()!=null && this.getEnvironments().size()!=0){
					sb.append("env:{$in:[");
					for(int i=0; i<this.getEnvironments().size(); i++){
						sb.append("'"+this.getEnvironments().get(i)+"',");
					}
					sb.append("]},");
			}
			if(this.getUserTypes()!=null && this.getUserTypes().size()!=0){
					sb.append("type:{$in:[");
					for(int i=0; i<this.getUserTypes().size(); i++){
						sb.append("'"+this.getUserTypes().get(i)+"',");
					}
					sb.append("]},");
			}
			
			if(this.getStartDate()!=null && this.getEndDate()!=null){
				long startTimestamp=DateAdapter.fromStringToDate(this.getStartDate()).getTime();
				long endTimestamp=DateAdapter.fromStringToDate(this.getEndDate()).getTime();
				sb.append("timestamp:{$gte:"+startTimestamp+",$lte:"+endTimestamp+"},");
			}
			else if(this.getStartDate()!=null && this.getEndDate()==null){
				long startTimestamp=DateAdapter.fromStringToDate(this.getStartDate()).getTime();
				sb.append("timestamp:{$gte:"+startTimestamp+"},");
			}
			else if(this.getStartDate()==null && this.getEndDate()!=null){
				long endTimestamp=DateAdapter.fromStringToDate(this.getEndDate()).getTime();
				sb.append("timestamp:{$lte:"+endTimestamp+"},");
			}
			sb.append("}).toArray(); return doc;}");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	public Class<?> getEntityClass() {
		
		return UserDailyData.class; 
	}
}
