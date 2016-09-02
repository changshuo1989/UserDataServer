package com.hrs.dataserver.dao;

import java.util.List;

import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.representation.FunctionDataRepresentation;

public interface CustomFunctionDataDAO {
	
	public Object executeFunction(FunctionDataRepresentation functionDataRep);
}
