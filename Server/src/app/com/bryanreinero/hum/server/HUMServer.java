package com.bryanreinero.hum.server;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.persistence.ConfigDAO;
import com.mongodb.MongoException;

public class HUMServer extends HttpServlet {

	private static final long serialVersionUID = -6170830231570604200L;
	public static DataAccessObject<String, DecisionTree> store; 
	
	public void init(ServletConfig config){
		ConfigDAO<String, DecisionTree> treestore = new ConfigDAO <String, DecisionTree>();
		try {
			treestore.setDeserializer(new XMLParser());
			store = treestore;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
