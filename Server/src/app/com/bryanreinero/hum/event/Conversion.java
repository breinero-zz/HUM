package com.bryanreinero.hum.event;

import java.util.List;

public class Conversion {

	private List<Object> attributes;
	private int client;
	private List<Event> events;
	private String name;
	
	public Conversion(String name, List<Object> attributes, int client, List<Event> events) {
		this.name = name;
		this.attributes = attributes;
		this.client = client;
		this.events = events;
	}

	public List<Object> getAttributes() {
		return attributes;
	}

	public int getClient() {
		return client;
	}

	public List<Event> getEvents() {
		return events;
	}

	public String getName() {
		return name;
	}

}
