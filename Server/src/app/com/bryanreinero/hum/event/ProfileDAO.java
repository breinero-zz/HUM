package com.bryanreinero.hum.event;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import com.bryanreinero.hum.persistence.Deserializer;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import org.joda.time.DateTime;

public class ProfileDAO implements Deserializer <String, Profile> {
	
	private final DBCollection collection;
	
	public ProfileDAO ( DBCollection collection ) {
		this.collection = collection;
	}

	@Override
	public Profile deserialize(String input) throws IllegalArgumentException {
		DBCursor cursor = collection.find( (DBObject) JSON.parse( input ) );
		
		Set<DBObject> events = new HashSet<DBObject>();
		
		for ( DBObject object : cursor )
			events.add( object );
		
		return convertDBObjectToProfile( events );
	}
	
	private Profile convertDBObjectToProfile( Set<DBObject> events ) {
		Profile profile = new Profile();
		
		for( DBObject event : events ) {
			Event current = null;
			
			switch ( Event.Type.getType( (String)event.get("type") ) ) {
			case impression : 
				current = convertToImpression( event );
				break;
			case click : 
				current = convertToClick( event );
				break;
			case conversion : 
				current = convertToConversion( event );
				break;
			case tag : 
				current = new Tag();
				break;
			}
			
			initializeEvent(current, event);
			profile.addEvent(current);
		}
		return profile;
	}
	
	private void initializeEvent( Event event, DBObject object ) {
		
		@SuppressWarnings("unchecked")
		Set<Attribute> attributes = convertToAttributeSet(
				(List<DBObject>) object.get( Event.ATTRIBUTE_FIELD ));
		event.setAttributes(attributes);
		
		event.setClient( (Integer)object.get( Event.CLIENT_FIELD ) );
		Date date = (Date)object.get( Event.DATE_FIELD );
		DateTime dateTime = new DateTime( date );
		event.setDate( dateTime );
	}
	
	private Impression convertToImpression( DBObject event ) {
		Impression impression = new Impression();
		
		Creative creative = convertToCreative( 
				(DBObject)event.get( Impression.CREATIVE_FIELD ) 
				); 
		impression.setCreative(creative);
		Placement placement = convertToPlacement((DBObject) event
				.get(Impression.PLACEMENT_FIELD));
		impression.setPlacement( placement ); 
		int campaign = (Integer)event.get( Impression.CAMPAIGN_FIELD );
		impression.setCampaign( campaign );
		
		return impression;
	}
	
	private Click convertToClick( DBObject event ) {
		Click click = new Click();
		
		Creative creative = convertToCreative( 
				(DBObject)event.get( Impression.CREATIVE_FIELD ) 
				); 
		click.setCreative( creative );
		Placement placement = convertToPlacement( (DBObject)event.get( Impression.PLACEMENT_FIELD ) );
		click.setPlacement( placement ); 
		int campaign = (Integer)event.get( Impression.CAMPAIGN_FIELD );
		click.setCampaign( campaign );
		
		return click;
	}
	
	private Conversion convertToConversion( DBObject event ) {
		Conversion conversion = new Conversion( 
				(String)event.get( Impression.CREATIVE_FIELD ) 
		);
		
		return conversion;
	}
	
	private Creative convertToCreative( DBObject object ) {
		
		String name = (String)object.get( Creative.NAME_FIELD );
		int height = (Integer)object.get( Creative.HEIGHT_FIELD );
		int width = (Integer)object.get( Creative.WIDTH_FIELD );
		
		@SuppressWarnings("unchecked")
		Set<Attribute> attributes = convertToAttributeSet((List<DBObject>) object
				.get(Creative.ATTRIBUTES_FIELD));
		
		return new Creative( name, width, height, attributes );
	}

	private Placement convertToPlacement( DBObject object ) {
		
		String site = (String)object.get( Placement.SITE_FIELD );
		String page = (String)object.get( Placement.PAGE_FIELD );
		String position = (String)object.get( Placement.POSITION_FIELD );
		String publisher = (String)object.get( Placement.PUBLISHER_FIELD );
		
		@SuppressWarnings("unchecked")
		Set<Attribute> attributes = convertToAttributeSet((List<DBObject>) object
				.get(Placement.ATTRIBUTES_FIELD));

		return new Placement( site, page, position, publisher, attributes );
	}

	private Set<Attribute> convertToAttributeSet( List<DBObject> object ) {
		Set<Attribute> attributes = new HashSet<Attribute>();
		
		for( DBObject obj : object )
			attributes.add( convertToAttribute( obj )  );
		
		return attributes;
	}
	
	private Attribute convertToAttribute( DBObject object ) {
		
		String name = (String)object.get( Attribute.NAME_FIELD );
		String value = (String)object.get( Attribute.VALUE_FIELD );
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setValue(value);
		return attribute;
	}
}
