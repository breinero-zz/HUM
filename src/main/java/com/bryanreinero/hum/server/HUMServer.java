package com.bryanreinero.hum.server;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.persistence.ConfigDAO;
import com.mongodb.MongoException;

public class HUMServer extends HttpServlet {

	private static final long serialVersionUID = -6170830231570604200L;
	public static ConfigurationDAO dao;
	private static final String rootTreeId = "root";

	private static final Logger logger = LogManager.getLogger( HUMServer.class.getName() );

	public void init(ServletConfig config) {

		try {
			
			dao = new ConfigDAO(
					"localhost",
					new XMLParser()
				);

		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (MongoException e) {
			logger.error(e.getMessage());
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse resp) {
		Executor executor = null;
		try {
			executor = new Executor(req, dao);
			dao.get(rootTreeId).accept(executor);
		}
		catch( Exception e ) {
			logger.warn( e.getMessage() );
			
			// TODO: set executor to static 404 error
		}
		
		try {
			Responder.respond(resp, executor.getResponse());
		} catch (IOException ioe) {
			logger.error(ioe);
		}
	}
}
