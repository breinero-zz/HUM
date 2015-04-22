package com.bryanreinero.hum.server;

import java.util.HashMap;
import java.util.Map;

public class DAOs  implements DAOService {
	private Map<String, DAO> daos = new HashMap<String, DAO>();
	
	public Object execute ( String store, Map<String, Object> request ) {
		
		if( !daos.containsKey( store) )
			return "Requested store: "+store+" does not exist";
		
		DAO target = daos.get(store);
		
		return target.execute(request);
	}
	
	public void put( String name, DAO store ) {
		daos.put(name, store);
	}
}
