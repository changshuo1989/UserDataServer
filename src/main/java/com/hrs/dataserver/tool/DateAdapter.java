package com.hrs.dataserver.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter {
	private static final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date fromStringToDate(String dateStr){
		Date date=null;
		try {
			date=dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String fromDateToString(Date date){
		String dateStr=dateFormat.format(date);
		return dateStr;
	}
	
	
	public static Date fromStringToDateTime(String dateStr){
		Date date=null;
		try{
			date=dateTimeFormat.parse(dateStr);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	
	public static String fromDateTimeToString(Date date){
		String dateTimeStr=dateTimeFormat.format(date);
		return dateTimeStr;
	}
	
	public static String fromTimestampToDateString(long ts){
		Date date=new Date(ts);
		return fromDateToString(date);
	}
	
	public static String fromTimestampToDateTimeString(long ts){
		Date date=new Date(ts);
		return fromDateTimeToString(date);
	}
	
	public static long fromTimestampToDateTimestamp(long ts){
		
		String dateStr=DateAdapter.fromTimestampToDateString(ts);
		return fromStringToDate(dateStr).getTime();
	}
}
