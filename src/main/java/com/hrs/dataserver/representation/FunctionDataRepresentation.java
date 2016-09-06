package com.hrs.dataserver.representation;

import java.util.List;

public class FunctionDataRepresentation {
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
}
