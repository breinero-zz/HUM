package com.bryanreinero.hum.persistence;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import com.bryanreinero.hum.persistence.ConfigDAO;
import com.bryanreinero.hum.server.Executor;
import com.bryanreinero.hum.server.Response;
import com.bryanreinero.hum.element.DecisionTree;

public class ConfigDAOTest {	
	
	ConfigDAO treestore = new ConfigDAO(null, "configurations");
	
	@Test(groups = { "basic" })
	public ConfigDAOTest () {
		DecisionTree defaultTree = treestore.get("unknown");

		assertNotNull(defaultTree);
		
		Executor visitor = new Executor(null);
		defaultTree.accept(visitor);
		Response response = visitor.getResponse();
		
		assertEquals ( ConfigDAO.defaultErrorCode, response.getResponseStatus());
	}
}
