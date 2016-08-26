package com.hrs.dataserver.filter;

public class SessionFilter {
	String start;
	String end;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("start: "+start);
		sb.append("end: "+end);
		return sb.toString();
	}
	
	
}
