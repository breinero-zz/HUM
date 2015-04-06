package com.bryanreinero.hum.util;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class JSONParseTest {
	
	public static void main(String[] args){
		String jsonString = "{ date : { \"$date\" : 1339817252165 }, userid : 1234, client : 0, action : \"impression\", site : \"yahoo\", creative : \"dynamic720x120\" }";
		DBObject obj = (DBObject)JSON.parse(jsonString);
	}

}
