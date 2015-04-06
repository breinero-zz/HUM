package com.bryanreinero.hum.persistence;

import com.mongodb.*;
import java.util.regex.*;

public class DataService {
	
	private Mongo mongo;
	private static final Pattern pattern = Pattern.compile("(\\w*)\\.(\\w*)");
	
	public DataService(Mongo mongo) {
		this.mongo = mongo;
	}
	
	public DBCollection getDataStore(String namespace){
		Matcher match = pattern.matcher(namespace);	
		if(match.matches()){
			String database = match.group(1);
			
			DB db = mongo.getDB(database);
			if(db == null)
				return null;
			
			if(match.groupCount() > 1)
				return db.getCollection( match.group(2));	
		}
		return null;
	}

}
