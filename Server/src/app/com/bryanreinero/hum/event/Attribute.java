package com.bryanreinero.hum.event;

public class Attribute {
	
	private String name;
	private Object value;
	
	public static final String NAME_FIELD = "name";
	public static final String VALUE_FIELD = "value";
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setValue( String value ) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	@Override
	public boolean equals ( Object object ) {
		if(object instanceof Attribute ) {
			Attribute attribute = (Attribute)object;
			return ( name.equals( attribute.getName() ) && value.equals( attribute.getValue() ) );
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (new String(name+" "+value)).hashCode();
	}
}
