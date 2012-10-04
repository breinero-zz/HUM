package com.bryanreinero.hum.server;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.persistence.ConfigDAO;
import com.bryanreinero.hum.persistence.DataService;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class HUMServer extends HttpServlet {

	private static final long serialVersionUID = -6170830231570604200L;
	public static DataAccessObject<String, DecisionTree> store; 
	private static final String rootTreeId = "root";
	
	private static DataService dataServices;
	
	public void init(ServletConfig config){
		try {
			Mongo mongo = new Mongo();
			dataServices = new DataService(mongo);
			ConfigDAO dao = new ConfigDAO (mongo, "configurations");
			dao.setDeserializer(new XMLParser());
			store = dao;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		Executor executor = new Executor(req);
		store.get(rootTreeId).accept(executor);
		
		try{
			Responder.respond(resp, executor.getResponse());
		}catch(IOException ioe){
			//TODO: real exception handling needs to happen here
			ioe.printStackTrace();
		}
	}
	
	public static DBCollection getDataService(String namespace){
		return dataServices.getDataStore(namespace);
	}
}
