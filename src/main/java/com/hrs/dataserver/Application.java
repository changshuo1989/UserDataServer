package com.hrs.dataserver;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hrs.dataserver.dao.UserDailyDataDAO;


@SpringBootApplication
public class Application implements CommandLineRunner{
	
	//@Autowired
	//private UserDailyDataDAO repo;
	
	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	}
	
	public void run(String... arg0) throws Exception {
		//repo.deleteAll();
		//System.out.println(new Date().getTime());
	}
	
}
