package com.bryanreinero.hum.event;

public class Conversion extends Event {

	private String name;
	
	public static final String NAME_FIELD = "name";
	
	public static final Type type = Event.Type.conversion;
	
	public Conversion(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
