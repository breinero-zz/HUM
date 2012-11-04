package com.bryanreinero.hum.event;

import java.util.List;
import java.util.StringTokenizer;

import org.bson.BSONObject;

public class Profile {
	
	private List<BSONObject> documents;
	
	public int count( List<BSONObject> docs ) {
		if ( docs == null )
			return 0;
		
		return docs.size();
	}
	
	private Object getField ( Object object, StringTokenizer tokenizer ) {
		if ( tokenizer.hasMoreTokens() && object instanceof BSONObject ) {
			String targetName = tokenizer.nextToken(".");
			return getField( ((BSONObject)object).get(targetName ), tokenizer  );
		}
		return object;	
	}
	
	private boolean checkNull(Object a, Object b ) { 
		if( a == null && b == null)
			return true;
 
		if( ( a == null && b != null ) || (a != null && b == null ) )
				return false;
		
		return true;
	}
}
