  package com.bryanreinero.hum.treeStore;

import java.io.ByteArrayInputStream;

import java.util.HashMap;
import java.util.Set;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.parser.XMLParser;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;

public class TreeStore {
	
	private static TreeStore store = null;
	private static final HashMap <String, DecisionTree> treeMap = new HashMap<String, DecisionTree>();
	static String centralControl = "http://127.0.0.1/~breinero/";
	private ConfigKiller configRetirer = ConfigKiller.getInstance();
	
	private static Thread ConfigRetirerThread;
	private static Thread ConfigCleanserThread;
	
	public synchronized static TreeStore getInstance(){
		if(store == null)
			store = new TreeStore();
		return store;
	}
	
	private TreeStore(){

		// start thread which removes configs once they have expired
		// TODO: set the following threads as daemons from the context listner
		ConfigRetirerThread = new Thread(configRetirer);
		ConfigRetirerThread.setName("ConfigRetirer");
		ConfigRetirerThread.setDaemon(true);
		ConfigRetirerThread.start();
		
		// start thread which cleans up configs which have gone out of scope
		ConfigCleanserThread = new Thread(new StoreCleanser());
		ConfigCleanserThread.setName("ConfigCleanser");
		ConfigCleanserThread.setDaemon(true);
		ConfigCleanserThread.start();
	}
	
	public DecisionTree getTree(String key){
		
		// return immediately if the config is
		// already in memory
		synchronized(treeMap){
			if(treeMap.containsKey(key))
				return treeMap.get(key);
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

		synchronized(treeMap){
			return treeMap.get(key);
		}
	}
	
	public void flushConfig(String configID){
		synchronized (treeMap){
			treeMap.remove(configID);
		}
	}
	
	public Set<String> getKeySet(){
		synchronized(treeMap){
			return treeMap.keySet();
		}
	}
	
	private class ConfigRetriever implements Runnable {
		
		private String key = null;
		
		public ConfigRetriever(String key){
			this.key = key;
		}
		
		@Override
		public void run() {
			retrieveConfiguration(key);
		}
		
		private void retrieveConfiguration(String key) {
			DecisionTree newTree = null;
			String responseBody = null;
			
	        HttpClient httpclient = new DefaultHttpClient();
	        
	        try {
	            HttpGet httpget = new HttpGet(TreeStore.centralControl+key+".xml");
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
					newTree = parser.parse(new ByteArrayInputStream(responseBody.getBytes("UTF-8")));
				} catch (SAXException e) {
					synchronized(treeMap){
						treeMap.put(key, null);
					}
					configRetirer.enqueue(key, 3600000l);
					
					e.printStackTrace();
				} catch (Exception e) {
					synchronized(treeMap){
						treeMap.put(key, null);
					}
					configRetirer.enqueue(key, 120000l);
					
					e.printStackTrace();
				}
				
			}	
			synchronized(treeMap){
				treeMap.put(key, newTree);
			}
			configRetirer.enqueue(key);
		}
	}
}
