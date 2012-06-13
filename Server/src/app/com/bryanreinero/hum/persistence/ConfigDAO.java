package com.bryanreinero.hum.persistence;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.server.DataAccessObject;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ConfigDAO<K, E> implements DataAccessObject<K, E> {

	private Datastore ds;
	private Morphia morphia = new Morphia();
	private Deserializer<String, DecisionTree> deserializer;
	
	public Deserializer<String, DecisionTree> getDeserializer() {
		return deserializer;
	}

	public void setDeserializer(Deserializer<String, DecisionTree> deserializer) {
		this.deserializer = deserializer;
	}

	public ConfigDAO(Mongo connection, String collection) {
		try {
			ds = morphia.createDatastore(connection, collection);
			morphia.map(ConfigurationTree.class);
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(K key) {
		ConfigurationTree config = ds.find(ConfigurationTree.class, "name", key).get();
		return (E)deserializer.deserialize(config.getValue());
	}

	@Override
	public void persist(E object) {
		ds.save(object);
	}

}
