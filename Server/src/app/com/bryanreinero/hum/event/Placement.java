package com.bryanreinero.hum.event;

import java.util.Set;

public class Placement {
	
	private String site;
	private String page;
	private String position;
	private String publisher;
	private Set<Attribute> attributes;
	
	public static final String SITE_FIELD = "site";
	public static final String PAGE_FIELD = "page";
	public static final String POSITION_FIELD = "position";
	public static final String PUBLISHER_FIELD = "publisher";
	public static final String ATTRIBUTES_FIELD = "attributes";
	
	
	public Placement ( String site, String page, String position, String publisher, Set<Attribute> attributes ) {
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

	public Set<Attribute> getAttributes() {
		return attributes;
	}
	
	
}
