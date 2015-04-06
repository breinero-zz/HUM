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

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.persistence.ConfigDAO;
import com.bryanreinero.hum.persistence.DataService;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class HUMServer extends HttpServlet {

	private static final String MONGODB_CONNECTION_PARAMETER = "mongodb";
	private static final String HUM_CONFIGURATION_MONGO_NAMESPACE_PARAMETER = "configuration_namespace";

	private static final long serialVersionUID = -6170830231570604200L;
	public static DataAccessObject<String, DecisionTree> store;
	private static final String rootTreeId = "root";

	private static DataService dataServices;

	private static final Logger logger = LogManager.getLogger( HUMServer.class.getName() );

	public void init(ServletConfig config) {

		try {

			MongoClient mongo = new MongoClient(
					config.getInitParameter(MONGODB_CONNECTION_PARAMETER));
			dataServices = new DataService(mongo);
			ConfigDAO dao = new ConfigDAO(
					mongo,
					config.getInitParameter(HUM_CONFIGURATION_MONGO_NAMESPACE_PARAMETER));
			dao.setDeserializer(new XMLParser());
			store = dao;

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
			executor = new Executor(req);
			store.get(rootTreeId).accept(executor);
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

	public static DBCollection getDataService(String namespace) {
		return dataServices.getDataStore(namespace);
	}
}
