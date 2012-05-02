package com.bryanreinero.hum.profile;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.MongoException;

public class MongoDBDriverTest {
	
	// TODO: authentication
	
	private Mongo connection;
	private DB db;
	private DBCollection coll;
	
	public DBCollection getColl() {
		return coll;
	}

	public MongoDBDriverTest() throws UnknownHostException, MongoException{
		connection = new Mongo( "localhost" , 27017 );
		db = connection.getDB("HUM");
		coll = db.getCollection("ViewerProfile");
	}
	
	public void submitDBObject(BasicDBObject doc){
		coll.insert(doc);
	}

}
