package com.bryanreinero.hum.server;

import java.util.HashMap;
import java.util.Map;

public class DAOs {
	private Map<String, DAO> daos = new HashMap<String, DAO>();
	
	public Object execute ( String store, Map<String, Object> request ) {
		
		if( !daos.containsKey( store) )
			return "Requested store: "+store+" does not exist";
		
		return daos.get(store).execute(request);
	}
	
	public void put( String name, DAO store ) {
		daos.put(name, store);
	}
}
