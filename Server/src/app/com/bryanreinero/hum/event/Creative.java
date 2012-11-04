package com.bryanreinero.hum.event;

import java.util.List;

public class Creative {
	
	private int width;
	private int height;
	private List attributes;
	private String name;
	
	public Creative ( String name, int width, int height, List attributes ) {
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
	public List getAttributes() {
		return attributes;
	}
	public String getName() {
		return name;
	}
}
