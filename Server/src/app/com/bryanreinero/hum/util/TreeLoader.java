package com.bryanreinero.hum.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.xml.sax.SAXException;

import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.persistence.ConfigDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class TreeLoader {
	MongoClient connection;
	DB db;
	DBCollection collection;
	private XMLParser parser;
	
	private ConfigDAO treestore;
	
	public TreeLoader() throws UnknownHostException, MongoException, SAXException{
		connection = new MongoClient( "localhost" , 27017 );
		db = connection.getDB("configurations");
		collection = db.getCollection("ConfigurationTree");
		parser = new XMLParser();
		treestore = new ConfigDAO(connection, "configurations");
		treestore.setDeserializer(parser);
	}
	
	private static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		}
		finally {
			stream.close();
		}
	}
	
	public static void main(String[] args){
		try {
			TreeLoader loader = new TreeLoader();
			String xml = TreeLoader.readFile(args[0]);
			DecisionTree tree = loader.parser.parse(new ByteArrayInputStream(xml.getBytes()));
			
			if(tree == null){
				System.out.println("tree was null. Exiting");
				return;
			}
			
			String name = tree.getName();
			
			BasicDBObject object = new BasicDBObject();
			object.put("name", name);
			object.put("type", tree.getTimeToLive());
			object.put("client", 0);
			
			BasicDBObject modify = new BasicDBObject("value" , xml);
			BasicDBObject set = new BasicDBObject("$set", modify);
			
			loader.collection.update(object, set, true, false);
			
			String lastErr = loader.db.getLastError().getErrorMessage();
			
			System.out.println(lastErr);
			
			DecisionTree clone = loader.treestore.get(name);
			System.out.println(clone);
				
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
