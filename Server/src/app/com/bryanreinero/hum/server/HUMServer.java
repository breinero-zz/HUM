package com.bryanreinero.hum.server;

import java.io.IOException;
import java.net.UnknownHostException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryanreinero.hum.profile.MongoDBDriverTest;
import com.bryanreinero.hum.treeStore.TreeStore;
import com.mongodb.MongoException;


public class HUMServer extends HttpServlet {

	private static final long serialVersionUID = -6170830231570604200L;
	public static TreeStore store = TreeStore.getInstance();
	public static MongoDBDriverTest connection = null;
	
	public void init(ServletConfig config){
		try {
			connection = new MongoDBDriverTest();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		Executor executor = new Executor(req);
		store.getTree("root").accept(executor);
		
		try{
			Responder.respond(resp, executor.getResponse());
		}catch(IOException ioe){
			//TODO: real exception handling needs to happen here
			ioe.printStackTrace();
		}
	}
}
