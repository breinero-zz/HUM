package com.bryanreinero.hum.persistence;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.element.Value;
import com.bryanreinero.hum.element.http.ResponseCode;
import com.bryanreinero.hum.element.http.ResponseHeader;
import com.bryanreinero.hum.element.Literal;
import com.bryanreinero.hum.server.DataAccessObject;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ConfigDAO implements DataAccessObject<String, DecisionTree> {

	private Datastore ds;
	private Morphia morphia = new Morphia();
	private Deserializer<String, DecisionTree> deserializer;
	
	// this is tree is returned when the requested match in not found
	private static final DecisionTree defaultTree = new DecisionTree ();
	
	public static final int defaultErrorCode = 404;
	public static final String defaultContentType = "text";
	
	// initialize the defaultTree
	static {
		defaultTree.setTimeToLive(600);

		Literal literal = new Literal();
		Name headerName = new Name();
		literal.setValue("Content-Type");
		headerName.addChild(literal);
		
		literal = new Literal();
		literal.setValue("text");
		Value headerValue = new Value();
		headerValue.addChild(literal);
		
		ResponseCode notFoundHeader = new ResponseCode();
		literal = new Literal();
		literal.setValue("404");
		notFoundHeader.addChild(literal);
		
		ResponseHeader contentType = new ResponseHeader();
		contentType.addChild(headerName);
		contentType.addChild(headerValue);
		defaultTree.addChild(contentType);
		defaultTree.addChild(notFoundHeader);
	}
	
	public Deserializer<String, DecisionTree> getDeserializer() {
		return deserializer;
	}

	public void setDeserializer(Deserializer<String, DecisionTree> deserializer) {
		this.deserializer = deserializer;
	}

	public ConfigDAO(Mongo connection, String collection) throws MongoException {
		ds = morphia.createDatastore(connection, collection);
		morphia.map(ConfigurationTree.class);
	}

	@Override
	public DecisionTree get(String key) {
		ConfigurationTree config = ds
				.find(ConfigurationTree.class, "name", key).get();

		if (config == null)
			return defaultTree;

		return deserializer.deserialize(config.getValue());
	}

	@Override
	public void persist(DecisionTree object) {
		ds.save(object);
	}
}
