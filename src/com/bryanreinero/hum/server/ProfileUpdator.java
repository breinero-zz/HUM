package com.bryanreinero.hum.server;

import java.util.Iterator;

public class ProfileUpdator {

	public void updateProfiles(Executor executor){
		Iterator<String>  iterator = executor.getProfileRecords().keySet().iterator();
		
		while(iterator.hasNext()){
			String name = iterator.next();
			String value = executor.getProfileRecord(name);
			
			//every profile event needs to be nested inside the user record
		}
	}
}