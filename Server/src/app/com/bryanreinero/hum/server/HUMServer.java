package com.bryanreinero.hum.server;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryanreinero.hum.DataStore.ConfigDAO;
import com.bryanreinero.hum.DataStore.HUMElementDS;
import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.visitor.PrintVisitor;


public class HUMServer extends HttpServlet {

	private static final long serialVersionUID = -6170830231570604200L;
	public static DataAccessObject<String, DecisionTree> store; 

	
	public void init(ServletConfig config){
			store = new HUMElementDS<String, DecisionTree>();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		Executor executor = new Executor(req);
		store.get("root").accept(executor);
		
		try{
			Responder.respond(resp, executor.getResponse());
		}catch(IOException ioe){
			//TODO: real exception handling needs to happen here
			ioe.printStackTrace();
		}
	}
}
