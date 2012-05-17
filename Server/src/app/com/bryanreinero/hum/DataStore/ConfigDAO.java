package com.bryanreinero.hum.DataStore;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.server.DataAccessObject;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ConfigDAO<K, E> implements DataAccessObject<K, E> {

	private Mongo connection;
	Datastore ds;
	Morphia morphia = new Morphia();
	
	public ConfigDAO() {
		try {
			connection = new Mongo( "localhost" , 27017 );
			ds = morphia.createDatastore(connection, "configurations");
			morphia.map(DecisionTree.class);
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
		return (E)ds.find(DecisionTree.class).field("name").equal(key).get();
	}

	@Override
	public void persist(E object) {
		ds.save(object);
	}

}
