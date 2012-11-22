package com.bryanreinero.element.visitor;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testng.annotations.Test;

import com.bryanreinero.hum.server.Executor;
import com.bryanreinero.hum.server.Response;

import com.bryanreinero.hum.element.*;

import static org.testng.AssertJUnit.*;


public class VisitorTest {
	
	private Executor executor;
	
	public VisitorTest () {
		HttpServletRequest req = null;
		try {
			executor = new Executor( req );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(groups = { "basic" }) 
	public void initializationTest () {
		Response response  = executor.getResponse();
		assertNotNull(response);
		assertEquals(HttpServletResponse.SC_OK, response.getResponseStatus());

		Literal literal = new Literal();
		literal.setValue("test_variable");
		
		Name name = new Name();
		name.addChild( literal );
		
		SetVariable variable = new SetVariable();
		variable.addChild( name );
		
		
		literal.setValue("test_value");
		Value value = new Value();
		value.addChild(literal);
		variable.addChild( value );
		
		DecisionTree tree = new DecisionTree();
		tree.addChild( variable );
		
		tree.accept( executor );
	}

	@Test(groups = { "basic" }) 
	public void visitTest () {
		Response response  = executor.getResponse();
		assertNotNull(response);
	    assertEquals(HttpServletResponse.SC_OK, response.getResponseStatus());
	}
}
