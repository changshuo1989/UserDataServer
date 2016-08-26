package com.hrs.dataserver;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hrs.dataserver.component.UpdatedData;
import com.hrs.dataserver.dao.UserDailyDataDAO;
import com.hrs.dataserver.entity.UserDailyData;
import com.hrs.dataserver.tool.DateAdapter;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private UserDailyDataDAO repo;
	
	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	}
	
	public void run(String... arg0) throws Exception {
		repo.deleteAll();
		/*
		UpdatedData data1=new UpdatedData();
		data1.setName("activity");
		data1.setFtime(DateAdapter.fromDateTimeToString(new Date()));
		data1.addToValue("duration", "10");
		
		UpdatedData data2=new UpdatedData();
		data2.setName("activity");
		data2.setFtime(DateAdapter.fromDateTimeToString(new Date()));
		data2.addToValue("duration", "20");
		
		UpdatedData data3=new UpdatedData();
		data3.setName("bloodpressure");
		data3.setFtime(DateAdapter.fromDateTimeToString(new Date()));
		data3.addToValue("sys", "20");
		data3.addToValue("dia", "30");
		data3.addToValue("map", "40");
		
		UpdatedData data4=new UpdatedData();
		data4.setName("activity");
		data4.setFtime(DateAdapter.fromDateTimeToString(new Date()));
		data4.addToValue("duration", "60");
		
		
		UserDailyData user1=new UserDailyData("env1", "hrs001", "patient", "2016-08-25");
		user1.addToUpdatedData(data1);
		user1.addToUpdatedData(data2);
		user1.addToUpdatedData(data3);

		repo.save(user1);
		
		
		UserDailyData user2=new UserDailyData("env2", "hrs002", "patient", "2016-08-25");
		user2.addToUpdatedData(data1);
		user2.addToUpdatedData(data2);
		user2.addToUpdatedData(data3);
	
		repo.save(user2);
		
		repo.updateData("env2", "hrs002", "patient", "2016-08-25", data4);
		
		System.out.println("this moment: "+new Date().getTime());
		*/
	}
	
}
