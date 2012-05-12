  package com.bryanreinero.hum.DataStore;

import java.io.ByteArrayInputStream;

import java.util.Map;

import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.server.DataAccessObject;
import com.bryanreinero.hum.util.UpdatingMap;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

public class HUMElementDS<K, V> implements DataAccessObject<K, V>  {
	
	private Map <K, V> map = new UpdatingMap<K, V>();
	
	@Override
	public V get(K key){
		
		synchronized(map){
			if(map.containsKey(key))
				return map.get(key);
		}

		// spawn a new thread to get the config from central control
		ConfigRetriever retriever = new ConfigRetriever(key);
		Thread retrieverThread = new Thread(retriever);
		retrieverThread.start();
		
		try {
			// wait for the retrieval to finish, but not forever
			retrieverThread.join(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (map) {
			return map.get(key);
		}
	}
	
	private class ConfigRetriever implements Runnable {
		
		private K key = null;
		
		public ConfigRetriever(K key){
			this.key = key;
		}
		
		@Override
		public void run() {
			retrieveConfiguration(key);
		}
		
		@SuppressWarnings("unchecked")
		private void retrieveConfiguration(K key) {
			
		    String centralControl = "http://127.0.0.1/~breinero/";
			V newObj = null;
			String responseBody = null;
			
	        HttpClient httpclient = new DefaultHttpClient();
	        
	        try {
	            HttpGet httpget = new HttpGet(centralControl+key+".xml");
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            responseBody = httpclient.execute(httpget, responseHandler);

	        } catch (Exception e) {
				e.printStackTrace();
			} finally {
	            // When HttpClient instance is no longer needed,
	            // shut down the connection manager to ensure
	            // immediate deallocation of all system resources
	            httpclient.getConnectionManager().shutdown();
	        }
			
			if(responseBody != null){
				try {
					XMLParser parser = new XMLParser();
					newObj = (V)parser.parse(new ByteArrayInputStream(responseBody.getBytes("UTF-8")));
				} catch (SAXException e) {
					//configRetirer.enqueue(key, 3600000l);
					
					e.printStackTrace();
				} catch (Exception e) {
					//configRetirer.enqueue(key, 120000l);
					
					e.printStackTrace();
				}
				
			}	
			synchronized(map){
				map.put(key, newObj);
			}
			//configRetirer.enqueue(key);
		}
	}

	@Override
	public void persist(V object) {
		// TODO Auto-generated method stub
		
	}

	public void setCache(Map<K, V> cache) {
		map = cache;
	}
}
