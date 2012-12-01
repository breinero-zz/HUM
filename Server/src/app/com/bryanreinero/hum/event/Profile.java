package com.bryanreinero.hum.event;

import java.util.HashSet;
import java.util.Set;

import org.bson.BSONObject;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.*;

public class Profile extends HumElement implements Visitable {
	
	private Set<Event> events = new HashSet<Event>();
	private Set<Attribute> attributes = new HashSet<Attribute>();
	
	public int count( Set<BSONObject> docs ) {
		if ( docs == null )
			return 0;
		
		return docs.size();
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	public void addEvent( Event event ) {
		events.add( event );
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute( Attribute attribute ) {
		attributes.add(attribute);
	}

	@Override
	public void addParent( HumElement parent ) {
		parent.addChild( this );
	}

	@Override
	public void accept( Visitor visitor) {
		visitor.visit( this );
	}
}
