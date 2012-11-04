package com.bryanreinero.hum.event;

import java.util.List;

public class Placement {
	
	private String site;
	private String page;
	private String position;
	private String publisher;
	private List attributes;
	
	public Placement ( String site, String page, String position, String publisher, List attributes ) {
		this.site = site;
		this.page = page;
		this.position = position;
		this.publisher = publisher;
		this.attributes = attributes;
	}

	public String getSite() {
		return site;
	}

	public String getPage() {
		return page;
	}

	public String getPosition() {
		return position;
	}

	public String getPublisher() {
		return publisher;
	}

	public List getAttributes() {
		return attributes;
	}
	
	
}
