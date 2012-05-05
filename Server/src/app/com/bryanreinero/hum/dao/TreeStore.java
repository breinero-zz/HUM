package com.bryanreinero.hum.dao;

import java.net.UnknownHostException;

import com.bryanreinero.hum.element.*;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
public class TreeStore {
	
	private static TreeStore instance = null;
	private Datastore ds = null;
	private DB db;
	private Morphia morphia;
	
	private TreeStore(){
		try {
			Mongo m = new Mongo();
			db = m.getDB( "test" );
			morphia = new Morphia();
			morphia.map(DecisionTree.class);
			ds = morphia.createDatastore(m, "test");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TreeStore getInstance(){
		if(instance == null)
			instance = new TreeStore();
		
		return instance;
	}
	
	public DecisionTree getTree(String id){
		return ds.get(DecisionTree.class, id);
	}
	
	public void storeTree(DecisionTree tree){
		ds.save(tree);
	}
	
    public void listCollection(){
    	DBCollection coll =  db.getCollection("DecisionTree");
		
		DBCursor cur = coll.find();

        while(cur.hasNext()) {
            System.out.println(cur.next());
        }
    }
	
	public static void main(String[] args){
		com.bryanreinero.hum.treeStore.TreeStore store = com.bryanreinero.hum.treeStore.TreeStore.getInstance();
		DecisionTree config = store.getTree("root");
		System.out.println(config);
		
		TreeStore.getInstance().storeTree(store.getTree("root"));
		TreeStore.getInstance().listCollection();
		
	}

}
