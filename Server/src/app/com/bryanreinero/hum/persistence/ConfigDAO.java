package com.bryanreinero.hum.persistence;

import java.net.UnknownHostException;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.server.DataAccessObject;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ConfigDAO<K, E> implements DataAccessObject<K, E> {

	private Mongo connection;
	private Datastore ds;
	private Morphia morphia = new Morphia();
	private Deserializer<String, DecisionTree> deserializer;
	
	public Deserializer<String, DecisionTree> getDeserializer() {
		return deserializer;
	}

	public void setDeserializer(Deserializer<String, DecisionTree> deserializer) {
		this.deserializer = deserializer;
	}

	public ConfigDAO() {
		try {
			connection = new Mongo( "localhost" , 27017 );
			ds = morphia.createDatastore(connection, "configurations");
			morphia.map(ConfigurationTree.class);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(K key) {
		ConfigurationTree config = ds.find(ConfigurationTree.class).field("name").equal(key).get();
		return (E)deserializer.deserialize(config.getValue());
	}

	@Override
	public void persist(E object) {
		ds.save(object);
	}

}
