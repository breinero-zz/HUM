package com.bryanreinero.hum.event;

import java.util.Set;
import org.joda.time.DateTime;

public abstract class Event {
	
	private Set<Attribute> attributes;
	private int client;
	private DateTime date;
	private String type;
	
	public static final String ATTRIBUTE_FIELD = "attributes";
	public static final String CLIENT_FIELD = "client";
	public static final String DATE_FIELD = "date";
	public static final String TYPE_FIELD = "type";
	
	public enum Type {
		unknown("unknown"), impression("impression"), click("click"),
		conversion("conversion"), tag("tag");
		
		private String name;
		
		private Type ( String name ) {
			this.name = name;
		}
		
		public static Type getType( String name ) {
			if( name.compareTo("impression") == 0 )
				return impression;
			if( name.compareTo("click") == 0 )
				return click;
			if( name.compareTo("converstion")  == 0 )
				return conversion;
			if( name.compareTo("tag")  == 0 )
				return tag;
			
			return unknown;
		}
		
		public String toString () {
			return name;
		}
	}
	
	public Set<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
