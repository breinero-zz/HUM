package com.bryanreinero.hum.element.json.test;

import java.net.UnknownHostException;
import java.util.Map;

import com.bryanreinero.hum.server.DAO;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TestDAO implements DAO {
	
	private MongoClient mongo;
	private DBCollection collection;
	
	public TestDAO () throws UnknownHostException {
		mongo = new MongoClient();
		collection = mongo.getDB("test").getCollection("test");
	}

	@Override
	public Object execute(Map<String, Object> request) {
		return collection.insert( (DBObject)request );
	}

}
