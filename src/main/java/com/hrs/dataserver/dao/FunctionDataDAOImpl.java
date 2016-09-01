package com.hrs.dataserver.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import com.hrs.dataserver.entity.FunctionData;
import com.mongodb.Function;

public class FunctionDataDAOImpl implements CustomFunctionDataDAO{
	private MongoOperations operations;
	
	@Autowired
	public FunctionDataDAOImpl(MongoOperations op){
		Assert.notNull(op, "MongoOperations must not be null!");
		this.operations=op;
	}	
}
