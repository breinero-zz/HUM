package com.bryanreinero.hum.event;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Persistor {
	
	public static DBObject convertToDBObject ( Event event ) {
		DBObject evn = new BasicDBObject( "type", event.getType());
		evn.put("attribtes", event.getAttributes());
		
		return evn;
	}
	
	public static DBObject convertToDBObject (Creative creative) {
		DBObject ctv = new BasicDBObject( "name", creative.getName());
		ctv.put("width", creative.getWidth());
		ctv.put("height", creative.getHeight());
		ctv.put("attribtes", creative.getAttributes());
		
		return ctv;
	}
	
	public static DBObject convertToDBObject ( Placement placement ) {
		DBObject plc = new BasicDBObject( "site", placement.getSite());
		plc.put("page", placement.getPage());
		plc.put("position", placement.getPosition());
		plc.put("publisher", placement.getPublisher());
		plc.put("attribtes", placement.getAttributes());
		
		return plc;
	}
	
	
	public static DBObject convertToDBObject ( Impression impression ) {
		DBObject imp = new BasicDBObject( "type", "impression");
		DBObject creative  = convertToDBObject(impression.getCreative());
		imp.put("creative", creative);
		DBObject placement  = convertToDBObject(impression.getPlacement());
		imp.put("placement", placement);
		imp.put("attributes", impression.getAttributes());
		imp.put("campaign", impression.getCampaign());
	
		return imp;
	}
	
	public DBObject convertToDBObject (Click click) {
		DBObject clk = new BasicDBObject( "type", "click");
		DBObject creative  = convertToDBObject(click.getCreative());
		clk.put("creative", creative);
		DBObject placement  = convertToDBObject(click.getPlacement());
		clk.put("placement", placement);
		clk.put("attributes", click.getAttributes());
		clk.put("campaign", click.getCampaign());
	
		return clk;
	}
	
	public static DBObject convertToDBObject ( Conversion conversion ) {
		DBObject cnv = new BasicDBObject("type", "conversion");
		cnv.put("name", conversion.getName());
		List<DBObject> events = new ArrayList<DBObject>();
		for ( Event event : conversion.getEvents() ){
			events.add( convertToDBObject( event ) );
		}
		cnv.put("events", events);
		cnv.put("attributes", conversion.getAttributes());
		
		return cnv;
	}

}
