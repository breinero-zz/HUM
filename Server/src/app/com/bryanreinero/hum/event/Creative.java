package com.bryanreinero.hum.event;

import java.util.Set;

public class Creative {
	
	private int width;
	private int height;
	private Set<Attribute> attributes;
	private String name;
	
	public static final String NAME_FIELD = "name";
	public static final String WIDTH_FIELD = "width";
	public static final String HEIGHT_FIELD = "height";
	public static final String ATTRIBUTES_FIELD = "attributes";
	
	public Creative ( String name, int width, int height, Set<Attribute> attributes ) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.attributes = attributes;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Set<Attribute> getAttributes() {
		return attributes;
	}
	public String getName() {
		return name;
	}
}
