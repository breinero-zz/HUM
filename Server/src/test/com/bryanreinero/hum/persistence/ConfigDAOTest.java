package com.bryanreinero.hum.persistence;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

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
		HttpServletRequest req = null;
		Executor visitor = null;
		try {
			visitor = new Executor(req);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		defaultTree.accept(visitor);
		Response response = visitor.getResponse();
		
		assertEquals ( ConfigDAO.defaultErrorCode, response.getResponseStatus());
	}
}
