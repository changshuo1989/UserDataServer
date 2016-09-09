package com.hrs.dataserver.dao;

import java.util.List;

import com.hrs.dataserver.entity.FunctionData;
import com.hrs.dataserver.representation.IFunctionDataRepresentation;
import com.hrs.dataserver.representation.UserFunctionDataRepresentation;

public interface CustomFunctionDataDAO {
	
	public Object executeFunction(IFunctionDataRepresentation functionDataRep);
	
	public FunctionData findFunctionData(String layer, String name);
}
